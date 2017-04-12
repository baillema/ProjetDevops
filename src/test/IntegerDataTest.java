package test;

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
		for (int i = 0; i < NBTEST*100; i++){
			value = r.nextInt();
			ID.set(Integer.toString(value), value);
		}
	}
	
	@Test
	public void testGet() {
		for (int i = 0; i < NBTEST; i++){
			ID.set(Integer.toString(i), NBTEST-i);
		}
		for (int i = 0; i < NBTEST; i++){
			assertEquals(NBTEST-i,(int)ID.get(Integer.toString(i)));
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

}
