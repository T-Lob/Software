package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import human.Patient;
import human.Physician;
import others.Database;
import others.ED;
import rooms.BoxRoom;
import rooms.Room;

public class PatientTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		@SuppressWarnings("unused")
		ED ed = new ED("Saint-Denis");
	}

	@Test
	public void testGetName() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (!patient.getName().equalsIgnoreCase("smo"))
			fail("The patient has a wrong name");
	}

	@Test
	public void testSetName() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		patient.setName("Pierre");
		if(!patient.getName().equalsIgnoreCase("Pierre"))
			fail("The patient name has not been changed");
	}

	@Test
	public void testGetSurname() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (!patient.getSurname().equalsIgnoreCase("koco"))
			fail("The patient has a wrong surname");
	}

	@Test
	public void testSetSurname() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		patient.setSurname("Blondin");
		if(!patient.getSurname().equalsIgnoreCase("blondin"))
			fail("The patient surname has not been changed");
	}

	@Test
	public void testGetID() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (patient.getID() != 6){
			fail("The patient has no ID");
		}
	}

	@Test
	public void testGetHealthIsurance() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (!patient.getHealthInsurance().equalsIgnoreCase("Silver"))
			fail("The patient has a wrong health insurance");
	}

	@Test
	public void testGetSeverityLevel() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (!patient.getSeverityLevel().equalsIgnoreCase("l3"))
			fail("The patient has a wrong severity level");
	}

	@Test
	public void testGetState() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (!patient.getState().equalsIgnoreCase("generated"))
			fail("The patient has not generated as a state");
	}

	@Test
	public void testSetLocation() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		Room location = new BoxRoom("Saint-Denis");
		
		patient.setLocation(location);
		if (!patient.getLocation().equals(location))
			fail("The patient's location hasn't been changed");
	}

	@Test
	public void testAddEventToHistory() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		String[] event = {"registration","10h"};
		patient.addEventToHistory(event);
		patient.getHistory();
	}

	@Test
	public void testGetArrivalTime() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (patient.getArrivalTime() != 10)
			fail("The patient has a wrong arrival time");
	}

	@Test
	public void testSetArrivalTime() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		patient.setArrivalTime(15);
		if (patient.getArrivalTime() != 15)
			fail("The patient's arrival time hasn't been changed");
	}

	@Test
	public void testSetPhysician() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		Physician phys = new Physician("Saint-Denis");
		
		patient.setPhysician(phys);
		if (!patient.getPhysician().equals(phys))
			fail("The patient's has the wrong physician");
	}

	@Test
	public void testGetBill() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (patient.getBill() != 0)
			fail("The patient has a wrong bill");
	}

	@Test
	public void testAddToBill() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		patient.addToBill(5);
		if (patient.getBill() != 5)
			fail("The patient's bill has not been added");
	}


	@Test
	public void testSetDestination() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		Room destination = new BoxRoom("Saint-Denis");
		
		patient.setDestination(destination);
		if (!patient.getDestination().equals(destination))
			fail("The patient has a wrong destination");
	}

	@Test
	public void testGetLevel() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		if (patient.getLevel() != 3)
			fail("getLevel() doesn't work");
	}

	@Test
	public void testCharges() {
		
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		patient.addToBill(20);
		if (patient.charges() != 16)
			fail("The charges are wrong for silver patients");
		patient.setHealthInsurance("Gold");
		if (patient.charges() != 10)
			fail("the charges are wrong for gold patients");
		patient.setHealthInsurance("None");
		if (patient.charges() != 20)
			fail("the charges are wrong for not insured patients");
	}

	@Test
	public void testSetState() {
		
		ED ed = Database.getEDbyName("Saint-Denis");
		Patient patient = new Patient("Saint-Denis", "Smo", "Koco", "Silver", "L3", 10);
		
		patient.setState("arrived");
		if (!patient.getState().equalsIgnoreCase("arrived"))
			fail("Patient state hasn't been changed");
		if (ed.getGeneratedPatients().contains(patient))
			fail("Generated Patients List contains arrived patient");
		if (!ed.getArrivedPatients().contains(patient))
			fail("Arrived Patients List doesn't contain arrived patient");
		if (ed.getRegisteredPatients().get(patient.getLevel()-1).contains(patient))
			fail("Registered Patients List contains arrived patient");
		if (ed.getWaitingForTransportPatients().contains(patient))
			fail("Waiting for Transport Patients List contains arrived patient");
		if(ed.getWaitingForVerdictPatients().contains(patient))
			fail("Waiting for Verdict Patients List contains arrived patient");
		if(ed.getReleasedPatients().contains(patient))
			fail("Released Patients List contains arrived patient");
		
		patient.setState("registered");
		if (ed.getGeneratedPatients().contains(patient))
			fail("Generated Patients List contains registered patient");
		if (ed.getArrivedPatients().contains(patient))
			fail("Arrived Patients List contains registered patient");
		if (!ed.getRegisteredPatients().get(patient.getLevel()-1).contains(patient))
			fail("Registered Patients List doesn't contain registered patient");
		if (ed.getWaitingForTransportPatients().contains(patient))
			fail("Waiting for Transport Patients List contains registered patient");
		if(ed.getWaitingForVerdictPatients().contains(patient))
			fail("Waiting for Verdict Patients List contains registered patient");
		if(ed.getReleasedPatients().contains(patient))
			fail("Released Patients List contains registered patient");
		
		patient.setState("waitingfortransport");
		if (ed.getGeneratedPatients().contains(patient))
			fail("Generated Patients List contains waiting for transport patient");
		if (ed.getArrivedPatients().contains(patient))
			fail("Arrived Patients List contains waiting for transport patient");
		if (ed.getRegisteredPatients().get(patient.getLevel()-1).contains(patient))
			fail("Registered Patients List contains waiting for transport patient");
		if (!ed.getWaitingForTransportPatients().contains(patient))
			fail("Waiting for Transport Patients List doesn't contain waiting for transport patient");
		if(ed.getWaitingForVerdictPatients().contains(patient))
			fail("Waiting for Verdict Patients List contains waiting for transport patient");
		if(ed.getReleasedPatients().contains(patient))
			fail("Released Patients List contains waiting for transport patient");
		
		patient.setState("waitingforverdict");
		if (ed.getGeneratedPatients().contains(patient))
			fail("Generated Patients List contains waiting for verdict patient");
		if (ed.getArrivedPatients().contains(patient))
			fail("Arrived Patients List contains waiting for verdict patient");
		if (ed.getRegisteredPatients().get(patient.getLevel()-1).contains(patient))
			fail("Registered Patients List contains waiting for verdict patient");
		if (ed.getWaitingForTransportPatients().contains(patient))
			fail("Waiting for Transport Patients List contains waiting for verdict patient");
		if(!ed.getWaitingForVerdictPatients().contains(patient))
			fail("Waiting for Verdict Patients List doesn't contain waiting for verdict patient");
		if(ed.getReleasedPatients().contains(patient))
			fail("Released Patients List contains waiting for verdict patient");
		
		patient.setState("released");
		if (ed.getGeneratedPatients().contains(patient))
			fail("Generated Patients List contains released patient");
		if (ed.getArrivedPatients().contains(patient))
			fail("Arrived Patients List contains released patient");
		if (ed.getRegisteredPatients().get(patient.getLevel()-1).contains(patient))
			fail("Registered Patients List contains released patient");
		if (ed.getWaitingForTransportPatients().contains(patient))
			fail("Waiting for Transport Patients List contains released patient");
		if(ed.getWaitingForVerdictPatients().contains(patient))
			fail("Waiting for Verdict Patients List contains released patient");
		if(!ed.getReleasedPatients().contains(patient))
			fail("Released Patients List doesn't contain released patient");
	}

}
