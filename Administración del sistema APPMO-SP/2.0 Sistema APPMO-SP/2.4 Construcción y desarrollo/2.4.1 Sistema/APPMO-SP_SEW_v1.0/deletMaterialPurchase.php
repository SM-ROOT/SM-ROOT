<?php
include 'conexion.php';

$idPurchase=$_POST['idPurchaseMaterial'];

$consulta="DELETE FROM appmo_purchase WHERE idPurchaseMaterial = '".$idPurchaseMaterial."' ";

 mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);

?>