package hu.blackbelt.judo.runtime.core.jsl.transfer;

/*-
 * #%L
 * JUDO Runtime Core :: JUDO Language Specification DSL Integration Tests
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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optimisticlock.optimisticlock.transferperson.TransferPerson;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optimisticlock.optimisticlock.transferperson.TransferPersonDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optimisticlock.optimisticlock.transferperson.TransferPersonForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optimisticlock.optimisticlock.transferstudent.TransferStudent;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optimisticlock.optimisticlock.transferstudent.TransferStudentDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optimisticlock.optimisticlock.transferstudent.TransferStudentForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.OptimisticLockDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoRuntimeExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
public class MappedTransferOptimisticLockTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("OptimisticLock", new OptimisticLockDaoModules());

    @Inject
    TransferPersonDao transferTransferPersonDao;

    @Inject
    TransferStudentDao transferStudentDao;

    @Test
    @TestCase("SimpleOptimisticLockOnTransfer")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-SRV-002",
            "REQ-SRV-005"
    })
    void testSimpleOptimisticLockOnTransfer() {

        final LocalDateTime ts1 = LocalDateTime.now().truncatedTo(ChronoUnit.MICROS);
        TransferPerson p1 = transferTransferPersonDao.create(TransferPersonForCreate.builder().withName("Gipsz Jakab").build());
        final LocalDateTime ts2 = LocalDateTime.now();
        final LocalDateTime createdTs1 = getCreatedTimestamp(p1);
        assertEquals(1, getVersion(p1));
        assertTrue((ts1.isBefore(createdTs1) || ts1.isEqual(createdTs1)) && (ts2.isAfter(createdTs1) || ts2.isEqual(createdTs1)));

        p1.setName("Teszt Elek");
        TransferPerson p2 = transferTransferPersonDao.update(p1);
        final LocalDateTime ts3 = LocalDateTime.now();
        final LocalDateTime updatedTs2 = getUpdateTimestamp(p2);
        assertEquals(2, getVersion(p2));
        assertEquals("Teszt Elek", p2.getName().orElseThrow());
        assertTrue((ts2.isBefore(updatedTs2) || ts2.isEqual(updatedTs2)) && (ts3.isAfter(updatedTs2) || ts3.isEqual(updatedTs2)));

        p2.setName("Gipsz Jakab");
        TransferPerson p3 = transferTransferPersonDao.update(p2);
        final LocalDateTime ts4 = LocalDateTime.now();
        final LocalDateTime updatedTs3 = getUpdateTimestamp(p3);
        assertEquals(3, getVersion(p3));
        assertEquals("Gipsz Jakab", p3.getName().orElseThrow());
        assertTrue((ts3.isBefore(updatedTs3) || ts3.isEqual(updatedTs3)) && (ts4.isAfter(updatedTs3) || ts4.isEqual(updatedTs3)));

        // invalid version
        TransferPerson p4 = TransferPerson.from(Map.of(
                "__identifier", p3.identifier().getIdentifier(),
                "__entityType", p3.identifier().getEntityType(),
                "__version", 2)
        );
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> transferTransferPersonDao.update(p4)
        );
        assertTrue(thrown.getMessage().contains("Outdated instance to update"));

    }

    @Test
    @TestCase("InheritanceOptimisticLockOnTransfer")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-012",
            "REQ-SRV-002",
            "REQ-SRV-005"
    })
    void testInheritanceOptimisticLockOnTransfer() {

        TransferStudent p1 = transferStudentDao.create(TransferStudentForCreate.builder().withName("Gipsz Jakab").build());
        assertEquals(1, getVersion(p1));

        TransferStudent p2 = transferStudentDao.create(TransferStudentForCreate.builder().withName("Teszt Elek").withSection("9/C").build());
        assertEquals(1, getVersion(p2));

        final LocalDateTime ts1 = LocalDateTime.now();

        p1.setName("Jakab Gipsz");
        transferStudentDao.update(p1);

        p2.setName("Elek Teszt");
        transferTransferPersonDao.update(p2.adaptTo(TransferPerson.class));

        final LocalDateTime ts2 = LocalDateTime.now();

        TransferStudent p1UpdatedAsTransferStudent = transferStudentDao.getById(p1.identifier()).orElseThrow();
        TransferPerson p1UpdatedAsTransferPerson = transferTransferPersonDao.getById((UUID) p1.identifier().getIdentifier()).orElseThrow();
        final LocalDateTime p1UpdatedAsTransferStudentTs = getUpdateTimestamp(p1UpdatedAsTransferStudent);
        final LocalDateTime p1UpdatedAsTransferPersonTs = getUpdateTimestamp(p1UpdatedAsTransferPerson);

        assertEquals(2, getVersion(p1UpdatedAsTransferStudent));
        assertEquals(2, getVersion(p1UpdatedAsTransferPerson));
        assertTrue((p1UpdatedAsTransferStudentTs.isBefore(ts2) || p1UpdatedAsTransferStudentTs.isEqual(ts2)) && (p1UpdatedAsTransferStudentTs.isAfter(ts1) || p1UpdatedAsTransferStudentTs.isEqual(ts1)));
        assertTrue((p1UpdatedAsTransferPersonTs.isBefore(ts2) || p1UpdatedAsTransferPersonTs.isEqual(ts2)) && (p1UpdatedAsTransferPersonTs.isAfter(ts1) || p1UpdatedAsTransferPersonTs.isEqual(ts1)));


        TransferStudent p2UpdatedAsTransferStudent = transferStudentDao.getById(p2.identifier()).orElseThrow();
        TransferPerson p2UpdatedAsTransferPerson = transferTransferPersonDao.getById((UUID) p2.identifier().getIdentifier()).orElseThrow();
        final LocalDateTime p2UpdatedAsTransferStudentTs = getUpdateTimestamp(p2UpdatedAsTransferStudent);
        final LocalDateTime p2UpdatedAsTransferPersonTs = getUpdateTimestamp(p2UpdatedAsTransferPerson);

        assertEquals(2, getVersion(p1UpdatedAsTransferStudent));
        assertEquals(2, getVersion(p1UpdatedAsTransferPerson));
        assertTrue((p2UpdatedAsTransferStudentTs.isBefore(ts2) || p2UpdatedAsTransferStudentTs.isEqual(ts2)) && (p2UpdatedAsTransferStudentTs.isAfter(ts1) || p2UpdatedAsTransferStudentTs.isEqual(ts1)));
        assertTrue((p2UpdatedAsTransferPersonTs.isBefore(ts2) || p2UpdatedAsTransferPersonTs.isEqual(ts2)) && (p2UpdatedAsTransferPersonTs.isAfter(ts1) || p2UpdatedAsTransferPersonTs.isEqual(ts1)));

    }

    public Integer getVersion(TransferPerson person) {
        return (Integer) person.toMap().get("__version");
    }

    public LocalDateTime getCreatedTimestamp(TransferPerson person) {
        return (LocalDateTime)person.toMap().get("__createTimestamp");
    }

    public LocalDateTime getUpdateTimestamp(TransferPerson person) {
        return (LocalDateTime)person.toMap().get("__updateTimestamp");
    }

    public Integer getVersion(TransferStudent student) {
        return (Integer) student.toMap().get("__version");
    }

    public LocalDateTime getUpdateTimestamp(TransferStudent student) {
        return (LocalDateTime)student.toMap().get("__updateTimestamp");
    }

}
