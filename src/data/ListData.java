package data;

import java.util.ArrayList;

public class ListData extends AbstractList {
	
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
	
	@Override
	public Object get(String name) {
		if(isPresent(name))	return this.value.get(this.name.indexOf(name));
		else return null;
	}

}
