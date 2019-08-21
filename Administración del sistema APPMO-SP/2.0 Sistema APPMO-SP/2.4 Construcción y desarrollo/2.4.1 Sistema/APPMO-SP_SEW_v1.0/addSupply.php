<?PHP
$hostname_localhost="localhost";
$database_localhost="appmo";
$username_localhost="root";
$password_localhost="";

$json=array();

	if(isset($_GET["rfc"]) && isset($_GET["number"]) && isset($_GET["mail"]) 
	 && isset($_GET["addres"])  && isset($_GET["numberaddres"]) && isset($_GET["cp"])
	 && isset($_GET["colony"]) && isset($_GET["city"]) 
	 && isset($_GET["state"]) ) {
		
		
		$rfc=$_GET['rfc'];
		$number=$_GET['number'];
		$mail=$_GET['mail'];
		$addres=$_GET['addres'];
		$numberaddres=$_GET['numberaddres'];
		$cp=$_GET['cp'];
		$colony=$_GET['colony'];
		$city=$_GET['city'];
		$state=$_GET['state'];
		
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO appmo_supply (rfc, number, mail, addres, numberaddres, cp, colony,  city, state)
		VALUES ('{$rfc}', '{$number}','{$mail}','{$addres}','{$numberaddres}', '{$cp}', '{$colony}',
		'{$city}','{$state}')";
		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT * FROM appmo_supply";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['supply'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["rfc"]='No Registra';
			$resulta["number"]='No Registra';
			$resulta["mail"]='No Registra';
			$resulta["addres"]='No Registra';
			$resulta["numberaddres"]='No Registra';
			$resulta["cp"]='No Registra';
			$resulta["colony"]='No Registra';
			$resulta["city"]='No Registra';
			$resulta["state"]='No Registra';
			
			$json['supply'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
		$resulta["rfc"]='WB no funciona';
		$resulta["number"]='WB no funciona';
		$resulta["mail"]='WB no funciona';
		$resulta["addres"]='No Registra';
		$resulta["numberaddres"]='No Registra';
		$resulta["cp"]='No Registra';
		$resulta["colony"]='No Registra';
		$resulta["city"]='No Registra';
		$resulta["state"]='No Registra';

			$json['supply'][]=$resulta;
			echo json_encode($json);
		}

?>

