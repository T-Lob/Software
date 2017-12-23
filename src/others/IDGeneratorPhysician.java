package others;

public class IDGeneratorPhysician {
	private static IDGeneratorPhysician instance = null;
	private int num;
		
	private IDGeneratorPhysician(){}
		
	public static IDGeneratorPhysician getInstance() {
		if (instance==null) {
			instance = new IDGeneratorPhysician();
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