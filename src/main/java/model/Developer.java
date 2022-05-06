package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Conexion;

public class Developer {
	private int id_Card;
	private Area area;
	private String names;
	private String surnames;
	private String e_mail;
	private int phone;
	private String direction;
	private String developer_Condition;
	private String birth_Date;

	public Developer() {

	}
	public Developer(int id_Card, Area area, String names, String surnames, String e_mail,
			int phone, String direction,String developer_Condition, String birth_Date) {
		this.id_Card = id_Card;
		this.area = area;
		this.names = names;
		this.surnames = surnames;
		this.e_mail = e_mail;
		this.phone = phone;
		this.direction = direction;
		this.developer_Condition = developer_Condition;
		this.birth_Date = birth_Date;
	}

	//Registro de desarrolladores
	public String insertDeveloper() {
		if(searchDeveloper(getId_card())!=null) {
			return "!Error¡ El dato ya ha sido ingresado";
		}
		String query="INSERT INTO DEVELOPER VALUES ("+getId_card()+
				","+area.getIdArea()+",'"+getNames()+"','"+getSurnames()+"','"+getE_mail()
				+"',"+getPhone()+",'"+getDirection()+"','"+getDeveloper_Condition()+
				"','"+getBirth_Date()+"');";
		Statement st;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Se ha registrado el desarrollador";
	}
	//Devuelve todos los desarrolladores en la base de datos
	public ArrayList<Developer> selectDevelopers() {
		ArrayList<Developer> devps = new ArrayList<Developer>();
		String query="SELECT * FROM DEVELOPER;";
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				devps.add(searchDeveloper(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return devps;
	}

	public ArrayList<Developer> selectDevelopersBy(String colName, String data) {
		ArrayList<Developer> devps = new ArrayList<Developer>();
		String query="SELECT * FROM DEVELOPER WHERE "+colName+"="+data+";";
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				devps.add(searchDeveloper(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return devps;
	}
	// Actualiza las áreas
	public String updateDeveloper(String field, String value) {
		if(searchDeveloper(getId_card())==null) {
			return "No existe el registro!!!";
		}
		String query="UPDATE DEVELOPER SET "+field+"='"+value+"'"
				+ " WHERE id_Card="+getId_card()+";";
		Statement st;
		try {
			Connection c = (new Conexion().getConexion());
			st = c.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Se ha actualizado el registro";
	}
    
	//Método para inactivar un desarrollador
	public String devChangeState() {
		String change= null;
		String reply =  "!Se ha inactivado¡";
		if(searchDeveloper(getId_card())==null) {
			return "No existe el registro!!!";
		}
		if(searchDeveloper(getId_card()).getDeveloper_Condition()
				.equals("Activo")) {
			change ="'Inactivo'";
		}else {
			reply = "!Se ha Activado¡";
			change ="'Activo'";
		}
		String query="UPDATE DEVELOPER SET dev_state ="+change
				+ " WHERE id_Card="+getId_card()+";";
		Statement st;
		try {
			Connection c = (new Conexion().getConexion());
			st = c.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reply;
	}
	//Buscar un desarollador en especifico
	public Developer searchDeveloper(int id_Card) {
		Developer developer = null;
		String query = "SELECT* FROM DEVELOPER WHERE id_Card = "+id_Card;
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				developer = new Developer (rs.getInt(1),(new Area().searchArea(rs.getInt(2))),
						rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),
						rs.getString(7),rs.getString(8),rs.getString(9));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return developer;
	}

	public int getId_card() {
		return id_Card;
	}

	public void setId_card(int id_Card) {
		this.id_Card = id_Card;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getBirth_Date() {
		return birth_Date;
	}

	public void setBirth_Date(String birth_Date) {
		this.birth_Date = birth_Date;
	}

	public String getDeveloper_Condition() {
		return developer_Condition;
	}

	public void setDeveloper_Condition(String developer_Condition) {
		this.developer_Condition = developer_Condition;
	}

	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
}
