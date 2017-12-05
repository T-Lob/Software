package rooms;

import others.Database;

public class WaitingRoom extends Room {
	
	/**
	 * This constructs a waiting room
	 * @param EDname the ED in which you wish to construct a room
	 */
	public WaitingRoom(String EDname) {
		this.ED=Database.getEDbyName(EDname);
	}

}
