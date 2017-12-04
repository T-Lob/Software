package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import healthServices.BloodTest;
import human.Patient;
import human.Physician;
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
		ed.bloodTestRoom.setState("empty");
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
		
		ED ed = Database.getEDbyName("Saint-Denis");
		Patient patient = new Patient("Saint-Denis");
		BloodTest bloodTest = new BloodTest("Saint-Denis");
		
		ed.bloodTestRoom.addToWaitingQueue(patient);
		bloodTest.check(patient);
		if (!ed.bloodTestRoom.getState().equalsIgnoreCase("full"))
			fail("Blood Test Room not occuppied");
		if (ed.bloodTestRoom.getWaitingQueue().size() != 0)
			fail("Patient still in waiting queue");
		if (!patient.getState().equalsIgnoreCase("checked"))
			fail("Patient not currently checked");
	}

	@Test
	public void testEndCheck() {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		Patient patient = new Patient("Saint-Denis");
		BloodTest bloodTest = new BloodTest("Saint-Denis");
		Physician physician = new Physician("Saint-Denis");
		
		patient.setPhysician(physician);
		bloodTest.endCheck(patient);
		if (!patient.getState().equalsIgnoreCase("waitingforverdict"))
			fail("Patient has wrong state");
		if (!ed.bloodTestRoom.getState().equalsIgnoreCase("empty"))
			fail("Blood test room not emptied");
		if (!patient.getLocation().equals(ed.waitingRoom))
			fail("Patient has wrong location");
		if (!bloodTest.getOutcome().equalsIgnoreCase("Bloodtest done for the patient "  + patient.getName() +  "in "+ String.valueOf(bloodTest.getDuration()) + " minutes"))
			fail("Wrong outcome");
		if (!physician.getLastMessage().equalsIgnoreCase(bloodTest.getOutcome()))
			fail("The physician has not received the outcome");
		if (patient.getBill() != bloodTest.getCost())
			fail("Patient has wrong bill");
	}

}
