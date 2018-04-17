 <?php

        // put your code here
        date_default_timezone_set("Asia/Kathmandu"); //setting the timezone of the kathmandu 
        $today = time();
        $tomorrow = strtotime("tomorrow");
        $yesterday = strtotime("yesterday");

        #echo $today . "<br/>" . "<br/>";
       # echo $yesterday . "<br/>" . "<br/>";
       # echo $tomorrow . "<br/>" . "<br/>";


        if ($today < $tomorrow && $today > $yesterday) {
          #  echo "your attendance has been already mmade";
            echo "<br/>";

            $d1 = date("Y-m-d h:i:s", $tomorrow);
            $d2 = date("Y-m-d h:i:s", $today);

           # echo $d1 . "<br/>" . $d2."<br/>";


            //now calculating the difference;
            $timstampRemain =$tomorrow-$today; 
            $hours = floor(($timstampRemain)/(60*60));  //timestamp converted into hour;
            $min = floor(($timstampRemain -($hours*60*60))/60); //remaining timestamp converted into mminutes;
            $seconds = floor(($timstampRemain-($hours*60*60)-($min*60))); //remaining timestamp converted into the seconds
            
            echo $hours. "hrs : ".$min."min: ".$seconds. "secs || is remaining for next attendance";
            
        } else {
            echo "you need to do your attendaance";
        }
        ?>
    </body>
</html>
