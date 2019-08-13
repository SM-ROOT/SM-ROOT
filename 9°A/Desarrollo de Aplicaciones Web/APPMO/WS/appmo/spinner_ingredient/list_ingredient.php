<?php
    require_once 'ingredient.php';
    $objFruta = new Fruta();
    $resultado = $objFruta->listar();
    $respuesta = array(
        "ingredient"=>$resultado
    );
    echo json_encode($respuesta);   
?>