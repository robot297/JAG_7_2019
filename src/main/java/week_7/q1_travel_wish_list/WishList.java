package week_7.q1_travel_wish_list;

import java.util.ArrayList;
import java.util.List;

import static input.InputUtils.stringInput;
import static input.InputUtils.yesNoInput;

/**
Program to create and display a list of places a user wishes to travel to.
 */


public class WishList {
    
    public static void main(String[] args) {

        // TODO modify this line so the wishList List stores Place objects
        List wishList = new ArrayList<>();
        
        do {
            
            String name = stringInput("Enter the name of the place");
            String reason = stringInput("Why do you want to visit " + name + "?");
            
            // TODO use the newPlace method to
            //  create a new Place object with the name, and reason
            // The Place object's constructor should calculate the date created and store that.
            
            // TODO add the new Place object, returned by newPlace, to the wishList
            
        } while (yesNoInput("More places to add to your wish list?"));
        
        // TODO Call the displayPlacesInNameOrder method to print
        //  a list of the places, sorted by name
        
    }
    
    public static Place newPlace(String placeName, String reason) {
        
        // TODO create a new Place object with the given placeName and reason
        
        return null;  // TODO change this to return the new Place
    }
    
    
    public static void displayPlacesInNameOrder(List<Place> places) {
        
        // TODO
        //  Sort the List of Place objects, places should be sorted in name order.
        //  the sort should be case insensitive. 'hawaii' should be before 'Rome'.
        //  Print each place, one per line
        //  Don't print anything else
        //  Make sure you've made Place Comparable, and it has a toString method
        
    }
    
}
