/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentplanner;

import org.hamcrest.core.Is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author tani.d
 */
public class QueueTest {

    Queue q = new Queue();
    /**
     * Test of Queue Class, of class Queue.
     */
    @Test
    public void testQueue() { 
        Queue qTest = new Queue();
        assertEquals(0, qTest.getSize());
        Appointment app1 = new Appointment("App1", new TimeSpan(1, 0));
        qTest.put(app1);
        assertEquals(1, qTest.getSize());
        
        
    }
    /**
     * Test of put method, of class Queue.
     */
    @Test
    public void testPut() { 
        Appointment app1 = new Appointment("App1", new TimeSpan(1, 0));
        Appointment app2 = new Appointment("App2", new TimeSpan(1, 0));
        Appointment app3 = new Appointment("App3", new TimeSpan(1, 0));
        Appointment app4 = new Appointment("App4", new TimeSpan(1, 0));
        Appointment app5 = new Appointment("App5", new TimeSpan(1, 0));
        Appointment app6 = new Appointment("App6", new TimeSpan(1, 0));
        
        assertTrue(q.isEmpty());
        assertEquals(0, q.getSize());
        
        q.put(app1);
        assertFalse(q.isEmpty());
        assertEquals(1, q.getSize());
        
        q.put(app2);
        assertEquals(2, q.getSize());
        
        q.put(app3);
        assertEquals(3, q.getSize());
        
        q.put(app4);
        assertEquals(4, q.getSize());
        
        q.put(app5);
        assertEquals(5, q.getSize());
        
        q.put(app6);
        assertEquals(5, q.getSize());
        assertTrue(q.isFull());
        
    }
    
   

    /**
     * Test of testRemoveAll method, of class Queue.
     */
    @Test
    public void testRemoveAll() { 
        Appointment app1 = new Appointment("App1", new TimeSpan(1, 0));
        Appointment app2 = new Appointment("App2", new TimeSpan(1, 0));
        Appointment app3 = new Appointment("App3", new TimeSpan(1, 0));
        Appointment app4 = new Appointment("App4", new TimeSpan(1, 0));
        Appointment app5 = new Appointment("App5", new TimeSpan(1, 0));
        Appointment app6 = new Appointment("App6", new TimeSpan(1, 0));
        
        assertTrue(q.isEmpty());
        assertEquals(0, q.getSize());
        //Setting up Queue:
        q.put(app1);
        assertFalse(q.isEmpty());
        assertEquals(1, q.getSize());       
        q.put(app2);
        assertEquals(2, q.getSize());       
        q.put(app3);
        assertEquals(3, q.getSize());        
        q.put(app4);
        assertEquals(4, q.getSize());        
        q.put(app5);
        assertEquals(5, q.getSize()); 
        
        q.removeAll();
        assertEquals(0, q.getSize()); 
        
        
        

    }

    /**
     * Test of getSize method, of class Queue.
     */

    @Test
    public void testGetSize() {
        Appointment app1 = new Appointment("App1", new TimeSpan(1, 0));
        Appointment app2 = new Appointment("App2", new TimeSpan(1, 0));
        q.put(app1);
        assertFalse(q.isEmpty());
        assertEquals(1, q.getSize()); 
    }
    
    /**
     * Test of testIsEmpty method, of class Queue.
     */

    @Test
    public void testIsEmpty() {
        Appointment app1 = new Appointment("App1", new TimeSpan(1, 0));
        
        assertTrue(q.isEmpty());
        q.put(app1);
        assertFalse(q.isEmpty());
        q.get();
        assertTrue(q.isEmpty());
        q.get();
        assertTrue(q.isEmpty());
    }
    
    /**
     * Test of testIsFull method, of class Queue.
     */

    @Test
    public void testIsFull() {
        Appointment app1 = new Appointment("App1", new TimeSpan(1, 0));
        Appointment app2 = new Appointment("App2", new TimeSpan(1, 0));
        Appointment app3 = new Appointment("App3", new TimeSpan(1, 0));
        Appointment app4 = new Appointment("App4", new TimeSpan(1, 0));
        Appointment app5 = new Appointment("App5", new TimeSpan(1, 0));
        Appointment app6 = new Appointment("App6", new TimeSpan(1, 0));
        assertTrue(q.isEmpty());  
        assertFalse(q.isFull());
        
        q.put(app1);   
        q.put(app2);     
        q.put(app3);      
        q.put(app4);        
        assertFalse(q.isFull());
        q.put(app5);
        assertTrue(q.isFull());
        q.put(app6);
        assertEquals(5, q.getSize());
    }

}