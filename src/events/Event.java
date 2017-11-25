package events;

import java.util.ArrayList;

import others.ED;

public abstract class Event {
	protected String name;
	protected ED ed;
	protected int occurenceTime;
	protected boolean isEnabled;
	protected int id;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getOccurenceTime() {
		return occurenceTime;
	}
	
	public void setOccurenceTime(int occurenceTime) {
		this.occurenceTime = occurenceTime;
	}
	
	public boolean isEnabled() {
		return isEnabled;
	}
	
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
	public ED getED() {
		return ed;
	}
	
	public int getID() {
		return id;
	}
	
	public abstract void execute();

	public static ArrayList <Event> updateEnabledEvents (ArrayList <Event> enabledEvents, ED ed) {
		return enabledEvents;
		
	}
	}
			
