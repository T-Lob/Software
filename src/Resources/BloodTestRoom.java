package Resources;

import others.Database;

public class BloodTestRoom extends Room {

	public BloodTestRoom(String EDname) {
		this.ED=Database.getEDbyName(EDname);
	}

}
