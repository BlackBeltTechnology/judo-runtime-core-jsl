package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.ta.TA;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.ta.TADao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.ta.TAForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.ta.TAMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.tb.TBDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.tb.TBForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.tb.TBMask;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.tc.TC;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.tc.TCDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transfervalidation.transfervalidation.tc.TCForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TransferValidationDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@Slf4j
public class DerivedRelationValidationTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("TransferValidation", new TransferValidationDaoModules(), null);


    @Inject
    TADao taDao;

    @Inject
    TBDao tbDao;

    @Inject
    TCDao tcDao;

    @Test
    @TestCase("DerivedNotValidated")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-EXPR-022",
            "REQ-SRV-002",
            "REQ-SRV-003",
            "REQ-SRV-005",

    })
    void testDerivedNotValidated() {

        TC c2 = tcDao.create(TCForCreate.builder().withName("C2").build());
        tbDao.create(TBForCreate.builder().withName("C1").withAggregatedC(TCForCreate.builderFrom(c2).build()).build());

        taDao.create(TAForCreate.builder().withName("TA").build());

        TA container = taDao.query().filterBy("this.name == 'TA'")
                .maskedBy(TAMask.tAMask()
                        .withName()
                        .withDerivedB(TBMask
                                .tBMask())
                )
                .selectOne()
                .orElseThrow();

        container = TA.from(removeKeys(List.of("__identifier","__entityType","__version"), "derivedB", container.toMap()));

        // Derived doesn't have identifier
        // Check the derived is not validated.
        final TA containerFinal = container;
        assertDoesNotThrow(() -> taDao.update(containerFinal));
    }

    Map<String, Object> removeKeys(List<String> keys, String relationName, Map<String, Object> map) {
        Map<String, Object> relationMap = (Map<String, Object>) map.get(relationName);
        for (String key : keys) {
            relationMap.remove(keys);
        }
        return map;
    }
}
