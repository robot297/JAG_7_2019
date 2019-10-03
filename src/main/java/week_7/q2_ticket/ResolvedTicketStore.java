package week_7.q2_ticket;

import java.util.LinkedList;


/*
 * Storage for Resolved Ticket objects.
 * Implemented as a Singleton - there can only ever be one TicketStore.
 * If you want to work with the TicketStore, call TicketStore.getInstance()
 *
 * You don't need to modify this class.
* */


public class ResolvedTicketStore {
    
    private static LinkedList<Ticket> resolvedTickets;
    
    private static ResolvedTicketStore instance;
    
    private ResolvedTicketStore() {
        resolvedTickets = new LinkedList<Ticket>();
    }
    
    public static ResolvedTicketStore getInstance(){
        if (instance == null) {
            instance = new ResolvedTicketStore();
        }
        return instance;
    }
    
    public void addTicket(Ticket t) {
        resolvedTickets.add(t);
    }
    
    public LinkedList<Ticket> getAll() {
        return resolvedTickets;
    }
    
}
