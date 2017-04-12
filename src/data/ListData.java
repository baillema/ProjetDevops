package data;

import java.util.ArrayList;
import java.util.function.Predicate;

public class ListData implements Data {

	ArrayList<String> name = new ArrayList<String>();
	ArrayList<ArrayList<Object>> value = new ArrayList<ArrayList<Object>>();
		
	@Override
	public void set(String name, Object value) {
		if(isPresent(name))
		{
			this.value.get(this.name.indexOf(name)).add(value);
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

	@Override
	public void remove(String name) {
		this.value.remove(this.name.indexOf(name));
	}
	
	public void removeElmt(String name, int index)
	{
		this.value.get(this.name.indexOf(name)).remove(index);
	}
	
	public void removeElmt(String name, String elmt)
	{
		//Remove only the first occurence of elmt elmt
		this.value.get(this.name.indexOf(name)).remove(elmt);
	}
}
