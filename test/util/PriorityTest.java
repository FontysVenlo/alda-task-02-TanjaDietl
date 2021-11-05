/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appointmentplanner.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tanja
 */
public class PriorityTest {
    

    @Test
    public void testPriority() {
        Priority p = Priority.HIGH;
        assertEquals(Priority.valueOf("HIGH"), p);
    }

    
    
}