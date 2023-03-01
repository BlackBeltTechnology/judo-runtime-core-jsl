package hu.blackbelt.judo.runtime.core.jsl;

/*-
 * #%L
 * JUDO Runtime Core :: JUDO Language Specification DSL Integration Tests
 * %%
 * Copyright (C) 2018 - 2022 BlackBelt Technology
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

import com.google.inject.Module;
import com.google.inject.*;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoDefaultModule;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoModelLoader;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.hsqldb.JudoHsqldbModules;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.postgresql.JudoPostgresqlModules;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;
import hu.blackbelt.judo.runtime.core.dao.rdbms.postgresql.PostgresqlDialect;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceByClassExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.testcontainers.containers.JdbcDatabaseContainer;

import java.io.File;

import static hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture.DIALECT_HSQLDB;
import static hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture.DIALECT_POSTGRESQL;

@Slf4j
@ExtendWith(JudoDatasourceByClassExtension.class)
abstract class AbstractJslTest {

    public static final String MODEL_SOURCES = "target/generated-test-sources/model";

    Injector injector;

    protected TransactionStatus transactionStatus;

    protected String dialect;

    public abstract Module getModelDaoModule();

    public abstract String getModelName();

    @BeforeEach
    protected void init(JudoDatasourceFixture datasource) throws Exception {
        JudoModelLoader modelHolder;

        dialect = datasource.getDialect();
        if (DIALECT_POSTGRESQL.equals(dialect)) {
            modelHolder = JudoModelLoader.loadFromDirectory(getModelName(), new File(MODEL_SOURCES), new PostgresqlDialect(), true);
            JdbcDatabaseContainer sqlContainer = datasource.getSqlContainer();
            JudoPostgresqlModules judoPostgresqlModules = JudoPostgresqlModules.builder()
                                                                               .databaseName(sqlContainer.getDatabaseName())
                                                                               .user(sqlContainer.getUsername())
                                                                               .password(sqlContainer.getPassword())
                                                                               .port(sqlContainer.getFirstMappedPort())
                                                                               .build();

            injector = Guice.createInjector(judoPostgresqlModules, getModelDaoModule(), new JudoDefaultModule(this, modelHolder));
        } else if (DIALECT_HSQLDB.equals(dialect)) {
            modelHolder = JudoModelLoader.loadFromDirectory(getModelName(), new File(MODEL_SOURCES), new HsqldbDialect(), true);
            injector = Guice.createInjector(JudoHsqldbModules.builder().build(), getModelDaoModule(), new JudoDefaultModule(this, modelHolder));
        } else {
            throw new IllegalArgumentException("Unsupported dialect: " + dialect);
        }

        transactionStatus = beginTransaction();
    }

    @AfterEach
    void tearDown() {
        rollbackTransaction();
    }

    private PlatformTransactionManager getTransactionManager() {
        return injector.getInstance(PlatformTransactionManager.class);
    }

    protected TransactionStatus beginTransaction() {
        return getTransactionManager().getTransaction(new DefaultTransactionDefinition());
    }

    protected void commitTransaction() {
        getTransactionManager().commit(transactionStatus);
    }

    protected void rollbackTransaction() {
        getTransactionManager().rollback(transactionStatus);
    }

    protected Object createSavePoint() {
        return transactionStatus.createSavepoint();
    }

    protected void rollbackToSavePoint(Object savePoint) {
        transactionStatus.rollbackToSavepoint(savePoint);
    }

}
