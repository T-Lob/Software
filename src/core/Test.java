package core;


import Human.*;
import Resources.*;
import others.Database;
import others.ED;

public class Test {
	public static void main(String[] args) {
		ED ed = new ED("ED");
		ED hosto=new ED("Hosto");
		
		HRFactory hrfactory = new HRFactory();
		RoomFactory roomfactory = new RoomFactory();
		Nurse nurse = (Nurse) hrfactory.createHR("ED","nurse");
		BoxRoom boxroom= (BoxRoom) roomfactory.createRoom("ED","boxroom");
		Physician physician = (Physician) hrfactory.createHR("HOSTO", "physician");
		Patient patient = new Patient("ED");
		Patient patient2 = new Patient("Hosto");
				
		System.out.println(ed.getNurseList());
		System.out.println(ed.getBoxRoomList());
		System.out.println(hosto.getPhysicianList());
		System.out.println(hosto.getBoxRoomList());
		nurse.installation(patient, boxroom);
		physician.consultation(patient2);
		
		System.out.println(ed.getNurseList());
		System.out.println(ed.getBoxRoomList());
		System.out.println(hosto.getPhysicianList());
		System.out.println(physician.getHistoryPatients());
		patient2.getHistory();

		
		
		
		
		
			
			
		
	
	}

}
