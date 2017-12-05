package others;

public class IDGenerator {
	private static IDGenerator instance = null;
	private int num;
		
	private IDGenerator(){}
		
	public static IDGenerator getInstance() {
		if (instance==null) {
			instance = new IDGenerator();
		}
		return instance;
	}
	
	/**
	 * This method works by simple increments of 1
	 * @return an unique ID
	 */
	public int getNextID(){
		return num++;
	}

}
