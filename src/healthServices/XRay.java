package healthServices;

import java.util.ArrayList;

import human.Patient;
import maths.ProbabilityDistribution;
import maths.Uniform;
import others.Database;
import others.Observable;
import others.Observer;

/**
 * XRay is part of HealthServices
 * its duration is random, by default it is defined by Uniform(10,20)
 */
public class XRay extends HealthServices implements Observable{
	private ArrayList<Observer> ObserverList = new ArrayList<Observer>();
	private int cost;
	
	/**
	 * This constructs an XRay
	 * <br> its default probabilityDistribution is an uniform law on [10;20].
	 * @param EDname The ED in which the XRay will be created.
	 */
	public XRay(String EDname) {
		this.ED = Database.getEDbyName(EDname);
		this.probabilityDistribution = new Uniform(10,20);
		this.duration = this.probabilityDistribution.getSample();
	}
	
	/**
	 * This constructs an XRay
	 * @param EDname The ED in which the XRay will be created.
	 * @param probabilityDistribution The probabilityDistribution of the XRay
	 * @param cost The cost for the Patient
	 */
	public XRay(String EDname,ProbabilityDistribution probabilityDistribution, int cost) {
		this.ED=Database.getEDbyName(EDname);
		this.probabilityDistribution= probabilityDistribution;
		this.cost=cost;
		this.duration = this.probabilityDistribution.getSample();
	}
	public XRay(String EDname,ProbabilityDistribution probabilityDistribution) {
		this.ED=Database.getEDbyName(EDname);
		this.probabilityDistribution= probabilityDistribution;
		this.duration = this.probabilityDistribution.getSample();
	}
	/**
	 * Sets the duration of this XRay
	 * @param duration The duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	/**
	 * 
	 * @return The cost of this HS
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * This is used to take a patient from the WaitinQueue to the XRay.
	 * <br> The room state is set to full and the patient state is set to checked.
	 * @param patient The patient which is going to undergo an XRay
	 */
	public void check (Patient patient) {
		this.ED.radioRoom.setState("full");
		this.ED.radioRoom.getWaitingQueue().remove(patient);
		patient.setState("checked");
		
	}
	
	/**
	 * At the end of the test, the patient is set to "waitingForVerdict", and the room to "empty"
	 * The patient is relocated to the waiting room and a message is sent to the physician.
	 * Finally, the patient is billed.
	 * @param patient The patient finishing this XRay.
	 */
	public void endCheck (Patient patient) {
		patient.setState("waitingForVerdict");
		ED.radioRoom.setState("empty");
		patient.setLocation(this.ED.waitingRoom);
		this.outcome = "Xray done for the patient "  + patient.getName() +  "in "+ String.valueOf(this.duration) + " minutes";
		patient.getPhysician().getMessageBox().add(this.outcome);
		patient.addToBill(cost);
	
	}
	
	/**
	 * Used to add an observer if it is not on the register
	 * @param observer The observer to add
	 */
	@Override
	public void registerObserver(Observer observer) {
		if (!this.ObserverList.contains(observer))
			this.ObserverList.add(observer);
	}

	/**
	 * Used to remove an observer if it is on the register
	 * @param observer The observer to remove.
	 */
	@Override
	public void removeObserver(Observer observer) {
		if (this.ObserverList.contains(observer))
			this.ObserverList.remove(observer);
	}

	/**
	 * Used to notify all the observer on this XRay register of the outcome of the scan.
	 */
	@Override
	public void notifyObservers() {
		for (Observer observer : this.ObserverList) {
			observer.update(this.outcome);
		}
	}
}

