package com.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StudentRegistration
 */
public class StudentRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	    
    /**
     * @see HttpServlet#HttpServlet()
     */
	//Class.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String rollno=request.getParameter("rollno");
		String branch=request.getParameter("branch");
		String marks=request.getParameter("marks");
		String pwd=request.getParameter("pwd");
		String cpwd=request.getParameter("cpwd");
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE","system","system");
			String query="insert into student values(?,?,?,?,?)";
			 PreparedStatement ps= con.prepareStatement(query);
			 ps.setString(1, name);
			 ps.setString(2, rollno);
			 ps.setString(3, branch);
			 ps.setString(4, marks);
			 ps.setString(5, pwd);
			 int updated=ps.executeUpdate();
			 out.println("updation : "+updated);
			 session.setAttribute("uid", name);
			 if(updated>0)
				 response.sendRedirect("jsp/LoginSuccesful.jsp");
			 else
				 response.sendRedirect("html/Signup.html");
		}
		catch(Exception e)
		{
			out.println("Excedption" +e);
		}
	}

}
