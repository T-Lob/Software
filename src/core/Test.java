package core;


import Human.*;
import Resources.*;
import others.Database;

public class Test {
	public static void main(String[] args) {
		Database.createED("ED");
		
		HRFactory hrfactory = new HRFactory();
		RoomFactory roomfactory = new RoomFactory();
		Nurse nurse = (Nurse) hrfactory.createHR("nurse");
		BoxRoom boxroom= (BoxRoom) roomfactory.createRoom("boxroom");
		Patient patient = new Patient();
				
		System.out.println(Database.getNurseList());
		System.out.println(Database.getBoxRoomList());
		nurse.installation(patient, boxroom);
		
		System.out.println(Database.getNurseList());
		System.out.println(Database.getBoxRoomList());

		
		
		
		
		
			
			
		
	
	}

}
