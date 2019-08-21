<?php
include 'conexion.php';

$idUser=$_POST['idUser'];
$name=$_POST['name'];
$subnamepather=$_POST['subnamepather'];
$subnamemother=$_POST['subnamemother'];
$phone=$_POST['phone'];
$type=$_POST['type'];
$addres=$_POST['addres'];
$numberaddres=$_POST['numberaddres'];
$cp=$_POST['cp'];
$colony=$_POST['colony'];
$city=$_POST['city'];
$state=$_POST['state'];

$consulta="UPDATE appmo_user SET name = '".$name."', subnamepather= '".$subnamepather."', 
subnamemother= '".$subnamemother."' , phone= '".$phone."', type= '".$type."', addres = '".$addres."',
numberaddres= '".$numberaddres."', cp = '".$cp."', colony= '".$colony."', city = '".$city."' , state = '".$state."' 
WHERE  idUser = '".$idUser."' ";

 mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);
?>	