<?php
    require_once 'Conexion.php';
    class Fruta extends Conexion{


        public function listar(){
            $sql = "SELECT appmo_client.name FROM appmo_client";
            $sentencia = $this->dblink->prepare($sql);
            $sentencia->execute();            
            return $sentencia->fetchAll(PDO::FETCH_OBJ);
        }  

    	   }
?>