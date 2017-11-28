package test;

import static org.junit.Assert.*;

import org.junit.Test;

import others.ED;
import rooms.BloodTestRoom;
import rooms.BoxRoom;
import rooms.MRIRoom;
import rooms.RadioRoom;
import rooms.Room;
import rooms.RoomFactory;
import rooms.ShockRoom;
import rooms.WaitingRoom;

public class RoomFactoryTest {

	@Test
	public void testCreateRoom() {
		
		@SuppressWarnings("unused")
		ED ed = new ED("Saint-Denis");
		RoomFactory factory = new RoomFactory();
		
		Room boxRoom = factory.createRoom("Saint-Denis", "boxroom");
		Room shockRoom = factory.createRoom("Saint-Denis", "shockroom");
		Room waitingRoom = factory.createRoom("Saint-Denis", "waitingroom");
		Room radioRoom = factory.createRoom("Saint-Denis", "radioroom");
		Room mriRoom = factory.createRoom("Saint-Denis", "mriroom");
		Room bloodTestRoom = factory.createRoom("Saint-Denis", "bloodtest laboratory");
		
		if (!(boxRoom instanceof BoxRoom))
			fail("mauvaise instanciation de boxRoom");
		
		if (!(shockRoom instanceof ShockRoom))
			fail("mauvaise instanciation de shockRoom");
		
		if (!(waitingRoom instanceof WaitingRoom))
			fail("mauvaise instanciation de waitingRoom");
		
		if (!(radioRoom instanceof RadioRoom))
			fail("mauvaise instanciation de radioRoom");
		
		if (!(mriRoom instanceof MRIRoom))
			fail("mauvaise instanciation de mriRoom");
		
		if (!(bloodTestRoom instanceof BloodTestRoom))
			fail("mauvaise instanciation de bloodTestRoom");
	}
}
