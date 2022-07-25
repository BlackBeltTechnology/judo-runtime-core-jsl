package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoDefaultModule;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoModelLoader;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.hsqldb.JudoHsqldbModules;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;
import hu.blackbelt.judo.runtime.core.jsl.itest.operators.guice.operators.OperatorsDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.operators.sdk.operators.operators.DefaultOperators;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class OperatorsTest {
    Injector injector;

    @Inject
    DefaultOperators.DefaultOperatorsDao defaultOperatorsDao;

    @BeforeEach
    void init() throws Exception {
        JudoModelLoader modelHolder = JudoModelLoader.
                loadFromClassloader("Operators", OperatorsTest.class.getClassLoader(), new HsqldbDialect(), true);

        injector = Guice.createInjector(
                JudoHsqldbModules.builder().build(),
                new OperatorsDaoModules(),
                new JudoDefaultModule(this, modelHolder));
    }

    @Test
    void testRelationalOperatorsForDefaultValues() {
        DefaultOperators defaultOperators = defaultOperatorsDao.create(DefaultOperators.builder().build());

        assertEquals(Optional.of(false), defaultOperators.getLt());
        assertEquals(Optional.of(true), defaultOperators.getLt2());
        assertEquals(Optional.of(false), defaultOperators.getGt());
        assertEquals(Optional.of(true), defaultOperators.getGt2());
        assertEquals(Optional.of(true), defaultOperators.getLte());
        assertEquals(Optional.of(true), defaultOperators.getLte2());
        assertEquals(Optional.of(true), defaultOperators.getGte());
        assertEquals(Optional.of(true), defaultOperators.getGte2());
        assertEquals(Optional.of(false), defaultOperators.getEq());
        assertEquals(Optional.of(true), defaultOperators.getNeq());
    }
}
