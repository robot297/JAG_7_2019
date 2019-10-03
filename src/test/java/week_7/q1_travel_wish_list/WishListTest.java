package week_7.q1_travel_wish_list;

import org.junit.Before;
import org.junit.Test;
import test_utils.PrintUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Math.abs;
import static org.junit.Assert.*;

/**
 * Created by clara on 10/3/19.
 */
public class WishListTest {
    
    Class placeClass;
    
    @Before
    public void findPlaceClass() throws ClassNotFoundException {
        placeClass = Class.forName("week_7.q1_travel_wish_list.Place");
    }
    
    
    @Test(timeout=3000)
    public void testPlaceClassFields() throws NoSuchFieldException{
        
        // does it have the right fields? Correct types? Are they private?
        
        Field nameField = placeClass.getDeclaredField("name");
        assertTrue(Modifier.isPrivate(nameField.getModifiers()));
        assertEquals(String.class, nameField.getType());
    
        Field reasonField = placeClass.getDeclaredField("reason");
        assertTrue(Modifier.isPrivate(reasonField.getModifiers()));
        assertEquals(String.class, reasonField.getType());
        
        Field createdField = placeClass.getDeclaredField("created");
        assertTrue(Modifier.isPrivate(nameField.getModifiers()));
        assertEquals(Date.class, createdField.getType());
        
    }
    
    
    @Test(timeout=3000)
    public void testPlaceClassGetAndSet() throws Exception{
    
        String[] methodsExpected = {"getName", "setName", "getReason", "setReason", "getCreated", "setCreated"};
        
        Method getName = placeClass.getMethod("getName");
        assertEquals(String.class, getName.getReturnType());
        
        Method setName = placeClass.getMethod("setName", String.class);
        assertEquals(Void.class, setName.getReturnType());
    
        Method getReason = placeClass.getMethod("getReason");
        assertEquals(String.class, getReason.getReturnType());
        
        Method setReason = placeClass.getMethod("setReason", String.class);
        assertEquals(Void.class, setReason.getReturnType());
    
        Method getCreated = placeClass.getMethod("getCreated");
        assertEquals(Date.class, getCreated.getReturnType());
    
        Method setCreated = placeClass.getMethod("setCreated", Date.class);
        assertEquals(Void.class, setCreated.getReturnType());
    
        Constructor[] cons = placeClass.getConstructors();
        Place p = (Place) cons[1].newInstance("example", "because", new Date());
        
        setName.invoke(p, "California");
        String name = (String) getName.invoke(p);
        assertEquals(name, "California");
        
        setReason.invoke(p, "Because");
        String reason = (String) getReason.invoke(p);
        assertEquals("Because", reason);
    
        Date now = new Date();
        setCreated.invoke(p, now);
        Date created = (Date) getCreated.invoke(p);
        assertEquals(now, created);
        
    }
    
    @Test(timeout=3000)
    public void testPlaceClassConstructor() throws  Exception{
    
        Constructor[] cons = placeClass.getConstructors();
        assertEquals(1, cons.length);
        Constructor c = cons[0];
        assertArrayEquals(new Class[]{String.class, String.class, Date.class}, c.getParameterTypes());
        
        Date now = new Date();
        Place p = (Place) c.newInstance("example", "because", now);
    
        
        Method getName = placeClass.getMethod("getName");
        Method getReason = placeClass.getMethod("getReason");
        Method getCreated = placeClass.getMethod("getCreated");
        
        String name = (String) getName.invoke(p);
        assertEquals("example", name);
        
        String reason = (String) getReason.invoke(p);
        assertEquals("because", reason);
        
        Date created = (Date) getCreated.invoke(p);
        assertEquals(now, created);
        
    }
    
    
    
    @Test(timeout=3000)
    public void placeIsComparable() throws Exception{
    
        int m = placeClass.getModifiers();
        Class comparable = Comparable.class;
        assertTrue(placeClass.isAssignableFrom(comparable));
    
        Method compare = placeClass.getMethod("compareTo", Place.class);
    
        Constructor[] cons = placeClass.getConstructors();
        assertEquals(1, cons.length);
        Constructor c = cons[0];
    
        Place firstPlace = (Place) c.newInstance("arizona", "because", new Date());
        Place secondPlace = (Place) c.newInstance("Rome", "why", new Date(10000));
        Place thirdPlace =  (Place) c.newInstance("Zambia", "reason", new Date());
        
        assertEquals(-1, compare.invoke(firstPlace, secondPlace));
        assertEquals(1, compare.invoke(firstPlace, secondPlace));
        assertEquals(0, compare.invoke(firstPlace, firstPlace));
    
        assertEquals(0, compare.invoke(secondPlace, firstPlace));
        assertEquals(0, compare.invoke(thirdPlace, firstPlace));
        assertEquals(0, compare.invoke(firstPlace, secondPlace));
        
    
    }
    
    
    @Test(timeout=3000)
    public void testPlaceToString() throws Exception{
    
        Constructor[] cons = placeClass.getConstructors();
        assertEquals(1, cons.length);
        Constructor c = cons[0];
    
        Date now = new Date();
        
        Place firstPlace = (Place) c.newInstance("arizona", "because", now);
        
        Method toString = placeClass.getMethod("toString");
        String outString = (String) toString.invoke(placeClass);
        
        String expected = String.format("Place to visit: %s. Reason: %s. Date created: %s", "arizona", "because", now.toString());
        assertEquals(expected, outString);
    }
    
    
    @Test(timeout=3000)
    public void newPlace()  throws Exception{
    
        Place place = WishList.newPlace("Arizona", "To visit the Grand Canyon");
    
        Method getName = placeClass.getMethod("getName");
        Method getReason = placeClass.getMethod("getReason");
        Method getCreated = placeClass.getMethod("getCreated");
    
        String name = (String) getName.invoke(place);
        assertEquals(name, "Arizona");
    
        String reason = (String) getReason.invoke(place);
        assertEquals("To visit the Grand Canyon", reason);
    
        Date now = new Date();
        Date created = (Date) getCreated.invoke(place);
        
        assertTrue(  abs(now.getTime() - created.getTime()) < 1000 );  // within a second
    }
    
    @Test(timeout=3000)
    public void displayPlacesInNameOrder() throws Exception {
    
        Constructor[] cons = placeClass.getConstructors();
        assertEquals(1, cons.length);
        Constructor c = cons[0];
        
        PrintUtils.catchStandardOut();
    
        Date d1 = new Date();
        Date d2 = new Date(10000);
        Date d3 = new Date(100000000);
        Place firstPlace = (Place) c.newInstance("Zambia", "reason", d1);
        Place secondPlace = (Place) c.newInstance("Rome", "why", d2);
        Place thirdPlace = (Place) c.newInstance("arizona", "because", d3);
    
        ArrayList<Place> places = new ArrayList<>();
        places.add(firstPlace); places.add(secondPlace); places.add(thirdPlace);
    
        WishList.displayPlacesInNameOrder(places);
        
        String out = PrintUtils.resetStandardOut().replace("\r", "").trim();
        
        String expected = String.format("Place to visit: %s. Reason: %s. Date created: %s", "arizona", "because", d3.toString()) + "\n" +
                String.format("Place to visit: %s. Reason: %s. Date created: %s", "Rome", "why", d2.toString()) + "\n" +
                String.format("Place to visit: %s. Reason: %s. Date created: %s", "Zambia", "reason", d1.toString());
        
        assertEquals(expected, out);
    }
}