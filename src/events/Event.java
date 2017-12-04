package events;

import others.ED;

public abstract class Event {
	protected String name;
	protected ED ed;
	protected double occurenceTime;
	protected int id;
	protected String type;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getOccurenceTime() {
		return occurenceTime;
	}
	
	public void setOccurenceTime(int occurenceTime) {
		this.occurenceTime = occurenceTime;
	}
	
	public ED getED() {
		return ed;
	}
	
	public int getID() {
		return id;
	}
	
	
	public String getType() {
		return type;
	}

	public abstract void execute();
		

	
	
}
	
			
