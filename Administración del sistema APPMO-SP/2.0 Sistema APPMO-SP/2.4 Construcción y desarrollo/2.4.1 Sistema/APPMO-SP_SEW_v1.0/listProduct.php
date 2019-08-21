<?php
include('functions.php'); 
if ($resultset = getSQLResultSet("SELECT typeProduct , nameProduct, quantityProduct FROM appmo_orderproduct  ORDER BY nameProduct")) {
	
    	while ($row = $resultset->fetch_array(MYSQLI_NUM)) {
    	echo json_encode($row);
    }
}
?>