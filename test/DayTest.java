/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentplanner;

import appointmentplanner.util.Priority;
import appointmentplanner.util.Queue;
import appointmentplanner.util.Stack;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;

/**
 *
 * @author tani.d
 */
public class DayTest {
    TimeSpan ts1 = new TimeSpan(1, 0);
    Time time1 = new Time(9, 30);
    Time time2 = new Time(10, 00);
    Time time3 = new Time(14, 30);
    
    Appointment app1 = new Appointment("TestAppointment Nr1", ts1);
    Appointment app2 = new Appointment("TestAppointment Nr2.1", ts1);
    Appointment app3 = new Appointment("TestAppointment Nr3", ts1);
    
    Day testDay = new Day(1);

    
    @Before
    public void initialize() {
        
        app1.setStart(time1);
        app2.setStart(time2);
        app3.setStart(time3);
        
    }
    
    @Test
    public void testDay() {
        assertEquals(1, testDay.getNrOfAppointments());
    }

    /**
     * Test of getNrOfAppointments method, of class Day.
     */
    
    @Test
    public void testGetNrOfAppointments() {
        assertEquals(1, testDay.getNrOfAppointments());
    }

    /**
     * Test of getNameOfTheDay method, of class Day.
     */
    
    @Test
    public void testGetNameOfTheDay() {
        assertEquals("Monday", testDay.getNameOfTheDay());
        Day testDay2 = new Day(2);
        assertEquals("Tuesday", testDay2.getNameOfTheDay());
        Day testDay3 = new Day(3);
        assertEquals("Wednesday", testDay3.getNameOfTheDay());
        Day testDay4 = new Day(4);
        assertEquals("Thursday", testDay4.getNameOfTheDay());
        Day testDay5 = new Day(5);
        assertEquals("Friday", testDay5.getNameOfTheDay());
        Day testDay6 = new Day(6);
        assertEquals("Saturday", testDay6.getNameOfTheDay());
        Day testDay7 = new Day(7);
        assertEquals("Sunday", testDay7.getNameOfTheDay());
        
    }

    /**
     * Test of canAddAppointmentOfDuration method, of class Day.
     */
    
    @Test
    public void testCanAddAppointmentOfDuration() {
        Day tDay = new Day(2);
        assertEquals(1, tDay.getNrOfAppointments());
        Appointment aliB = new Appointment("Ali B", new TimeSpan(1,00));
        aliB.setStart(new Time(8,30));
        tDay.addAppointmentWithStartTimeSet(aliB);
        
        assertEquals(2, tDay.getNrOfAppointments());
        
        Appointment bert = new Appointment("bert", new TimeSpan(1,00));
        bert.setStart(new Time(10,30));
        tDay.addAppointmentWithStartTimeSet(bert);
        
        assertEquals(3, tDay.getNrOfAppointments());
        
        assertTrue(tDay.canAddAppointmentOfDuration(new TimeSpan(2,0)));
        assertFalse(tDay.canAddAppointmentOfDuration(new TimeSpan(22,0)));
        
        
        
        
        
        Day tDay2 = new Day(2);
        
        Appointment appBeforeLunch = new Appointment("Appointment before", new TimeSpan(1,00));
        appBeforeLunch.setStart(new Time(9,00));
        tDay2.addAppointmentWithStartTimeSet(appBeforeLunch);
        assertTrue(tDay2.canAddAppointmentOfDuration(new TimeSpan(2,00)));
        tDay2.removeAppointment("lunch break");
        tDay2.removeAppointment("Appointment before");   
        assertTrue(tDay2.getNrOfAppointments() == 0);
        assertTrue(tDay2.canAddAppointmentOfDuration(new TimeSpan(8, 59)));
        
        


    }

    /**
     * Test of addAppointmentWithStartTimeSet method, of class Day.
     */
    
