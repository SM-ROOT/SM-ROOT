<?PHP
$hostname_localhost="localhost";
$database_localhost="appmo";
$username_localhost="root";
$password_localhost="";

$json=array();

	if(isset($_GET["name"]) && isset($_GET["quantity"])  && isset($_GET["measure"]) && isset($_GET["coste"]) ) {
		
		$name=$_GET['name'];
		$quantity=$_GET['quantity'];
		$measure=$_GET['measure'];
		$coste=$_GET['coste'];
		
		
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);


		$insert="INSERT INTO appmo_purchaseMaterial (name, quantity, measure, coste)
		VALUES ('{$name}', '{$quantity}', '{$measure}', '{$coste}' )";
		$resultado_insert=mysqli_query($conexion,$insert);
		
		
		if($resultado_insert){
			$consulta="SELECT * FROM appmo_purchaseMaterial GROUP BY name";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['product'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["name"]='No Registra';
			$resulta["quantity"]='No Registra';
			$resulta["measure"]='No Registra';
			$resulta["coste"]='No Registra';
			$json['product'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["name"]='WS No esta funcionando';
			$resulta["quantity"]='WS No esta funcionando';
			$resulta["measure"]='WS No esta funcionando';
			$resulta["coste"]='WS No esta funcionando';
			$json['product'][]=$resulta;
			echo json_encode($json);
		}

?>

