package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import data.ListData;

public class ListDataTest {

	private ListData LD;
	private final static int NBTEST= 500;
	
	@Before
	public void init(){
		LD = new ListData();
	}
	
	@Test
	public void testSet() {
		//ArrayList<Integer> randomList = new ArrayList<Integer>();
		Random r = new Random();	
		for(int i = 0; i < NBTEST; i++){
			int nbEl = r.nextInt(100);
			for(int j = 0; j < nbEl; j++) LD.set(Integer.toString(i), ""+j);		
		}
	}

	@Test
	public void testGet() {
		ArrayList<Object> tmpList = new ArrayList<Object>();
		int nbEl = 50;
		for(int i = 0; i < NBTEST; i++){
			tmpList = new ArrayList<Object>();
			for (int j = 0; j < nbEl; j++){
				tmpList.add("\""+(j+i)+"\"");
				LD.set(Integer.toString(i), "\""+(j+i)+"\"");
			}
			assertEquals(tmpList, LD.get(Integer.toString(i)));
		}	
	}

	@Test
	public void testIsPresent() {
		for(int i = 0; i < NBTEST; i++){
			LD.set(Integer.toString(i), "\""+i+"\"");
		}
		for(int i = 0; i < NBTEST; i++){
			assertTrue(LD.isPresent(Integer.toString(i)));
		}
	}

	@Test
	public void testRemove() {
		for(int i = 0; i < NBTEST; i++){
			LD.set(Integer.toString(i), "\""+i+"\"");
			LD.remove(Integer.toString(i));
		}
		for(int i = 0; i < NBTEST; i++){
			assertFalse(LD.isPresent(Integer.toString(i)));
		}
	}

	@Test
	public void testRemoveElmtStringInt() {
		//
	}

	@Test
	public void testRemoveElmtStringString() {
	}

}
