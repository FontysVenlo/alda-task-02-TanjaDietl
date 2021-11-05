/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentplanner;


import java.util.Objects;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author tani.d
 */
public class TimeSpanTest {
    private TimeSpan timeSpan;
    private TimeSpan timeSpanTwo;
    private Time timeOne;
    private Time timeTwo;
    


    /**
     * Test of getHours method, of class TimeSpan.
     */
    @Test
    public void testGetHours() {
        timeSpan = new TimeSpan(12,12);
        assertEquals(732, timeSpan.getTimeSpanInMinutes());
    }
    /**
     * Test of getHours method, of class TimeSpan.
     */
    @Test
    public void testTimeSpan() {
        timeOne = new Time(2,25);
        timeTwo = new Time(3,40);
        timeSpan = new TimeSpan(timeTwo, timeOne);
        assertEquals(75, timeSpan.getTimeSpanInMinutes());
        assertEquals(75, TimeSpan.getMinutesInTimeSpan(timeOne, timeTwo));
        
        
        
        
    }
    /**
     * Test of getMinutes method, of class TimeSpan.
     */
    @Test
    public void testGetMinutes() {
        timeSpan = new TimeSpan(12,12);
        assertEquals(12, timeSpan.getMinutes());
    }

    /**
     * Test of getTimeSpanInMinutes method, of class TimeSpan.
     */
    @Test
    public void testGetTimeSpanInMinutes() {
        
        timeOne = new Time(24,0);
        timeTwo = new Time(1,00);
        timeSpan = new TimeSpan(timeOne, timeTwo);
        assertEquals(1380, timeSpan.getTimeSpanInMinutes());
    }

    /**
     * Test of isSmallerThan method, of class TimeSpan.
     */
    @Test
    public void testIsSmallerThan() {
        timeOne = new Time(12,0);
        timeTwo = new Time(12,0);
        timeSpan = new TimeSpan(timeOne, timeTwo);
        timeSpanTwo = new TimeSpan(0,0);
        assertFalse(timeSpanTwo.isSmallerThan(timeSpan));
        
        timeOne = new Time(12,0);
        timeTwo = new Time(14,0);
        timeSpan = new TimeSpan(timeOne, timeTwo);
        timeSpanTwo = new TimeSpan(1,0);
        assertTrue(timeSpanTwo.isSmallerThan(timeSpan));
        
        timeOne = new Time(12,0);
        timeTwo = new Time(14,0);
        timeSpan = new TimeSpan(timeOne, timeTwo);
        timeSpanTwo = new TimeSpan(3,0);
        assertFalse(timeSpanTwo.isSmallerThan(timeSpan));
        
        TimeSpan ts1 = new TimeSpan(1,35);
        TimeSpan ts2 = new TimeSpan(2,40);
        assertTrue(ts1.isSmallerThan(ts2));
        
        
        TimeSpan ts3 = new TimeSpan(2,40);
        TimeSpan ts4 = new TimeSpan(2,40);
        assertFalse(ts3.isSmallerThan(ts4));
        
        
        
        
    }

    /**
     * Test of equals method, of class TimeSpan.
     */
    @Test
    public void testEquals() {
        timeOne = new Time(12,0);
        timeTwo = new Time(12,0);
        timeSpan = new TimeSpan(timeOne, timeTwo);
        timeSpanTwo = new TimeSpan(1,0);
        assertFalse(timeSpan.equals(timeSpanTwo));
        
        timeOne = new Time(12,0);
        timeTwo = new Time(11,0);
        timeSpan = new TimeSpan(timeOne, timeTwo);
        timeSpanTwo = new TimeSpan(1,0);
        assertTrue(timeSpan.equals(timeSpanTwo));
        
        timeOne = new Time(12,0);
        timeSpanTwo = new TimeSpan(1,0);
        assertFalse(timeSpanTwo.equals(timeOne));
        
        assertFalse(timeSpanTwo.equals(null));
        
        
        
        
        
    }
    

    

    /**
     * Test of toString method, of class TimeSpan.
     */
    @Test
    public void testHash() {
        timeSpanTwo = new TimeSpan(1,0);
        Time time1 = new Time(1,0);
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(time1);
        
        assertEquals(hash, timeSpanTwo.hashCode());
    }

    /**
     * Test of addTimeSpan method, of class TimeSpan.
     */
    @Test
    public void testAddTimeSpan() {
        timeOne = new Time(12,0);
        timeTwo = new Time(11,0);
        timeSpan = new TimeSpan(timeOne, timeTwo);
        timeSpanTwo = new TimeSpan(2,0);
        timeSpan.addTimeSpan(timeSpanTwo);
        assertEquals(3, timeSpan.getHours());
        
    }

    /**
     * Test of getMinutesInTimeSpan method, of class TimeSpan.
     */
    @Test
    public void testGetMinutesInTimeSpan() {
        timeOne = new Time(2,0);
        timeTwo = new Time(0,30);
        
        assertEquals(90, TimeSpan.getMinutesInTimeSpan(timeOne, timeTwo));
        
    }
    
}