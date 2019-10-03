package week_7.q2_ticket;

/**
 * This class keeps track of the Ticket ID numbers
 * Each Ticket gets it's own unique ID, starting from 1 and counting up
 */

public class TicketCounter {
    
    private static int counter = 1;
    
    public static int getNextCounterValue() {
        return counter++;
    }
    
    public static void setCounter(int value) {
        counter = value;
    }
}
