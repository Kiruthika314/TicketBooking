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
 <div class="auth-links">
<a href="login.jsp" >Login</a>
<a href="signin.jsp" >Sign in</a><br>
</div>
 </DIV>
<FORM action="SelectBus" method="post">
<label>Starting point</label><br>
<select name="startingpoint" >
    <option >Chennai</option>
    <option >Trichy</option>
    <option >Nagapattinam</option>
    <option >Madurai</option>
</select>
<br>
<label>Destination</label><br>
<select name="destination" >
    <option >Chennai</option>
    <option >Trichy</option>
    <option >Nagapattinam</option>
    <option >Madurai</option>
</select>
<br>
<label>Date</label><br>
<input type="date" name="date"><br><br>
<input type="Submit" class="submit-button">

</FORM>
</DIV>
</body>
</html>