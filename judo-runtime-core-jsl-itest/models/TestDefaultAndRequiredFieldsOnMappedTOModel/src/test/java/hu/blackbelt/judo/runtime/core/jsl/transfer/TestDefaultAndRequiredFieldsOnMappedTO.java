package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import hu.blackbelt.judo.dao.api.ValidationResult;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.entitywithoptionalfields.EntityWithOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.entitywithoptionalfields.EntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.entitywithoptionalfields.EntityWithOptionalFieldsIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.entitywithoptionalfieldswithdefault.EntityWithOptionalFieldsWithDefault;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.entitywithoptionalfieldswithdefault.EntityWithOptionalFieldsWithDefaultDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.entitywithoptionalfieldswithdefault.EntityWithOptionalFieldsWithDefaultIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.entitywithrequiredfields.EntityWithRequiredFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.entitywithrequiredfields.EntityWithRequiredFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.entitywithrequiredfields.EntityWithRequiredFieldsIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.entitywithrequiredfieldswithdefault.EntityWithRequiredFieldsWithDefault;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.entitywithrequiredfieldswithdefault.EntityWithRequiredFieldsWithDefaultDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.entitywithrequiredfieldswithdefault.EntityWithRequiredFieldsWithDefaultIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.enum_.Enum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.querystringattribute.QueryStringAttributeDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transfermapsfieldtwice.TransferMapsFieldTwice;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transfermapsfieldtwice.TransferMapsFieldTwiceDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transfermapsfieldtwice.TransferMapsFieldTwiceForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldsmapsentitywithoptionalfields.TransferWithOptionalFieldsMapsEntityWithOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldsmapsentitywithoptionalfields.TransferWithOptionalFieldsMapsEntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldsmapsentitywithoptionalfields.TransferWithOptionalFieldsMapsEntityWithOptionalFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldsmapsentitywithoptionalfieldswithdefault.TransferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefault;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldsmapsentitywithoptionalfieldswithdefault.TransferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldsmapsentitywithoptionalfieldswithdefault.TransferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldsmapsentitywithrequiredfields.TransferWithOptionalFieldsMapsEntityWithRequiredFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldsmapsentitywithrequiredfields.TransferWithOptionalFieldsMapsEntityWithRequiredFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldsmapsentitywithrequiredfields.TransferWithOptionalFieldsMapsEntityWithRequiredFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldsmapsentitywithrequiredfieldswithdefault.TransferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefault;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldsmapsentitywithrequiredfieldswithdefault.TransferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldsmapsentitywithrequiredfieldswithdefault.TransferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultexpressionmapsentitywithoptionalfields.TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultexpressionmapsentitywithoptionalfields.TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultexpressionmapsentitywithoptionalfields.TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultexpressionmapsentitywithoptionalfieldswithdefault.TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefault;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultexpressionmapsentitywithoptionalfieldswithdefault.TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefaultDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultexpressionmapsentitywithoptionalfieldswithdefault.TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefaultForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultexpressionmapsentitywithrequiredfields.TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultexpressionmapsentitywithrequiredfieldswithdefault.TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsWithDefaultDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultmapsentitywithoptionalfields.TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultmapsentitywithoptionalfields.TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultmapsentitywithoptionalfields.TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultmapsentitywithoptionalfieldswithdefault.TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultmapsentitywithoptionalfieldswithdefault.TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultmapsentitywithoptionalfieldswithdefault.TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultmapsentitywithrequiredfields.TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultmapsentitywithrequiredfields.TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultmapsentitywithrequiredfields.TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultmapsentitywithrequiredfieldswithdefault.TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefault;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultmapsentitywithrequiredfieldswithdefault.TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithoptionalfieldswithdefaultmapsentitywithrequiredfieldswithdefault.TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldsmapsentitywithoptionalfields.TransferWithRequiredFieldsMapsEntityWithOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldsmapsentitywithoptionalfields.TransferWithRequiredFieldsMapsEntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldsmapsentitywithoptionalfields.TransferWithRequiredFieldsMapsEntityWithOptionalFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldsmapsentitywithoptionalfieldswithdefault.TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefault;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldsmapsentitywithoptionalfieldswithdefault.TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldsmapsentitywithoptionalfieldswithdefault.TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldsmapsentitywithrequiredfields.TransferWithRequiredFieldsMapsEntityWithRequiredFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldsmapsentitywithrequiredfields.TransferWithRequiredFieldsMapsEntityWithRequiredFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldsmapsentitywithrequiredfields.TransferWithRequiredFieldsMapsEntityWithRequiredFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldsmapsentitywithrequiredfieldswithdefault.TransferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefault;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldsmapsentitywithrequiredfieldswithdefault.TransferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldsmapsentitywithrequiredfieldswithdefault.TransferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultexpressionmapsentitywithoptionalfields.TransferWithRequiredFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultexpressionmapsentitywithoptionalfieldswithdefault.TransferWithRequiredFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefaultDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultexpressionmapsentitywithrequiredfields.TransferWithRequiredFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultexpressionmapsentitywithrequiredfieldswithdefault.TransferWithRequiredFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsWithDefaultDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultmapsentitywithoptionalfields.TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultmapsentitywithoptionalfields.TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultmapsentitywithoptionalfields.TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultmapsentitywithoptionalfieldswithdefault.TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultmapsentitywithoptionalfieldswithdefault.TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultmapsentitywithoptionalfieldswithdefault.TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultmapsentitywithrequiredfields.TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFields;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultmapsentitywithrequiredfields.TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultmapsentitywithrequiredfields.TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultmapsentitywithrequiredfieldswithdefault.TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefault;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultmapsentitywithrequiredfieldswithdefault.TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.testdefaultandrequiredfieldsonmappedto.testdefaultandrequiredfieldsonmappedto.transferwithrequiredfieldswithdefaultmapsentitywithrequiredfieldswithdefault.TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TestDefaultAndRequiredFieldsOnMappedTODaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import hu.blackbelt.judo.sdk.query.EnumerationFilter;
import hu.blackbelt.judo.sdk.query.StringFilter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class TestDefaultAndRequiredFieldsOnMappedTO {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("TestDefaultAndRequiredFieldsOnMappedTO", new TestDefaultAndRequiredFieldsOnMappedTODaoModules());

    @Inject
    EntityWithOptionalFieldsDao entityWithOptionalFieldsDao;

    @Inject
    TransferWithOptionalFieldsMapsEntityWithOptionalFieldsDao transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao;

    @Inject
    TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao;


    @Inject
    TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsDao transferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsDao;

    @Inject
    TransferWithRequiredFieldsMapsEntityWithOptionalFieldsDao transferWithRequiredFieldsMapsEntityWithOptionalFieldsDao;

    @Inject
    TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao;

    @Inject
    TransferWithRequiredFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsDao transferWithRequiredFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsDao;

    @Inject
    EntityWithRequiredFieldsDao entityWithRequiredFieldsDao;

    @Inject
    TransferWithOptionalFieldsMapsEntityWithRequiredFieldsDao transferWithOptionalFieldsMapsEntityWithRequiredFieldsDao;


    @Inject
    TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao;

    @Inject
    TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsDao transferWithOptionalFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsDao;

    @Inject
    TransferWithRequiredFieldsMapsEntityWithRequiredFieldsDao transferWithRequiredFieldsMapsEntityWithRequiredFieldsDao;

    @Inject
    TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao;
    @Inject
    TransferWithRequiredFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsDao transferWithRequiredFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsDao;

    @Inject
    EntityWithOptionalFieldsWithDefaultDao entityWithOptionalFieldsWithDefaultDao;

    @Inject
    TransferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao;

    @Inject
    TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao;

    @Inject
    TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefaultDao transferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefaultDao;

    @Inject
    TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao;

    @Inject
    TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao;

    @Inject
    TransferWithRequiredFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefaultDao transferWithRequiredFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefaultDao;

    @Inject
    EntityWithRequiredFieldsWithDefaultDao entityWithRequiredFieldsWithDefaultDao;
    @Inject
    TransferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao;

    @Inject
    TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao;

    @Inject
    TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsWithDefaultDao transferWithOptionalFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsWithDefaultDao;

    @Inject
    TransferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao;

    @Inject
    TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao;

    @Inject
    TransferWithRequiredFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsWithDefaultDao transferWithRequiredFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsWithDefaultDao;

    @Inject
    QueryStringAttributeDao queryStringAttributeDao;

    @Inject
    TransferMapsFieldTwiceDao transferMapsFieldTwiceDao;

    /**
     * This test check the mapped transfer object maps an entity with contains primitive optional field.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel TransferConstructor.jsl
     *
     * @positiveRequirements
     *
     * @scenario
     *
     *  Create one instance of TransferWithOptionalFieldsMapsEntityWithOptionalFields.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFields.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFields.
     *
     *  Check that the creation throws ValidationException.
     *
     *  Create one instance of TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFields.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     */
    @Test
    @TestCase("EntityOptionalPrimitives")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
            "REQ-SRV-003",
            "REQ-SRV-004",
            "REQ-EXPR-001"
    })
    void testTransferMapsEntityWithOptionalFields() {
        TransferWithOptionalFieldsMapsEntityWithOptionalFields t1 = transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.create(TransferWithOptionalFieldsMapsEntityWithOptionalFieldsForCreate.builder().build());

        assertEquals(1, transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(Optional.empty(), t1.getIntAttr());
        assertEquals(Optional.empty(), t1.getScaledAttr());
        assertEquals(Optional.empty(), t1.getStringAttr());
        assertEquals(Optional.empty(), t1.getRegexAttr());
        assertEquals(Optional.empty(), t1.getBoolAttr());
        assertEquals(Optional.empty(), t1.getDateAttr());
        assertEquals(Optional.empty(), t1.getTimestampAttr());
        assertEquals(Optional.empty(), t1.getTimeAttr());
        assertEquals(Optional.empty(), t1.getEnumAttr());

        Optional<EntityWithOptionalFields> e1Optional = entityWithOptionalFieldsDao.getById(t1.adaptTo(EntityWithOptionalFieldsIdentifier.class));

        assertTrue(e1Optional.isPresent());

        EntityWithOptionalFields e1 = e1Optional.orElseThrow();

        assertEquals(Optional.empty(), e1.getIntegerAttr());
        assertEquals(Optional.empty(), e1.getScaledAttr());
        assertEquals(Optional.empty(), e1.getStringAttr());
        assertEquals(Optional.empty(), e1.getRegexAttr());
        assertEquals(Optional.empty(), e1.getBoolAttr());
        assertEquals(Optional.empty(), e1.getDateAttr());
        assertEquals(Optional.empty(), e1.getTimestampAttr());
        assertEquals(Optional.empty(), e1.getTimeAttr());
        assertEquals(Optional.empty(), e1.getEnumAttr());

        transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.delete(t1);

        t1 = transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.create(TransferWithOptionalFieldsMapsEntityWithOptionalFieldsForCreate.builder().withStringAttr("init").build());
        assertEquals(Optional.of("init"), t1.getStringAttr());

        assertEquals(1, transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.query().filterByStringAttr(StringFilter.equalTo("init")).count());

        t1.setStringAttr("update");
        t1 = transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.update(t1);
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.query().filterByStringAttr(StringFilter.equalTo("update")).count());
        assertEquals(0, transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.query().filterByStringAttr(StringFilter.equalTo("init")).count());

        t1.setStringAttr(null);
        t1 = transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.update(t1);
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(0, transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.query().filterByStringAttr(StringFilter.equalTo("update")).count());

        transferWithOptionalFieldsMapsEntityWithOptionalFieldsDao.delete(t1);

        TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFields t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.create(TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsForCreate.builder().build());

        assertEquals( 1, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(Optional.of(1), t2.getIntAttr());
        assertEquals(Optional.of(2.34), t2.getScaledAttr());
        assertEquals(Optional.of("Hello there"), t2.getStringAttr());
        assertEquals(Optional.of("+36 30 123 1234"), t2.getRegexAttr());
        assertEquals(Optional.of(true), t2.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), t2.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), t2.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), t2.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), t2.getEnumAttr());

        e1Optional = entityWithOptionalFieldsDao.getById(t2.adaptTo(EntityWithOptionalFieldsIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(Optional.of(1), e1.getIntegerAttr());
        assertEquals(Optional.of(2.34), e1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), e1.getStringAttr());
        assertEquals(Optional.of("+36 30 123 1234"), e1.getRegexAttr());
        assertEquals(Optional.of(true), e1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), e1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), e1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), e1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), e1.getEnumAttr());

        transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.delete(t2);

        t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.create(TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsForCreate.builder().withStringAttr("init").build());
        assertEquals(Optional.of("init"), t2.getStringAttr());

        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.query().filterByStringAttr(StringFilter.equalTo("init")).count());

        t2.setStringAttr("update");
        t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.update(t2);
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.query().filterByStringAttr(StringFilter.equalTo("update")).count());
        assertEquals(0, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.query().filterByStringAttr(StringFilter.equalTo("init")).count());

        t2.setStringAttr(null);
        t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.update(t2);
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(0, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.query().filterByStringAttr(StringFilter.equalTo("update")).count());
        transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsDao.delete(t2);

        TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFields t2Expression = transferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsDao.create(TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsForCreate.builder().build());

        assertEquals( 1, transferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(Optional.empty(), t2Expression.getStringAttr());

        e1Optional = entityWithOptionalFieldsDao.getById(t2Expression.adaptTo(EntityWithOptionalFieldsIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(Optional.empty(), e1.getStringAttr());

        transferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsDao.delete(t2Expression);

        ValidationException exception = assertThrows(ValidationException.class, () -> transferWithRequiredFieldsMapsEntityWithOptionalFieldsDao.create(TransferWithRequiredFieldsMapsEntityWithOptionalFieldsForCreate.builder().build()));
        assertEquals(9, exception.getValidationResults().size());
        ValidationResult validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());


        TransferWithRequiredFieldsMapsEntityWithOptionalFields t3 = transferWithRequiredFieldsMapsEntityWithOptionalFieldsDao.create(TransferWithRequiredFieldsMapsEntityWithOptionalFieldsForCreate.builder()
                .withIntAttr(1)
                .withScaledAttr(2.34)
                .withStringAttr("Hello there")
                .withEnumAttr(Enum.EnumA)
                .withBoolAttr(true)
                .withDateAttr(LocalDate.parse("2022-07-11"))
                .withTimestampAttr(LocalDateTime.parse("2022-07-11T19:09:33"))
                .withTimeAttr(LocalTime.parse("23:59:59"))
                .withRegexAttr("+36 30 123 1234")
                .build());


        assertEquals(1, transferWithRequiredFieldsMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(1, t3.getIntAttr());
        assertEquals(2.34, t3.getScaledAttr());
        assertEquals("Hello there", t3.getStringAttr());
        assertEquals("+36 30 123 1234", t3.getRegexAttr());
        assertEquals(true, t3.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), t3.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), t3.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), t3.getTimeAttr());
        assertEquals(Enum.EnumA, t3.getEnumAttr());

        e1Optional = entityWithOptionalFieldsDao.getById(t3.adaptTo(EntityWithOptionalFieldsIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(Optional.of(1), e1.getIntegerAttr());
        assertEquals(Optional.of(2.34), e1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), e1.getStringAttr());
        assertEquals(Optional.of("+36 30 123 1234"), e1.getRegexAttr());
        assertEquals(Optional.of(true), e1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), e1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), e1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), e1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), e1.getEnumAttr());

        assertEquals(1, transferWithRequiredFieldsMapsEntityWithOptionalFieldsDao.query().filterByStringAttr(StringFilter.equalTo("Hello there")).count());

        t3.setStringAttr("update");
        t3 = transferWithRequiredFieldsMapsEntityWithOptionalFieldsDao.update(t3);
        assertEquals(1, transferWithRequiredFieldsMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(1, transferWithRequiredFieldsMapsEntityWithOptionalFieldsDao.query().filterByStringAttr(StringFilter.equalTo("update")).count());
        assertEquals(0, transferWithRequiredFieldsMapsEntityWithOptionalFieldsDao.query().filterByStringAttr(StringFilter.equalTo("Hello there")).count());

        t3.setStringAttr(null);
        final TransferWithRequiredFieldsMapsEntityWithOptionalFields finalT3 = t3;

        exception = assertThrows(ValidationException.class, () -> transferWithRequiredFieldsMapsEntityWithOptionalFieldsDao.update(finalT3));

        assertEquals(1, exception.getValidationResults().size());
        validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        assertEquals("stringAttr", validationResult.getLocation());
        transferWithRequiredFieldsMapsEntityWithOptionalFieldsDao.delete(t3);

        TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFields t4 = transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao.create(TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsForCreate.builder().build());

        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(1, t4.getIntAttr());
        assertEquals(2.34, t4.getScaledAttr());
        assertEquals("Hello there", t4.getStringAttr());
        assertEquals("+36 30 123 1234", t4.getRegexAttr());
        assertEquals(true, t4.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), t4.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), t4.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), t4.getTimeAttr());
        assertEquals(Enum.EnumA, t4.getEnumAttr());

        e1Optional = entityWithOptionalFieldsDao.getById(t4.adaptTo(EntityWithOptionalFieldsIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(Optional.of(1), e1.getIntegerAttr());
        assertEquals(Optional.of(2.34), e1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), e1.getStringAttr());
        assertEquals(Optional.of("+36 30 123 1234"), e1.getRegexAttr());
        assertEquals(Optional.of(true), e1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), e1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), e1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), e1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), e1.getEnumAttr());
        transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao.delete(t4);

        t4 = transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao.create(TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsForCreate.builder().withStringAttr("init").build());
        assertEquals("init", t4.getStringAttr());
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao.query().filterByStringAttr(StringFilter.equalTo("init")).count());

        t4.setStringAttr("update");
        t4 = transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao.update(t4);
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao.countAll());
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao.query().filterByStringAttr(StringFilter.equalTo("update")).count());
        assertEquals(0, transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao.query().filterByStringAttr(StringFilter.equalTo("init")).count());

        t4.setStringAttr(null);
        final TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFields t4updated = t4;
        exception = assertThrows(ValidationException.class, () -> transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao.update(t4updated));

        assertEquals(1, exception.getValidationResults().size());
        validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        assertEquals("stringAttr", validationResult.getLocation());
        transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsDao.delete(t4);

        // TODO: JNG-4997
        //exception = assertThrows(ValidationException.class, () -> transferWithRequiredFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsDao.create(TransferWithRequiredFieldsWithDefaultExpressionMapsEntityWithOptionalFields.builder().build()));
        //assertEquals(1, exception.getValidationResults().size());
        //validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        //assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        //assertEquals("stringAttr", validationResult.getLocation());

    }


    /**
     * This test check the mapped transfer object maps an entity with contains primitive required field.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel TransferConstructor.jsl
     *
     * @positiveRequirements
     *
     * @scenario
     *
     *  Create one instance of TransferWithOptionalFieldsMapsEntityWithRequiredFields.
     *
     *  Check that the creation throws ValidationException.
     *
     *  Create one instance of TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFields.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithRequiredFieldsMapsEntityWithRequiredFields.
     *
     *  Check that the creation throws ValidationException.
     *
     *  Create one instance of TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFields.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     */
    @Test
    @TestCase("EntityRequiredPrimitives")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
            "REQ-SRV-003",
            "REQ-SRV-004",
            "REQ-EXPR-001"
    })
    void testTransferMapsEntityWithRequiredFields() {
        ValidationException exception = assertThrows(ValidationException.class, () -> transferWithOptionalFieldsMapsEntityWithRequiredFieldsDao.create(TransferWithOptionalFieldsMapsEntityWithRequiredFieldsForCreate.builder().build()));
        assertEquals(9, exception.getValidationResults().size());
        ValidationResult validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());


        TransferWithOptionalFieldsMapsEntityWithRequiredFields t1 = transferWithOptionalFieldsMapsEntityWithRequiredFieldsDao.create(TransferWithOptionalFieldsMapsEntityWithRequiredFieldsForCreate.builder()
                .withIntAttr(1)
                .withScaledAttr(2.34)
                .withStringAttr("Hello there")
                .withEnumAttr(Enum.EnumA)
                .withBoolAttr(true)
                .withDateAttr(LocalDate.parse("2022-07-11"))
                .withTimestampAttr(LocalDateTime.parse("2022-07-11T19:09:33"))
                .withTimeAttr(LocalTime.parse("23:59:59"))
                .withRegexAttr("+36 30 123 1234")
                .build());


        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.countAll());
        assertEquals(Optional.of(1), t1.getIntAttr());
        assertEquals(Optional.of(2.34), t1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), t1.getStringAttr());
        assertEquals(Optional.of("+36 30 123 1234"), t1.getRegexAttr());
        assertEquals(Optional.of(true), t1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), t1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), t1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), t1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), t1.getEnumAttr());

        Optional<EntityWithRequiredFields> e1 = entityWithRequiredFieldsDao.getById(t1.adaptTo(EntityWithRequiredFieldsIdentifier.class));

        assertTrue(e1.isPresent());

        EntityWithRequiredFields entity = e1.orElseThrow();

        assertEquals(1, entity.getIntegerAttr());
        assertEquals(2.34, entity.getScaledAttr());
        assertEquals("Hello there", entity.getStringAttr());
        assertEquals("+36 30 123 1234", entity.getRegexAttr());
        assertEquals(true, entity.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), entity.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), entity.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), entity.getTimeAttr());
        assertEquals(Enum.EnumA, entity.getEnumAttr());

        assertEquals(1, transferWithOptionalFieldsMapsEntityWithRequiredFieldsDao.query().filterByStringAttr(StringFilter.equalTo("Hello there")).count());

        t1.setStringAttr("update");
        t1 = transferWithOptionalFieldsMapsEntityWithRequiredFieldsDao.update(t1);
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithRequiredFieldsDao.countAll());
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithRequiredFieldsDao.query().filterByStringAttr(StringFilter.equalTo("update")).count());
        assertEquals(0, transferWithOptionalFieldsMapsEntityWithRequiredFieldsDao.query().filterByStringAttr(StringFilter.equalTo("Hello there")).count());

        t1.setStringAttr(null);
        final TransferWithOptionalFieldsMapsEntityWithRequiredFields finalT1 = t1;

        exception = assertThrows(ValidationException.class, () -> transferWithOptionalFieldsMapsEntityWithRequiredFieldsDao.update(finalT1));

        assertEquals(1, exception.getValidationResults().size());
        validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        assertEquals("stringAttr", validationResult.getLocation());
        transferWithOptionalFieldsMapsEntityWithRequiredFieldsDao.delete(t1);


        TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFields t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.create(TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsForCreate.builder().build());

        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.countAll());
        assertEquals(Optional.of(1), t2.getIntAttr());
        assertEquals(Optional.of(2.34), t2.getScaledAttr());
        assertEquals(Optional.of("Hello there"), t2.getStringAttr());
        assertEquals(Optional.of("+36 30 123 1234"), t2.getRegexAttr());
        assertEquals(Optional.of(true), t2.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 11)), t2.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-11T19:09:33")), t2.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), t2.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), t2.getEnumAttr());

        e1 = entityWithRequiredFieldsDao.getById(t2.adaptTo(EntityWithRequiredFieldsIdentifier.class));

        assertTrue(e1.isPresent());

        entity = e1.orElseThrow();

        assertEquals(1, entity.getIntegerAttr());
        assertEquals(2.34, entity.getScaledAttr());
        assertEquals("Hello there", entity.getStringAttr());
        assertEquals("+36 30 123 1234", entity.getRegexAttr());
        assertEquals(true, entity.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), entity.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), entity.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), entity.getTimeAttr());
        assertEquals(Enum.EnumA, entity.getEnumAttr());

        transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.delete(t2);

        t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.create(TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsForCreate.builder().withStringAttr("init").build());
        assertEquals(Optional.of("init"), t2.getStringAttr());
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.countAll());
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.query().filterByStringAttr(StringFilter.equalTo("init")).count());

        t2.setStringAttr("update");
        t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.update(t2);
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.countAll());
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.query().filterByStringAttr(StringFilter.equalTo("update")).count());
        assertEquals(0, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.query().filterByStringAttr(StringFilter.equalTo("init")).count());

        t2.setStringAttr(null);
        final TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFields finalT2 = t2;

        exception = assertThrows(ValidationException.class, () -> transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.update(finalT2));

        assertEquals(1, exception.getValidationResults().size());
        validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        assertEquals("stringAttr", validationResult.getLocation());
        transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsDao.delete(t2);

        // TODO: JNG-4997
        //exception = assertThrows(ValidationException.class, () -> transferWithOptionalFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsDao.create(TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithRequiredFields.builder().build()));
        //assertEquals(1, exception.getValidationResults().size());
        //validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        //assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        //assertEquals("stringAttr", validationResult.getLocation());


        exception = assertThrows(ValidationException.class, () -> transferWithRequiredFieldsMapsEntityWithRequiredFieldsDao.create(TransferWithRequiredFieldsMapsEntityWithRequiredFieldsForCreate.builder().build()));
        assertEquals(9, exception.getValidationResults().size());
        validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());

        TransferWithRequiredFieldsMapsEntityWithRequiredFields t3 = transferWithRequiredFieldsMapsEntityWithRequiredFieldsDao.create(TransferWithRequiredFieldsMapsEntityWithRequiredFieldsForCreate.builder()
                .withIntAttr(1)
                .withScaledAttr(2.34)
                .withStringAttr("Hello there")
                .withEnumAttr(Enum.EnumA)
                .withBoolAttr(true)
                .withDateAttr(LocalDate.parse("2022-07-11"))
                .withTimestampAttr(LocalDateTime.parse("2022-07-11T19:09:33"))
                .withTimeAttr(LocalTime.parse("23:59:59"))
                .withRegexAttr("+36 30 123 1234")
                .build());


        assertEquals(1, transferWithRequiredFieldsMapsEntityWithRequiredFieldsDao.countAll());
        assertEquals(1, t3.getIntAttr());
        assertEquals(2.34, t3.getScaledAttr());
        assertEquals("Hello there", t3.getStringAttr());
        assertEquals("+36 30 123 1234", t3.getRegexAttr());
        assertEquals(true, t3.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), t3.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), t3.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), t3.getTimeAttr());
        assertEquals(Enum.EnumA, t3.getEnumAttr());

        e1 = entityWithRequiredFieldsDao.getById(t3.adaptTo(EntityWithRequiredFieldsIdentifier.class));

        assertTrue(e1.isPresent());

        entity = e1.orElseThrow();

        assertEquals(1, entity.getIntegerAttr());
        assertEquals(2.34, entity.getScaledAttr());
        assertEquals("Hello there", entity.getStringAttr());
        assertEquals("+36 30 123 1234", entity.getRegexAttr());
        assertEquals(true, entity.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), entity.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), entity.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), entity.getTimeAttr());
        assertEquals(Enum.EnumA, entity.getEnumAttr());

        assertEquals(1, transferWithRequiredFieldsMapsEntityWithRequiredFieldsDao.query().filterByStringAttr(StringFilter.equalTo("Hello there")).count());

        t3.setStringAttr("update");
        t3 = transferWithRequiredFieldsMapsEntityWithRequiredFieldsDao.update(t3);
        assertEquals(1, transferWithRequiredFieldsMapsEntityWithRequiredFieldsDao.countAll());
        assertEquals(1, transferWithRequiredFieldsMapsEntityWithRequiredFieldsDao.query().filterByStringAttr(StringFilter.equalTo("update")).count());
        assertEquals(0, transferWithRequiredFieldsMapsEntityWithRequiredFieldsDao.query().filterByStringAttr(StringFilter.equalTo("Hello there")).count());

        t3.setStringAttr(null);
        final TransferWithRequiredFieldsMapsEntityWithRequiredFields finalT3 = t3;

        exception = assertThrows(ValidationException.class, () -> transferWithRequiredFieldsMapsEntityWithRequiredFieldsDao.update(finalT3));

        assertEquals(1, exception.getValidationResults().size());
        validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        assertEquals("stringAttr", validationResult.getLocation());
        transferWithRequiredFieldsMapsEntityWithRequiredFieldsDao.delete(t3);

        TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFields t4 = transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao.create(TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsForCreate.builder().build());

        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao.countAll());
        assertEquals(1, t4.getIntAttr());
        assertEquals(2.34, t4.getScaledAttr());
        assertEquals("Hello there", t4.getStringAttr());
        assertEquals("+36 30 123 1234", t4.getRegexAttr());
        assertEquals(true, t4.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), t4.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), t4.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), t4.getTimeAttr());
        assertEquals(Enum.EnumA, t4.getEnumAttr());

        e1 = entityWithRequiredFieldsDao.getById(t4.adaptTo(EntityWithRequiredFieldsIdentifier.class));

        assertTrue(e1.isPresent());

        entity = e1.orElseThrow();

        assertEquals(1, entity.getIntegerAttr());
        assertEquals(2.34, entity.getScaledAttr());
        assertEquals("Hello there", entity.getStringAttr());
        assertEquals("+36 30 123 1234", entity.getRegexAttr());
        assertEquals(true, entity.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 11), entity.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-11T19:09:33"), entity.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), entity.getTimeAttr());
        assertEquals(Enum.EnumA, entity.getEnumAttr());

        transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao.delete(t4);

        t4 = transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao.create(TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsForCreate.builder().withStringAttr("init").build());
        assertEquals("init", t4.getStringAttr());
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao.countAll());
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao.query().filterByStringAttr(StringFilter.equalTo("init")).count());

        t4.setStringAttr("update");
        t4 = transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao.update(t4);
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao.countAll());
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao.query().filterByStringAttr(StringFilter.equalTo("update")).count());
        assertEquals(0, transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao.query().filterByStringAttr(StringFilter.equalTo("init")).count());

        t4.setStringAttr(null);
        final TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFields finalT4 = t4;

        exception = assertThrows(ValidationException.class, () -> transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao.update(finalT4));

        assertEquals(1, exception.getValidationResults().size());
        validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        assertEquals("stringAttr", validationResult.getLocation());

        transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsDao.delete(t4);

        // TODO: JNG-4997
        //assertThrows(ValidationException.class, () -> transferWithRequiredFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsDao.create(TransferWithRequiredFieldsWithDefaultExpressionMapsEntityWithRequiredFields.builder().build()));
        //assertEquals(1, exception.getValidationResults().size());
        //validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        //assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        //assertEquals("stringAttr", validationResult.getLocation());

    }

    /**
     * This test check the mapped transfer object maps an entity with contains primitive optional field with default values.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel TransferConstructor.jsl
     *
     * @positiveRequirements
     *
     * @scenario
     *
     *  Create one instance of TransferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     */
    @Test
    @TestCase("EntityOptionalPrimitivesWithDefaultValue")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
            "REQ-SRV-003",
            "REQ-SRV-004",
            "REQ-EXPR-001"
    })
    void testTransferMapsEntityWithOptionalFieldsWithDefaultValues() {
        TransferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefault t1 = transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.create(TransferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultForCreate.builder().build());

        assertEquals(1, transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(Optional.of(1), t1.getIntAttr());
        assertEquals(Optional.of(2.34), t1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), t1.getStringAttr());
        assertEquals(Optional.of("+36-1-223-123"), t1.getRegexAttr());
        assertEquals(Optional.of(true), t1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2021, 7, 11)), t1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2021-07-11T19:09:33")), t1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), t1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), t1.getEnumAttr());

        Optional<EntityWithOptionalFieldsWithDefault> e1Optional = entityWithOptionalFieldsWithDefaultDao.getById(t1.adaptTo(EntityWithOptionalFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        EntityWithOptionalFieldsWithDefault e1 = e1Optional.orElseThrow();

        assertEquals(Optional.of(1), e1.getIntegerAttr());
        assertEquals(Optional.of(2.34), e1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), e1.getStringAttr());
        assertEquals(Optional.of("+36-1-223-123"), e1.getRegexAttr());
        assertEquals(Optional.of(true), e1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2021, 7, 11)), e1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2021-07-11T19:09:33")), e1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), e1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), e1.getEnumAttr());
        transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.delete(t1);

        t1 = transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.create(TransferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultForCreate.builder().withEnumAttr(Enum.EnumB).build());
        assertEquals(Optional.of(Enum.EnumB), t1.getEnumAttr());
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumB)).count());

        t1.setEnumAttr(Enum.EnumC);
        t1.setRegexAttr("+36-1-223-345");
        t1 = transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.update(t1);
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumC)).count());
        assertEquals(0, transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumB)).count());
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByRegexAttr(StringFilter.equalTo("+36-1-223-345")).count());

        t1.setEnumAttr(null);
        transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.update(t1);
        assertEquals(0, transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumC)).count());
        transferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefaultDao.delete(t1);


        TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.create(TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultForCreate.builder().build());

        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(Optional.of(2), t2.getIntAttr());
        assertEquals(Optional.of(3.34), t2.getScaledAttr());
        assertEquals(Optional.of("Lorem Ipsum"), t2.getStringAttr());
        assertEquals(Optional.of("+36 30 123 5678"), t2.getRegexAttr());
        assertEquals(Optional.of(false), t2.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 12)), t2.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-12T19:09:33")), t2.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:58")), t2.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumB), t2.getEnumAttr());

        e1Optional = entityWithOptionalFieldsWithDefaultDao.getById(t2.adaptTo(EntityWithOptionalFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(Optional.of(2), e1.getIntegerAttr());
        assertEquals(Optional.of(3.34), e1.getScaledAttr());
        assertEquals(Optional.of("Lorem Ipsum"), e1.getStringAttr());
        assertEquals(Optional.of("+36 30 123 5678"), e1.getRegexAttr());
        assertEquals(Optional.of(false), e1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 12)), e1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-12T19:09:33")), e1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:58")), e1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumB), e1.getEnumAttr());
        transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.delete(t2);

        t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.create(TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultForCreate.builder().withEnumAttr(Enum.EnumC).build());
        assertEquals(Optional.of(Enum.EnumC), t2.getEnumAttr());
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumC)).count());

        t2.setEnumAttr(Enum.EnumB);
        t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.update(t2);
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumB)).count());
        assertEquals(0, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumC)).count());

        t2.setEnumAttr(null);
        transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.update(t2);
        assertEquals(0, transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumB)).count());
        transferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.delete(t2);


        TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefault t2Expression = transferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefaultDao.create(TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefaultForCreate.builder().build());

        assertEquals(Optional.empty(), t2Expression.getStringAttr());
        assertEquals( 1, transferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefaultDao.countAll());

        e1Optional = entityWithOptionalFieldsWithDefaultDao.getById(t2Expression.adaptTo(EntityWithOptionalFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(Optional.empty(), e1.getStringAttr());

        transferWithOptionalFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefaultDao.delete(t2Expression);

        TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefault t3 = transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao.create(TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultForCreate.builder().build());

        assertEquals(1, transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(1, t3.getIntAttr());
        assertEquals(2.34, t3.getScaledAttr());
        assertEquals("Hello there", t3.getStringAttr());
        assertEquals("+36-1-223-123", t3.getRegexAttr());
        assertEquals(true, t3.getBoolAttr());
        assertEquals(LocalDate.of(2021, 7, 11), t3.getDateAttr());
        assertEquals(LocalDateTime.parse("2021-07-11T19:09:33"), t3.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), t3.getTimeAttr());
        assertEquals(Enum.EnumA, t3.getEnumAttr());

        e1Optional = entityWithOptionalFieldsWithDefaultDao.getById(t3.adaptTo(EntityWithOptionalFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(Optional.of(1), e1.getIntegerAttr());
        assertEquals(Optional.of(2.34), e1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), e1.getStringAttr());
        assertEquals(Optional.of("+36-1-223-123"), e1.getRegexAttr());
        assertEquals(Optional.of(true), e1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2021, 7, 11)), e1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2021-07-11T19:09:33")), e1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), e1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), e1.getEnumAttr());
        transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao.delete(t3);

        t3 = transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao.create(TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultForCreate.builder().withEnumAttr(Enum.EnumB).build());
        assertEquals(Enum.EnumB, t3.getEnumAttr());
        assertEquals(1, transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumB)).count());

        t3.setEnumAttr(Enum.EnumC);
        t3 = transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao.update(t3);
        assertEquals(1, transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumC)).count());
        assertEquals(0, transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumB)).count());

        t3.setStringAttr(null);
        final TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefault finalT3 = t3;

        ValidationException exception = assertThrows(ValidationException.class, () -> transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao.update(finalT3));

        assertEquals(1, exception.getValidationResults().size());
        ValidationResult validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        assertEquals("stringAttr", validationResult.getLocation());

        transferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefaultDao.delete(t3);

        TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault t4 = transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.create(TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultForCreate.builder().build());

        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(2, t4.getIntAttr());
        assertEquals(3.34, t4.getScaledAttr());
        assertEquals("Lorem Ipsum", t4.getStringAttr());
        assertEquals("+36 30 123 5678", t4.getRegexAttr());
        assertEquals(false, t4.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 12), t4.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-12T19:09:33"), t4.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:58"), t4.getTimeAttr());
        assertEquals(Enum.EnumB, t4.getEnumAttr());

        e1Optional = entityWithOptionalFieldsWithDefaultDao.getById(t4.adaptTo(EntityWithOptionalFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(Optional.of(2), e1.getIntegerAttr());
        assertEquals(Optional.of(3.34), e1.getScaledAttr());
        assertEquals(Optional.of("Lorem Ipsum"), e1.getStringAttr());
        assertEquals(Optional.of("+36 30 123 5678"), e1.getRegexAttr());
        assertEquals(Optional.of(false), e1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 12)), e1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-12T19:09:33")), e1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:58")), e1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumB), e1.getEnumAttr());
        transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.delete(t4);

        t4 = transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.create(TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultForCreate.builder().withEnumAttr(Enum.EnumA).build());
        assertEquals(Enum.EnumA, t4.getEnumAttr());
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumA)).count());

        t4.setEnumAttr(Enum.EnumC);
        t4 = transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.update(t4);
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumC)).count());
        assertEquals(0, transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumA)).count());

        t4.setStringAttr(null);
        final TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault finalT4 = t4;

        exception = assertThrows(ValidationException.class, () -> transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.update(finalT4));

        assertEquals(1, exception.getValidationResults().size());
        validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        assertEquals("stringAttr", validationResult.getLocation());

        transferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefaultDao.delete(t4);

        // TODO: JNG-4997
        //exception = assertThrows(ValidationException.class, () -> transferWithRequiredFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefaultDao.create(TransferWithRequiredFieldsWithDefaultExpressionMapsEntityWithOptionalFieldsWithDefault.builder().build()));
        //assertEquals(1, exception.getValidationResults().size());
        //validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        //assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        //assertEquals("stringAttr", validationResult.getLocation());
    }

    /**
     * This test check the mapped transfer object maps an entity with contains primitive required field with default values.
     *
     * @prerequisites The model runtime is empty. It means that the database of the application has to be empty before this test starts running.
     *
     * @type Behaviour
     *
     * @others Implement this test case in the *judo-runtime-core-jsl-itest* module.
     *
     * @jslModel TransferConstructor.jsl
     *
     * @positiveRequirements
     *
     * @scenario
     *
     *  Create one instance of TransferWithOptionalFieldsMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithOptionalFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithRequiredFieldsMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     *  Create one instance of TransferWithRequiredFieldsWithDefaultMapsEntityWithOptionalFieldsWithDefault.
     *
     *  Check the transfer instance contains the mapped values.
     *
     *  Get the entity representation the transfer instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     */
    @Test
    @TestCase("EntityRequiredPrimitivesWithDefaultValue")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-SRV-002",
            "REQ-SRV-003",
            "REQ-SRV-004",
            "REQ-EXPR-001"
    })
    void testTransferMapsEntityWithRequiredFieldsWithDefault() {
        TransferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefault t1 = transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao.create(TransferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultForCreate.builder().build());

        assertEquals(1, transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(Optional.of(1), t1.getIntAttr());
        assertEquals(Optional.of(2.34), t1.getScaledAttr());
        assertEquals(Optional.of("Hello there"), t1.getStringAttr());
        assertEquals(Optional.of("+36-1-223-123"), t1.getRegexAttr());
        assertEquals(Optional.of(true), t1.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2021, 7, 11)), t1.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2021-07-11T19:09:33")), t1.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:59")), t1.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumA), t1.getEnumAttr());

        Optional<EntityWithRequiredFieldsWithDefault> e1Optional = entityWithRequiredFieldsWithDefaultDao.getById(t1.adaptTo(EntityWithRequiredFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        EntityWithRequiredFieldsWithDefault e1 = e1Optional.orElseThrow();

        assertEquals(1, e1.getIntegerAttr());
        assertEquals(2.34, e1.getScaledAttr());
        assertEquals("Hello there", e1.getStringAttr());
        assertEquals("+36-1-223-123", e1.getRegexAttr());
        assertEquals(true, e1.getBoolAttr());
        assertEquals(LocalDate.of(2021, 7, 11), e1.getDateAttr());
        assertEquals(LocalDateTime.parse("2021-07-11T19:09:33"), e1.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), e1.getTimeAttr());
        assertEquals(Enum.EnumA, e1.getEnumAttr());
        transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao.delete(t1);

        t1 = transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao.create(TransferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultForCreate.builder().withEnumAttr(Enum.EnumB).build());
        assertEquals(Optional.of(Enum.EnumB), t1.getEnumAttr());
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumB)).count());

        t1.setEnumAttr(Enum.EnumC);
        t1 = transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao.update(t1);
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumC)).count());
        assertEquals(0, transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumB)).count());


        t1.setEnumAttr(null);
        final TransferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefault finalT1 = t1;
        ValidationException exception = assertThrows(ValidationException.class, () -> transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao.update(finalT1));

        assertEquals(1, exception.getValidationResults().size());
        ValidationResult validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        assertEquals("enumAttr", validationResult.getLocation());

        transferWithOptionalFieldsMapsEntityWithRequiredFieldsWithDefaultDao.delete(t1);

        TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefault t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.create(TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultForCreate.builder().build());

        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(Optional.of(2), t2.getIntAttr());
        assertEquals(Optional.of(3.34), t2.getScaledAttr());
        assertEquals(Optional.of("Lorem Ipsum"), t2.getStringAttr());
        assertEquals(Optional.of("+36 30 123 5678"), t2.getRegexAttr());
        assertEquals(Optional.of(false), t2.getBoolAttr());
        assertEquals(Optional.of(LocalDate.of(2022, 7, 12)), t2.getDateAttr());
        assertEquals(Optional.of(LocalDateTime.parse("2022-07-12T19:09:33")), t2.getTimestampAttr());
        assertEquals(Optional.of(LocalTime.parse("23:59:58")), t2.getTimeAttr());
        assertEquals(Optional.of(Enum.EnumB), t2.getEnumAttr());

        e1Optional = entityWithRequiredFieldsWithDefaultDao.getById(t2.adaptTo(EntityWithRequiredFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(2, e1.getIntegerAttr());
        assertEquals(3.34, e1.getScaledAttr());
        assertEquals("Lorem Ipsum", e1.getStringAttr());
        assertEquals("+36 30 123 5678", e1.getRegexAttr());
        assertEquals(false, e1.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 12), e1.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-12T19:09:33"), e1.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:58"), e1.getTimeAttr());
        assertEquals(Enum.EnumB, e1.getEnumAttr());

        transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.delete(t2);

        t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.create(TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultForCreate.builder().withEnumAttr(Enum.EnumA).build());
        assertEquals(Optional.of(Enum.EnumA), t2.getEnumAttr());
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumA)).count());

        t2.setEnumAttr(Enum.EnumC);
        t2 = transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.update(t2);
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumC)).count());
        assertEquals(0, transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumA)).count());

        t2.setStringAttr(null);
        final TransferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefault finalT2 = t2;

        exception = assertThrows(ValidationException.class, () -> transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.update(finalT2));

        assertEquals(1, exception.getValidationResults().size());
        validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        assertEquals("stringAttr", validationResult.getLocation());
        transferWithOptionalFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.delete(t2);

        // TODO: JNG-4997
        //exception = assertThrows(ValidationException.class, () -> transferWithOptionalFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsWithDefaultDao.create(TransferWithOptionalFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsWithDefault.builder().build()));
        //assertEquals(1, exception.getValidationResults().size());
        //validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        //assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        //assertEquals("stringAttr", validationResult.getLocation());

        TransferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefault t3 = transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao.create(TransferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultForCreate.builder().build());
        assertEquals(1, transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(1, t3.getIntAttr());
        assertEquals(2.34, t3.getScaledAttr());
        assertEquals("Hello there", t3.getStringAttr());
        assertEquals("+36-1-223-123", t3.getRegexAttr());
        assertEquals(true, t3.getBoolAttr());
        assertEquals(LocalDate.of(2021, 7, 11), t3.getDateAttr());
        assertEquals(LocalDateTime.parse("2021-07-11T19:09:33"), t3.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), t3.getTimeAttr());
        assertEquals(Enum.EnumA, t3.getEnumAttr());

        e1Optional = entityWithRequiredFieldsWithDefaultDao.getById(t3.adaptTo(EntityWithRequiredFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(1, e1.getIntegerAttr());
        assertEquals(2.34, e1.getScaledAttr());
        assertEquals("Hello there", e1.getStringAttr());
        assertEquals("+36-1-223-123", e1.getRegexAttr());
        assertEquals(true, e1.getBoolAttr());
        assertEquals(LocalDate.of(2021, 7, 11), e1.getDateAttr());
        assertEquals(LocalDateTime.parse("2021-07-11T19:09:33"), e1.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:59"), e1.getTimeAttr());
        assertEquals(Enum.EnumA, e1.getEnumAttr());
        transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao.delete(t3);

        t3 = transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao.create(TransferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultForCreate.builder().withEnumAttr(Enum.EnumA).build());
        assertEquals(Enum.EnumA, t3.getEnumAttr());
        assertEquals(1, transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumA)).count());

        t3.setEnumAttr(Enum.EnumC);
        t3 = transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao.update(t3);
        assertEquals(1, transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumC)).count());
        assertEquals(0, transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumA)).count());

        t3.setStringAttr(null);
        final TransferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefault finalT3 = t3;
        assertThrows(ValidationException.class, () -> transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao.update(finalT3));

        transferWithRequiredFieldsMapsEntityWithRequiredFieldsWithDefaultDao.delete(t3);


        TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefault t4 = transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.create(TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultForCreate.builder().build());

        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(2, t4.getIntAttr());
        assertEquals(3.34, t4.getScaledAttr());
        assertEquals("Lorem Ipsum", t4.getStringAttr());
        assertEquals("+36 30 123 5678", t4.getRegexAttr());
        assertEquals(false, t4.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 12), t4.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-12T19:09:33"), t4.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:58"), t4.getTimeAttr());
        assertEquals(Enum.EnumB, t4.getEnumAttr());

        e1Optional = entityWithRequiredFieldsWithDefaultDao.getById(t4.adaptTo(EntityWithRequiredFieldsWithDefaultIdentifier.class));

        assertTrue(e1Optional.isPresent());

        e1 = e1Optional.orElseThrow();

        assertEquals(2, e1.getIntegerAttr());
        assertEquals(3.34, e1.getScaledAttr());
        assertEquals("Lorem Ipsum", e1.getStringAttr());
        assertEquals("+36 30 123 5678", e1.getRegexAttr());
        assertEquals(false, e1.getBoolAttr());
        assertEquals(LocalDate.of(2022, 7, 12), e1.getDateAttr());
        assertEquals(LocalDateTime.parse("2022-07-12T19:09:33"), e1.getTimestampAttr());
        assertEquals(LocalTime.parse("23:59:58"), e1.getTimeAttr());
        assertEquals(Enum.EnumB, e1.getEnumAttr());
        transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.delete(t4);

        t4 = transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.create(TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultForCreate.builder().withEnumAttr(Enum.EnumA).build());
        assertEquals(Enum.EnumA, t4.getEnumAttr());
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumA)).count());

        t4.setEnumAttr(Enum.EnumC);
        t4 = transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.update(t4);
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.countAll());
        assertEquals(1, transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumC)).count());
        assertEquals(0, transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.query().filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumA)).count());

        t4.setStringAttr(null);
        final TransferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefault finalT4 = t4;
        assertThrows(ValidationException.class, () -> transferWithRequiredFieldsWithDefaultMapsEntityWithRequiredFieldsWithDefaultDao.update(finalT4));

        assertEquals(1, exception.getValidationResults().size());
        validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        assertEquals("stringAttr", validationResult.getLocation());

        // TODO: JNG-4997
        //exception = assertThrows(ValidationException.class, () -> transferWithRequiredFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsWithDefaultDao.create(TransferWithRequiredFieldsWithDefaultExpressionMapsEntityWithRequiredFieldsWithDefault.builder().build()));
        //assertEquals(1, exception.getValidationResults().size());
        //validationResult = exception.getValidationResults().stream().findAny().orElseThrow();
        //assertEquals("MISSING_REQUIRED_ATTRIBUTE", validationResult.getCode());
        //assertEquals("stringAttr", validationResult.getLocation());

    }

    @Test
    void testMapsAFieldTwice() {
        TransferMapsFieldTwice transferMapsFieldTwice = transferMapsFieldTwiceDao.create(
                TransferMapsFieldTwiceForCreate.builder().build()
        );

        assertEquals(Optional.of(1), transferMapsFieldTwice.getIntAttr());
        assertEquals(1, transferMapsFieldTwice.getIntegerAttr());
    }
}
