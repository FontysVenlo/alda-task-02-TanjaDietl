/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentplanner;

import appointmentplanner.util.Priority;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author tanja
 */
public class AppointmentTest {
    
    Time startTime = new Time(01, 00);
    Time endTime = new Time(02,00);
    Appointment appointment = new Appointment("abc", new TimeSpan(startTime, endTime));
    Appointment appointment1 = new Appointment("abc1", new TimeSpan(startTime, endTime), Priority.MEDIUM);
    /**
     * Test of getDescription method, of class Appointment.
     */
    @Test
    public void testAppointment() {
        appointment.setStart(startTime);
        assertEquals(appointment.getStart(), startTime);
        assertEquals(Priority.valueOf("MEDIUM"),appointment1.getPriority());
    }

    /**
     * Test of getDescription method, of class Appointment.
     */
    @Test
    public void testGetDescription() {
        assertEquals("abc", appointment.getDescription());
    }

    /**
     * Test of setStart method, of class Appointment.
     */
    @Test
    public void testSetStart() {
        Time startTime2 = new Time(5, 0);
        appointment.setStart(startTime2);
        assertEquals(startTime2, appointment.getStart());
    }

    /**
     * Test of getDuration method, of class Appointment.
     */
    @Test
    public void testGetDuration() {
        TimeSpan expectedDuration = new TimeSpan(startTime, endTime);
        appointment.setStart(startTime);
        assertEquals(expectedDuration, appointment.getDuration());
    }

    /**
     * Test of getEnd method, of class Appointment.
     */
    @Test
    public void testGetEnd() {
        Time endTime2 = new Time(2,0);
        appointment.setStart(startTime);

        assertEquals(endTime2, appointment.getEnd());
    }

    /**
     * Test of getStart method, of class Appointment.
     */
    @Test
    public void testGetStart() {
       appointment.setStart(startTime);
       assertEquals(startTime, appointment.getStart());
    }
    
    /**
     * Test of getStart method, of class Appointment.
     */
    @Test
    public void testGetPriority() {
       appointment.setPriority(Priority.HIGH);
       assertEquals(Priority.valueOf("HIGH"), appointment.getPriority());
    }
    /**
     * Test of getStart method, of class Appointment.
     */
    @Test
    public void testSetPriority() {
       appointment.setPriority(Priority.MEDIUM);
       assertEquals(Priority.valueOf("MEDIUM"), appointment.getPriority());
    }
    
}