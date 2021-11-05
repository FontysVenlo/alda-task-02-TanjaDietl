/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentplanner.util;

import appointmentplanner.Appointment;
import appointmentplanner.TimeSpan;
import appointmentplanner.util.Queue;
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
        assertEquals(6, q.getSize());
        q.removeAll();
        assertTrue(q.isEmpty());
        
        q.put("item 1");
        assertFalse(q.isEmpty());
        q.get();
        assertTrue(q.isEmpty());
        
        Queue<String> q2 = new Queue();
        q2.put("item1");
        q2.put("item2");
        String get = q2.get();
        String get2 = q2.get();
        assertTrue(q2.isEmpty());
        assertEquals("item1", get);
        assertEquals("item2", get2);

        Queue<Appointment> q3 = new Queue();
        Appointment app7 = new Appointment("Ali B", new TimeSpan(1, 0));
        Appointment app8 = new Appointment("Bert1", new TimeSpan(1, 0));
        Appointment app9 = new Appointment("Bert2", new TimeSpan(1, 0));
        
        q3.put(app7);
        q3.put(app8);
        q3.put(app9);
        
        assertEquals(app7, q3.get());
        assertEquals(app8, q3.get());
        assertEquals(app9, q3.get());
        

        
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
        System.out.println("GET EMPTY");
        assertTrue(q.isEmpty());
        q.put(app1);
        assertFalse(q.isEmpty());
        assertEquals(app1, q.get());
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

        for (int i = 0; i < 201; i++) {
            q.put("APP");
        }
        assertEquals(200, q.getSize());
        assertTrue(q.isFull());
    }

}