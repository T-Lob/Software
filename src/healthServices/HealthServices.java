package healthServices;

import maths.ProbabilityDistribution;
import others.Resource;

/**
 * This abstract class is extended by all the health services : The consultation, The BloodTest, The MRIScan and the XRay?
 */
public abstract class HealthServices extends Resource {
	protected ProbabilityDistribution probabilityDistribution;
	protected int cost;
	protected String outcome;
	protected double duration;
	protected int timeOfAvailability;
	
	/**
	 * 
	 * @return The probability distribution of this Healt service
	 */
	public ProbabilityDistribution getProbabilityDistribution() {
		return probabilityDistribution;
	}
	
	/**
	 * Sets the probability distribution of this HS
	 * @param probabilityDistribution The PorbabilityDistribution to set.
	 */
	public void setProbabilityDistribution(ProbabilityDistribution probabilityDistribution) {
		this.probabilityDistribution = probabilityDistribution;
	}
	
	/**
	 * 
	 * @return The outcom of this test.
	 */
	public String getOutcome() {
		return outcome;
	}
	
	/**
	 * Sets the outcome of this test
	 * @param outcome the outcom of this test.
	 */
	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}
	
	/**
	 * 
	 * @return The cost of this HS
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * 
	 * @return The duration of this HS
	 */
	public double getDuration() {
		return duration;
	}
	
	/**
	 * Sets the time during which the test is available.
	 * @param t time available
	 */
	public void setTimeOfAvailability(int t) {
		this.timeOfAvailability = t;
	}
	
	/**
	 * 
	 * @return the time during which the test is available.
	 */
	public int getTimeOfAvailability() {
		return timeOfAvailability;
	}
	

}
