<?php
    require_once 'Conexion.php';
    class Fruta extends Conexion{


        public function listar(){
            $sql = "SELECT nameProduct FROM appmo_orderproduct";
            $sentencia = $this->dblink->prepare($sql);
            $sentencia->execute();            
            return $sentencia->fetchAll(PDO::FETCH_OBJ);
        }  

    	   }
?>