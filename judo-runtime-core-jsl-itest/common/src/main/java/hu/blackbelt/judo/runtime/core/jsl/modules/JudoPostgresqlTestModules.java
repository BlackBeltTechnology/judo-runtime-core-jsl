package hu.blackbelt.judo.runtime.core.jsl.modules;

/*-
 * #%L
 * JUDO Runtime Core :: Parent
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


import com.google.inject.Singleton;
import com.google.inject.name.Names;
import hu.blackbelt.judo.dispatcher.api.Sequence;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoModule;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.PlatformTransactionManagerProvider;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.postgresql.*;
import hu.blackbelt.judo.runtime.core.dao.rdbms.Dialect;
import hu.blackbelt.judo.runtime.core.dao.rdbms.RdbmsInit;
import hu.blackbelt.judo.runtime.core.dao.rdbms.RdbmsParameterMapper;
import hu.blackbelt.judo.runtime.core.dao.rdbms.postgresql.PostgresqlDialect;
import hu.blackbelt.judo.runtime.core.dao.rdbms.postgresql.PostgresqlRdbmsInit;
import hu.blackbelt.judo.runtime.core.dao.rdbms.query.mappers.MapperFactory;
import lombok.Builder;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public class JudoPostgresqlTestModules extends JudoModule {

    String host = "localhost";
    Integer port = 5432;
    String user = "judo";
    String password = "judo";
    String databaseName = "judo";
    Integer poolSize = 10;

    private DataSource dataSource;

    private PostgresqlRdbmsInit postgresqlRdbmsInit;

    public static class JudoPostgresqlTestModulesBuilder {
        String host = "localhost";
        Integer port = 5432;
        String user = "judo";
        String password = "judo";
        String databaseName = "judo";
        Integer poolSize = 10;
    }

    @Builder
    private JudoPostgresqlTestModules(String host, Integer port, String user, String password, String databaseName, Integer poolSize, DataSource dataSource, PostgresqlRdbmsInit postgresqlRdbmsInit) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.databaseName = databaseName;
        this.poolSize = poolSize;
        this.dataSource = dataSource;
        this.postgresqlRdbmsInit = postgresqlRdbmsInit;
    }

    @Override
    protected void configureDialect() {
        bind(Dialect.class).toInstance(new PostgresqlDialect());
    }

    @Override
    protected void configureMapperFactory() {
        bind(MapperFactory.class).toProvider(PostgresqlMapperFactoryProvider.class).in(Singleton.class);
    }

    @Override
    protected void configureRdbmsParameterMapper() {
        bind(RdbmsParameterMapper.class).toProvider(PostgresqlRdbmsParameterMapperProvider.class).in(Singleton.class);
    }

    @Override
    protected void configureDataSource() {
        if(dataSource != null) {
            bind(DataSource.class).toInstance(dataSource);
        } else {
            bind(DataSource.class).toProvider(PostgresqlDataSourceProvider.class).in(Singleton.class);
        }
    }

    @Override
    protected void configureSequence() {
        bind(Sequence.class).toProvider(PostgresqlRdbmsSequenceProvider.class).in(Singleton.class);
    }

    @Override
    protected void configureTransactionManager() {
        bind(Integer.class).annotatedWith(Names.named(PostgresqlDataSourceProvider.POSTGRESQL_PORT)).toInstance(port);
        bind(String.class).annotatedWith(Names.named(PostgresqlDataSourceProvider.POSTGRESQL_HOST)).toInstance(host);
        bind(String.class).annotatedWith(Names.named(PostgresqlDataSourceProvider.POSTGRESQL_USER)).toInstance(user);
        bind(String.class).annotatedWith(Names.named(PostgresqlDataSourceProvider.POSTGRESQL_PASSWORD)).toInstance(password);
        bind(String.class).annotatedWith(Names.named(PostgresqlDataSourceProvider.POSTGRESQL_DATABASENAME)).toInstance(databaseName);
        bind(PlatformTransactionManager.class).toProvider(new PlatformTransactionManagerProvider()).in(Singleton.class);
    }

    @Override
    protected void configureRdbmsInit() {
        if(postgresqlRdbmsInit != null) {
            bind(RdbmsInit.class).toInstance(postgresqlRdbmsInit);
        } else {
            bind(RdbmsInit.class).toProvider(PostgresqlRdbmsInitProvider.class).in(Singleton.class);
        }
    }
}
