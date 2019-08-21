<?php
    require_once 'bread.php';
    $objFruta = new Fruta();
    $resultado = $objFruta->listar();
    $respuesta = array(
        "bread"=>$resultado
    );
    echo json_encode($respuesta);   
?>