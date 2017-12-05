package others;

public class IDGeneratorEvent {
	private static IDGeneratorEvent instance = null;
	private int num;
		
	private IDGeneratorEvent(){}
		
	public static IDGeneratorEvent getInstance() {
		if (instance==null) {
			instance = new IDGeneratorEvent();
		}
		return instance;
	}
	
	/**
	 * This methods works by simples increments of 1.
	 * @return an unique ID.
	 */
	public int getNextID(){
		return num++;
	}

}
