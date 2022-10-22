package hu.blackbelt.judo.runtime.core.jsl;

/*-
 * #%L
 * JUDO Runtime Core JSL :: Parent
 * %%
 * Copyright (C) 2018 - 2022 BlackBelt Technology
 * %%
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the Eclipse
 * Public License, v. 2.0 are satisfied: GNU General Public License, version 2
 * with the GNU Classpath Exception which is
 * available at https://www.gnu.org/software/classpath/license.html.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 * #L%
 */

import com.google.inject.Inject;
import com.google.inject.Module;
import hu.blackbelt.judo.runtime.core.exception.ValidationException;
import hu.blackbelt.judo.runtime.core.jsl.itest.compositionrelationships.guice.compositionrelationships.CompositionRelationshipsDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.compositionrelationships.sdk.compositionrelationships.compositionrelationships.EntityA;
import hu.blackbelt.judo.runtime.core.jsl.itest.compositionrelationships.sdk.compositionrelationships.compositionrelationships.EntityC;
import hu.blackbelt.judo.runtime.core.jsl.itest.compositionrelationships.sdk.compositionrelationships.compositionrelationships.EntityD;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Slf4j
public class CompositionRelationshipsTest extends AbstractJslTest {
    @Inject
    EntityA.EntityADao entityADao;

    @Inject
    EntityC.EntityCDao entityCDao;

    @Inject
    EntityD.EntityDDao entityDDao;

    @Inject
    PlatformTransactionManager transactionManager;

    EntityA entityA;
    EntityC singleConA;
    EntityC singleRequiredConA;
    EntityD entityD1;
    EntityD entityD2;


    @BeforeEach
    protected void init() throws Exception {
        super.init();

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

    @Override
    public Module getModelDaoModule() {
        return new CompositionRelationshipsDaoModules();
    }

    @Override
    public String getModelName() {
        return "CompositionRelationships";
    }

    @Test
    void testMissingRequiredRelationshipThrowsException() {
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> entityADao.create(EntityA.builder().build())
        );

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredConA")))
        ));
    }

    @Test
    void testNullOutOptionalRelationRemovesNested() {
        assertEquals(Optional.of(singleConA), entityADao.getSingleConA(entityA));
        assertEquals(2, entityCDao.query().execute().size());

        entityA.setSingleConA(null);
        entityADao.update(entityA);

        assertEquals(Optional.empty(), entityADao.getSingleConA(entityA));
        assertEquals(1, entityCDao.query().execute().size());
        assertEquals(Optional.empty(), entityCDao.getById(singleConA.get__identifier()));
    }

    @Test
    void testUnsetOptionalRelationRemovesNested() {
        assertEquals(Optional.of(singleConA), entityADao.getSingleConA(entityA));
        assertEquals(2, entityCDao.query().execute().size());

        entityADao.unsetSingleConA(entityA);

        assertEquals(Optional.empty(), entityADao.getSingleConA(entityA));
        assertEquals(1, entityCDao.query().execute().size());
        assertEquals(Optional.empty(), entityCDao.getById(singleConA.get__identifier()));
    }

    @Test
    void testDeleteOptionalRelation() {
        assertEquals(Optional.of(singleConA), entityADao.getSingleConA(entityA));
        assertEquals(2, entityCDao.query().execute().size());

        entityCDao.delete(singleConA);

        assertEquals(Optional.empty(), entityADao.getSingleConA(entityA));
        assertEquals(1, entityCDao.query().execute().size());
    }

    @Test
    void testDeleteRequiredRelationThrowsException() {
        entityA.setSingleRequiredConA(null);
        ValidationException thrown = assertThrows(
                ValidationException.class,
                () -> entityADao.update(entityA)
        );

        assertThat(thrown.getValidationResults(), containsInAnyOrder(allOf(
                hasProperty("code", equalTo("MISSING_REQUIRED_RELATION")),
                hasProperty("location", equalTo("singleRequiredConA")))
        ));
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

    @Test
    void testManualTransactionManagementRollback() throws SystemException, NotSupportedException {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        assertEquals(Optional.of("TEST-A"), entityADao.getById(entityA.get__identifier()).get().getStringA());
        entityA.setStringA("BLAAA");
        entityADao.update(entityA);
        transactionManager.rollback(transactionStatus);
        assertEquals(Optional.of("TEST-A"), entityADao.getById(entityA.get__identifier()).get().getStringA());
    }

    @Test
    void testManualTransactionManagementCommit() throws SystemException, NotSupportedException {
        TransactionStatus transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
        assertEquals(Optional.of("TEST-A"), entityADao.getById(entityA.get__identifier()).get().getStringA());
        entityA.setStringA("BLAAA");
        entityADao.update(entityA);
        transactionManager.commit(transactionStatus);
        assertEquals(Optional.of("BLAAA"), entityADao.getById(entityA.get__identifier()).get().getStringA());
    }

}
