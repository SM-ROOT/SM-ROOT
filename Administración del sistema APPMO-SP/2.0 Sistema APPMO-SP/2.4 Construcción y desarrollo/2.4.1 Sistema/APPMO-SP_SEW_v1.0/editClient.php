<?php
include 'conexion.php';

$idClient=$_POST['idClient'];
$name=$_POST['name'];
$phone=$_POST['phone'];
$addres=$_POST['addres'];
$numberaddres=$_POST['numberaddres'];
$cp=$_POST['cp'];
$colony=$_POST['colony'];
$city=$_POST['city'];
$state=$_POST['state'];


$consulta="UPDATE appmo_client SET name = '".$name."', phone= '".$phone."', 
addres = '".$addres."', numberaddres= '".$numberaddres."', cp = '".$cp."',
colony= '".$colony."', city = '".$city."' , state = '".$state."' WHERE  idClient = '".$idClient."' ";

 mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);
?>