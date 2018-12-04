import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;

//where the required SQL statements are executed
//also establish connection and setup major tasks
//create the tables, fill them up with data
//and execute SQL statements : 8 of them

public class Driver {

	public static void main(String[] args) throws SQLException{

		Connection conn;
		conn = GettingConnection.getconnection();
		try
		{
			Statement stmt = conn.createStatement();
			CreatingTables createTable = new CreatingTables(stmt, conn);
			createTable.createtable();

			InsertData insert = new InsertData(stmt, conn);
			insert.fillTable();

			
			//First SQL statement
			//Select all authors from the author's table and order all of them based on alphabetical order.
			String query1 = "select * from authors ORDER BY firstName ASC, lastName ASC";
			ResultSet resultset = stmt.executeQuery(query1);
			System.out.println("Author's information:   ID  Name");
			while (resultset.next())
			{
				int authorId = resultset.getInt(1);
				String name = resultset.getString(2);
				
				System.out.println("                        " + authorId + "  "+ name);
			}
			System.out.println(
					"*****************************************************************************************"); 
			
			
			
			
			//Second SQL statement
			//Select all publishers from publisher table
			String query2 = "select * from publishers";
			ResultSet resultset2 = stmt.executeQuery(query2);
			System.out.println("Publisher's information: ID   Name");	
			while (resultset2.next())
			{
				String publisherName = resultset2.getString(2);
				int publisherId = resultset2.getInt(1);
				
				System.out.println("                         "  + publisherId + "   " + publisherName);
			}
			System.out.println(
					"*****************************************************************************************"); 
			
			
			
			
			//Third SQL statement
			//select specific publishers with id from 1 to 18, so we select 17 publishers. 

			for (int i = 1; i < 19; i++)
			{
				String query3 = "Select title, isbn, year from titles where publisherId= "
						+ i + " ";
				ResultSet resultset3 = stmt.executeQuery(query3);
				while (resultset3.next())
				{
					String title = resultset3.getString(1);
					String isbn = resultset3.getString(2);
					String year = resultset3.getString(3);
					System.out.println("Title:" + title + "ISBN:" + title
							+ "Year:" + year);
				}
			}
			System.out.println(
					"*****************************************************************************************"); 
			
			
			

			//Fourth SQL statement
			//add new author
			String query4 = "insert into authors(authorId, firstname, lastname) values (18, 'John', 'Doe') ";
			stmt.executeUpdate(query4);
			String sub = "Select firstname, lastname from authors where authorid= 18";
			ResultSet rs = stmt.executeQuery(sub);
			while (rs.next())
			{
				String lastName = rs.getString(2);
				String name = rs.getString(1);
				System.out.print("The author which was recently added to the database: ");
				System.out.println(name + " " + lastName);
			}
			System.out.println(
					"*****************************************************************************************"); 
			
			

			//Fifth SQL statement
			//Edit/Update the existing information about an author

			String query5 = "Update authors set firstname='Ahmed' where authorid=1";
			stmt.executeUpdate(query5);
			String subquery = "Select * from authors";
			ResultSet rs2 = stmt.executeQuery(subquery);
			while (rs2.next())
			{
				
				String first = rs2.getString(2);
				String last = rs2.getString(3);
				int Id = rs2.getInt(1);
				System.out.println("AuthorId:" + Id + " " + "FirstName:"+ first + " " + "LastName: " + last);
			}
			System.out.println(
					"*****************************************************************************************"); 
			
			

			//Sixth SQL statement
			//Add a new title for an author
			String query6 = "Insert into titles(isbn, title, editionNumber, year, publisherID, price) values ('251', 'Introduction to Database System', 8,'2010', 8, 190)";
			stmt.executeUpdate(query6);
			String sub3 = "Select * from titles";
			ResultSet rs3 = stmt.executeQuery(sub3);
			
			while (rs3.next())
			{
				String isbn, titles, year;
				int edition, publisher, price;
				isbn = rs3.getString(1);
				titles = rs3.getString(2);
				edition = rs3.getInt(3);
				year = rs3.getString(4);
				publisher = rs3.getInt(5);
				price = rs3.getInt(6);
				System.out.println("ISBN " + isbn + " " + "TITLES: " + titles
						+ " " + "Edition: " + edition + " " + "Year: " + year
						+ " " + "PublisherId: " + publisher + " " + "Price: "
						+ price);
				
				
			}
			System.out.println(
					"*****************************************************************************************"); 
			
			

			//Seventh SQL statement
			//Add new publisher
			String query7 = "Insert into publishers(publisherId, publishername) values( 18,'GreenWorld')";
			stmt.executeUpdate(query7);
			String sub4 = "Select * from publishers";
			ResultSet rs4 = stmt.executeQuery(sub4);
			System.out.println("Publisher's information: ID   Name");
			
			while (rs4.next())
			{
				int id = rs4.getInt(1);
				String name = rs4.getString(2);
				System.out.println("                         "  + id + "   " + name);
			}
			System.out.println(
					"*****************************************************************************************"); 
			
			

			//Eighth SQL statement
			//Edit/Update the existing information about a publisher
			String query8 = "Update publishers set publishername ='GreenWorldReuters' where publisherid=2";
			stmt.executeUpdate(query8);
			String sub5 = "Select * from publishers";
			ResultSet rs5 = stmt.executeQuery(sub5);
			
			System.out.println("Publisher's information: ID   Name");
			while (rs5.next())
			{
				String name = rs5.getString(2);
				int id = rs5.getInt(1);
				
				System.out.println("                         "  + id + "   " + name);
			}
			
			//terminating process once we are done
			 stmt.close();


		}
		catch (Exception exc) {
			exc.printStackTrace();
		}   
		//close the connection
		conn.close();
	}



}
