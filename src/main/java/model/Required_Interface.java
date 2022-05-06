package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Conexion;

public class Required_Interface {
	private int idR_interface;
	private String name_rq;
	private String rq_funtion;
	private Component comp;
	
	public Required_Interface() {
	
	}

	public Required_Interface(int idR_interface, String name_rq, String rq_funtion, Component comp) {
		this.idR_interface = idR_interface;
		this.name_rq = name_rq;
		this.rq_funtion = rq_funtion;
		this.comp = comp;
	}
	public void addRequiredInterface() {
		String query="INSERT INTO required_interface VALUES ("+getIdR_interface()+",'"
				+comp.getComp_Id()+"',"+getName_rq()+"','"+getRq_funtion()+"');";
		Statement st;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Required_Interface> selectRequiredInterfaces() {
		ArrayList<Required_Interface> rqi = new ArrayList<Required_Interface>();
		
		String query="SELECT * FROM required_interface WHERE component_idcomponent ="
				+comp.getComp_Id()+";";
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				rqi.add(new Required_Interface(rs.getInt(1),rs.getString(3),rs.getString(4),comp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rqi;
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
