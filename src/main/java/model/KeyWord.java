package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Conexion;

public class KeyWord {
	private int idKeyWord;
	private Component comp;
	private String key_word;
	
	public KeyWord(int idKeyWord, String key_word, Component comp) {
		this.idKeyWord = idKeyWord;
		this.key_word = key_word;
		this.comp = comp;
	}

	public KeyWord() {
		
	}

	public void addKeyWord() {
		String query="INSERT INTO keyword VALUES ("+comp.getComp_Id()+",'"+getKey_word()+");";
		Statement st;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<KeyWord> selectKeyWords() {
		ArrayList<KeyWord> kw = new ArrayList<KeyWord>();
		
		String query="SELECT * FROM keyword WHERE component_idcomponent ="+comp.getComp_Id()+";";
		Statement st;
		ResultSet rs;
		System.err.println(query);
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				kw.add(new KeyWord(rs.getInt(1),rs.getString(3),comp));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kw;
	}
	public int getIdKeyWord() {
		return idKeyWord;
	}
	public void setIdKeyWord(int idKeyWord) {
		this.idKeyWord = idKeyWord;
	}
	public String getKey_word() {
		return key_word;
	}
	public void setKey_word(String key_word) {
		this.key_word = key_word;
	}
	public Component getComp() {
		return comp;
	}
	public void setComp(Component comp) {
		this.comp = comp;
	}
}
