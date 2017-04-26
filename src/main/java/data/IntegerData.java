package data;

import java.util.ArrayList;

public class IntegerData extends AbstractData {
	
	ArrayList<Integer> value = new ArrayList<Integer>();
	
	/* (non-Javadoc)
	 * @see data.Data#set(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean set(String name, Object value) {
		boolean res = false;
		if(!isPresent(name) && isValideValue((String) value))
		{
			this.name.add(name);
			this.value.add(Integer.parseInt((String) value));
			res = true;
		}
		
		return res;
	}
	
	/**
	 * Add a value "value" which will be referenced under the name "name". The name must not be already used.
	 * @param name
	 * @param value
	 */
	public void set(String name, int value) {
		if(!isPresent(name))
		{
			this.name.add(name);
			this.value.add(value);
		}
	}

	
	/* (non-Javadoc)
	 * @see data.Data#get(java.lang.String)
	 */
	@Override
	public Object get(String name) {
		if(isPresent(name))return this.value.get(this.name.indexOf(name));
		else return null;	
	}
	
	/* (non-Javadoc)
	 * @see data.Data#remove(java.lang.String)
	 */
	@Override
	public boolean remove(String name) {
		boolean res = false;
		if(isPresent(name))
		{
			this.value.remove(this.name.indexOf(name));
			this.name.remove(this.name.indexOf(name));
			res = true;
		}
		
		return res;
	}

	/**
	 * Increases by 1 the value of the integer linked to the name "name"
	 * @param name  name linked to the integer to increase
	 * @return true if the operation went well
	 * 		   false if the name doesn't exist
	 */
	public boolean incr(String name) {
		boolean res = false;
		if(isPresent(name))
		{
			int val = this.value.get(this.name.indexOf(name)).intValue()+1;
			this.value.set(this.name.indexOf(name), val);
			res = true;
		}
		
		return res;
	}

	/* (non-Javadoc)
	 * @see data.Data#isValideValue(java.lang.String)
	 */
	@Override
	public boolean isValideValue(String value) {
		boolean res;
		try  
		{  
			Integer.parseInt(value);
			res = true;
		}
		catch(NumberFormatException nfe){ res = false; }
		
		return res;
	}	
}
