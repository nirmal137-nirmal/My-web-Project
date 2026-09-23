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
	
	<% String succ = (String) request.getAttribute("succMsg"); // type cast kra hai
	   String err = (String) request.getAttribute("errorMsg");
 	%>

	<div align="center">
		<h1>Login</h1>
		
		<h2 style="color: green"><%= succ != null ? succ : ""  %></h2>
		<h2 style="color: red"> <%= err != null ? err : "" %></h2>

		<form action="LoginCtl" method="post">  <!-- request body me sara form ka data hide hoke jayega --> 
			<table>
				<tr>
					<th align="left">Login</th>
					<td><input type="email" name="login" value=""
						placeholder="enter your login"></td>
				</tr>

				<tr>
					<th>Password</th>
					<td><input type="password" name="password" value=""
						placeholder="enter your password"></td>
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="SignIn"></td>
					
							<!-- request jayegi submit Pe click Karenge to --> 
				</tr>

			</table>


		</form>


	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>