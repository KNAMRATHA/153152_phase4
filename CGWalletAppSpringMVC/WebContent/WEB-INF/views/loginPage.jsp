<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>
<body>
	<center>
		<div style="background-color: green">
			<h3>Login here</h3>
		</div>
		<table border="1">
			<form action="loginCustomer" method="post"
				modelAttribute="customer">
				<tr>
					<td>Mobile Number</td>
					<td><input type="text" name="mobileNo" path="mobileNo" size="30" /></td>
								</tr>
				<tr>
					<td><input type="submit" value="Login"></td>
				</tr>
			</form>
			<div><font color="red"><c:if test="${not empty errorMessage }">
			${errorMessage}
		</c:if>
			
			</font></div>
			</table>
			</center>
</body>
</html>