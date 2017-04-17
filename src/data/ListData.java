package data;

import java.util.ArrayList;
import java.util.Timer;

public class ListData implements Data {

	ArrayList<String> name = new ArrayList<String>();
	ArrayList<ArrayList<Object>> value = new ArrayList<ArrayList<Object>>();
	ArrayList<Timer> timer = new ArrayList<Timer>();
	
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

	@Override
	public boolean isPresent(String name) {
		return this.name.contains(name);
	}

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

	public boolean removeElmt(String name, String elmt)
	{
		//Remove only the first occurence of elmt
		boolean res = false;
		if(isPresent(name))
		{
			if(isElmtPresent(name, elmt)) 
			{
				this.value.get(this.name.indexOf(name)).remove(elmt);
				res = true;
			}
		}
		return res;
	}
	
	public boolean isElmtPresent(String name, String elmt)
	{
		return this.value.get(this.name.indexOf(name)).contains(elmt);
	}

	@Override
	public boolean isValideValue(String value) {
		boolean res = false;
		if(value.endsWith("\"") && value.startsWith("\"")) res = true;
		return res;
	}
}
