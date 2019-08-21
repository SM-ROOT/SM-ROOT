<?php
include 'conexion.php';

$idRoute=$_POST['idRoute'];

$consulta="DELETE FROM appmo_route WHERE idRoute= '".$idRoute."' ";

 mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);

?>