package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Conexion;

public class Funtionality {
	private int id_funtionality;
	private String name_fun;
	private String descrip_fun;
	private Component comp;
	
	public Funtionality() {
		
	}

	public Funtionality(int id_funtionality, String name_fun, String descrip_fun, Component comp) {
		this.id_funtionality = id_funtionality;
		this.name_fun = name_fun;
		this.descrip_fun = descrip_fun;
		this.comp = comp;
	}
	public void addFuncionality() {
		String query="INSERT INTO required_interface VALUES ("+getId_funtionality()+",'"
				+comp.getComp_Id()+"',"+getName_fun()+"','"+getDescrip_fun()+"');";
		Statement st;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<Funtionality> selectRequiredInterfaces() {
		ArrayList<Funtionality> fun = new ArrayList<Funtionality>();
		
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
				fun.add(new Funtionality(rs.getInt(1),rs.getString(3),rs.getString(4),comp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fun;
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
