package week_6.q1_course;

import org.junit.Test;

import static org.junit.Assert.*;


public class Question_1_ITEC_Course_ManagerTest {
    
    


    
    
    @Test
    public void testAddITECCoursesToArrayList() throws Exception {
    
        Question_1_ITEC_Course_Manager q1 = new Question_1_ITEC_Course_Manager();
        q1.testCourseManagerMethods();
        assertTrue("Add 4 (or more) ITECCourse objects to the allITECCourses ArrayList. " +
                "\nAdd the 3 that are already created. Create another 1 (or more) ITECCourse objects and add that too.", q1.allITECCourses.size() >= 4);
        
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
        // Now there are 7 free spaces
        
        ITECCourse t3 = new ITECCourse("Test 3", 3456, 3);
        // There are 3 free spaces
        
        // Total free spaces 2 + 7 + 3 = 12
    
        q1.allITECCourses.add(t1);
        q1.allITECCourses.add(t2);
        q1.allITECCourses.add(t3);
        
        assertEquals("calculateTotalFreeSpace should add up the free space in all of your " +
                "ITECCourse objects in the allITECCourse ArrayList", q1.calculateTotalFreeSpace(), 12);
    
    }
    
    
    
}