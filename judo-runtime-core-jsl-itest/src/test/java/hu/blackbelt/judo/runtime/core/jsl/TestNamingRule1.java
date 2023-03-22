package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Module;
import com.google.inject.Inject;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mmodeltc021.mmodeltc021.aaent.AaEnt;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mmodeltc021.mmodeltc021.aaent.AaEntDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mmodeltc021.mmodeltc021.xxenum.xxEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MModelTC021DaoModules;


public class TestNamingRule1 extends AbstractJslTest {

    @Inject
    AaEntDao aaEntDao;
    
    @Override
    public Module getModelDaoModule() {
        return new MModelTC021DaoModules();
    }

    @Override
    public String getModelName() {
        return "mModelTC021";
    }
    
    /**
     * Testing the naming rules. In this case check that case when the element name starts
     * a lower case character and follows it an upper case character.
     * 
     * @prerequisites "Nothing"
     * 
     * @type Behaviour
     * 
     * @scenario
     *  . Parse (and/or build) the model.
     *  
     *  . The result of the model parsing (and/or building) is successful.
     *  
     *  . Create an aEnt1 entity instance (ae1) with the default values.
     *  
     *  . Check the values of the following fields of the new entity instance (ae1).
     *      * ffBool == true
     *      * ssS99 == "abc"
     *      * zzEnum == xEnum#xB1
     *  
     *  . The test is passed if all modifications and checks are successful, and there were no exceptions.
     */
    @Test
    //@Disabled("JNG-4618")
    @TestCase("TC021")
    @Requirement(reqs = {
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
            "REQ-MDL-001",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-006",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-EXPR-001"
    })
    public void testNamingRule() {
        AaEnt ae = aaEntDao.create(
                AaEnt
                .builder()
                .build()
        );
        
        assertTrue(ae.getFfBool().orElseThrow());
        assertEquals("abc", ae.getSsS99().orElseThrow());
        assertEquals(xxEnum.xxB1, ae.getZzEnum().orElseThrow());
    }

}
