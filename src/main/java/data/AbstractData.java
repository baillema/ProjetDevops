package data;

import java.util.ArrayList;

public abstract class AbstractData implements Data {

	ArrayList<String> name = new ArrayList<String>();
	
	public boolean isPresent(String name) {
		return this.name.contains(name);		
	}
}
