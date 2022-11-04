package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.itest.defaultrequiredvalue.guice.defaultrequiredvalue.DefaultRequiredValueDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.defaultrequiredvalue.sdk.defaultrequiredvalue.defaultrequiredvalue.DefaultRequiredEntity;
import hu.blackbelt.judo.runtime.core.jsl.itest.defaultrequiredvalue.sdk.defaultrequiredvalue.defaultrequiredvalue.ReferenceEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Slf4j
public class DefaultRequiredValueTest extends AbstractJslTest {

    @Inject
    ReferenceEntity.ReferenceEntityDao referenceEntityDao;

    @Inject
    DefaultRequiredEntity.DefaultRequiredEntityDao defaultRequiredEntityDao;

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

        referenceEntityDao.create(ReferenceEntity.builder().build());
        referenceEntityDao.create(ReferenceEntity.builder().build());

        DefaultRequiredEntity defaultEntity = defaultRequiredEntityDao.create(DefaultRequiredEntity.builder().build());

        assertEquals(6, defaultEntity.getSumEntitiesIntegerValue());
        assertEquals(LocalDate.of(2022, 11, 4), defaultEntity.getCreateDate());
        assertThrows(ValidationException.class, () -> defaultRequiredEntityDao.create(DefaultRequiredEntity.builder()
                .withCreateDate(LocalDate.of(2022, 11, 4))
                .build()));

        DefaultRequiredEntity defaultEntity1 = defaultRequiredEntityDao.create(DefaultRequiredEntity.builder()
                .withCreateDate(LocalDate.of(2022, 11, 5))
                .withSumEntitiesIntegerValue(5)
                .build());

        assertEquals(5, defaultEntity1.getSumEntitiesIntegerValue());
        assertEquals(LocalDate.of(2022, 11, 5), defaultEntity1.getCreateDate());
    }

}
