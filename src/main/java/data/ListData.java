package data;

import java.util.ArrayList;

public class ListData extends AbstractList {
	
	/* (non-Javadoc)
	 * @see data.Data#set(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean set(String name, Object value) {
		boolean res = false;
		if(isValideValue((String)value))
		{
			if(isPresent(name)) this.value.get(this.name.indexOf(name)).add(value);
			
			else
			{
				this.name.add(name);
				ArrayList<Object> val = new ArrayList<Object>();
				val.add(value);
				this.value.add(val);	
			}
			res = true;
		}

		return res;
	}

	/**
	 * Add the value "value" to the List "name". If the List doesn't exist, it will be created.
	 * @param name  name of the List
	 * @param value  value to be added to the List
	 * @return true if operations went well
	 * 		   false if the value is invalid
	 */
	public boolean set(String name, String value) {
		boolean res = false;
		if(isValideValue(value))
		{
			if(isPresent(name))
			{
				this.value.get(this.name.indexOf(name)).add(value);
				res = true;
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

}
