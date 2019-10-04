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
        assertEquals(Void.TYPE, setName.getReturnType());
    
        Method getReason = placeClass.getMethod("getReason");
        assertEquals(String.class, getReason.getReturnType());
        
        Method setReason = placeClass.getMethod("setReason", String.class);
        assertEquals(Void.TYPE, setReason.getReturnType());
    
        Method getCreated = placeClass.getMethod("getCreated");
        assertEquals(Date.class, getCreated.getReturnType());
    
        Method setCreated = placeClass.getMethod("setCreated", Date.class);
        assertEquals(Void.TYPE, setCreated.getReturnType());
    
        Constructor[] cons = placeClass.getConstructors();
        Place p = (Place) cons[0].newInstance("example", "because");
        
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
        assertArrayEquals(new Class[]{String.class, String.class}, c.getParameterTypes());
        
        Date now = new Date();
        Place p = (Place) c.newInstance("example", "because");
    
        
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
    
        Class comparable = Comparable.class;
        assertTrue(comparable.isAssignableFrom(placeClass));
    
        Method compare = placeClass.getMethod("compareTo", Place.class);
    
        Constructor[] cons = placeClass.getConstructors();
        assertEquals(1, cons.length);
        Constructor c = cons[0];
    
        Place firstPlace = (Place) c.newInstance("arizona", "because");
        Place secondPlace = (Place) c.newInstance("Rome", "why");
        Place thirdPlace =  (Place) c.newInstance("Zambia", "reason");
        
        assertTrue((int)compare.invoke(firstPlace, secondPlace) < 0);
        assertTrue((int)compare.invoke(secondPlace, firstPlace) > 0);
        assertEquals(0, compare.invoke(firstPlace, firstPlace));
        assertEquals(0, compare.invoke(secondPlace, secondPlace));
        assertEquals(0, compare.invoke(thirdPlace, thirdPlace));
    
        assertTrue((int)compare.invoke(secondPlace, thirdPlace) < 0);
        assertTrue((int)compare.invoke(thirdPlace, firstPlace) > 0);
        assertTrue((int)compare.invoke(firstPlace, thirdPlace) < 0);
        
        
    
    }
    
    
    @Test(timeout=3000)
    public void testPlaceToString() throws Exception{
    
        Constructor[] cons = placeClass.getConstructors();
        assertEquals(1, cons.length);
        Constructor c = cons[0];
    
        Date now = new Date();
        
        Place firstPlace = (Place) c.newInstance("arizona", "because");
        
        Method toString = placeClass.getMethod("toString");
        String outString = (String) toString.invoke(firstPlace);
        
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
    
        Date d = new Date();
        Place firstPlace = (Place) c.newInstance("Zambia", "reason");
        Place secondPlace = (Place) c.newInstance("Rome", "why");
        Place thirdPlace = (Place) c.newInstance("arizona", "because");
    
        ArrayList<Place> places = new ArrayList<>();
        places.add(firstPlace); places.add(secondPlace); places.add(thirdPlace);
    
        WishList.displayPlacesInNameOrder(places);
        
        String out = PrintUtils.resetStandardOut().replace("\r", "").trim();
        
        String expected = String.format("Place to visit: %s. Reason: %s. Date created: %s", "arizona", "because", d.toString()) + "\n" +
                String.format("Place to visit: %s. Reason: %s. Date created: %s", "Rome", "why", d.toString()) + "\n" +
                String.format("Place to visit: %s. Reason: %s. Date created: %s", "Zambia", "reason", d.toString());
        
        assertEquals(expected, out);
    }
}