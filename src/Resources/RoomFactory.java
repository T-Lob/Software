package Resources;

public class RoomFactory {
	
	public Room createRoom(String EDname, String roomType) {
		if (roomType == null) {
			return null;
		}
		
		if (roomType.equalsIgnoreCase("BOXROOM")){
			return new BoxRoom(EDname);
		} else if (roomType.equalsIgnoreCase("SHOCKROOM")){
			return new ShockRoom(EDname);
		} else if (roomType.equalsIgnoreCase("WAITINGROOM")) {
			return new WaitingRoom(EDname);
		} else if (roomType.equalsIgnoreCase("RADIOROOM")){
			return new RadioRoom(EDname);
		} else if (roomType.equalsIgnoreCase("MRIROOM")){
			return new MRIRoom(EDname);
		} else if (roomType.equalsIgnoreCase("BLOODTEST LABORATORY")) {
			return new BloodTestRoom(EDname);
		}
		
		return null;
	}
}
