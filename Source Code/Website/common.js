var userNamePattern = new RegExp('^[a-zA-Z][0-9a-zA-Z_]{4,15}$');
var passwordPattern = new RegExp('^[0-9a-zA-Z_]{6,16}$');
var cardNumPattern = new RegExp('^[0-9]{19}$');
var customerNamePattern = new RegExp('^[\u4E00-\u9FA5]{2,10}$');
var idCardNumPattern = new RegExp('^[0-9]{17}[0-9A-Z]$');
var phoneNumPattern = new RegExp('^1[0-9]{10}$');

var isOK = {
  "userName": false,
  "password": false,
  "cardNum": false,
  "customerName": false,
  "idCardNum": false,
  "phoneNum": false
};

var patterns = new Array(userNamePattern, passwordPattern,cardNumPattern,customerNamePattern,idCardNumPattern,phoneNumPattern);
var num = {
  "userName": "0",
  "password": "1",
  "cardNum": "2",
  "customerName": "3",
  "idCardNum": "4",
  "phoneNum": "5"
};



function checkPattern(sign) {
  var currentValue = document.getElementById(sign).value;

  if (currentValue == "") {
    document.getElementById(sign + "Group").className = "control-group error";
    isOK[sign] = false;
    document.getElementById(sign + "Span").innerHTML = "不能为空";
  } else if (!patterns[num[sign]].test(currentValue)) {
    document.getElementById(sign + "Group").className = "control-group error";
    isOK[sign] = false;
    document.getElementById(sign + "Span").innerHTML = "格式不正确";
  } else {
    document.getElementById(sign + "Group").className = "control-group success";
    isOK[sign] = true;
    document.getElementById(sign + "Span").innerHTML = "格式正确";
  }
}


function systemLoad() {
  var xmlhttp;
  if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp = new XMLHttpRequest();
  } else { // code for IE6, IE5
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
      if (xmlhttp.responseText == "0") {
        window.location.href = 'login.html';
      } 
    }
  }
  xmlhttp.open("POST", "system.php", true);
  xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xmlhttp.send("Navi=checkLogined");
}


function callLossReportWindow() {
  window.location.href = 'lossReport.html';
}

function callFinancingWindow(){
  window.location.href = 'financing.html';  
}

function callChartWindow() {
  window.location.href = 'index.html';
}

function logout() {
  var xmlhttp;
  if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp = new XMLHttpRequest();
  } else { // code for IE6, IE5
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
      if (xmlhttp.responseText == "0") {
        alert("退出失败，请重新退出");
      } else {
        window.location.href = 'login.html';
      }
    }
  }
  xmlhttp.open("POST", "system.php", true);
  xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xmlhttp.send("Navi=logout");
}

function cancel() {
  var xmlhttp;
  if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp = new XMLHttpRequest();
  } else { // code for IE6, IE5
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
      if (xmlhttp.responseText == "0") {
        alert("请先进行登录！");
        window.location.href = 'login.html';
      } else {
        window.location.href = 'index.html';
      }
    }
  }
  xmlhttp.open("POST", "system.php", true);
  xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xmlhttp.send("Navi=checkLogined");

}

function lossReport() {
  var xmlhttp;
  var cdnum = document.getElementById("cardNum").value;
  var ctnam = document.getElementById("customerName").value;
  var idnum = document.getElementById("idCardNum").value;
  var phnum = document.getElementById("phoneNum").value;

  if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp = new XMLHttpRequest();
  } else {
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
  }
  xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
      alert(xmlhttp.responseText);
      // document.getElementById("hint").innerHTML = xmlhttp.responseText;
    }
  }

  xmlhttp.open("POST", "system.php", true);
  xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xmlhttp.send("Navi=lossReport&CardNum=" + cdnum + "&CustomerName=" + ctnam + "&IdCardNum=" + idnum + "&PhoneNum=" + phnum);
}

function login() {
  if (isOK["userName"] && isOK["password"]) {
    document.getElementById("loginSpan").innerHTML="";
    var xmlhttp;
    //  var usrnm = document.loginForm.userName.value;
    // var paswd = document.loginForm.password.value;
    var usrnm = document.getElementById("userName").value;
    var paswd = document.getElementById("password").value;
    if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
      xmlhttp = new XMLHttpRequest();
    } else { // code for IE6, IE5
      xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange = function() {
      if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        if (xmlhttp.responseText == "true")
          window.location.href = 'index.html';
        else if (xmlhttp.responseText == "false")
          document.getElementById("loginSpan").innerHTML = "账号/密码错误，请重新登录";
        else
          document.getElementById("loginSpan").innerHTML = "系统异常，请再试试";
      }
    }

    xmlhttp.open("POST", "system.php", true);
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlhttp.send("Navi=login&UserName=" + usrnm + "&Password=" + paswd);

  } else{
    document.getElementById("loginSpan").innerHTML="请确认信息格式是否正确！";
  }
}
