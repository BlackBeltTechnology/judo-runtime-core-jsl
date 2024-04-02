package hu.blackbelt.judo.runtime.core.jsl.entity;

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
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optimisticlock.optimisticlock.person.Person;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optimisticlock.optimisticlock.person.PersonDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optimisticlock.optimisticlock.person.PersonForCreate;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optimisticlock.optimisticlock.student.Student;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optimisticlock.optimisticlock.student.StudentDao;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.optimisticlock.optimisticlock.student.StudentForCreate;
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
public class OptimisticLockTest {

    @RegisterExtension
    static JudoRuntimeExtension runtimeExtension = new JudoRuntimeExtension("OptimisticLock", new OptimisticLockDaoModules());

    @Inject
    PersonDao personDao;

    @Inject
    StudentDao studentDao;

    @Test
    @TestCase("SimpleOptimisticLock")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001"
    })
    void testSimpleOptimisticLock() {

        final LocalDateTime ts1 = LocalDateTime.now().truncatedTo(ChronoUnit.MICROS);
        Person p1 = personDao.create(PersonForCreate.builder().withName("Gipsz Jakab").build());
        final LocalDateTime ts2 = LocalDateTime.now();
        final LocalDateTime createdTs1 = getCreatedTimestamp(p1);
        assertEquals(1, getVersion(p1));
        assertTrue((ts1.isBefore(createdTs1) || ts1.isEqual(createdTs1)) && (ts2.isAfter(createdTs1) || ts2.isEqual(createdTs1)));

        p1.setName("Teszt Elek");
        Person p2 = personDao.update(p1);
        final LocalDateTime ts3 = LocalDateTime.now();
        final LocalDateTime updatedTs2 = getUpdateTimestamp(p2);
        assertEquals(2, getVersion(p2));
        assertEquals("Teszt Elek", p2.getName().orElseThrow());
        assertTrue((ts2.isBefore(updatedTs2) || ts2.isEqual(updatedTs2)) && (ts3.isAfter(updatedTs2) || ts3.isEqual(updatedTs2)));

        p2.setName("Gipsz Jakab");
        Person p3 = personDao.update(p2);
        final LocalDateTime ts4 = LocalDateTime.now();
        final LocalDateTime updatedTs3 = getUpdateTimestamp(p3);
        assertEquals(3, getVersion(p3));
        assertEquals("Gipsz Jakab", p3.getName().orElseThrow());
        assertTrue((ts3.isBefore(updatedTs3) || ts3.isEqual(updatedTs3)) && (ts4.isAfter(updatedTs3) || ts4.isEqual(updatedTs3)));

        // invalid version
        Person p4 = Person.from(Map.of(
                        "__identifier", p3.identifier().getIdentifier(),
                        "__entityType", p3.identifier().getEntityType(),
                        "__version", 2)
                );
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> personDao.update(p4)
        );
        assertTrue(thrown.getMessage().contains("Outdated instance to update"));

    }

    @Test
    @TestCase("InheritanceOptimisticLock")
    @Requirement(reqs = {
            "REQ-MDL-001",
            "REQ-MDL-002",
            "REQ-MDL-003",
            "REQ-TYPE-001",
            "REQ-TYPE-004",
            "REQ-ENT-001",
            "REQ-ENT-012",
    })
    void testInheritanceOptimisticLock() {

        Student p1 = studentDao.create(StudentForCreate.builder().withName("Gipsz Jakab").build());
        assertEquals(1, getVersion(p1));

        Student p2 = studentDao.create(StudentForCreate.builder().withName("Teszt Elek").withSection("9/C").build());
        assertEquals(1, getVersion(p2));

        final LocalDateTime ts1 = LocalDateTime.now();

        p1.setName("Jakab Gipsz");
        studentDao.update(p1);

        p2.setName("Elek Teszt");
        personDao.update(p2.adaptTo(Person.class));

        final LocalDateTime ts2 = LocalDateTime.now();

        Student p1UpdatedAsStudent = studentDao.getById(p1.identifier()).orElseThrow();
        Person p1UpdatedAsPerson = personDao.getById((UUID) p1.identifier().getIdentifier()).orElseThrow();
        final LocalDateTime p1UpdatedAsStudentTs = getUpdateTimestamp(p1UpdatedAsStudent);
        final LocalDateTime p1UpdatedAsPersonTs = getUpdateTimestamp(p1UpdatedAsPerson);

        assertEquals(2, getVersion(p1UpdatedAsStudent));
        assertEquals(2, getVersion(p1UpdatedAsPerson));
        assertTrue((p1UpdatedAsStudentTs.isBefore(ts2) || p1UpdatedAsStudentTs.isEqual(ts2)) && (p1UpdatedAsStudentTs.isAfter(ts1) || p1UpdatedAsStudentTs.isEqual(ts1)));
        assertTrue((p1UpdatedAsPersonTs.isBefore(ts2) || p1UpdatedAsPersonTs.isEqual(ts2)) && (p1UpdatedAsPersonTs.isAfter(ts1) || p1UpdatedAsPersonTs.isEqual(ts1)));


        Student p2UpdatedAsStudent = studentDao.getById(p2.identifier()).orElseThrow();
        Person p2UpdatedAsPerson = personDao.getById((UUID) p2.identifier().getIdentifier()).orElseThrow();
        final LocalDateTime p2UpdatedAsStudentTs = getUpdateTimestamp(p2UpdatedAsStudent);
        final LocalDateTime p2UpdatedAsPersonTs = getUpdateTimestamp(p2UpdatedAsPerson);

        assertEquals(2, getVersion(p1UpdatedAsStudent));
        assertEquals(2, getVersion(p1UpdatedAsPerson));
        assertTrue((p2UpdatedAsStudentTs.isBefore(ts2) || p2UpdatedAsStudentTs.isEqual(ts2)) && (p2UpdatedAsStudentTs.isAfter(ts1) || p2UpdatedAsStudentTs.isEqual(ts1)));
        assertTrue((p2UpdatedAsPersonTs.isBefore(ts2) || p2UpdatedAsPersonTs.isEqual(ts2)) && (p2UpdatedAsPersonTs.isAfter(ts1) || p2UpdatedAsPersonTs.isEqual(ts1)));

    }

    public Integer getVersion(Person person) {
        return (Integer) person.toMap().get("__version");
    }

    public LocalDateTime getCreatedTimestamp(Person person) {
        return (LocalDateTime)person.toMap().get("__createTimestamp");
    }

    public LocalDateTime getUpdateTimestamp(Person person) {
        return (LocalDateTime)person.toMap().get("__updateTimestamp");
    }

    public Integer getVersion(Student student) {
        return (Integer) student.toMap().get("__version");
    }

    public LocalDateTime getUpdateTimestamp(Student student) {
        return (LocalDateTime)student.toMap().get("__updateTimestamp");
    }

}
