package data;

import java.util.ArrayList;

public class SSetData extends AbstractList {
	
	/* (non-Javadoc)
	 * @see data.Data#set(java.lang.String, java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see data.Data#get(java.lang.String)
	 */
	@Override
	public Object get(String name) {
		return this.value.get(this.name.indexOf(name));
	}

	private void insertSorted(Object value, String name) {
		if(isValuePresent(value, name)) System.out.println("Value already present in this set.");
		
		this.value.get(this.name.indexOf(name)).add(value);
		this.value.get(this.name.indexOf(name)).sort((o1, o2) -> o1.toString().compareToIgnoreCase(o2.toString()));
	}
			
	/**
	 * Checks if the value "value" is stored in the  sorted Set "name".
	 * @param value  the value
	 * @param name	 name of the sorted Set
	 * @return true if the object is stored in the sorted Set, else false
	 */
	private boolean isValuePresent(Object value, String name) {
		return this.value.get(this.name.indexOf(name)).contains(value);
	}

}
