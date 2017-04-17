package data;

import java.util.ArrayList;

public abstract class AbstractList extends AbstractData {

	ArrayList<ArrayList<Object>> value = new ArrayList<ArrayList<Object>>();
	
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
	
	public boolean isValideValue(String value) {
		boolean res = false;
		if(value.endsWith("\"") && value.startsWith("\"")) res = true;
		return res;
	}
	
	public boolean isElmtPresent(String name, String elmt)
	{
		return this.value.get(this.name.indexOf(name)).contains(elmt);
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
}
