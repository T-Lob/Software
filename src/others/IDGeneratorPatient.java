package others;

public class IDGeneratorPatient {
	private static IDGeneratorPatient instance = null;
	private int num;
		
	private IDGeneratorPatient(){}
		
	public static IDGeneratorPatient getInstance() {
		if (instance==null) {
			instance = new IDGeneratorPatient();
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