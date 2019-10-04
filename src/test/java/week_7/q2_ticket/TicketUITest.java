package week_7.q2_ticket;

import input.InputUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import test_utils.PrintUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static junit.framework.TestCase.fail;
import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.expect;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replay;

@RunWith(PowerMockRunner.class)  // Needed for PowerMock to record method calls
@PrepareForTest(InputUtils.class)   // To enable InputUtils to be mocked
public class TicketUITest {
    
    
    @Before()
    public void resetStaticTicketVar() throws Exception {
        TicketCounter.setCounter(0);
    }
    
    
    @Before()
    public void clearTicketStore() throws Exception {
        TicketStore store = TicketStore.getInstance();
        store.removeAll();
    }
    
    
    /* *************** Test TicketUI Methods **********************/
    
    
    @Test(timeout=3000)
    public void testPriorityInRange() throws Exception {
    
        // TODO if you modify the signature of getNewTicketInfo, for example, to pass in a max and min priority, you'll need to modify this test too.
    
        System.out.println("If this test times out, make sure you don't accept priorities outside the range 1-5. " +
        "\nIf you get 'Unexpected method call' errors, check you only ask 3 questions: description, reporter, priority, in that order");
        
        TicketUI ui = new TicketUI();
    
        // 'record' expected user responses
        mockStatic(InputUtils.class);
        expect(InputUtils.stringInput(anyString())).andReturn("Whatever");   // whatever problem
        expect(InputUtils.stringInput(anyString())).andReturn("Whatever");   // whatever user
        
        expect(InputUtils.intInput(anyString())).andReturn(6);   // Mock user enters 6 for priority
        expect(InputUtils.intInput(anyString())).andReturn(1000);   // Mock user enters 100 for priority
        expect(InputUtils.intInput(anyString())).andReturn(-1);   // Mock user enters -1 for priority
        expect(InputUtils.intInput(anyString())).andReturn(0);   // Mock user enters 0 for priority
        expect(InputUtils.intInput(anyString())).andReturn(4);   // Mock user enters 4 for priority
    
        // or, if positiveIntInput is used,
        expect(InputUtils.positiveIntInput(anyString())).andReturn(6);   // Mock user enters 6 for priority
        expect(InputUtils.positiveIntInput(anyString())).andReturn(1000);   // Mock user enters 100 for priority
        expect(InputUtils.positiveIntInput(anyString())).andReturn(-1);   // Mock user enters -1 for priority
        expect(InputUtils.positiveIntInput(anyString())).andReturn(0);   // Mock user enters 0 for priority
        expect(InputUtils.positiveIntInput(anyString())).andReturn(4);   // Mock user enters 4 for priority
    
        
        replay(InputUtils.class);
        
        Ticket t = ui.getNewTicketInfo();
        
        assertEquals("Do not accept ticket priorities outside the range 1-5", 4, t.getPriority());
        
    }
    
    
    @Test(timeout=3000)
    public void addedNewMenuItemsSearchByDescriptionDeleteByDescription() {
        
        TicketManager q3 = new TicketManager();
        
        mockStatic(InputUtils.class);
        expect(InputUtils.intInput(anyString())).andReturn(TicketUI.QUIT);
        replay(InputUtils.class);
        
        PrintUtils.catchStandardOut();
        q3.manage();
        String out = PrintUtils.resetStandardOut();
        
        assertTrue("Ensure your menu has an option 'Search by description'. Use that exact text.", out.toLowerCase().contains("search by description"));
        assertTrue("Ensure your menu has an option 'Delete by description'. Use that exact text.", out.toLowerCase().contains("delete by description"));
    
        
        Class ui = TicketUI.class;
        try {
            Field dbd = ui.getDeclaredField("DELETE_BY_DESCRIPTION");
            Field sbd = ui.getDeclaredField("SEARCH_BY_DESCRIPTION");
            
            assertTrue("Constants added should be static", Modifier.isStatic(dbd.getModifiers()));
            assertTrue("Constants added should be static", Modifier.isStatic(sbd.getModifiers()));
    
            assertTrue("Constants added should be constants (final)", Modifier.isFinal(sbd.getModifiers()));
            assertTrue("Constants added should be constants (final)", Modifier.isFinal(sbd.getModifiers()));
            
        } catch (NoSuchFieldException e) {
            fail("Add two new constants with the same names given in the instructions. ");
        }
    }
    
    

}