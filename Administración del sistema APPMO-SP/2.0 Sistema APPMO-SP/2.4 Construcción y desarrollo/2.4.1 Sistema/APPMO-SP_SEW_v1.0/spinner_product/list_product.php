<?php
    require_once 'product.php';
    $objFruta = new Fruta();
    $resultado = $objFruta->listar();
    $respuesta = array(
        "product"=>$resultado
    );
    echo json_encode($respuesta);   
?>