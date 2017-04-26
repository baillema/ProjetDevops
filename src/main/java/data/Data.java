package data;

/*
 * Attention à bien passer des clones des data
 */

public interface Data {
	
	/**
	 * Adds a value "value" which will be referenced under the name "name". The name must not be already used.
	 * @param name   name of the value
	 * @param value	  value to store
	 * @return true if operations went well, false if an error occured (usually, name already used)
	 */
	boolean set(String name, Object value);
	
	/**
	 * Returns the object stored under the name "name"
	 * @param name  name of the object
	 * @return	the object stored under the name "name"
	 */
	Object get(String name);
	
	/**
	 * Checks if the name "name" is currently used
	 * @param name the name to check
	 * @return true if the name is currently used, else false
	 */
	boolean isPresent(String name);
	
	/**
	 * Tries to remove the value stored under the name "name"
	 * @param name the name of the value to remove
	 * @return true if operations went well, else false
	 */
	boolean remove(String name);
	
	/**
	 * Checks if the value is valid
	 * @param value the value to check
	 * @return true if the value is valid, else false
	 */
	boolean isValideValue(String value);
		
}
