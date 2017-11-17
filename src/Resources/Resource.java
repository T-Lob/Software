package Resources;
import others.*;
public abstract class Resource {
	
	protected String name;
	protected int ID;
	protected String state;
	protected ED ED;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getID() {
		return ID;
	}
	
	public boolean isAvailable(){
		return this.state == "empty" || this.state =="idle";
	}
}
