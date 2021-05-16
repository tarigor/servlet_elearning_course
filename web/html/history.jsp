<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%--<%@ taglib prefix="ex" uri="http://www.ketkee.com/dateFormatter"%>--%>
<!DOCTYPE html >
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>H+ Sport</title>
	<link rel="stylesheet" href="css/style.css">
</head>
<body>

<header id="home" class="header">
	<nav class="nav" role="navigation">
		<div class="container nav-elements">
			<div class="branding">
				<a href="#home"><img src="images/hpluslogo.svg"
				                     alt="Logo - H Plus Sports"></a>
			</div>
			<!-- branding -->
			<ul class="navbar">
				<li><a href="home">home</a></li>
				<li><a href="history">order history</a></li>
				<!-- <li><a href="viewProfile">view my profile</a></li> -->
				<li><a href='<%=response.encodeURL("viewProfile")%>'>view
					my profile</a></li>
				<li><a href='logout'>logout</a></li>
				<li><a href="redirect">linkedIn</a></li>
			
			</ul>
			<!-- navbar -->
		</div>
		<!-- container nav-elements -->
	</nav>
	<!-- <div class="container tagline">
<h1 class="headline">Our Mission</h1>
<p>We support and encourage <em>active and healthy</em> lifestyles, by offering <em>ethically sourced</em> and <em>eco-friendly</em> nutritional products for the <em>performance-driven</em> athlete.</p>
</div>container tagline -->
</header>

<fmt:setBundle basename="by.epam.servletCourse.resources.localization" var="message"/>

<div class="container">
	<h2 class="headline"><fmt:message bundle="${message}" key="label.header.orders"/> ${requestScope.userName}</h2>
	<c:if test="${requestScope.historyList!=null}">
		<table id="history">
			<jsp:useBean id="historyList" scope="request" type="java.util.List"/>
			<tr>
				<th>Number</th>
				<th>Order Date</th>
				<th>Product Name</th>
				<th>Image</th>
			</tr>
			<c:forEach items="${historyList}" var="history" varStatus="loop">
				<tr>
					<td>${loop.count}</td>
					<td> ${history.orderDate}</td>
					<td> ${history.productName}</td>
					<td><img src="${history.imagePath}" width="200px" height="150px"></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>

<footer class="footer">
	<div class="container">
		<nav class="nav" role="navigation">
			<div class="container nav-elements">
				<div class="branding">
					<a href="#home"><img src="images/hpluslogo.svg"
					                     alt="Logo - H Plus Sports"></a>
					<p class="address">
						100 Main Street<br> Seattle, WA 98144
					</p>
				</div>
			</div>
		</nav>
		<p class="legal">H+ Sport is a fictitious brand created by
			lynda.com solely for the purpose of training. All products and
			people associated with H+ Sport are also fictitious. Any resemblance
			to real brands, products, or people is purely coincidental.
			Information provided about the product is also fictitious and should
			not be construed to be representative of actual products on the
			market in a similar product category.</p>
	</div>
	<!-- container -->
</footer>