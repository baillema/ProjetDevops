package data;

public interface Data {
	
	void set(String name, Object value);
	
	Object get(String name);
	
	boolean isPresent(String name);
	
}
