package core;


import human.*;
import rooms.*;
import others.ED;

public class Test {
	public static void main(String[] args) {
		ED ed = new ED("ED");
		ED hosto=new ED("Hosto");
		
		HRFactory hrfactory = new HRFactory();
		RoomFactory roomfactory = new RoomFactory();
		Nurse nurse2 = (Nurse) hrfactory.createHR("Hosto","nurse");
		BoxRoom boxroom= (BoxRoom) roomfactory.createRoom("Hosto","boxroom");
		Physician physician = (Physician) hrfactory.createHR("HOSTO", "physician");
		Patient patient2 = new Patient("Hosto");
				
		System.out.println(ed.getBoxRoomList());
		System.out.println(hosto.getPhysicianList());
		System.out.println(hosto.getBoxRoomList());
		nurse2.installation(patient2, boxroom);
		physician.consultation(patient2);
		
		System.out.println(ed.getNurseList());
		System.out.println(ed.getBoxRoomList());
		System.out.println(hosto.getPhysicianList());
		System.out.println(physician.getHistoryPatients());
		patient2.getHistory();	
	
	}

}
