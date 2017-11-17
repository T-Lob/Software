package Resources;

import others.Database;

public class RadioRoom extends Room {

	public RadioRoom(String EDname) {
		this.ED=Database.getEDbyName(EDname);
	}

}
