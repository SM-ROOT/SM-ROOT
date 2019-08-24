<?PHP
$hostname_localhost ="localhost";
$database_localhost ="pru";
$username_localhost ="root";
$password_localhost ="";

$json=array();
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

$estado = $_POST["estado"];


$query_search ="UPDATE nombres set estado=1 where estado=0";
$query_exec = mysqli_query($query_search) or die (mysql_error());

mysql_close(localhost);
?>