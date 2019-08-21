<?PHP
$hostname_localhost ="localhost";
$database_localhost ="appmo";
$username_localhost ="root";
$password_localhost ="";

$json=array();
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

$idOrder=$_POST['idOrder'];
$status=$_POST['status'];

$consulta="UPDATE appmo_order SET status= '".$status."' WHERE  idOrder= '".$idOrder."' ";
  
 mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);
?>