<?php
include 'conexion.php';

$idOrder=$_POST['idOrder'];

$consulta="DELETE FROM appmo_order WHERE idOrder =  '".$idOrder."' ";

 mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);

?>