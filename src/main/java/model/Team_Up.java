package model;


public class Team_Up {
	private Developer developers;
	private String rol;

	public Team_Up() {
		
	}

	public Team_Up(Developer developers,String rol) {
		this.developers = developers;
		this.rol = rol;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public Developer getDevelopers() {
		return developers;
	}
	public void setDevelopers(Developer developers) {
		this.developers = developers;
	}
	
}
