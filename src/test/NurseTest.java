package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import human.Nurse;
import others.Database;
import others.ED;

public class NurseTest {
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		@SuppressWarnings("unused")
		ED ed = new ED("Saint-Denis");
	}
	
	@Test
	public void testSetState() {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		Nurse nurse = new Nurse("Saint-Denis");
		
		nurse.setState("busy");
		if (!(nurse.getState().equalsIgnoreCase("busy")))
			fail("Nurse State not busy");
		if (!(ed.getNurseList().get(1).contains(nurse)))
			fail("Nurse not added to the occupied nurses");
		if (ed.getNurseList().get(0).contains(nurse))
			fail("Nurse added to the idle nurses");
		if (ed.getNurseList().get(2).contains(nurse))
			fail("Nurse added to the offduty nurses");
		
		nurse.setState("idle");
		if (!(nurse.getState().equalsIgnoreCase("idle")))
			fail("Nurse State is not idle");
		if (ed.getNurseList().get(1).contains(nurse))
			fail("Nurse added to the occupied nurses");
		if (!(ed.getNurseList().get(0).contains(nurse)))
			fail("Nurse not added to the idle nurses");
		if (ed.getNurseList().get(2).contains(nurse))
			fail("Nurse added to the offduty nurses");
		
		nurse.setState("offduty");
		if (!(nurse.getState().equalsIgnoreCase("offduty")))
			fail("Nurse State is not offduty");
		if (ed.getNurseList().get(1).contains(nurse))
			fail("Nurse added to the occupied nurses");
		if (ed.getNurseList().get(0).contains(nurse))
			fail("Nurse added to the idle nurses");
		if (!(ed.getNurseList().get(2).contains(nurse)))
			fail("Nurse not added to the offduty nurses");
	}

	@Test
	public void testRegistration() {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		Nurse nurse = new Nurse("Saint-Denis");
	}

	@Test
	public void testInstallation() {
		fail("Not yet implemented");
	}

	@Test
	public void testEndOfInstallation() {
		fail("Not yet implemented");
	}

}
