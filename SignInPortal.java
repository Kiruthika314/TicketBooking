package com.kiruthi;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignInPortal
 */
@WebServlet("/SignInPortal")
public class SignInPortal extends HttpServlet {
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
		        String pw = request.getParameter("pw");


		       if(pw.equals(pword)) {
		        String query = "insert into account values(?,?)";
		        PreparedStatement pst = con.prepareStatement(query);
		       
		        pst.setString(1, pname);
		        pst.setString(2, pword);
		       
		        int rs= pst.executeUpdate();
		        if(rs==1)out.println("<p> Account created successfully</p>");
		        out.println("<a href ='Home.jsp' > Book a bus</a>");}
		       else {
		    	   out.println("<p>Refresh and retry</p>");
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

