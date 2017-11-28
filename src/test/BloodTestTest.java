package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import healthServices.BloodTest;
import others.Database;
import others.ED;

public class BloodTestTest {

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
	public void testSetDuration() {
		
		BloodTest bloodTest = new BloodTest("Saint-Denis");
		int duration = 15;
		
		bloodTest.setDuration(duration);
		if (bloodTest.getDuration() != duration)
			fail("Wrong blood test duration");
	}

	@Test
	public void testCheck() {
		fail("Not yet implemented");
	}

	@Test
	public void testEndCheck() {
		fail("Not yet implemented");
	}

}
