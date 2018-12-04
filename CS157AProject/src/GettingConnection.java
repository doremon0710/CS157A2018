import java.sql.*;
import java.io.*;
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.Connection;


//To establish connection to the database
//Host the database using AWS
public class GettingConnection {
	//user name and password to get connected to the database
	private static String password = "classproject";
	private static String username = "project157a";
	
	public static Connection getconnection()
    {
		//Use AWS Dynamo DB as the database hosting service
        String connectionString ="jdbc:mysql://rds-mysql-157a.cwoy7z4hfj1e.us-east-1.rds.amazonaws.com:3306/books?useSSL=false";
        Connection conn = null;
        try
        {
        	conn = DriverManager.getConnection(connectionString, username, password);
        } catch (SQLException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;

    }

}
