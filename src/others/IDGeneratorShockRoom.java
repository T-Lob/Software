package others;

public class IDGeneratorShockRoom {
	private static IDGeneratorShockRoom instance = null;
	private int num;
		
	private IDGeneratorShockRoom(){}
		
	public static IDGeneratorShockRoom getInstance() {
		if (instance==null) {
			instance = new IDGeneratorShockRoom();
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