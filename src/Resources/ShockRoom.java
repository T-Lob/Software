package Resources;
import others.IDGenerator;

public class ShockRoom extends Room {
	
	public ShockRoom() {
		this.ID = IDGenerator.getInstance().getNextID();
		
	}
	public ShockRoom(String name) {
		this.name=name;
		this.ID = IDGenerator.getInstance().getNextID();
		
		}

}
