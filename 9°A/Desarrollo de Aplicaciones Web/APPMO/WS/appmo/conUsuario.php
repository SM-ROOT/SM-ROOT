<?php
//funcoina correctamente con la app Móvil de login San Pedro
include('functions.php'); 

	//$user=$_GET['usuario']; esto funciona de esta forma tambien //usuario es la columna de la tabla

	//if ($resultset = getSQLResultSet("SELECT usuario, contrasena FROM usuarios WHERE tipo = 1")) { //parainiciar login con sol una tabla


if ($resultset = getSQLResultSet("SELECT name, password FROM appmo_user WHERE type ='GERENTE' ")){  //esta relacionando con otra tabla



		//if ($resultset = getSQLResultSet("SELECT contrasena FROM `usuarios` WHERE usuario='{$user}'")){ //esto funciona bien de esta forma sin la columna TIPO
	
    	while ($row = $resultset->fetch_array(MYSQLI_NUM)) {
    	echo json_encode($row);
		
    	
    	}
    	
   }
   
?>