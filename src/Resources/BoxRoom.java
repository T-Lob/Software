package Resources;
import others.*;

public class BoxRoom extends Room {
	public BoxRoom(ED ed) {
		this.ED=ed;
		this.ID = IDGenerator.getInstance().getNextID();
		this.ED.addToBoxRoomList(this);
	}
	
	public BoxRoom(ED ed,String name) {
		this.ED=ed;
		this.name=name;
		this.ID = IDGenerator.getInstance().getNextID();
		this.ED.addToBoxRoomList(this);
		
	}
	
	public void setState(String state) {
		this.state=state;
		
		if (state.equalsIgnoreCase("empty")){
			this.ED.getBoxRoomList().get(0).add(this);
			this.ED.getBoxRoomList().get(1).remove(this);
			this.ED.getBoxRoomList().get(2).remove(this);
			
		} else if (state.equalsIgnoreCase("onlyPatient")){
			this.ED.getBoxRoomList().get(1).add(this);
			this.ED.getBoxRoomList().get(0).remove(this);
			this.ED.getBoxRoomList().get(2).remove(this);
			
		} else if (state.equalsIgnoreCase("full")) {
			this.ED.getBoxRoomList().get(2).add(this);
			this.ED.getBoxRoomList().get(1).remove(this);
			this.ED.getBoxRoomList().get(0).remove(this);
		}
	
	
}

}
