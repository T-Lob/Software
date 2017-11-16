
import java.util.ArrayList;

import others.IDGenerator;


public class Physician extends Resource {
	
	public String name = "";
	public String surname = "";
	public int ID;
	public String state = "idle";
	public String username = "";
	public ArrayList<Patient> currentPatients;
	public ArrayList<Patient> historyPatients;
	public ArrayList<String> messageBox;
	
	
	

	public Physician(String name, String surname, String username) {
		super();
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		this.username = username;
		this.currentPatients = new ArrayList<Patient>();
		this.historyPatients = new ArrayList<Patient>();
		this.messageBox = new ArrayList<String>();
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Override
	public boolean isAvailable(Resource resource) {
		// TODO Auto-generated method stub
		return this.state == "idle";
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
	public void takedecision(Patient patient) {
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