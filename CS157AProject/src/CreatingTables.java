import java.sql.*;
import java.util.Random;

//To initialize/ creating tables
public class CreatingTables {
	
	private Statement statement;
    private Connection connection;

    // Initializing the statement and connection with our database
    public CreatingTables(Statement statement, Connection connection)
    {
        this.statement = statement;
        this.connection = connection;
    }

    // Function to create the four table provided
    public void createtable()
    {
        //Creating table Author
        String authors = "CREATE TABLE authors( " + " authorID integer not null, "
        										  + " firstName varchar(200), "
                								  + " lastName varchar(200), "
                								  + " CONSTRAINT constraint_pk_authorID PRIMARY KEY(authorID))";
       //Create ISBN table
        String authorISBN = "CREATE TABLE authorISBN( " + "authorID INTEGER, "
                										+ "isbn CHAR(10), "
                										+ "CONSTRAINT constraint_fk_authorId FOREIGN KEY(authorID) REFERENCES authors(authorID)) ";
        
        
        // Creating table Publishers
        String publishers = "CREATE TABLE publishers( " + " publisherID integer not NULL, "
                										+ " publisherName char(100), "
                										+ " CONSTRAINT constraint_pk_publisherId PRIMARY KEY ( publisherID))";
        
        
        //Creating table Titles
        //Due to the foreign key constraint, the publisher table has to be created before the tiles table
        String titles = "CREATE TABLE titles( " + " isbn CHAR(20), "
                								+ " title VARCHAR(100), "
                								+ " editionNumber integer, "
                								+ " Year CHAR(4), " 
                								+ " publisherID integer, "
                								+ " price INTEGER, "
                								+ " CONSTRAINT constraint_pk_isbn PRIMARY KEY (isbn), "
                								+ " CONSTRAINT constraint_fk_publisherId FOREIGN KEY ( publisherID ) REFERENCES publishers( publisherID ))";


        try
        {
            //Executes the given tables to add tables to Database
        	//Drop the below tables in case they previously exist in the database
        	String t = "DROP TABLE IF EXISTS authorISBN";
        	String t1 = "DROP TABLE IF EXISTS authors";
        	String t2 = "DROP TABLE IF EXISTS titles";
        	String t3 = "DROP TABLE IF EXISTS publishers";
        	//Create those tables
        	statement.executeUpdate(t);
        	statement.executeUpdate(t1);
        	statement.executeUpdate(t2);
        	statement.executeUpdate(t3);
        	statement.executeUpdate(authors);
        	statement.executeUpdate(authorISBN);
        	statement.executeUpdate(publishers);
        	statement.executeUpdate(titles);
        } catch (SQLException se)
        {
            // Catches if there are exceptions raised by SQL
            se.printStackTrace();
        } catch (Exception e)
        {
            // Catches any Java Exceptions
            e.printStackTrace();
        }
    }
    //method to drop tables --> just in case
    public void dropTable(String name1,String name2,String name3,String name4) {
        try
        {
        	statement.executeUpdate("DROP TABLE IF EXISTS " + name1 + ";");
        	statement.executeUpdate("DROP TABLE IF EXISTS " + name2 + ";");
        	statement.executeUpdate("DROP TABLE IF EXISTS " + name3 + ";");
        	statement.executeUpdate("DROP TABLE IF EXISTS " + name4 + ";");
        } catch (SQLException se)
        {
            se.printStackTrace();
        }
    	
    }

}
