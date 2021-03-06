package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Conexion;

public class Component {
	private int comp_Id;
	private String comp_name;
    private String comp_location;
    private String comp_domain;
    private String comp_added_date;
    private String comp_category;
    private String comp_State;
    private ArrayList<OS> os;
    private ArrayList<KeyWord> keywords;
    private ArrayList<Required_Interface> required_Interfaces;
    private ArrayList<Supplied_Interface> supplied_Interfaces;
    private ArrayList<Funtionality> funcionalities;

    public Component() {
  
    }
	
	public Component(int comp_Id, String comp_name, String comp_location, String comp_domain, String comp_added_date,
			String comp_category, String comp_State) {
		super();
		this.comp_Id = comp_Id;
		this.comp_name = comp_name;
		this.comp_location = comp_location;
		this.comp_domain = comp_domain;
		this.comp_added_date = comp_added_date;
		this.comp_category = comp_category;
		this.comp_State = comp_State;
	}


	//Registro de desarrolladores
	public String insertComponent() {
		if(searchComponent(getComp_Id())!=null) {
			return "!Error? El componente ya existe!";
		}
		String query="INSERT INTO COMPONENT VALUES ("+getComp_Id()+",'"+getComp_name()+
				"','"+getComp_location()+"','"+getComp_domain()+"','"+getComp_added_date()+
				"','"+getComp_category()+"','"+getComp_State()+"');";
		Statement st;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Se ha registrado el componente";
	}
	
