# Lab 6

### Problem 1:

 Part 1 :
 
 Modify ITECCourse to add a variable to store the room that the course meets in.
 Should this variable be public, protected, or private?
 Add get and set methods for this variable.
 
 Modify ITECCourse's constructor so that you can specify the room when you create an ITECCourse object.
 Modify writeCourseInfo so that it also prints the room.
 
 Test your code by creating a ITECCourse object for this class:
 Name: Info Tech Concepts, code 1100, max students 30, room T3050.
 Enroll Miriam, and Nils, and Oprah.
 
 When you modify the constructor, the current code that uses the old constructor won't compile.
 what will you do with the other ITECCourse objects for which you don't know the room? (Hint: null is often used for unknown values)
 
 Part 2:
 
 Add a method to ITECCourse that returns the number of free spaces in the class.
 So if the max number of students in a class is 30, and there are 10 students enrolled,
 this method will return 20.

 
 Part 3:
 
 Modify ITECCourseManager testCourseManagerMethods() to add all of the ITECCourse objects to the
 allITECCourses ArrayList.
 
 At the end of testCourseManagerMethods(), loop over all of the ITECCourse objects and
 print the name of each course, and the number of free spaces in each course.
 


### Problem 2: Lakes

Start with the lakes running program from Lab 4. 

Modify this program to use objects to store the data. What class(es) will you create? Your classes and objects should improve your program's organization, conciseness, clarity and structure.




### Question 3: Coffee Shop


 Write a program that creates a sales report for a coffee shop.
 The coffee shop will use this at the end of every day to calculate sales, expenses, and profit.
 
 The coffee shop sells 12 different drinks. The name of each drink, the price the shop
 charges the customer, and how much it costs to make each drink, are saved in the file
 coffee_price_data.txt. It's in the root directory of this project.
 
 The data is in the format
 
 name;cost to make;price charged
 
 As in this example,
 
 Cappuccino;1.56;3.50
 
 So the cappuccino drink costs the coffee shop $1.56 to make, and they charge the customer $3.50.
 
 The file coffee_sales_data.txt contains the sales data for one day. This file is in the format
 
 name;number sold
 
 As in this example,
 
 Cappuccino;100
 
 The coffee shop sold 100 cappuccino drinks.
 
 
 Your program should read this data from coffee_price_data.txt, and coffee_sales_data.txt, and
 store it all in some kind of data structure.
 
 You should deal with any file-related exceptions properly.
 
 Once you have gathered all the data, generate a report that will be written out to a new file called
 daily_sales_report.txt. For each drink, record the number of drinks sold, the total that it cost to
 make the total quantity of those drinks (expenses), and the total amount (revenue) spent by
 customers on that drink.
 
 So, for example, if the coffee shop sold 100 cappuccinos today, you'll write a line that says
 
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
 
 Test and comment your code.