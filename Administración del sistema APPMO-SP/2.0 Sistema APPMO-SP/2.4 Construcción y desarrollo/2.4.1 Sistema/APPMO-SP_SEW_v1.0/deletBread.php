<?php
include 'conexion.php';

$idProduct=$_POST['idProduct'];

$consulta="DELETE FROM appmo_product WHERE idProduct= '".$idProduct."' ";

 mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);

?>