package Human;
import others.Database;
import others.IDGenerator;

public class Transporter extends HumanResource{
private Patient currentPatient;
	
	public Transporter() {
		this.ID = IDGenerator.getInstance().getNextID();
		Database.addToTransporterList(this);
	}
	public Transporter(String name, String surname) {
		this.name = name;
		this.surname = surname;
		this.ID = IDGenerator.getInstance().getNextID();
		Database.addToTransporterList(this);
	}
	public Transporter(String name) {
		this.name = name;
		this.ID = IDGenerator.getInstance().getNextID();
		Database.addToTransporterList(this);
	}
	public Patient getCurrentPatient() {
		return currentPatient;
	}

	public void setCurrentPatient(Patient currentPatient) {
		this.currentPatient = currentPatient;
	}
	
	public void setState(String state) {
		this.state=state;
		if (state.equalsIgnoreCase("transporting")){
			Database.getTransporterList().get(1).add(this);
			Database.getTransporterList().get(0).remove(this);
			Database.getTransporterList().get(2).remove(this);
		} else if (state.equalsIgnoreCase("idle")) {
			Database.getTransporterList().get(0).add(this);
			Database.getTransporterList().get(1).remove(this);
			Database.getTransporterList().get(2).remove(this);
		}
		else if (state.equalsIgnoreCase("offduty")){
			Database.getTransporterList().get(2).add(this);
			Database.getTransporterList().get(1).remove(this);
			Database.getTransporterList().get(0).remove(this);
			}
		
}


}
