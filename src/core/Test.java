package core;


import Human.*;
import Resources.*;
import others.ED;

public class Test {
	public static void main(String[] args) {
		ED ed = new ED("ED");
		
		HRFactory hrfactory = new HRFactory();
		RoomFactory roomfactory = new RoomFactory();
		Nurse nurse = (Nurse) hrfactory.createHR(ed,"nurse");
		BoxRoom boxroom= (BoxRoom) roomfactory.createRoom(ed,"boxroom");
		Patient patient = new Patient(ed);
				
		System.out.println(ed.getNurseList());
		System.out.println(ed.getBoxRoomList());
		nurse.installation(patient, boxroom);
		
		System.out.println(ed.getNurseList());
		System.out.println(ed.getBoxRoomList());

		
		
		
		
		
			
			
		
	
	}

}
