package week_7.q2_ticket;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.LinkedList;

import static org.junit.Assert.*;


public class TicketDataStoreTest {
    
    @Before()
    public void resetStaticTicketVar() throws Exception {
//        Class ticketClass = Class.forName("week_7.q2_ticket.Ticket");
//        Field counter = ticketClass.getDeclaredField("ticketIdCounter");
//        counter.setAccessible(true);
//        counter.set(null, 1);
        TicketCounter.setCounter(0);
    }
    
    
    @Before()
    public void clearTicketStore() throws Exception {
        TicketStore store = TicketStore.getInstance();
        store.removeAll();
    }
    
    
    @Test(timeout=3000)
    public void testAddTicketSorted() throws Exception {
    
        TicketStore store = TicketStore.getInstance();
    
        // Test tickets with all different priorities
        Ticket testPr1 = new Ticket("The server is on fire", 1, "reporter", new Date());
        Ticket testPr5 = new Ticket("Mouse mat stolen", 5, "reporter", new Date());
        Ticket testPr3 = new Ticket("Word needs updating", 3, "reporter", new Date());
    
        //Add these tickets. Assert they are added with lowest priority first
        store.add(testPr1);
        store.add(testPr5);
        store.add(testPr3);
    
        LinkedList allTickets = store.getAllTickets();
    
        String msg = "Tickets should be added in priority order, same priority sort by name";
        assertEquals(msg, allTickets.pop(), testPr1);
        assertEquals(msg, allTickets.pop(), testPr3);
        assertEquals(msg, allTickets.pop(), testPr5);
    
    }
    
    
    @Test(timeout=3000)
    public void testAddTicketSortedByDate() throws Exception {
        
        // Tickets with joint priorities should be sorted by date
        TicketStore store = TicketStore.getInstance();
        
        Ticket testPr1_newer = new Ticket("The server is down", 1, "reporter", new Date(1500000000000L));
        Ticket testPr1_older = new Ticket("Another server is down", 1, "reporter", new Date(1400000000000L));
        Ticket testPr4 = new Ticket("Several mouse mats stolen", 4, "reporter", new Date());
    
        //Add these tickets. Assert they are added with lowest priority first,
        //and equal priorities sorted with oldest first
        store.add(testPr1_newer); store.add(testPr1_older); store.add(testPr4);
    
        LinkedList<Ticket> allTickets = store.getAllTickets();
    
        String msg = "Tickets should be added in priority order. Tickets with the same priority should be sorted by date";
        assertEquals(msg, testPr1_older, allTickets.pop());
        assertEquals(msg, testPr1_newer, allTickets.pop());
        assertEquals(msg, testPr4, allTickets.pop());
        
    }
    
    
    @Test(timeout=3000)
    public void testPeekNextTicket() throws Exception {
    
        TicketStore store = TicketStore.getInstance();
    
        Ticket testPr4 = new Ticket("The server is dusty", 4, "1", new Date());
        Ticket testPr2 = new Ticket("Server keeps rebooting", 2, "2", new Date());
        Ticket testPr3 = new Ticket("Mouse mat stolen", 3, "3", new Date());
    
        //Add these tickets
        store.add(testPr2); store.add(testPr4); store.add(testPr3);

        // Most severe should be returned
        assertEquals("The most severe ticket should be returned on a call to peekNextTicket", TicketStore.getInstance().peekNextTicket(), testPr2);
        
    }
    
    
    @Test
    public void testCountTicketsInQueue() throws Exception {
    
        TicketStore store = TicketStore.getInstance();
    
        Ticket testPr4 = new Ticket("The server is dusty", 4, "1", new Date());
        Ticket testPr2 = new Ticket("Server keeps rebooting", 2, "2", new Date());
        Ticket testPr3 = new Ticket("Mouse mat stolen", 3, "3", new Date());
    
        //Add these tickets
        store.add(testPr2); store.add(testPr4); store.add(testPr3);
    
        assertEquals("After adding three tickets, there should be 3 tickets in the queue", store.ticketsInQueue(), 3);
        
    }
    
    
    
