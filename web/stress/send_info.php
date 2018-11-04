<?php 
ini_set('display_errors', 1); 
ini_set('error_reporting', E_ALL);

include('config.php');

$mysqli = new mysqli(DB_HOST, DB_USER, DB_PASSWORD, DB_NAME);


foreach ($_POST['word'] as $key=>$value) {
	if ($value>0){
		$query = "UPDATE words SET stress=".$value." WHERE id=".$key;

		if ($mysqli->query($query) === TRUE) {
			echo "Record updated successfully";
		} else {
			echo "Error updating record: " . $mysqli->error;
		}
		//$item1 = $_POST['word']['241'];
	}


	echo $query;
}
header('Location: ' . $_SERVER['HTTP_REFERER']);
 ?>