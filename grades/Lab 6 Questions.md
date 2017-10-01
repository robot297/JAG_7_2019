# Lab 6

### Problem 1: ITEC Courses
 
 **Part 1:**
 
 Modify ITECCourse. Add a variable to store the room that the ITECCourse meets in.
 Should this variable be public, protected, or private?
 The variable's name should be 'room'
 Add get and set methods (setRoom and getRoom) for this variable.
 
 Add a second ITECCourse constructor so that there are two ways to create ITECCourse objects.
 The second constructor will take 4 arguments: the same 3 as the current constructor, plus a String
 to specify the room when you create an ITECCourse object. The room should be the 4th argument.
 
 Test your code by creating a ITECCourse object for this class:
 Name: Info Tech Concepts, code 1100, max students 30, room T3050.
 Enroll Miriam, and Nils, and Oprah.
 
 
 **Part 2:**
 
 Add a method to ITECCourse.java called freeSpace. This method will return the number of free spaces
 in the class.
 
 So if the max number of students in a class is 30, and there are 10 students enrolled,
 this method will return 20.
 
 Modify writeCourseInfo to print the room and the free space.
 Print the room, if there is one. Or print a "no room" message if the ITECCourse does not have a room.
 
 
 **Part 3:**
 
 Modify ITECCourseManager testCourseManagerMethods() to add all of your ITECCourse objects to the
 allITECCourses ArrayList. You should have 4 (or more) ITECCourse objects.
 
 Finish the calculateTotalFree space method. It should add up all of the free space in all of the courses
 in allITECCourses. It will return the total free spaces, as an integer.
 
 
 **Part 4:**
 
 At the end of testCourseManagerMethods(), loop over all of the ITECCourse objects and
 print the name of each ITECCourse, and the number of free spaces in each ITECCourse.
 


### Problem 2: Lakes

 This is the same question from Lab 4, but for this question, you'll refactor your code to
 use Lake objects to store the data.
 
 You are a runner, and you are in training for a race. You'd like to keep track of all of your
 times for your training runs. You only like to run around lakes. Here's some example data,
 
 
 ```
 Cedar, 45.15
 Cedar, 43.32
 Harriet, 49.34
 Harriet, 44.43
 Harriet, 46.22
 Como, 32.11
 Como, 28.14
 ```
 
 
 Write a program that enables you to enter the names of lakes and times, and store all of this
 data.
 
 You'll need to save every time entered for each lake.
 
 Create a Lake class to store all of the data for one lake. Create it in the same directory as this file.
 Add methods to this class to :
 
  - Adding a new time
  - Find and return the fastest time for this lake
  - Return a list of all of the times for that lake
 
 Save all of your Lake objects in a list or HashMap, so your program has a reference to every Lake.
 
 Your program should still work if you started running around another lake too (e.g. Lake of the Isles, or Lake Phalen).
 
 Your program should be able to analyze the data that you have stored, and print your fastest
 time for each lake you ran around. So, for the data above, your program will display something like
 
 ```
 Cedar, 43.32
 Harriet, 44.43
 Como, 32.11
 ```
 
 Your program should be case-insensitive. So "Como" is the same lake as "como" or "COMO".
 
 
 **You should write some tests for your Lake class. Finish the incomplete tests in
 In test/java/week_6/q2_lakes/Question_2_Lake_RunningTest.java.**
 
 


### Question 3: Coffee Shop

 This is the same question as week 5, but you'll refactor your program to
 use Drink objects.
 
 Write a program that creates a sales report for a coffee shop.
 The coffee shop will use this at the end of every day to calculate sales, expenses, and profit.
 
 The coffee shop sells 12 different drinks. The name of each drink, the price the shop
 charges the customer, and how much it costs to make each drink, are saved in the file
 coffee_price_data.txt. It's in the root directory of this project.
 
 The data is in the format
 
 `
 name;cost to make;price charged
 `
 
 As in this example,
 
 `
 Cappuccino;1.56;3.50
 `
 
 So the cappuccino drink costs the coffee shop $1.56 to make, and they charge the customer $3.50.
 
 The file coffee_sales_data.txt contains the sales data for one day. This file is in the format
 
 `
 name;number sold
 `
 
 As in this example,
 
 `
 Cappuccino;100
 `
 
 Which means that the coffee shop sold 100 cappuccino drinks.
 
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
 
 So, for example, if the coffee shop sold 100 cappuccinos today, you'll write a line that says
 
 `
 Cappuccino: Sold 100, Expenses $150.60, Revenue $350.00, Profit $190.40
 `
 
 perhaps using this String formatting template...
 
 `
 "%s: Sold %d, Expenses $%.2f, Revenue $%.2f, Profit $%.2f"
 `
 
 
 And a similar line for each of the drinks. The autograder is looking for this exact format.
 
 At the bottom of the file, write the total expenses, total revenue, and total profit for all drinks,
 for example, like this,
 
 `
 All Drinks: Total Sold 1000, Expenses $1000, Revenue $2500, Profit $1500
 `
 
 You should use try-with-resources exception handling for both file reading, and file writing.
 
 Use methods to organize your code. The autograder will call the salesReport() method, and will examine
 the output file your program creates.   The instructor will assess the quality of your code and solution.
 
 You should probably write some extra helper methods for the sub-tasks of this problem.
 
 You'll need to write your own unit tests for your Drink class methods.
 
 Test and comment your code.
 
 */