	//Devuelve todos los desarrolladores en la base de datos
	public ArrayList<Component> selectComponents() {
		ArrayList<Component> comps = new ArrayList<Component>();
		String query="SELECT * FROM COMPONENT;";
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				comps.add(searchComponent(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comps;
	}

	public ArrayList<Component> selectComponentsBy(String colName, String data) {
		ArrayList<Component> comps = new ArrayList<Component>();
		String query="SELECT * FROM COMPONENT WHERE "+colName+"='"+data+"';";
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				comps.add(searchComponent(rs.getInt(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comps;
	
	}
	// Actualiza las ?reas
	public String updateComponent(String field, String value) {
		if(searchComponent(getComp_Id())==null) {
			return "No existe el registro!!!";
		}
		String query="UPDATE COMPONENT SET "+field+"='"+value+"'"
				+ " WHERE idComponent="+getComp_Id()+";";
		Statement st;
		try {
			Connection c = (new Conexion().getConexion());
			st = c.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Se ha actualizado el componente";
	}
    
	//M?todo para inactivar un desarrollador
	public String compChangeState() {
		String change= null;
		String reply =  "!Se ha inactivado?";
		if(searchComponent(getComp_Id())==null) {
			return "No existe el registro!!!";
		}
		if(searchComponent(getComp_Id()).getComp_State()
				.equals("ACTIVO")) {
			change ="'INACTIVO'";
		}else {
			reply = "!Se ha Activado?";
			change ="'ACTIVO'";
		}
		String query="UPDATE COMPONENT SET comp_state ="+change
				+ " WHERE idComponent="+getComp_Id()+";";
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
	public Component searchComponent(int idComponent) {
		Component comp = null;
		String query = "SELECT* FROM COMPONENT WHERE idComponent = "+idComponent;
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				comp = new Component (rs.getInt(1),rs.getString(2),
						rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),
						rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comp;
	}
	
	public void setOSsopported(ArrayList<OS> os) {
		Connection connect = (new Conexion().getConexion());
		for(int i = 0; i < os.size(); i++) {
			
			String query="INSERT INTO supported_os VALUES ("+getComp_Id()+","
					+os.get(i).getIdOs()+");";
			Statement st;
			try {
				st = connect.createStatement();
				st.executeUpdate(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	
	
	public ArrayList<OS> getOsSopported(){
		String query="SELECT * FROM supported_os WHERE COMPONENT_idComponent ="+getComp_Id()+";";
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				os.add(new OS().searchSopporedOs(rs.getInt(2)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return os;
	}
	
	public void setKeyWords(ArrayList<KeyWord> keyWords) {
		Connection connect = (new Conexion().getConexion());
		for(int i = 0; i < keyWords.size(); i++) {
			
			String query="INSERT INTO keyword VALUES ("+keyWords.get(i).getIdKeyWord()
					+","+getComp_Id()+",'"+keyWords.get(i).getKey_word()+"');";
			Statement st;
			try {
				st = connect.createStatement();
                st.execute(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	
	
	public ArrayList<KeyWord> getKeyWords(){
		String query="SELECT * FROM keyword WHERE COMPONENT_idComponent ="+getComp_Id()+";";
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				keywords.add(new KeyWord(rs.getInt(1), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return keywords;
	}
	
	public void setFuntionality(ArrayList<Funtionality> funcionalities) {
		Connection connect = (new Conexion().getConexion());
		for(int i = 0; i <funcionalities.size(); i++) {
			
			String query="INSERT INTO functionality VALUES ("+funcionalities.get(i)
				.getId_funtionality()+","+getComp_Id()+",'"+funcionalities.get(i).getName_fun()
				+"','"+funcionalities.get(i).getDescrip_fun()+"');";
			Statement st;
			try {
				st = connect.createStatement();
                st.execute(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	

	public ArrayList<Funtionality> getFuncionlities(){
		String query="SELECT * FROM functionality WHERE COMPONENT_idComponent ="+getComp_Id()+";";
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				funcionalities.add(new Funtionality(rs.getInt(1),rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funcionalities;
	}
	
	public void setRequiredInterface(ArrayList<Required_Interface> requiredInterfaces) {
		Connection connect = (new Conexion().getConexion());
		for(int i = 0; i <requiredInterfaces.size(); i++) {
			
			String query="INSERT INTO required_interface VALUES ("+requiredInterfaces.get(i)
			.getIdR_interface()+","+getComp_Id()+",'"+requiredInterfaces.get(i).getName_rq()
				+"','"+requiredInterfaces.get(i).getRq_funtion()+"');";
			Statement st;
			try {
				st = connect.createStatement();
                st.execute(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	

	public ArrayList<Required_Interface> getRequiredInterfaces(){
		String query="SELECT * FROM functionality WHERE COMPONENT_idComponent ="+getComp_Id()+";";
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				required_Interfaces.add(new Required_Interface(rs.getInt(1),rs.getString(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return required_Interfaces;
	}
	
	public void setSuppliedInterface(ArrayList<Supplied_Interface> suppliedInterfaces) {
		Connection connect = (new Conexion().getConexion());
		for(int i = 0; i <suppliedInterfaces.size(); i++) {
			
			String query="INSERT INTO supplied_interface VALUES ("+suppliedInterfaces.get(i)
			.getIdS_interface()+","+getComp_Id()+",'"+suppliedInterfaces.get(i).getName_si()
				+"','"+suppliedInterfaces.get(i).getSi_funtion()+"');";
			Statement st;
			try {
				st = connect.createStatement();
                st.execute(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	

	public ArrayList<Supplied_Interface> getSuppliedInterfaces(){
		String query="SELECT * FROM supplied_interface WHERE COMPONENT_idComponent ="+getComp_Id()+";";
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				supplied_Interfaces.add(new Supplied_Interface(rs.getInt(1),rs.getString(3),rs.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return supplied_Interfaces;
	} 
	  
	public int getComp_Id() {
		return comp_Id;
	}
	
	public void setComp_Id(int comp_Id) {
		this.comp_Id = comp_Id;
	}
	public String getComp_name() {
		return comp_name;
	}
	public void setComp_name(String comp_name) {
		this.comp_name = comp_name;
	}
	public String getComp_location() {
		return comp_location;
	}
	public void setComp_location(String comp_location) {
		this.comp_location = comp_location;
	}
	public String getComp_domain() {
		return comp_domain;
	}
	public void setComp_domain(String comp_domain) {
		this.comp_domain = comp_domain;
	}
	public String getComp_added_date() {
		return comp_added_date;
	}
	public void setComp_added_date(String comp_added_date) {
		this.comp_added_date = comp_added_date;
	}
	public String getComp_category() {
		return comp_category;
	}
	public void setComp_category(String comp_category) {
		this.comp_category = comp_category;
	}
	public String getComp_State() {
		return comp_State;
	}
	public void setComp_State(String comp_State) {
		this.comp_State = comp_State;
	}  
}