<?PHP
$hostname_localhost="localhost";
$database_localhost="appmo";
$username_localhost="root";
$password_localhost="";

$json=array();

	if(isset($_GET["nameProduct"]) && isset($_GET["typeProduct"]) && isset($_GET["quantityProduct"])   ) {
		
		$nameProduct=$_GET['nameProduct'];
		$typeProduct=$_GET['typeProduct'];
		$quantityProduct=$_GET['quantityProduct'];
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);


		$insert="INSERT INTO appmo_orderproduct (nameProduct, typeProduct, quantityProduct)
		VALUES ('{$nameProduct}', '{$typeProduct}' , '{$quantityProduct}')";
		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT * FROM appmo_orderproduct GROUP BY nameProduct";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['product'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["nameProduct"]='No Registra';
			$resulta["typeProduct"]='No Registra';
			$resulta["quantityProduct"]='No Registra';
			$json['product'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["nameProduct"]='WS No esta funcionando';
			$resulta["typeProduct"]='WS No esta funcionando';
			$resulta["quantityProduct"]='WS No esta funcionando';
			$json['product'][]=$resulta;
			echo json_encode($json);
		}

?>

