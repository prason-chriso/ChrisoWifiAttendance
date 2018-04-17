<?php 
#$userId = $_POST['user_id'];
$userId = 1;
$checkAgain = true ; //this variable is declared so the system will have to return the value for the last timestamp checked in only once and if the value in the checked in colum is found then the value will be set to false this will prevent the resending of the data as response back to the application and reeduce traffic commplexity;


#$userId = 1;

#$query = "Select * from attendance where userId ='".$userId."';";

$query= "SELECT * FROM `userlist` AS u LEFT JOIN attendance AS a ON u.id = a.userId LEFT JOIN lastattendancets as la ON u.id =la.userId WHERE u.id ='".$userId."';";


$link = mysql_connect("localhost","root","");
if(!$link){
	die("cannot open the connection to the server".mysql_error());
}

if(!mysql_select_db("wifi_attendance")){
	die("cannot choose the database");
}


$a = mysql_query($query, $link);
if($a){

while($row = mysql_fetch_assoc($a)) {

		if($checkAgain){ //no matter what this block is executed only once;
		echo mysql_num_rows($a);
			$val = $row['lastCheckedIn'];
		
			if($val==null){
				//it means that the user previios attendance is not recorded i.e new user 
				$checkAgain = false;
				echo "new_user"."\n";; //this is the indication message as response
			}
			else{
				$checkAgain = false;

				//now the resonse should be returned depending upon whether the user has clicked on to the button on current date or not
				 $response  = responseGenerate($val);
				 echo $response."\n";
			}
		}//END OF CHECK AGAIN 

	echo $row['attendanceDate']."\n";
	
} //end of while loop 

} //end of $a i.e successful execution of code
else{
	die("error in the code".mysql_error());
}



//this function will create the response to the user whether he/she can or can't make the attendance
function responseGenerate($val){
 		date_default_timezone_set("Asia/Kathmandu"); //setting the timezone of the kathmandu 
        $today = $val;  //record in the database -> $val
        $tomorrow = strtotime("tomorrow");	
        $yesterday = strtotime("today");

        #echo $today . "<br/>" . "<br/>";
       # echo $yesterday . "<br/>" . "<br/>";
       # echo $tomorrow . "<br/>" . "<br/>";


        if ($today < $tomorrow && $today > $yesterday) {
          #  echo "your attendance has been already mmade";
            echo "<br/>";
/*
            $d1 = date("Y-m-d h:i:s", $tomorrow);
            $d2 = date("Y-m-d h:i:s", $today);
*/
           # echo $d1 . "<br/>" . $d2."<br/>";


            //now calculating the difference;
            $timstampRemain =$tomorrow-$today; 
            $hours = floor(($timstampRemain)/(60*60));  //timestamp converted into hour;
            $min = floor(($timstampRemain -($hours*60*60))/60); //remaining timestamp converted into mminutes;
            $seconds = floor(($timstampRemain-($hours*60*60)-($min*60))); //remaining timestamp converted into the seconds
            
            return $hours. "hrs : ".$min."min: ".$seconds. "secs || is remaining for next attendance".$today." ".$tomorrow." ".$yesterday;
            
        } else {
            return "need_attendance";
        }

} //end of the responsegenerator block 


mysql_close($link);

?>