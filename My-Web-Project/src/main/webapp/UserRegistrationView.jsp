<%@page import="com.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>

	<%-- <%
	String succ = (String) request.getAttribute("succMsg");
	String err = (String) request.getAttribute("errorMsg");
	%> --%>

	<%
	String succ = ServletUtility.getSuccesMessage(request);
	String err = ServletUtility.getErrorMessage(request);
	%>


	<div align="center">
		<h1>User Registration</h1>

		<%-- 	<h2 style="color: green"><%=succ != null ? succ : ""%></h2>   <!-- ternary operator used -->
		<h2 style="color: red"> <%=err != null ? err : "" %></h2> --%>

		<h2 style="color: green"><%=succ%></h2>
		<h2 style="color: red"><%=err%></h2>

		<form action="UserRegistrationCtl" method="post">

			<table>
				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName" value=""
						placeholder="enter your firstName"></td>
				</tr>

				<tr>
					<th>Last Name</th>
					<td><input type="text" name="lastName" value=""
						placeholder="enter your lastName"></td>
				</tr>

				<tr>

					<th align="left">Login</th>
					<td><input type="email" name="login" value=""
						placeholder="enter your login"></td>
				</tr>

				<tr>
					<th align="left">Password</th>
					<td><input type="password" name="password" value=""
						placeholder="enter your password"></td>

				</tr>

				<tr>
					<th align="left">D.O.B.</th>
					<td><input type="date" name="dob" value=""></td>

				</tr>

				<tr>
					<th></th>
					<td><input type="Submit" name="operation" value="SignUp"></td>

				</tr>


			</table>

		</form>
	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>