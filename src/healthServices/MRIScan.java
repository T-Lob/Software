package healthServices;

import human.Patient;
import maths.ProbabilityDistribution;
import maths.Uniform;
import others.Database;

public class MRIScan extends HealthServices {
	private ProbabilityDistribution probabilityDistribution= new Uniform(10,20);
	private int duration=this.probabilityDistribution.getSample();
	
	public MRIScan(String EDname) {
		this.ED=Database.getEDbyName(EDname);	
	}
	
	public MRIScan(String EDname,ProbabilityDistribution probabilityDistribution, int cost) {
		this.probabilityDistribution= probabilityDistribution;
		this.ED=Database.getEDbyName(EDname);
		this.cost = cost;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void check (Patient patient) {
		if (this.getTimeOfAvailability() <= this.ED.getTime()) {
			this.setTimeOfAvailability(this.ED.getTime());
			patient.setLocation(this.ED.mriRoom);
			this.ED.mriRoom.setState("full");
			this.WaitingQueue.remove(patient);
			patient.setState("checked");
			this.timeOfAvailability += this.duration;
		}
	}
	
	public void endCheck (Patient patient) {
		if (this.timeOfAvailability == this.ED.getTime()) {
			patient.setState("waitingForVerdict");
			this.ED.mriRoom.setState("empty");
			this.outcome = "MRI done for the patient "  + patient.getName() +  "in "+ String.valueOf(this.duration) + " minutes";
			patient.getPhysician().addToMessageBox(this.outcome);
			patient.addToBill(cost);
			this.duration = this.probabilityDistribution.getSample();
		}
	}
}
