package view;

import java.util.ArrayList;
import java.util.Scanner;

import org.eclipse.jdt.internal.compiler.lookup.BlockScope;

import model.*;

public class Test {
	static Scanner sc = new Scanner(System.in);
	public Test() {

	}
	public void items() {

	}
	public void areaT() {
		Area area1 = new Area();
		ArrayList<Area> areas = new ArrayList<Area>();
		System.out.println("Funciones: \n"+"Crear\n"+"consultar\n"
				+"actualizar\n"+"Seleccione item:");
		String o =sc.nextLine();
		switch (o) {
		case "CREAR":
			System.out.println("Nombre: ");
			area1.setIdArea(0);
			area1.setArea_name(sc.nextLine());	
			System.out.println("Funcion: ");
			area1.setDescriptionArea(sc.nextLine());
			area1.insertArea();
			break;
		case "CONSULTAR":
			selectArrreas();
			break;
		case "ACTUALIZAR":
			areas=selectArrreas();
			System.out.println("Seleccione area: ");
			int a = sc.nextInt();
			sc.nextLine();
			System.out.println("Selecione campo:\n name_area\nareafuntion\n");
			String campo= sc.nextLine();
			System.out.println("Nuevo dato: ");
			areas.get(a).updateAreas(campo, sc.nextLine());

			break;
		}
	}
	public ArrayList<Area> selectArrreas() {
		Area area1 = new Area();
		ArrayList<Area> areas = new ArrayList<Area>();
		areas = area1.selectAreas();
		for(int i = 0; i<areas.size(); i++) {
			System.out.println("\n"+i);
			System.out.println(areas.get(i).getIdArea());
			System.out.println(areas.get(i).getArea_name());
			System.out.println(areas.get(i).getDescriptionArea());
		}
		return areas;
	}
	public void devpt() {
		//Objeto de tipo área con parámetros
		Area area1 = new Area();
		ArrayList<Developer> devps = new ArrayList<Developer>();
		ArrayList<Area> areas = new ArrayList<Area>();
		Developer d = new Developer();
		System.out.println("Funciones: \n"+"Crear\n"+"consultar\n"
				+"actualizar\n"+"Inactiva\n"+"Seleccione item:");
		String o =sc.nextLine();
		switch (o) {
		case "CREAR":
			System.out.println("Cedula:");
			d.setId_card(Integer.parseInt(sc.nextLine()));
			System.out.println("nombre: ");
			d.setNames(sc.nextLine());
			System.out.println("Apellidos");
			d.setSurnames(sc.nextLine());
			System.out.println("email");
			d.setE_mail(sc.nextLine());
			System.out.println("Teléfono");
			d.setPhone(Integer.parseInt(sc.nextLine()));
			System.out.println("Direccion");
			d.setDirection(sc.nextLine());
			System.out.println("Estado: Activo o Inactivo");
			d.setDeveloper_Condition(sc.nextLine());
			System.out.println("Fecha de nacimiento:");
			d.setBirth_Date(sc.nextLine());
			areas=selectArrreas();
			System.out.println("Seleccione area a la que pertenece");
			d.setArea(areas.get(Integer.parseInt(sc.nextLine())));
			d.insertDeveloper();
			break;
		case "CONSULTAR":
			selecD();
			break;
		case "ACTUALIZAR":
			devps= selecD();
			System.out.println("\nSeleccione desarrollador\n");
			int a = sc.nextInt();
			sc.nextLine();
			System.out.println("\nSelecione campo:\n");
			String campo= sc.nextLine();
			System.out.println("Nuevo dato: ");
			devps.get(a).updateDeveloper(campo, sc.nextLine());
			break;
		case "INACTIVAR":
			devps= selecD();
			System.out.println("\nSeleccione desarrollador\n");
			int b = sc.nextInt();
			sc.nextLine();
			devps.get(b).devChangeState();
			break;

		}
	}
	public ArrayList<Developer> selecD(){
		Developer d = new Developer();
		ArrayList<Developer> devps = new ArrayList<Developer>();
		devps= d.selectDevelopers();
		for(int i = 0; i<devps.size(); i++) {
			System.out.println("\n"+i);
			System.out.println(devps.get(i).getId_card());
			System.out.println(devps.get(i).getArea().getArea_name());
			System.out.println(devps.get(i).getNames());
			System.out.println(devps.get(i).getE_mail()+"\n"+devps.get(i).getPhone()+
					"\n"+devps.get(i).getDirection()+"\n"+devps.get(i).getDeveloper_Condition()+
					"\n"+devps.get(i).getBirth_Date());
		}
		return devps;
	}

