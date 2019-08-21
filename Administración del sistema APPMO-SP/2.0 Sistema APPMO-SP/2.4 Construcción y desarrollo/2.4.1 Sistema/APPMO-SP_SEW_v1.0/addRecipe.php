<?PHP
$hostname_localhost="localhost";
$database_localhost="appmo";
$username_localhost="root";
$password_localhost="";

$json=array();

	if(isset($_GET["name"]) && isset($_GET["type"])  ) {

	$name=$_GET['name'];
	$type=$_GET['type'];
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO appmo_recipe (name, type)
		VALUES ('{$name}', '{$type}')";
		$resultado_insert=mysqli_query($conexion,$insert);

		
		if($resultado_insert){
			$consulta="SELECT name, type FROM appmo_recipe";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['recipe'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["name"]='No Registra';
			$resulta["type"]='No Registra';
			$json['recipe'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["name"]='WS No esta funcionando';
			$resulta["type"]='WS No esta funcionando';
			$json['recipe'][]=$resulta;
			echo json_encode($json);
		}

?>

