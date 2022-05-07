package model;

public class Supplied_Interface {
	private int idS_interface;
	private String name_si;
	private String si_funtion;
	public Supplied_Interface() {
		// TODO Auto-generated constructor stub
	}
	public Supplied_Interface(int idS_interface, String name_si,String si_funtion) {
		this.idS_interface = idS_interface;
		this.name_si = name_si;
		this.si_funtion = si_funtion;

	}
	
	public int getIdS_interface() {
		return idS_interface;
	}

	public void setIdS_interface(int idS_interface) {
		this.idS_interface = idS_interface;
	}

	public String getName_si() {
		return name_si;
	}

	public void setName_si(String name_si) {
		this.name_si = name_si;
	}

	public String getSi_funtion() {
		return si_funtion;
	}

	public void setSi_funtion(String si_funtion) {
		this.si_funtion = si_funtion;
	}
	
	
}
