package others;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import human.HRFactory;
import human.Nurse;
import human.Patient;
import rooms.RoomFactory;


public class ReadFile {
	private static ArrayList<String> EDnames = new ArrayList<String>();
	
	private static final String FILENAME = "./my_symergy.ini";

	public static void Read() {

		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			String sCurrentLine;
			String currentED = null;

			while ((sCurrentLine = br.readLine()) != null) {
				ArrayList<String> arguments = new ArrayList<String>(Arrays.asList(sCurrentLine.replace(" ","").replace(",",":").split(":")));
				if (arguments.get(0).equalsIgnoreCase("ED")) {
					currentED=arguments.get(1);
					EDnames.add(currentED);
					Database.addToGeneratedResources(new ED(arguments.get(1)));
					System.out.println("Created ED "+ arguments.get(1));	
				}
				
				else if (arguments.get(0).equalsIgnoreCase("Nurse")) {
					arguments.add(1,currentED);
					for (int i=0; i<Integer.parseInt(arguments.get(2));i++) {
						Database.addToGeneratedResources(HRFactory.createHR(arguments.get(1),"NURSE"));
					}
					System.out.println("Created " + arguments.get(2) + " Nurse(s)" + " in " + arguments.get(1));
				}
				else if (arguments.get(0).equalsIgnoreCase("Physician")) {
					arguments.add(1,currentED);
					for (int i=0; i<Integer.parseInt(arguments.get(2));i++) {
						Database.addToGeneratedResources(HRFactory.createHR(arguments.get(1),"Physician"));
					}
					System.out.println("Created " + arguments.get(2) + " Physician(s)" + " in " + arguments.get(1));
				}
				else if (arguments.get(0).equalsIgnoreCase("Transporter")) {
					arguments.add(1,currentED);
					for (int i=0; i<Integer.parseInt(arguments.get(2));i++) {
						Database.addToGeneratedResources(HRFactory.createHR(arguments.get(1),"Transporter"));
					}
					System.out.println("Created " + arguments.get(2) + " Transporter(s)" + " in " + arguments.get(1));
				}
				
				else if (arguments.get(0).equalsIgnoreCase("L1Patient")) {
					arguments.add(1,currentED);
					for (int i=0; i<Integer.parseInt(arguments.get(2));i++) {
						Database.addToGeneratedResources(new Patient (arguments.get(1),"L1"));
					}
					System.out.println("Created " + arguments.get(2) + " L1 Patient(s)" + " in " + arguments.get(1));
				}
				
				else if (arguments.get(0).equalsIgnoreCase("L2Patient")) {
					arguments.add(1,currentED);
					for (int i=0; i<Integer.parseInt(arguments.get(2));i++) {
						Database.addToGeneratedResources(new Patient (arguments.get(1),"L2"));
					}
					System.out.println("Created " + arguments.get(2) + " L2 Patient(s)" + " in " + arguments.get(1));
				}
				else if (arguments.get(0).equalsIgnoreCase("L3Patient")) {
					arguments.add(1,currentED);
					for (int i=0; i<Integer.parseInt(arguments.get(2));i++) {
						Database.addToGeneratedResources(new Patient (arguments.get(1),"L3"));
					}
					System.out.println("Created " + arguments.get(2) + " L3 Patient(s)" + " in " + arguments.get(1));
				}
				else if (arguments.get(0).equalsIgnoreCase("L4Patient")) {
					arguments.add(1,currentED);
					for (int i=0; i<Integer.parseInt(arguments.get(2));i++) {
						Database.addToGeneratedResources(new Patient (arguments.get(1),"L4"));
					}
					System.out.println("Created " + arguments.get(2) + " L4 Patient(s)" + " in " + arguments.get(1));
				}
				else if (arguments.get(0).equalsIgnoreCase("L5Patient")) {
					arguments.add(1,currentED);
					for (int i=0; i<Integer.parseInt(arguments.get(2));i++) {
						Database.addToGeneratedResources(new Patient (arguments.get(1),"L5"));
					}
					System.out.println("Created " + arguments.get(2) + " L5 Patient(s)" + " in " + arguments.get(1));
				}
			
				else if (arguments.get(0).equalsIgnoreCase("BoxRoom")) {
					arguments.add(1,currentED);
					for (int i=0; i<Integer.parseInt(arguments.get(2));i++) {
						Database.addToGeneratedResources(RoomFactory.createRoom(arguments.get(1), "BoxRoom"));
						
					}
					System.out.println("Created " + arguments.get(2) + " BoxRoom(s)" + " in " + arguments.get(1));
				} 
				else if (arguments.get(0).equalsIgnoreCase("ShockRoom")) {
					arguments.add(1,currentED);
					for (int i=0; i<Integer.parseInt(arguments.get(2));i++) {
					Database.addToGeneratedResources(RoomFactory.createRoom(arguments.get(1), "ShockRoom"));
					}
					System.out.println("Created " + arguments.get(2) + " ShockRoom(s)" + " in " + arguments.get(1));
				} 

				else if (arguments.get(0).substring(0,1).equals("-")) {
					System.out.println("-----------------");
				}
	
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<String> getEDnames() {
		return EDnames;
	}

	public static void setEDnames(ArrayList<String> eDnames) {
		EDnames = eDnames;
	}

	
	
}
