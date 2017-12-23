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
			System.out.println("\n\nWrite your command, help to show all commands, exit to exit the CLI");
			str = sc.nextLine();
			str=str.replace("<", "").replace(">", "");
			String [] array =str.split(" ");
			String command = array[0];
				try {
					
				arguments= Arrays.copyOfRange(array, 1, array.length);
				if (command.equalsIgnoreCase("HELP")) {
					try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
						String sCurrentLine;
						System.out.println("---------HELP----------");
						while ((sCurrentLine = br.readLine()) != null) {
							System.out.println(sCurrentLine);
						}
						System.out.println("-----------------------");
						
				}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			
				else if (command.equalsIgnoreCase("CreateED")) {
					Database.addToGeneratedResources(new ED(arguments[0]));
					System.out.println(Database.getGeneratedEDs());
					
				}
				else if (command.equalsIgnoreCase("addRoom")) {
					Database.addToGeneratedResources(RoomFactory.createRoom(arguments[0], arguments[1], arguments[2]));
				} 
				/* Attention dans ce qui suit probleme si la PD n'est pas uniforme (un seul argument au lieu de deux), facile de regler mais flemme */
				else if (command.equalsIgnoreCase("addRadioService")) {
					if (arguments.length==3) {
						if (arguments[1].equalsIgnoreCase("uniform")) {
						System.out.println("Uniform Distribution needs another argument");
						}
						else {
							Database.addToGeneratedResources(HSFactory.createHS(arguments[0],"XRAY" ,ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]))));
							
						}
					}
					else {
						Database.addToGeneratedResources(HSFactory.createHS(arguments[0],"XRAY" ,ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3]))));
					}
				}
				else if (command.equalsIgnoreCase("addMRI")) {
					if (arguments.length==3) {
						if (arguments[1].equalsIgnoreCase("uniform")) {
						System.out.println("Uniform Distribution needs another argument");
						}
						else {
							Database.addToGeneratedResources(HSFactory.createHS(arguments[0],"MRISCAN" ,ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]))));
							
						}
					}
					else {
						Database.addToGeneratedResources(HSFactory.createHS(arguments[0],"MRISCAN" ,ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3]))));
					}
				}
				else if (command.equalsIgnoreCase("addBloodTest")) {
					if (arguments.length==3) {
						if (arguments[1].equalsIgnoreCase("uniform")) {
						System.out.println("Uniform Distribution needs another argument");
						}
						else {
							Database.addToGeneratedResources(HSFactory.createHS(arguments[0],"BLOODTEST" ,ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]))));
							
						}
					}
					else {
						Database.addToGeneratedResources(HSFactory.createHS(arguments[0],"BLOODTEST" ,ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3]))));
					}
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
					Database.getEDbyName(arguments[0]).setL1(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "L1"));
					}
				else if (command.equalsIgnoreCase("setL2arrivalDist")) {
					Database.getEDbyName(arguments[0]).setL2(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "L2"));
					}
				else if (command.equalsIgnoreCase("setL3arrivalDist")) {
					Database.getEDbyName(arguments[0]).setL3(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "L3"));
					}
				else if (command.equalsIgnoreCase("setL4arrivalDist")) {
					Database.getEDbyName(arguments[0]).setL4(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "L4"));
					}
				else if (command.equalsIgnoreCase("setL5arrivalDist")) {
					Database.getEDbyName(arguments[0]).setL5(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "L5"));
					}
				else if (command.equalsIgnoreCase("addPatient")) {
					if (arguments.length==1) {
						String[] nextPatient = Database.getEDbyName(arguments[0]).getToBeGeneratedPatients().get(0);
						Database.getEDbyName(arguments[0]).getToBeGeneratedPatients().remove(nextPatient);
						Database.addToGeneratedResources(new Patient(arguments[0], nextPatient[0], Double.parseDouble(nextPatient[1])));
						Database.addGeneration(nextPatient[0],nextPatient[2]);	
					}
					else {
						String[] nextPatient = Database.getEDbyName(arguments[0]).getToBeGeneratedPatients().get(0);
					
					Database.getEDbyName(arguments[0]).getToBeGeneratedPatients().remove(nextPatient);
					Database.addToGeneratedResources(new Patient(arguments[0], arguments[1], arguments[2], arguments[3], nextPatient[0], Double.parseDouble(nextPatient[1])));
					Database.addGeneration(nextPatient[0],nextPatient[2]);
					}
					}
				else if (command.equalsIgnoreCase("setPatientInsurance")) {
					Database.getEDbyName(arguments[0]).getPatientbyId(Integer.parseInt(arguments[1])).setHealthInsurance(arguments[2]);
					}
				else if (command.equalsIgnoreCase("executeEvent")) {
					if (arguments.length==1) {
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
					else if (arguments.length==2) {
						try {
						ED ed=Database.getEDbyName(arguments[0]);
						int i=Integer.parseInt(arguments[1]);
						if (i<=0) {
							System.out.println("Your number is negative");
						}
						while (i>0) {
							Event e1=ed.getEventQueue().get(0);
							Database.setTime(e1.getOccurenceTime());
							ed=Database.execute(e1, ed);
							System.out.println(e1.getType()+ " executed at time: " +Database.getTime());
							ed.getNewEnabledEvents().remove(e1.getType());
							ed.addToNewEnabledEvents("PatientArrival");
							ed.setNewEnabledEvents(Database.updateEnabledEvents(ed.getNewEnabledEvents(), ed));
							ed.setEventQueue(Database.updateEventQueue(ed));
							i--;
						}
						}
						catch (IndexOutOfBoundsException e) {
							System.out.println("Number is too high, final state displayed");
							Database.getEDbyName(arguments[0]).display();
						}
					}
				}
					else if (command.equalsIgnoreCase("display")) {
					ED ed=Database.getEDbyName(arguments[0]);
					ed.display();
					}
				else {
					System.out.println("\nThis command does not exist");
				}
				
				}
				catch (ArrayIndexOutOfBoundsException exception) {
					System.out.println("\nWrong arguments, type help if needed");
				}
				catch (NullPointerException exception) {
					System.out.println("\nWrong arguments, type help if needed");
				}
				}
			
		
	
	sc.close();
	System.out.println("on sort de la boucle");
	}
}
