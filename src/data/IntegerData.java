package data;

import java.util.ArrayList;

public class IntegerData implements Data {
	
	ArrayList<String> name = new ArrayList<String>();
	ArrayList<Integer> value = new ArrayList<Integer>();
	ArrayList<Thread> timer = new ArrayList<Thread>();
	
	@Override
	public void set(String name, Object value) {
		if(!isPresent(name))
		{
			this.name.add(name);
			this.value.add((int) value);
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
	public void remove(String name) {
		if(isPresent(name))
		{
			this.value.remove(this.name.indexOf(name));
			this.name.remove(this.name.indexOf(name));
		}
	}

	public Object incr(String name) {
		if(isPresent(name))
		{
			int val = this.value.get(this.name.indexOf(name)).intValue()+1;
			this.value.set(this.name.indexOf(name), val);
			return get(name);
		}
		else return null;
	}	
}
