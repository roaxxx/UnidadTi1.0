package model;


public class Funtionality {
	private int id_funtionality;
	private String name_fun;
	private String descrip_fun;
	
	public Funtionality() {
		
	}

	public Funtionality(int id_funtionality, String name_fun, String descrip_fun) {
		this.id_funtionality = id_funtionality;
		this.name_fun = name_fun;
		this.descrip_fun = descrip_fun;
	}

	public int getId_funtionality() {
		return id_funtionality;
	}

	public void setId_funtionality(int id_funtionality) {
		this.id_funtionality = id_funtionality;
	}

	public String getName_fun() {
		return name_fun;
	}

	public void setName_fun(String name_fun) {
		this.name_fun = name_fun;
	}

	public String getDescrip_fun() {
		return descrip_fun;
	}

	public void setDescrip_fun(String descrip_fun) {
		this.descrip_fun = descrip_fun;
	}
}
