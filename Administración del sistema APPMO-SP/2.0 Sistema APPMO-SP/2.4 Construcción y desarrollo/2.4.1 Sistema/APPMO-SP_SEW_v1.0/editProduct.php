<?php
include 'conexion.php';

$idProduct=$_POST['idProduct'];
$nameProduct=$_POST['nameProduct'];
$type=$_POST['type'];
$quantity=$_POST['quantity'];


$consulta="UPDATE appmo_product SET nameProduct = '".$nameProduct."', type= '".$type."', 
quantity= '".$quantity."'  WHERE  idProduct = '".$idProduct."' ";

 mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);
?>