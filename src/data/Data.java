package data;

/*
 * Attention à bien passer des clones des data
 */

public interface Data {
	
	void set(String name, Object value);
	
	Object get(String name);
	
	boolean isPresent(String name);
	
	void remove(String name);
	
	void expire(String name, int val);
		
}
