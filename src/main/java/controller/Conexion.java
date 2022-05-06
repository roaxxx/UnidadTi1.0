package controller;


import java.sql.*;

public class Conexion {
	private Connection conexion;
	
	public Conexion() {
		
	}

	public Connection getConexion() {
		String bd ="unidadtif";
		String port="3306";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:"+port+"/"+bd+"?useTimezone=true&serverTimezone=UTC";
			String usuario="fernando_roa";
			String password="qemu.hw998";
			conexion = DriverManager.getConnection(url, usuario, password);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		return conexion;
	}
}