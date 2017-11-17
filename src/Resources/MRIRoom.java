package Resources;

import others.Database;

public class MRIRoom extends Room {

	public MRIRoom(String EDname) {
		this.ED=Database.getEDbyName(EDname);
	}

}
