<?PHP
$hostname_localhost="localhost";
$database_localhost="appm";
$username_localhost="root";
$password_localhost="";

$conexion=mysqli_connect($hostname_localhost,$username_localhost,$password_localhost,$database_localhost);

		$rfc=$_POST['rfc'];
		$number=$_POST['number'];
		$mail=$_POST['mail'];
		$addres=$_POST['addres'];
		$numberaddres=$_POST['numberaddres'];
		$cp=$_POST['cp'];
		$colony=$_POST['colony'];
		$city=$_POST['city'];
		$state=$_POST['state'];

	$sql="UPDATE appmo_supply SET rfc= ? , number= ?, mail=?, addres=?, numberaddres=?, 
	 cp=?, colony=? city=?, state=? WHERE rfc=?";
	$stm=$conexion->prepare($sql);
	$stm->bind_param('ssssi',$rfc, $number, $mail, $addres, $numberaddres, $cp, $colony,
	 $city, $state);
		
	if($stm->execute()){
		echo "actualiza";
	}else{
		echo "noActualiza";
	}
	mysqli_close($conexion);
?>