    @Test(timeout=3000)
    public void testSearchDescriptionEmptyStore() throws Exception {
        TicketStore store = TicketStore.getInstance();
        
        // Any searches on an empty list should return an empty list.
        assertEquals(0, store.searchByDescription("office").size());
        assertEquals(0, store.searchByDescription("").size());
        assertEquals(0, store.searchByDescription(null).size());

    }
        
        
        @Test(timeout=3000)
    public void testSearchDescriptionExpectedFound() throws Exception {
    
        TicketStore store = TicketStore.getInstance();
        
        Ticket test1 = new Ticket("The server is on fire", 1, "1", new Date());
        Ticket test2 = new Ticket("Server keeps rebooting", 2, "2", new Date());
        Ticket test3 = new Ticket("Mouse mat stolen", 3, "3", new Date());
        Ticket test4 = new Ticket("Critical security updates", 1, "3", new Date());
    
        //Add these tickets
        
        store.add(test1); store.add(test2); store.add(test3); store.add(test4);
    
        System.out.println("This test uses the following example tickets\n" + "\n" + test1 + "\n" + test2 + "\n" + test3 + "\n" + test4);
        
        // Search for 'server'. Should not be case sensitive, return test1 and test2
        
        LinkedList<Ticket> results = store.searchByDescription("Server");
        String msg = "Return a LinkedList of the 2 Tickets whose description contains the search text 'Server'. If no matches, return an empty list. Your search should not be case sensitive";
        assertNotNull(msg, results);
        assertEquals(msg, results.size(), 2);   // 2 results
        assertTrue(msg, results.contains(test1));
        assertTrue(msg, results.contains(test2));
        assertFalse(msg, results.contains(test3));
        assertFalse(msg, results.contains(test4));
        
        results = store.searchByDescription("SeCuRiTy UpDaTeS");
        
        msg = "Return a LinkedList of 1 Ticket whose description contains the search text 'security updates'. Your search should not be case sensitive";
        assertNotNull(msg, results);
        assertEquals(msg, results.size(), 1);   // just one ticket
        assertTrue(msg, results.contains(test4));
    
    
        results = store.searchByDescription("at stol");   // matches "Mouse mat stolen"
    
        msg = "Return a LinkedList of 1 Ticket whose description contains the search text 'at stol'. Your search should not be case sensitive";
        assertNotNull(msg, results);
        assertEquals(msg, results.size(), 1);   // just one ticket
        assertTrue(msg, results.contains(test3));
        
    }
    
    
    @Test(timeout=3000)
    public void testSearchDescriptionNotFound() throws Exception {
        
        TicketStore store = TicketStore.getInstance();
        
        Ticket test1 = new Ticket("The server is on fire", 1, "1", new Date());
        Ticket test2 = new Ticket("Server keeps rebooting", 2, "2", new Date());
        Ticket test3 = new Ticket("Mouse mat stolen", 3, "3", new Date());
        Ticket test4 = new Ticket("Critical security updates", 1, "3", new Date());
        
        //Add these tickets
        
        store.add(test1); store.add(test2); store.add(test3); store.add(test4);
        
        System.out.println("This test uses the following example tickets\n" + "\n" + test1 + "\n" + test2 + "\n" + test3 + "\n" + test4);
        
        // Search for something not in the list
        LinkedList<Ticket> results = store.searchByDescription("Powerpoint");
        String msg = "Search for 'Powerpoint' should return a LinkedList of results. If no matches, return an empty list.";
        assertNotNull(msg, results);
        assertEquals(msg, results.size(), 0);   // No results
        
        // Empty string - should return empty list
        results = store.searchByDescription("");
        assertNotNull("A search for an empty string should return an empty list", results);
        assertEquals("A search for an empty string should return an empty list", results.size(), 0);   // No results
    
        
        // Null string - should return empty list
        results = store.searchByDescription(null);
        assertNotNull("A search for null string should return an empty list", results);
        assertEquals("A search for null string should return an empty list", results.size(), 0);   // No results
        
    }
    
    
    @Test(timeout=3000)
    public void testGetTicketByIDTicketFound() throws Exception {
        
        TicketStore store = TicketStore.getInstance();
        
        Ticket test1 = new Ticket("The server is on fire", 1, "1", new Date());    //1
        Ticket test2 = new Ticket("Server keeps rebooting", 2, "2", new Date());  // 2
        Ticket test3 = new Ticket("Mouse mat stolen", 3, "3", new Date());          // 3
        Ticket test4 = new Ticket("Critical security updates", 1, "3", new Date());    //4
        
        store.add(test1); store.add(test2); store.add(test3); store.add(test4);
        
        String msg = "Fetch ticket for ticket ID returns wrong ticket. ID, expected ticket = ";
        
        assertEquals(msg + 4 + test4, test4, store.getTicketById(4));
        assertEquals(msg + 3 + test3, test3, store.getTicketById(3));
        assertEquals(msg + 2 + test2, test2, store.getTicketById(2));
        assertEquals(msg + 1 + test1, test1, store.getTicketById(1));
        
    }
    
