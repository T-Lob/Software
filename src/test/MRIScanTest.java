package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import healthServices.MRIScan;
import human.Patient;
import human.Physician;
import others.Database;
import others.ED;

public class MRIScanTest {

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
		ed.mriRoom.setState("empty");
	}

	@Test
	public void testSetDuration() {
		
		MRIScan mriScan = new MRIScan("Saint-Denis");
		int duration = 15;
		
		mriScan.setDuration(duration);
		if (mriScan.getDuration() != duration)
			fail("Wrong MRI scan duration");
	}

	@Test
	public void testCheck() {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		Patient patient = new Patient("Saint-Denis");
		MRIScan mriScan = new MRIScan("Saint-Denis");
		
		ed.mriRoom.addToWaitingQueue(patient);
		mriScan.check(patient);
		if (!ed.mriRoom.getState().equalsIgnoreCase("full"))
			fail("MRI Room not occuppied");
		if (ed.mriRoom.getWaitingQueue().size() != 0)
			fail("Patient still in waiting queue");
		if (!patient.getState().equalsIgnoreCase("checked"))
			fail("Patient not currently checked");
	}

	@Test
	public void testEndCheck() {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		Patient patient = new Patient("Saint-Denis");
		MRIScan mriScan = new MRIScan("Saint-Denis");
		Physician physician = new Physician("Saint-Denis");
		
		patient.setPhysician(physician);
		mriScan.endCheck(patient);
		if (!patient.getState().equalsIgnoreCase("waitingforverdict"))
			fail("Patient has wrong state");
		if (!ed.mriRoom.getState().equalsIgnoreCase("empty"))
			fail("MRI room not emptied");
		if (!patient.getLocation().equals(ed.waitingRoom))
			fail("Patient has wrong location");
		if (!mriScan.getOutcome().equalsIgnoreCase("MRI done for the patient "  + patient.getName() +  "in "+ String.valueOf(mriScan.getDuration()) + " minutes"))
			fail("Wrong outcome");
		if (!physician.getLastMessage().equalsIgnoreCase(mriScan.getOutcome()))
			fail("The physician has not received the outcome");
		if (patient.getBill() != mriScan.getCost())
			fail("Patient has wrong bill");
	}

}
