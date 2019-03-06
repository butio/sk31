<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="inventoryControl.StockDB"%>
<%
ArrayList<StockDB> arrayList= (ArrayList<StockDB>) request.getAttribute("RESULT");
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
<meta http-equiv="Content-Style-Type" content="text/css">
<link rel="stylesheet" href=" ${pageContext.request.contextPath}/css/design.css">
<link rel="stylesheet" type="text/css" href="./css/reset.css">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在庫管理</title>
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
	<div id="earning">
		<table class="table table-hover">
			<tr>
				<th>地域</th>
				<th>自動販売機場所</th>
				<th>商品名</th>
				<th>在庫数</th>
				<th>最大在庫数</th>
				<th>地域在庫ボーダーライン</th>
			</tr>
			<%
				for (StockDB s : arrayList) {
			%>
			<tr>
				<td><%=s.getArea()%></td>
				<td><%=s.getPlace()%></td>
				<td><%=s.getProductName()%></td>
				<td><%=s.getStock()%></td>
				<td><%=s.getMaxStock()%></td>
				<td><%=s.getStockBorderline()%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<center class="item">
		<button class="button" onclick="location.href='./RegionalStock'">戻る</button>
		<button class="button" onclick="location.href='./VmStock'">自動販売機の在庫を見に行く</button>
	</center>
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