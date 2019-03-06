<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Sales.Earnings" %>
<%@ page import="java.util.ArrayList" %>
<%


String select = request.getParameter("select");
String choise = request.getParameter("choise");

Earnings E = (Earnings)request.getAttribute("E");

ArrayList<ArrayList<String>> aryTable = (ArrayList<ArrayList<String>>) request.getAttribute("PieChart");


%>
<link rel="stylesheet" href=" ${pageContext.request.contextPath}/css/design.css">
<link rel="stylesheet" type="text/css" href="./css/reset.css">
    <link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>売上参照</title>
</head>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);


      function drawChart() {


        var data = google.visualization.arrayToDataTable([
          ['商品', 'Hours per Day'],
  		<%for(ArrayList<String> rec: aryTable){%>
  		<%int cnt = 0;%>
  		<%for(String data : rec){%>
  		<%if(cnt == 0){%>
  		['<%=data%>',
  		 <%cnt ++;%>
  		<%}else{%>
  		<%=data%>],
  		<%}%>

  		<%}%>
  <%}%>
  ])
  		var options = {
          title: '商品売上割合'
        };

        var chart = new google.visualization.PieChart(document.getElementById('chart'));

        chart.draw(data, options);
      }
    </script>
<body>
<form name="Vending" method="post" action="./SelectVendingServlet">
	</form>
	<form name="Area" method="post" action="./SelectArea"></form>
	<form name="All" method="post" action="./EarningVending">
		<input type="hidden" name="action" value="table">

	</form>
	<form name="Stock" method="post" action="./StockCall"></form>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="adminTop.jsp"> 自動販売機総合システム </a>
		</div>

		<div class="collapse navbar-collapse" id="navbarEexample8">
			<ul class="nav navbar-nav">
				<li><a href="./StockCall">在庫管理</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> 売上参照 </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="#" onClick="document.All.submit();">全て</a>
						<a class="dropdown-item" href="#"
							onClick="document.Area.submit();">エリア</a> <a
							class="dropdown-item" href="#"
							onClick="document.Vending.submit();">自動販売機</a>
					</div></li>
				<li><a href="#">商品登録・変更</a></li>
				<li><a href="#">自販機登録・変更</a></li>
			</ul>
		</div>
	</div>
	</nav>

<form action ="./EarningVending"  method="post">
<div id="earning">
				<h2>表示範囲</h2>
				<p>
					期間：<%=E.getDate_Mes()%><br> 性別：<%=E.getSex_Mes()%><br>
					年代：<%=E.getAge_Mes()%><br>
				</p>
<center>
				<div id="chart" style="width: 900px; height: 500px;"></div>
</center>
</div>
<center>
  <p class="buyer">購入者の性別を選択</p>
  <select name="sex" class="design2">
  <option value="" selected>--</option>
  <option value="1">男性</option>
  <option value="2">女性</option>
  </select>
  <p class="buyer">購入者の年代を選択</p>
  <select name="Age" class="design2">
  <option value="" selected>--</option>
  <option value="9">10代以下</option>
  <option value="19">10代</option>
  <option value="29">20代</option>
  <option value="39">30代</option>
  <option value="49">40代</option>
  <option value="59">50代</option>
  <option value="60">60代以上</option>
  </select>
  <p class="buyer">売上期間の選択</p>
  <%if(E.getMes() != ""){ %>
  <p class="message"><%=E.getMes() %></p>
  	 <br>

  <%} %>
  <select name="year" class="design2">
  <option value="" selected>--</option>
  <% for(int cnt = 2019; cnt >= 1900 ; cnt --) {%>
  <option value="<%=cnt %>"><%=cnt %></option>
  <%} %>
  </select>
    <select name="month" class="design3">
      <option value="" selected>--</option>
  <% for(int cnt = 1; cnt <= 12 ; cnt ++) {%>
  <option value="<%=String.format("%02d",cnt) %>"><%=cnt %></option>
  <%} %>
  </select>
      <select name="day" class="design3">
        <option value="" selected>--</option>
  <% for(int cnt = 1; cnt <= 31 ; cnt ++) {%>
  <option value="<%=String.format("%02d",cnt) %>"><%=cnt %></option>
  <%} %>
  </select>
  ～
    <select name="year2" class="design2">
      <option value="" selected>--</option>
  <% for(int cnt = 2019; cnt >= 1900 ; cnt --) {%>
  <option value="<%=cnt %>"><%=cnt %></option>
  <%} %>
  </select>
    <select name="month2" class="design3">
      <option value="" selected>--</option>
  <% for(int cnt = 1; cnt <= 12 ; cnt ++) {%>
  <option value="<%=String.format("%02d",cnt) %>"><%=cnt %></option>
  <%} %>
  </select>
      <select name="day2" class="design3">
        <option value="" selected>--</option>
  <% for(int cnt = 1; cnt <= 31 ; cnt ++) {%>
  <option value="<%=String.format("%02d",cnt) %>"><%=cnt %></option>
  <%} %>
  </select>
</center>

<center class="item">
<button class="button" type="submit" name="action" value="Piechart">選択項目を反映して再表示</button>
				<input type="hidden" name="select" value="<%=select%>">
				<%if(choise != null){ %>
				<input type="hidden" name="choise"value="<%=choise%>">
				<%} %>
<button class="button" type="submit" name="action" value="Piechart_category">円グラフで表示（カテゴリー毎）</button>

<button class="button" type="submit" name="action" value="table">表で表示</button>




</center>
</form>
</div>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
		crossorigin="anonymous"></script>
</body>
</html>