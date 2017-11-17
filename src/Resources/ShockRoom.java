package Resources;
import others.Database;
import others.ED;
import others.IDGenerator;

public class ShockRoom extends Room {
	
	public ShockRoom(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.ID = IDGenerator.getInstance().getNextID();
		this.ED.addToShockRoomList(this);
		
	}
	public ShockRoom(String EDname,String name) {
		this.ED=Database.getEDbyName(EDname);
		this.name=name;
		this.ID = IDGenerator.getInstance().getNextID();
		this.ED.addToShockRoomList(this);
		
		}
	public void setState(String state) {
		this.state=state;
		
		if (state.equalsIgnoreCase("empty")){
			this.ED.getShockRoomList().get(0).add(this);
			this.ED.getShockRoomList().get(1).remove(this);
			this.ED.getShockRoomList().get(2).remove(this);
			
		} else if (state.equalsIgnoreCase("onlyPatient")){
			this.ED.getShockRoomList().get(1).add(this);
			this.ED.getShockRoomList().get(0).remove(this);
			this.ED.getShockRoomList().get(2).remove(this);
			
		} else if (state.equalsIgnoreCase("full")) {
			this.ED.getShockRoomList().get(2).add(this);
			this.ED.getShockRoomList().get(1).remove(this);
			this.ED.getShockRoomList().get(0).remove(this);
		}

}
}
