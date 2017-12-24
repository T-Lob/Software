package healthServices;

import java.util.ArrayList;

import human.Patient;
import maths.ProbabilityDistribution;
import maths.Uniform;
import others.Database;
import others.Observable;
import others.Observer;

/**
 * Bloodtest is part of HealthServices
 * its duration is random, by default it is defined by Uniform(15,90)
 */
public class BloodTest extends HealthServices implements Observable {
	private ArrayList<Observer> ObserverList = new ArrayList<Observer>();
	private int cost=20;
	
	/**
	 * 
	 * @return The cost of this HS
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * This constructs a BloodTest
	 * <br> its default probabilityDistribution is an uniform law on [15;90].
	 * @param EDname The ED in which the BloodTest will be created.
	 */
	public BloodTest(String EDname) {
		this.ED = Database.getEDbyName(EDname);
		this.probabilityDistribution = new Uniform(15,90);
		this.duration = this.probabilityDistribution.getSample();
	}
	
	/**
	 * This constructs a BloodTest
	 * @param EDname The ED in which the BloodTest will be created.
	 * @param probabilityDistribution The probabilityDistribution of the BloodTest
	 * @param cost The cost for the Patient
	 */
	public BloodTest(String EDname,ProbabilityDistribution probabilityDistribution, int cost) {
		this.ED=Database.getEDbyName(EDname);
		this.probabilityDistribution= probabilityDistribution;
		this.cost = cost;
		this.duration = this.probabilityDistribution.getSample();
	}
	
	public BloodTest(String EDname,ProbabilityDistribution probabilityDistribution) {
		this.ED=Database.getEDbyName(EDname);
		this.probabilityDistribution= probabilityDistribution;
		this.duration = this.probabilityDistribution.getSample();
	}
	
	/**
	 * Sets the duration of this BloodTest.
	 * @param duration The duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	/**
	 * This is used to take a patient from the WaitinQueue to the BloodTestRoom.
	 * <br> The room state is set to full and the patient state is set to checked.
	 * @param patient The patient which is going to undergo a bloodtest
	 */
	public void check (Patient patient) {
		this.ED.bloodTestRoom.setState("full");
		this.ED.bloodTestRoom.getWaitingQueue().remove(patient);
		patient.setState("checked");
		
	}
	
	/**
	 * At the end of the test, the patient is set to "waitingForVerdict", and the room to "empty"
	 * The patient is relocated to the waiting room and a message is sent to the physician.
	 * Finally, the patient is billed.
	 * @param patient The patient finishing this bloodtest
	 */
	public void endCheck (Patient patient) {
		patient.setState("waitingForVerdict");
		ED.bloodTestRoom.setState("empty");
		patient.setLocation(this.ED.waitingRoom);
		this.outcome = "Bloodtest done for the patient "  + patient.getName() +  "in "+ String.valueOf(this.duration) + " minutes";
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
	 * Used to notify all the observer on this bloodtest register of the outcome of the test
	 */
	@Override
	public void notifyObservers() {
		for (Observer observer : this.ObserverList) {
			observer.update(this.outcome);
		}
	}
}
