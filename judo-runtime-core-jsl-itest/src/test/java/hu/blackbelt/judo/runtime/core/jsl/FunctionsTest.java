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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    CollectionFunctions.CollectionFunctionsDao collectionFunctionsDao;

    @Inject
    Parent.ParentDao parentDao;

    @Inject
    Child.ChildDao childDao;
    
    @Inject
    Kleene.KleeneDao kleeneDao;

    @Inject
    Booler.BoolerDao boolerDao;

    @Inject
    BoolerTester.BoolerTesterDao boolerTesterDao;

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
        assertEquals(Optional.of(true), anyTypeFunctions.getIntegerIsUndefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getIntegerIsUndefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getScaledIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getScaledIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getScaledIsUndefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getScaledIsUndefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getStringIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getStringIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getStringIsUndefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getStringIsUndefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getBoolIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getBoolIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getBoolIsUndefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getBoolIsUndefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getDateIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getDateIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getDateIsUndefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getDateIsUndefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getTimestampIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getTimestampIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getTimestampIsUndefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getTimestampIsUndefinedFalse());

        assertEquals(Optional.of(false), anyTypeFunctions.getTimeIsDefinedFalse());
        assertEquals(Optional.of(true), anyTypeFunctions.getTimeIsDefinedTrue());
        assertEquals(Optional.of(true), anyTypeFunctions.getTimeIsUndefinedTrue());
        assertEquals(Optional.of(false), anyTypeFunctions.getTimeIsUndefinedFalse());
    }

    @Test
    public void testStrings() {
        StringFunctions str = stringFunctionsDao.create(StringFunctions.builder().build());

        assertEquals(5, str.getLength().orElseThrow());
        String first = str.getFirst().orElseThrow();
        assertEquals("ap", first);
        String last = str.getLast().orElseThrow();
        assertEquals("e", last);
        Long position = str.getPosition().orElseThrow();
        assertEquals(2, position);
        String substring = str.getSubstring().orElseThrow();
        assertEquals("ppl", substring);
        String lower = str.getLower().orElseThrow();
        assertEquals("apple", lower);
        String lowerCase = str.getLowerCase().orElseThrow();
        assertEquals("apple", lowerCase);
        String upper = str.getUpper().orElseThrow();
        assertEquals("APPLE", upper);
        String upperCase = str.getUpperCase().orElseThrow();
        assertEquals("APPLE", upperCase);
        String capitalize = str.getCapitalize().orElseThrow();
        assertEquals("Apple", capitalize);
        Boolean matches = str.getMatches().orElseThrow();
        assertTrue(matches);
        Boolean like = str.getLike().orElseThrow();
        assertTrue(like);
        // var ilike = str.getIlike().orElseThrow();
        String replace = str.getReplace().orElseThrow();
        assertEquals("appendix", replace);
        String trim = str.getTrim().orElseThrow();
        assertEquals("apple", trim);
        String ltrim = str.getLtrim().orElseThrow();
        assertEquals("apple ", ltrim);
        String rtrim = str.getRtrim().orElseThrow();
        assertEquals(" apple", rtrim);

        assertEquals("     apple", str.getLpad().orElseThrow());
        assertTrue(str.getLpadTrue().orElseThrow());
        assertFalse(str.getLpadFalse().orElseThrow());
        assertEquals("*****apple", str.getLpad1().orElseThrow());
        assertEquals("le", str.getLpad2().orElseThrow());
        assertEquals("le", str.getLpad3().orElseThrow());
        assertEquals("apple     ", str.getRpad().orElseThrow());
        assertEquals("apple*****", str.getRpad1().orElseThrow());
        assertEquals("ap", str.getRpad2().orElseThrow());
        assertEquals("ap", str.getRpad3().orElseThrow());

        assertTrue(str.getLpadUndefined().isEmpty());
        assertFalse(str.getLpadUndefinedFalse().orElseThrow());
        assertTrue(str.getLpadUndefinedTrue().orElseThrow());
        assertTrue(str.getLpadUndefined1().isEmpty());
        assertTrue(str.getRpadUndefined().isEmpty());
        assertTrue(str.getRpadUndefined1().isEmpty());

        String parenthizedAsString = str.getParenthizedAsString().orElseThrow();
        assertEquals("26", parenthizedAsString);
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

        assertEquals(1.0, numericFunctions.getRoundIntScaled().orElseThrow());
        assertEquals(1.0, numericFunctions.getRoundInt2Scaled().orElseThrow());
        assertEquals(1.2, numericFunctions.getRoundScaled1Scaled().orElseThrow());
        assertEquals(7.9, numericFunctions.getRoundScaled2Scaled().orElseThrow());
        assertEquals(2.5, numericFunctions.getRoundScaled3Scaled().orElseThrow());
        assertEquals(-2.5, numericFunctions.getRoundScaledNegative1Scaled().orElseThrow());
        assertEquals(-1.2, numericFunctions.getRoundScaledNegative2Scaled().orElseThrow());
        assertEquals(-7.9, numericFunctions.getRoundScaledNegative3Scaled().orElseThrow());

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
        assertEquals(Optional.of("TRUE"), bool.getLogicalExpressionAsString());
    }
    
    @Test
    public void testKleene() {
    	Kleene kleene = kleeneDao.create(Kleene.builder().build());
    	
    	/*
    	|=============================================================================================
    	|`p`            |`q`            |`p or q`       |`p and q`      |`p xor q`      |`p implies q`
    	|`true`	        |`true`	        |`true` 	    |`true`	        |`false`        |`true`
    	|`true`	        |`false`	    |`true`         |`false`	    |`true`         |`false`
    	|`true`	        |`undefined`	|`true`	        |`undefined`	|`undefined`    |`undefined`
    	|`false`	    |`true`	        |`true`	        |`false`	    |`true`         |`true`
    	|`false`	    |`false`	    |`false`	    |`false`	    |`false`        |`true`
    	|`false`	    |`undefined`	|`undefined`	|`false`	    |`undefined`    |`true`
    	|`undefined`	|`true`	        |`true`	        |`undefined`	|`undefined`    |`true`
    	|`undefined`	|`false`	    |`undefined`	|`false`	    |`undefined`    |`undefined`
    	|`undefined`	|`undefined`	|`undefined`	|`undefined`	|`undefined`    |`undefined`
    	|=============================================================================================
    		*/
    	
	   	assertEquals(Optional.of(true), kleene.getTrueOrTrue());
	   	assertEquals(Optional.of(true), kleene.getTrueOrFalse());
	   	assertEquals(Optional.of(true), kleene.getTrueOrUndefined());
	   	assertEquals(Optional.of(true), kleene.getFalseOrTrue());
	   	assertEquals(Optional.of(false), kleene.getFalseOrFalse());
	   	assertEquals(Optional.empty(), kleene.getFalseOrUndefined());
	   	assertEquals(Optional.of(true), kleene.getUndefinedOrTrue());
	   	assertEquals(Optional.empty(), kleene.getUndefinedOrFalse());
	   	assertEquals(Optional.empty(), kleene.getUndefinedOrUndefined());
	
	   	assertEquals(Optional.of(true), kleene.getTrueAndTrue());
	   	assertEquals(Optional.of(false), kleene.getTrueAndFalse());
	   	assertEquals(Optional.empty(), kleene.getTrueAndUndefined());
	   	assertEquals(Optional.of(false), kleene.getFalseAndTrue());
	   	assertEquals(Optional.of(false), kleene.getFalseAndFalse());
	   	assertEquals(Optional.of(false), kleene.getFalseAndUndefined());
	   	assertEquals(Optional.empty(), kleene.getUndefinedAndTrue());
	   	assertEquals(Optional.of(false), kleene.getUndefinedAndFalse());
	   	assertEquals(Optional.empty(), kleene.getUndefinedAndUndefined());
	
	   	assertEquals(Optional.of(false), kleene.getTrueXorTrue());
	   	assertEquals(Optional.of(true), kleene.getTrueXorFalse());
	   	assertEquals(Optional.empty(), kleene.getTrueXorUndefined());
	   	assertEquals(Optional.of(true), kleene.getFalseXorTrue());
	   	assertEquals(Optional.of(false), kleene.getFalseXorFalse());
	   	assertEquals(Optional.empty(), kleene.getFalseXorUndefined());
	   	assertEquals(Optional.empty(), kleene.getUndefinedXorTrue());
	   	assertEquals(Optional.empty(), kleene.getUndefinedXorFalse());
	   	assertEquals(Optional.empty(), kleene.getUndefinedXorUndefined());
	   	 
	   	assertEquals(Optional.of(true), kleene.getTrueImpliesTrue());
	   	assertEquals(Optional.of(false), kleene.getTrueImpliesFalse());
	   	assertEquals(Optional.empty(), kleene.getTrueImpliesUndefined());
	   	assertEquals(Optional.of(true), kleene.getFalseImpliesTrue());
	   	assertEquals(Optional.of(true), kleene.getFalseImpliesFalse());
	   	assertEquals(Optional.of(true), kleene.getFalseImpliesUndefined());
	   	assertEquals(Optional.of(true), kleene.getUndefinedImpliesTrue());
	   	assertEquals(Optional.empty(), kleene.getUndefinedImpliesFalse());
	   	assertEquals(Optional.empty(), kleene.getUndefinedImpliesUndefined());
    }

    @Test
    public void testDates() {
        DateFunctions date = dateFunctionsDao.create(DateFunctions.builder().build());

        assertEquals(Optional.of("2022-07-11"), date.getOwnDateAsString());
        assertEquals(Optional.of("2021-03-02"), date.getDateAsString());
        assertEquals(Optional.of(2021L), date.getYear());
        assertEquals(Optional.of(3L), date.getMonth());
        assertEquals(Optional.of(2L), date.getDay());

        assertEquals(LocalDate.of(2011, 1, 28), date.getOf().orElseThrow());

        assertEquals(280L, date.getDayOfYear().orElseThrow());
        assertEquals(1L, date.getDayOfWeekMonday().orElseThrow());
        assertEquals(2L, date.getDayOfWeekTuesday().orElseThrow());
        assertEquals(3L, date.getDayOfWeekWednesday().orElseThrow());
        assertEquals(4L, date.getDayOfWeekThursday().orElseThrow());
        assertEquals(5L, date.getDayOfWeekFriday().orElseThrow());
        assertEquals(6L, date.getDayOfWeekSaturday().orElseThrow());
        assertEquals(7L, date.getDayOfWeekSunday().orElseThrow());

        assertTrue(date.getDayOfYearUndefined().isEmpty());
        assertTrue(date.getDayOfWeekUndefined().isEmpty());
    }

    @Test
    public void testTime() {
        TimeFunctions time = timeFunctionsDao.create(TimeFunctions.builder().build());

        // assertEquals(Optional.of("23:15:59"), time.getOwnTimeAsString()); FIXME: JNG-3900
        // assertEquals(Optional.of("23:15:59"), time.getTimeAsString()); FIXME: JNG-3900
        assertEquals(Optional.of(23L), time.getHour());
        assertEquals(Optional.of(15L), time.getMinute());
        assertEquals(Optional.of(59L), time.getSecond());
        assertEquals(LocalTime.of(13, 45, 0), time.getOf().orElseThrow());

        assertEquals(LocalTime.of(11, 11, 11), time.getTimeFromSeconds().orElseThrow());
        assertEquals(40271L, time.getTimeAsSeconds().orElseThrow());
        assertEquals(40271L, time.getTimeAsSeconds1().orElseThrow());

        assertTrue(time.getUndefinedFromSeconds().isEmpty());
        assertTrue(time.getUndefinedAsSeconds().isEmpty());
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

    @Test
    public void testCollection() {
        CollectionFunctions collectionFunctions = collectionFunctionsDao.create(CollectionFunctions.builder()
                .withParentsField(
                        List.of(
                                Parent.builder().withName("Person").build(),
                                Parent.builder().withName("Another Person").build()
                        )
                )
                .withParentsRelation(
                        List.of(
                                Parent.builder().withName("Someone Else").build()
                        )
                )
                .withChildrenField(
                        List.of(
                                Child.builder().withName("Cindy").withAge(23L).build(),
                                Child.builder().withName("Rebecca").withAge(46L).build()
                        )
                )
                .withChildrenRelation(
                        List.of(
                                Child.builder().withName("Mark").withAge(33L).build(),
                                Child.builder().withName("John").build()
                        )
                )
                .build());

        // TODO: JNG-3911, JNG-4162: add missing test types

        assertEquals(2, collectionFunctions.getSizeParentsField().get());
        assertEquals(1, collectionFunctions.getSizeParentsRelation().get());

        // FIXME: JNG-4172 add tests
        // FIXME: JNG-4176 add tests

        assertEquals(Optional.of(23L), collectionFunctions.getMinChildrenField());
        assertEquals(Optional.of(33L), collectionFunctions.getMinChildrenRelation());

        assertEquals(Optional.of(46L), collectionFunctions.getMaxChildrenField());
        assertEquals(Optional.of(33L), collectionFunctions.getMaxChildrenRelation());

        assertEquals(Optional.of(69L), collectionFunctions.getSumChildrenField());
        assertEquals(Optional.of(33L), collectionFunctions.getSumChildrenRelation());

        assertEquals(Optional.of(34L), collectionFunctions.getAvgChildrenField());
        assertEquals(Optional.of(33L), collectionFunctions.getAvgChildrenRelation());
        assertEquals(Optional.of(34.5), collectionFunctions.getAvgScaledChildrenField());
        assertEquals(Optional.of(33.5), collectionFunctions.getAvgScaledChildrenRelation());
        assertEquals(Optional.of(34L), collectionFunctions.getDivisionConst());
        assertEquals(Optional.of(35L), collectionFunctions.getRoundConst());
    }

    @Test
    public void testBooleanAggregatorFunctions() {
        /*

        |--------------------------------------------|
        | T || 3 | 2 | 2 | 1 | 1 | 1 | 0 | 0 | 0 | 0 |
        | F || 0 | 1 | 0 | 2 | 1 | 0 | 3 | 2 | 1 | 0 |
        | U || 0 | 0 | 1 | 0 | 1 | 2 | 0 | 1 | 2 | 3 |
        |--------------------------------------------|

        |------------------------------------------------------|
        | A | B | C || anyTrue | allTrue | anyFalse | allFalse |
        | - | - | - ||    F    |    T    |    F     |    T     |
        | U | - | - ||    F    |    F    |    F     |    F     |
        | T | - | - ||    T    |    T    |    F     |    F     |
        | F | - | - ||    F    |    F    |    T     |    T     |
        | T | T | T ||    T    |    T    |    F     |    F     |
        | T | T | F ||    T    |    F    |    T     |    F     |
        | T | T | U ||    T    |    F    |    F     |    F     |
        | T | F | F ||    T    |    F    |    T     |    F     |
        | T | F | U ||    T    |    F    |    T     |    F     |
        | T | U | U ||    T    |    F    |    F     |    F     |
        | F | F | F ||    F    |    F    |    T     |    T     |
        | F | F | U ||    F    |    F    |    T     |    F     |
        | F | U | U ||    F    |    F    |    T     |    F     |
        | U | U | U ||    F    |    F    |    F     |    F     |
        |------------------------------------------------------|

        */

        boolerTesterDao.create(BoolerTester.builder().build());

        // | - | - | - ||    F    |    T    |    F     |    T     |
        assertBoolers(false, true, false, true);

        // | U | - | - ||    F    |    F    |    F     |    F     |
        Booler a = createBooler(null);
        assertBoolers(false, false, false, false);

        // | T | - | - ||    T    |    T    |    F     |    F     |
        a = updateBooler(a, true);
        assertBoolers(true, true, false, false);

        // | F | - | - ||    F    |    F    |    T     |    T     |
        a = updateBooler(a, false);
        assertBoolers(false, false, true, true);

        // | T | T | T ||    T    |    T    |    F     |    F     |
        a = updateBooler(a, true);
        Booler b = createBooler(true);
        Booler c = createBooler(true);
        assertBoolers(true, true, false, false);

        // | T | T | F ||    T    |    F    |    T     |    F     |
        c = updateBooler(c, false);
        assertBoolers(true, false, true, false);

        // | T | T | U ||    T    |    F    |    F     |    F     |
        c = updateBooler(c, null);
        assertBoolers(true, false, false, false);

        // | T | F | F ||    T    |    F    |    T     |    F     |
        b = updateBooler(b, false);
        c = updateBooler(c, false);
        assertBoolers(true, false, true, false);

        // | T | F | U ||    T    |    F    |    T     |    F     |
        c = updateBooler(c, null);
        assertBoolers(true, false, true, false);

        // | T | U | U ||    T    |    F    |    F     |    F     |
        b = updateBooler(b, null);
        assertBoolers(true, false, false, false);

        // | F | F | F ||    F    |    F    |    T     |    T     |
        a = updateBooler(a, false);
        b = updateBooler(b, false);
        c = updateBooler(c, false);
        assertBoolers(false, false, true, true);

        // | F | F | U ||    F    |    F    |    T     |    F     |
        c = updateBooler(c, null);
        assertBoolers(false, false, true, false);

        // | F | U | U ||    F    |    F    |    T     |    F     |
        b = updateBooler(b, null);
        assertBoolers(false, false, true, false);

        // | U | U | U ||    F    |    F    |    F     |    F     |
        a = updateBooler(a, null);
        assertBoolers(false, false, false, false);
    }

    private void assertBoolers(Boolean anyTrue, Boolean allTrue, Boolean anyFalse, Boolean allFalse) {
        Optional<BoolerTester> testerOptional = boolerTesterDao.query().execute().stream().findAny();
        assertTrue(testerOptional.isPresent());
        BoolerTester tester = testerOptional.get();
        Boolean anyTrueGot = tester.getAnyTrue().orElseThrow();
        assertEquals(anyTrue, anyTrueGot, String.format("anyTrue should be %s, but is %s", anyTrue, anyTrueGot));
        Boolean allTrueGot =  tester.getAllTrue().orElseThrow();
        assertEquals(allTrue, allTrueGot, String.format("allTrue should be %s, but is %s", allTrue, allTrueGot));
        Boolean anyFalseGot =  tester.getAnyFalse().orElseThrow();
        assertEquals(anyFalse, anyFalseGot, String.format("anyFalse should be %s, but is %s", anyFalse, anyFalseGot));
        Boolean allFalseGot = tester.getAllFalse().orElseThrow();
        assertEquals(allFalse, allFalseGot, String.format("allFalse should be %s, but is %s", allFalse, allFalseGot));
    }

    private Booler createBooler(Boolean b) {
        return boolerDao.create(Booler.builder().withB(b).build());
    }

    private Booler updateBooler(Booler booler, Boolean b) {
        booler.setB(b);
        return boolerDao.update(booler);
    }

}
