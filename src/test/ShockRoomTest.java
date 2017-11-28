package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import human.Physician;
import others.Database;
import others.ED;
import rooms.ShockRoom;

public class ShockRoomTest {

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
		ShockRoom shockRoom = new ShockRoom("Saint-Denis");
		
		shockRoom.setState("onlypatient");
		if (!(shockRoom.getState().equalsIgnoreCase("onlypatient")))
			fail("ShockRoom State not onlypatient");
		if (!(ed.getShockRoomList().get(1).contains(shockRoom)) || ed.getShockRoomList().get(1).size() != 1)
			fail("ShockRoom not added to the only patient shock rooms");
		if (ed.getShockRoomList().get(0).size() != 0)
			fail("ShockRoom added to the empty shock rooms");
		if (ed.getShockRoomList().get(2).size() != 0)
			fail("ShockRoom added to the full shock rooms");
		
		
		shockRoom.setState("empty");
		if (!(shockRoom.getState().equalsIgnoreCase("empty")))
			fail("ShockRoom State is not empty");
		if (ed.getShockRoomList().get(1).size() != 0)
			fail("ShockRoom added to the only patient shock rooms");
		if (!(ed.getShockRoomList().get(0).contains(shockRoom)) || ed.getShockRoomList().get(0).size() != 1)
			fail("ShockRoom not added to the empty shock rooms");
		if (ed.getShockRoomList().get(2).size() != 0)
			fail("ShockRoom added to the full shock rooms");
		
		shockRoom.setState("full");
		if (!(shockRoom.getState().equalsIgnoreCase("full")))
			fail("ShockRoom State is not full");
		if (ed.getShockRoomList().get(1).size() != 0)
			fail("ShockRoom added to the only patient shock rooms");
		if (ed.getShockRoomList().get(0).size() != 0)
			fail("ShockRoom added to the empty shock rooms");
		if (!(ed.getShockRoomList().get(2).contains(shockRoom)) || ed.getShockRoomList().get(2).size() != 1)
			fail("ShockRoom not added to the full shock rooms");
	}

	@Test
	public void testSetPhysician() {
		
		ShockRoom shockRoom = new ShockRoom("Saint-Denis");
		Physician phys = new Physician("Saint-Denis");
		
		shockRoom.setPhysician(phys);
		if (!shockRoom.getPhysician().equals(phys))
			fail("Wrong Physician in the shock room");
	}

}
