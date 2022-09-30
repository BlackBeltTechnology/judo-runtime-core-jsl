package hu.blackbelt.judo.runtime.core.jsl;

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
import com.google.inject.Module;
import hu.blackbelt.judo.runtime.core.jsl.itest.functions.guice.functions.FunctionsDaoModules;
import hu.blackbelt.judo.runtime.core.jsl.itest.functions.sdk.functions.functions.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class FunctionsTest extends AbstractJslTest {
    @Inject
    Entity.EntityDao entityDao;

    @Inject
    EntityWithPrimitiveDefaults.EntityWithPrimitiveDefaultsDao entityWithPrimitiveDefaultsDao;

    @Inject
    AnyTypeFunctions.AnyTypeFunctionsDao anyTypeFunctionsDao;

    AnyTypeFunctions anyTypeFunctions;

    @Inject
    StringFunctions.StringFunctionsDao stringFunctionsDao;

    @Inject
    NumericFunctions.NumericFunctionsDao numericFunctionsDao;

    @Inject
    BooleanFunctions.BooleanFunctionsDao booleanFunctionsDao;

    @Inject
    DateFunctions.DateFunctionsDao dateFunctionsDao;

    @Inject
    TimeFunctions.TimeFunctionsDao timeFunctionsDao;

    @Inject
    TimestampFunctions.TimestampFunctionsDao timestampFunctionsDao;

    @Inject
    EnumFunctions.EnumFunctionsDao enumFunctionsDao;

    @Inject
    InstanceFunctions.InstanceFunctionsDao instanceFunctionsDao;

    @Inject
    Parent.ParentDao parentDao;

    @Inject
    Child.ChildDao childDao;

    @BeforeEach
    protected void init() throws Exception {
        super.init();
        Entity entity = entityDao
                        .create(Entity.builder().build());
        EntityWithPrimitiveDefaults entityWithPrimitiveDefaults = entityWithPrimitiveDefaultsDao
                        .create(EntityWithPrimitiveDefaults.builder().build());

        anyTypeFunctions = anyTypeFunctionsDao.create(AnyTypeFunctions.builder()
                        .withEntity(entity)
                        .withEntityWithPrimitives(entityWithPrimitiveDefaults)
                        .build());
    }

    @Override
    public Module getModelDaoModule() {
        return  new FunctionsDaoModules();
    }

    @Override
    public String getModelName() {
        return "Functions";
    }

    @Test
    public void testAnyType() {
        assertEquals(Optional.of(false), anyTypeFunctions.getIntegerIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getIntegerIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getIntegerIsUnDefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getIntegerIsUnDefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getScaledIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getScaledIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getScaledIsUnDefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getScaledIsUnDefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getStringIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getStringIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getStringIsUnDefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getStringIsUnDefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getBoolIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getBoolIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getBoolIsUnDefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getBoolIsUnDefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getDateIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getDateIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getDateIsUnDefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getDateIsUnDefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getTimestampIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getTimestampIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getTimestampIsUnDefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getTimestampIsUnDefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getTimeIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getTimeIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getTimeIsUnDefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getTimeIsUnDefinedFalse());
    }

    @Test
    public void testStrings() {
        StringFunctions str = stringFunctionsDao.create(StringFunctions.builder().build());

        // FIXME: JNG-4080 
        /*
        assertEquals(Optional.of(5), str.getLength());
        assertEquals(Optional.of("ap"), str.getFirst());
        assertEquals(Optional.of("e"), str.getLast());
        //assertEquals(Optional.of(0), str.getPosition());
        assertEquals(Optional.of("ppl"), str.getSubstring());
        assertEquals(Optional.of("apple"), str.getLower());
        assertEquals(Optional.of("apple"), str.getLowerCase());
        assertEquals(Optional.of("APPLE"), str.getUpper());
        assertEquals(Optional.of("APPLE"), str.getUpperCase());
        //assertEquals(Optional.of("Apple"), str.getCapitalize());
        assertEquals(Optional.of(true), str.getMatches());
        assertEquals(Optional.of(true), str.getLike());
        //assertEquals(Optional.of(true), str.getILike());
        assertEquals(Optional.of("appendix"), str.getReplace());        
        assertEquals(Optional.of("apple"), str.getTrim());
        //assertEquals(Optional.of("apple"), str.getLTrim());
        //assertEquals(Optional.of("apple"), str.getRTrim());
         * 
         */
    }

    @Test
    public void testNumerics() {
        NumericFunctions numericFunctions = numericFunctionsDao.create(NumericFunctions.builder().build());

        Long roundInt = numericFunctions.getRoundInt().orElseThrow();
        assertEquals(1, roundInt);
        Long roundInt2 = numericFunctions.getRoundInt2().orElseThrow();
        assertEquals(1, roundInt2);
        Long roundScaled1 = numericFunctions.getRoundScaled1().orElseThrow();
        assertEquals(1, roundScaled1);
        Long roundScaled2 = numericFunctions.getRoundScaled2().orElseThrow();
        assertEquals(8, roundScaled2);
        Long roundScaled3 = numericFunctions.getRoundScaled3().orElseThrow();
        assertEquals(3, roundScaled3);
        Long roundScaledNegative1 = numericFunctions.getRoundScaledNegative1().orElseThrow();
        assertEquals(-2, roundScaledNegative1);
        Long roundScaledNegative2 = numericFunctions.getRoundScaledNegative2().orElseThrow();
        assertEquals(-1, roundScaledNegative2);
        Long roundScaledNegative3 = numericFunctions.getRoundScaledNegative3().orElseThrow();
        assertEquals(-8, roundScaledNegative3);

        Long floorInt = numericFunctions.getFloorInt().orElseThrow();
        assertEquals(1, floorInt);
        Long floorScaled1 = numericFunctions.getFloorScaled1().orElseThrow();
        assertEquals(2, floorScaled1);
        Long floorScaled2 = numericFunctions.getFloorScaled2().orElseThrow();
        assertEquals(-3, floorScaled2);

        Long ceilInt = numericFunctions.getCeilInt().orElseThrow();
        assertEquals(1, ceilInt);
        Long ceilScaled1 = numericFunctions.getCeilScaled1().orElseThrow();
        assertEquals(3, ceilScaled1);
        Long ceilScaled2 = numericFunctions.getCeilScaled2().orElseThrow();
        assertEquals(-2, ceilScaled2);

        Long absInt = numericFunctions.getAbsInt().orElseThrow();
        assertEquals(1, absInt);
        Long absInt2 = numericFunctions.getAbsInt2().orElseThrow();
        assertEquals(3, absInt2);
        Double absScaled1 = numericFunctions.getAbsScaled1().orElseThrow();
        assertEquals(2.9, absScaled1);
        Double absScaled2 = numericFunctions.getAbsScaled2().orElseThrow();
        assertEquals(2.9, absScaled2);

        String intAsString = numericFunctions.getIntAsString().orElseThrow();
        assertEquals("1", intAsString);
        String scaledAsString = numericFunctions.getScaledAsString().orElseThrow();
        assertEquals("123456.789", scaledAsString);
    }

    @Test
    public void testBooleans() {
        BooleanFunctions bool = booleanFunctionsDao.create(BooleanFunctions.builder().build());

        assertEquals(Optional.of("TRUE"), bool.getOwnBoolAsString());
        assertEquals(Optional.of("TRUE"), bool.getTrueAsString());
        assertEquals(Optional.of("FALSE"), bool.getFalseAsString());
    }

    @Test
    public void testDates() {
        DateFunctions date = dateFunctionsDao.create(DateFunctions.builder().build());

        assertEquals(Optional.of("2022-07-11"), date.getOwnDateAsString());
        assertEquals(Optional.of("2021-03-02"), date.getDateAsString());
        assertEquals(Optional.of(2021L), date.getYear());
        assertEquals(Optional.of(3L), date.getMonth());
        assertEquals(Optional.of(2L), date.getDay());
        // TODO: add missing test after JNG-3899 is fixed
    }

    @Test
    public void testTime() {
        TimeFunctions time = timeFunctionsDao.create(TimeFunctions.builder().build());

        // assertEquals(Optional.of("23:15:59"), time.getOwnTimeAsString()); FIXME: JNG-3900
        // assertEquals(Optional.of("23:15:59"), time.getTimeAsString()); FIXME: JNG-3900
        assertEquals(Optional.of(23L), time.getHour());
        assertEquals(Optional.of(15L), time.getMinute());
        assertEquals(Optional.of(59L), time.getSecond());
        // TODO: add missing test after JNG-3901 is fixed
    }

    @Test
    public void testTimestamp() {
        // TODO: add tests after JNG-1586, JNG-3681
        TimestampFunctions timestampFunctions = timestampFunctionsDao.create(TimestampFunctions.builder().build());

        OffsetDateTime timestampOfDate = timestampFunctions.getTimestampOfDate().orElseThrow();
        OffsetDateTime timestampOfDateAndTime = timestampFunctions.getTimestampOfDateAndTime().orElseThrow();

        LocalDate dateOfTimestampWithDate = timestampFunctions.getDateOfTimestampWithDate().orElseThrow();
        LocalDate dateOfTimestampWithDateAndTime = timestampFunctions.getDateOfTimestampWithDateAndTime().orElseThrow();
        LocalTime timeOfTimestampWithDate = timestampFunctions.getTimeOfTimestampWithDate().orElseThrow();
        LocalTime timeOfTimestampWithDateAndTime = timestampFunctions.getTimeOfTimestampWithDateAndTime().orElseThrow();

        long asMilliseconds = timestampFunctions.getAsMilliseconds().orElseThrow();
        long asMilliseconds2 = timestampFunctions.getAsMilliseconds2().orElseThrow();

        OffsetDateTime fromMilliseconds = timestampFunctions.getFromMilliseconds().orElseThrow();

        OffsetDateTime plusAll = timestampFunctions.getPlusAll().orElseThrow();
        OffsetDateTime plusDate = timestampFunctions.getPlusDate().orElseThrow();

        OffsetDateTime plusAllReversed = timestampFunctions.getPlusAllReversed().orElseThrow();
        OffsetDateTime plusDateReversed = timestampFunctions.getPlusDateReversed().orElseThrow();

        OffsetDateTime plusMilliseconds = timestampFunctions.getPlusMilliseconds().orElseThrow();

        OffsetDateTime minusAll = timestampFunctions.getMinusAll().orElseThrow();
        OffsetDateTime minusDate = timestampFunctions.getMinusDate().orElseThrow();

        OffsetDateTime minusAllReversed = timestampFunctions.getMinusAllReversed().orElseThrow();
        OffsetDateTime minusDateReversed = timestampFunctions.getMinusDateReversed().orElseThrow();

        OffsetDateTime minusMilliseconds = timestampFunctions.getMinusMilliseconds().orElseThrow();
    }

    @Test
    public void testEnum() {
        EnumFunctions myEnum = enumFunctionsDao.create(EnumFunctions.builder().build());

        // assertEquals(Optional.of("Atomic"), myEnum.getOwnEnumAsString()); FIXME: JNG-3905
        // TODO: add missing test after JNG-3904 is fixed
    }

    @Test
    public void testInstance() {
        Parent parent1 = parentDao.create(Parent.builder().withName("James Webb").build());
        Child child1 = childDao.create(Child.builder().withName("Erika Young").withAge(11L).build());

        InstanceFunctions instanceFunctions = instanceFunctionsDao.create(InstanceFunctions.builder()
                        .withParent(Parent.builder().withName("Another Person").build())
                        .withChild(Child.builder().withName("Another Child").withAge(31L).build())
                        .build());
               
        assertTrue(instanceFunctions.getTypeOfParent().get());
        assertFalse(instanceFunctions.getKindOfChild().get());
        assertTrue(instanceFunctions.getKindOfParent().get());
        assertFalse(instanceFunctions.getNotTypeOfChild().get());

        
    }
}
