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

import org.junit.jupiter.api.extension.*;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class JudoDatasourceSingletonExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback, ParameterResolver {

    private static final JudoDatasourceFixture JUDO_DATASOURCE_FIXTURE = new JudoDatasourceFixture();
    private static boolean initialized = false;

    @Override
    public void beforeAll(ExtensionContext context) {
        if (!initialized) {
            JUDO_DATASOURCE_FIXTURE.setupDatabase();
            JUDO_DATASOURCE_FIXTURE.prepareDatasources();
            initialized = true;
        }
    }

    @Override
    public void afterAll(ExtensionContext context) {
        // rdbmsDatasourceFixture.teardownDatasource();
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(JudoDatasourceFixture.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return JUDO_DATASOURCE_FIXTURE;
    }

    @Override
    public void afterEach(ExtensionContext extensionContext) {
        try {
            TransactionStatus transactionStatus = JUDO_DATASOURCE_FIXTURE.getTransactionManager().getTransaction(new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_MANDATORY));
            JUDO_DATASOURCE_FIXTURE.getTransactionManager().commit(transactionStatus);
        } catch (Exception e) {
        }
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) {
    }
}
