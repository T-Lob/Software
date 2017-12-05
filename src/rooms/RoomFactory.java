package rooms;

public class RoomFactory {
	
	/**
	 * Creates a room between BoxRoom, schockRoom, WaitingRoom, RadioRoom, MRIRoom and BlodtestRoom.
	 * @param EDname The ED in which you wish to create the room
	 * @param roomType The type of room to create.
	 * @return the room created
	 */
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