    @Test
    public void testAddAppointmentWithStartTimeSet() {
       Day testDay1 = new Day(3);
       Appointment appWST1 = new Appointment("testAddAppointmentWithStartTimeSet 1", new TimeSpan(1,0));
        appWST1.setStart(new Time(13,31));
        testDay1.addAppointmentWithStartTimeSet(appWST1);
        
        Appointment appWST2 = new Appointment("testAddAppointmentWithStartTimeSet 2", new TimeSpan(2,0));
        appWST2.setStart(new Time(14,32));
        testDay1.addAppointmentWithStartTimeSet(appWST2);
        
        Appointment appWST3 = new Appointment("testAddAppointmentWithStartTimeSet 3", new TimeSpan(0,59));
        appWST3.setStart(new Time(10,30));
        testDay1.addAppointmentWithStartTimeSet(appWST3);
        
        Appointment appWST4 = new Appointment("testAddAppointmentWithStartTimeSet 4", new TimeSpan(1,3));
        appWST4.setStart(new Time(8,31));
        testDay1.addAppointmentWithStartTimeSet(appWST4);
              
        Appointment appWST5 = new Appointment("testAddAppointmentWithStartTimeSet 5", new TimeSpan(0,59));
        appWST5.setStart(new Time(8,30));
        testDay1.addAppointmentWithStartTimeSet(appWST5);
        
        TimeGap[] aTimeGaps = testDay1.getAvailableTimeGaps();
        for (int i = 0; i < aTimeGaps.length; i++) {
            System.out.println("AVAILIBLE TIMEGAPS: Start: " +aTimeGaps[i].getStart()+" End: " +aTimeGaps[i].getEnd() + " Lenght: " + aTimeGaps[i].getLength());
            
        }
        
        assertEquals(6, testDay1.getNrOfAppointments());
        
        assertTrue(testDay1.containsAppointmentWithDescription("testAddAppointmentWithStartTimeSet 4"));
        testDay1.removeAppointment("testAddAppointmentWithStartTimeSet 4");
        assertFalse(testDay1.containsAppointmentWithDescription("testAddAppointmentWithStartTimeSet 4"));
        assertEquals(5, testDay1.getNrOfAppointments());
        
        testDay1.removeAppointment("testAddAppointmentWithStartTimeSet 1");
        testDay1.removeAppointment("testAddAppointmentWithStartTimeSet 2");
        testDay1.removeAppointment("testAddAppointmentWithStartTimeSet 3");
        testDay1.removeAppointment("testAddAppointmentWithStartTimeSet 4");
        testDay1.removeAppointment("testAddAppointmentWithStartTimeSet 5");
        testDay1.removeAppointment("lunch break");
        
        assertEquals(0, testDay1.getNrOfAppointments());
        
        Appointment appWST6 = new Appointment("testAddAppointmentWithStartTimeSet 5", new TimeSpan(0,59));
        appWST6.setStart(new Time(8,31));
        testDay1.addAppointmentWithStartTimeSet(appWST6);
        Appointment appWST7 = new Appointment("testAddAppointmentWithStartTimeSet 5", new TimeSpan(0,59));
        appWST7.setStart(new Time(15,30));
        testDay1.addAppointmentWithStartTimeSet(appWST7);
        
        assertTrue(testDay1.containsAppointmentWithDescription("testAddAppointmentWithStartTimeSet 5"));
        
        assertEquals(1, testDay1.getNrOfAppointments());
        testDay1.removeAppointment("testAddAppointmentWithStartTimeSet 5");
        
        assertEquals(0, testDay1.getNrOfAppointments());
        Appointment appWST8 = new Appointment("testAddAppointmentWithStartTimeSet 6", new TimeSpan(9,59));
        appWST8.setStart(new Time(8,30));
        testDay1.addAppointmentWithStartTimeSet(appWST8);
        assertEquals(0, testDay1.getNrOfAppointments());
        
        Appointment appWST9 = new Appointment("testAddAppointmentWithStartTimeSet 6", new TimeSpan(2,59));
        testDay1.addAppointmentWithStartTimeSet(appWST9);
        assertEquals(0, testDay1.getNrOfAppointments());
        
        
        
      
    } 

