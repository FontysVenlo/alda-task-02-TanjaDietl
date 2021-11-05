/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentplanner;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author tanja
 */
public class TimeTest {
    
    private Time time;
    private Time newTime;
    private Time resultTime;
    private Time expectedResultTime;
    

    /**
     * Test of getHours method, of class Time.
     */
    @Test
    public void testGetHours() {
        time = new Time(12,0);
        assertEquals(12, time.getHours());
    }

    /**
     * Test of getMinutes method, of class Time.
     */
    @Test
    public void testGetMinutes() {
        time = new Time(12,0);
        assertEquals(0, time.getMinutes());
    }

    /**
     * Test of addTime method, of class Time.
     */
    @Test
    public void testAddTime() {
        time = new Time(1,15);
        newTime = new Time(0,45);        
        expectedResultTime = new Time(2,0);
        
        assertEquals(expectedResultTime, time.addTime(newTime));
    }

    /**
     * Test of isBefore method, of class Time.
     */
    @Test
    public void testIsBefore() {
        time = new Time(12,30);
        newTime = new Time(12,31);
        assertEquals(true, time.isBefore(newTime));
        
        time = new Time(12,30);
        newTime = new Time(12,29);
        assertEquals(false, time.isBefore(newTime));
        
        time = new Time(2,25);
        newTime = new Time(3,40);
        assertEquals(true, time.isBefore(newTime));
    }

    /**
     * Test of getProperTime method, of class Time.
     */
    @Test
    public void testGetProperTime() {
        //time = new Time(13,30);
        //assertEquals(time, Time.getProperTime(12, 90));
        
        time = new Time(0,0);
        assertEquals(time, Time.getProperTime(-12, 90));
        
        time = new Time(0,0);
        assertEquals(time, Time.getProperTime(3, -180));
        
        time = new Time(3,0);
        assertEquals(time, Time.getProperTime(27, 0));
        
        time = new Time(2,0);
        assertEquals(time, Time.getProperTime(98, 0));
        
        time = new Time(1,10);
        assertEquals(time, Time.getProperTime(0, 70));
        
        time = new Time(7,0);
        assertEquals(time, Time.getProperTime(4, 180));
        
        time = new Time(1,50);
        assertEquals(time, Time.getProperTime(2, -10));
        
        time = new Time(0,0);
        assertEquals(time, Time.getProperTime(-2, 0));
        
        time = new Time(0,0);
        assertEquals(time, Time.getProperTime(-10, -10));
        
        time = new Time(0,2);
        assertEquals(time, Time.getProperTime(-1, 62));
        
        time = new Time(0,0);
        assertEquals(time, Time.getProperTime(-220, -100));
        
        time = new Time(0,0);
        assertEquals(time, Time.getProperTime(-60, 60));
    }

    /**
     * Test of equals method, of class Time.
     */
    @Test
    public void testEquals() {
        time = new Time(13,30);
        newTime = new Time(13,30);
        assertTrue(time.equals(newTime));
        
        time = new Time(13,30);
        newTime = new Time(13,20);
        assertFalse(time.equals(newTime));
        
        time = new Time(13,30);
        TimeSpan newTimeSpan = new TimeSpan(13,20);
        assertFalse(time.equals(newTimeSpan));
        
    }

    /**
     * Test of hashCode method, of class Time.
     */
    @Test
    public void testHashCode() {
        time = new Time(13,30);
        assertEquals(778, time.hashCode());
    }

    /**
     * Test of toString method, of class Time.
     */
    @Test
    public void testToString() {
        time = new Time(13,30);
        assertEquals("13:30", time.toString());
    }
    
}