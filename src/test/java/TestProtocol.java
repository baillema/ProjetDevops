import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import server.Protocol;

public class TestProtocol {

	private Protocol P;
	
	@Before
	public void init(){
		P = new Protocol();
	}
	
	@Test
	public void testProcessInput() {
		String res =P.processInput("test");
		res.equalsIgnoreCase("Waiting structure mode");
	}
	
	@Test
	public void testProcessInputQuit() {
	}
	
	@Test
	public void testProcessInputData() {
	}
	
	@Test
	public void testProcessInputHelp() {
	}
	
	

}
