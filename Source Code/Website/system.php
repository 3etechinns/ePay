<?php

session_start();

$direct = $_POST["Navi"];
if($direct == "lossReport"){
		$cardNum = $_POST["CardNum"];
		$customerName = $_POST["CustomerName"];
		$idCardNum = $_POST["IdCardNum"];
		$phoneNum = $_POST["PhoneNum"];
		$userName = $_SESSION["userName"];

		$db = "Surpassing";
		$response = "empty";
		$conn = oci_connect("system", "citicup", $db);

		if (!$conn) {
	    	$e = oci_error();
	    	trigger_error(htmlentities($e['message'], ENT_QUOTES), E_USER_ERROR);
	    	$response = "failed to connect the database";
		}

		$query="select CARDNUM, CUSTOMERNAME, IDENTIFICATIONCARDNUM, PHONENUM from PERSON_INFO where USERNAME = '".$userName."'";

		$stmt = oci_parse($conn,$query );
		
		oci_define_by_name($stmt, 'CARDNUM', $cardNumDB);
		oci_define_by_name($stmt, 'CUSTOMERNAME', $customerNameDB);
		oci_define_by_name($stmt, 'IDENTIFICATIONCARDNUM', $idCardNumDB);
		oci_define_by_name($stmt, 'PHONENUM', $phoneNumDB);

		if(!oci_execute($stmt))
	  		$response = "excuted failed";

		oci_fetch($stmt);

		if($cardNum==$cardNumDB&&$customerName==$customerNameDB&&$idCardNum==$idCardNumDB&&$phoneNum==$phoneNumDB)
			$response = "true";
		else
			$response = "false";

		oci_free_statement($stmt);
		oci_close($conn);
	
}
else if($direct == "login"){
	$userName = $_POST["UserName"];
	$password = $_POST["Password"];

	$db = "Surpassing";

	$response = "empty";

	$conn = oci_connect("system", "citicup", $db);
	if (!$conn) {
    	$e = oci_error();
    	trigger_error(htmlentities($e['message'], ENT_QUOTES), E_USER_ERROR);
    	$response = "failed to connect the database";
	}

	$query="select PASSWORD from PERSON_INFO where USERNAME = '".$userName."'";

	$stmt = oci_parse($conn,$query );
	
	oci_define_by_name($stmt, 'PASSWORD', $passwordDB);

	if(!oci_execute($stmt))
  		$response = "excuted failed";

	oci_fetch($stmt);

	$password = md5($password);

	if($password == $passwordDB){
		$response = "true";
		$_SESSION["userName"] = $userName;
	}
	else
		$response = "false";

	oci_free_statement($stmt);
	oci_close($conn);
}else if($direct == "checkLogined"){
	if(isset($_SESSION["userName"]))
		$response = $_SESSION["userName"];
	else
		$response = "0";
}else if($direct == "logout"){
	unset($_SESSION["userName"]);
	if(isset($_SESSION["userName"]))
		$response = "0";
	else
		$response = "1";
}
else if($direct == "getGraphData"){
		$userName = $_SESSION["userName"];
		$localMonth =  (int)date('m');
		$localYear = (int)date('Y');
		$month0 = strtotime($localYear."-".$localMonth."-01 00:00:00");

		$db = "Surpassing";
		$response = "empty";
		$conn = oci_connect("system", "citicup", $db);

		if (!$conn) {
	    	$e = oci_error();
	    	trigger_error(htmlentities($e['message'], ENT_QUOTES), E_USER_ERROR);
	    	$response = "failed to connect the database";
		}

		$query="select SUM(AMOUNT) from ELENOTE_INFO where PAYERNAME = '".$userName."'";
		$stmt = oci_parse($conn, $query);		
		oci_define_by_name($stmt, 'SUM(AMOUNT)', $data);
		if(!oci_execute($stmt))
	  		$response = "excuted failed";
		oci_fetch($stmt);

		$response = $data;


		$query="select SUM(AMOUNT) from BANK_TRADE where BUYERNAME = '".$userName."'";
		$stmt = oci_parse($conn, $query);		
		oci_define_by_name($stmt, 'SUM(AMOUNT)', $data);
		if(!oci_execute($stmt))
	  		$response = "excuted failed";
		oci_fetch($stmt);

		$response = $response.",".$data;

		$query="select SUM(AMOUNT) from ELENOTE_INFO where RECERNAME = '".$userName."'";
		$stmt = oci_parse($conn, $query);		
		oci_define_by_name($stmt, 'SUM(AMOUNT)', $data);
		if(!oci_execute($stmt))
	  		$response = "excuted failed";
		oci_fetch($stmt);

		$response = $response.",".$data.";";


		$query="select AMOUNT,SALERNAME,TRADETIME from BANK_TRADE where BUYERNAME = '".$userName."' ORDER BY TRADETIME DESC";
		$stmt = oci_parse($conn, $query);		
		oci_define_by_name($stmt, 'AMOUNT', $tradeAmountDB);
		oci_define_by_name($stmt, 'SALERNAME', $salerNameDB);
		oci_define_by_name($stmt, 'TRADETIME', $tradeTimeDB);
		if(!oci_execute($stmt))
	  		$response = "excuted failed";
	  	while(oci_fetch($stmt))
				$response = $response.$salerNameDB.",".$tradeTimeDB.",".$tradeAmountDB.".";

		$response = $response.";";

		$query="select AMOUNT,RECERNAME,TRANSFERTIME from ELENOTE_INFO where PAYERNAME = '".$userName."' ORDER BY TRANSFERTIME DESC";
		$stmt = oci_parse($conn, $query);		
		oci_define_by_name($stmt, 'AMOUNT', $transferAmountDB);
		oci_define_by_name($stmt, 'RECERNAME', $receiverNameDB);
		oci_define_by_name($stmt, 'TRANSFERTIME', $transferTimeDB);
		if(!oci_execute($stmt))
	  		$response = "excuted failed";
		while(oci_fetch($stmt))
				$response = $response.$receiverNameDB.",".$transferTimeDB.",".$transferAmountDB.".";

		$response = $response.";";

		$query="select AMOUNT,RECERNAME,TRANSFERTIME from ELENOTE_INFO where RECERNAME = '".$userName."' ORDER BY TRANSFERTIME DESC";
		$stmt = oci_parse($conn, $query);		
		oci_define_by_name($stmt, 'AMOUNT', $incomeAmountDB);
		oci_define_by_name($stmt, 'RECERNAME', $payerNameDB);
		oci_define_by_name($stmt, 'TRANSFERTIME', $incomeTimeDB);
		if(!oci_execute($stmt))
	  		$response = "excuted failed";
		while(oci_fetch($stmt))
				$response = $response.$payerNameDB.",".$incomeTimeDB.",".$incomeAmountDB.".";

		$response = $response.";";


		$month1 = strtotime("-1 month", $month0);
		$month2 = strtotime("-2 month", $month0);
		$month3 = strtotime("-3 month", $month0);
		$month4 = strtotime("-4 month", $month0);
		$monthOutput0 =  date('Y-m', $month0);
		$monthOutput1 =  date('Y-m', $month1);
		$monthOutput2 =  date('Y-m', $month2);
		$monthOutput3 =  date('Y-m', $month3);
		$monthOutput4 =  date('Y-m', $month4);
		$tradeAmount0 = 0;
		$tradeAmount1 = 0;
		$tradeAmount2 = 0;
		$tradeAmount3 = 0;
		$tradeAmount4 = 0;
		$transferAmount0 = 0;
		$transferAmount1 = 0;
		$transferAmount2 = 0;
		$transferAmount3 = 0;
		$transferAmount4 = 0;
		$incomeAmount0 = 0;
		$incomeAmount1 = 0;
		$incomeAmount2 = 0;
		$incomeAmount3 = 0;
		$incomeAmount4 = 0;

		$query="select AMOUNT, TRADETIME from BANK_TRADE where BUYERNAME = '".$userName."'";
		$stmt = oci_parse($conn, $query);		
		oci_define_by_name($stmt, 'AMOUNT', $tradeAmountDB);
		oci_define_by_name($stmt, 'TRADETIME', $tradeTimeDB);
		if(!oci_execute($stmt))
	  		$response = "excuted failed";
	  	while(oci_fetch($stmt)){
	  		if($tradeTimeDB>=$month0)
	  			$tradeAmount0 += $tradeAmountDB;
	  		else if($tradeTimeDB>=$month1)
	  			$tradeAmount1 += $tradeAmountDB;
	  		else if($tradeTimeDB>=$month2)
	  			$tradeAmount2 += $tradeAmountDB;
	  		else if($tradeTimeDB>=$month3)
	  			$tradeAmount3 += $tradeAmountDB;
	  		else if($tradeTimeDB>=$month4)
	  			$tradeAmount4 += $tradeAmountDB;
	  	}

	  	$query="select AMOUNT, TRANSFERTIME from ELENOTE_INFO where PAYERNAME = '".$userName."'";
		$stmt = oci_parse($conn, $query);		
		oci_define_by_name($stmt, 'AMOUNT', $transferAmountDB);
		oci_define_by_name($stmt, 'TRANSFERTIME', $transferTimeDB);
		if(!oci_execute($stmt))
	  		$response = "excuted failed";
	  	while(oci_fetch($stmt)){
	  		if($transferTimeDB>=$month0)
	  			$transferAmount0 += $transferAmountDB;
	  		else if($transferTimeDB>=$month1)
	  			$transferAmount1 += $transferAmountDB;
	  		else if($transferTimeDB>=$month2)
	  			$transferAmount2 += $transferAmountDB;
	  		else if($transferTimeDB>=$month3)
	  			$transferAmount3 += $transferAmountDB;
	  		else if($transferTimeDB>=$month4)
	  			$transferAmount4 += $transferAmountDB;
	  	}

	  	$query="select AMOUNT, TRANSFERTIME from ELENOTE_INFO where RECERNAME = '".$userName."'";
		$stmt = oci_parse($conn, $query);		
		oci_define_by_name($stmt, 'AMOUNT', $incomeAmountDB);
		oci_define_by_name($stmt, 'TRANSFERTIME', $incomeTimeDB);
		if(!oci_execute($stmt))
	  		$response = "excuted failed";
	  	while(oci_fetch($stmt)){
	  		if($incomeTimeDB>=$month0)
	  			$incomeAmount0 += $incomeAmountDB;
	  		else if($incomeTimeDB>=$month1)
	  			$incomeAmount1 += $incomeAmountDB;
	  		else if($incomeTimeDB>=$month2)
	  			$incomeAmount2 += $incomeAmountDB;
	  		else if($incomeTimeDB>=$month3)
	  			$incomeAmount3 += $incomeAmountDB;
	  		else if($incomeTimeDB>=$month4)
	  			$incomeAmount4 += $incomeAmountDB;
	  	}
		$response = $response.$monthOutput4.",".$tradeAmount4.",".$transferAmount4.",".$incomeAmount4.".";
		$response = $response.$monthOutput3.",".$tradeAmount3.",".$transferAmount3.",".$incomeAmount3.".";
		$response = $response.$monthOutput2.",".$tradeAmount2.",".$transferAmount2.",".$incomeAmount2.".";
		$response = $response.$monthOutput1.",".$tradeAmount1.",".$transferAmount1.",".$incomeAmount1.".";
		$response = $response.$monthOutput0.",".$tradeAmount0.",".$transferAmount0.",".$incomeAmount0.".";

		oci_free_statement($stmt);
		oci_close($conn);

}
else if($direct == "getFinancingData"){
		$userName = $_SESSION["userName"];
		$localMonth =  (int)date('m');
		$localYear = (int)date('Y');
		$month0 = strtotime($localYear."-".$localMonth."-01 00:00:00");

		$db = "Surpassing";
		$response = "empty";
		$conn = oci_connect("system", "citicup", $db);

		if (!$conn) {
	    	$e = oci_error();
	    	trigger_error(htmlentities($e['message'], ENT_QUOTES), E_USER_ERROR);
	    	$response = "failed to connect the database";
		}

		$query="select SUM(AMOUNT) from PERSON_DEPOSIT_INFO where USERNAME = '".$userName."'";
		$stmt = oci_parse($conn, $query);		
		oci_define_by_name($stmt, 'SUM(AMOUNT)', $data);
		if(!oci_execute($stmt))
	  		$response = "excuted failed";
		oci_fetch($stmt);

		$response = $data;

		$query="select BALANCE from PERSON_INTEREST_INFO where USERNAME = '".$userName."'";
		$stmt = oci_parse($conn, $query);		
		oci_define_by_name($stmt, 'BALANCE', $data);
		if(!oci_execute($stmt))
	  		$response = "excuted failed";
		oci_fetch($stmt);

		$response =$response.",".$data.";";

		$query="select AMOUNT, INTESERTRATE, TIME, DEPOSITWAY from PERSON_DEPOSIT_INFO where USERNAME = '".$userName."' ORDER BY TIME DESC";
		$stmt = oci_parse($conn, $query);		
		oci_define_by_name($stmt, 'AMOUNT', $amountDB);
		oci_define_by_name($stmt, 'INTESERTRATE', $interestRateDB);
		oci_define_by_name($stmt, 'TIME', $timeDB);
		oci_define_by_name($stmt, 'DEPOSITWAY', $depositWayDB);
		if(!oci_execute($stmt))
	  		$response = "excuted failed";
		while(oci_fetch($stmt))
				$response = $response.$depositWayDB.",".$timeDB.",".$interestRateDB.",".$amountDB."?";

		$response = $response.";";


		oci_free_statement($stmt);
		oci_close($conn);

}



//输出响应

echo $response;


?>