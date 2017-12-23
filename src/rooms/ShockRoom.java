package rooms;
import human.Physician;
import others.Database;
import others.IDGenerator;

public class ShockRoom extends Room {
	
	private Physician physician;
	
	/**
	 * This constructs a ShockRoom and adds it to the ShockRoomList.
	 * <br>Its default state is empty.
	 * <br>Its ID is calculated by the getNextID method.
	 * @param EDname The name of the ED in which you wish to create a ShockRoom.
	 */
	public ShockRoom(String EDname) {
		this.ED=Database.getEDbyName(EDname);
		this.ID = IDGenerator.getInstance().getNextID();
		this.name="ShockRoom";
		this.state = "empty";
		//this.ED.upInstanciedBoxRooms();
		this.ED.getShockRoomList().get(0).add(this);
		
	}
	
	/**
	 * This constructs a ShockRoom and adds it to the ShockRoomList.
	 * <br>Its default state is empty.
	 * <br>Its ID is calculated by the getNextID method.
	 * @param EDname The name of the ED in which you wish to create a ShockRoom.
	 * @param name The name of this room
	 */
	public ShockRoom(String EDname,String name) {
		this.ED=Database.getEDbyName(EDname);
		this.name=name;
		this.ID = IDGenerator.getInstance().getNextID();
		this.state = "empty";
		this.ED.upInstanciedBoxRooms();
		this.ED.getShockRoomList().get(0).add(this);	
	}
	
	/**
	 * This is used to set the state of this ShockRoom, it works by moving this ShockRoom from one line to the other in the ShockRoomList Arraylist
	 * (only if it is not already in this line)
	 * @param state The state in which you wish to set this ShockRoom.
	 */
	public void setState(String state) {
		this.state=state;
		
		if (state.equalsIgnoreCase("empty") & !(this.ED.getShockRoomList().get(0).contains(this))){
			this.ED.getShockRoomList().get(0).add(this);
			this.ED.getShockRoomList().get(1).remove(this);
			this.ED.getShockRoomList().get(2).remove(this);
			
		} else if (state.equalsIgnoreCase("onlyPatient") & !(this.ED.getShockRoomList().get(1).contains(this))){
			this.ED.getShockRoomList().get(1).add(this);
			this.ED.getShockRoomList().get(0).remove(this);
			this.ED.getShockRoomList().get(2).remove(this);
			
		} else if (state.equalsIgnoreCase("full") & !(this.ED.getShockRoomList().get(2).contains(this))) {
			this.ED.getShockRoomList().get(2).add(this);
			this.ED.getShockRoomList().get(1).remove(this);
			this.ED.getShockRoomList().get(0).remove(this);
		}  else if (state.equalsIgnoreCase("reserved")){
			this.ED.getShockRoomList().get(0).remove(this);
			this.ED.getShockRoomList().get(1).remove(this);
			this.ED.getShockRoomList().get(2).remove(this);
		}
	}

	/**
	 * 
	 * @return the physician assigned to this SchockRoom.
	 */
	public Physician getPhysician() {
		return physician;
	}

	/**
	 * Sets the physician assigned to this SchockRoom.
	 * @param physician The physician to assign.
	 */
	public void setPhysician(Physician physician) {
		this.physician = physician;
	}
	
	
}
