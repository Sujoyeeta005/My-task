package java_files;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class WrongPass extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String str=(String) request.getAttribute("uname");
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2 align=center>");
        out.println("Hello ");
        out.println(str);
        out.println(". You have entered a Wrong Password");
        out.println("</h2>");
        out.println("<br><br><center>");
    	out.println("<a href=\"index.html\">Click To Login</a>");
    	out.println("</center>");
	}

}
