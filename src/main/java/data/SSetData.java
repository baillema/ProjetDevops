package data;

import java.util.ArrayList;

public class SSetData extends AbstractList {
	
	@Override
	public boolean set(String name, Object value) {
		boolean res = false;
		if(isValideValue((String)value))
		{
			if(isPresent(name))
			{
				if(isValuePresent(value, name)) res = false;
				else
				{
					insertSorted(value, name);
					res = true;
				}
			}
			else
			{
				this.name.add(name);
				ArrayList<Object> val = new ArrayList<Object>();
				val.add(value);
				this.value.add(val);
				res = true;
			}
		}
		return res;		
	}

	@Override
	public Object get(String name) {
		return this.value.get(this.name.indexOf(name));
	}

	private void insertSorted(Object value, String name) {
		if(isValuePresent(value, name)) System.out.println("Value already present in this set.");
		
		this.value.get(this.name.indexOf(name)).add(value);
		this.value.get(this.name.indexOf(name)).sort((o1, o2) -> o1.toString().compareToIgnoreCase(o2.toString()));
	}
			
	private boolean isValuePresent(Object value, String name) {
		return this.value.get(this.name.indexOf(name)).contains(value);
	}

}
