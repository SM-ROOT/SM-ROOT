<?php
    require_once '../negocio/Fruta.clase.php';
    $objFruta = new Fruta();
    $resultado = $objFruta->listar();
    $respuesta = array(
        "user"=>$resultado
    );
    echo json_encode($respuesta);   
?>