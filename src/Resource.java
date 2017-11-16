public abstract class Resource {
	
	private String name;
	private int ID;
	private String state;
	
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
	
	public boolean isAvailable(Resource resource){
		return false;
	}
}
