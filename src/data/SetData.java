package data;

import java.util.ArrayList;

public class SetData extends AbstractList {
	
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

	@Override
	public Object get(String name) {
		if(isPresent(name))	return this.value.get(this.name.indexOf(name));
		else return null;
	}
	
	private boolean isValuePresent(Object value, String name) {
		return this.value.get(this.name.indexOf(name)).contains(value);
	}

}
