<?php
$hostname='localhost';
$database='appmo';
$username='root';
$password='';

$conexion = new mysqli($hostname, $username, $password, $database);
if ($conexion ->connect_errno) {
	echo "Conexion erronea";
} else{
	echo "La conexion fue exitosa";
}

?>