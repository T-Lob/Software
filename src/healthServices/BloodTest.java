package healthServices;

import java.util.ArrayList;

import human.Patient;
import maths.ProbabilityDistribution;
import maths.Uniform;
import others.Database;
import others.Observable;
import others.Observer;
import rooms.WaitingRoom;

public class BloodTest extends HealthServices implements Observable {
	private ArrayList<Observer> ObserverList = new ArrayList<Observer>();
	
	
	public BloodTest(String EDname) {
		this.ED = Database.getEDbyName(EDname);
		this.probabilityDistribution = new Uniform(15,90);
		this.duration = this.probabilityDistribution.getSample();
	}
	
	public BloodTest(String EDname,ProbabilityDistribution probabilityDistribution, int cost) {
		this.ED=Database.getEDbyName(EDname);
		this.probabilityDistribution= probabilityDistribution;
		this.cost = cost;
		this.duration = this.probabilityDistribution.getSample();
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void check (Patient patient) {
		this.ED.bloodTestRoom.setState("full");
		this.ED.bloodTestRoom.getWaitingQueue().remove(patient);
		patient.setState("checked");
		
	}
	
	public void endCheck (Patient patient) {
		patient.setState("waitingForVerdict");
		ED.bloodTestRoom.setState("empty");
		patient.setLocation(this.ED.waitingRoom);
		this.outcome = "Bloodtest done for the patient "  + patient.getName() +  "in "+ String.valueOf(this.duration) + " minutes";
		patient.getPhysician().getMessageBox().add(this.outcome);
		patient.addToBill(cost);
	
	}
	
	@Override
	public void registerObserver(Observer observer) {
		if (!this.ObserverList.contains(observer))
			this.ObserverList.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		if (this.ObserverList.contains(observer))
			this.ObserverList.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer observer : this.ObserverList) {
			observer.update(this.outcome);
		}
	}
}
