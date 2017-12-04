package healthServices;

import maths.ProbabilityDistribution;
import others.Resource;

public abstract class HealthServices extends Resource {
	protected ProbabilityDistribution probabilityDistribution;
	protected int cost;
	protected String outcome;
	protected double duration;
	protected int timeOfAvailability;
	
	
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
	public double getDuration() {
		return duration;
	}
	public void setTimeOfAvailability(int t) {
		this.timeOfAvailability = t;
	}
	public int getTimeOfAvailability() {
		return timeOfAvailability;
	}
	

}
