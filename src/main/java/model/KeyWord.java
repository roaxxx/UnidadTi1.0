package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.Conexion;

public class KeyWord {
	private int idKeyWord;
	private String key_word;
	
	public KeyWord() {
		
	}
	
	public KeyWord(int idKeyWord, String key_word) {
		this.idKeyWord = idKeyWord;
		this.key_word = key_word;
	}


	public KeyWord searchSopporedOs(int idkeyWord) {
		KeyWord kw= null;
		String query="SELECT * FROM keyword WHERE idKEYWORD ="+idkeyWord+";";
		Statement st;
		ResultSet rs;
		try {
			Connection connect = (new Conexion().getConexion());
			st = connect.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				kw = new KeyWord(rs.getInt(1), rs.getString(3));
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
}