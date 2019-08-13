<?php
    require_once '../datos/Conexion.clase.php';
    class Fruta extends Conexion{


        public function listar(){
            $sql = "SELECT * FROM appmo_user";
            $sentencia = $this->dblink->prepare($sql);
            $sentencia->execute();            
            return $sentencia->fetchAll(PDO::FETCH_OBJ);
        }  

    	   }
?>