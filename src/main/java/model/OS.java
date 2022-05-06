package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Conexion;


public class OS {
	private int idOs;
	private String os_name;
	
	
	public OS() {

	}

	public OS(int idOs, String os_name) {

		this.idOs = idOs;
		this.os_name = os_name;
	}
	
	public ArrayList<OS> selectOs(){
		ArrayList<OS> os = new ArrayList<OS>();
		String query = "SELECT* FROM OS;";
        Statement st;
		ResultSet rs;
		try {
			Connection c = (new Conexion().getConexion());
			st = c.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				os.add(new OS(rs.getInt(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return os;
	}

	public int getIdOs() {
		return idOs;
	}

	public void setIdOs(int idOs) {
		this.idOs = idOs;
	}

	public String getOs_name() {
		return os_name;
	}

	public void setOs_name(String os_name) {
		this.os_name = os_name;
	}


}
