<?PHP
$hostname_localhost="localhost";
$database_localhost="appmo";
$username_localhost="root";
$password_localhost="";

$json=array();

	if(isset($_GET["name"]) && isset($_GET["type"]) && isset($_GET["quantity"])   ) {
		
		$name=$_GET['name'];
		$type=$_GET['type'];
		$quantity=$_GET['quantity'];
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);


		$insert="INSERT INTO appmo_storageProduct (name, type, quantity)
		VALUES ('{$name}', '{$type}' , '{$quantity}')";
		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT name, type, quantity FROM appmo_storageProduct GROUP BY name";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['product'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["name"]='No Registra';
			$resulta["type"]='No Registra';
			$resulta["quantity"]='No Registra';
			$json['product'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["name"]='WS No esta funcionando';
			$resulta["type"]='WS No esta funcionando';
			$resulta["quantity"]='WS No esta funcionando';
			$json['product'][]=$resulta;
			echo json_encode($json);
		}

?>

