package Resources;
import others.Database;
import others.IDGenerator;

public class ShockRoom extends Room {
	
	public ShockRoom() {
		this.ID = IDGenerator.getInstance().getNextID();
		
	}
	public ShockRoom(String name) {
		this.name=name;
		this.ID = IDGenerator.getInstance().getNextID();
		
		}
	public void setState(String state) {
		this.state=state;
		
		if (state.equalsIgnoreCase("empty")){
			Database.getShockRoomList().get(0).add(this);
			Database.getShockRoomList().get(1).remove(this);
			Database.getShockRoomList().get(2).remove(this);
			
		} else if (state.equalsIgnoreCase("onlyPatient")){
			Database.getShockRoomList().get(1).add(this);
			Database.getShockRoomList().get(0).remove(this);
			Database.getShockRoomList().get(2).remove(this);
			
		} else if (state.equalsIgnoreCase("full")) {
			Database.getShockRoomList().get(2).add(this);
			Database.getShockRoomList().get(1).remove(this);
			Database.getShockRoomList().get(0).remove(this);
		}

}
}
