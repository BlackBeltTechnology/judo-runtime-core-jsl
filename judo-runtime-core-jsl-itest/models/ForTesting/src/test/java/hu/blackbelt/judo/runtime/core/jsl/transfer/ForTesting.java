package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.fortesting.fortesting.c.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.fortesting.fortesting.d.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.fortesting.fortesting.ta.TA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.fortesting.fortesting.ta.TAAttachedRelationsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.fortesting.fortesting.ta.TADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.fortesting.fortesting.tb.TB;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.fortesting.fortesting.tb.TBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.fortesting.fortesting.tc.TC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.fortesting.fortesting.tc.TCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.fortesting.fortesting.td.TD;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.fortesting.fortesting.td.TDDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.ForTestingDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class ForTesting extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new ForTestingDaoModules();
    }

    @Override
    public String getModelName() {
        return "ForTesting";
    }

    @Inject
    TCDao tcDao;


    @Inject
    TDDao tdDao;

    @Test
    void testingMapped() {
        TD td = tdDao.create(TD.builder().build());
        TC tc = tcDao.create(TC.builder().build());

        assertEquals(td.identifier(), tcDao.queryDerivedD(tc).get().identifier());


    }

    @Inject
    TADao taDao;


    @Inject
    TBDao tbDao;

    @Test
    void testingAutoMapped() {
        TB tb = tbDao.create(TB.builder().withName("B").build());
        TA ta = taDao.create(TA.builder().build(), TAAttachedRelationsForCreate.builder().withSingleB(tb).build());

        assertEquals(tb.identifier(), taDao.queryDerivedB(ta).get().identifier());


    }


}
