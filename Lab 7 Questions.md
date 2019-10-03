# Lab 7

**For all questions: if a specific method or variable name is given, use it.**
 
 **Make sure you add any new Java file(s) to your git repository so they are uploaded to GitHub.
    Otherwise, I won't get all of your code, and your code won't compile for the autograder.**

 To add files to your repository, click 'Yes' when you create a new file and IntelliJ asks if you want to add it to your git repository.
 If you said no, or don't see the option - that's no problem. Just right-click on your new file and select Git > add. Commit and push to GitHub and verify you see the file there.
 

### Problem 1: Travel Wish List
 
A place object will need three private fields:
 *  A String name (for example, Hawaii)
 *  A String reason (a reason for visiting, for example, to go surfing)
 *  A Date created (when the Place object was created.

Create get and set methods for all three fields. Remember that
IntelliJ can generate these methods for you.

In this class, you'll need a constructor that takes two arguments, the
name, and the reason.

The constructor can set the Date created.

Place objects need to be sorted by name.
You'll need to make Place implement Comparable, so one Place can
be compared to another Place. This will allow a List of Place objects
to be sorted.

Add the `compareTo()` method that Comparable requires.

Place objects need to be printed. Create a `toString` method
that returns a String with all of the information about this place.
For the example data above, the String should be this EXACT form:

"Place to visit: Hawaii. Reason: to go surfing. Date created: Thu Oct 03 10:37:02 CDT 2019"

If the place name is "Rome" and the reason is "to visit the Colosseum" then
the String should be in this EXACT form:

"Place to visit: Rome. Reason: to visit the Colosseum. Date created: Thu Oct 03 10:42:19 CDT 2019"

The Date information will be whatever date's toString method returns. You don't need to do any
date formatting.
    
    
### Problem 2: Lakes

 This is the same question from Lab 4, but for this question, you'll refactor your code to use Lake objects to store the data.
 
 You are a runner, and you are in training for a race. You'd like to keep track of all of your times for your training runs. You only like to run around lakes. Here's some example data.
 For this program, we'll assume that these are decimal values of minutes, not minutes and seconds, and the math will be more straightforward. 
  
 ```
 Cedar, 45.15
 Cedar, 43.32
 Harriet, 49.34
 Harriet, 44.43
 Harriet, 46.22
 Como, 32.11
 Como, 28.14
 ```
 

Write a program that enables you to enter the names of lakes and times, and store all of this data.

You'll need to save every time entered for each lake.

Create a Lake class to store all of the data for one lake. Create it in the same directory as this file.
Add methods to this class to :

- Add a new time
- Find and return the fastest time for this lake
- Return a list of all of the times for that lake

Save all of your Lake objects in a list or HashMap, so your program has a reference to every Lake.

Your program should still work if you started running around another lake too (e.g. Lake of the Isles, or Lake Phalen).

Your program should be able to analyze the data that you have stored, and print your fastest
time for each lake you ran around. So, for the data above, your program will display something like

 ```
 Cedar, 43.32 minutes
 Harriet, 44.43 minutes
 Como, 28.14 minutes
 ```
 

Your program should also be able to analyze the data that you have stored, and print your average time for each lake you ran around. So, for the data above, your program will display something like this.
Again, we'll assume that these are decimal values of minutes, so you can figure out the regular average of the numbers. 
 
 
  
  ```
  Cedar, 44.24 minutes
  Harriet, 46.66 minutes
  Como, 30.13 minutes
  ```
  
 Your program should be case-insensitive. So "Como" is the same lake as "como" or "COMO".
 
 
 **Optional** You can write some tests for your Lake class. Finish the incomplete tests in
 In test/java/week_7/q2_lakes/Question_2_Lake_RunningTest.java. 
 

 
 
## Question 2 Support Ticket Manager 

This program is a prototype to manage IT support tickets for a company. Users would call or email a helpdesk to report computer problems, and this program keeps a record of all current problems. 

Tickets need to store a description of the problem, the date the issue was reported, and the user who reported it. 

The tickets are assigned a priority between 1-5.   
1 is the most urgent (e.g. all servers down)
5 is the least serious (e.g. missing mouse mat). 

Each ticket will have a unique integer ID. This is generated internally in the `Ticket` class. (We'll improve on this approach later in the semester when we cover databases.)

When a problem is fixed, the ticket is removed from the list of open tickets and added to a separate list of resolved tickets. A String describing the resolution is stored in the Ticket, and the date the ticket was resolved. 

For this question, you'll add some features to the program. 

Run and test the program with some example support tickets.

### Problem 1: Review Design

To think about: 
* What is each class for? How are different responsibilities divided between the classes? 

* If `TicketStore` used a database instead of an in-memory LinkedList, would `TicketUI` or `Question_3_Support_Ticket_Manager` have to do anything differently?

* Make sure you understand the role of the static and instance `ticketID` and `ticketIdCounter` variables in the Ticket class. Why are a static variable, and an instance variable, declared in the class? How are these used to create ID values for each ticket?

* How does Q3_Ticket_Manager access a TicketStore object? Is it possible to create more than one TicketStore object? Why do you think this is? Hint: Google the "Singleton design pattern." 


### Problem 2: Validate ticket priorities

Add a check to `TicketUI.getNewTicketInfo()` method, to ensure that the priority entered for a new ticket is between 1 and 5, inclusive. 


### Problem 3: New menu options

Add two new options to the menu: **Delete by Description**, and **Search by Description**.  You'll need to add some more int constants; and modify the `configureMenuOptions` method; and modify the switch statement in `manage`.


### Problem 4: Implement search by description

The Search By Description feature will search your ticket list and returns a new list of Tickets whose descriptions contain a certain String. For example, you might want to search for all tickets with the word “server” in the description.

The search should NOT be case sensitive.

Note that you should not modify the description when you save tickets. So, the approach of saving all descriptions in lowercase or uppercase is not an acceptable solution for this problem.
 
Implement `TicketStore.searchByDescription` to search the list and return all matching tickets. 

If `TicketStore.searchByDescription` doesn't find any matches, return an empty list.
If `TicketStore.searchByDescription` is called with an empty string, or a null string, it should return an empty list.

Implement Question_2_Support_Ticket_Manager.menuOptionSearchByDescription. 
Use the `askUserQuestion` method in TicketUI to ask the user for the search String.
Use `TicketStore.searchByDescription` to search for matching tickets
Use the `displayTickets` method in TicketUI to display all matching tickets. 


### Problem 5: Store information about deleted/resolved tickets

In your program, when a ticket is deleted, it has been resolved in some way. Either a technician fixed the problem, or the user has figured out how to change their own screensaver, or it’s become a non-issue in some other way.

Modify the program so you can save information about deleted tickets.

Add two new fields (variables) to the Ticket class.

Another Date; `resolvedDate`, the date the ticket was closed.
And, a String that documents why the ticket was closed – the fix or the resolution for the ticket. This String should be called `resolution`


### Problem 6: Implement delete by ID

A ResolvedTicketStore class is provided for you. It stores resolved tickets in a list, and provides an `addTicket` method. 

Create a ResolvedTicketStore object in Question_2_Ticket_Manager when the program starts. Note that you'll need to use the `ResolveTicketStore.getInstance()` method to access the singleton `resolvedTickets` object. 

Now, when you delete a Ticket, your `deleteTicketById` method should ask for the resolution. Store the resolution String, plus the current Date in the correct Ticket. Now, remove this Ticket from the ticketQueue list.

Finally, add the resolved ticket to your ResolvedTicketStore.


### Problem 7: Implement delete by description

Implement Delete by Description. As before, you'll need to ask for the resolution, save the resolution and date resolved in the Ticket, remove the ticket from the TicketStore, and add it to the ResolvedTicketStore.
 
Follow the notes in `deleteTicketByDescription`.


### Problem 8: Save ticket information to file

At the moment, when the program is closed, all the data is lost.  Add the ability to save all data to files.  You will need to save your files in a specific location, and using specific file names. But, you can decide how to organize and structure the data in your files. 

Use `TicketFileIO` to manage the file input and output. Question_3_Support_Ticket_Manager will use methods in this class when the program starts and ends.  

Your `Question_2_Support_Ticket_Manager` should **not** do any file reading or writing. Delegate these tasks to your TicketFileIO class.

Write all files to the directory given by the `ticketDataDirectory` in TicketFileIO.

When the program closes, write out all the data about all open tickets to one file in the **TicketData** directory of your project.
These open tickets should be saved in a file called `open_tickets.txt`.

Write all data about resolved tickets to a separate file.
Resolved tickets should go into one file per day.
This file should have the current date in the filename. 
Example, if the resolved tickets are saved on February 20, 2014, the filename will be `resolved_tickets_as_of_February_20_2014.txt`.
If the program is run more than once on a given day, append any more resolved tickets to the same file. For example, if the program is run on February 20, 2014, and some tickets are resolved, they should be saved to `resolved_tickets_as_of_February_20_2014.txt`. Then, if the program is run again on the same day, and more tickets are resolved, these tickets should be appended to the same `resolved_tickets_as_of_February_20_2014.txt` file. 


Create a static method with this name, and arguments. This method should be used for writing open tickets, and resolved tickets.
 
```
    /* Write the list of tickets to the file with the name provided.
    The append argument specifies what to do if the file already exists. 
    If append=true, add data to the file
    If append=false, create a new file for this data. */
    
    public static void saveTickets(LinkedList<Ticket> ticketList, String fileName, boolean append) {
        // TODO implement this method
    }
    
```
    

Here's some code to generate today's date and time as a string in the correct format,

    
```
SimpleDateFormat filenameFormatter = new SimpleDateFormat("MMMM_dd_yyyy_hh");
Date date = new Date();   //defaults to today, right now
String s = filenameFormatter.format(date);  // s will be in the format "September_28_2017"
        
```

How will you deal with Dates? Here's two approaches. What are the pros and cons of each? 

1. Either, convert the Date to a timestamp. A timestamp is a long integer number, the number of seconds since Midnight on Jan 1, 1970. It's much easier to deal with numerical timestamps that human-readable dates.  
2. Or, use SimpleDateFormat to convert a Date to a String and back again


Example, converting dates to timestamps and back again: 

```
Date d = new Date();
long timestamp = d.getTime();
// Write the timestamp to a file 


// if you start with a timestamp from a file 
long ts = // get from file 
Date fromtimestamp = new Date(ts);
// Use date to create new Ticket 

```


Or, you can use a SimpleDateFormat to convert strings like "03/20/19 20:28:31" to a Date object representing March 20, 2019 at 8.28.31pm. And, to convert Dates to Strings.  Example, Date to String
You can modify the date pattern `"MM-dd-yyyy HH:mm:ss"` to anything you like, so long as it represents the date and time. 

```
SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
Date now = new Date();
String dateString = formatter.format(now);
System.out.println(dateString);    // Example: "03/20/2019 20:34:17"  March 20, 2019, at 8:28:31 pm
```

Example, String to Date

```
SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

String wednesdayEveningString = "03-20-2019 21:17:05";
try {
    Date wednesdayDate = formatter.parse(wednesdayEveningString);
    System.out.println(wednesdayDate);
}
catch (ParseException e) {
    System.out.println("error" + e);
}
```

### Problem 9: Read open tickets when program starts 

When your program opens, it should look for a file called `TicketData/open_tickets.txt`. If this file exists, read in this file, and create Ticket objects, and store these in the TicketStore object list so the user can see all open tickets.

Use a static method with this name and arguments.

```
    /** Read a file, turn the data into Ticket objects, and return a list of Ticket objects */
    public static LinkedList<Ticket> loadTickets(String fileName) {
       // TODO implement this method and return a LinkedList of Tickets from the file of the given name
    }
``` 
    
You don't need to read in the previous resolved tickets, you only need to read the open tickets from the `open_tickets.txt` file. The ResolveTicketStore object will have an empty list when the program starts.

What happens to ticket IDs when the program is closed and opened? Make sure they don't reset to 1 when the user restarts the program. Every ticket created should always have a unique positive integer ID, (excluding 0) no matter how many times the program is used. 

If you save the ticket ID in a file, make sure it's also in the the **TicketData** directory.

The tests will create a separate test directory and will read and write to this location, so if you keep all of your files in **TicketData** then the tests won't overwrite your files.

You will need to create a second constructor for creating a tickets when the ID is already known. Make sure you don't break your mechanism for ensuring unique IDs. 

*Actually, you'll only be able to create approx 2 billion ticket IDs with this approach. That should be enough for now, although perhaps something that will be revisited in a future version using a relational database. 

