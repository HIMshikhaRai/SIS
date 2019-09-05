
package loginform;

import java.sql.*;

public class MyConnection 
{
    public static Connection getConnection() throws ClassNotFoundException
    {
        Connection con=null;
        try
        {
          Class.forName("com.mysql.jdbc.Driver");
          con=DriverManager.getConnection("jdbc:mysql://localhost/login_form","root","");
        }
        catch(SQLException ex)
        {
            System.out.println(ex.getMessage());
        }
        return con;
        

    }

}