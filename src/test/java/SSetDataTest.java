import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import data.SSetData;

public class SSetDataTest {

	private SSetData SSD;
	private final static int NBTEST= 500;
	
	@Before
	public void init(){
		SSD = new SSetData();
	}
	
	@Test
	public void testSet() {
		Random r = new Random();	
		for(int i = 0; i < NBTEST; i++){
			int nbEl = r.nextInt(100);
			for(int j = 0; j < nbEl; j++) SSD.set(Integer.toString(i), ""+j);		
		}
	}

	@Test
	public void testGet() {
		ArrayList<String> tmpList;
		int nbEl = 100;
		for(int i = 0; i < NBTEST; i++){
			tmpList = new ArrayList<String>();
			for (int j = 0; j < nbEl; j++){
				tmpList.add("\""+(j+i)+"\"");
				SSD.set(Integer.toString(i), "\""+(j+i)+"\"");
			}
			tmpList.sort(String::compareToIgnoreCase);
			assertEquals(tmpList, SSD.get(Integer.toString(i)));
		}	
	}

}
