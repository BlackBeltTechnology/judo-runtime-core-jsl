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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
                .withStringC("TEST-C")
                .withMultipleDonB(List.of(entityD1, entityD2))
                .build());
        singleConA = entityCDao.create(EntityC.builder()
                .build());
        entityA = entityADao.create(EntityA.builder()
                .withStringA("TEST-A")
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

    @Disabled
    void testDeleteRequiredRelationThrowsException() {
        entityA.setSingleRequiredConA(null);
        entityADao.update(entityA);
        EntityA aaa = entityADao.getById(entityA.get__identifier());
        assertNull(aaa.getSingleRequiredConA());
//        IllegalArgumentException thrown = assertThrows(
//                IllegalArgumentException.class,
//                () -> entityADao.update(entityA)
//        );
        // FIXME JNG-3859

//        assertTrue(thrown.getMessage().contains("missing mandatory attribute"));
//        assertTrue(thrown.getMessage().contains("name: singleRequiredConA"));
    }

    @Test
    void testTraverse() {
        List<EntityA> maskedAs = entityADao.query().execute();
        EntityA maskedA = maskedAs.get(0);
        EntityC singleRequiredConA = maskedA.getSingleRequiredConA();


        assertEquals(Optional.of("TEST-A"), maskedA.getStringA());
        assertNotEquals(Optional.empty(), maskedA.getSingleConA());
        assertNotEquals(null, maskedA.getSingleRequiredConA());
        assertEquals(Optional.of("TEST-C"), singleRequiredConA.getStringC());
        assertEquals(2, singleRequiredConA.getMultipleDonB().size());
        assertEquals(Optional.of(entityD1), singleRequiredConA.getMultipleDonB().stream().filter(d -> d.get__identifier().equals(entityD1.get__identifier())).findFirst());
        assertEquals(Optional.of(entityD2), singleRequiredConA.getMultipleDonB().stream().filter(d -> d.get__identifier().equals(entityD2.get__identifier())).findFirst());
    }

    @Test
    void testMask() {
        List<EntityA> maskedAs = entityADao.query().maskedBy(EntityA.EntityADao.Mask.entityAMask().withSingleRequiredConA(EntityC.EntityCDao.Mask.entityCMask().withStringC())).execute();

        EntityA maskedA = maskedAs.get(0);
        EntityC requiredC = maskedA.getSingleRequiredConA();

        assertEquals(1, maskedAs.size());
        assertEquals(Optional.empty(), maskedA.getSingleConA());
        assertEquals(Optional.empty(), maskedA.getStringA());
        assertEquals(singleRequiredConA, maskedA.getSingleRequiredConA());
        assertEquals(Optional.empty(), requiredC.getStringB());
        assertEquals(Optional.of("TEST-C"), requiredC.getStringC());
    }

    @Test
    void testUpdateRootUpdatesInterimElement() {
        EntityA entityA2 = entityADao.query().execute().get(0);
        EntityC requiredC = entityA2.getSingleRequiredConA();
        EntityC singleC = entityA2.getSingleConA().get();

        assertEquals(Optional.empty(), requiredC.getStringB());
        assertEquals(Optional.empty(), singleC.getStringB());
        assertEquals(Optional.empty(), singleC.getStringC());
        assertEquals(Optional.of(singleConA), entityA2.getSingleConA());

        requiredC.setStringB("Hello!");
        singleC.setStringB("NEW-B");
        singleC.setStringC("NEW-C");

        EntityA updatedA = entityADao.update(entityA2);

        assertEquals(Optional.of("Hello!"), updatedA.getSingleRequiredConA().getStringB());
        assertEquals(Optional.of("NEW-B"), entityA2.getSingleConA().get().getStringB());
        assertEquals(Optional.of("NEW-C"), entityA2.getSingleConA().get().getStringC());
    }
}
