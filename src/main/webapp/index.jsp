<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Iniciar sesi�n</title>
<link rel="stylesheet" href="css/loginStyle.css">
</head>
<body>
	<div class="registro">
	<img class="avatar" src ="Imgs/monDepsIcon.jpg" alt="Logo MonDevps">
		<h1>Iniciar sesi�n</h1> 
		<form action="UserGUI" method="post">		    
			    <!-- USERNAME -->
		    <label for ="username" >Usuario </label> 
			<input type="text"  placeholder="Digite el usuario"name="user" ><br>
				<!-- PASSWORD -->	
			<label for ="passwrd" >Contrase�a </label>
			<input type="password"  placeholder="Ingrese su contrase�a" name="password" >
			<br>	
			<input type="submit" value="Ingresar"> 
			<br><br><br><br>
			<a href="#"> Ha perdido su contrase�a?</a><br>
			<a href="#"> No tienen una cuenta?</a>
		</form>
	</div>
</body>
</html>