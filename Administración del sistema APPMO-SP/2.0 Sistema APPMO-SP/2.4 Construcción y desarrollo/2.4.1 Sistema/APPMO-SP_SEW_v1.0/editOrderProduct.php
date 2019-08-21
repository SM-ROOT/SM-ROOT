<?php
include 'conexion.php';

$idPurchaseMaterial=$_POST['idPurchaseMaterial'];
$name=$_POST['name'];
$quantity=$_POST['quantity'];
$measure=$_POST['measure'];
$coste=$_POST['coste'];

$consulta="UPDATE appmo_purchasematerial SET name = '".$name."' ,  quantity= '".$quantity."' , measure = '".$measure."', 
coste = '".$coste."' WHERE  idPurchaseMaterial = '".$idPurchaseMaterial."' ";

 mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);
?>	