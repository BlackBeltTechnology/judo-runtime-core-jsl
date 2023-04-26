package hu.blackbelt.judo.runtime.core.jsl.entity;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritancemodel.inheritancemodel.e.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritancemodel.inheritancemodel.f.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritancemodel.inheritancemodel.g.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritancemodel.inheritancemodel.i.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritancemodel.inheritancemodel.h.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritancemodel.inheritancemodel.myenum.MyEnum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.inheritancemodel.inheritancemodel.parentabstract.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.InheritanceModelDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class InheritanceTest extends AbstractJslTest {

    @Override
    public Module getModelDaoModule() {
        return new InheritanceModelDaoModules();
    }

    @Override
    public String getModelName() {
        return "InheritanceModel";
    }

    @Inject
    EDao eDao;

    @Inject
    FDao fDao;

    @Inject
    GDao gDao;

    @Inject
    HDao hDao;

    @Inject
    IDao iDao;

    @Inject
    ParentAbstractDao parentAbstractDao;

    /**
     * This test check of the child entity contains all the parent entities attributes.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel InheritanceModel.jsl
     *
     *
     * @positiveRequirements  REQ-ENT-012
     *
     * @scenario
     *
     *  Create one instance of the E entity
     *
     *  Check all the attributes is present of the parent entity in the E instance.
     *
     *  Create one instance of the F entity
     *
     *  Check all the of the parents entities attributes is present (parentA,ParentB,ParentAbstract) in the F instance.
     *
     */
    @Test
    @TestCase("InheritedEntitiesContainsAllOfParentAttributes")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-003",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-ENT-009",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
            "REQ-EXPR-005",
    })
    void testInheritedEntitiesContainsAllOfParentAttributes() {


        E e = eDao.create(E.builder().build());

        assertTrue(asMethodWithName("getNameA", e));
        assertTrue(asMethodWithName("getIdentifierA", e));
        assertTrue(asMethodWithName("getDerivedA", e));
        assertTrue(asMethodWithName("getQueryA", e));
        assertTrue(asMethodWithName("queryRelationEntities", eDao));


        F f = fDao.create(F.builder().build());

        assertTrue(asMethodWithName("getNameA", f));
        assertTrue(asMethodWithName("getNameB", f));
        assertTrue(asMethodWithName("getNameAbstract", f));
        assertTrue(asMethodWithName("getIdentifierA", f));
        assertTrue(asMethodWithName("getDerivedA", f));
        assertTrue(asMethodWithName("getQueryA", f));
        assertTrue(asMethodWithName("queryRelationEntities", fDao));

    }

    /**
     * Testing an entity that inheritance form an inherited entity.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel InheritanceModel.jsl
     *
     *
     * @positiveRequirements REQ-ENT-012
     *
     * @scenario
     *
     *  Create one instance of the G entity
     *
     *  Check all the of the parents entities attributes is present (E -> parentA,ParentB) in the G instance.
     *
     */
    @Test
    @TestCase("MultiLevelInheritanceIsAllowed")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-003",
            "REQ-ENT-004",
            "REQ-ENT-005",
            "REQ-ENT-008",
            "REQ-ENT-009",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004"
    })
    void testMultiLevelInheritanceIsAllowed() {

        G g = gDao.create(G.builder().build());

        assertTrue(asMethodWithName("getNameA", g));
        assertTrue(asMethodWithName("getNameB", g));
        assertTrue(asMethodWithName("getNameE", g));
        assertTrue(asMethodWithName("getIdentifierA", g));
        assertTrue(asMethodWithName("getDerivedA", g));
        assertTrue(asMethodWithName("getQueryA", g));
        assertTrue(asMethodWithName("queryRelationEntities", gDao));

    }

    /**
     * Testing the inherited required attributes missing in the child entity
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel InheritanceModel.jsl
     *

     * @negativeRequirements REQ-ENT-012
     *
     * @scenario
     *
     *  Create one instance of the H entity without attribute parameters.
     *
     *  Check the thrown expectation contains all the missing required attributes
     */
    @Test
    @TestCase("InheritedEntitiesContainsAllOfRequiredParentAttributes")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-003",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004"
    })
    void testInheritedEntitiesContainsAllOfRequiredParentAttributes() {

        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> hDao.create(H.builder().build())
        );

        assertThat(thrown.getValidationResults(), containsInAnyOrder(
                matchMissingAttribute("integerAttr"),
                matchMissingAttribute("scaledAttr"),
                matchMissingAttribute("stringAttr"),
                matchMissingAttribute("regexAttr"),
                matchMissingAttribute("boolAttr"),
                matchMissingAttribute("dateAttr"),
                matchMissingAttribute("timestampAttr"),
                matchMissingAttribute("timeAttr"),
                matchMissingAttribute("enumAttr")
        ));
        List<H> list = hDao.query().execute();

        assertEquals(0, list.size());

    }

    /**
     * Testing the inherited entity contains the attributes with default values of the parent entity.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel InheritanceModel.jsl
     *
     *
     * @positiveRequirements REQ-ENT-012
     *
     *
     * @scenario
     *
     *  Create one instance of the I entity without attribute parameters.
     *
     *  Check all the of the parents entity attributes is present (ParentDefaultD) in the I instance and all of them contains the default value.
     *
     */
    @Test
    @TestCase("InheritedEntitiesContainsAllOfDefaultValueParentAttributes")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-003",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
    })
    void testInheritedEntitiesContainsAllOfDefaultValueParentAttributes() {

        I i = iDao.create(I.builder().build());

        assertEquals(Optional.of(1), i.getIntegerAttr());
        assertEquals(Optional.of(2.34), i.getScaledAttr());
        assertEquals(Optional.of("test"), i.getStringAttr());
        assertEquals(Optional.of("+36-1-123-123"), i.getRegexAttr());
        assertEquals(Optional.of(true), i.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), i.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), i.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), i.getTimeAttr());
        assertEquals(Optional.of(MyEnum.Bombastic), i.getEnumAttr());

    }

    /**
     * Testing the abstract entity Dao cannot create instances.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel InheritanceModel.jsl

     * @negativeRequirements REQ-ENT-012
     *
     * @scenario
     *
     *  Try to create one instance of the ParentAbstract entity.
     *
     *  Should throw a validation error, because abstract entities cannot create instances.
     *
     */
    @Test
    @Disabled("JNG-4793")
    @TestCase("AbstractEntityInstanceThrowError")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-ENT-012",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SYNT-001",
            "REQ-SYNT-002",
            "REQ-SYNT-003",
            "REQ-SYNT-004",
    })
    void testAbstractEntityInstanceThrowError() {

        assertThrows(
                ValidationException.class,
                () -> parentAbstractDao.create(ParentAbstract.builder()
                        .build())
        );

        //TODO If JNG-4793 implemented add more detailed error massage of here

    }

    public boolean asMethodWithName(String methodName,Object object) {
        return Arrays.stream(object.getClass().getDeclaredMethods())
                .anyMatch(f -> f.getName().equals(methodName));
    }

    private org.hamcrest.Matcher matchMissingAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo(attrName)));
    }
}