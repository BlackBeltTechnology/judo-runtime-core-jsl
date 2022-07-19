package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoDefaultModule;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoModelLoader;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.hsqldb.JudoHsqldbModules;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;
import hu.blackbelt.judo.runtime.core.jsl.itest.compositionrelationships.guice.compositionrelationships.CompositionRelationshipsDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.compositionrelationships.sdk.compositionrelationships.compositionrelationships.EntityA;
import hu.blackbelt.judo.runtime.core.jsl.itest.compositionrelationships.sdk.compositionrelationships.compositionrelationships.EntityC;
import hu.blackbelt.judo.runtime.core.jsl.itest.compositionrelationships.sdk.compositionrelationships.compositionrelationships.EntityD;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class CompositionRelationshipsTest {
    Injector injector;

    @Inject
    EntityA.EntityADao entityADao;

    @Inject
    EntityC.EntityCDao entityCDao;

    @Inject
    EntityD.EntityDDao entityDDao;

    EntityA entityA;
    EntityC singleConA;
    EntityC singleRequiredConA;
    EntityD entityD1;
    EntityD entityD2;


    @BeforeEach
    void init() throws Exception {
        JudoModelLoader modelHolder = JudoModelLoader.
                loadFromClassloader("CompositionRelationships", CompositionRelationshipsTest.class.getClassLoader(), new HsqldbDialect(), true);

        injector = Guice.createInjector(
                JudoHsqldbModules.builder().build(),
                new CompositionRelationshipsDaoModules(),
                new JudoDefaultModule(this, modelHolder));

        entityD1 = entityDDao.create(EntityD.builder()
                .build());
        entityD2 = entityDDao.create(EntityD.builder()
                .build());
        singleRequiredConA = entityCDao.create(EntityC.builder()
                .withMultipleDonB(List.of(entityD1, entityD2))
                .build());
        singleConA = entityCDao.create(EntityC.builder()
                .build());
        entityA = entityADao.create(EntityA.builder()
                .withSingleRequiredConA(singleRequiredConA)
                .withSingleConA(singleConA)
                .build());
    }

    @Test
    void testMissingRequiredRelationshipThrowsException() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> entityADao.create(EntityA.builder().build())
        );

        assertTrue(thrown.getMessage().contains("missing mandatory attribute"));
        assertTrue(thrown.getMessage().contains("name: singleRequiredConA"));
    }

    @Test
    void testUnsetOptionalRelationRemovesNested() {
        assertEquals(Optional.of(singleConA), entityADao.getSingleConA(entityA));
        assertEquals(2, entityCDao.query().execute().size());

        entityA.setSingleConA(null);
        entityADao.update(entityA);

        // entityADao.unsetSingleConA(entityA); FIXME: JNG-3858

        assertEquals(Optional.empty(), entityADao.getSingleConA(entityA));
        assertEquals(1, entityCDao.query().execute().size());
    }

    @Test
    void testDeleteOptionalRelation() {
        assertEquals(Optional.of(singleConA), entityADao.getSingleConA(entityA));
        assertEquals(2, entityCDao.query().execute().size());

        entityCDao.delete(singleConA);

        assertEquals(Optional.empty(), entityADao.getSingleConA(entityA));
        assertEquals(1, entityCDao.query().execute().size());
    }
}
