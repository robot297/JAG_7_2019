package week_6.q3_coffee;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test_utils.FileUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by Clara on 8/3/17.
 */
public class Question_3_Coffee_ShopTest {
    
    private String testPriceFilename;
    private String testSalesFilename;
    private String testOutputFile;
    
    
    @Before
    public void createFilenames() {
        testPriceFilename = FileUtils.uniqueFilename("temporary_file_for_testing_test_price_data");
        testSalesFilename = FileUtils.uniqueFilename("temporary_file_for_testing_test_sales_data.txt");
        testOutputFile = FileUtils.uniqueFilename("temporary_file_for_testing_report.txt");
    }
    
    @Test
    public void salesReport() throws Exception {
        
        Question_3_Coffee_Shop q3 = new Question_3_Coffee_Shop();
        
        String originalReportFilename = q3.output_report_file;
        
        //Create some example input files
        
        String priceData = "Coke;0.1;2\n" +
                "Sprite;0.2;2.50";
        
        String salesData = "Coke;4\n" +
                "Sprite;7";
        
        FileWriter writer = new FileWriter(testPriceFilename);
        writer.write(priceData);
        writer.close();
        
        writer = new FileWriter(testSalesFilename);
        writer.write(salesData);
        writer.close();
        
        
        // Replace the original filenames with these testing files
        
        q3.output_report_file = testOutputFile;
        q3.sales_data_file = testSalesFilename;
        q3.price_data_file = testPriceFilename;
        
        // Contents of expected sales report, based on the data above
        
        String expectedSalesReport = "Coke: Sold 4, Expenses $0.40, Revenue $8.00, Profit $7.60\n" +
                "Sprite: Sold 7, Expenses $1.40, Revenue $17.50, Profit $16.10\n" +
                "All Drinks: Sold 11, Expenses $1.80, Revenue $25.50, Profit $23.70";
        
        
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
            
            assertEquals("Make sure you write the data in the exact format requested. \nVerify your math is correct. \nWrite the drink data in the *same order* as found in the data files.\n Click the 'Click to see difference' link near this error message to see the expected file, and your output", expectedSalesReport, data);
            
        } catch (FileNotFoundException f) {
            
            fail("Write the report to a file called " + originalReportFilename + ". Use the variable output_report_file for the file name.");
        }
        
    }
    
    
    @Test
    public void codeQualityTests() {
        fail("This test is supposed to fail, the instructor will assess your code and assign the rest of the points");
    }
    
    
    
    @Test
    public void checkMethodsDoNotThrowException() throws Exception {
        //Verify readCoffeeDataFiles and writeReportFile do not throw exceptions
        
        // TODO verify try-with-resources is used. Check methods and verify none throw exceptions.
        
        Class q7 = Class.forName("week_6.q3_coffee.Question_3_Coffee_Shop");
        
        // since the return type has changed, have to search the methods in the class, instead of being able to specify a particular method.
        Method[] allMethods = q7.getDeclaredMethods();
        for (Method m : allMethods) {
            assertEquals("Add try-catch blocks to your writeReportFile method. " +
                    "Handle any possible exceptions with try-catch statements within the method.", 0, m.getExceptionTypes().length);
                
            }
    }
    
    
    // Since the implementation of the code is mostly up to you, it's impossible for me to write any more
    // detailed tests. Maybe you could write some tests for your methods?
    
    
    @After
    public void cleanupFiles() {
        
        // Delete temporary files used for the tests.
        
        FileUtils.moveToTemporaryTestFolder(testOutputFile);
        FileUtils.moveToTemporaryTestFolder(testPriceFilename);
        FileUtils.moveToTemporaryTestFolder(testSalesFilename);
    }
    
}

