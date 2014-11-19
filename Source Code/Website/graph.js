var depositTime={
  "03": "一季度",
  "06": "二季度",
  "12": "一年",
  "24": "二年",
  "36": "三年",
  "60": "五年"
}

var depositInterest={
  "0": "固定利率",
  "1": "按基金浮动利率",
  "2": "CPI同浮动利率",
  "4": "定活两便固定利率",
  "5": "定活两便按基金浮动利率",
  "6": "定活两便CPI同浮动利率"
}

function graphLoad() {
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
      } else {
       getGraphData();
      }
    }
  }
  xmlhttp.open("POST", "system.php", true);
  xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xmlhttp.send("Navi=checkLogined");
}

function financingLoad() {
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
      } else {
       getFinancingData();
      }
    }
  }
  xmlhttp.open("POST", "system.php", true);
  xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xmlhttp.send("Navi=checkLogined");
}


function addAnotherRow(num,per,dat,exp,n,table) {
  var newTradeRow=document.getElementById(table).insertRow(n);
  var numColumn=newTradeRow.insertCell(0);
  var personColumn=newTradeRow.insertCell(1);
  var dateColumn=newTradeRow.insertCell(2);
  var expenseColumn=newTradeRow.insertCell(3);
  numColumn.innerHTML=num;
  personColumn.innerHTML=per;
  dateColumn.innerHTML=dat;
  expenseColumn.innerHTML=exp;
}

function addFinancingRow(num,per,dat,ins,cap,n) {
  var newTradeRow=document.getElementById("financingTable").insertRow(n);
  var numColumn=newTradeRow.insertCell(0);
  var personColumn=newTradeRow.insertCell(1);
  var dateColumn=newTradeRow.insertCell(2);
  var interestColumn=newTradeRow.insertCell(3);
  var capitalColumn=newTradeRow.insertCell(4);
  numColumn.innerHTML=num;
  personColumn.innerHTML=per;
  dateColumn.innerHTML=dat;
  interestColumn.innerHTML=ins;
  capitalColumn.innerHTML=cap;
}

function getGraphData() {

  var xmlhttp;
  if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp = new XMLHttpRequest();
  } else { // code for IE6, IE5
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
  }

  xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
      var graphData = xmlhttp.responseText;
      showGraph(graphData);
    }
  }

  xmlhttp.open("POST", "system.php", true);
  xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xmlhttp.send("Navi=getGraphData");

}

