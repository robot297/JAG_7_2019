package week_7.q2_ticket;

import java.util.LinkedList;

/** The instruction are in the Lab 7 Questions.md file  */

public class TicketManager {

    public static void main(String[] args) {
        new TicketManager().manage();
    }

    // Global objects - the data stores, and the user interface
    private TicketUI ticketUI = new TicketUI();
    private TicketStore ticketStore = TicketStore.getInstance();
    
    // TODO Problem 6: create a ResolvedTicketStore object here


    void manage() {
        
        boolean quit = false;

        while (!quit) {

            int userChoice = ticketUI.showMenuGetChoice();

            // TODO Problem 3: add the two new options to this switch statement
            
            switch (userChoice) {
                case TicketUI.ADD_TICKET:
                    menuOptionAddTicket();
                    break;
                case TicketUI.SEARCH_BY_ID:
                    menuOptionSearchById();
                    break;
                case TicketUI.DELETE_BY_TICKET_ID:
                    menuOptionDeleteById();
                    break;
                case TicketUI.SHOW_NEXT_TICKET:
                    menuOptionShowNextTicket();
                    break;
                case TicketUI.SHOW_ALL_TICKETS:
                    menuOptionDisplayAllTickets();
                    break;
                case TicketUI.QUIT:
                    quit = true;
            }
        }
    }
    
    
    private void menuOptionAddTicket() {
        
        // Get ticket data. Use ticketUI method to ask for data.
        Ticket newTicket = ticketUI.getNewTicketInfo();
        
        // Add to the ticket store
        ticketStore.add(newTicket);
        ticketUI.userMessage("Ticket added to the ticket queue");
    }


    protected void menuOptionSearchById() {

        int ticketID = ticketUI.getTicketID();
        Ticket ticket = ticketStore.getTicketById(ticketID);
        if (ticket == null) {
            ticketUI.userMessage("No ticket found with that ID");
        } else {
            ticketUI.displayTicket(ticket);
        }
    }

    /* Ask for ticket ID, call deleteTicketByID to find ticket, confirm delete operation, and delete */
    protected void menuOptionDeleteById() {
        int ticketID = ticketUI.getTicketID();
        deleteTicketById(ticketID);
    }


    /* Display next ticket in the queue - the highest priority*/
    protected void menuOptionShowNextTicket() {
        Ticket next = ticketStore.peekNextTicket();
        ticketUI.displayTicket(next);
    }


    /* Show all tickets, in priority order */
    protected void menuOptionDisplayAllTickets() {
        LinkedList<Ticket> allTickets = ticketStore.getAllTickets();
        ticketUI.displayTickets(allTickets);
    }

    
    /* Ask user for a search term, display all tickets matching that term */
    protected void menuOptionSearchByDescription() {

        // TODO Problem 3: implement this method.

        // Use TicketUI getSearchTerm method to ask user for search term e.g. "server" or "powerpoint"
        // Create a method in TicketStore to get list of matching Tickets for a search term;
        //      this method should return a list of all tickets which contain the user's
        //      search term in their description
        // Use TicketUI displayTickets method to print the list of matching tickets

    }


    /* Ask user for a search term, display all matching tickets, ask for ID of ticket to delete */
    protected void menuOptionDeleteTicketByDescription() {

        // TODO Problem 7: implement this method.
        // Ask user for search term e.g. "server". Use TicketUI to ask the question.

        // If there are matching tickets, use TicketUI to ask user which ticket ID to delete;
        // call deleteTicketById(ticketID) to delete the ticket.
        // Optional: give the user the choice to quit the delete process by entering -1

        // else, use TicketUI to show user 'not found' message

    }

    
    /* This method will be called by menuOptionDeleteTicketByDescription and menuOptionDeleteById */
    protected void deleteTicketById(int ticketID) {

        // Fetch the ticket
        Ticket toDelete = ticketStore.getTicketById(ticketID);

        // Check if a ticket with this ID is in the store
        if (toDelete == null) {
            ticketUI.userMessage("Ticket not found in the ticket store.");
        } else {
            // Display ticket info, and verify delete operation
            ticketUI.displayTicket(toDelete);
            if (ticketUI.areYouSure("Delete the above ticket, are you sure?")) {

                /// TODO Problem 6: use TicketUI to get the resolution for this Ticket
                // Save the resolution and the current date in this Ticket
                // add it to the ResolvedTicketStore object.

                // Delete ticket from store.
                ticketStore.deleteTicketById(ticketID);
            }
        }
    }

}
