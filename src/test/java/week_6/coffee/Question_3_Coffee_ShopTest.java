package week_6.coffee;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.*;

/**
 * Created by clara on 8/3/17.
 */
public class Question_3_Coffee_ShopTest {
    
    @Test
    public void salesReport() throws Exception {
        
        //Example input files
        
        String priceData = "Coke;0.1;2\n" +
                "Sprite;0.2;2.50";
        
        String salesData = "Coke:4\n" +
                "Sprite:7";
        
        // Contents of expected sales report, based on the data above
        
        String expectedSalesReport = "Coke: Sold 4, Expenses $0.40, Revenue $8.00, Profit $7.60\n" +
                "Sprite: Sold 7, Expenses $1.40, Revenue $17.50, Profit $16.10\n" +
                "All Drinks: Sold 13, Expenses $1.80, Revenue $25.50, Profit $23.70";
        
        week_6.Question_3_Coffee_Shop q3 = new week_6.Question_3_Coffee_Shop();
        
        q3.salesReport();
        
        // Read the file and compare to expectedSalesReport
        
        try {
            
            BufferedReader reader = new BufferedReader(new FileReader(q3.output_report_file));
            
            String data = "";
            String line = reader.readLine();
            while (line != null) {
                data = data + line + "\n";
                line = reader.readLine();
            }
            
            data = data.trim();  // remove trailing white space
            
            reader.close();
            
            assertEquals("Make sure you write the data in the exact format requested, and verify your math is correct.", expectedSalesReport, data);
            
        } catch (FileNotFoundException f) {
            fail("Write the report to a file called " + q3.output_report_file + ". Use the variable output_report_file for the file name.");
        }
        
    }
    
    // Since the implementation of the code is mostly up to you, it's impossible for me to write any more
    // detailed tests. Maybe you could write some tests for your methods?
    
    
}