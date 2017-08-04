package week_6.course;

import java.util.ArrayList;

/**
 
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
 
 */

public class Question_1_ITEC_Course_Manager {
    
    public static void main(String[] args) {
        Question_1_ITEC_Course_Manager manager = new Question_1_ITEC_Course_Manager();
        manager.testCourseManagerMethods();
    }
    
    
    ArrayList<ITECCourse> allITECCourses = new ArrayList<ITECCourse>();
    
    
    public void testCourseManagerMethods() {
    
        //Much less code!
        ITECCourse maintenanceCourse = new ITECCourse("Microcomputer Systems Maintenance", 1310, 20, null);
    
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
    
        System.out.println("Max students in the course is " + maintenanceCourse.getMaxStudents());
    
        //And can set variables, if set methods are provided
    
        //Let's increase the max number of students
    
        maintenanceCourse.setMaxStudents(24);
        System.out.println("The maximum number of students is now " + maintenanceCourse.getMaxStudents());
    
    
        ITECCourse datacomCourse = new ITECCourse("Data Communications", 1424, 30, null);
    
        datacomCourse.addStudent("Dave");
        datacomCourse.addStudent("Ed");
        datacomCourse.addStudent("Flora");
    
        datacomCourse.writeCourseInfo();
    
    
        //Test the add students method with an example class
        // This class has a max of 3 students
        ITECCourse smallCourse = new ITECCourse("Made up name small class", 1234, 3, null);
        smallCourse.addStudent("Jake");
        smallCourse.addStudent("Kirby");
        smallCourse.addStudent("Liam");
        //We shouldn't be able to add another student – what happens?
        smallCourse.addStudent("Marigold");
        
        
        // TODO Part 1 Create a new ITECCourse object using the new constructor
        // TODO Part 1 modify the other ITECCourse objects to use the new constructor.
        
        
        // TODO part 3 Add all of the ITECCourse objects to the allITECCourses ArrayList
        // TODO part 3 loop over all the courses
        // and print the ITECourse name, and the number of free spaces in the ITECCourse.
    
    }
    
    
}

