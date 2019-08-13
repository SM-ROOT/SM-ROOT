<?php
$hostname_localhost="localhost";
$database_localhost="appmo";
$username_localhost="root";
$password_localhost="";

$json=array();

    if(isset($_GET["name"]) && isset($_GET["curator"]) ) {
		
		$name=$_GET['name'];
		$curator=$_GET['curator'];
        
        
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="DELETE FROM appmo_route WHERE idRoute = idRoute";


		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT * FROM appmo_route";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['route'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["name"]='No Registra';
			$resulta["curator"]='No Registra';
			$json['route'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["name"]='WS No esta funcionando';
			$resulta["curator"]='WS No esta funcionando';
			$json['route'][]=$resulta;
			echo json_encode($json);
		}

?>

