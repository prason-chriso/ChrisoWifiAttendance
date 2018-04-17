<?php

$userId = $_POST['user_id'];
#$userId = 1;
$date = date("Y-m-d H:i:s");


$link = mysql_connect("localhost","root","");
if(!$link){
	die("cannot connect to the server ".mysql_error());
}

if(!mysql_select_db("wifi_attendance")){
	die("database not found".mysql_error());
}


$query = "INSERT INTO `attendance` (`userId`, `attendanceDate`) VALUES ( '".$userId."', '".$date."');";

$a = mysql_query($query);
if(!$a){
	echo "cannot insert the data ".mysql_error();
	die();

}

$timestamp = time();

if( (mysql_num_rows(mysql_query("Select * from lastattendancets;"))) ==0){
$query1 = "INSERT INTO `lastattendancets` (`userId`, `lastCheckedIn`) VALUES ( '".$userId."', '".$timestamp."');";
}
else{
	$query1 = "Update lastattendancets set lastCheckedIn='".$timestamp."' where userId='".$userId."';";
}
$b = mysql_query($query1);
if(!$b){
	echo "cannot insert in the lastattendancets ".mysql_error();
	die();
}

//else "" is returned bacck if the data has been succcessfully inserted as new reccord


?>