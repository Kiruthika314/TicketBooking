<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home page</title>
<link rel="stylesheet" href="Style.css">

</head>
<body>

<DIV CLASS="java" align="center" >
<div class="header-container">
<img src="busimg.png" class="logo">
<H1>BUS TICKET RESERVATION</H1>

 </DIV>
<FORM action="SignInPortal" method="post">
<label>Enter user name</label><br>
<input type="text" name="pname" required><br><br>
<label>Enter password</label><br>
<input type="password" name="pw" required><br><br>
<label>Retype password</label><br>
<input type="password" name="pword" required><br><br>
<input type="Submit" class="submit-button" value="Create account"><br>
<br>
<p>Already have an account?</p>
<br><a href="login.jsp">Login</a>
</FORM>
</DIV>
</body>
</html>