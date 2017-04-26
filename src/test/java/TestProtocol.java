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
		P.processInput("test");
		String res = P.processInput("q");
		assertTrue(res.equalsIgnoreCase("Bye."));
	}
	
	@Test
	public void testProcessInputDataInt() {
		P.processInput("test");
		String res = P.processInput("int");
		assertTrue(res.equalsIgnoreCase("Enter in mode int"));
		res = P.processInput("set test 42");
		assertTrue(res.equalsIgnoreCase("OK"));
		res = P.processInput("set test erreur");
		assertTrue(res.equalsIgnoreCase("Invalid value"));
		res = P.processInput("set test test erreur");
		assertTrue(res.equalsIgnoreCase("Problem in number of argument"));
		res = P.processInput("get test");
		assertTrue(res.equalsIgnoreCase("test 42"));
		res = P.processInput("get erreur");
		assertTrue(res.equalsIgnoreCase("No set value for that name"));
		res = P.processInput("incr test");
		assertTrue(res.equalsIgnoreCase("Increment done"));
		res = P.processInput("incr erreur");
		assertTrue(res.equalsIgnoreCase("Value not present"));
		res = P.processInput("del test");
		assertTrue(res.equalsIgnoreCase("test successfuly removed"));
		res = P.processInput("del erreur");
		assertTrue(res.equalsIgnoreCase("Value not present"));
		res = P.processInput("erreur erreur");
		assertTrue(res.equalsIgnoreCase("Invalid command int"));
	}
	
	@Test
	public void testProcessInputDataList() {
		P.processInput("test");
		String res = P.processInput("list");
		assertTrue(res.equalsIgnoreCase("Enter in mode list"));
		res = P.processInput("set test \"test\"");
		assertTrue(res.equalsIgnoreCase("OK"));
		res = P.processInput("set erreur erreur");
		assertTrue(res.equalsIgnoreCase("Invalid value"));
		res = P.processInput("set erreur erreur erreur");
		assertTrue(res.equalsIgnoreCase("Problem in number of argument"));
		res = P.processInput("get test");
		assertTrue(res.equalsIgnoreCase("test [\"test\"]"));
		res = P.processInput("get erreur");
		assertTrue(res.equalsIgnoreCase("erreur not present in this scope."));
		res = P.processInput("del test");
		assertTrue(res.equalsIgnoreCase("test successfuly removed"));
		res = P.processInput("del erreur");
		assertTrue(res.equalsIgnoreCase("erreur not present in this scope."));
		res = P.processInput("erreur erreur");
		assertTrue(res.equalsIgnoreCase("Invalid command list"));
		
	}
	
	@Test
	public void testProcessInputDataSet() {
		P.processInput("test");
		String res = P.processInput("set");
		assertTrue(res.equalsIgnoreCase("Enter in mode set"));
		res = P.processInput("set test \"test\"");
		assertTrue(res.equalsIgnoreCase("OK"));
		res = P.processInput("set erreur erreur");
		assertTrue(res.equalsIgnoreCase("Invalid value"));
		res = P.processInput("set erreur erreur erreur");
		assertTrue(res.equalsIgnoreCase("Problem in number of argument"));
		res = P.processInput("get test");
		assertTrue(res.equalsIgnoreCase("test [\"test\"]"));
		res = P.processInput("get erreur");
		assertTrue(res.equalsIgnoreCase("erreur not present in this scope."));
		res = P.processInput("del test");
		assertTrue(res.equalsIgnoreCase("test successfuly removed"));
		res = P.processInput("del erreur");
		assertTrue(res.equalsIgnoreCase("erreur not present in this scope."));
		res = P.processInput("erreur erreur");
		assertTrue(res.equalsIgnoreCase("Invalid command set"));
		
	}
	
	@Test
	public void testProcessInputDataSSet() {
		P.processInput("test");
		String res = P.processInput("sset");
		assertTrue(res.equalsIgnoreCase("Enter in mode sset"));
		res = P.processInput("set test \"test\"");
		assertTrue(res.equalsIgnoreCase("OK"));
		res = P.processInput("set erreur erreur");
		assertTrue(res.equalsIgnoreCase("Invalid value"));
		res = P.processInput("set erreur erreur erreur");
		assertTrue(res.equalsIgnoreCase("Problem in number of argument"));
		res = P.processInput("get test");
		assertTrue(res.equalsIgnoreCase("test [\"test\"]"));
		res = P.processInput("get erreur");
		assertTrue(res.equalsIgnoreCase("erreur not present in this scope."));
		res = P.processInput("del test");
		assertTrue(res.equalsIgnoreCase("test successfuly removed"));
		res = P.processInput("del erreur");
		assertTrue(res.equalsIgnoreCase("erreur not present in this scope."));
		res = P.processInput("erreur erreur");
		assertTrue(res.equalsIgnoreCase("Invalid command sset"));
		
	}
	
	@Test
	public void testProcessInputDataWait() {
		P.processInput("test");
		P.processInput("int");
		String res = P.processInput("q");
		assertTrue(res.equalsIgnoreCase("Go back to structure choice "));
	}
	
	@Test
	public void testProcessInputHelp() {
		String rep;
		rep = "You can choose between four types of data : integer (int), list (list), set (set), sorted set (sset)\n";
		rep+= "The commands that are available for all types : set, get, del\n";
		rep+= "Ex : set val 10 (for int), set val \"hello\" (for list, set, sset)\n";
		rep+= "Ex : get val (for all types)\n";
		rep+= "Ex : del val (for all types)\n";
		rep+= "Only for int : incr. Ex : incr val (add 1 to val)\n";
		rep+= "Only for other than int : delelmt\n";
		rep+= "Ex : delelmt val \"hello\"\n";
		
		P.processInput("test");
		String res = P.processInput("help");
		assertTrue(res.equalsIgnoreCase(rep));
		
	}
	
	@Test
	public void testProcessInputError() {
		P.processInput("test");
		String res = P.processInput("42");
		assertTrue(res.equalsIgnoreCase("Invalid structure "));
	}
	
	
	
	 

}
