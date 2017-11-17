package Human;

import Resources.Resource;
import others.Database;

public abstract class HumanResource extends Resource {
	protected String surname;
	protected String state="idle";
	public int timeOfAvailability;
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getTimeOfAvailability() {
		return timeOfAvailability;
	}
	public void setTimeOfAvailability(int timestamp) {
		this.timeOfAvailability = timestamp;
	}
	
	public void setState(String state) {
		this.state=state;
		if (state.equalsIgnoreCase("visiting")){
			Database.getPhysicianList().get(1).add((Physician) this);
			Database.getPhysicianList().get(0).remove((Physician) this);
			Database.getPhysicianList().get(2).remove((Physician) this);
	
			
		} else if (state.equalsIgnoreCase("busy")){
			Database.getNurseList().get(1).add((Nurse) this);
			Database.getNurseList().get(0).remove((Nurse) this);
			Database.getNurseList().get(2).remove((Nurse) this);
			
		} else if (state.equalsIgnoreCase("transporting")){
			Database.getTransporterList().get(1).add((Transporter) this);
			Database.getTransporterList().get(0).remove((Transporter) this);
			Database.getTransporterList().get(2).remove((Transporter) this);
		
		} else if (state.equalsIgnoreCase("idle")) {
				if (this.getClass()==Physician.class) {
					Database.getPhysicianList().get(0).add((Physician) this);
					Database.getPhysicianList().get(1).remove((Physician) this);
					Database.getPhysicianList().get(2).remove((Physician) this);

			} else if (this.getClass()==Nurse.class) {
				Database.getNurseList().get(0).add((Nurse) this);
				Database.getNurseList().get(1).remove((Nurse) this); 
				Database.getNurseList().get(2).remove((Nurse) this); 
				
			}
			else if (this.getClass()==Transporter.class) {
				Database.getTransporterList().get(0).add((Transporter) this);
				Database.getTransporterList().get(1).remove((Transporter) this);
				Database.getTransporterList().get(2).remove((Transporter) this);
				
			}
			
		} else if (state.equalsIgnoreCase("offduty")){
			if (this.getClass()==Physician.class) {
				Database.getPhysicianList().get(2).add((Physician) this);
				Database.getPhysicianList().get(1).remove((Physician) this);
				Database.getPhysicianList().get(0).remove((Physician) this);

		} else if (this.getClass()==Nurse.class) {
			Database.getNurseList().get(2).add((Nurse) this);
			Database.getNurseList().get(1).remove((Nurse) this); 
			Database.getNurseList().get(0).remove((Nurse) this); 
			
		}
		else if (this.getClass()==Transporter.class) {
			Database.getTransporterList().get(2).add((Transporter) this);
			Database.getTransporterList().get(1).remove((Transporter) this);
			Database.getTransporterList().get(0).remove((Transporter) this);
			
		}
	}
	}
}
