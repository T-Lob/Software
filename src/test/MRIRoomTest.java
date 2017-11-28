package test;

import static org.junit.Assert.*;

import org.junit.Test;

import human.Patient;
import others.ED;
import rooms.MRIRoom;

public class MRIRoomTest {

	@Test
	public void testAddToWaitingQueue() {
		
		@SuppressWarnings("unused")
		ED ed = new ED("Saint-Denis");
		MRIRoom mriRoom = new MRIRoom("Saint-Denis");
		Patient patient = new Patient("Saint-Denis");
		
		mriRoom.addToWaitingQueue(patient);
		if (!mriRoom.getWaitingQueue().get(0).equals(patient) || mriRoom.getWaitingQueue().size() != 1)
			fail("The waiting queue doesn't contain the patient");
	}

}
