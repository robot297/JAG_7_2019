package input;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.google.common.collect.Lists.newArrayList;


/**
 * Created by Clara on 5/30/17.
 * Utility methods for
 *   - getting validated integer and double input from user.
 *   - turning yes/no responses into boolean values
 *   - String input - doesn't do much more than the basic scanner, but there for consistency
 */

public class InputUtils {

    //Create two scanners, one for Strings, and one for numbers - int and float values.

    //Use this scanner to read text data that will be stored in String variables
    static Scanner stringScanner = new Scanner(System.in);
    //Use this scanner to read in numerical data that will be stored in int or double variables
    static Scanner numberScanner = new Scanner(System.in);

    /* Print question, return whatever user types as a String. */
    public static String stringInput(String question) {
        if (question != null) {
            System.out.println(question);
        }
        return stringScanner.nextLine();
    }

    /* Calls the above method, but does not print a question. */
    public static String stringInput() {
        return stringInput(null);
    }


    //Takes a question, asks user the question, checks to make sure user enters a double, and
    //then returns that double to the calling method.
    public static double doubleInput(String question) {

        while (true) {
            // If user has provided a question, then print it for the user
            if (question != null) {
                System.out.println(question);
            }

            //Try to read what the user typed, expect it to be a double.
            try {
                // If the input can be read as a double, that double value will be returned
                // This ends the loop, and this method, and control returns to the calling method.
                return numberScanner.nextDouble();

            } // if the input can't be read as a double, then an error will be raised.
            // That error can be 'caught' by this code, and we can print an error message.
            // Since we are inside a while loop, then the loop can repeat and ask the user for input again.
            catch (InputMismatchException ime) {
                System.out.println("Error - please enter a number");
                numberScanner.next();
                /* If the value fetched from numberScanner.nextDouble can't be interpreted as a double,
                then that value is 'left' in the scanner, in case you wanted to try and read it with a numberScanner.nextLine()
                or other scanner method.
                So as your loop repeats, it keeps trying to read the same invalid data from the scanner.
                To fix, in the event of an exception, clear the invalid input with numberScanner.next()
                */
            }
        }

    }


    // A variant of the method below - notice it calls doubleInput with null as the argument
    public static double doubleInput() {
        return doubleInput(null);
    }


    //Takes a question, asks user the question, checks to make sure user enters an int, and
    //then returns that int to the calling method.
    public static int intInput(String question) {

        while (true) {
            // If user has provided a question, then print it for the user
            if (question != null) {
                System.out.println(question);
            }

            //Try to read what the user typed as an int.
            try {
                // If the input can be read as a int, that int will be returned
                // This ends the loop, and this method, and control returns to the calling method.
                return numberScanner.nextInt();

            } // if the input can't be read as an int, then an error will be raised.
            // For example, if the user enters 'ten' or 1.4 or 123456543454343434, these are not ints, so will cause an error.
            // That error can be 'caught' by this code, and we can print an error message.
            // Since we are inside a while loop, then the loop can repeat and ask the user for input again.
            catch (InputMismatchException ime) {
                System.out.println("Error - please enter an integer number");
                numberScanner.next();
                /* If the value fetched from numberScanner.nextInt can't be interpreted as a int,
                then that value is 'left' in the scanner, in case you wanted to try and read it with a
                numberScanner.nextLine() or numberScanner.nextDouble() or other scanner method.
                So as your loop repeats, it keeps trying to read the same invalid data from the scanner.
                To fix, in the event of an exception, clear the invalid input with numberScanner.next()
                */
            }
        }

    }

    // A variant of the method below - notice it calls intInput with null as the argument
    public static int intInput() {
        return intInput(null);
    }


    /* Converts a Yes or No input to a boolean value
    "yes" or "y" or uppercase variants returns true
    "no" or "n" or uppercase variants return false
    All other inputs ask user to re-enter data
     */
    public static boolean yesNoInput(String question) {

        // Values that are considered to be a yes response
        ArrayList<String> yesValues = newArrayList("yes", "y");

        // Same for no responses
        ArrayList<String> noValues = newArrayList("no", "n");

        while (true) {

            // If user has provided a question, then print it for the user
            if (question != null) {
                System.out.print(question);
            }

            // Suggest expected responses
            System.out.println(" Y/N? ");

            String response = stringScanner.nextLine().toLowerCase();

            if (yesValues.contains(response)) {
                return true;
            }

            if (noValues.contains(response)) {
                return false;
            }

            // If the user input is not a yes or a no response, the loop will repeat.

        }

    }

    public static boolean yesNoInput() {
        return yesNoInput(null);
    }


}



