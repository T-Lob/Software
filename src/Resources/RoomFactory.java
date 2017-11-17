package Resources;

import others.ED;

public class RoomFactory {
	
	public Room createRoom(ED ed, String roomType) {
		if (roomType == null) {
			return null;
		}
		
		if (roomType.equalsIgnoreCase("BOXROOM")){
			return new BoxRoom(ed);
		} else if (roomType.equalsIgnoreCase("SHOCKROOM")){
			return new ShockRoom(ed);
		} else if (roomType.equalsIgnoreCase("WAITINGROOM")) {
			return new WaitingRoom(ed);
		} else if (roomType.equalsIgnoreCase("RADIOROOM")){
			return new RadioRoom(ed);
		} else if (roomType.equalsIgnoreCase("MRIROOM")){
			return new MRIRoom(ed);
		} else if (roomType.equalsIgnoreCase("BLOODTEST LABORATORY")) {
			return new BloodTestRoom(ed);
		}
		
		return null;
	}
}
