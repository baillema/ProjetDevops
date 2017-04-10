package data;

import java.util.ArrayList;

public class IntegerData implements Data {
	
	ArrayList<String> name = new ArrayList<String>();
	ArrayList<Integer> value = new ArrayList<Integer>();
	
	@Override
	public void set(String name, Object value) {
		if(isPresent(name)) System.out.println("Name already present in this scope.");
		else
		{
			this.name.add(name);
			this.value.add((int) value);
		}
	}

	@Override
	public Object get(String name) {
		return value.get(name.indexOf(name));
	}
	
	@Override
	public boolean isPresent(String name) {
		return this.name.contains(name);
	}
	
}
