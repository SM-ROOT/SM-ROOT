<?PHP
$hostname_localhost ="localhost";
$database_localhost ="appmo";
$username_localhost ="root";
$password_localhost ="";

$json=array();
	
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="SELECT appmo_order_orderproduct.idOrder, appmo_orderproduct.nameProduct AS product, appmo_orderproduct.typeProduct AS tipo, appmo_orderproduct.quantityProduct AS quantity 
FROM appmo_orderproduct, appmo_order_orderproduct
WHERE appmo_orderproduct.idOrderProduct = appmo_order_orderproduct.idOrderProduct AND
appmo_order_orderproduct.idOrder";
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			$json['order'][]=$registro;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}
		mysqli_close($conexion);
		echo json_encode($json);
?>