<?php
    require_once 'Conexion.php';
    class Fruta extends Conexion{


        public function listar(){
            $sql = "SELECT appmo_material.name FROM appmo_material ORDER BY appmo_material.name";
            $sentencia = $this->dblink->prepare($sql);
            $sentencia->execute();            
            return $sentencia->fetchAll(PDO::FETCH_OBJ);
        }  

    	   }
?>