package com.kiruthi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class book
 */
@WebServlet("/book")
public class book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    
	    
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Select Bus</title>");
        out.println("<link rel='stylesheet' type='text/css' href='Style.css'>"); // Link to the CSS file
        out.println("</head>");
        out.println("<body>");
       

	    out.println("<div class='java' align ='center'");
	    try (Connection con = Connections.getConnect()) {
	        String date = request.getParameter("date"); 
	        String time = request.getParameter("time");
	        System.out.println("Date and time received: " + date + " " + time);

	        LocalDate parsedDate = LocalDate.parse(date); 
	        

	        java.sql.Date sqlDate = java.sql.Date.valueOf(parsedDate);  
	       int sqlTime=Integer.parseInt(time);

	        int bno = Integer.parseInt(request.getParameter("busNo")); 
	        String startingPoint = request.getParameter("startingPoint"); 
	        String destination = request.getParameter("destination");
	        String pname = request.getParameter("passName");

	        System.out.println("Parsed data: " + bno + ", " + startingPoint + ", " + destination);

	        String query = "insert into booked values(?,?,?,?,?,?)";
	        PreparedStatement pst = con.prepareStatement(query);
	        pst.setInt(1, bno);
	        pst.setString(2, startingPoint);
	        pst.setString(3, destination);
	        pst.setDate(4, sqlDate);
	        pst.setInt(5, sqlTime);
	        pst.setString(6, pname);

	        int rowsAffected = pst.executeUpdate();

	        if (rowsAffected > 0) {
	            out.println("<p>Hey,<strong> " + pname + "</strong>! Your booking is confirmed for Bus no. " + bno + " on " + sqlDate + " at " + sqlTime+"</p>");
	        } else {
	            out.println("<p>Booking failed. Please try again.</p>");
	        }
	    } catch (Exception e1) {
	        e1.printStackTrace();
	        out.println("<p>An error occurred: " + e1.getMessage()+"<p>");
	    }
	    out.println("</div>"); 
	    out.println("</body>");
	    out.println("</html>");

	}
    
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doPost(request, response); // Forward GET requests to doPost
	    }
}

