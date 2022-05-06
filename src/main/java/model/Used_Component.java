package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controller.Conexion;

public class Used_Component {
	private String comp_impact;
	private Component comp;
	private String usedDate;
	

	
	public Used_Component(String comp_impact,Component comp,String usedDate) {
		this.comp_impact = comp_impact;
		this.comp = comp;
		this.usedDate = usedDate;
	}
	
	public String getComp_impact() {
		return comp_impact;
	}
	
	public void setComp_impact(String comp_impact) {
		this.comp_impact = comp_impact;
	}
	
}
