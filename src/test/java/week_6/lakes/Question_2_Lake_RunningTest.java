package week_6.lakes;

import org.junit.Test;
import test_utils.PrintUtils;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by Clara on 8/4/17.
 */
public class Question_2_Lake_RunningTest {
    
    @Test
    public void testPrintFastestTimeForAllLakes() throws Exception {
        
        Question_2_Lake_Running q5 = new Question_2_Lake_Running();
        
        q5.addLake("Como", 5);
        q5.addLake("Como", 6);
        q5.addLake("CoMo", 3);
        
        q5.addLake("Harriet", 5);
        q5.addLake("HaRRiet", 16);
        
        
        q5.addLake("Superior", 45345);
        q5.addLake("Superior", 1121226);
        
        PrintUtils.catchStandardOut();
        
        q5.printFastestTimeForAllLakes();
        
        String out = PrintUtils.resetStandardOut();
        out = out.replace("\n", " ").toLowerCase();

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
    
    
        Question_2_Lake_Running q5 = new Question_2_Lake_Running();
        
        
        q5.addLake("Lake Como", 5);
        q5.addLake("Lake Como", 6);
        q5.addLake("Lake Como", 3);
        
        q5.addLake("Harriet", 5);
        q5.addLake("Harriet", 16);
        
        
        q5.addLake("Superior", 45345);
        q5.addLake("Superior", 1121226);
        
        double delta = 0.000001;
        
        assertEquals(3, q5.fastestTimeForLake("Lake Como"), delta);
        assertEquals(5, q5.fastestTimeForLake("Harriet"), delta);
        assertEquals(45345, q5.fastestTimeForLake("Superior"), delta);
        assertEquals("This method should return -1 if a lake is not found", -1, q5.fastestTimeForLake("Not There"), delta);
        
    }
    
    
    @Test
    public void testAddLake() throws Exception {
    
        Question_2_Lake_Running q5 = new Question_2_Lake_Running();
        
        
        q5.addLake("Como", 5);
        q5.addLake("CoMo", 2);
        q5.addLake("COMO", 3);    // Should all be considered the same lake.
        
        
        double delta = 0.000001;
        assertEquals(2, q5.fastestTimeForLake("Como"), delta);
        assertEquals(2, q5.fastestTimeForLake("CoMo"), delta);
        assertEquals(2, q5.fastestTimeForLake("cOMO"), delta);
        assertEquals(2, q5.fastestTimeForLake("coMO"), delta);
        
        
        // TODO .... can YOU write more tests? Depends on how you structure your Lake object. data structure used.
    }
}