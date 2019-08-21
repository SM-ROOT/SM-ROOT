<?PHP
$hostname_localhost="localhost";
$database_localhost="appmo";
$username_localhost="root";
$password_localhost="";

$json=array();

	if(isset($_GET["name"]) && isset($_GET["subnamepather"]) && isset($_GET["subnamemother"])
		&& isset($_GET["phone"]) && isset($_GET["type"]) && isset($_GET["password"]) && isset($_GET["addres"])
		&& isset($_GET["numberaddres"]) && isset($_GET["cp"]) && isset($_GET["colony"])
		&& isset($_GET["city"]) && isset($_GET["state"])  ) {
				
		$name=$_GET['name'];
		$subnamepather=$_GET['subnamepather'];
		$subnamemother=$_GET['subnamemother'];
		$phone=$_GET['phone'];
		$type=$_GET['type'];
		$password=$_GET['password'];
		$addres=$_GET['addres'];
		$numberaddres=$_GET['numberaddres'];
		$cp=$_GET['cp'];
		$colony=$_GET['colony'];
		$city=$_GET['city'];
		$state=$_GET['state'];
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO appmo_user (`name`, subnamepather, subnamemother, phone, `type`, `password`, addres, numberaddres, 
		 cp, colony, city, state)
		VALUES ('{$name}', '{$subnamepather}', '{$subnamemother}','{$phone}', '{$type}', '{$password}',
		'{$addres}', '{$numberaddres}',  '{$cp}', '{$colony}', '{$city}', '{$state}')";

		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT * FROM appmo_user";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['usuario'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["name"]='No Registra';
			$resulta["subnamepather"]='No Registra';
			$resulta["subnamemother"]='No Registra';
			$resulta["mail"]='No Registra';
			$resulta["phone"]='No Registra';
			$resulta["type"]='No Registra';
			$resulta["password"]='No Registra';
			$resulta["addres"]='No Registra';
			$resulta["numberaddres"]='No Registra';
			$resulta["colony"]='No Registra';
			$resulta["cp"]='No Registra';
			$resulta["city"]='No Registra';
			$resulta["state"]='No Registra';
			$json['usuario'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["name"]='WS No esta funcionando';
			$resulta["subnamepather"]='WS No esta funcionando';
			$resulta["subnamemother"]='WS No esta funcionando';
			$resulta["mail"]='WS No esta funcionando';
			$resulta["phone"]='WS No esta funcionando';
			$resulta["type"]='WS No esta funcionando';
			$resulta["password"]='WS No esta funcionando';
			$resulta["addres"]='WS No esta funcionando';
			$resulta["numberaddres"]='WS No esta funcionando';
			$resulta["colony"]='WS No esta funcionando';
			$resulta["cp"]='WS No esta funcionando';
			$resulta["city"]='WS No esta funcionando';
			$resulta["state"]='WS No esta funcionando';
			$json['usuario'][]=$resulta;
			echo json_encode($json);
		}

?>

