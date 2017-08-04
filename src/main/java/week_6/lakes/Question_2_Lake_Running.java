package week_6.lakes;

import static input.InputUtils.*;

/**
 *
 *
 This is the same question from Lab 4, but you'll refactor your code to use Lake objects to store the data.
 
 You are a runner, and you are in training for a race. You'd like to keep track of all of your
 times for your training runs. You only like to run around lakes. Here's some example data,
 Cedar, 45.15
 Cedar, 43.32
 Harriet, 49.34
 Harriet, 44.43
 Harriet, 46.22
 Como, 32.11
 Como, 28.14
 
 Write a program that enables you to enter the names of lakes and times, and store all of this
 data.
 
 You'll need to save every time entered for each lake.
 
 Create a Lake class to store all of the data for one lake. Create it in the same directory as this file.
 Add methods to this class to :
  - Adding a new time
  - Find and return the fastest time for this lake
  - Return a list of all of the times for that lake
 
 Save all of your Lake objects in a list or HashMap, so your program has a reference to every Lake.
 
 Your program should still work if you started running
 around another lake too (e.g. Lake of the Isles, or Lake Phalen).
 
 Your program should be able to analyze the data that you have stored, and print your fastest
 time for each lake you ran around. So, for the data above, your program will display something like
 
 Cedar, 43.32
 Harriet, 44.43
 Como, 32.11
 
 Your program should be case-insensitive. So "Como" is the same lake as "como" or "COMO".
 
 
 You should write some tests for your Lake class. In test/java/week_6/lakes/Question_2_Lake_RunningTest.java, finish
 
 
 */
public class Question_2_Lake_Running {
    
    // TODO create a global data structure to store all of your Lake objects.
    // You methods will read and write to the lakes in this data structure.
    
    
    public static void main(String[] args) {
        new Question_2_Lake_Running().running();
    }
    
    public void running() {
        
        while (moreLakes())  {
            String name = stringInput("Enter lake name");
            double time = doubleInput("Enter time for running lake " + name);
            addLake(name, time);
        }
        
        printFastestTimeForAllLakes();
        
    }
    
    
    
    
    public void printFastestTimeForAllLakes() {
        // TODO read data from your data structure, and print the fastest time for each lake.
        // Your output should look something like this
        
        // Lake Harriet, 50.12 minutes
        // Como Lake, 45.12 minutes
        // ....
        // Use the fastestTimeForLake method to help.
        
        
    }
    
    
    public double fastestTimeForLake(String lakeName) {
        // TODO read data from your Lakes data structure, and find the fastest time for the lake of this name.
        
        // Your lake name should not be case sensitive. "Como" is the same as "como".
        
        // If the lake is not in your data structure, return -1 to indicate it was not found.
        
        return 0; // replace with your code
        
    }
    
    
    public void addLake(String name, double time) {
        // TODO Create a new Lake object, and add to your data structure
        // Your lake name should not be case sensitive. "Como" is the same as "como".
    }
    
    
    
    
    // You don't need to modify this method.
    private boolean moreLakes() {
        return yesNoInput("More lake data to add?");
    }
    
    
}
