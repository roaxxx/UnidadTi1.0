package model;

public class Required_Interface {
	private int idR_interface;
	private String name_rq;
	private String rq_funtion;
	
	public Required_Interface() {
	
	}

	public Required_Interface(int idR_interface, String name_rq, String rq_funtion) {
		this.idR_interface = idR_interface;
		this.name_rq = name_rq;
		this.rq_funtion = rq_funtion;
	}

	public int getIdR_interface() {
		return idR_interface;
	}
	public void setIdR_interface(int idR_interface) {
		this.idR_interface = idR_interface;
	}
	public String getName_rq() {
		return name_rq;
	}
	public void setName_rq(String name_rq) {
		this.name_rq = name_rq;
	}
	public String getRq_funtion() {
		return rq_funtion;
	}
	public void setRq_funtion(String rq_funtion) {
		this.rq_funtion = rq_funtion;
	}
	
}
