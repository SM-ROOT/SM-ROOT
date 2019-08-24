<?php
include 'conexion.php';

$idUser=$_POST['idUser'];

$consulta="DELETE FROM appmo_user WHERE idUser= '".$idUser."' ";

 mysqli_query($conexion,$consulta) or die (mysqli_error());
 mysqli_close($conexion);

?>