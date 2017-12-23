package others;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

import events.Event;
import healthServices.HSFactory;
import healthServices.HealthServices;
import human.HRFactory;
import human.Patient;
import maths.ProbabilityDistributionFactory;
import rooms.RoomFactory;

public class CLI {
	static String str="";
	static String [] arguments;
	private static final String FILENAME = "./help.txt";

	
	public static void main(String[] args) {
		ReadFile.Read();
		Scanner sc = new Scanner(System.in);
		while(!(str.equalsIgnoreCase("EXIT"))) {
			System.out.println("Write your command, help to show all commands");
			str = sc.nextLine();
			str=str.replace("<", "").replace(">", "");
			String [] array =str.split(" ");
			String command = array[0];
			System.out.println("Command : " + command);
			if (command.equalsIgnoreCase("HELP")) {
				try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
					String sCurrentLine;
					while ((sCurrentLine = br.readLine()) != null) {
						System.out.println(sCurrentLine);
					}
					
			}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (array.length >1) {
				//String argument=array[1];
				arguments= Arrays.copyOfRange(array, 1, array.length);
				System.out.println(Arrays.toString(arguments));
				System.out.println("Vous avez saisi : " + str);
			
				if (command.equalsIgnoreCase("CreateED")) {
					Database.addToGeneratedResources(new ED(arguments[0]));
					System.out.println(Database.getGeneratedEDs());
					
				}
				else if (command.equalsIgnoreCase("addRoom")) {
					Database.addToGeneratedResources(RoomFactory.createRoom(arguments[0], arguments[1], arguments[2]));
				} 
				/* Attention dans ce qui suit probleme si la PD n'est pas uniforme (un seul argument au lieu de deux), facile de regler mais flemme */
				else if (command.equalsIgnoreCase("addRadioService")) {
					Database.addToGeneratedResources(HSFactory.createHS(arguments[0],"XRAY" ,ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3]))));
				}
				else if (command.equalsIgnoreCase("addMRI")) {
					Database.addToGeneratedResources(HSFactory.createHS(arguments[0],"MRISCAN" ,ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3]))));
				}
				else if (command.equalsIgnoreCase("addBloodTest")) {
					Database.addToGeneratedResources(HSFactory.createHS(arguments[0],"BLOODTEST" ,ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3]))));
				}
				else if (command.equalsIgnoreCase("addNurse")) {
					if (arguments.length==1) {
						Database.addToGeneratedResources(HRFactory.createHR(arguments[0],"NURSE"));
					}
					else if (arguments.length==3) {
						Database.addToGeneratedResources(HRFactory.createHR(arguments[0],"NURSE", arguments[1], arguments[2]));	
					}
					
				}
				else if (command.equalsIgnoreCase("addPhysi") || command.equalsIgnoreCase("addPhysician")) {
					if (arguments.length==1) {
						Database.addToGeneratedResources(HRFactory.createHR(arguments[0],"PHYSICIAN"));
					}
					else if (arguments.length==3) {
						Database.addToGeneratedResources(HRFactory.createHR(arguments[0],"PHYSICIAN", arguments[1], arguments[2]));	
					}
				}
				else if (command.equalsIgnoreCase("addTransp") || command.equalsIgnoreCase("addTransporter")) {
					if (arguments.length==1) {
						Database.addToGeneratedResources(HRFactory.createHR(arguments[0],"Transporter"));
					}
					else if (arguments.length==3) {
						Database.addToGeneratedResources(HRFactory.createHR(arguments[0],"Transporter", arguments[1], arguments[2]));	
					}
				}
				else if (command.equalsIgnoreCase("setL1arrivalDist")) {
					Database.getEDbyName(arguments[0]).setL1(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "l1"));
					}
				else if (command.equalsIgnoreCase("setL2arrivalDist")) {
					Database.getEDbyName(arguments[0]).setL2(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "l1"));
					}
				else if (command.equalsIgnoreCase("setL3arrivalDist")) {
					Database.getEDbyName(arguments[0]).setL3(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "l1"));
					}
				else if (command.equalsIgnoreCase("setL4arrivalDist")) {
					Database.getEDbyName(arguments[0]).setL4(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "l1"));
					}
				else if (command.equalsIgnoreCase("setL5arrivalDist")) {
					Database.getEDbyName(arguments[0]).setL5(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "l1"));
					}
				else if (command.equalsIgnoreCase("addPatient")) {
					String[] nextPatient = Database.getEDbyName(arguments[0]).getToBeGeneratedPatients().get(0);
					Database.getEDbyName(arguments[0]).getToBeGeneratedPatients().remove(nextPatient);
					Database.addToGeneratedResources(new Patient(arguments[0], arguments[1], arguments[2], arguments[3], nextPatient[0], Double.parseDouble(nextPatient[1])));
					Database.addGeneration(nextPatient[0],nextPatient[2]);
					}
				else if (command.equalsIgnoreCase("setPatientInsurance")) {
					Database.getEDbyName(arguments[0]).getPatientbyId(Integer.parseInt(arguments[1])).setHealthInsurance(arguments[2]);
					}
				else if (command.equalsIgnoreCase("executeEvent")) {
					ED ed=Database.getEDbyName(arguments[0]);
					Event e1=ed.getEventQueue().get(0);
					Database.setTime(e1.getOccurenceTime());
					ed=Database.execute(e1, ed);
					System.out.println(e1.getType()+ " executed at time: " +Database.getTime());
					ed.getNewEnabledEvents().remove(e1.getType());
					ed.addToNewEnabledEvents("PatientArrival");
					ed.setNewEnabledEvents(Database.updateEnabledEvents(ed.getNewEnabledEvents(), ed));
					ed.setEventQueue(Database.updateEventQueue(ed));
					}
				else {
					System.out.println("This command does not exit");
				}
				
				
			}
			
			else {
				System.out.println("Please add arguments");
			}
				
				
				}
			
		
	
	sc.close();
	System.out.println("on sort de la boucle");
	}
}
