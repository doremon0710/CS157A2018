import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.text.DecimalFormat;

//This class is used to populate data into title, publisher and isbn tables
//Do so by reading data from text files
public class InsertData
{
	private Statement statement;
	private Connection connection;
	private double leftLimit = 10;
	private double rightLimit = 500;

	public InsertData(Statement statement, Connection connection)
	{
		this.statement = statement;
		this.connection = connection;
	}
	public InsertData()
	{

	}
	public void fillTable()
	{
		try
		{
			DecimalFormat format = new DecimalFormat("##.00");
			ArrayList<String> firstName = new ArrayList<String>();
			ArrayList<String> lastName = new ArrayList<String>();
			//Getting the data in by BufferReader
			try (BufferedReader br = new BufferedReader(new FileReader("names.txt")))
			{
				String sCurrentLine;

				while ((sCurrentLine = br.readLine()) != null) {
					splitName(firstName,lastName,sCurrentLine);
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

			ArrayList<String> titles = readFile("books.txt");
			ArrayList<String> publisherName = readFile("publisher.txt");
			ArrayList<String> isbn = readFile("isbn.txt");

			//Insert values in tables authors, tiles, publisher and isbn

			for (int i = 0; i < 18; i++){
				Random rand = new Random();
				//Inserting values in relation Authors
				String authors = "INSERT INTO authors(authorID, firstname, lastname) "
						+ "VALUES(" + i + ", '" + firstName.get(i) + "', '" + lastName.get(i) + "' )";



				//Inserting values in relation Titles
				String title = "INSERT INTO titles(isbn, title, editionNumber, Year, publisherID, price)"
						+ " VALUES ('ISBN" + isbn.get(i) + " ' ,' " + titles.get(i) + " ' , " + (rand.nextInt(4) + 1)
						+ " , " + (2010 + rand.nextInt(8) + 1) + " , " + i + " , "
						+ (format.format(leftLimit + new Random().nextDouble() * (rightLimit - leftLimit))) + ") ";

				String publisher = "INSERT INTO publishers(publisherID, publisherName)"
						+ " Values("+ i + ", '" + publisherName.get(i) + "') ";

				String authorIsbn = "INSERT INTO authorISBN(authorId, Isbn)"
						+ " VALUES("+ i + " , '" + i + "')";

				//SQL statements to be passed in
				statement.execute(authors);
				statement.execute(authorIsbn);
				statement.execute(publisher);
				statement.execute(title);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}
	//data input processing method
	public static void splitName(List<String> firstName, List<String> lastName, String name) {
		firstName.add(name.substring(0, name.indexOf(" ")));
		lastName.add(name.substring(name.indexOf(" ") + 1, name.length()));
	}

	public static ArrayList<String> readFile(String fileName){
		ArrayList<String> store = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
		{
			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				store.add(sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return store;
	}
}
