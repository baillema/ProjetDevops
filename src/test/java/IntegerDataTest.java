

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import data.IntegerData;

public class IntegerDataTest {
	
	private final static int NBTEST  = 500;
	
	IntegerData ID;
	
	@Before
	public void init(){
		ID = new IntegerData();
	}

	@Test
	public void testSet() {
		Random r = new Random();
		int value;
		for (int i = 0; i < NBTEST; i++){
			value = r.nextInt();
			ID.set(Integer.toString(value), value);
		}
	}
	
	@Test
	public void testSet2() {
		Random r = new Random();
		int value;
		for (int i = 0; i < NBTEST; i++){
			value = r.nextInt();
			assertTrue(ID.set(Integer.toString(value), Integer.toString(value)));
			assertFalse(ID.set(Integer.toString(value), Integer.toString(value)));
			assertFalse(ID.set(Integer.toString(value+NBTEST), Integer.toString(value)+" "));
		}
	}
	
	@Test
	public void testGet() {
		for (int i = 0; i < NBTEST; i++){
			ID.set(Integer.toString(i), NBTEST-i);
		}
		for (int i = 0; i < NBTEST; i++){
			assertEquals(NBTEST-i,(int)ID.get(Integer.toString(i)));
			assertEquals(null,ID.get(Integer.toString(i+NBTEST)));
		}
	}
	
	@Test
	public void testPresent() {
		for (int i = 0; i < NBTEST; i++){
			ID.set(Integer.toString(i), NBTEST-i);
		}
		for (int i = 0; i < NBTEST; i++){
			assertTrue(ID.isPresent(Integer.toString(i)));
		}
	}
	
	@Test
	public void testIncr() {
		for (int i = 0; i < NBTEST; i++){
			ID.set(Integer.toString(i), NBTEST-i);
		}
		for (int i = 0; i < NBTEST; i++){
			ID.incr(Integer.toString(i));
			assertFalse(ID.incr(Integer.toString(i+NBTEST)));
		}
		for (int i = 0; i < NBTEST; i++){
			assertEquals(NBTEST+1-i,(int)ID.get(Integer.toString(i)));
		}
		
		
	}
	
	@Test
	public void testSetSameName() {
		ID.set("test", -1);
		Random r = new Random();
		int value;
		for (int i = 0; i < NBTEST*100; i++){
			value = r.nextInt();
			ID.set("test", value);
		}
		assertEquals(-1,(int)ID.get("test"));
	}
	
	@Test
	public void testRemove() {
		for (int i = 0; i < NBTEST; i++){
			ID.set(Integer.toString(i), 42);
			ID.remove(Integer.toString(i));
			assertFalse(ID.remove(Integer.toString(i+NBTEST)));
		}
		for (int i = 0; i < NBTEST; i++){
			assertFalse(ID.isPresent(Integer.toString(i)));
		}
	}
	
	@Test
	public void testIsValidValue() {
		for (int i = 0; i < NBTEST; i++){
			assertTrue(ID.isValideValue(Integer.toString(i)));
		}
		for (int i = 0; i < NBTEST; i++){
			assertFalse(ID.isValideValue(Integer.toString(i)+"e"));
		}
	}

}
