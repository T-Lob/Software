package others;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
	static String s1="";
	static String s2="";
	static String s3="";
	static String [] arguments;
	static ArrayList<String> testScenario = new ArrayList<String>();
	static int testScenarioIndex =-1;
	static boolean testOnGoing=false;
	static boolean loadedSimergy=false;
	static boolean loadedTestScenario=false;
	private static final String FILENAME = "./help.txt";
	private static final String FILENAME2 = "./testScenario.txt";

	
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		System.out.println("\n\nWould you like to load my_simergy.ini file ? (y/n)");
		while (!s1.equalsIgnoreCase("Y") & !s1.equalsIgnoreCase("N")) {	
			System.out.println("Please type \"y\" for yes or \"n\" for no");
			s1 = sc1.nextLine();
		}
			if(s1.equalsIgnoreCase("Y")) {
				ReadFile.Read(); 
				loadedSimergy=true;
			}
			else if (s1.equalsIgnoreCase("N")) {
			System.out.println("You did not load my_simergy.ini.");
			}
		
		System.out.println("\n\nWould you like to load testScenario.txt ? (y/n)");
		while (!s2.equalsIgnoreCase("Y") & !s2.equalsIgnoreCase("N")) {	
			System.out.println("Please type \"y\" for yes or \"n\" for no");
			s2 = sc2.nextLine();
		}
			if(s2.equalsIgnoreCase("Y")) {
				testOnGoing=true;
				try (BufferedReader br = new BufferedReader(new FileReader(FILENAME2))) {
					String sCurrentLine;
					System.out.println("----------------Test Scenario---------------");
					while ((sCurrentLine = br.readLine()) != null) {
						testScenario.add(sCurrentLine);
					}
							str=testScenario.get(0);
					}
				catch (IOException e) {
					e.printStackTrace();
				} 
				
			}
			else if (s2.equalsIgnoreCase("N")) {
			System.out.println("You did not load testScenario.txt.");
			}
			
		
			Scanner sc = new Scanner(System.in);
			while(!(str.equalsIgnoreCase("EXIT"))) {
				
				if (!testOnGoing) {
				System.out.println("\n\nWrite your command, help to show all commands, exit to exit the CLI");
				str = sc.nextLine();
				}
				if (testOnGoing) {
					Scanner sc3 = new Scanner(System.in);
					System.out.println("\nPress Enter to get to the next step in the test Scenario, type end to stop the test scenario ");
					s3 = sc3.nextLine();
					 if (s3.equalsIgnoreCase("END")) {
						testOnGoing=false;
						System.out.println("\n\n-----------------Test Scenario ended-----------------");
						str = "help";
					}
					 if (testOnGoing) {
						try {
					testScenarioIndex++;
					str=testScenario.get(testScenarioIndex);
					System.out.println(str);
						}
						catch (IndexOutOfBoundsException e) {
							testOnGoing=false;
							loadedTestScenario=true;
							System.out.println("\n\n-----------------Test Scenario ended-----------------");
							System.out.println("\n\nWrite your command, help to show all commands, exit to exit the CLI");
							str = sc.nextLine();
	
						}
					 }
					}
				str=str.replace("<", "").replace(">", "").replace(",", "");
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
						System.out.println("Created ED " + arguments[0]);
						
					}
					else if (command.equalsIgnoreCase("addRoom")) {
						Database.addToGeneratedResources(RoomFactory.createRoom(arguments[0], arguments[1], arguments[2]));
						System.out.println("Created " + arguments[1] +  arguments[2] + "in " + Database.getEDbyName(arguments[0]).getEDname());
					} 
			
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
						System.out.println("Created Xray service with " + arguments[1] +  " distribution in " + Database.getEDbyName(arguments[0]).getEDname());
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
						System.out.println("Created MRI service with " + arguments[1] +  " distribution in " + Database.getEDbyName(arguments[0]).getEDname());
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
						System.out.println("Created Bloodtest service with " + arguments[1] +  " distribution in " + Database.getEDbyName(arguments[0]).getEDname());
					}
					else if (command.equalsIgnoreCase("addNurse")) {
						if (arguments.length==1) {
							Database.addToGeneratedResources(HRFactory.createHR(arguments[0],"NURSE"));
						}
						else if (arguments.length==3) {
							Database.addToGeneratedResources(HRFactory.createHR(arguments[0],"NURSE", arguments[1], arguments[2]));	
						}
						System.out.println("Created Nurse in " + Database.getEDbyName(arguments[0]).getEDname());
						
					}
					else if (command.equalsIgnoreCase("addPhysi") || command.equalsIgnoreCase("addPhysician")) {
						if (arguments.length==1) {
							Database.addToGeneratedResources(HRFactory.createHR(arguments[0],"PHYSICIAN"));
						}
						else if (arguments.length==3) {
							Database.addToGeneratedResources(HRFactory.createHR(arguments[0],"PHYSICIAN", arguments[1], arguments[2]));	
						}
						System.out.println("Created Physician in " + Database.getEDbyName(arguments[0]).getEDname());
					}
					else if (command.equalsIgnoreCase("addTransp") || command.equalsIgnoreCase("addTransporter")) {
						if (arguments.length==1) {
							Database.addToGeneratedResources(HRFactory.createHR(arguments[0],"Transporter"));
						}
						else if (arguments.length==3) {
							Database.addToGeneratedResources(HRFactory.createHR(arguments[0],"Transporter", arguments[1], arguments[2]));	
						}
						System.out.println("Created Transporter in " + Database.getEDbyName(arguments[0]).getEDname());
					} 
					else if (command.equalsIgnoreCase("setL1arrivalDist")) {
						ED ed =Database.getEDbyName(arguments[0]);
						String[] L1Patient=null;
						if (arguments.length==3) {
							if (arguments[1].equalsIgnoreCase("uniform")) {
							System.out.println("Uniform Distribution needs another argument");
							}
							else {
								Database.getEDbyName(arguments[0]).setL1(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2])), "L1"));
								System.out.println("Setted L1 arrival distribution to " + arguments[1] + " (" +arguments[2]+") " +  " in " + Database.getEDbyName(arguments[0]).getEDname());
							} 
						}
							else {
							Database.getEDbyName(arguments[0]).setL1(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "L1"));
							System.out.println("Setted L1 arrival distribution to " + arguments[1] + " (" +arguments[2] +"," +arguments[3]+") " +  " in " + Database.getEDbyName(arguments[0]).getEDname());
							} 
						for (String [] s: ed.getToBeGeneratedPatients()) {
							if (s[0].equalsIgnoreCase("L1")) {
								L1Patient=s;
							}
						}
						ed.getToBeGeneratedPatients().remove(L1Patient);
						ed.getL1().addGeneration(ed);
						ed.sortTBGP();
					}
					else if (command.equalsIgnoreCase("setL2arrivalDist")) {
						ED ed =Database.getEDbyName(arguments[0]);
						String[] L2Patient=null;
						if (arguments.length==3) {
							if (arguments[1].equalsIgnoreCase("uniform")) {
							System.out.println("Uniform Distribution needs another argument");
							}
							else {
								Database.getEDbyName(arguments[0]).setL2(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2])), "L2"));
								System.out.println("Setted L2 arrival distribution to " + arguments[1] + " (" +arguments[2]+") " +  "in " + Database.getEDbyName(arguments[0]).getEDname());
							} 
						}
							else {
							Database.getEDbyName(arguments[0]).setL2(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "L2"));
							System.out.println("Setted L2 arrival distribution to " + arguments[1] + " (" +arguments[2] +"," +arguments[3]+") " +  "in " + Database.getEDbyName(arguments[0]).getEDname());
							} 
						for (String [] s: ed.getToBeGeneratedPatients()) {
							if (s[0].equalsIgnoreCase("L2")) {
								L2Patient=s;
							}
						}
						ed.getToBeGeneratedPatients().remove(L2Patient);
						ed.getL2().addGeneration(ed);
						ed.sortTBGP();
					}
						
					else if (command.equalsIgnoreCase("setL3arrivalDist")) {
						ED ed =Database.getEDbyName(arguments[0]);
						String[] L3Patient=null;
						if (arguments.length==3) {
							if (arguments[1].equalsIgnoreCase("uniform")) {
							System.out.println("Uniform Distribution needs another argument");
							}
							else {
								Database.getEDbyName(arguments[0]).setL3(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2])), "L3"));
								System.out.println("Setted L3 arrival distribution to " + arguments[1] + " (" +arguments[2]+") " +  "in " + Database.getEDbyName(arguments[0]).getEDname());
							} 
						}
							else {
							Database.getEDbyName(arguments[0]).setL3(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "L3"));
							System.out.println("Setted L3 arrival distribution to " + arguments[1] + " (" +arguments[2] +"," +arguments[3]+") " +  "in " + Database.getEDbyName(arguments[0]).getEDname());
							 
							} 
						for (String [] s: ed.getToBeGeneratedPatients()) {
							if (s[0].equalsIgnoreCase("L3")) {
								L3Patient=s;
							}
						}
						ed.getToBeGeneratedPatients().remove(L3Patient);
						ed.getL3().addGeneration(ed);
						ed.sortTBGP();
					}
					else if (command.equalsIgnoreCase("setL4arrivalDist")) {
						ED ed =Database.getEDbyName(arguments[0]);
						String[] L4Patient=null;
						if (arguments.length==3) {
							if (arguments[1].equalsIgnoreCase("uniform")) {
							System.out.println("Uniform Distribution needs another argument");
							}
							else {
								Database.getEDbyName(arguments[0]).setL4(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2])), "L4"));
								System.out.println("Setted L4 arrival distribution to " + arguments[1] + " (" +arguments[2]+") " +  "in " + Database.getEDbyName(arguments[0]).getEDname());
							} 
						}
							else {
							Database.getEDbyName(arguments[0]).setL4(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "L4"));
							System.out.println("Setted L4 arrival distribution to " + arguments[1] + " (" +arguments[2] +"," +arguments[3]+") " +  "in " + Database.getEDbyName(arguments[0]).getEDname());
							} 
						for (String [] s: ed.getToBeGeneratedPatients()) {
							if (s[0].equalsIgnoreCase("L4")) {
								L4Patient=s;
							}
						}
						ed.getToBeGeneratedPatients().remove(L4Patient);
						ed.getL4().addGeneration(ed);
						ed.sortTBGP();
					}
					else if (command.equalsIgnoreCase("setL5arrivalDist")) {
						ED ed =Database.getEDbyName(arguments[0]);
						String[] L5Patient=null;
						if (arguments.length==3) {
							if (arguments[1].equalsIgnoreCase("uniform")) {
							System.out.println("Uniform Distribution needs another argument");
							}
							else {
								Database.getEDbyName(arguments[0]).setL5(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2])), "L5"));
								System.out.println("Setted L5 arrival distribution to " + arguments[1] + " (" +arguments[2]+")" +  " in " + Database.getEDbyName(arguments[0]).getEDname());
							} 
						}
							else {
							Database.getEDbyName(arguments[0]).setL5(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "L5"));
							System.out.println("Setted L5 arrival distribution to " + arguments[1] + " (" +arguments[2] +"," +arguments[3]+") " +  "in " + Database.getEDbyName(arguments[0]).getEDname());
							} 
						for (String [] s: ed.getToBeGeneratedPatients()) {
							if (s[0].equalsIgnoreCase("L5")) {
								L5Patient=s;
							}
						}
						ed.getToBeGeneratedPatients().remove(L5Patient);
						ed.getL5().addGeneration(ed);
						ed.sortTBGP();
					}
					else if (command.equalsIgnoreCase("addPatient")) {
						if (arguments.length==1) {
							String[] nextPatient = Database.getEDbyName(arguments[0]).getToBeGeneratedPatients().get(0);
							Database.getEDbyName(arguments[0]).getToBeGeneratedPatients().remove(nextPatient);
							Database.addToGeneratedResources(new Patient(arguments[0], nextPatient[0], Double.parseDouble(nextPatient[1])));
							Database.addGeneration(nextPatient[0],nextPatient[2]);	
							System.out.println("Added " + nextPatient[0] +" Patient to " + Database.getEDbyName(arguments[0]).getEDname());
							
						}
						else if (arguments.length==4) {
							String[] nextPatient = Database.getEDbyName(arguments[0]).getToBeGeneratedPatients().get(0);
						
						Database.getEDbyName(arguments[0]).getToBeGeneratedPatients().remove(nextPatient);
						Database.addToGeneratedResources(new Patient(arguments[0], arguments[1], arguments[2], arguments[3], nextPatient[0], Double.parseDouble(nextPatient[1])));
						Database.addGeneration(nextPatient[0],nextPatient[2]);
						System.out.println("Added " + nextPatient[0] +" Patient (" + arguments[1] + " " + arguments[2] +") to " + Database.getEDbyName(arguments[0]).getEDname());
						}
						}
					else if (command.equalsIgnoreCase("setPatientInsurance")) {
						Database.getEDbyName(arguments[0]).getPatientbyId(Integer.parseInt(arguments[1])).setHealthInsurance(arguments[2]);
						System.out.println("Setted "+ Database.getEDbyName(arguments[0]).getPatientbyId(Integer.parseInt(arguments[1])).getName() + " health insurance to " + arguments[2]);
						}
					else if (command.equalsIgnoreCase("executeEvent")) {
						if (arguments.length==1) {
							ED ed=Database.getEDbyName(arguments[0]);
							ed.sortEventQueue();
							Event e1=ed.getEventQueue().get(0);
							Database.setTime(e1.getOccurenceTime());
							ed=Database.execute(e1, ed);
							System.out.println(e1.getType()+ " executed at time: " +Database.getTime() + " in " + Database.getEDbyName(arguments[0]).getEDname());
							ed.getNewEnabledEvents().remove(e1.getType());
							ed.addToNewEnabledEvents("PatientArrival");
							ed.setNewEnabledEvents(Database.updateEnabledEvents(ed.getNewEnabledEvents(), ed));
							ed.setEventQueue(Database.updateEventQueue(ed));
	
							
						}
						else if (arguments.length==2) {
							try {
							ED ed=Database.getEDbyName(arguments[0]);
							int i=Integer.parseInt(arguments[1]);
							ed.sortEventQueue();
							if (i<=0) {
								System.out.println("Your number is negative");
							}
							while (i>0) {
								Event e1=ed.getEventQueue().get(0);
								Database.setTime(e1.getOccurenceTime());
								ed=Database.execute(e1, ed);
								System.out.println(e1.getType()+ " executed at time: " +Database.getTime() + " in " + Database.getEDbyName(arguments[0]).getEDname());
								ed.getNewEnabledEvents().remove(e1.getType());
								ed.addToNewEnabledEvents("PatientArrival");
								ed.setNewEnabledEvents(Database.updateEnabledEvents(ed.getNewEnabledEvents(), ed));
								ed.setEventQueue(Database.updateEventQueue(ed));
								i--;
							}
							}
							catch (IndexOutOfBoundsException e) {
								System.out.println("\n \n Number is too high, final state displayed");
								Database.getEDbyName(arguments[0]).displayFinalState();
							}
						}
						
					}
						else if (command.equalsIgnoreCase("execute")) {
							try {
								ED ed=Database.getEDbyName(arguments[0]);
								int i=100000;
								ed.sortEventQueue();
								while (i>0) {
									Event e1=ed.getEventQueue().get(0);
									Database.setTime(e1.getOccurenceTime());
									ed=Database.execute(e1, ed);
									System.out.println(e1.getType()+ " executed at time: " +Database.getTime() + " in " + Database.getEDbyName(arguments[0]).getEDname());
									ed.getNewEnabledEvents().remove(e1.getType());
									ed.addToNewEnabledEvents("PatientArrival");
									ed.setNewEnabledEvents(Database.updateEnabledEvents(ed.getNewEnabledEvents(), ed));
									ed.setEventQueue(Database.updateEventQueue(ed));
									i--;
								}
								}
								catch (IndexOutOfBoundsException e) {
									System.out.println("Final State");
									Database.getEDbyName(arguments[0]).displayFinalState();
								}
							
						}
						else if (command.equalsIgnoreCase("bill")) {
							ED ed=Database.getEDbyName(arguments[0]);
							Patient patient =ed.getPatientbyId(Integer.valueOf(arguments[1]));
							System.out.println("Bill for " + patient.getName() +": "+patient.charges());
						}
					
						else if(command.equalsIgnoreCase("generatedPatients")) {
							ED ed=Database.getEDbyName(arguments[0]);
							System.out.println(Arrays.toString(ed.toBeGeneratedPatients.get(0)));
							System.out.println(Arrays.toString(ed.toBeGeneratedPatients.get(1)));
							System.out.println(Arrays.toString(ed.toBeGeneratedPatients.get(2)));
							System.out.println(Arrays.toString(ed.toBeGeneratedPatients.get(3)));
							System.out.println(Arrays.toString(ed.toBeGeneratedPatients.get(4)));
						}
					
						else if (command.equalsIgnoreCase("display")) {
						ED ed=Database.getEDbyName(arguments[0]);
						ed.display();
						}
					
						else if (command.equalsIgnoreCase("simergy")) {
							if(!loadedSimergy) {
							ReadFile.Read(); 
							loadedSimergy=true;
							}
							else {
								System.out.println("Error: my_simergy.ini is already loaded");
							}
						}
					
						else if (command.equalsIgnoreCase("testScenario")) {
							if(!loadedTestScenario) {
							testOnGoing=true;
							try (BufferedReader br = new BufferedReader(new FileReader(FILENAME2))) {
								String sCurrentLine;
								System.out.println("----------------Test Scenario---------------");
								while ((sCurrentLine = br.readLine()) != null) {
									if (!testScenario.contains(sCurrentLine)) {
									testScenario.add(sCurrentLine);
									}
								}
										str=testScenario.get(0);
								}
							catch (IOException e) {
								e.printStackTrace();
								} 
								}
							else {
								System.out.println("Error: You have already finished testScenario.txt");
							}
							}
						
					else {
						if(!command.equalsIgnoreCase("exit"))
						System.out.println("\nThe command \"" + command + "\" does not exist, type help if needed");
					}
					
					}
					catch (ArrayIndexOutOfBoundsException exception) {
						System.out.println("\nWrong arguments, maybe you need more than " + arguments.length +  " arguments. Type help if needed.");
					}
					catch (NullPointerException exception) {
						System.out.println("\nWrong arguments, maybe ED \"" + arguments[0] + "\" does not exist. Type help if needed. ");
					}
					catch (NumberFormatException exception) {
						System.out.println("\nWrong arguments, one or more of the arguments should be a number. Type help if needed.");
					}
					
					}
				
			
		
		sc.close();
		System.out.println("Exited");
		
	}
}
