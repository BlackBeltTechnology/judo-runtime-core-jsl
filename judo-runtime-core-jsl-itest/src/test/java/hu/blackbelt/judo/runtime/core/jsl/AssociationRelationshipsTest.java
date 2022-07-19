package hu.blackbelt.judo.runtime.core.jsl;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoDefaultModule;
import hu.blackbelt.judo.runtime.core.bootstrap.JudoModelLoader;
import hu.blackbelt.judo.runtime.core.bootstrap.dao.rdbms.hsqldb.JudoHsqldbModules;
import hu.blackbelt.judo.runtime.core.dao.rdbms.hsqldb.HsqldbDialect;
import hu.blackbelt.judo.runtime.core.jsl.itest.associationrelationships.guice.associationrelationships.AssociationRelationshipsDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.associationrelationships.sdk.associationrelationships.associationrelationships.EntityA;
import hu.blackbelt.judo.runtime.core.jsl.itest.associationrelationships.sdk.associationrelationships.associationrelationships.EntityC;
import hu.blackbelt.judo.runtime.core.jsl.itest.associationrelationships.sdk.associationrelationships.associationrelationships.EntityD;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class AssociationRelationshipsTest {
    Injector injector;

    @Inject
    EntityA.EntityADao entityADao;

    @Inject
    EntityC.EntityCDao entityCDao;

    @Inject
    EntityD.EntityDDao entityDDao;

    EntityD entityD;
    EntityC entityC;
    EntityA entityA;

    @BeforeEach
    void init() throws Exception {
        JudoModelLoader modelHolder = JudoModelLoader.
                loadFromClassloader("AssociationRelationships", AssociationRelationshipsTest.class.getClassLoader(), new HsqldbDialect(), true);

        injector = Guice.createInjector(
                JudoHsqldbModules.builder().build(),
                new AssociationRelationshipsDaoModules(),
                new JudoDefaultModule(this, modelHolder));

        entityD = entityDDao.create(EntityD.builder()
                .build());
        entityC = entityCDao.create(EntityC.builder()
                .build());
        entityA = createA(entityC, List.of(entityD));
    }

    @Test
    public void testSetUnsetSingleRelation() {
        assertEquals(Optional.empty(), entityADao.getSingleConA(entityA));

        entityADao.setSingleConA(entityA, entityC);

        assertEquals(Optional.of(entityC), entityADao.getSingleConA(entityA));

        entityADao.unsetSingleConA(entityA);

        assertEquals(Optional.empty(), entityADao.getSingleConA(entityA));
    }

    @Test
    public void testAddRemoveMultipleRelations() {
        assertEquals(List.of(), entityCDao.getMultipleAonB(entityC));

        EntityA entityA2 = createA(entityC, List.of(entityD));

        entityCDao.addMultipleAonB(entityC, List.of(entityA, entityA2));

        assertEquals(2, entityCDao.getMultipleAonB(entityC).size());

        entityCDao.removeMultipleAonB(entityC, List.of(entityA));

        assertEquals(List.of(entityA2), entityCDao.getMultipleAonB(entityC));
    }

    @Test
    public void testRequiredRelationEnforced() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> entityADao.create(EntityA.builder().build())
        );

        assertTrue(thrown.getMessage().contains("missing mandatory attribute"));
        assertTrue(thrown.getMessage().contains("name: singleRequiredConA"));

        List<EntityA> list = entityADao.query().execute();

        assertEquals(1, list.size()); // we are expecting 1, because the beforeEach() setup already created 1 element
    }

    @Test
    public void testTraverse() {
        EntityD entityD2 = entityDDao.create(EntityD.builder().build());

        entityCDao.addMultipleDonC(entityC, List.of(entityD2));

        EntityA startA = entityADao.getById(entityA.get__identifier());

        EntityC entityC2 = entityADao.getSingleRequiredConA(startA);

        assertEquals(entityC, entityC2);

        List<EntityA> entityA2 = entityCDao.getTwoWayMultipleAonC(entityC2);

        assertEquals(1, entityA2.size());
        assertEquals(entityA, entityA2.get(0));
    }

    private EntityA createA(EntityC entityC, List<EntityD> entityDs) {
        return entityADao.create(EntityA.builder()
                .withSingleRequiredConA(entityC)
                .withMultipleDonA(entityDs)
                .build());
    }
}
