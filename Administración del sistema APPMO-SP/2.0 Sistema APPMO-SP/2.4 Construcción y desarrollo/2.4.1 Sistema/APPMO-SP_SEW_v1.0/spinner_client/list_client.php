<?php
    require_once 'Client.php';
    $objFruta = new Fruta();
    $resultado = $objFruta->listar();
    $respuesta = array(
        "client"=>$resultado
    );
    echo json_encode($respuesta);   
?>