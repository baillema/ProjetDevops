package data;

import java.util.ArrayList;

public class SSetData implements Data {

	ArrayList<String> name = new ArrayList<String>();
	ArrayList<ArrayList<Object>> value = new ArrayList<ArrayList<Object>>();
		
	@Override
	public void set(String name, Object value) {
		if(isPresent(name))
		{
			if(isValuePresent(value, name)) System.out.println("Value already present in this set.");
			else insertSorted(value, name);
		}
		else
		{
			this.name.add(name);
			this.value.add(new ArrayList<Object>());
			this.value.get(this.name.indexOf(name)).add(value);
		}		
	}

	@Override
	public Object get(String name) {
		return this.value.get(this.name.indexOf(name));
	}

	@Override
	public boolean isPresent(String name) {
		return this.name.contains(name);
	}
	
	private boolean isValuePresent(Object value, String name) {
		return this.value.get(this.name.indexOf(name)).contains(value);
	}
	
	private void insertSorted(Object value, String name) {
		//TODO à insérer de façon sorted
		this.value.get(this.name.indexOf(name)).add(value);		
	}

}
