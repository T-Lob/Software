package others;

public class IDGeneratorTransporter {
	private static IDGeneratorTransporter instance = null;
	private int num;
		
	private IDGeneratorTransporter(){}
		
	public static IDGeneratorTransporter getInstance() {
		if (instance==null) {
			instance = new IDGeneratorTransporter();
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