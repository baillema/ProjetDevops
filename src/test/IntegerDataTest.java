package test;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import data.IntegerData;

public class IntegerDataTest {
	
	IntegerData ID;
	
	@Before
	public void init(){
		ID = new IntegerData();
	}

	@Test
	public void testSet() {
		Random r = new Random();
		int value;
		for (int i = 0; i < 50000; i++){
			value = r.nextInt();
			ID.set(Integer.toString(value), value);
		}
	}
	
	@Test
	public void testGet() {
		for (int i = 0; i < 500; i++){
			ID.set(Integer.toString(i), 500-i);
		}
		for (int i = 0; i < 500; i++){
			assertEquals(500-i,(int)ID.get(Integer.toString(i)));
		}
		
		
	}
	
	@Test
	public void testPresent() {
		for (int i = 0; i < 500; i++){
			ID.set(Integer.toString(i), 500-i);
		}
		for (int i = 0; i < 500; i++){
			assertTrue(ID.isPresent(Integer.toString(i)));
		}
	}
	
	@Test
	public void testIncr() {
		for (int i = 0; i < 500; i++){
			ID.set(Integer.toString(i), 500-i);
		}
		for (int i = 0; i < 500; i++){
			ID.incr(Integer.toString(i));
		}
		for (int i = 0; i < 500; i++){
			assertEquals(501-i,(int)ID.get(Integer.toString(i)));
		}
		
		
	}

}
