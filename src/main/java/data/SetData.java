package data;

import java.util.ArrayList;

public class SetData extends AbstractList {
	
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
					this.value.get(this.name.indexOf(name)).add(value);
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
		if(isPresent(name))	return this.value.get(this.name.indexOf(name));
		else return null;
	}
	
	/**
	 * Checks if the value "value" is stored in the Set "name".
	 * @param value  the value
	 * @param name	 name of the Set
	 * @return true if the object is stored in the Set, else false
	 */
	private boolean isValuePresent(Object value, String name) {
		return this.value.get(this.name.indexOf(name)).contains(value);
	}

}
