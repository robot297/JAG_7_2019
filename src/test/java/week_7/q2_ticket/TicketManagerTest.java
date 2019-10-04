package week_7.q2_ticket;

import input.InputUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import test_utils.PrintUtils;

import java.lang.reflect.Field;
import java.util.Date;

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.*;
import static org.powermock.api.easymock.PowerMock.mockStatic;

@RunWith(PowerMockRunner.class)  // Needed for PowerMock to record method calls
@PrepareForTest({InputUtils.class, TicketManager.class})   // To enable InputUtils to be mocked
public class TicketManagerTest {
    
    private TicketStore store = TicketStore.getInstance();
    private ResolvedTicketStore resolvedTicketStore = ResolvedTicketStore.getInstance();
    
    private String testDirectory = "TestTicketData";
    
    @Before
    public void resetStaticTicketVar() throws Exception {
        TicketCounter.setCounter(1);
    }
    
    
    @Before
    public void clearTicketStore() throws Exception {
        store.removeAll();
        resolvedTicketStore.getAll().clear();
    }
    
    

    
    
    @Test(timeout=3000)
    public void e2eAddNewTicket() {
        
        mockStatic(InputUtils.class);
        
        // Select 1 from menu
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.ADD_TICKET);
        // Provide valid priority, description, reporter
        expect(InputUtils.intInput(anyString())).andReturn(4);
        expect(InputUtils.positiveIntInput(anyString())).andReturn(4);
        expect(InputUtils.stringInput(anyString())).andReturn("Server down");
        expect(InputUtils.stringInput(anyString())).andReturn("Reporter");
        
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.QUIT);
        
        PowerMock.replay(InputUtils.class);
        
        TicketManager q3 = new TicketManager();
        q3.manage();
        
        System.out.println(store.getAllTickets());
        
        // Assert ticket exists in list
        
        Ticket added = store.getTicketById(1);
        
        assertNotNull(added);
        assertEquals(1, added.getTicketID());
        assertEquals("Server down", added.getDescription());
        assertEquals("Reporter", added.getReporter());
        assertEquals("dates not same", new Date().getTime(), added.getDateReported().getTime(), 1000);
    }
    
    
    @Test(timeout=3000)
    public void e2eSearchId() {
        
        
        Ticket test = new Ticket("Mouse missing", 3, "Cubicle 123", new Date());
        TicketStore.getInstance().add(test);
        
        mockStatic(InputUtils.class);
        
        // Select 1 from menu
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.SEARCH_BY_ID);
        // Provide valid priority, description, reporter
        expect(InputUtils.intInput(anyString())).andReturn(1);  // search for ID 1
        expect(InputUtils.positiveIntInput(anyString())).andReturn(1);
        
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.QUIT);
        
        PowerMock.replay(InputUtils.class);
        
        PrintUtils.catchStandardOut();
        TicketManager q3 = new TicketManager();
        q3.manage();
        String out = PrintUtils.resetStandardOut();
        
        assertTrue(out.contains("3"));
        assertTrue(out.contains("Mouse missing"));
        assertTrue(out.contains("Cubicle 123"));
        
    }
    
    
    @Test(timeout=3000)
    public void e2eDeleteTicketByIdAndConfirm() throws Exception{
        
        
        Ticket test = new Ticket("Mouse missing", 3, "Cubicle 123", new Date());
        store.add(test);
        
        mockStatic(InputUtils.class);
        
        // Select 1 from menu
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.DELETE_BY_TICKET_ID);
        expect(InputUtils.intInput(anyString())).andReturn(1);  // search for ID 1
        expect(InputUtils.positiveIntInput(anyString())).andReturn(1);
        
        expect(InputUtils.yesNoInput(anyString())).andReturn(true);
    
        expect(InputUtils.stringInput(anyString())).andReturn("Fixed ticket");
    
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.QUIT);
        
        PowerMock.replay(InputUtils.class);
        
        TicketManager q3 = new TicketManager();
        q3.manage();
        
        assertEquals(0, TicketStore.getInstance().getAllTickets().size());
        assertEquals(1, ResolvedTicketStore.getInstance().getAll().size());
        Ticket resolvedTicket = ResolvedTicketStore.getInstance().getAll().getFirst();
        
        assertTrue(TicketUtil.sameOpenTicket(test, resolvedTicket));
        
    }
    
    
    @Test(timeout=3000)
    public void e2eDeleteTicketByIdDontConfirm() {
        
        TicketStore.getInstance().removeAll();
        
        Ticket test = new Ticket("Mouse missing", 3, "Cubicle 123", new Date());
        store.add(test);
        
        mockStatic(InputUtils.class);
        
        // Select 1 from menu
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.DELETE_BY_TICKET_ID);
        // Provide valid priority, description, reporter
        expect(InputUtils.intInput(anyString())).andReturn(1);  // search for ID 1
        expect(InputUtils.positiveIntInput(anyString())).andReturn(1);
        
        expect(InputUtils.yesNoInput(anyString())).andReturn(false);
        
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.QUIT);
        
        PowerMock.replay(InputUtils.class);
        
        TicketManager q3 = new TicketManager();
        q3.manage();
        
        assertEquals(1, store.getAllTickets().size());
        assertEquals(test, store.getTicketById(1));
        
    }
    
    
    @Test(timeout=3000)
    public void e2eShowNextTicket() {
        
        Ticket test = new Ticket("Mouse missing", 3, "Cubicle 123", new Date());
        store.add(test);
        
        mockStatic(InputUtils.class);
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.SHOW_NEXT_TICKET);
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.QUIT);
        PowerMock.replay(InputUtils.class);
        
        PrintUtils.catchStandardOut();
        TicketManager q3 = new TicketManager();
        q3.manage();
        String out = PrintUtils.resetStandardOut();
        
        assertTrue(out.contains(test.toString()));
        
    }
    
    
    @Test(timeout=3000)
    public void e2eShowAllTickets() {
        
        Ticket test1 = new Ticket("Mouse missing", 3, "Cubicle 123", new Date());
        Ticket test2 = new Ticket("Server down", 1, "Admin", new Date());
        Ticket test3 = new Ticket("Word is broken", 5, "User", new Date());
        store.add(test1);
        store.add(test2);
        store.add(test3);
        
        mockStatic(InputUtils.class);
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.SHOW_ALL_TICKETS);
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.QUIT);
        PowerMock.replay(InputUtils.class);
        
        PrintUtils.catchStandardOut();
        TicketManager q3 = new TicketManager();
        q3.manage();
        String out = PrintUtils.resetStandardOut();
        
        // Expect test2, then test1, then test3
        int locTest1 = out.indexOf(test1.toString());
        int locTest2 = out.indexOf(test2.toString());
        int locTest3 = out.indexOf(test3.toString());
        
        assertTrue(locTest1 >= 0 && locTest2 >= 0 && locTest3 >= 0);
        assertTrue(locTest2 < locTest1);
        assertTrue(locTest1 < locTest3);
        
    }
    
    
    @Test(timeout=3000)
    public void e2eDeleteTicketByDescription() throws Exception {
        Ticket test1 = new Ticket("Mouse missing", 3, "Cubicle 123", new Date());
        Ticket test2 = new Ticket("Server down", 1, "Admin", new Date());
        Ticket test3 = new Ticket("Lost mouse", 5, "User", new Date());
        store.add(test1);
        store.add(test2);
        store.add(test3);
        
        
        int option = 0;
        try {
            Field searchByDescription = TicketUI.class.getDeclaredField("DELETE_BY_DESCRIPTION");
            searchByDescription.setAccessible(true);
            option = (int) searchByDescription.get(null);
        } catch (NoSuchFieldException|IllegalAccessException|ClassCastException e) {
            fail("Create a static DELETE_BY_TICKET_DESCRIPTION variable in TicketUI and use it to create and show the menu. " + e);
        }
        
        mockStatic(InputUtils.class);
        expect(InputUtils.intInput(anyString())).andReturn(option);
        expect(InputUtils.stringInput(anyString())).andReturn("mouse");   // Search term
        expect(InputUtils.intInput(anyString())).andReturn(3);    // ticket 3
        expect(InputUtils.yesNoInput(anyString())).andReturn(true);
        expect(InputUtils.stringInput(anyString())).andReturn("rebooted");  // resolution
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.QUIT);
        PowerMock.replay(InputUtils.class);
        
        TicketManager q3 = new TicketManager();
        q3.manage();
        
        assertNull(store.getTicketById(3));
        
        Ticket resolved = ResolvedTicketStore.getInstance().getAll().getFirst();
    
        System.out.println(test3);
        System.out.println(resolved);
        assertTrue("Resolved ticket not found in resolved ticket list", TicketUtil.sameOpenTicket(resolved, test3));
        
    }
    
    
    @Test(timeout=3000)
    public void e2eDeleteTicketByDescriptionUserCancels() throws Exception {
        
        Ticket test1 = new Ticket("Mouse missing", 3, "Cubicle 123", new Date());
        Ticket test2 = new Ticket("Server down", 1, "Admin", new Date());
        Ticket test3 = new Ticket("Lost mouse", 5, "User", new Date());
        store.add(test1);
        store.add(test2);
        store.add(test3);
        
        int option = 0;
        try {
            Field searchByDescription = TicketUI.class.getDeclaredField("DELETE_BY_DESCRIPTION");
            searchByDescription.setAccessible(true);
            option = (int) searchByDescription.get(null);
        } catch (NoSuchFieldException|IllegalAccessException|ClassCastException e) {
            fail("Create a static DELETE_BY_TICKET_DESCRIPTION variable in TicketUI and use it to create and show the menu. " + e);
        }
        
        mockStatic(InputUtils.class);
        expect(InputUtils.intInput(anyString())).andReturn(option);
        expect(InputUtils.stringInput(anyString())).andReturn("mouse");   // Search term
        expect(InputUtils.intInput(anyString())).andReturn(-1);    // ticket 3
        
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.QUIT);
        PowerMock.replay(InputUtils.class);
        
        TicketManager q3 = new TicketManager();
        q3.manage();
        
        assertTrue("When deleting by description, user should be able to type -1 to not delete any Tickets.", TicketUtil.sameOpenTicket(test1, store.getTicketById(1)));
        assertTrue("When deleting by description, user should be able to type -1 to not delete any Tickets.", TicketUtil.sameOpenTicket(test2, store.getTicketById(2)));
        assertTrue("When deleting by description, user should be able to type -1 to not delete any Tickets.", TicketUtil.sameOpenTicket(test3, store.getTicketById(3)));
        
    }
    
    
    @Test(timeout=3000)
    public void e2eSearchByDescription() {
        Ticket test1 = new Ticket("Mouse missing", 3, "Cubicle 123", new Date());
        Ticket test2 = new Ticket("Server down", 1, "Admin", new Date());
        Ticket test3 = new Ticket("Lost mouse", 5, "User", new Date());
        store.add(test1);
        store.add(test2);
        store.add(test3);
        
        int option = 0;
        try {
            Field searchByDescription = TicketUI.class.getDeclaredField("SEARCH_BY_DESCRIPTION");
            searchByDescription.setAccessible(true);
            option = (int) searchByDescription.get(null);
        } catch (NoSuchFieldException|IllegalAccessException|ClassCastException e) {
            fail("Create a static SEARCH_BY_DESCRIPTION variable in TicketUI and use it to create and show the menu. " + e);
        }
        
        mockStatic(InputUtils.class);
        expect(InputUtils.intInput(anyString())).andReturn(option);
        expect(InputUtils.stringInput(anyString())).andReturn("mouse");
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.QUIT);
        PowerMock.replay(InputUtils.class);
        
        PrintUtils.catchStandardOut();
        TicketManager q3 = new TicketManager();
        q3.manage();
        String out = PrintUtils.resetStandardOut();
        
        // Expect test1, then test3. No test2
        int locTest1 = out.indexOf(test1.toString());
        int locTest2 = out.indexOf(test2.toString());
        int locTest3 = out.indexOf(test3.toString());
        
        assertTrue(locTest1 >= 0 && locTest3 >= 0);
        assertEquals(locTest2, -1);
        assertTrue(locTest1 < locTest3);
        
    }
    
    
    @Test(timeout=3000)
    public void e2eQuit() {
        
        mockStatic(InputUtils.class);
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.QUIT);
        PowerMock.replay(InputUtils.class);
        
        TicketManager q3 = new TicketManager();
        q3.manage();
    }
    
    
    @Test(timeout = 3000)
    public void ticketManagerDoesNotUseInputUtilsOrSystemOutPrintln() {
    
    }
    
}
