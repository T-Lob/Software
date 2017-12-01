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
	
	public int getNextID(){
		return num++;
	}

}
