<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Booking</title>
<link rel="stylesheet" href="Style.css">
</head>
<body>
<DIV CLASS="busbooking" ALIGN="center">

<FORM action="book" method="post"><!-- action="Bookings" -->
<label>Enter your name:</label><br>
<input type="text" name="passName" required><br><br>
<label>Enter mobile number:</label><br>
<input type="number" name="mobile" ><br><br>

    <input type="hidden" name="busNo" value="${param.busNo}">
    <input type="hidden" name="startingPoint" value="${param.startingPoint}">
    <input type="hidden" name="destination" value="${param.destination}">
    <input type="hidden" name="date" value="${param.date}">
    <input type="hidden" name="time" value="${param.time}">
    
<input type="submit" value="Confirm booking" class="submit-button"><!-- onclick="func()"> -->




<br>

</FORM>
</DIV>
</body>
</html>