package com.kiruthi;
import java.sql.DriverManager;
public class Connections {
		
		public static  java.sql.Connection getConnect() throws Exception{

			String url = "jdbc:mysql://localhost:3306/busreservations";
		    String uname = "root";
		    String pw = "kiruthi314";
			return DriverManager.getConnection(url, uname, pw);
			

		}
		
}