    /**
     * Test of addAppointment method, of class Day.
     */
    
    @Test
    public void testAddAppointment() {
        Day testDay2 = new Day(4);
        Appointment appWST1 = new Appointment("testAddAppointment 1", new TimeSpan(1,0));
        testDay2.addAppointment(appWST1);
        
        Appointment appWST2 = new Appointment("testAddAppointment 2", new TimeSpan(2,0));
        testDay2.addAppointment(appWST2);
        
        Appointment appWST3 = new Appointment("testAddAppointment 3", new TimeSpan(0,59));
        testDay2.addAppointment(appWST3);
        
        Appointment appWST4 = new Appointment("testAddAppointment 4", new TimeSpan(2,0));
        testDay2.addAppointment(appWST4);
              
        Appointment appWST5 = new Appointment("testAddAppointment 5", new TimeSpan(1,59));
        testDay2.addAppointment(appWST5);
        
        testDay2.getAppointments().show();
        assertEquals(6, testDay2.getNrOfAppointments());
        
        
    }

    /**
     * Test of removeAppointment method, of class Day.
     */
    
    @Test
    public void testRemoveAppointment() {
        Appointment appointmentToDelte = new Appointment("deleteMe", new TimeSpan(1, 0));
        appointmentToDelte.setStart(new Time(8, 30));
        testDay.addAppointmentWithStartTimeSet(appointmentToDelte);
        
        assertTrue(testDay.containsAppointmentWithDescription("deleteMe"));
        testDay.removeAppointment("deleteMe");
        assertFalse(testDay.containsAppointmentWithDescription("deleteMe"));
        
        testDay.removeAppointment("lunch break");
        assertFalse(testDay.containsAppointmentWithDescription("lunch break"));
        testDay.removeAppointment("lunch break");
        assertFalse(testDay.containsAppointmentWithDescription("lunch break"));
        
        
    }

    /**
     * Test of containsAppointmentWithDescription method, of class Day.
     */
    
    @Test
    public void testContainsAppointmentWithDescription() {        
        Appointment appointmentToDelte = new Appointment("HelloWorld", new TimeSpan(1, 0));
        appointmentToDelte.setStart(new Time(8, 30));
        testDay.addAppointmentWithStartTimeSet(appointmentToDelte);
        
        assertTrue(testDay.containsAppointmentWithDescription("HelloWorld"));
        
    }

    /**
     * Test of getAvailableStartTimesForAppointmentsOfDuration method, of class
     * Day.
     */
    
    @Test
    public void testGetAvailableStartTimesForAppointmentsOfDuration() {
        Day myDay = new Day(1);        
        Time[] timeSpanArray = myDay.getAvailableStartTimesForAppointmentsOfDuration(new TimeSpan(1, 0));
        
        assertEquals(timeSpanArray[0], new Time(8, 30));
        assertEquals(timeSpanArray[1], new Time(13, 30));
        
    }

    /**
     * Test of getAvailableTimeGaps method, of class Day.
     */
    
