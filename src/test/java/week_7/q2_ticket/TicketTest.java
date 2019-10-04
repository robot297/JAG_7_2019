package week_7.q2_ticket;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by clara on 10/4/19.
 */
public class TicketTest {
    
    @Test
    public void testNewFields() {
    
        Class ticketClass = Ticket.class;
    
        try {
            Field dateResolved = ticketClass.getDeclaredField("dateResolved");
            assertEquals("Add dateResolved field to Ticket. Type should be Date.", Date.class, dateResolved.getType());
            assertTrue("dateResolved should be private", Modifier.isPrivate(dateResolved.getModifiers()));
            
            Field resolution = ticketClass.getDeclaredField("resolution");
            assertEquals("Add resolution field to Ticket. Type should be String.", String.class, resolution.getType());
            assertTrue("resolution should be private", Modifier.isPrivate(resolution.getModifiers()));
    
        } catch (NoSuchFieldException e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void testToStringResolved() {
    
        Class ticketClass = Ticket.class;
    
        long currentTime = new Date().getTime();
        Date halfHourAgo = new Date(currentTime - (30 * 60 * 1000));
        Date now = new Date();
        
        Ticket ticket = new Ticket("Charger broken", 3, "Hello Kitty", halfHourAgo);
    
        String toString = ticket.toString();
    
        assertTrue(toString.contains("Charger broken"));
        assertTrue(toString.contains("3"));
        assertTrue(toString.contains("Hello Kitty"));
        assertTrue(toString.contains(halfHourAgo.toString()));
    
        
        try {
            Field dateResolved = ticketClass.getDeclaredField("dateResolved");
            assertEquals("Add dateResolved field to Ticket. Type should be Date.", Date.class, dateResolved.getType());
        
            Field resolution = ticketClass.getDeclaredField("resolution");
            assertEquals("Add resolution field to Ticket. Type should be String.", String.class, resolution.getType());
        
            
            dateResolved.setAccessible(true);
            dateResolved.set(ticket, now);
    
            resolution.setAccessible(true);
            resolution.set(ticket, "Replaced charger, user happy.");
            
            toString = ticket.toString();
            
            assertTrue(toString.contains("Charger broken"));
            assertTrue(toString.contains("3"));
            assertTrue(toString.contains("Hello Kitty"));
            assertTrue(toString.contains(halfHourAgo.toString()));
    
            // Plus resolved data
            assertTrue(toString.contains("Replaced charger, user happy."));
            assertTrue(toString.contains(now.toString()));
    
    
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail(e.getMessage());
        }
        
        
        
        
    }
    
}