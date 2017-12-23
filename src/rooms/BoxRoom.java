package rooms;
import human.Physician;
import others.*;

/**
 * Box Rooms are used for consultation, it has a physician assigned to it during consultation.
 */
public class BoxRoom extends Room {
	
	private Physician physician;
	
	/**
	 * This constructs a BoxRoom and adds it to the BoxRoomList.
	 * <br>Its default state is empty.
	 * <br>Its ID is calculated by the getNextID method.
	 * @param EDname The name of the ED in which you wish to create a BoxRoom.
	 */
	public BoxRoom(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.ID = IDGeneratorBoxRoom.getInstance().getNextID();
		this.name="BoxRoom" + String.valueOf(this.ID);
		this.state = "empty";
		Database.getEDbyName(EDname).upInstanciedBoxRooms();
		Database.getEDbyName(EDname).getBoxRoomList().get(0).add(this);
	}
	
	/**
	 * This constructs a BoxRoom and adds it to the BoxRoomList.
	 * <br>Its default state is empty.
	 * <br>Its ID is calculated by the getNextID method.
	 * @param EDname The name of the ED in which you wish to create a BoxRoom.
	 * @param name The name of this room
	 */
	public BoxRoom(String EDname,String name) {
		this.ED=Database.getEDbyName(EDname);
		this.name=name;
		this.ID = IDGeneratorBoxRoom.getInstance().getNextID();
		this.state = "empty";
		this.ED.upInstanciedBoxRooms();
		this.ED.getBoxRoomList().get(0).add(this);
	}
	
	/**
	 * This is used to set the state of this BoxRoom, it works by moving this BoxRoom from one line to the other in the BoxRoomList Arraylist
	 * (only if it is not already in this line)
	 * @param state The state in which you wish to set this BoxRoom.
	 */
	public void setState(String state) {
		this.state=state;
		
		if (state.equalsIgnoreCase("empty") & !(this.ED.getBoxRoomList().get(0).contains(this))){
			this.ED.getBoxRoomList().get(0).add(this);
			this.ED.getBoxRoomList().get(1).remove(this);
			this.ED.getBoxRoomList().get(2).remove(this);
			
		} else if (state.equalsIgnoreCase("onlyPatient") & !(this.ED.getBoxRoomList().get(1).contains(this))){
			this.ED.getBoxRoomList().get(1).add(this);
			this.ED.getBoxRoomList().get(0).remove(this);
			this.ED.getBoxRoomList().get(2).remove(this);
			
		} else if (state.equalsIgnoreCase("full") & !(this.ED.getBoxRoomList().get(2).contains(this))) {
			this.ED.getBoxRoomList().get(2).add(this);
			this.ED.getBoxRoomList().get(1).remove(this);
			this.ED.getBoxRoomList().get(0).remove(this);
		} else if (state.equalsIgnoreCase("reserved")){
			this.ED.getBoxRoomList().get(0).remove(this);
			this.ED.getBoxRoomList().get(1).remove(this);
			this.ED.getBoxRoomList().get(2).remove(this);
		}
	}

	/**
	 * 
	 * @return the physician currently to this room.
	 */
	public Physician getPhysician() {
		return physician;
	}

	/**
	 * Assigns a physician to this room
	 * @param physician the physician which is to install.
	 */
	public void setPhysician(Physician physician) {
		this.physician = physician;
	}
	
	
}
