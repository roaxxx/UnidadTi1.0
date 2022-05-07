package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Conexion;

public class Team {
    int team_Id;
	private String team_name;
	private String created_date;
	private ArrayList<Team_Up> team_up;

	public Team() {
		this.team_up = new ArrayList<Team_Up>();
	}

	public Team(int team_Id, String team_name, String created_date) {
		super();
		this.team_Id = team_Id;
		this.team_name = team_name;
		this.created_date = created_date;
	}

	//Se crea un nuevo equipo
	public String addTeam() {
		if(searchTeam(team_Id)!=null) {
			return "El equipo ya existe";
		}
		String query="INSERT INTO TEAM VALUES ("+getTeam_Id()+",'"+getTeam_name()+
				"','"+getCreated_date()+"');";
		Statement st;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Se ha creado el equipo";
	}

	//Se consultan todos los equipos
	public ArrayList<Team> selectTeams(){
		ArrayList<Team> teams = new ArrayList<Team>();
		String query="SELECT* FROM TEAM;";
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				teams.add(searchTeam(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teams;
	}

	//Consulta todos los equipos con un dato especifico
	public ArrayList<Team> selectTeamsBy(String field, String data){
		ArrayList<Team> teams = new ArrayList<Team>();
		String query="SELECT*FROM TEAM WHERE "+field+"="+data;
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				teams.add(searchTeam(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return teams;
	}

	//Actualiza un equipo
	public String updateTeam(String field, String value) {
		if(searchTeam(getTeam_Id())==null) {
			return "No existe el registro!!!";
		}

		String query="UPDATE TEAM SET "+field+"='"+value+"' WHERE idTeam="+getTeam_Id()+";";
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

	//Se busca un equipo especifico
	public Team searchTeam(int id_team){
		Team team = null;
		ResultSet rs;
		Statement st;
		String query = "SELECT * FROM TEAM WHERE idteam ="+id_team;
		try {
			Connection c = (new Conexion().getConexion());
			st = c.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				team = new Team(rs.getInt(1),rs.getString(2),rs.getString(3));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return team;
	}	

	public boolean addteam_Ups(ArrayList<Team_Up> team_up) {
		this.team_up=team_up;
		for (int i = 0; i < team_up.size(); i++) {
			String query = "INSERT INTO developer_has_team values ("
					+team_up.get(i).getDevelopers().getId_card()+","+getTeam_Id()+
					",'"+team_up.get(i).getRol()+"');";
			Statement st;
			try {
				Connection connect = (new Conexion().getConexion());
				st = connect.createStatement();
				st.execute(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return true;
	}

	public ArrayList<Team_Up> selectTeam_Up(){
		ArrayList<Team_Up> team_ups = new ArrayList<Team_Up>();
		ResultSet rs;
		Statement st;
		String query = "SELECT * FROM developer_has_team WHERE team_idteam ="+getTeam_Id();	
		try {
			Connection c = (new Conexion().getConexion());
			st = c.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				team_ups.add(new Team_Up(new Developer().searchDeveloper(rs.getInt(1)), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return team_ups;
	}
	public int getTeam_Id() {
		return team_Id;
	}
	
	public void setTeam_Id(int team_Id) {
		this.team_Id = team_Id;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String name_Proyect) {
		this.team_name = name_Proyect;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public ArrayList<Team_Up> getTeam_up() {
		return team_up;
	}

	public void setTeam_up(ArrayList<Team_Up> team_up) {
		this.team_up = team_up;
	}
	
}
