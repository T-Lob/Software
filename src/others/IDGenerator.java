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
	
	public int getNextID(){
		return num++;
	}

}
