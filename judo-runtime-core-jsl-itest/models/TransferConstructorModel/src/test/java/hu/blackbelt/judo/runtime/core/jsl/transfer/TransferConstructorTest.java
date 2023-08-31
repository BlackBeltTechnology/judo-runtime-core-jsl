package hu.blackbelt.judo.runtime.core.jsl.transfer;

import com.google.inject.Inject;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithmultirelation.EntityWithMultiRelation;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithmultirelation.EntityWithMultiRelationDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithmultirelation.EntityWithMultiRelationIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithsinglerelation.EntityWithSingleRelation;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithsinglerelation.EntityWithSingleRelationDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.entitywithsinglerelation.EntityWithSingleRelationIdentifier;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.mappedentitywithmultirelation.MappedEntityWithMultiRelation;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.mappedentitywithmultirelation.MappedEntityWithMultiRelationDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.mappedentitywithsinglerelation.MappedEntityWithSingleRelation;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.transferconstructor.transferconstructor.mappedentitywithsinglerelation.MappedEntityWithSingleRelationDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.TransferConstructorDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class TransferConstructorTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("TransferConstructor", new TransferConstructorDaoModules());

    @Inject
    MappedEntityWithMultiRelationDao mappedEntityWithMultiRelationDao;

    @Inject
    MappedEntityWithSingleRelationDao mappedEntityWithSingleRelationDao;

    @Inject
    EntityWithSingleRelationDao entityWithSingleRelationDao;

    @Inject
    EntityWithMultiRelationDao entityWithMultiRelationDao;

    /**
     * This test check the transfer constructor on an entity with relations.
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
     *  Create one instance of mappedEntityWithMultiRelation.
     *
     *  Create one instance of mappedEntityWithSingleRelation.
     *
     *  Check the mappedEntityWithSingleRelation instance contains the constructor expressions.
     *
     *  Get the entity representation the MappedEntityWithSingleRelation instance.
     *
     *  Check the entity representation contains the constructor assigned values.
     *
     */
    @Test
    public void testTransferAssossiationConstrtuctor() {

        MappedEntityWithMultiRelation mappedEntityWithMultiRelation = mappedEntityWithMultiRelationDao.create(MappedEntityWithMultiRelation.builder().build());

        MappedEntityWithSingleRelation mappedEntityWithSingleRelation = mappedEntityWithSingleRelationDao.create(MappedEntityWithSingleRelation.builder().build());

        assertEquals(mappedEntityWithSingleRelationDao.queryMappedAssociation(mappedEntityWithSingleRelation).orElseThrow().identifier(),mappedEntityWithMultiRelation.identifier());
        assertEquals(mappedEntityWithSingleRelationDao.queryMappedAssociationCollection(mappedEntityWithSingleRelation).count(),1);
        assertEquals(mappedEntityWithSingleRelationDao.queryMappedAssociationCollection(mappedEntityWithSingleRelation).execute().get(0).identifier(),mappedEntityWithMultiRelation.identifier());
        assertEquals(mappedEntityWithSingleRelationDao.queryMappedAssociationOpposite(mappedEntityWithSingleRelation).orElseThrow().identifier(),mappedEntityWithMultiRelation.identifier());
        assertEquals(mappedEntityWithSingleRelationDao.queryMappedAssociationOppositeCollection(mappedEntityWithSingleRelation).count(),1);
        assertEquals(mappedEntityWithSingleRelationDao.queryMappedAssociationOppositeCollection(mappedEntityWithSingleRelation).execute().get(0).identifier(),mappedEntityWithMultiRelation.identifier());

        Optional<EntityWithSingleRelation> entityWithSingleRelationOptional = entityWithSingleRelationDao.getById(mappedEntityWithSingleRelation.adaptTo(EntityWithSingleRelationIdentifier.class));

        assertTrue(entityWithSingleRelationOptional.isPresent());

        EntityWithSingleRelation entityWithSingleRelation = entityWithSingleRelationOptional.orElseThrow();

        EntityWithMultiRelation entityWithMultiRelation = entityWithMultiRelationDao.getById(mappedEntityWithMultiRelation.adaptTo(EntityWithMultiRelationIdentifier.class)).orElseThrow();

        assertEquals(entityWithSingleRelationDao.queryAssociation(entityWithSingleRelation).orElseThrow().identifier(),entityWithMultiRelation.identifier());
        assertEquals(entityWithSingleRelationDao.queryAssociationCollection(entityWithSingleRelation).count(),1);
        assertEquals(entityWithSingleRelationDao.queryAssociationCollection(entityWithSingleRelation).execute().get(0).identifier(),entityWithMultiRelation.identifier());
        assertEquals(entityWithSingleRelationDao.queryEntityWithTwoWayOpposite(entityWithSingleRelation).orElseThrow().identifier(),entityWithMultiRelation.identifier());
        assertEquals(entityWithSingleRelationDao.queryEntityWithTwoWayOppositeCollection(entityWithSingleRelation).count(),1);
        assertEquals(entityWithSingleRelationDao.queryEntityWithTwoWayOppositeCollection(entityWithSingleRelation).execute().get(0).identifier(),entityWithMultiRelation.identifier());


        //transient relations
//        assertEquals(mappedEntityWithSingleRelationDao.queryTransientRelation(mappedEntityWithSingleRelation).orElseThrow().identifier(),mappedEntityWithMultiRelation.identifier());
//        assertEquals(mappedEntityWithSingleRelationDao.queryTransientRelationCollection(mappedEntityWithSingleRelation).count(),1);
//        assertEquals(mappedEntityWithSingleRelationDao.queryTransientRelationCollection(mappedEntityWithSingleRelation).execute().get(0).identifier(),mappedEntityWithMultiRelation.identifier());

    }


}