    @Test
    public void testGetAvailableTimeGaps() {
        TimeSpan ts = new TimeSpan(1, 0);
        Day myDay = new Day(1);
        
        TimeGap[] timeSpanArray = myDay.getAvailableTimeGaps();
        
        assertEquals(new Time(8, 30), timeSpanArray[0].getStart());
        assertEquals(new Time(12, 30), timeSpanArray[0].getEnd());
        assertEquals(new Time(13, 30), timeSpanArray[1].getStart());
        assertEquals(new Time(17, 30), timeSpanArray[1].getEnd());
        
        
        Appointment testAppointmentOne = new Appointment("testOne", new TimeSpan(1,30));
        testAppointmentOne.setStart(new Time(8,30));
        myDay.addAppointmentWithStartTimeSet(testAppointmentOne);
        
        timeSpanArray = myDay.getAvailableTimeGaps();
        assertEquals(2, timeSpanArray.length);
        
        Appointment testAppointmentTwo = new Appointment("testTwo", new TimeSpan(1,40));
        testAppointmentTwo.setStart(new Time(14,30));
        myDay.addAppointmentWithStartTimeSet(testAppointmentTwo);
        
        timeSpanArray = myDay.getAvailableTimeGaps();
        assertEquals(3, timeSpanArray.length);
        
        assertTrue(myDay.canAddAppointmentOfDuration(new TimeSpan(2,0)));
        
        
        for (TimeGap timeSpanArray1 : timeSpanArray) {
            System.out.println("###########TimeSpan Objects: " + timeSpanArray1.toString());
        }
        System.out.println("===================");
        Time[] starTimes = myDay.getAvailableStartTimesForAppointmentsOfDuration(new TimeSpan(1,0));
        System.out.println("===================");
        for (Time starTime : starTimes) {
            System.out.println("###########TimeStart Objects: " + starTime.toString()
            );
        }
        
    }

    /**
     * Test of testCheckOverlap method, of class Day.
     */
        
    @Test
    public void testCheckOverlap() {
        TimeSpan timeSpan1 = new TimeSpan(1, 0);

        //TEST APPBEFOREDAY
        Appointment testAppointmentBeforeDay = new Appointment("testCheckOverlap before Day", ts1);
        testAppointmentBeforeDay.setStart(new Time(7, 0));
        testDay.addAppointment(testAppointmentBeforeDay);
        assertTrue(testDay.checkOverlap(testAppointmentBeforeDay));

        //TEST APPAFTERDAY
        Appointment testAppointmentAfterDay = new Appointment("testCheckOverlap after Day", ts1);
        testAppointmentAfterDay.setStart(new Time(18, 0));
        testDay.addAppointment(testAppointmentAfterDay);
        assertTrue(testDay.checkOverlap(testAppointmentAfterDay));

        //TEST1 NO-COLIDE WITH LUNCH
        Appointment testAppointment3 = new Appointment("testCheckOverlap Nr1", timeSpan1);
        testAppointment3.setStart(new Time(15, 00));
        testDay.addAppointmentWithStartTimeSet(testAppointment3);
        assertTrue(testDay.containsAppointmentWithDescription("testCheckOverlap Nr1"));

        //TEST 2 SAME TIME
        Appointment testAppointment1 = new Appointment("testCheckOverlap Nr2", timeSpan1);
        testAppointment1.setStart(new Time(12, 30));
        testDay.addAppointmentWithStartTimeSet(testAppointment1);
        assertFalse(testDay.containsAppointmentWithDescription("testCheckOverlap Nr2"));

        //TEST3 COLIDE WITH LUNCH
        Appointment testAppointment2 = new Appointment("testCheckOverlap Nr3", timeSpan1);
        testAppointment2.setStart(new Time(12, 10));
        assertTrue(testDay.checkOverlap(testAppointment2));
        testDay.addAppointmentWithStartTimeSet(testAppointment2);
        assertFalse(testDay.containsAppointmentWithDescription("testCheckOverlap Nr3"));

        //TEST4 COLIDE WITH TEST1
        Appointment testAppointment4 = new Appointment("testCheckOverlap Nr4", timeSpan1);
        testAppointment4.setStart(new Time(14, 30));
        testDay.addAppointmentWithStartTimeSet(testAppointment4);
        assertTrue(testDay.checkOverlap(testAppointment4));
        assertFalse(testDay.containsAppointmentWithDescription("testCheckOverlap Nr4"));
        
        //TEST AFTER LUNCH BUT BEFORE TEST 1
        Appointment testAppointment5 = new Appointment("testCheckOverlap Nr5", new TimeSpan(0,30));
        testAppointment5.setStart(new Time(13,31));
        assertFalse(testDay.checkOverlap(testAppointment5));
        testDay.addAppointmentWithStartTimeSet(testAppointment5);
        
    }
    
    /**
     * Test of getAvailableTimeGaps method, of class Day.
     */
    
