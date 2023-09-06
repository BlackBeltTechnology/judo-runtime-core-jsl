package hu.blackbelt.judo.runtime.core.jsl.fixture;

/*-
 * #%L
 * JUDO Runtime Core :: JUDO Language Specification DSL Integration Tests
 * %%
 * Copyright (C) 2018 - 2023 BlackBelt Technology
 * %%
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is
 * available at https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 * #L%
 */

import hu.blackbelt.judo.meta.rdbms.RdbmsTable;
import hu.blackbelt.judo.meta.rdbms.runtime.RdbmsModel;
import hu.blackbelt.judo.meta.rdbms.runtime.RdbmsUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.emf.common.util.BasicEList;
import org.hsqldb.jdbc.JDBCDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.UUID;
import java.util.function.Supplier;

@Slf4j
public class JudoDatasourceFixture {

//    static {
//        System.setProperty("user.timezone", "UTC");
//        //         TZ: 'GMT+2'
//        //        PGTZ: 'GMT+2'
//    }

    public static final String CONTAINER_NONE = "none";
    public static final String CONTAINER_POSTGRESQL = "postgresql";
    public static final String CONTAINER_YUGABYTEDB = "yugabytedb";

    public static final String DIALECT_HSQLDB = "hsqldb";
    public static final String DIALECT_POSTGRESQL = "postgresql";

    @Getter
    protected String dialect = System.getProperty("dialect", DIALECT_HSQLDB);

    @Getter
    protected String container = System.getProperty("container", CONTAINER_NONE);

    @Getter
    protected String timezone = System.getProperty("tz", "GMT");

    @Getter
    protected DataSource dataSource;


    @Getter
    PlatformTransactionManager transactionManager;

    @Getter
    public JdbcDatabaseContainer sqlContainer;

    public void setupDatabase() {
        if (dialect.equals(DIALECT_POSTGRESQL)) {
            if (container.equals(CONTAINER_NONE) || container.equals(CONTAINER_POSTGRESQL)) {
                sqlContainer =
                        (PostgreSQLContainer) new PostgreSQLContainer("postgres:latest").withStartupTimeout(Duration.ofSeconds(600))
                                                                                        .withEnv("TZ", timezone)
                                                                                        .withEnv("PGTZ", timezone);
            } else if (container.equals(CONTAINER_YUGABYTEDB)) {
                sqlContainer =
                        (YugabytedbSQLContainer) new YugabytedbSQLContainer().withStartupTimeout(Duration.ofSeconds(600))
                                                                             .withEnv("TZ", timezone)
                                                                             .withEnv("PGTZ", timezone);
            }
        }
    }

    public void teardownDatasource() {
        if (sqlContainer != null && sqlContainer.isRunning()) {
            sqlContainer.stop();
        }
    }

    public void truncateTables(RdbmsModel rdbmsModel) {
        RdbmsUtils rdbmsUtils = new RdbmsUtils(rdbmsModel.getResourceSet());
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            for (RdbmsTable rdbmsTable : rdbmsUtils.getRdbmsTables().orElse(new BasicEList<>())) {
                log.debug("Truncating table: %s (%s)".formatted(rdbmsTable.getName(), rdbmsTable.getSqlName()));
                if (dialect.equals(DIALECT_POSTGRESQL)) {
                    statement.execute("TRUNCATE TABLE %s RESTART IDENTITY CASCADE;".formatted(rdbmsTable.getSqlName()));
                } else if (dialect.equals(DIALECT_HSQLDB)) {
                    statement.execute("TRUNCATE TABLE %s RESTART IDENTITY AND COMMIT NO CHECK".formatted(rdbmsTable.getSqlName()));
                }
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Could not truncate tables", throwables);
        }
    }

    public void dropTables(RdbmsModel rdbmsModel) {
        RdbmsUtils rdbmsUtils = new RdbmsUtils(rdbmsModel.getResourceSet());
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            for (RdbmsTable rdbmsTable : rdbmsUtils.getRdbmsTables().orElse(new BasicEList<>())) {
                log.debug("Drop table: %s (%s)".formatted(rdbmsTable.getName(), rdbmsTable.getSqlName()));
                statement.execute("DROP TABLE %s CASCADE;".formatted(rdbmsTable.getSqlName()));
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Could not drop tables", throwables);
        }
    }

    public void prepareDatasources() {
        if (dialect.equals(DIALECT_HSQLDB)) {
            final JDBCDataSource ds = new JDBCDataSource();
            ds.setUrl("jdbc:hsqldb:mem:" + UUID.randomUUID());
            ds.setUser("sa");
            ds.setPassword("saPassword");
            dataSource = ds;
        } else if (dialect.equals(DIALECT_POSTGRESQL)) {
            sqlContainer.start();
            final PGSimpleDataSource ds = new PGSimpleDataSource();
            ds.setURL(sqlContainer.getJdbcUrl());
            ds.setUser(sqlContainer.getUsername());
            ds.setPassword(sqlContainer.getPassword());
            dataSource = ds;
        } else {
            throw new IllegalStateException("Unsupported dialect: " + dialect);
        }
        transactionManager = new DataSourceTransactionManager(dataSource);
    }

    public <T extends Throwable> T assertThrowsInTransaction(final Class<T> expectedType, final Executable executable) {
        return Assertions.assertThrows(expectedType, () -> {
            TransactionStatus transactionStatus = getTransactionManager().getTransaction(new DefaultTransactionDefinition());
            try {
                executable.execute();
            } catch (Exception e) {
                if (!transactionStatus.isCompleted()) {
                    getTransactionManager().rollback(transactionStatus);
                }
            } finally {
                if (!transactionStatus.isCompleted()) {
                    getTransactionManager().commit(transactionStatus);
                }
            }
        });
    }

    public <R> R runInTransaction(Supplier<R> executable) {
        TransactionStatus transactionStatus = getTransactionManager().getTransaction(new DefaultTransactionDefinition());
        try {
            return executable.get();
        } catch (Exception e) {
            if (!transactionStatus.isCompleted()) {
                getTransactionManager().rollback(transactionStatus);
            }
        } finally {
            if (!transactionStatus.isCompleted()) {
                getTransactionManager().commit(transactionStatus);
            }
        }
        return null;
    }

}
