package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Conexion;

public class Supplied_Interface {
	private int idS_interface;
	private String name_si;
	private String si_funtion;
	private Component comp;
	
	public Supplied_Interface() {
		// TODO Auto-generated constructor stub
	}
	public Supplied_Interface(int idS_interface, String name_si,
			String si_funtion, Component comp) {
		this.idS_interface = idS_interface;
		this.name_si = name_si;
		this.si_funtion = si_funtion;
		this.comp = comp;
	}
	
	public void addSuppliedInterface() {
		String query="INSERT INTO required_interface VALUES ("+getIdS_interface()+",'"
				+comp.getComp_Id()+"',"+getName_si()+"','"+getSi_funtion()+"');";
		Statement st;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Supplied_Interface> selectSuppliedInterfaces() {
		ArrayList<Supplied_Interface> sin = new ArrayList<Supplied_Interface>();
		
		String query="SELECT * FROM required_interface WHERE component_idcomponent ="
				+comp.getComp_Id()+";";
		Statement st;
		ResultSet rs;
		System.err.println(query);
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				sin.add(new Supplied_Interface(rs.getInt(1),rs.getString(3),rs.getString(4),comp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sin;
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
