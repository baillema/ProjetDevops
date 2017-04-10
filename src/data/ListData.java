package data;

import java.util.ArrayList;

public class ListData implements Data {

	ArrayList<Object> listV = new ArrayList<Object>();
	ArrayList<String> listN = new ArrayList<String>();
	
	@Override
	public void set(String name, Object value) {
		listV.add(value);
		listN.add(name);
	}

	@Override
	public Object get(String name) {
		return listV.get(listN.indexOf(name));
	}

	@Override
	public boolean isPresent(String name) {
		return listN.contains(name);
	}

}
