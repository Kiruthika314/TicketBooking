package com.kiruthi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SelectBus")
public class SelectBus extends HttpServlet {
	int bno=0;
	String st="";
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
       
        
        try (Connection con = Connections.getConnect()) {
            String query = "SELECT busNo, timing ,capacity FROM buses WHERE startingpoint = ? AND destination = ?";
            String que="Select count(passName) from booked where startingpoint = ? AND destination = ? AND bdate=?";
            
            PreparedStatement pst = con.prepareStatement(query);
            PreparedStatement pst2 = con.prepareStatement(que);
            
            String startingPoint = request.getParameter("startingpoint");
            String destination = request.getParameter("destination");
            String date=request.getParameter("date");
            LocalDate parsedDate = LocalDate.parse(date);  // Parse the string to LocalDate
            java.sql.Date sqlDate = java.sql.Date.valueOf(parsedDate);
            
            pst.setString(1, startingPoint);
            pst.setString(2, destination);
            
            pst2.setString(1, startingPoint);
            pst2.setString(2, destination);
            pst2.setDate(3, sqlDate);
           
            
            ResultSet rs = pst.executeQuery();
            ResultSet rs2 = pst2.executeQuery();
            
            int bookedSeat = rs2.next() ? rs2.getInt(1) : 0;
            while(rs.next() ) {
            	
            	bookedSeat=rs2.getInt(1);
            	 int timing=rs.getInt(2);
               	 int cap=rs.getInt(3);
               	 int availableSeats=cap-bookedSeat;
               	 bno=rs.getInt("busNo");
               	 out.println("<div class='java' align='center'> ");
                   out.println("<p>Bus No<strong>: " + bno +"</strong></p><br>");
                   out.println("<p>Time<strong> :"+timing+".00"+"</strong></p><br>");
                   out.println("<p>Available seats: <strong>"+availableSeats+"</strong></p>");
 
                   if(availableSeats>0) {
                   out.println("<form action='Booking.jsp' method='post'><br>");
                   out.println("<input type='hidden' name='busNo' value='" + rs.getInt("busNo") + "'>");
                   out.println("<input type='hidden' name='startingPoint' value='" + startingPoint + "'>");
                   out.println("<input type='hidden' name='destination' value='" + destination + "'>");
                   out.println("<input type='hidden' name='date' value='" + sqlDate + "'>");
                   out.println("<input type='hidden' name='time' value='" + rs.getInt("timing") +"'>");
                   out.println("<input type='submit' value='Book' class='submit-button'>");
                   out.println("</form>");
                   out.println("<hr>");
                }
            	
                else {
                    out.println("All Seats are booked");
                }
            	}
             
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("An error occurred while accessing the database.");
        } catch (Exception e1) {
			
			e1.printStackTrace();
			 out.println("An error occurred while accessing the database.");
	}         
        out.println("</div>"); 
        out.println("</body>");
        out.println("</html>");
//			 finally {
//            out.close();
//        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Forward GET requests to doPost
    }
}
