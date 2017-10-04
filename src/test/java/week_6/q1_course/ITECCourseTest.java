package week_6.q1_course;

import org.junit.Test;
import test_utils.ArrayListUtils;
import test_utils.MethodUtil;
import test_utils.PrintUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ITECCourseTest {
  
    // Several of these tests should currently pass, they are for the existing code.
    
    @Test
    public void addStudent() throws Exception {
        
        String n1 = "test1", n2 = "test2", n3 = "test3";
        
        ITECCourse test = new ITECCourse("Test", 1234, 3);
        test.addStudent(n1);
        assertEquals(test.getNumberOfStudents(), 1);
        test.addStudent(n2);
        assertEquals(test.getNumberOfStudents(), 2);
        test.addStudent(n3);
        assertEquals(test.getNumberOfStudents(), 3);
        ArrayList<String> students = ArrayListUtils.newStringArrayList(n1, n2, n3);
        assertTrue(ArrayListUtils.stringArrayListEqual(students, test.getStudents()));
        
        // No classroom at all!
        ITECCourse test2 = new ITECCourse("Test", 1234, 0);
        test2.addStudent(n1);
        assertEquals(test2.getNumberOfStudents(), 0);  // Not added
        assertTrue(test2.getStudents().isEmpty());
    
    
        ITECCourse test3 = new ITECCourse("Test", 1234, 1);
        test3.addStudent(n1);
        assertEquals(test3.getNumberOfStudents(), 1);
        test3.addStudent(n2);   // should not be added
        assertEquals(test3.getNumberOfStudents(), 1);
        students = ArrayListUtils.newStringArrayList(n1);
        assertTrue(ArrayListUtils.stringArrayListEqual(students, test3.getStudents()));
    
    
    }
    
    @Test
    public void removeStudent() throws Exception {
    
        String n1 = "test1", n2 = "test2", n3 = "test3";
    
        ITECCourse test = new ITECCourse("Test", 1234, 3);
        test.addStudent(n1);
        test.addStudent(n2);
        test.addStudent(n3);
        
        test.removeStudent(n2);
        
        ArrayList<String> students = ArrayListUtils.newStringArrayList(n1, n3);
        assertTrue(ArrayListUtils.stringArrayListEqual(students, test.getStudents()));
    
        
        test.removeStudent("doesn't exist");
        assertTrue(ArrayListUtils.stringArrayListEqual(students, test.getStudents()));
        
        
        // Test remove from empty class list, no changes made
    
        ITECCourse test2 = new ITECCourse("Test2", 1234, 3);
        test2.removeStudent("example");
        assertTrue(test2.getStudents().isEmpty());
        
    
    }
    
    @Test
    public void writeCourseInfo() throws Exception {
        
        PrintUtils.catchStandardOut();
        
        ITECCourse test = new ITECCourse("Test Class", 9999, 30);
        test.addStudent("example1");
        test.addStudent("example2");
        
        String[] expectedStrings = {"Test Class", "9999", "30", "28", "example1", "example2"};
        
        test.writeCourseInfo();
        
        
        String out = PrintUtils.resetStandardOut();
        
        for (String ex : expectedStrings) {
            assertTrue(out + " should contain + " + ex, out.contains(ex));
        }
        
        
        Class courseMgr = Class.forName("week_6.q1_course.ITECCourse");
        Constructor[] constructors = courseMgr.getConstructors();
        for (Constructor c : constructors) {
            if (c.getParameters().length == 4) {
                //the new 4-arg constructor
                //Make an object
                ITECCourse course = (ITECCourse) c.newInstance("Test Class", 9999, 30, "T-3050");
                course.addStudent("example1");
                course.addStudent("example2");
                
                PrintUtils.catchStandardOut();
                course.writeCourseInfo();
                String out2 = PrintUtils.resetStandardOut();
                
                for (String ex : expectedStrings) {
                    assertTrue(out2 + " should contain + " + ex, out.contains(ex));   //Same data as previous example
                }
                assertTrue(out2 + " should contain T-3050", out2.contains("T-3050"));  // Plus the classroom
                
            }
        }
    }
    
    
    
    @Test
    public void testFreeSpace() throws Exception {
        
        // Find the freeSpace method. No args, return int.
        
        Method freeSpace = MethodUtil.findMethod("week_6.q1_course.ITECCourse", "freeSpace", new Class[]{});
        
        
        ITECCourse test = new ITECCourse("test name", 1234, 10);
        
        try {
            int free = (int) freeSpace.invoke(test);
            assertEquals("When a class with max size of 10 is created, and before students are added, the free space method should return 10", free, 10);
            
            // Add some students
            
            test.addStudent("test1");
            test.addStudent("test3");
            test.addStudent("test3");
            
            // Students have taken 3 of the 10 spaces, freeSpace should return 7
            free = (int) freeSpace.invoke(test);
            assertEquals("After adding 3 students to a class with max size of 10, freeSpace should return 7", free, 7);
            
        } catch (ClassCastException cce) {
            fail("The freeSpace method must return an int");
        }
    }
    
    
    
    @Test
    public void testITECCourseManagerConstructors() throws Exception {
        
        Class courseMgr = Class.forName("week_6.q1_course.ITECCourse");
        
        // Use reflection to check for the new constructor signature.
        
        Constructor[] constructors = courseMgr.getConstructors();
        assertEquals("Create a new constructor. Don't delete the existing one. ITECCourse should have two constructors", constructors.length, 2);
        
        Constructor con = constructors[0];
        Constructor con2 = constructors[1];
        
        Class[] parameters = con.getParameterTypes();
        Class[] parameters2 = con2.getParameterTypes();
    
        // This is kinda awkward. Look into refactoring.
        if (parameters.length == 4) {
            // This seems to be the new constructor, Make sure the 4th argument is a String
            assertEquals("Add a 4th String argument to the constructor to represent the classroom that the q1_course is held in.", parameters[3].getSimpleName(), "String");
        
            // And the other constructor's parameter list will be 3 elements
            assertEquals("Don't modify the existing constructor's parameters", parameters2.length, 3);
    
            con.newInstance("Test", 1000, 30, "T3000"); //This should not error

        } else if (parameters2.length == 4) {
            // This one is the new constructor. Same checks.
            // This seems to be the new constructor, Make sure the 4th argument is a String
            assertEquals("Add a 4th String argument to the constructor to represent the classroom that the q1_course is held in.", parameters2[3].getSimpleName(), "String");
        
            // And the other constructor's parameter list will be 3 elements
            assertEquals("Don't modify the existing constructor's parameters", parameters.length, 3);
        
            con2.newInstance("Test", 1000, 30, "T3000"); //This should not error
            
        } else {
            // no 4-argument constructor
            assertEquals("Add a 4th argument to the constructor to represent the classroom the q1_course is held in. The constructor should have 4 arguments.", parameters.length, 4);
        }
        
        
        
    }
    
    @Test
    public void testITECCourseManagerGetSet() throws Exception {
        
        // Check for a private classroom variable, and get and set methods variable
        
        Class courseMgr = Class.forName("week_6.q1_course.ITECCourse");
        
        
        Method getclassroom = null, setclassroom = null;
        Field classroom = null;
        try {
            getclassroom = courseMgr.getMethod("getClassroom");
            // No parameters, returns a String
            assertEquals("getclassroom should not take any arguments", getclassroom.getParameterCount(), 0);
            assertEquals("getclassroom should return a String", getclassroom.getReturnType().getSimpleName(), "String");
        } catch (NoSuchMethodException ce) {
            fail("Add a getclassroom() method to ITECCourse.java");
        }
        
        try {
            // setclassroom should have one String parameter, returns void
            setclassroom = courseMgr.getMethod("setClassroom", String.class);
            assertEquals("getClassroom should return a String", setclassroom.getReturnType(), void.class);
        } catch (NoSuchMethodException ce) {
            fail("Add a setClassroom() method to ITECCourse.java which takes one String argument");
        }
        
        
        // Check for a private classroom variable. Class variables aka fields.
        
        try {
            classroom = courseMgr.getDeclaredField("classroom");
            //assert f is private
            int modifiers = classroom.getModifiers();
            assertEquals("The classroom variable should be private", modifiers, Modifier.PRIVATE);
            
        } catch (NoSuchFieldException e) {
            fail("Add a private variable called 'classroom' to the ITECCourse class.");
        }
        
        
        // Try using these methods
        
        ITECCourse course = new ITECCourse("Test", 1234, 30);
        setclassroom.invoke(course, "T-3050");
    
        classroom.setAccessible(true);
    
        // Correct value stored?
        assertEquals(classroom.get(course), "T-3050");
    
        // Right value returned?
        assertEquals(getclassroom.invoke(course), "T-3050");
        
        
    }
    
    
}