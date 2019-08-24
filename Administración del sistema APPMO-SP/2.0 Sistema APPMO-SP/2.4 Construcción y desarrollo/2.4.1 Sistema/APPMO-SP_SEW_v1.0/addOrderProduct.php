<?PHP
$hostname_localhost="localhost";
$database_localhost="appmo";
$username_localhost="root";
$password_localhost="";

$json=array();

	if(isset($_GET["nameProduct"]) && isset($_GET["typeProduct"])
	&& isset($_GET["quantityProduct"])) {

		
		$typeProduct=$_GET['typeProduct'];
		$nameProduct=$_GET['nameProduct'];
		$quantityProduct=$_GET['quantityProduct'];
		
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO appmo_orderproduct (typeProduct, nameProduct, quantityProduct)
		VALUES ('{$typeProduct}', '{$nameProduct}','{$quantityProduct}')";
		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT * FROM appmo_orderproduct";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['orderproduct'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["typeProduct"]='No Registra';
			$resulta["nameProduct"]='No Registra';
			$resulta["quantityProduct"]='No Registra';
			
			$json['orderproduct'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
		$resulta["typeProduct"]='WB no funciona';
		$resulta["nameProduct"]='WB no funciona';
		$resulta["quantityProduct"]='WB no funciona';

			$json['orderproduct'][]=$resulta;
			echo json_encode($json);
		}

?>

