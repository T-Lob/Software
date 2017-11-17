
public class RoomFactory {
	
	public Room createRoom(String roomType) {
		if (roomType == null) {
			return null;
		}
		
		if (roomType.equalsIgnoreCase("BOXROOM")){
			return new BoxRoom();
		} else if (roomType.equalsIgnoreCase("SHOCKROOM")){
			return new ShockRoom();
		} else if (roomType.equalsIgnoreCase("WAITINGROOM")) {
			return new WaitingRoom();
		} else if (roomType.equalsIgnoreCase("RADIOROOM")){
			return new RadioRoom();
		} else if (roomType.equalsIgnoreCase("MRIROOM")){
			return new MRIRoom();
		} else if (roomType.equalsIgnoreCase("BLOODTEST LABORATORY")) {
			return new BloodTestRoom();
		}
		
		return null;
	}
}
