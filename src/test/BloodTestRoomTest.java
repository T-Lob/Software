package test;

import static org.junit.Assert.*;

import org.junit.Test;

import human.Patient;
import others.ED;
import rooms.BloodTestRoom;

public class BloodTestRoomTest {

	@Test
	public void testAddToWaitingQueue() {
		
		@SuppressWarnings("unused")
		ED ed = new ED("Saint-Denis");
		BloodTestRoom bloodTestRoom = new BloodTestRoom("Saint-Denis");
		Patient patient = new Patient("Saint-Denis");
		
		bloodTestRoom.addToWaitingQueue(patient);
		if (!bloodTestRoom.getWaitingQueue().get(0).equals(patient) || bloodTestRoom.getWaitingQueue().size() != 1)
			fail("The waiting queue doesn't contain the patient");
	}

}
