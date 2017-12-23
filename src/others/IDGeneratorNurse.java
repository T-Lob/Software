package others;

public class IDGeneratorNurse {
	private static IDGeneratorNurse instance = null;
	private int num;
		
	private IDGeneratorNurse(){}
		
	public static IDGeneratorNurse getInstance() {
		if (instance==null) {
			instance = new IDGeneratorNurse();
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