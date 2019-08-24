<?php
//funcoina correctamente con la app Móvil de login San Pedro

#include('conexion.php'); 
include('functions.php'); 

$user=$_GET['user'];


//$tipo=$_GET["tipo"];
//	$user=$_GET['usuario']; esto funciona de esta forma tambien //usuario es la columna de la tabla

	if ($resultset = getSQLResultSet("SELECT contrasena FROM usuarios WHERE usuario= '$user'")) { //parainiciar login con sol una tabla


//if ($resultset = getSQLResultSet("SELECT contrasena FROM usuarios INNER JOIN tipo_usuario WHERE usuarios.tipo = tipo_usuario.id_tipo AND usuario='$user'")){  //esta relacionando con otra tabla



		//if ($resultset = getSQLResultSet("SELECT contrasena FROM `usuarios` WHERE usuario='{$user}'")){ //esto funciona bien de esta forma sin la columna TIPO
	
    	while ($row = $resultset->fetch_array(MYSQLI_NUM)) {
    	echo json_encode($row);
		
    	
    	}
    	
   }
   
?>