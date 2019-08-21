<?php
$hostname_localhost="localhost";
$database_localhost="appmo";
$username_localhost="root";
$password_localhost="";

$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);


$nameClient= $_POST["nameClient"];
$destination = $_POST["destination"];
$date = $_POST["date(format)"];
$status = $_POST["status"];

$result=mysql_query("UPDATE appmo_order set status=$status");
?>