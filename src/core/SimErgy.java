package core;


import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import events.Event;
import human.*;
import rooms.*;
import others.Database;
import others.ED;
import others.ReadFile;

public class SimErgy {
	static String str="";
	
	@SuppressWarnings("unused")
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Scanner sc1 = new Scanner(System.in);
		System.out.println("\n\nType anything to Load the .ini file");
		if(sc1.hasNext()) {
			ReadFile.Read();
			Scanner sc2 = new Scanner(System.in);
				System.out.println("\n\nChoose between the two EDs by typing the associated number");
				for (int i=1; i<ReadFile.getEDnames().size()+1;i++) {
					System.out.println(i+"-"+ReadFile.getEDnames().get(i-1));
				}
				str = sc2.nextLine();
				str=str.replace(" ", "");
				ED ed=Database.getGeneratedEDs().get(Integer.valueOf(str)-1);
		
		ed.updateState();
		ed.setNewEnabledEvents(Database.updateEnabledEvents(ed.getOldEnabledEvents(), ed));
		ed.setEventQueue(Database.updateEventQueue(ed));
		while (Database.getTime()<10000) {
			Event e1=ed.getEventQueue().get(0);
			Database.setTime(e1.getOccurenceTime());
			ed=Database.execute(e1, ed);
			System.out.println(e1.getType()+ " executed at time: " +Database.getTime());
			ed.getNewEnabledEvents().remove(e1.getType());
			ed.addToNewEnabledEvents("PatientArrival");
			ed.setNewEnabledEvents(Database.updateEnabledEvents(ed.getNewEnabledEvents(), ed));
			ed.setEventQueue(Database.updateEventQueue(ed));
			if(ed.getEventQueue().size()==0) {
				ed.displayFinalState();
				System.out.println("End of simulation, all the patients should be released.");
				System.out.println("----------------");
				break;
			}
			System.out.println("----------------");
			
		 }
		if(Database.getTime()>=10000) {
			ed.display();
			System.out.println("Time out ! Some patients may not have been released yet.");
		}
		
		
		}
	
	}

}
