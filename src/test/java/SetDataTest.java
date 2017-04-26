import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import data.SetData;

public class SetDataTest {
	
	private SetData SD;
	private final static int NBTEST= 500;
	
	@Before
	public void init(){
		SD = new SetData();
	}
	

	@Test
	public void testSet() {
		Random r = new Random();	
		for(int i = 0; i < NBTEST; i++){
			int nbEl = r.nextInt(100);
			for(int j = 0; j < nbEl; j++) SD.set(Integer.toString(i), ""+j);		
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
				SD.set(Integer.toString(i), "\""+(j+i)+"\"");
			}
			assertEquals(tmpList, SD.get(Integer.toString(i)));
		}	
	}

}
