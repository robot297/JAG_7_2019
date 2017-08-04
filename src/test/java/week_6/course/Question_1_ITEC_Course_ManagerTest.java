package week_6.course;

import org.junit.Test;
import org.powermock.reflect.exceptions.MethodNotFoundException;

import java.lang.reflect.*;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;


public class Question_1_ITEC_Course_ManagerTest {
    
    /*
    
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
        
    */
    
    @Test
    public void testCourseManagerMethods() throws Exception {
        
        Class courseMgr = Class.forName("week_6.course.ITECCourse");
        
        // Use reflection to check for the new constructor signature.
    
        Constructor[] constructors = courseMgr.getConstructors();
        assertEquals("Modify the existing constructor, don't add a new one or delete the existing on", constructors.length, 1);
        
        Constructor con = constructors[0];
        Class[] parameters = con.getParameterTypes();
        Parameter[] params = con.getParameters();
            
        assertEquals("Add a 4th argument to the constructor to represent the room the course is held in. The constructor should have 4 arguments.", parameters.length, 4);
        assertEquals("Add a 4th String argument to the constructor to represent the room that the course is held in.", parameters[3].getSimpleName(), "String");
        
        
        // Check for a get and set method for a room variable
        
        try {
            Method getRoom = courseMgr.getMethod("getRoom");
            // No parameters, returns a String
            assertEquals("getRoom should not take any arguments", getRoom.getParameterCount(), 0);
            assertEquals("getRoom should return a String", getRoom.getReturnType().getSimpleName(), "String");
        } catch (NoSuchMethodException ce) {
            fail("Add a getRoom() method to ITECCourse.java");
        }
    
        try {
            // setRoom should have on e String parameter, returns void
            Method setRoom = courseMgr.getMethod("setRoom", String.class);
            assertEquals("getRoom should return a String", setRoom.getReturnType(), void.class);
        } catch (NoSuchMethodException ce) {
            fail("Add a setRoom() method to ITECCourse.java which takes one String argument" + ce.toString());
        }
    
    
        // Check for a private room variable. Class variables aka fields.
        
        try {
            Field f = courseMgr.getDeclaredField("room");
            //assert f is private
            int modifiers = f.getModifiers();
            assertEquals("The room variable should be private", modifiers, Modifier.PRIVATE);
            
        } catch (NoSuchFieldException e) {
            fail("Add a private variable called 'room' to the ITECCourse class.");
        }
        
    }
    
    
  /*  Part 2:
    
    Add a method to ITECCourse that returns the number of free spaces in the class.
    So if the max number of students in a class is 30, and there are 10 students enrolled,
 this method will return 20.
    */
  
  /*
    
    Part 3:
    
    Modify ITECCourseManager testCourseManagerMethods() to add all of the ITECCourse objects to the
    allITECCourses ArrayList.
    
    At the end of testCourseManagerMethods(), loop over all of the ITECCourse objects and
    print the name of each course, and the number of free spaces in each course.
    */
  
  
    
}