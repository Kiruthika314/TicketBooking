package com.kiruthi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginPortal
 */
@WebServlet("/LoginPortal")
public class LoginPortal extends HttpServlet {
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
	        
	        String pname = request.getParameter("pname");
	        String pword = request.getParameter("pword");


	       
	        String query = "Select * from account where passName=? and pWord=?";
	        PreparedStatement pst = con.prepareStatement(query);
	       
	        pst.setString(1, pname);
	        pst.setString(2, pword);
	       
	        ResultSet rs= pst.executeQuery();

	        if (rs.next()) {
	        	String query2 = "Select * from booking where pass_name=? ";
		        PreparedStatement pst2 = con.prepareStatement(query2);
		        pst2.setString(1, pname);
		        ResultSet rss=pst2.executeQuery();
		        while(rss.next()) {
		        	out.println("<p> Name : "+rss.getString(1)+"| Bus No.: "+rss.getInt(2)+"| Date : "+rss.getDate(3)+"</p");
		        }
		     	            
	        } else {
	            out.println("<p>Username or Password is incorrect. Please try again.</p>");
	        }
	    } catch (Exception e1) {
	        e1.printStackTrace();
	        out.println("<p>An error occurred: " + e1.getMessage()+"<p>");
	    }
	    out.println("<a href ='Home.jsp' > Book a bus</a>");
	    out.println("</div>"); 
	    out.println("</body>");
	    out.println("</html>");

	}
    
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doPost(request, response); // Forward GET requests to doPost
	    }
}

