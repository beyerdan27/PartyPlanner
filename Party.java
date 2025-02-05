/**
 * Party.java class for managing party logic in PartyPlanner
 * Preconditions: partyguests.txt and companies.txt files to import
 * Postconditions: loaded party guests, sorted at each table
 * @param numTablesA
 * @param numSeatsA
 */
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.ArrayList;
 import java.util.Scanner;
public class Party{
	//instance variables as the kids say nowadays
	//private String[] temp = new String[2];
	private int numTables, numSeats;
	ArrayList<String> companyIDs = new ArrayList<String>();
	ArrayList<ArrayList<String>> guests = new ArrayList<ArrayList<String>>();
	public Party(int numTablesA, int numSeatsA){ //constructing the party, like it's a party planner
		numTables = numTablesA;
		numSeats = numSeatsA;
	}
	public int loadData(){
		try{
			File companies = new File("companies.txt");
			Scanner reader1 = new Scanner(companies);
			while(reader1.hasNextLine()){
				String[] temp = reader1.nextLine().split(",");
				System.out.println(temp[0]);
			}
		}
		catch(FileNotFoundException e){
			return 404;
		}
		/*try{
			
		}
		catch(FileNotFoundException e){
			return 404;
		}*/
		return 200;
			
		
		
		
	}
}
