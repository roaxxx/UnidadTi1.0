package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Conexion;

public class Project {
	private int project_Id;
	private Team team;
	private String project_Name;
	private String description_project;
	private String date_finish;
	private String project_State;
	private ArrayList<Used_Component> used_Comp;

	public Project() {
		
	}

	public Project(int project_Id, Team team, String project_Name, String description_project,
			String date_finish,String project_State) {
		
		this.project_Id = project_Id;
		this.team = team;
		this.project_Name = project_Name;
		this.description_project = description_project;
		this.date_finish = date_finish;
		this.project_State = project_State;
	}
	//Se crea un nuevo proyecto
	public String addProject() {
		if(searchProject(getProject_Id())!=null) {
			return "El proyecto ya existe";
		}
		String query="INSERT INTO project VALUES ("+getProject_Id()+","+team.getTeam_Id()+
				",'"+getProject_Name()+"','"+getDescription_project()+"','"+getDate_finish()+
				"','"+getProject_State()+"');";
		Statement st;
		System.err.println(query);
		try {
			Connection c = (new Conexion().getConexion());
			st = c.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return "Se ha creado el equipo";
	}
	
	
	//Se consultan todos los equipos
	public ArrayList<Project> selectProjects(){
		ArrayList<Project> projects = new ArrayList<Project>();
		String query="SELECT * FROM PROJECT;";
		Statement st;
		ResultSet rs;
		try {
			Connection c = (new Conexion().getConexion());
			st = c.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				projects.add(searchProject(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;
	}
	//Se consultan proyectos especificos
	public ArrayList<Project> selectProjectsBy(String colName, String data){
		ArrayList<Project> projects = new ArrayList<Project>();
		String query="SELECT* FROM PROJECT WHERE "+colName+"='"+data+"';";
		Statement st;
		ResultSet rs;
		try {
			Connection c = (new Conexion().getConexion());
			st = c.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				projects.add(searchProject(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return projects;
	}
	//Actualiza un proyecto
	public String updateProject(String field,String value) {
		if(searchProject(getProject_Id())==null) {
			return "No existe el registro!!!";
		}
		String query="UPDATE PROJECT SET "+field+"='"+value+"'"
				+ " WHERE idProject ="+getProject_Id()+";";
		Statement st;
		try {
			Connection c = (new Conexion().getConexion());
			st = c.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Se ha actualizado el proyecto";
	}
	//Método para inactivar un proyecto
	public String projChangeState() {
		String change= null;
		String reply =  "!Se ha inactivado¡";
		if(searchProject(getProject_Id())==null) {
			return "No existe el registro!!!";
		}
		if(searchProject(getProject_Id()).getProject_State()
				.equals("ACTIVO")) {
			change ="'INACTIVO'";
		}else {
			reply = "!Se ha Activado¡";
			change ="'ACTIVO'";
		}
		String query="UPDATE PROJECT SET project_state ="+change
				+ " WHERE idProject="+getProject_Id()+";";
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
	//Método que busca un proyecto en especifico
	public Project searchProject(int id_project){
		Project project = null;
		ResultSet rs;
		Statement st;
		String query = "SELECT * FROM project WHERE idProject ="+id_project;
		try {
			Connection c = (new Conexion().getConexion());
			st = c.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				project = new Project(rs.getInt(1),(new Team().searchTeam(rs.getInt(2))),
						rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return project;
	}
	
	public void addUsedComponent(ArrayList<Used_Component> used_Comp) {
		this.used_Comp = used_Comp;
		Statement st;
		try {
			Connection c = (new Conexion().getConexion());
			for (int i = 0; i<used_Comp.size();i++) {
				String query = "INSERT INTO PROJECT_has_COMPONENT VALUES ("
						+getProject_Id()+","+used_Comp.get(i)+")";
				System.out.println(query);
				st = c.createStatement();
				//st.execute(query);
			}		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<Used_Component> selectUsedComponents() {
		ArrayList<Project> projects = new ArrayList<Project>();
		String query="SELECT * FROM PROJECT_has_COMPONENT;";
		Statement st;
		ResultSet rs;
		try {
			Connection c = (new Conexion().getConexion());
			st = c.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				used_Comp.add(new Used_Component(rs.getString(4),new Component()
						.searchComponent(rs.getInt(2)),rs.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return used_Comp;
	}
	public int getProject_Id() {
		return project_Id;
	}

	public void setProject_Id(int project_Id) {
		this.project_Id = project_Id;
	}

	public String getProject_Name() {
		return project_Name;
	}

	public void setProject_Name(String project_Name) {
		this.project_Name = project_Name;
	}

	public String getDescription_project() {
		return description_project;
	}

	public void setDescription_project(String description_project) {
		this.description_project = description_project;
	}

	public String getDate_finish() {
		return date_finish;
	}

	public void setDate_finish(String date_finish) {
		this.date_finish = date_finish;
	}

	public String getProject_State() {
		return project_State;
	}

	public void setProject_State(String project_State) {
		this.project_State = project_State;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
