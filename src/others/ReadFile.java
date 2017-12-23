package others;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import human.HRFactory;
import human.Nurse;
import rooms.RoomFactory;


public class ReadFile {
	
	private static final String FILENAME = "./scenario.txt";

	public static void main(String[] args) {

		try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

			String sCurrentLine;
			String currentED = null;

			while ((sCurrentLine = br.readLine()) != null) {
				ArrayList<String> arguments = new ArrayList<String>(Arrays.asList(sCurrentLine.replace(" ","").replace(",",":").split(":")));
				if (arguments.get(0).equalsIgnoreCase("ED")) {
					currentED=arguments.get(1);
					Database.addToGeneratedResources(new ED(arguments.get(0)));
					Database.getGeneratedEDs().get(0).getNurseList().get(0).add(new Nurse("Saint-Denis"));
					System.out.println("Created ED "+ arguments.get(1));	
				}
				
				else if (arguments.get(0).equalsIgnoreCase("Nurse")) {
					arguments.add(1,currentED);
					System.out.println(arguments);
					for (int i=0; i<Integer.parseInt(arguments.get(2));i++) {
						Database.addToGeneratedResources(HRFactory.createHR(arguments.get(1),"NURSE"));
					}
				}
			
				else if (arguments.get(0).equalsIgnoreCase("BoxRoom")) {
					arguments.add(1,currentED);
					System.out.println(arguments);
					for (int i=0; i<Integer.parseInt(arguments.get(2));i++) {
						Database.addToGeneratedResources(RoomFactory.createRoom("test", "BoxRoom"));
					}
				} 
				else if (arguments.get(0).equalsIgnoreCase("ShockRoom")) {
					arguments.add(1,currentED);
					System.out.println(arguments);
					for (int i=0; i<Integer.parseInt(arguments.get(2));i++) {
					Database.addToGeneratedResources(RoomFactory.createRoom(arguments.get(1), "ShockRoom"));
					}
				} 
//				else if (arguments.get(0).equalsIgnoreCase("BoxRoom")) {
//					arguments.add(1,currentED);
//					System.out.println(arguments);
//					for (int i=0; i<Integer.parseInt(arguments.get(2));i++) {
//					Database.addToGeneratedResources(RoomFactory.createRoom(arguments.get(1), "BoxRoom"));
//					}
//				} 
				else if (arguments.get(0).substring(0,1).equals("-")) {
					System.out.println("-----------------");
				}
	
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	
}
