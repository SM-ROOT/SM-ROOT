<?php
include 'conexion.php';

$idClient=$_POST['idClient'];

$consulta="DELETE FROM appmo_client WHERE idClient= '".$idClient."' ";

 mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);

?>