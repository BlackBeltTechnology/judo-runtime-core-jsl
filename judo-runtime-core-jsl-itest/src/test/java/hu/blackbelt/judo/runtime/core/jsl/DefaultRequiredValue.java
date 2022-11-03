package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.itest.defaultrequiredvalue.guice.defaultrequiredvalue.DefaultRequiredValueDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.defaultrequiredvalue.sdk.defaultrequiredvalue.defaultrequiredvalue.*;
import hu.blackbelt.judo.runtime.core.jsl.itest.primitives.sdk.primitives.primitives.EntityRequiredFields;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class DefaultRequiredValue extends AbstractJslTest {

    @Inject
    referenceEntity.referenceEntityDao referenceEntityDao;

    @Inject
    defaultRequiredEntity.defaultRequiredEntityDao defaultRequiredEntityDao;

    @Override
    public Module getModelDaoModule() {
        return new DefaultRequiredValueDaoModules();
    }

    @Override
    public String getModelName() {
        return "DefaultRequiredValue";
    }

    @Test
    public void testDefaultValues() {

        referenceEntity reference1 = referenceEntityDao.create(referenceEntity.builder().build());
        referenceEntity reference2 = referenceEntityDao.create(referenceEntity.builder().build());
        referenceEntity reference3 = referenceEntityDao.create(referenceEntity.builder().build());
        referenceEntity reference4 = referenceEntityDao.create(referenceEntity.builder().build());

        defaultRequiredEntity defaultEntity1 = defaultRequiredEntityDao.create(defaultRequiredEntity.builder().build());
        defaultRequiredEntity defaultEntity2 = defaultRequiredEntityDao.create(defaultRequiredEntity.builder().build());

        assertEquals(12,defaultEntity1.getSumEntitiesIntegerValue());

        defaultRequiredEntityDao.delete(defaultEntity1);

    }

    @Test()
    public void testMissingRequiredFieldsThrowExceptions() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> referenceEntityDao.create(referenceEntity.builder().build())
        );

        assertThat(thrown.getValidationResults(), containsInAnyOrder(
                matchMissingAttribute("stringAtt"),
                matchMissingAttribute("intAtt"),
                matchMissingAttribute("intDer")
        ));
        List<referenceEntity> list = referenceEntityDao.query().execute();

        assertEquals(0, list.size());
    }

    private org.hamcrest.Matcher matchMissingAttribute(String attrName) {
        return allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_ATTRIBUTE")),
                hasProperty("location", equalTo(attrName)));
    }

}
