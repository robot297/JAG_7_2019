package week_6.q2_lakes;

import org.junit.Test;
import test_utils.PrintUtils;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by Clara on 8/4/17.
 */
public class Question_2_Lake_RunningTest {
    
    double delta = 0.000001;
    
    
    @Test
    public void testPrintFastestTimeForAllLakes() throws Exception {
        
        Question_2_Lake_Running q5 = new Question_2_Lake_Running();
        
        q5.addRunTime("Como", 5);
        q5.addRunTime("Como", 6);
        q5.addRunTime("CoMo", 3);
        
        q5.addRunTime("Harriet", 5);
        q5.addRunTime("HaRRiet", 16);
        
        
        q5.addRunTime("Superior", 45345);
        q5.addRunTime("Superior", 1121226);
        
        PrintUtils.catchStandardOut();
        
        q5.printFastestTimeForAllLakes();
        
        String out = PrintUtils.resetStandardOut();
        out = out.replace("\n", " ").toLowerCase();
        out = out.replace("\r", " ").toLowerCase();
        

        /* Check that out contains
         como .... 3 ... harriet ... 5 ... superior ... 45345
         In any order
        */
        
        assertTrue(Pattern.matches(".*como.*3.*", out));
        assertTrue(Pattern.matches(".*harriet.*5.*", out));
        assertTrue(Pattern.matches(".*superior.*45345.*", out));
        
        
    }
    
    @Test
    public void testFastestTimeForLake() throws Exception {
        
        
        Question_2_Lake_Running q2 = new Question_2_Lake_Running();
        
        
        q2.addRunTime("Lake Como", 5);
        q2.addRunTime("Lake Como", 6);
        q2.addRunTime("Lake Como", 3);
        
        q2.addRunTime("Harriet", 5);
        q2.addRunTime("Harriet", 16);
        
        
        q2.addRunTime("Superior", 45345);
        q2.addRunTime("Superior", 1121226);
        
        double delta = 0.000001;
        
        assertEquals("If the times for Lake Como are 5, 6, 3, then 3 should be the fastest. \nThe lake name should not be case sensitive.", 3, q2.fastestTimeForLake("Lake Como"), delta);
        assertEquals("If the times for Lake Como are 5, 6, 3, then 3 should be the fastest. \nThe lake name should not be case sensitive.", 3, q2.fastestTimeForLake("lake como"), delta);
        
        assertEquals("If the times for Harriet are 5, 16, the fastest should be 5. \nThe lake name should not be case sensitive.", 5, q2.fastestTimeForLake("Harriet"), delta);
        assertEquals("If the times for Harriet are 5, 16, the fastest should be 5. \nThe lake name should not be case sensitive.", 5, q2.fastestTimeForLake("HARrIET"), delta);
        
        assertEquals("If the times for Superior are 45345 and 1121226 then 45345 should be fastest. \nThe lake name should not be case sensitive.", 45345, q2.fastestTimeForLake("SUPERIOR"), delta);
        assertEquals("If the times for Superior are 45345 and 1121226 then 45345 should be fastest. \nThe lake name should not be case sensitive.", 45345, q2.fastestTimeForLake("SuPeRiOr"), delta);
        
        assertEquals("This fastest time method should return -1 if a lake is not found", -1, q2.fastestTimeForLake("Not There"), delta);
        
    }
    
    
    @Test
    public void testAverageTimeForLake() throws Exception {
        
        
        Question_2_Lake_Running q2 = new Question_2_Lake_Running();
        
        
        q2.addRunTime("Como", 5);
        q2.addRunTime("Como", 8);
        q2.addRunTime("CoMo", 2);   // average 5
        
        q2.addRunTime("Harriet", 5);
        q2.addRunTime("HaRRiet", 17);  // Average 11
        
        q2.addRunTime("Superior", 45345);
        q2.addRunTime("Superior", 1121229);  // Average 583287
        
        
        double delta = 0.1;
        
        assertEquals("For times 5, 8, 2, the average should be 5. \nThe lake name should not be case sensitive. ", 5, q2.averageTimeForLake("Como"), delta);
        assertEquals("For times 5, 8, 2, the average should be 5. The lake name should not be case sensitive.", 5, q2.averageTimeForLake("COMO"), delta);
        
        assertEquals("For times 5, 17, the average should be 11. The lake name should not be case sensitive.", 11, q2.averageTimeForLake("HaAriEt"), delta);
        assertEquals("For times 5, 17, the average should be 11. The lake name should not be case sensitive.", 11, q2.averageTimeForLake("Harriet"), delta);
        
        assertEquals("For times 45345, 1121229, the average should be 583287. The lake name should not be case sensitive.", 583287, q2.averageTimeForLake("SUperior"), delta);
        assertEquals("For times 45345, 1121229, the average should be 583287. The lake name should not be case sensitive.", 583287, q2.averageTimeForLake("superior"), delta);
        
        
        assertEquals("The average method should return -1 if a lake is not found", -1, q2.averageTimeForLake("Not There"), delta);
        assertEquals("The average method should return -1 if a lake is not found", -1, q2.averageTimeForLake("HHAARRIIEETT"), delta);
        
    }
    
    
    
    @Test
    public void testaddRunTime() throws Exception {
    
        Question_2_Lake_Running question_2_lake_running = new Question_2_Lake_Running();
        
        
        question_2_lake_running.addRunTime("Como", 5);
        question_2_lake_running.addRunTime("CoMo", 2);
        question_2_lake_running.addRunTime("COMO", 3);    // Should all be considered the same lake.
        
        
        assertEquals(2, question_2_lake_running.fastestTimeForLake("Como"), delta);
        assertEquals(2, question_2_lake_running.fastestTimeForLake("CoMo"), delta);
        assertEquals(2, question_2_lake_running.fastestTimeForLake("cOMO"), delta);
        assertEquals(2, question_2_lake_running.fastestTimeForLake("coMO"), delta);
        
        
    }
    
    
}

