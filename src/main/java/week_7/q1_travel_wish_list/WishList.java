package week_7.q1_travel_wish_list;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static input.InputUtils.stringInput;
import static input.InputUtils.yesNoInput;

/**

 */
public class WishList {
    
    public static void main(String[] args) {
    
        System.out.println(new Date());
        // TODO modify this line so the List stores Place objects
        List wishList = new ArrayList<>();
        
        do {
            
            String name = stringInput("Enter the name of the place");
            String reason = stringInput("Why do you want to visit " + name + "?");
            
            // TODO create a new Place object with the name, and reason
            // TODO add the new Place object to the wishList
            
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
        //  sort the List of Place objects and print each place
        //  Don't print anything else
        //  Make sure you've made Place Comparable, and it has a toString method
        
    }
    
}