	public void teamT() {
		Team team = new Team();
		ArrayList<Team> teams = new ArrayList<Team>();
		ArrayList<Team_Up> tms = new ArrayList<Team_Up>();
		System.out.println("Funciones: \n"+"Crear\n"+"consultar\n"
				+"actualizar\n"+"Seleccione item:");
		String o =sc.nextLine();
		switch (o) {
		case "CREAR":
			System.out.println("Código:");
			team.setTeam_Id(Integer.parseInt(sc.nextLine()));
			System.out.println("nombre: ");
			team.setTeam_name(sc.nextLine());
			System.out.println("Fecha de creación");
			team.setCreated_date(sc.nextLine());
			team.addTeam();

			ArrayList<Developer> devps = new ArrayList<Developer>();
			devps= selecD();
			String s= " ";
			while(!s.equals("END")) {
				System.out.println("Seleccione participantes del equipo"+team.getTeam_name());
				String participante=sc.nextLine();
				System.out.println("Roll");
				tms.add(new Team_Up(devps.get(Integer.parseInt(participante)),sc.nextLine()));
				System.out.println("Terminar: (END)");
				s = sc.nextLine();
			}
			team.addteam_Ups(tms);
			break;
		case "CONSULTAR":
			teams = selcTeams();
			System.out.println("Elija el equipo");
			int t = Integer.parseInt(sc.nextLine());

			tms = teams.get(t).selectTeam_Up();
			for ( int i =0; i<tms.size();i++) {
				System.out.println(tms.get(i).getDevelopers().getNames());
			}

			break;
		case "ACTUALIZAR":
			teams =selcTeams();
			System.out.println("\nSeleccione el equipo\n");
			int a = sc.nextInt();
			sc.nextLine();
			System.out.println("\nSelecione campo:\n");
			String campo= sc.nextLine();
			System.out.println("Nuevo dato: ");
			teams.get(a).updateTeam(campo, sc.nextLine());


			break;
		}
		team_up(team);
		team_up(team);

	}
	public ArrayList<Team> selcTeams(){
		Team t = new Team();
		ArrayList<Team> teams = new ArrayList<Team>();
		teams= t.selectTeams();
		for(int i = 0; i<teams.size(); i++) {
			System.out.println("\n"+i);
			System.out.println(teams.get(i).getCreated_date());
		}
		return teams;
	}

	public void team_up(Team team) {


		//Team_Up tu2 = new Team_Up(devps1, team, sc.nextLine());
		//tu2.teamUp();
	}

	public void projectT() {
		ArrayList<Team> teams = new ArrayList<Team>();
		ArrayList<Project> projs = new ArrayList<Project>();
		Project p = new Project();
		System.out.println("Funciones: \n"+"Crear\n"+"consultar\n"
				+"actualizar\n"+"Inactiva\n"+"Seleccione item:");
		String o =sc.nextLine();
		switch (o) {
		case "CREAR":
			System.out.println("Código:");
			p.setProject_Id(Integer.parseInt(sc.nextLine()));
			System.out.println("nombre: ");
			p.setProject_Name(sc.nextLine());
			System.out.println("Descripción del proyecto");
			p.setDescription_project(sc.nextLine());
			System.out.println("Fecha de entrega");
			p.setDate_finish(sc.nextLine());
			System.out.println("Estado de proyecto (ACTIVO,INACTIVO)");
			p.setProject_State(sc.nextLine());
			System.out.println("Seleccione equipo encargado");
			p.setTeam(selcTeams().get(Integer.parseInt(sc.nextLine())));
			p.addProject();
			break;
		case "CONSULTAR":
			selecProj();
			break;
		case "ACTUALIZAR":
			projs =selecProj();
			System.out.println("\nSeleccione el proyecto\n");
			int a = sc.nextInt();
			sc.nextLine();
			System.out.println("\nSelecione campo:\n");
			String campo= sc.nextLine();
			System.out.println("Nuevo dato: ");
			projs.get(a).updateProject(campo, sc.nextLine());
			break;
		case "INACTIVAR":
			projs =selecProj();
			System.out.println("\nSeleccione proyecto: \n");
			int b = sc.nextInt();
			sc.nextLine();
			projs.get(b).projChangeState();
			break;

		}
	}
	public ArrayList<Project> selecProj(){
		Project p = new Project();
		ArrayList<Project> projs = new ArrayList<Project>();
		projs = p.selectProjects();
		for(int i = 0; i<projs.size(); i++) {
			System.out.println("\n"+i);
			System.out.println(projs.get(i).getProject_Id());
			System.out.println(projs.get(i).getTeam().getTeam_name());
			System.out.println(projs.get(i).getProject_Name());
			System.out.println(projs.get(i).getDate_finish());
		}
		return projs;
	}

