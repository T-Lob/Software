package test;

import static org.junit.Assert.*;

import org.junit.Test;

import human.Patient;
import others.ED;
import rooms.RadioRoom;

public class RadioRoomTest {

	@Test
	public void testAddToWaitingQueue() {
		
		@SuppressWarnings("unused")
		ED ed = new ED("Saint-Denis");
		RadioRoom radioRoom = new RadioRoom("Saint-Denis");
		Patient patient = new Patient("Saint-Denis");
		
		radioRoom.addToWaitingQueue(patient);
		if (!radioRoom.getWaitingQueue().get(0).equals(patient) || radioRoom.getWaitingQueue().size() != 1)
			fail("The waiting queue doesn't contain the patient");
	}

}
