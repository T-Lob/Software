package others;

public class IDGeneratorBoxRoom {
	private static IDGeneratorBoxRoom instance = null;
	private int num;
		
	private IDGeneratorBoxRoom(){}
		
	public static IDGeneratorBoxRoom getInstance() {
		if (instance==null) {
			instance = new IDGeneratorBoxRoom();
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