package data;

/*
 * Attention à bien passer des clones des data
 */

public interface Data {
	
	boolean set(String name, Object value);
	
	Object get(String name);
	
	boolean isPresent(String name);
	
	boolean remove(String name);
	
	boolean isValideValue(String value);
		
}
