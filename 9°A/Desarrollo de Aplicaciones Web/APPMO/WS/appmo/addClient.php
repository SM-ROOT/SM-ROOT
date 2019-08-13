<?PHP
$hostname_localhost="localhost";
$database_localhost="appmo";
$username_localhost="root";
$password_localhost="";

$json=array();

    if(isset($_GET["name"]) && isset($_GET["phone"]) && isset($_GET["addres"]) 
    && isset($_GET["numberaddres"])  && isset($_GET["cp"]) && isset($_GET["colony"])
    && isset($_GET["city"]) && isset($_GET["state"]) ) {
		
		$name=$_GET['name'];
		$phone=$_GET['phone'];
        $addres=$_GET['addres'];
        $numberaddres=$_GET['numberaddres'];
        $cp=$_GET['cp'];
        $colony=$_GET['colony'];
        $city=$_GET['city'];
        $state=$_GET['state'];
		
		
		
		$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);
		
		$insert="INSERT INTO appmo_client (name, phone, addres,numberaddres, cp, colony, city, state)
		VALUES ('{$name}', '{$phone}', '{$addres}', '{$numberaddres}', 
        '{$cp}', '{$colony}', '{$city}', '{$state}')";
		$resultado_insert=mysqli_query($conexion,$insert);
		
		if($resultado_insert){
			$consulta="SELECT * FROM appmo_client";
			$resultado=mysqli_query($conexion,$consulta);
			
			if($registro=mysqli_fetch_array($resultado)){
				$json['client'][]=$registro;
			}
			mysqli_close($conexion);
			echo json_encode($json);
		}
		else{
			$resulta["name"]='No Registra';
			$resulta["phone"]='No Registra';
            $resulta["addres"]='No Registra';
            $resulta["numberaddres"]='No Registra';
            $resulta["cp"]='No Registra';
            $resulta["colony"]='No Registra';
            $resulta["city"]='No Registra';
            $resulta["state"]='No Registra';
			$json['client'][]=$resulta;
			echo json_encode($json);
		}
		
	}
	else{
			$resulta["name"]='WS No esta funcionando';
			$resulta["phone"]='WS No esta funcionando';
            $resulta["addres"]='WS No esta funcionando';
            $resulta["numberaddres"]='WS No esta funcionando';
            $resulta["cp"]='WS No esta funcionando';
            $resulta["colony"]='WS No esta funcionando';
            $resulta["city"]='WS No esta funcionando';
            $resulta["state"]='WS No esta funcionando';
			$json['client'][]=$resulta;
			echo json_encode($json);
		}

?>

