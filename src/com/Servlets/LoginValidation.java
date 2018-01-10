package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginValidation
 */
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
    	System.err.println("test can see error messages");System.err.println("test can see error messages");
         String huid = request.getParameter("uid");
         String hpwd = request.getParameter("pwd");
         String password = null;
         String htpwd = null;
         ResultSet rs,prs;
         response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out=response.getWriter();
         if(huid.equals("admin"))
         {
        	 if(hpwd.equals("admin"))
        	 {
        		 request.getSession().setAttribute("uid", "admin");
        		 response.sendRedirect("jsp/LoginSuccesful.jsp");
        	 }
        	 else
        	 {
                response.sendRedirect("jsp/LoginFailed.html");
        	 }
        		 
         }
         else
         {
        	 try
        	 
        	 {
        		  
        		 out.println("in try user is"+huid);
        		 Class.forName("oracle.jdbc.driver.OracleDriver");
        		 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","system","system");
        		 out.println("conn esta"+con);
        		 Statement st= con.createStatement();
        		 rs=st.executeQuery("select * from student");
        		 String query="select * from student where name="+""+huid;
        		 PreparedStatement ps= con.prepareStatement(query);
        		ps.executeQuery();
        		prs=ps.getResultSet();
        		while (prs.next()) 
       		 {
       			out.println("Details"+ prs.getString(1));
       		 }
        	            con.close();
        	            
        	 }
        	 catch(Exception e)
        	 {
        		 out.println("conn failed excception caUGHT:: "+e);
        	 }
        	
         }
	}

}

