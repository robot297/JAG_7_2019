package week_6.q2_lakes;

import static input.InputUtils.*;

/**
 
 This is the same question from Lab 4, but for this question, you'll refactor your code to
 use Lake objects to store the data.
 
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
 
  - Add a new time
  - Find and return the fastest time for this lake
  - Return a list of all of the times for that lake
 
 Save all of your Lake objects in a list or HashMap, so your program has a reference to every Lake.
 
 Your program should still work if you started running around another lake too (e.g. Lake of the Isles, or Lake Phalen).
 
 Your program should be able to analyze the data that you have stored, and print your fastest
 time for each lake you ran around. So, for the data above, your program will display something like
 
 Cedar, 43.32 minutes
 Harriet, 44.43 minutes
 Como, 28.14 minutes
 
 
 Your program should also be able to analyze the data that you have stored, and print your average
 time for each lake you ran around. So, for the data above, your program will display something like
 
 Cedar, 43.74 minutes
 Harriet, 44.43 minutes
 Como, 28.14 minutes
 
 Your program should be case-insensitive. So "Como" is the same lake as "como" or "COMO".
 
 
 Optional: You can write some tests for your Lake class. Finish the incomplete tests in
 In test/java/week_6/q2_lakes/Question_2_Lake_RunningTest.java
 
 Make sure you add your new java file(s) to your git repository so they are uploaded to GitHub.
 Otherwise, I won't get all of your code, and your code won't compile for the autograder.

 */


public class Question_2_Lake_Running {
    
    // TODO create a global data structure to store all of your Lake objects.
    // You methods will read and write to the Lakes in this data structure.
    
    
    public static void main(String[] args) {
        new Question_2_Lake_Running().running();
    }
    
    public void running() {
        
        // Get data from user
        do {
            String lakeName = stringInput("Enter lake name");
            double runTime = doubleInput("Enter time for running lake " + lakeName);
            addRunTime(lakeName, runTime);
        } while (moreLakes());
    
        // Display fastest time for all lakes
        printFastestTimeForAllLakes();
    
        // Display average time for all lakes
        printAverageTimeForAllLakes();
    
        
        // Search for the fastest time for a Lake
        String searchFastest = stringInput("Enter a lake name to search for the fastest time");
        double fastest = fastestTimeForLake(searchFastest);
        System.out.println(String.format("The fastest time for %s is %f minutes", searchFastest, fastest));
    
    
        // Search for the average time for a Lake
        String searchAverage = stringInput("Enter a lake name to calculate for the average time");
        double average = averageTimeForLake(searchAverage);
        System.out.println(String.format("The fastest time for %s is %f minutes", searchAverage, average));
    
    
    }
    
    
    
    
    public void printFastestTimeForAllLakes() {
        // TODO read data from your data structure, and print the fastest time for each lake.
        // Your output should look something like this
        
        // Harriet, 44.43 minutes
        // Como, 28.14 minutes
        // ....
        
        
    }
    
    
    public void printAverageTimeForAllLakes() {
        // TODO read data from your data structure, and print the average time for each lake.
        // Your output should look something like this
        
        // Harriet, 46.67 minutes
        // Como 30.12 minutes
        // ....
        
        
    }
    
    public double fastestTimeForLake(String lakeName) {
        // TODO find the lake with this name in your Lakes data structure, and use the method defined
        // in your Lake class to get the fastest time for the lake.
        
        // Your lake name should not be case sensitive. "Como" and "COMO" and "como" are all the same lake.
        
        // If the lake is not in your data structure, return -1 to indicate it was not found.
        
        return 0; // replace with your code
        
    }
    
    public double averageTimeForLake(String lakeName) {
        
        // todo implement this
        
        return 0;
        
    }
    
    
    public void addRunTime(String lakeName, double time) {
        // TODO
        // If the lake is already in your data structure, add this time to the lake.
        // If not, create a new Lake object, and add to your data structure
        // Your lake name should not be case sensitive. "Como" and "COMO" and "como" are all the same lake.
        
    }
    
    
    
    
    // You don't need to modify this method.
    private boolean moreLakes() {
        return yesNoInput("More lake data to add?");
    }
    
    
}
