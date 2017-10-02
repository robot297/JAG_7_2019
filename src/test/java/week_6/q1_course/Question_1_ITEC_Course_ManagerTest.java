package week_6.q1_course;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class Question_1_ITEC_Course_ManagerTest {
    
    @Test
    public void testAddITECCoursesToArrayList() throws Exception {
        
        Question_1_ITEC_Course_Manager q1 = new Question_1_ITEC_Course_Manager();
        q1.useCourseManagerMethods();
        
        // Get private allITECourses variable.
        // So private variables are normally private, but you can still get and set them using reflection.
        // this is not recommended, with some exceptions e.g. for testing. Reflection can be slow and error-prone.
        // It's a good idea to respect the access modifiers!!
        Class mgr = Class.forName("week_6.q1_course.Question_1_ITEC_Course_Manager");
        Field all = mgr.getDeclaredField("allITECCourses");
        all.setAccessible(true);   // Since it's private, have to request that we can access it.
        ArrayList<ITECCourse> expectedCourses  = (ArrayList<ITECCourse>) all.get(q1);
        
        
        assertTrue("Add 4 (or more) ITECCourse objects to the allITECCourses ArrayList. " +
                "\nAdd the 3 that are already created. Create another 1 (or more) ITECCourse objects and add that too.", expectedCourses.size() >= 4);
        
    }
    
    @Test
    public void testCalculateTotalFreeSpace() throws Exception {
    
        Question_1_ITEC_Course_Manager q1 = new Question_1_ITEC_Course_Manager();
        
        // Example classes...
        ITECCourse t1 = new ITECCourse("Test 1", 1234, 5);
        t1.addStudent("t1");
        t1.addStudent("t1");
        t1.addStudent("t1");
        // Now there are 2 free spaces
        
        ITECCourse t2 = new ITECCourse("Test 2", 2345, 10);
        t2.addStudent("t2");
        t2.addStudent("t2");
        t2.addStudent("t2");
        t2.addStudent("t2");
        // Now there are 6 free spaces
        
        ITECCourse t3 = new ITECCourse("Test 3", 3456, 3);
        // There are 3 free spaces
        
        // Total free spaces 2 + 6 + 3 = 11
        int expectedSpace = 2 + 6 + 3;
    
        Class mgr = Class.forName("week_6.q1_course.Question_1_ITEC_Course_Manager");
        Field allField = mgr.getDeclaredField("allITECCourses");
        allField.setAccessible(true);
        ArrayList<ITECCourse> expectedCourses  = (ArrayList<ITECCourse>) allField.get(q1);
    
        expectedCourses.add(t1);
        expectedCourses.add(t2);
        expectedCourses.add(t3);
        
        allField.set(q1, expectedCourses);
        
        assertEquals("calculateTotalFreeSpace should add up the free space in all of your " +
                "ITECCourse objects in the allITECCourse ArrayList", q1.calculateTotalFreeSpace(), expectedSpace);
    
    }
    
    
    
}