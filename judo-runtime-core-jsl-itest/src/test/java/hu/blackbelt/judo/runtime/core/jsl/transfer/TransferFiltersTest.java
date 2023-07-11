package hu.blackbelt.judo.runtime.core.jsl.transfer;

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
import hu.blackbelt.judo.dispatcher.api.FileType;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.transferoptionalprimitives.*;
import hu.blackbelt.judo.psm.generator.sdk.core.test.api.mappedtransferprimitives.mappedtransferprimitives.enum_.Enum;
import hu.blackbelt.judo.psm.generator.sdk.core.test.guice.MappedTransferPrimitivesDaoModules;
import hu.blackbelt.judo.requirement.report.annotation.Requirement;
import hu.blackbelt.judo.requirement.report.annotation.TestCase;
import hu.blackbelt.judo.runtime.core.jsl.AbstractJslTest;
import hu.blackbelt.judo.runtime.core.jsl.fixture.JudoDatasourceFixture;
import hu.blackbelt.judo.sdk.query.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class TransferFiltersTest extends AbstractJslTest {
    @Inject
    TransferOptionalPrimitivesDao transferOptionalPrimitivesDao;

    TransferOptionalPrimitives transf1;

    TransferOptionalPrimitives transf2;

    static final int INTEGER_1 = 1;
    static final int INTEGER_2 = 2;
    static final double SCALED_1 = 1.23;
    static final double SCALED_2 = 2.34;
    static final String STRING_1 = "test";
    static final String STRING_2 = "Another";
    static final String REGEX_1 = "+36 333-333-3333";
    static final String REGEX_2 = "+36 333-333-3334";
    static final boolean BOOL_1 = true;
    static final boolean BOOL_2 = false;
    static final LocalDate DATE_1 = LocalDate.of(2022, 7, 11);
    static final LocalDate DATE_2 = LocalDate.of(1999, 9, 19);
    static final LocalDateTime TIMESTAMP_1 = LocalDateTime.parse("2022-07-11T19:09:33");
    static final LocalDateTime TIMESTAMP_2 = LocalDateTime.parse("1999-09-19T09:09:09");
    static final LocalTime TIME_1 = LocalTime.parse("23:59:59");
    static final LocalTime TIME_2 = LocalTime.parse("12:34:56");
    static final Enum ENUM_1 = Enum.EnumB;
    static final Enum ENUM_2 = Enum.EnumA;

    @BeforeEach
    protected void init(JudoDatasourceFixture datasource) throws Exception {
        super.init(datasource);

        transf1 = transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                .withIntegerAttr(INTEGER_1)
                .withScaledAttr(SCALED_1)
                .withStringAttr(STRING_1)
                .withRegexAttr(REGEX_1)
                .withBoolAttr(BOOL_1)
                .withDateAttr(DATE_1)
                .withTimestampAttr(TIMESTAMP_1)
                .withTimeAttr(TIME_1)
                .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                .withEnumAttr(ENUM_1)
                .build());

        transf2 = transferOptionalPrimitivesDao.create(TransferOptionalPrimitives.builder()
                .withIntegerAttr(INTEGER_2)
                .withScaledAttr(SCALED_2)
                .withStringAttr(STRING_2)
                .withRegexAttr(REGEX_2)
                .withBoolAttr(BOOL_2)
                .withDateAttr(DATE_2)
                .withTimestampAttr(TIMESTAMP_2)
                .withTimeAttr(TIME_2)
                .withBinaryAttr(FileType.builder().fileName("test.txt").build())
                .withEnumAttr(ENUM_2)
                .build());
    }

    @Override
    public Module getModelDaoModule() {
        return new MappedTransferPrimitivesDaoModules();
    }

    @Override
    public String getModelName() {
        return "MappedTransferPrimitives";
    }

    @Test
    @TestCase("TransferFilterWithMultipleFilters")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-SVR-002"
    })
    public void testFilterWithMultipleFilters() {
        List<TransferOptionalPrimitives> list = transferOptionalPrimitivesDao.query().execute();

        assertEquals(2, list.size());

        List<TransferOptionalPrimitives> multiFilter = transferOptionalPrimitivesDao
                .query()
                .filterByIntegerAttr(NumberFilter.equalTo(INTEGER_1))
                .filterByScaledAttr(NumberFilter.lessThan(INTEGER_2))
                .filterByStringAttr(StringFilter.equalTo(STRING_1))
                .execute();

        assertEquals(1, multiFilter.size());
        assertEquals(transf1.identifier(), multiFilter.get(0).identifier());
    }

    @Test
    @TestCase("TransferIntegerNumberFilter")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-SVR-002"
    })
    public void testIntegerNumberFilter() {
        List<TransferOptionalPrimitives> list = transferOptionalPrimitivesDao.query().execute();

        assertEquals(2, list.size());

        TransferOptionalPrimitives filteredByString = transferOptionalPrimitivesDao
                .query()
                .filterBy("this.integerAttr >= 1 and this.integerAttr < 2")
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), filteredByString.identifier());

        TransferOptionalPrimitives equals = transferOptionalPrimitivesDao
                .query()
                .filterByIntegerAttr(NumberFilter.equalTo(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), equals.identifier());

        TransferOptionalPrimitives notEquals = transferOptionalPrimitivesDao
                .query()
                .filterByIntegerAttr(NumberFilter.notEqualTo(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(transf2.identifier(), notEquals.identifier());

        List<TransferOptionalPrimitives> greaterOrEquals = transferOptionalPrimitivesDao
                .query()
                .filterByIntegerAttr(NumberFilter.greaterOrEqualThan(INTEGER_1))
                .execute();

        assertEquals(2, greaterOrEquals.size());

        TransferOptionalPrimitives greater = transferOptionalPrimitivesDao
                .query()
                .filterByIntegerAttr(NumberFilter.greaterThan(INTEGER_1))
                .execute()
                .get(0);

        assertEquals(transf2.identifier(), greater.identifier());

        List<TransferOptionalPrimitives> lessOrEqual = transferOptionalPrimitivesDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessOrEqualThan(INTEGER_2))
                .execute();

        assertEquals(2, lessOrEqual.size());

        TransferOptionalPrimitives less = transferOptionalPrimitivesDao
                .query()
                .filterByIntegerAttr(NumberFilter.lessThan(INTEGER_2))
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), less.identifier());
    }

    @Test
    @TestCase("TransferScaledNumberFilter")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-SVR-002"
    })
    public void testScaledNumberFilter() {
        List<TransferOptionalPrimitives> list = transferOptionalPrimitivesDao.query().execute();

        assertEquals(2, list.size());

        TransferOptionalPrimitives filteredByString = transferOptionalPrimitivesDao
                .query()
                .filterBy("this.scaledAttr >= 1 and this.scaledAttr <= 2")
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), filteredByString.identifier());

        TransferOptionalPrimitives equals = transferOptionalPrimitivesDao
                .query()
                .filterByScaledAttr(NumberFilter.equalTo(SCALED_1))
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), equals.identifier());

        TransferOptionalPrimitives notEquals = transferOptionalPrimitivesDao
                .query()
                .filterByScaledAttr(NumberFilter.notEqualTo(SCALED_1))
                .execute()
                .get(0);

        assertEquals(transf2.identifier(), notEquals.identifier());

        List<TransferOptionalPrimitives> greaterOrEquals = transferOptionalPrimitivesDao
                .query()
                .filterByScaledAttr(NumberFilter.greaterOrEqualThan(SCALED_1))
                .execute();

        assertEquals(2, greaterOrEquals.size());

        TransferOptionalPrimitives greater = transferOptionalPrimitivesDao
                .query()
                .filterByScaledAttr(NumberFilter.greaterThan(SCALED_1))
                .execute()
                .get(0);

        assertEquals(transf2.identifier(), greater.identifier());

        List<TransferOptionalPrimitives> lessOrEqual = transferOptionalPrimitivesDao
                .query()
                .filterByScaledAttr(NumberFilter.lessOrEqualThan(SCALED_2))
                .execute();

        assertEquals(2, lessOrEqual.size());

        TransferOptionalPrimitives less = transferOptionalPrimitivesDao
                .query()
                .filterByScaledAttr(NumberFilter.lessThan(SCALED_2))
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), less.identifier());
    }


    @Test
    @TestCase("TransferStringFilter")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-SVR-002"
    })
    public void testStringFilter() {
        List<TransferOptionalPrimitives> list = transferOptionalPrimitivesDao.query().execute();

        assertEquals(2, list.size());

        TransferOptionalPrimitives filteredByString = transferOptionalPrimitivesDao
                .query()
                .filterBy("this.stringAttr!matches('test')")
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), filteredByString.identifier());

        TransferOptionalPrimitives equals = transferOptionalPrimitivesDao
                .query()
                .filterByStringAttr(StringFilter.equalTo(STRING_1))
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), equals.identifier());

        TransferOptionalPrimitives notEquals = transferOptionalPrimitivesDao
                .query()
                .filterByStringAttr(StringFilter.notEqualTo(STRING_1))
                .execute()
                .get(0);

        assertEquals(transf2.identifier(), notEquals.identifier());

        List<TransferOptionalPrimitives> iLike = transferOptionalPrimitivesDao
                .query()
                .filterByStringAttr(StringFilter.ilike(STRING_1.toUpperCase()))
                .execute();

        assertEquals(1, iLike.size());
        assertEquals(transf1.identifier(), iLike.get(0).identifier());

        List<TransferOptionalPrimitives> like = transferOptionalPrimitivesDao
                .query()
                .filterByStringAttr(StringFilter.like(STRING_1))
                .execute();

        assertEquals(1, like.size());
        assertEquals(transf1.identifier(), like.get(0).identifier());

        List<TransferOptionalPrimitives> lessThan = transferOptionalPrimitivesDao
                .query()
                .filterByStringAttr(StringFilter.lessThan(STRING_1))
                .execute();

        assertEquals(transf2.identifier(), lessThan.get(0).identifier());

        List<TransferOptionalPrimitives> greaterThan = transferOptionalPrimitivesDao
                .query()
                .filterByStringAttr(StringFilter.greaterThan(STRING_2))
                .execute();

        assertEquals(transf1.identifier(), greaterThan.get(0).identifier());

        List<TransferOptionalPrimitives> greaterOrEqualThan = transferOptionalPrimitivesDao
                .query()
                .filterByStringAttr(StringFilter.greaterOrEqualThan(STRING_2))
                .execute();

        assertEquals(2, greaterOrEqualThan.size());

        List<TransferOptionalPrimitives> lessOrEqualThan = transferOptionalPrimitivesDao
                .query()
                .filterByStringAttr(StringFilter.lessOrEqualThan(STRING_1))
                .execute();

        assertEquals(2, lessOrEqualThan.size());
    }

    @Test
    @TestCase("TransferBooleanFilter")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-SVR-002"
    })
    public void testBooleanFilter() {
        List<TransferOptionalPrimitives> list = transferOptionalPrimitivesDao.query().execute();

        assertEquals(2, list.size());

        TransferOptionalPrimitives filteredByString = transferOptionalPrimitivesDao
                .query()
                .filterBy("this.boolAttr == true")
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), filteredByString.identifier());

        TransferOptionalPrimitives isTrue = transferOptionalPrimitivesDao
                .query()
                .filterByBoolAttr(BooleanFilter.isTrue())
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), isTrue.identifier());

        TransferOptionalPrimitives isFalse = transferOptionalPrimitivesDao
                .query()
                .filterByBoolAttr(BooleanFilter.isFalse())
                .execute()
                .get(0);

        assertEquals(transf2.identifier(), isFalse.identifier());
    }

    @Test
    @TestCase("TransferDateFilter")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-SVR-002"
    })
    public void testDateFilter() {
        List<TransferOptionalPrimitives> list = transferOptionalPrimitivesDao.query().execute();

        assertEquals(2, list.size());

        TransferOptionalPrimitives equalTo = transferOptionalPrimitivesDao
                .query()
                .filterByDateAttr(DateFilter.equalTo(DATE_1))
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), equalTo.identifier());

        TransferOptionalPrimitives notEqualTo = transferOptionalPrimitivesDao
                .query()
                .filterByDateAttr(DateFilter.notEqualTo(DATE_1))
                .execute()
                .get(0);

        assertEquals(transf2.identifier(), notEqualTo.identifier());

        List<TransferOptionalPrimitives> greaterOrEqual = transferOptionalPrimitivesDao
                .query()
                .filterByDateAttr(DateFilter.greaterOrEqualThan(DATE_2))
                .execute();

        assertEquals(2, greaterOrEqual.size());

        List<TransferOptionalPrimitives> lessOrEqual = transferOptionalPrimitivesDao
                .query()
                .filterByDateAttr(DateFilter.lessOrEqualThan(DATE_1))
                .execute();

        assertEquals(2, lessOrEqual.size());

        List<TransferOptionalPrimitives> greaterThan = transferOptionalPrimitivesDao
                .query()
                .filterByDateAttr(DateFilter.greaterThan(DATE_2))
                .execute();

        assertEquals(1, greaterThan.size());

        List<TransferOptionalPrimitives> lessThan = transferOptionalPrimitivesDao
                .query()
                .filterByDateAttr(DateFilter.lessThan(DATE_1))
                .execute();

        assertEquals(1, lessThan.size());
    }


    @Test
    @TestCase("TransferTimestampFilter")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-SVR-002"
    })
    public void testTimestampFilter() {
        List<TransferOptionalPrimitives> list = transferOptionalPrimitivesDao.query().execute();

        assertEquals(2, list.size());

        TransferOptionalPrimitives equalTo = transferOptionalPrimitivesDao
                .query()
                .filterByTimestampAttr(TimestampFilter.equalTo(TIMESTAMP_1))
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), equalTo.identifier());

        TransferOptionalPrimitives notEqualTo = transferOptionalPrimitivesDao
                .query()
                .filterByTimestampAttr(TimestampFilter.notEqualTo(TIMESTAMP_1))
                .execute()
                .get(0);

        assertEquals(transf2.identifier(), notEqualTo.identifier());

        List<TransferOptionalPrimitives> greaterOrEqual = transferOptionalPrimitivesDao
                .query()
                .filterByTimestampAttr(TimestampFilter.greaterOrEqualThan(TIMESTAMP_2))
                .execute();

        assertEquals(2, greaterOrEqual.size());

        List<TransferOptionalPrimitives> lessOrEqual = transferOptionalPrimitivesDao
                .query()
                .filterByTimestampAttr(TimestampFilter.lessOrEqualThan(TIMESTAMP_1))
                .execute();

        assertEquals(2, lessOrEqual.size());

        List<TransferOptionalPrimitives> greaterThan = transferOptionalPrimitivesDao
                .query()
                .filterByTimestampAttr(TimestampFilter.greaterThan(TIMESTAMP_2))
                .execute();

        assertEquals(1, greaterThan.size());

        List<TransferOptionalPrimitives> lessThan = transferOptionalPrimitivesDao
                .query()
                .filterByTimestampAttr(TimestampFilter.lessThan(TIMESTAMP_1))
                .execute();

        assertEquals(1, lessThan.size());
    }

    @Test
    @TestCase("TransferTimeFilter")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-SVR-002"
    })
    public void testTimeFilter() {
        List<TransferOptionalPrimitives> list = transferOptionalPrimitivesDao.query().execute();

        assertEquals(2, list.size());

        TransferOptionalPrimitives equalTo = transferOptionalPrimitivesDao
                .query()
                .filterByTimeAttr(TimeFilter.equalTo(TIME_1))
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), equalTo.identifier());

        TransferOptionalPrimitives notEqualTo = transferOptionalPrimitivesDao
                .query()
                .filterByTimeAttr(TimeFilter.notEqualTo(TIME_1))
                .execute()
                .get(0);

        assertEquals(transf2.identifier(), notEqualTo.identifier());

        List<TransferOptionalPrimitives> greaterOrEqual = transferOptionalPrimitivesDao
                .query()
                .filterByTimeAttr(TimeFilter.greaterOrEqualThan(TIME_2))
                .execute();

        assertEquals(2, greaterOrEqual.size());

        List<TransferOptionalPrimitives> lessOrEqual = transferOptionalPrimitivesDao
                .query()
                .filterByTimeAttr(TimeFilter.lessOrEqualThan(TIME_1))
                .execute();

        assertEquals(2, lessOrEqual.size());

        List<TransferOptionalPrimitives> greaterThan = transferOptionalPrimitivesDao
                .query()
                .filterByTimeAttr(TimeFilter.greaterThan(TIME_2))
                .execute();

        assertEquals(1, greaterThan.size());

        List<TransferOptionalPrimitives> lessThan = transferOptionalPrimitivesDao
                .query()
                .filterByTimeAttr(TimeFilter.lessThan(TIME_1))
                .execute();

        assertEquals(1, lessThan.size());
    }

    @Test
    @TestCase("TransferEnumFilter")
    @Requirement(reqs = {
            "REQ-TYPE-001",
            "REQ-TYPE-002",
            "REQ-TYPE-004",
            "REQ-TYPE-005",
            "REQ-TYPE-006",
            "REQ-TYPE-007",
            "REQ-TYPE-008",
            "REQ-TYPE-009",
            "REQ-TYPE-010",
            "REQ-ENT-001",
            "REQ-ENT-002",
            "REQ-SVR-002"
    })
    public void testEnumFilter() {
        List<TransferOptionalPrimitives> list = transferOptionalPrimitivesDao.query().execute();

        assertEquals(2, list.size());

        TransferOptionalPrimitives equalTo = transferOptionalPrimitivesDao
                .query()
                .filterByEnumAttr(EnumerationFilter.equalTo(Enum.EnumB))
                .execute()
                .get(0);

        assertEquals(transf1.identifier(), equalTo.identifier());

        TransferOptionalPrimitives notEqualTo = transferOptionalPrimitivesDao
                .query()
                .filterByEnumAttr(EnumerationFilter.notEqualTo(Enum.EnumB))
                .execute()
                .get(0);

        assertEquals(transf2.identifier(), notEqualTo.identifier());
    }
}
