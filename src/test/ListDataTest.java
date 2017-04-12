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
		ArrayList<Integer> randomList = new ArrayList<Integer>();
		Random r = new Random();
		for(int i = 0; i < NBTEST; i++){
			int nbEl = r.nextInt(100);
			for (int j = 0; j < nbEl; j++){
				randomList.add(j);
			}
			LD.set(Integer.toString(i), randomList);
			randomList.clear();
		}
	}

	@Test
	public void testGet() {
		ArrayList<String> tempList = new ArrayList<String>();
		int nbEl = 50;
		for(int i = 0; i < NBTEST; i++){
			for (int j = 0; j < nbEl; j++){
				tempList.add(Integer.toString(j+i));
			}
			LD.set(Integer.toString(i), tempList.clone());
			tempList.clear();
		}
		for(int i = 0; i < NBTEST; i++){
			tempList = (ArrayList<String>) LD.get(Integer.toString(i));
			for (int j = 0; j < nbEl; j++){
				assertEquals(Integer.toString(i+j),  tempList.get(j));
				
			}
			
		}
		
	}

	@Test
	public void testIsPresent() {
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		for(int i = 0; i < NBTEST; i++){
			LD.set(Integer.toString(i), tempList);
		}
		for(int i = 0; i < NBTEST; i++){
			assertTrue(LD.isPresent(Integer.toString(i)));
		}
	}

	@Test
	public void testRemove() {
		ArrayList<Integer> tempList = new ArrayList<Integer>();
		for(int i = 0; i < NBTEST; i++){
			LD.set(Integer.toString(i), tempList);
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
