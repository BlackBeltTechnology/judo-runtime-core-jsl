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
import hu.blackbelt.judo.runtime.core.jsl.fixture.RdbmsDatasourceByClassExtension;
import hu.blackbelt.judo.runtime.core.jsl.fixture.RdbmsDatasourceFixture;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.testcontainers.containers.JdbcDatabaseContainer;

import java.io.File;

import static hu.blackbelt.judo.runtime.core.jsl.fixture.RdbmsDatasourceFixture.DIALECT_HSQLDB;
import static hu.blackbelt.judo.runtime.core.jsl.fixture.RdbmsDatasourceFixture.DIALECT_POSTGRESQL;

@Slf4j
@ExtendWith(RdbmsDatasourceByClassExtension.class)
abstract class AbstractJslTest {

    Injector injector;

    TransactionStatus transactionStatus;

    @BeforeEach
    protected void init(RdbmsDatasourceFixture datasource) throws Exception {
        JudoModelLoader modelHolder;

        if (datasource.getDialect().equals(DIALECT_POSTGRESQL)) {
            modelHolder = JudoModelLoader.loadFromDirectory(getModelName(), new File("target/generated-test-sources/model"), new PostgresqlDialect(), true);
            JdbcDatabaseContainer sqlContainer = datasource.getSqlContainer();
            JudoPostgresqlModules judoPostgresqlModules = JudoPostgresqlModules.builder()
                                                                               .databaseName(sqlContainer.getDatabaseName())
                                                                               .user(sqlContainer.getUsername())
                                                                               .password(sqlContainer.getPassword())
                                                                               .port(sqlContainer.getFirstMappedPort())
                                                                               .build();

            injector = Guice.createInjector(judoPostgresqlModules, getModelDaoModule(), new JudoDefaultModule(this, modelHolder));
        } else if (datasource.getDialect().equals(DIALECT_HSQLDB)) {
            modelHolder = JudoModelLoader.loadFromDirectory(getModelName(), new File("target/generated-test-sources/model"), new HsqldbDialect(), true);
            injector = Guice.createInjector(JudoHsqldbModules.builder().build(), getModelDaoModule(), new JudoDefaultModule(this, modelHolder));
        }

        beginTransaction();
    }

    public void beginTransaction() {
        transactionStatus = injector.getInstance(PlatformTransactionManager.class).getTransaction(new DefaultTransactionDefinition());
    }

    public void endTransaction() {
        injector.getInstance(PlatformTransactionManager.class).rollback(transactionStatus);
    }

    @AfterEach
    protected void teardown() {
        endTransaction();
    }

    public abstract Module getModelDaoModule();

    public abstract String getModelName();

}

