<?php
    require_once 'Conexion.php';
    class Fruta extends Conexion{


        public function listar(){
            $sql = "SELECT nameProduct FROM appmo_product ORDER BY nameProduct";
            $sentencia = $this->dblink->prepare($sql);
            $sentencia->execute();            
            return $sentencia->fetchAll(PDO::FETCH_OBJ);
        }  

    	   }
?>