package week_6.q3_coffee;

/**
 *
 *
 This is the same question as week 5, but you'll refactor your program to
 use Drink objects.
 
 Write a program that creates a sales report for a q3_coffee shop.
 The q3_coffee shop will use this at the end of every day to calculate sales, expenses, and profit.
 
 The q3_coffee shop sells 12 different drinks. The name of each drink, the price the shop
 charges the customer, and how much it costs to make each drink, are saved in the file
 coffee_price_data.txt. It's in the root directory of this project.
 
 The data is in the format
 
 name;cost to make;price charged
 
 As in this example,
 
 Cappuccino;1.56;3.50
 
 So the cappuccino drink costs the q3_coffee shop $1.56 to make, and they charge the customer $3.50.
 
 The file coffee_sales_data.txt contains the sales data for one day. This file is in the format
 
 name;number sold
 
 As in this example,
 
 Cappuccino;100
 
 The q3_coffee shop sold 100 cappuccino drinks.
 
 
 
 
 Your program should read this data from coffee_price_data.txt, and coffee_sales_data.txt, and
 store it all of the data for each drink in one Drink object. One Drink object
 should store that drink's name, price, number sold, cost to make one drink, price one drink sold for.
 
 A Drink object should be able to calculate the total expenses to make all the drinks of that type sold;
 the total price all of that drink of that type sold for, and profit for that type of drink.
 
 You should deal with any file-related exceptions properly.
 
 Once you have gathered all the data, generate a report that will be written out to a new file called
 daily_sales_report.txt. For each drink, record the number of drinks sold, the total that it cost to
 make the total quantity of those drinks (expenses), and the total amount (revenue) spent by
 customers on that drink.
 
 So, for example, if the q3_coffee shop sold 100 cappuccinos today, you'll write a line that says
 
 Cappuccino: Sold 100, Expenses $150.60, Revenue $350.00, Profit $190.40
 
 perhaps using this String formatting template...
 
 "%s: Sold %d, Expenses $%.2f, Revenue $%.2f, Profit $%.2f"
 
 
 And a similar line for each of the drinks. The autograder is looking for this exact format.
 
 At the bottom of the file, write the total expenses, total revenue, and total profit for all drinks,
 for example, like this,
 
 All Drinks: Total Sold 1000, Expenses $1000, Revenue $2500, Profit $1500
 
 You should use try-with-resources exception handling for both file reading, and file writing.
 
 Use methods to organize your code. The autograder will call the salesReport() method, and will examine
 the output file your program creates.   The instructor will assess the quality of your code and solution.
 
 You should probably write some extra helper methods for the subtasks of this problem.
 
 You'll need to write your own unit tests for your Drink class methods.
 
 Test and comment your code.
 
 */

public class Question_3_Coffee_Shop {
    
    public static void main(String[] args) {
        Question_3_Coffee_Shop q7 = new Question_3_Coffee_Shop();
        q7.salesReport();
    }
    
    
    
    public String price_data_file = "coffee_price_data.txt";
    public String sales_data_file = "coffee_sales_data.txt";
    
    public String output_report_file = "daily_sales_report.txt";
    
    public void salesReport() {
        
        // Suggested outline of program.
        
        // You may (and probably should) add more methods if necessary.
        
        Object allDrinkData = readCoffeeDataFiles(price_data_file, sales_data_file);  // TODO replace Object with the type of your data structure
        writeReportFile(allDrinkData, output_report_file);
        
    }
    
    
    public Object readCoffeeDataFiles(String dataFile, String salesFile) {
        
        // TODO read in the data from the files
        // TODO create a Drink object for each type of drink, containing all info about one drink
        // TODO put all the Drink objects into some type data structure, and return it.
        // TODO change the return type of this method to the type of your data structure.
        
        return null;
        
    }
    
    public void writeReportFile(Object drinkData, String filename) {
        
        // TODO finish this method.
        
        // You may find this format String helpful
        String reportLineTemplate = "%s: Sold %d, Expenses $%.2f, Revenue $%.2f, Profit $%.2f";
    }
    
    
}