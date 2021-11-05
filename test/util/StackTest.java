/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentplanner.util;

import appointmentplanner.Appointment;
import appointmentplanner.TimeSpan;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tanja
 */
public class StackTest {
    
    public StackTest() {
    }

    @Test
    public void testSize() {
        Stack<Appointment> stack = new Stack<Appointment>();
        Appointment app1= new Appointment("TestAppointment1", new TimeSpan(2, 0));
        stack.push(app1);
        assertEquals(1, stack.size());
    }

    @Test
    public void testIsEmpty() {
       Stack<Appointment> stack = new Stack<Appointment>();
       Appointment app1= new Appointment("TestAppointment1", new TimeSpan(2, 0));
        stack.push(app1);
        assertEquals(1, stack.size());
        stack.pop();
        assertEquals(0, stack.size());
        
      
    }

    @Test
    public void testPush() { 
        Stack<Appointment> stack = new Stack<Appointment>();
        Appointment app1= new Appointment("TestAppointment1", new TimeSpan(2, 0));
        stack.push(app1);
        assertEquals(1, stack.size());
        Appointment app2= new Appointment("TestAppointment2", new TimeSpan(4, 0));
        stack.push(app2);
        stack.push(app2);
        stack.push(app2);
        assertEquals(4, stack.size());
    }

    @Test 
    public void testPop() {
        Stack<Appointment> stack = new Stack<Appointment>();
        Appointment app1= new Appointment("TestAppointment1", new TimeSpan(2, 0));
        stack.push(app1);
        stack.pop();
        assertEquals(0, stack.size());
        stack.pop();
        assertEquals(0, stack.size());
        
    }
    
}
