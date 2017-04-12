package data;

import java.util.ArrayList;
import java.util.Timer;

public class SetData implements Data {

	ArrayList<String> name = new ArrayList<String>();
	ArrayList<ArrayList<Object>> value = new ArrayList<ArrayList<Object>>();
	ArrayList<Timer> timer = new ArrayList<Timer>();
	
	@SuppressWarnings("unchecked")
	@Override
	public void set(String name, Object value) {
		if(isPresent(name))
		{
			if(isValuePresent(value, name)) System.out.println("Value already present in this set.");
			else this.value.get(this.name.indexOf(name)).add(value);
		}
		else
		{
			this.name.add(name);
			this.value.add(new ArrayList<Object>());
			this.value.set(this.name.indexOf(name),(ArrayList<Object>) value);
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

	@Override
	public void remove(String name) {
		this.value.remove(this.name.indexOf(name));
		this.name.remove(this.name.indexOf(name));
	}
	
	public void removeElmt(String name, int index)
	{
		this.value.get(this.name.indexOf(name)).remove(index);
	}
	
	public void removeElmt(String name, String elmt)
	{
		this.value.get(this.name.indexOf(name)).remove(elmt);
	}
}
