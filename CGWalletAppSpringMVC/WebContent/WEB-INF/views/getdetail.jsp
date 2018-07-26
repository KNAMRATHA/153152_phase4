<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h3>Customers with less balance is</h3>

<table border="3">
			<tr>
				<th>Mobile Number</th>
				<th>Name</th>
				<th>Amount</th>
				</tr>
				<c:forEach items="${customer1}" var="customer">
				<tr>
					<td>${customer.mobileNo}</td>
					<td>${customer.name}</td>
					<td>${customer.wallet.balance}</td>
					</tr>
					</c:forEach>
					</table>
</center>
</body>
</html>