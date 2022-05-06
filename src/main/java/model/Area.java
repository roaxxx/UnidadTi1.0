package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Conexion;

public class Area {
	int idArea;
	private String area_name;
	private String descriptionArea;
	public Area() {

	}

	public Area(int idArea,String area_name, String descriptionArea) {
		this.idArea=idArea;
		this.area_name=area_name;
		this.descriptionArea=descriptionArea; 
	}

	//Crea nuevas áreas
	public String insertArea() {
		
		if(searchArea(idArea)!=null) {
			return "El dato ya ha sido ingresado";
		}
		String query="INSERT INTO AREA VALUES ("+getIdArea()+",'"+getArea_name()+
				"','"+getDescriptionArea()+"');";

		Statement st;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Se ha creado el area";
	}
	
	//Devuelve las áreas
	public ArrayList<Area> selectAreas() {
		ArrayList<Area> areas = new ArrayList<Area>();
		String query="SELECT* FROM AREA;";
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				areas.add(searchArea(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return areas;
	}
	
	//Devuelve las áreas especificas
	public ArrayList<Area> selectAreasBy(String colName, String data) {
		
		ArrayList<Area> areas = new ArrayList<Area>();
		String query="SELECT* FROM AREA WHERE "+colName+"="+data;
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				areas.add(searchArea(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return areas;
	}
	// Actualiza las áreas
	public String updateAreas(String field, String value) {
		if(searchArea(getIdArea())==null) {
			return "No existe el registro!!!";
		}
		
		String query="UPDATE AREA SET "+field+"='"+value+"' WHERE idArea="+getIdArea()+";";
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
	//Busca una área determinada
	public Area searchArea(int idArea) {

		String query = "SELECT* FROM AREA WHERE idArea = "+idArea;
		Statement st;
		ResultSet rs;
		Area area = null;
		try {
			Connection c = (new Conexion().getConexion());
			st = c.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				area = new Area (rs.getInt(1),rs.getString(2),rs.getString(3));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return area;
	}
	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getDescriptionArea() {
		return descriptionArea; 
	}

	public void setDescriptionArea(String descriptionArea) {
		this.descriptionArea = descriptionArea;
	}
}
