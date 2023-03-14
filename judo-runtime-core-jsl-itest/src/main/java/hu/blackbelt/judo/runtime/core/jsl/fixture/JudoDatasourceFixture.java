package hu.blackbelt.judo.runtime.core.jsl.fixture;

import lombok.Getter;
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
import java.sql.*;
import java.time.Duration;
import java.util.function.Supplier;

public class JudoDatasourceFixture {

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
    protected DataSource dataSource;


    @Getter
    PlatformTransactionManager transactionManager;

    @Getter
    public JdbcDatabaseContainer sqlContainer;

    public void setupDatabase() {
        if (dialect.equals(DIALECT_POSTGRESQL)) {
            if (container.equals(CONTAINER_NONE) || container.equals(CONTAINER_POSTGRESQL)) {
                sqlContainer = (PostgreSQLContainer) new PostgreSQLContainer("postgres:latest").withStartupTimeout(Duration.ofSeconds(600));
            } else if (container.equals(CONTAINER_YUGABYTEDB)) {
                sqlContainer = (YugabytedbSQLContainer) new YugabytedbSQLContainer().withStartupTimeout(Duration.ofSeconds(600));
            }
        }
    }

    public void teardownDatasource() {
        if (sqlContainer != null && sqlContainer.isRunning()) {
            sqlContainer.stop();
        }
    }

    public void dropSchema() {
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            if (dialect.equals(DIALECT_POSTGRESQL)) {
                statement.execute("select 'drop table \"' || tablename || '\" cascade;' from pg_tables;");
            } else if (dialect.equals(DIALECT_HSQLDB)) {
                statement.execute("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK");
                //statement.execute("DROP SCHEMA PUBLIC CASCADE");
            }
        } catch (SQLException throwables) {
            throw new RuntimeException("Could not drop schema", throwables);
        }

    }

    public void prepareDatasources() {
        if (dialect.equals(DIALECT_HSQLDB)) {
            final JDBCDataSource ds = new JDBCDataSource();
            ds.setUrl("jdbc:hsqldb:mem:memdb");
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
