<?PHP
$hostname_localhost="localhost";
$database_localhost="appmo";
$username_localhost="root";
$password_localhost="";

$json=array();

	if(isset($_GET["purchaseDate"]) ) {
		
		$purchaseDate=$_GET['purchaseDate'];
		
		
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);


		$insert="INSERT INTO appmo_purchase (purchaseDate)
		VALUES ('{$purchaseDate}' )";
		$resultado_insert=mysqli_query($conexion,$insert);
		
		
		if($resultado_insert){
			$consulta="SELECT * FROM appmo_purchase GROUP BY idPurchase";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['purchase'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["purchaseDate"]='No Registra';
			$json['purchase'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["purchaseDate"]='WS No esta funcionando';
			$json['purchase'][]=$resulta;
			echo json_encode($json);
		}

?>

