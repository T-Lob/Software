package others;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

import healthServices.HSFactory;
import healthServices.HealthServices;
import human.HRFactory;
import maths.ProbabilityDistributionFactory;
import rooms.RoomFactory;

public class CLI {
	static String str="";
	static String [] arguments;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(!(str.equalsIgnoreCase("EXIT"))) {
			System.out.println("Write your command, write help for help");
			str = sc.nextLine();
			str=str.replace("<", "").replace(">", "");
			System.out.println(str);
			String [] array =str.split(" ");
			String command = array[0];
			if (str=="help") {
				System.out.println("create ED ");
			}
			if (array.length >1) {
				String argument=array[1];
				arguments= argument.split(",");
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
				else if (command.equalsIgnoreCase("setL1arrivalDist")) {
					Database.getEDbyName(arguments[0]).setL1(new PatientGeneration(arguments[0],ProbabilityDistributionFactory.createPD(arguments[1], Integer.valueOf(arguments[2]),Integer.valueOf(arguments[3])), "l1"));
					}
				}
			}
		}
	
	sc.close();
	System.out.println("on sort de la boucle");
	}
}