function showGraph(graphData) {
  var totalData = graphData.split(";");
  var numData = totalData[0].split(",");
  if(!numData[1])
    numData[1]="0";
  if(!numData[0])
    numData[0]="0";
  if(!numData[2])
    numData[2]="0";
  var expenses = parseInt(numData[0]) + parseInt(numData[1]);
  document.getElementById("shoppingLabel").innerHTML = "购物：" + numData[1] + "元";
  document.getElementById("transferLabel").innerHTML = "转账：" + numData[0] + "元";
  document.getElementById("totalExpenses").innerHTML = "支出: " + expenses + "元";
  document.getElementById("incomeLabel").innerHTML = "转账：" + numData[2] + "元";
  document.getElementById("totalIncome").innerHTML = "收入: " + parseInt(numData[2]) + "元";
  var tradeData = totalData[1].split(".");

  for (var i = 0; i < 50; i++) {
    if (tradeData[i]) {
      var tradeData1 = tradeData[i].split(",");
      var tradeDate = new Date(parseInt(tradeData1[1] + "000"));
      var chineseDate = tradeDate.toLocaleDateString();
      addAnotherRow(i+1,tradeData1[0], chineseDate, tradeData1[2],i,"tradeTable");
    } else
      break;
  }

  var transferData = totalData[2].split(".");

   for (var i = 0; i < 50; i++) {
    if (transferData[i]) {
      var transferData1 = transferData[i].split(",");
      var transferDate = new Date(parseInt(transferData1[1] + "000"));
      var chineseDate = transferDate.toLocaleDateString();
      addAnotherRow(i+1,transferData1[0], chineseDate, transferData1[2],i,"transferTable");
    } else
      break;
  }

  var incomeData = totalData[3].split(".");

   for (var i = 0; i < 50; i++) {
    if (incomeData[i]) {
      var incomeData1 = incomeData[i].split(",");
      var incomeDate = new Date(parseInt(incomeData1[1] + "000"));
      var chineseDate = incomeDate.toLocaleDateString();
      addAnotherRow(i+1,incomeData1[0], chineseDate, incomeData1[2],i,"incomeTable");
    } else
      break;
  }

  var chartData = totalData[4].split(".");
  var chartData0 = chartData[0].split(",");
  var chartData1 = chartData[1].split(",");
  var chartData2 = chartData[2].split(",");
  var chartData3 = chartData[3].split(",");
  var chartData4 = chartData[4].split(",");

  Morris.Donut({
    element: 'donut-expense',
    data: [{
      label: "转账",
      value: parseInt(numData[0])
    }, {
      label: "购物",
      value: parseInt(numData[1])
    }],
    colors: [
      '#FFFF66',
      '#FFCC00',
      '#FF9900'
    ]
  });


Morris.Line({
  element: 'tradeChart',
  data: [{
    year: chartData0[0],
    value: parseInt(chartData0[1])
  }, {
    year: chartData1[0],
    value: parseInt(chartData1[1])
  }, {
    year: chartData2[0],
    value: parseInt(chartData2[1])
  }, {
    year: chartData3[0],
    value: parseInt(chartData3[1])
  }, {
    year: chartData4[0],
    value: parseInt(chartData4[1])
  }],
  xkey: 'year',
  ykeys: ['value'],
  labels: ['月支出']
});

Morris.Line({
  element: 'transferChart',
  data: [{
    year: chartData0[0],
    value: parseInt(chartData0[2])
  }, {
    year: chartData1[0],
    value: parseInt(chartData1[2])
  }, {
    year: chartData2[0],
    value: parseInt(chartData2[2])
  }, {
    year: chartData3[0],
    value: parseInt(chartData3[2])
  }, {
    year: chartData4[0],
    value: parseInt(chartData4[2])
  }],
  xkey: 'year',
  ykeys: ['value'],
  labels: ['月支出']
});


Morris.Line({
  element: 'imcomeChart',
  data: [{
    year: chartData0[0],
    value: parseInt(chartData0[3])
  }, {
    year: chartData1[0],
    value: parseInt(chartData1[3])
  }, {
    year: chartData2[0],
    value: parseInt(chartData2[3])
  }, {
    year: chartData3[0],
    value: parseInt(chartData3[3])
  }, {
    year: chartData4[0],
    value: parseInt(chartData4[3])
  }],
  xkey: 'year',
  ykeys: ['value'],
  labels: ['月收入']
});

}

function getFinancingData(){
  var xmlhttp;
  if (window.XMLHttpRequest) { // code for IE7+, Firefox, Chrome, Opera, Safari
    xmlhttp = new XMLHttpRequest();
  } else { // code for IE6, IE5
    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
  }

  xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
      var financingData = xmlhttp.responseText;
      showFinancingData(financingData);
    }
  }

  xmlhttp.open("POST", "system.php", true);
  xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xmlhttp.send("Navi=getFinancingData");
}

function showFinancingData(financingData){
  financingData=financingData.split(";");

  var overviewData=financingData[0].split(",");
  var capital=parseInt(overviewData[0]);
  var balance=parseInt(overviewData[1])+capital;

Morris.Bar({
  element: 'financingBar',
  data: [
    { x: '本金', y: capital},
    { x: '现在', y: balance}
  ],
  xkey: 'x',
  ykeys: ['y'],
  labels: ['数目']
});

  var detailData=financingData[1].split("?");

   for (var i = 0; i < 50; i++) {
    if (detailData[i]) {
      var tableData = detailData[i].split(",");
      var tableDate = new Date(parseInt(tableData[1]));
      var chineseDate = tableDate.toLocaleDateString();
      if(tableData[0]=="000")
        var way="活期利率";
      else{
        var way=tableData[0];
        var thisTime=way.substr(1);
        var thisWay=way.charAt(0);
        way=depositTime[thisTime]+depositInterest[thisWay];
      }
      addFinancingRow(i+1,way, chineseDate, tableData[2],tableData[3],i);
    } else
      break;
  }

}