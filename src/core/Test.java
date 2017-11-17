package core;


import Human.*;
import Resources.*;
import others.Database;
import others.ED;

public class Test {
	public static void main(String[] args) {
		ED ed = new ED("ED");
		ED ed2=new ED("Hosto");
		
		HRFactory hrfactory = new HRFactory();
		RoomFactory roomfactory = new RoomFactory();
		Nurse nurse = (Nurse) hrfactory.createHR("ED","nurse");
		BoxRoom boxroom= (BoxRoom) roomfactory.createRoom("ED","boxroom");
		Patient patient = new Patient("ED");
				
		System.out.println(ed.getNurseList());
		System.out.println(ed.getBoxRoomList());
		nurse.installation(patient, boxroom);
		
		System.out.println(ed.getNurseList());
		System.out.println(ed.getBoxRoomList());
		System.out.println(Database.getEDbyName("Hosto"));
		System.out.println(Database.getEDbyName("ED"));

		
		
		
		
		
			
			
		
	
	}

}