    @Test(timeout=3000)
    public void testGetTicketByIDNotFound() throws Exception {
        
        TicketStore store = TicketStore.getInstance();
        
        Ticket test1 = new Ticket("The server is on fire", 1, "1", new Date());    //1
        Ticket test2 = new Ticket("Server keeps rebooting", 2, "2", new Date());  // 2
        Ticket test3 = new Ticket("Mouse mat stolen", 3, "3", new Date());          // 3
        Ticket test4 = new Ticket("Critical security updates", 1, "3", new Date());    //4
    
        store.add(test1); store.add(test2); store.add(test3); store.add(test4);
        
        String msgNull = "Searching for a ticket ID that is not in the list should return null";
        
        assertNull(msgNull, store.getTicketById(0));  // not valid
        assertNull(msgNull, store.getTicketById(-2));  // not valid
        assertNull(msgNull, store.getTicketById(200));  // doesn't exist valid
        
    }
    
    
    @Test(timeout=3000)
    public void testDeleteTicketByID() throws Exception {
    
        TicketStore store = TicketStore.getInstance();
        
        Ticket test1 = new Ticket("The server is on fire", 1, "1", new Date());    //1
        Ticket test2 = new Ticket("Server keeps rebooting", 2, "2", new Date());  // 2
        Ticket test3 = new Ticket("Mouse mat stolen", 3, "3", new Date());          // 3
        Ticket test4 = new Ticket("Critical security updates", 1, "3", new Date());    //4
    
        store.add(test1); store.add(test2); store.add(test3); store.add(test4);
    
        store.deleteTicketById(3);
        
        LinkedList<Ticket> allTickets = store.getAllTickets();
        
        String msg = "Delete one ticket from list, list should be one ticket shorter, remaining tickets still present";
        assertEquals(msg, allTickets.size(), 3);
        assertFalse(msg, allTickets.contains(test3));   // 3 has been deleted

        assertTrue(msg, allTickets.contains(test1));
        assertTrue(msg, allTickets.contains(test2));
        assertTrue(msg, allTickets.contains(test4));  // Others still present
        
        // Delete non-existent ticket
        store.deleteTicketById(3);
        assertEquals("After deleting non-existent ticket, list does not change", allTickets.size(), 3);   // List not changed
    
        store.deleteTicketById(-1);
        assertEquals("After deleting non-existent ticket, list does not change", allTickets.size(), 3);   // List not changed
        
        // Delete another ticket
        
        store.deleteTicketById(1);
        assertEquals(msg, allTickets.size(), 2);   // one less ticket
        assertFalse(msg, allTickets.contains(test1));   // 1 has been deleted
        
        assertTrue(msg, allTickets.contains(test2));
        assertTrue(msg, allTickets.contains(test4));  // Others still present
    
    
        // Delete last two, should not be errors
    
        store.deleteTicketById(2);
        store.deleteTicketById(4);
        assertEquals(msg, allTickets.size(), 0);   // empty
        
        // Delete non-existent ticket
    
        store.deleteTicketById(1);   // no errors
        
    }
    
    
}