package healthServices;

import java.util.ArrayList;

import human.Patient;
import maths.ProbabilityDistribution;
import maths.Uniform;
import others.Database;
import others.Observable;
import others.Observer;

public class MRIScan extends HealthServices implements Observable{
	private ArrayList<Observer> ObserverList = new ArrayList<Observer>();
	
	public MRIScan(String EDname) {
		this.ED = Database.getEDbyName(EDname);
		this.probabilityDistribution = new Uniform(10,20);
		this.duration = this.probabilityDistribution.getSample(); 
	}
	
	public MRIScan(String EDname,ProbabilityDistribution probabilityDistribution, int cost) {
		this.probabilityDistribution= probabilityDistribution;
		this.ED=Database.getEDbyName(EDname);
		this.cost = cost;
		this.duration = this.probabilityDistribution.getSample();
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void check (Patient patient) {
		this.ED.mriRoom.setState("full");
		this.WaitingQueue.remove(patient);
		patient.setState("checked");
		
	}
	
	public void endCheck (Patient patient) {
		patient.setState("waitingForVerdict");
		this.ED.mriRoom.setState("empty");
		this.outcome = "MRI done for the patient "  + patient.getName() +  "in "+ String.valueOf(this.duration) + " minutes";
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
