package data;

import java.util.ArrayList;

public class IntegerData implements Data {
	
	ArrayList<String> name = new ArrayList<String>();
	ArrayList<Integer> value = new ArrayList<Integer>();
	ArrayList<Thread> timer = new ArrayList<Thread>();
	
	@Override
	public boolean set(String name, Object value) {
		boolean res = false;
		if(!isPresent(name) && isValideValue((String) value))
		{
			this.name.add(name);
			this.value.add(Integer.parseInt((String) value));
			res = true;
		}
		
		return res;
	}
	
	public void set(String name, int value) {
		if(!isPresent(name))
		{
			this.name.add(name);
			this.value.add(value);
		}
	}

	@Override
	public Object get(String name) {
		if(isPresent(name))return this.value.get(this.name.indexOf(name));
		else return null;	
	}
	
	@Override
	public boolean isPresent(String name) {
		return this.name.contains(name);
	}
	
	@Override
	public boolean remove(String name) {
		boolean res = false;
		if(isPresent(name))
		{
			this.value.remove(this.name.indexOf(name));
			this.name.remove(this.name.indexOf(name));
			res = true;
		}
		
		return res;
	}

	public boolean incr(String name) {
		boolean res = false;
		if(isPresent(name))
		{
			int val = this.value.get(this.name.indexOf(name)).intValue()+1;
			this.value.set(this.name.indexOf(name), val);
			res = true;
		}
		
		return res;
	}

	@Override
	public boolean isValideValue(String value) {
		boolean res;
		try  
		{  
			Integer.parseInt(value);
			res = true;
		}
		catch(NumberFormatException nfe){ res = false; }
		
		return res;
	}	
}
