package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import human.Physician;
import others.Database;
import others.ED;
import rooms.BoxRoom;

public class BoxRoomTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		@SuppressWarnings("unused")
		ED ed = new ED("Saint-Denis");
	}
	
	@Before
	public void setUp() throws Exception {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		ed.getGeneratedPatients().clear();
		ed.getArrivedPatients().clear();
		for (int i =0; i<3; i++) {
			ed.getNurseList().get(i).clear();
			ed.getRegisteredPatients().get(i).clear();
			ed.getPhysicianList().get(i).clear();
			ed.getTransporterList().get(i).clear();
		}
		ed.getRegisteredPatients().get(3).clear();
		ed.getRegisteredPatients().get(4).clear();
		ed.getWaitingForTransportPatients().clear();
		ed.getWaitingForVerdictPatients().clear();
		ed.getReleasedPatients().clear();
		for (int i = 0; i<2; i++) {
			ed.getBoxRoomList().get(i).clear();
			ed.getShockRoomList().get(i).clear();
		}
	}

	@Test
	public void testSetState() {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		BoxRoom boxRoom = new BoxRoom("Saint-Denis");
		
		boxRoom.setState("onlypatient");
		if (!(boxRoom.getState().equalsIgnoreCase("onlypatient")))
			fail("Boxroom State not onlypatient");
		if (!(ed.getBoxRoomList().get(1).contains(boxRoom)) || ed.getBoxRoomList().get(1).size() != 1)
			fail("Boxroom not added to the only patient box rooms");
		if (ed.getBoxRoomList().get(0).size() != 0)
			fail("Boxroom added to the empty box rooms");
		if (ed.getBoxRoomList().get(2).size() != 0)
			fail("Boxroom added to the full box rooms");
		
		
		boxRoom.setState("empty");
		if (!(boxRoom.getState().equalsIgnoreCase("empty")))
			fail("Boxroom State is not empty");
		if (ed.getBoxRoomList().get(1).size() != 0)
			fail("Boxroom added to the only patient box rooms");
		if (!(ed.getBoxRoomList().get(0).contains(boxRoom)) || ed.getBoxRoomList().get(0).size() != 1)
			fail("Boxroom not added to the empty box rooms");
		if (ed.getBoxRoomList().get(2).size() != 0)
			fail("Boxroom added to the full box rooms");
		
		boxRoom.setState("full");
		if (!(boxRoom.getState().equalsIgnoreCase("full")))
			fail("Boxroom State is not full");
		if (ed.getBoxRoomList().get(1).size() != 0)
			fail("Boxroom added to the only patient box rooms");
		if (ed.getBoxRoomList().get(0).size() != 0)
			fail("Boxroom added to the empty box rooms");
		if (!(ed.getBoxRoomList().get(2).contains(boxRoom)) || ed.getBoxRoomList().get(2).size() != 1)
			fail("Boxroom not added to the full box rooms");
	}

	@Test
	public void testSetPhysician() {
		
		BoxRoom boxRoom = new BoxRoom("Saint-Denis");
		Physician phys = new Physician("Saint-Denis");
		
		boxRoom.setPhysician(phys);
		if (!boxRoom.getPhysician().equals(phys))
			fail("Wrong Physician in the box room");
	}

}
