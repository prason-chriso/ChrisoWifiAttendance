<?php 

// infititalizing the declared variable 
/*$name = "prason";
$pass = "one";
*/


$name = $_POST['login_name'];
$pass = $_POST['login_pass'];
$query = "Select * from userlist where name ='".$name."' and password ='".$pass."';";

$link = mysql_connect("localhost","root","");
if(!$link){
	die("cannot open the connection to the server".mysql_error());
}

if(!mysql_select_db("wifi_attendance")){
	die("cannot choose the database");
}


$a = mysql_query($query, $link);
if($a){

$count = mysql_num_rows($a);

}
else{
	die($query ."     error in the code ".mysql_error());
}
if ($count>0) {
		$row = mysql_fetch_array($a);
		echo "log in success ".$row['name'].",>>".$row['id'];
}
	else{

	echo "Log in failed .... Register new User first";

		
	}
?>