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
	
	private void insertSorted(Object value, String name) {
		if(isPresent(name)) System.out.println("Value already present in this set.");
		
		int size = this.value.get(this.name.indexOf(name)).size();
		for(int i=0; i<size; i++)
		{
			if(this.value.get(this.name.indexOf(name)).get(i).toString().compareToIgnoreCase(name)<0)
			{
				this.value.get(this.name.indexOf(name)).set(i,name);
				break;
			}
		}
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
		this.value.get(this.name.indexOf(name)).remove(elmt);
	}
	
	private boolean isValuePresent(Object value, String name) {
		return this.value.get(this.name.indexOf(name)).contains(value);
	}
	
}
