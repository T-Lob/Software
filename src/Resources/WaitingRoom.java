package Resources;

import others.Database;

public class WaitingRoom extends Room {

	public WaitingRoom(String EDname) {
		this.ED=Database.getEDbyName(EDname);
	}

}
