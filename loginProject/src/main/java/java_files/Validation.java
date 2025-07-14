package java_files;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Validation {
	public static int checkUser(String id,String pass) 
    {
        int flag = 0;
        try {
            Connection con=CreateConnection.myConnection();
            PreparedStatement ps = con.prepareStatement("select * from student where id=?");
            ps.setString(1, id);
            ResultSet rs =ps.executeQuery();
            if(rs.next()==true) {
            	String passw=rs.getString("password");
            	if(passw.equals(pass))
            		flag=2;
            	else
            		flag=1;
            }
            else {
            	flag=0;
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return flag;                 
    }   
}

