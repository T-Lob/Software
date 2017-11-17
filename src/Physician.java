
import java.util.ArrayList;

import others.IDGenerator;


public class Physician extends HumanResource {
	
	
	public ArrayList<Patient> currentPatients = new ArrayList<Patient>();
	public ArrayList<Patient> historyPatients = new ArrayList<Patient>();
	public ArrayList<String> messageBox = new ArrayList<String>();
	
	
	public Physician() {
		this.ID = IDGenerator.getInstance().getNextID();
		
	}

	public Physician(String name, String surname, String username) {
		super();
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		this.username = username;
	}
	
	
	public ArrayList<Patient> getCurrentPatients() {
		return currentPatients;
	}
	
	public void addToCurrentPatients(Patient patient) {
		this.currentPatients.add(patient);
	}
	public void removeFromCurrentPatients(Patient patient) {
		this.currentPatients.remove(patient);
	}

	public ArrayList<Patient> getHistoryPatients() {
		return historyPatients;
	}
	public void addToHistoryPatients(Patient patient) {
		this.historyPatients.add(patient);
	}

	public ArrayList<String> getMessageBox() {
		return messageBox;
	}
	public String getLastMessage() {
		return messageBox.get(messageBox.size()-1);
	}
	public void  addToMessageBox(String message) {
		this.messageBox.add(message);
	}
	
	/*
	public void takeDecision(Patient patient) {
		Uniform U = new Uniform(0,100);
		double x = U.sample();
		if(0<=(double)x && (double)x<35) {
			Message newMessage = new Message("you have no test to take",this,patient,timestamp);
			patient.messageBox.addNewMessage(newMessage);
			Event.Transportation(Patient patient, Resource)
		}
		if(35<=(double)x && (double)x<55) {
			Message newMessage = new Message("you have to take a radiography exam",this,patient,timestamp);
			patient.messageBox.addNewMessage(newMessage);
		}
		if(55<=(double)x && (double)x<95) {
			Message newMessage = new Message("you have to take a blood-test exam",this,patient,timestamp);
			patient.messageBox.addNewMessage(newMessage);
		}
		if(95<=(double)x && (double)x<=100) {
			Message newMessage = new Message("you have to take a blood-test exam",this,patient,timestamp);
			patient.messageBox.addNewMessage(newMessage);
		}
		return;
		*/	
}