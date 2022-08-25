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
import hu.blackbelt.judo.runtime.core.jsl.itest.operators.sdk.operators.operators.DerivedOperators;
import hu.blackbelt.judo.runtime.core.jsl.itest.operators.sdk.operators.operators.DerivedSource;
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

    @Inject
    DerivedOperators.DerivedOperatorsDao derivedOperatorsDao;

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
    void testOperatorsForDefaultValues() {
        DefaultOperators operators = defaultOperatorsDao.create(DefaultOperators.builder().build());

        assertEquals(Optional.of(1), operators.getRounded());
        assertEquals(Optional.of(7), operators.getAddition());
        assertEquals(Optional.of(3), operators.getSubtraction());
        assertEquals(Optional.of(25), operators.getPow());
        assertEquals(Optional.of(10), operators.getMultiplication());
        assertEquals(Optional.of(Float.valueOf("2.5")), operators.getDivision());
        assertEquals(Optional.of(2), operators.getDivisionWhole());
        assertEquals(Optional.of(1), operators.getModulo());
        assertEquals(Optional.of(false), operators.getLt());
        assertEquals(Optional.of(true), operators.getLt2());
        assertEquals(Optional.of(false), operators.getGt());
        assertEquals(Optional.of(true), operators.getGt2());
        assertEquals(Optional.of(true), operators.getLte());
        assertEquals(Optional.of(true), operators.getLte2());
        assertEquals(Optional.of(true), operators.getGte());
        assertEquals(Optional.of(true), operators.getGte2());
        assertEquals(Optional.of(false), operators.getEq());
        assertEquals(Optional.of(true), operators.getNeq());
        assertEquals(Optional.of(1), operators.getConditional());
        assertEquals(Optional.of(false), operators.getGroupAnd());
        assertEquals(Optional.of(true), operators.getGroupOr());
    }

    @Test
    void testOperatorsForDerivedFields() {
        DerivedOperators operators = derivedOperatorsDao.create(DerivedOperators.builder()
                .withSource(DerivedSource.builder().build())
                .build());

        assertEquals(Optional.of("John Pro"), operators.getStringConcat());
        assertEquals(Optional.of(4), operators.getFirstNameLength());
        assertEquals(Optional.of(99), operators.getRounded());
        assertEquals(Optional.of(37), operators.getAddition());
        assertEquals(Optional.of(33), operators.getSubtraction());
        assertEquals(Optional.of(70), operators.getMultiplication());
        assertEquals(Optional.of(17), operators.getDivision());
        assertEquals(Optional.of(17), operators.getDivisionWhole());
        assertEquals(Optional.of(1), operators.getModulo());
        assertEquals(Optional.of(false), operators.getLt());
        assertEquals(Optional.of(true), operators.getLt2());
        assertEquals(Optional.of(true), operators.getGt());
        assertEquals(Optional.of(true), operators.getGt2());
        assertEquals(Optional.of(false), operators.getLte());
        assertEquals(Optional.of(true), operators.getLte2());
        assertEquals(Optional.of(true), operators.getGte());
        assertEquals(Optional.of(true), operators.getGte2());
        assertEquals(Optional.of(false), operators.getEq());
        assertEquals(Optional.of(true), operators.getNeq());
        assertEquals(Optional.of(1), operators.getConditional());
    }
}
