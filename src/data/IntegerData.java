package data;


public class IntegerData implements Data {
	
	String [] name = new String[100];
	int [] value= new int[100];
	int current = 0;
	
	@Override
	public void set(String name, Object value) {
		if(isPresent(name)) System.out.println("Name already present in this scope.");
		else
		{
			this.name[current] = name;
			this.value[current] = (int) value;
			current++;
		}
	}

	@Override
	public Object get(String name) {
		int tmp =  0;
		if(!isPresent(name)) System.out.println("Name not present in this scope.");
		else for(int i=0; i<current; i++) {if(this.name[i].equals(name)) tmp=this.value[i];}
		return (Object) tmp;
	}
	
	@Override
	public boolean isPresent(String name) {
		boolean res = false;	
		for(int i=0; i<current && !res; i++){ if(this.name[i].equals(name)) res=true; }			
		return res;
	}
	
}
