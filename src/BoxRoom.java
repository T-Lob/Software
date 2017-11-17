import others.IDGenerator;

public class BoxRoom extends Room {
	public BoxRoom() {
		this.ID = IDGenerator.getInstance().getNextID();
	}
	
	public BoxRoom(String name) {
		this.name=name;
		this.ID = IDGenerator.getInstance().getNextID();
		
	}

}
