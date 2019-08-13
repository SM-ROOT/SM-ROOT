<?PHP
$hostname_localhost ="localhost";
$database_localhost ="appmo";
$username_localhost ="root";
$password_localhost ="";

$json=array();
				
		$conexion = mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$consulta="SELECT appmo_purchase_purchaseMaterial.idPurchase, appmo_purchase.purchaseDate, appmo_purchasematerial.name, appmo_purchasematerial.quantity, appmo_purchasematerial.measure, appmo_purchasematerial.coste 
FROM appmo_purchase, appmo_purchasematerial, appmo_purchase_purchasematerial WHERE appmo_purchase.idPurchase = appmo_purchase_purchasematerial.idPurchase AND appmo_purchasematerial.idPurchaseMaterial =
appmo_purchase_purchasematerial.idPurchaseMaterial AND appmo_purchase_purchasematerial.idPurchase = appmo_purchase_purchasematerial.idPurchase";
		$resultado=mysqli_query($conexion,$consulta);
		
		while($registro=mysqli_fetch_array($resultado)){
			$json['purchaseMaterial'][]=$registro;
			//echo $registro['id'].' - '.$registro['nombre'].'<br/>';
		}
		mysqli_close($conexion);
		echo json_encode($json);
?>