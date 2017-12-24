package healthServices;

import java.util.ArrayList;

import human.Patient;
import maths.ProbabilityDistribution;
import maths.Uniform;
import others.Database;
import others.Observable;
import others.Observer;

/**
 * MRIScan is part of HealthServices
 * its duration is random, by default it is defined by Uniform(10,20)
 */
public class MRIScan extends HealthServices implements Observable{
	private ArrayList<Observer> ObserverList = new ArrayList<Observer>();
	private int cost=500;
	
	/**
	 * This constructs an MRIScan
	 * <br> its default probabilityDistribution is an uniform law on [10;20].
	 * @param EDname The ED in which the MRIScan will be created.
	 */
	public MRIScan(String EDname) {
		this.ED = Database.getEDbyName(EDname);
		this.probabilityDistribution = new Uniform(10,20);
		this.duration = this.probabilityDistribution.getSample(); 
	}
	
	
	/**
	 * This constructs an MRIScan
	 * @param EDname The ED in which the MRIScan will be created.
	 * @param probabilityDistribution The probabilityDistribution of the MRIScan
	 * @param cost The cost for the Patient
	 */
	public MRIScan(String EDname,ProbabilityDistribution probabilityDistribution, int cost) {
		this.probabilityDistribution= probabilityDistribution;
		this.ED=Database.getEDbyName(EDname);
		this.cost = cost;
		this.duration = this.probabilityDistribution.getSample();
	}
	public MRIScan(String EDname,ProbabilityDistribution probabilityDistribution) {
		this.probabilityDistribution= probabilityDistribution;
		this.ED=Database.getEDbyName(EDname);
		this.duration = this.probabilityDistribution.getSample();
	}
	
	/**
	 * 
	 * @return The cost of this HS
	 */
	public int getCost() {
		return cost;
	}
	/**
	 * Sets the duration of this MRIScan
	 * @param duration The duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	/**
	 * This is used to take a patient from the WaitinQueue to the MRIRoom.
	 * <br> The room state is set to full and the patient state is set to checked.
	 * @param patient The patient which is going to undergo an MRIScan
	 */
	public void check (Patient patient) {
		this.ED.mriRoom.setState("full");
		this.ED.mriRoom.getWaitingQueue().remove(patient);
		patient.setState("checked");
		
	}
	
	/**
	 * At the end of the test, the patient is set to "waitingForVerdict", and the room to "empty"
	 * The patient is relocated to the waiting room and a message is sent to the physician.
	 * Finally, the patient is billed.
	 * @param patient The patient finishing this MRIScan.
	 */
	public void endCheck (Patient patient) {
		patient.setState("waitingForVerdict");
		ED.mriRoom.setState("empty");
		patient.setLocation(this.ED.waitingRoom);
		this.outcome = "MRI done for the patient "  + patient.getName() +  "in "+ String.valueOf(this.duration) + " minutes";
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
	 * Used to notify all the observer on this MRIScan register of the outcome of the scan.
	 */
	@Override
	public void notifyObservers() {
		for (Observer observer : this.ObserverList) {
			observer.update(this.outcome);
		}
	}
}