    @Test
    public void testGetAppointments() {
        Day myQueDay = new Day(1);
        
        
        Appointment appT5 = new Appointment("Appointment 5", new TimeSpan(0,59));
        appT5.setStart(new Time(13,31));
        myQueDay.addAppointmentWithStartTimeSet(appT5);
        
        Appointment appT4 = new Appointment("Appointment 4", new TimeSpan(0,59));
        appT4.setStart(new Time(11,30));
        myQueDay.addAppointmentWithStartTimeSet(appT4);
        
        Appointment appT3 = new Appointment("Appointment 3", new TimeSpan(0,59));
        appT3.setStart(new Time(10,30));
        myQueDay.addAppointmentWithStartTimeSet(appT3);
        
        Appointment appT2 = new Appointment("Appointment 2", new TimeSpan(0,59));
        appT2.setStart(new Time(9,30));
        myQueDay.addAppointmentWithStartTimeSet(appT2);
              
        Appointment appT1 = new Appointment("Appointment 1", new TimeSpan(0,59));
        appT1.setStart(new Time(8,30));
        myQueDay.addAppointmentWithStartTimeSet(appT1);
        

        Queue myTestQueue = new Queue();
        myTestQueue = myQueDay.getAppointments();
        
        myTestQueue.show();
        assertEquals(6, myTestQueue.getSize());
        
        
        Queue<Appointment> appQueue = new Queue();
        Day myQueDay1 = new Day(2);
        Appointment app1 = new Appointment("Ali B", new TimeSpan(1, 0));
        myQueDay1.addAppointment(app1);
        Appointment app2 = new Appointment("Bert1", new TimeSpan(1, 0));
        myQueDay1.addAppointment(app2);
        Appointment app3 = new Appointment("Bert2", new TimeSpan(1, 0));
        myQueDay1.addAppointment(app3);
        
        
        appQueue = myQueDay1.getAppointments();
        System.out.println("#+#+#+#+#+#+#+#+#+#+##+#+#+#+");
        appQueue.show();
        assertEquals(app1, appQueue.get());
        assertEquals(app2, appQueue.get());
        assertEquals(app3, appQueue.get());
        
        
        
        
        
    }

    @Test
    public void testGetAppointmentsOfPriority() {
        Day myStackDay = new Day(1);

        Appointment appT5 = new Appointment("Appointment 5", new TimeSpan(0,59), Priority.HIGH);
        appT5.setStart(new Time(13,31));
        myStackDay.addAppointmentWithStartTimeSet(appT5);
        
        Appointment appT4 = new Appointment("Appointment 4", new TimeSpan(0,59), Priority.HIGH);
        appT4.setStart(new Time(11,30));
        myStackDay.addAppointmentWithStartTimeSet(appT4);
        
        Appointment appT3 = new Appointment("Appointment 3", new TimeSpan(0,59), Priority.HIGH);
        appT3.setStart(new Time(10,30));
        myStackDay.addAppointmentWithStartTimeSet(appT3);
        
        Appointment appT2 = new Appointment("Appointment 2", new TimeSpan(0,59));
        appT2.setStart(new Time(9,30));
        myStackDay.addAppointmentWithStartTimeSet(appT2);
              
        Appointment appT1 = new Appointment("Appointment 1", new TimeSpan(0,59));
        appT1.setStart(new Time(8,30));
        myStackDay.addAppointmentWithStartTimeSet(appT1);
        

        Stack<Appointment> stack = myStackDay.getAppointmentsOfPriority(Priority.HIGH);

        assertTrue(!stack.isEmpty());
        System.out.println("#+#+#+#+#+#+#+#+#+#+#+#+##++#+#+#+#+#");
        System.out.println("Stac Size: " + stack.size());

        assertEquals(appT3, stack.pop());
        assertEquals(appT4, stack.pop());
        assertEquals(appT5, stack.pop());
        assertTrue(stack.isEmpty());
          
    }
    

 
}