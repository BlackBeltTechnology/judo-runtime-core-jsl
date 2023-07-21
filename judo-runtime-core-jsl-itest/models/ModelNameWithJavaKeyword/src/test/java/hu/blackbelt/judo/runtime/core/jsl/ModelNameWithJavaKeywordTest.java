package hu.blackbelt.judo.runtime.core.jsl;


import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.AbstractDaoModules;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.abstract_.abstract_.entityformapping.*;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ModelNameWithJavaKeywordTest extends AbstractJslTest {

    @Inject
    EntityForMappingDao entityForMappingDao;

    @Override
    public Module getModelDaoModule() {
        return new AbstractDaoModules();
    }

    @Override
    public String getModelName() {
        return "Abstract";
    }

    @Test
    @TestCase("TestEntityThatHasJavaKeywordInItsPackageName")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
    })
    public void TestEntityThatHasJavaKeywordInItsPackageName() {
        EntityForMapping entity = entityForMappingDao.create(EntityForMapping.builder().build());

        assertEquals(1, entityForMappingDao.countAll());
    }
}