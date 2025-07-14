package java_files;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Connection con=CreateConnection.myConnection();
			String uid=request.getParameter("id");
			String pass=request.getParameter("pw");
			String uname=request.getParameter("nm");
			String department=request.getParameter("dept");
			String query="insert into student values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, uname);
			ps.setString(2, uid);
			ps.setString(3, pass);
			ps.setString(4, department);
			int b=ps.executeUpdate();
			if(b==1) {
				response.setContentType("text/html");
				PrintWriter out=response.getWriter();
				out.println("<center>");
	        	out.println("You have successfully Signed up");
	        	out.println("<br><br>");
	        	out.println("<a href=\"index.html\">Click To Login</a>");
	        	out.println("</center>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}
