<?php
$hostname_localhost="localhost";
$database_localhost="appmo";
$username_localhost="root";
$password_localhost="";

$json=array();

    if(isset($_GET["name"]) && isset($_GET["destination"])) {
		
		$name=$_GET['name'];
		$destination=$_GET['destination'];

        
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO appmo_order (appmo_order.nameClient, appmo_order.destination)
		VALUES ('{$name}', '{$destination}')";

		print ($insert);

		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT nameClient, destination FROM appmo_order";
			$resultado=mysqli_query($conexion, $consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['order'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["name"]='No Registra';
			$resulta["destination"]='No Registra';
			$json['order'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["name"]='WS No esta funcionando';
			$resulta["destination"]='WS No esta funcionando';
			$json['order'][]=$resulta;
			echo json_encode($json);
		}

?>

