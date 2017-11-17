package Resources;
import others.Database;
import others.IDGenerator;

public class BoxRoom extends Room {
	public BoxRoom() {
		this.ID = IDGenerator.getInstance().getNextID();
		Database.addToBoxRoomList(this);
	}
	
	public BoxRoom(String name) {
		this.name=name;
		this.ID = IDGenerator.getInstance().getNextID();
		Database.addToBoxRoomList(this);
		
	}
	
	public void setState(String state) {
		this.state=state;
		
		if (state.equalsIgnoreCase("empty")){
			Database.getBoxRoomList().get(0).add(this);
			Database.getBoxRoomList().get(1).remove(this);
			Database.getBoxRoomList().get(2).remove(this);
			
		} else if (state.equalsIgnoreCase("onlyPatient")){
			Database.getBoxRoomList().get(1).add(this);
			Database.getBoxRoomList().get(0).remove(this);
			Database.getBoxRoomList().get(2).remove(this);
			
		} else if (state.equalsIgnoreCase("full")) {
			Database.getBoxRoomList().get(2).add(this);
			Database.getBoxRoomList().get(1).remove(this);
			Database.getBoxRoomList().get(0).remove(this);
		}
	
	
}

}
