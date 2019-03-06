<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="java.util.ArrayList" %>
<%
String Choise = (String)request.getAttribute("Choise");
String ChoisePrice = (String)request.getAttribute("ChoisePrice");
ArrayList<ArrayList<String>> aryTable = (ArrayList<ArrayList<String>>) request.getAttribute("Result");
%>
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
<link rel="stylesheet" href=" ${pageContext.request.contextPath}/css/design.css">
<link rel="stylesheet" type="text/css" href="./css/reset.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>売上参照</title>
</head>
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
<h3 class="infomation">情報を参照する<%=Choise %>を選択してください。</h3>
<form action ="./EarningVending"  method="post">
<center class="jihanki">
<select name="select" class="design">
<% for(ArrayList<String> rec: aryTable){ %>
	<% int cnt = 0; %>
	<% for(String data : rec){ %>
		<% if(cnt == 0){ %>
		<option value="<%=data %>">
		<% cnt = 1; %>
		<% }else{ %>
		<%=data %></option>
		<% } %>
	<% } %>
<%} %>
</select>
</center>
<center class="jihanki">
  <input type="hidden" name="choise"value="<%=ChoisePrice %>"></input>
  <button class="button" type="submit" name="action" value="table">票で表示</button>
  <button class="button" type="submit" name="action" value="Piechart">グラフで表示</button>
</center>
</form>
</div>
</body>
</html>