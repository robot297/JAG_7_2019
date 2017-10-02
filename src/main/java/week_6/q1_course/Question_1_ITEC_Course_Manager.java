package week_6.q1_course;

import java.util.ArrayList;

/**
 
 Part 1:
 
 Modify ITECCourse. Add a variable to store the classroom that the ITECCourse meets in.
 Should this variable be public, protected, or private?
 The variable's name should be 'classroom'
 Add get and set methods (setClassroom and getClassroom) for this variable.
 
 Add a second ITECCourse constructor so that there are two ways to create ITECCourse objects.
 The second constructor will take 4 arguments: the same 3 as the current constructor, plus a String
 to specify the classroom when you create an ITECCourse object. The classroom should be the 4th argument.
 
 Test your code by creating a ITECCourse object for this class:
 Name: Info Tech Concepts, code 1100, max students 30, classroom T3050.
 Enroll Miriam, and Nils, and Oprah.
 
 
 Part 2:
 
 Add a method to ITECCourse.java called freeSpace. This method will return the number of free spaces
 in the class.
 
 So if the max number of students in a class is 30, and there are 10 students enrolled,
 this method will return 20.
 
 Modify writeCourseInfo to include  the classroom and the free space.
 Print the classroom, if there is one. Or print a "no classroom" message if the ITECCourse does not have a classroom.
 
 
 Part 3:
 
 Modify ITECCourseManager testCourseManagerMethods() to add all of your ITECCourse objects to the
 allITECCourses ArrayList. You should have 4 (or more) ITECCourse objects.
 
 Finish the calculateTotalFree space method. It should add up all of the free space in all of the courses
 in allITECCourses. It will return the total free spaces, as an integer.
 
 
 Part 4:
 
 At the end of testCourseManagerMethods(), loop over all of the ITECCourse objects and
 print the name of each ITECCourse, and the number of free spaces in each ITECCourse.
 
 */

public class Question_1_ITEC_Course_Manager {
    
    public static void main(String[] args) {
        Question_1_ITEC_Course_Manager manager = new Question_1_ITEC_Course_Manager();
        manager.useCourseManagerMethods();
    }
    
    // Global variable to store all ITECCourse objects
    private ArrayList<ITECCourse> allITECCourses = new ArrayList<>();
    
    
    public void useCourseManagerMethods() {
    
        // Create an ITECCourse object called maintenanceCourse
        ITECCourse maintenanceCourse = new ITECCourse("Microcomputer Systems Maintenance", 1310, 20);
    
        //Add some students.
        maintenanceCourse.addStudent("Anna");
        maintenanceCourse.addStudent("Bill");
        maintenanceCourse.addStudent("Carl");
    
        //Carl decided to drop the class...
        maintenanceCourse.removeStudent("Carl");
    
        maintenanceCourse.writeCourseInfo();
    
        // Can also get individual variable values with getter methods
        System.out.println("Course name is + " + maintenanceCourse.getName());
        System.out.println("Course code is + " + maintenanceCourse.getCode());
    
        System.out.println("Max students in the ITECCourse is " + maintenanceCourse.getMaxStudents());
    
        //And can set variables, if set methods are provided
    
        //Let's increase the max number of students
        maintenanceCourse.setMaxStudents(24);
        System.out.println("The maximum number of students is now " + maintenanceCourse.getMaxStudents());
    
    
        // Another test object. This represents the Data Communications course
        ITECCourse datacomCourse = new ITECCourse("Data Communications", 1424, 30);
    
        datacomCourse.addStudent("Dora");
        datacomCourse.addStudent("Ed");
        datacomCourse.addStudent("Flora");
    
        datacomCourse.writeCourseInfo();
    
    
        //Test the add students method with an example class
        // This class has a max of 3 students
        ITECCourse smallCourse = new ITECCourse("Made up name small class", 1234, 3);
        smallCourse.addStudent("Jake");
        smallCourse.addStudent("Katherine");
        smallCourse.addStudent("Liam");
        //We shouldn't be able to add another student – what happens?
        smallCourse.addStudent("Marigold");
        
        
        // TODO Part 1 Create a new ITECCourse object with a classroom. Use your new constructor
        
        
        
        // TODO part 3/4 Add all of the ITECCourse objects to the allITECCourses ArrayList
        // Figure out the total number of free spaces in all the classes.
        int totalSpacesLeft = calculateTotalFreeSpace();
    
        System.out.println(
                String.format("There are %d spaces remaining in all the ITEC courses", totalSpacesLeft));
    
    
        // TODO part 4 Loop over all of the ITECCourse objects and call writeCourseInfo for each
        
    
    }
    
    
    public int calculateTotalFreeSpace() {
    
        // TODO part 3 loop over all the courses and add up all of the free spaces in all of the classes.
        
        return 0;  // TODO replace with your calculated value
    
    }
    
    
}