	public ArrayList<OS> os() {
		OS os = new OS();
		ArrayList<OS> oss2 = new ArrayList<OS>();
		ArrayList<OS> osS = new ArrayList<OS>();
		osS= os.selecAllOs();
		for(int i = 0; i<osS.size(); i++) {
			System.out.println(i);
			System.out.println(osS.get(i).getIdOs());
			System.out.println(osS.get(i).getOs_name());

		}
		return oss2;
	}
	public void compT() {
		OS os = new OS();
		ArrayList<Component> comps = new ArrayList<Component>();
		ArrayList<OS> oss = new ArrayList<OS>();
		ArrayList<OS> oss1 = new ArrayList<OS>();
		ArrayList<KeyWord> kws = new ArrayList<KeyWord>();
		String link= "https://github.com/roaxxx/Comp.git";
		Component comp = new Component();
		System.out.println("Funciones: \n"+"Crear\n"+"consultar\n"
				+"actualizar\n"+"Inactiva\n"+"Seleccione item:");
		String o =sc.nextLine();
		switch (o) {
		case "CREAR":
			System.out.println("Código:");
			comp.setComp_Id(Integer.parseInt(sc.nextLine()));
			System.out.println("nombre: ");
			comp.setComp_name(sc.nextLine());
			System.out.println("Ubicación");
			comp.setComp_location(sc.nextLine());
			System.out.println("Contexto de componente");
			comp.setComp_domain(sc.nextLine());
			System.out.println("Fecha de incorporación");
			comp.setComp_added_date(sc.nextLine());
			System.out.println("categoria de componente");
			comp.setComp_category(sc.nextLine());
			System.out.println("Estado (ACTIVO,INACTIVO)");
			comp.setComp_State(sc.nextLine());
			//comp.insertComponent();
			oss= selectOS();
			String s= " ";
			while(!s.equals("END")) {
				System.out.println("Seleccione sistemas operativos compatibles");
				oss1.add(oss.get(Integer.parseInt(sc.nextLine())));
				System.out.println("Terminar: (END)");
				s = sc.nextLine();
			}
			//comp.setOSsopported(oss1);
			s= " ";
			//KeyWord k = new KeyWord();
			while(!s.equals("END")) {
				System.out.println("Ingrese la palabra clave");
				kws.add(new KeyWord(0,sc.nextLine()));
				System.out.println("Terminar: (END)");
				s = sc.nextLine();
			}
			comp.setKeyWords(kws);
				break;
		case "CONSULTAR":
			selectComps();
			break;
		case "ACTUALIZAR":
			comps = selectComps();
			System.out.println("\nSeleccione el componente\n");
			int a = sc.nextInt();
			sc.nextLine();
			System.out.println("\nSelecione campo:\n");
			String campo= sc.nextLine();
			System.out.println("Nuevo dato: ");
			comps.get(a).updateComponent(campo, sc.nextLine());
			break;
		case "INACTIVAR":
			comps = selectComps();
			System.out.println("\nSeleccione el componente\n");
			int b = sc.nextInt();
			sc.nextLine();
			comp.compChangeState();
			break;

		}
	}
	public ArrayList<OS> selectOS() {
		OS os = new OS();
		ArrayList<OS> kws = new ArrayList<OS>();
		kws = os.selecAllOs();
		System.out.println(kws.size());
		for (int i = 0; i <kws.size(); i++) {
			System.out.println(i);
			System.out.println(kws.get(i).getOs_name());
		}
		
		return kws;
	}

	public ArrayList<Component> selectComps() {
		Component comp = new Component();
		ArrayList<Component> comps = new ArrayList<Component>();
		comps = comp.selectComponents();
		for(int i = 0; i<comps.size(); i++) {
			System.out.println(i);
			System.out.println(comps.get(i).getComp_Id());
			System.out.println(comps.get(i).getComp_name());

		}
		return comps;
	}
	public void generalT() {

	}

	public static void main(String[] args) {
		Test t = new Test();
		//t.os();

		while(true) {
			System.out.println("\nItems relacionados:" +"\n"+"area"+"\n"+"desarrollador\n"
					+"Equipo\n"+"Proyecto\n"+"Componente\n");

			System.out.println("Seleccione item: ");
			String option = sc.nextLine();		
			if(option.toUpperCase().equals("AREA")) {
				t.areaT();
			}else if(option.toUpperCase().equals("DESARROLLADOR")) {
				t.devpt();
			}else if(option.toUpperCase().equals("EQUIPO")) {
				t.teamT();
			}else if(option.toUpperCase().equals("PROYECTO")) {
				t.projectT();	
			}else if(option.toUpperCase().equals("COMPONENTE")) {
				t.compT();
			}
		}
	}
}
