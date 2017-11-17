package healthServices;
import java.util.ArrayList;

import Human.Patient;
import Resources.Resource;
import maths.ProbabilityDistribution;

public abstract class HealthServices extends Resource {
	protected ArrayList<Patient> WaitingQueue = new ArrayList<Patient>();
	protected ProbabilityDistribution probabilityDistribution;
	protected int cost;
	protected String outcome;
	protected int duration;
	protected int timeOfAvailability;
	
	public ArrayList<Patient> getWaitingQueue() {
		return WaitingQueue;
	}
	public void addToWaitingQueue(Patient patient) {
		this.WaitingQueue.add(patient);
	}
	public ProbabilityDistribution getProbabilityDistribution() {
		return probabilityDistribution;
	}
	public void setProbabilityDistribution(ProbabilityDistribution probabilityDistribution) {
		this.probabilityDistribution = probabilityDistribution;
	}
	public String getOutcome() {
		return outcome;
	}
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	public int getCost() {
		return cost;
	}
	public int getDuration() {
		return duration;
	}
	public void setTimeOfAvailability(int t) {
		this.timeOfAvailability = t;
	}
	public int getTimeOfAvailability() {
		return timeOfAvailability;
	}
	

}
