<?php
include 'conexion.php';

$id=$_POST['id'];
$rfc=$_POST['rfc'];
$number=$_POST['number'];
$mail=$_POST['mail'];
$addres=$_POST['addres'];
$numberaddres=$_POST['numberaddres'];
$cp=$_POST['cp'];
$colony=$_POST['colony'];
$city=$_POST['city'];
$state=$_POST['state'];


$consulta="UPDATE appmo_supply SET rfc = '".$rfc."', number= '".$number."', 
mail= '".$mail."' , addres = '".$addres."', numberaddres= '".$numberaddres."', cp = '".$cp."',
colony= '".$colony."', city = '".$city."' , state = '".$state."' WHERE  id = '".$id."' ";

 mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);
?>