package healthServices;

import Human.Patient;
import maths.ProbabilityDistribution;
import maths.Uniform;
import others.Database;

public class MRIScan extends HealthServices {
	private ProbabilityDistribution probabilityDistribution= new Uniform(10,20);
	private int duration=this.probabilityDistribution.getSample();
	
	public MRIScan() {
		
	}
	public MRIScan(ProbabilityDistribution probabilityDistribution) {
		this.probabilityDistribution= probabilityDistribution;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public void check (Patient patient) {
		if (this.timeOfAvailability <= Database.getTime()) {
			this.setTimeOfAvailability(Database.getTime());
			patient.setLocation(Database.mriRoom);
			Database.mriRoom.setState("full");
			this.WaitingQueue.remove(patient);
			patient.setState("checked");
			this.timeOfAvailability += this.duration;
		}
	}
	
		public void endCheck (Patient patient) {
		if (this.timeOfAvailability == Database.getTime()) {
			patient.setState("waitingForVerdict");
			Database.mriRoom.setState("empty");
			this.outcome = "MRI done for the patient "  + patient.getName() +  "in "+ String.valueOf(this.duration) + " minutes";
			patient.getPhysician().addToMessageBox(this.outcome);
			patient.addToBill(cost);
			
		}
		}
		}
