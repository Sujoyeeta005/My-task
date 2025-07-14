package java_files;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        
        String uid = request.getParameter("id");
        String pass = request.getParameter("pw");
        int isValid=Validation.checkUser(uid, pass);
        String userName="";
        if(isValid==2)
        {
        	try {
				userName=getUserName(uid);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
        	request.setAttribute("x", userName);
            RequestDispatcher rs = request.getRequestDispatcher("/Welcome");
            rs.forward(request, response);
        }
        else if(isValid==1) {
        	
        	try {
				userName+=getUserName(uid);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
        	request.setAttribute("uname", userName);
        	RequestDispatcher rs = request.getRequestDispatcher("/WrongPass");
        	rs.forward(request, response);
        }
        else if(isValid==0)
        {
        	response.setContentType("text/html");
            PrintWriter out = response.getWriter();
        	out.println("<center>");
        	out.println("User ID doesn't Exist");
        	out.println("<br><br>");
        	out.println("<a href=\"signup.html\">Click To Signup</a>");
        	out.println("</center>");
        }
    }  
	public static String getUserName(String uid) throws SQLException, ClassNotFoundException {
		String name="";
		Connection con=CreateConnection.myConnection();
		
		PreparedStatement ps = con.prepareStatement("select * from student where id=?");
		ps.setString(1, uid);
        ResultSet rs =ps.executeQuery();
        if(rs.next())
        	name=rs.getString("name");
        return name;
	}
}

