<?php
//now initializing the variable to store the data 

$name = $_POST['user_name'];
$pass = $_POST['user_pass'];
$class=  $_POST['user_class'];
$roll = $_POST['user_roll'];
$type = $_POST['user_type'];


/*$name = "chriso";
$pass = "chirso";
$class = "bim";
$roll = "5";
$type = "1";*/
$link = mysql_connect("localhost","root","");
if(!$link){
	die("cannot connect to the server ".mysql_error());
}

if(!mysql_select_db("wifi_attendance")){
	die("database not found".mysql_error());
}

$query = "INSERT INTO `userList` (`name`, `password`, `class`, `roll`, `type`) VALUES ( '".$name."', '".$pass."', '".$class."', '".$roll."', '".$type."');";

$a = mysql_query($query);
if(!$a){
	die("cannot insert the data ".mysql_error());

}
else{
	echo "successfully inserted the data in the tabble";
}


?>


