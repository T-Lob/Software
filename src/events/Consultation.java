package events;

import others.Database;
import others.IDGenerator;

public class Consultation extends Event {
	public Consultation(String edName) {
		this.ed=Database.getEDbyName(edName);
		this.id=IDGenerator.getInstance().getNextID();
		this.name=("Consultation" + String.valueOf(id));
		this.occurenceTime=(this.ed.getTime());
		this.type= "Consulatation";
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
