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
	private ArrayList<String> companyIDs = new ArrayList<String>();
	private ArrayList<ArrayList<String>> guests = new ArrayList<ArrayList<String>>();
	private ArrayList<Integer> numGuestsPerCompany = new ArrayList<Integer>();
	numGuestsPerCompany.set(0, "-1");
	public Party(int numTablesA, int numSeatsA){ //constructing the party, like it's a party planner
		numTables = numTablesA;
		numSeats = numSeatsA;
	}
	public int loadData(){
		try{
			File companies = new File("companies.txt");
			Scanner reader1 = new Scanner(companies);
			while(reader1.hasNextLine()){
				String temp = reader1.nextLine();
				if(temp.length()>0){
					companyIDs.add(temp.substring(3));
				}
			}
			reader1.close();
		}
		catch(FileNotFoundException e){
			return 404;
		}
		try{
			File guestss = new File("partyguests.txt");
			Scanner reader2 = new Scanner(guestss);
			while(reader2.hasNextLine()){
				String[] temp = reader2.nextLine().split(",");
				if(temp.length>0){
					ArrayList<String> templist = new ArrayList<String>();
					for(String t:temp) templist.add(t);
					guests.add(templist);
				}
			}
			reader2.close();
		}
		catch(FileNotFoundException e){
			return 404;
		}
		/*(for(ArrayList<String> templistt:guests){
			for(String s:templistt){
			System.out.print(s+" ");
		}
		System.out.println();
		}*/
		return 200;
	}
	public ArrayList<Integer> enumerateGuests(){ //returns list of companies that exceed attendance limits
		String currentCompanyIDBeingCounted = guests.get(0).get(3);
		int currentCount=0;
		for(ArrayList<String> templist:guests){
			if(templist.get(3).equals(currentCompanyIDBeingCounted)){
				currentCount++;
				numGuestsPerCompany.set(currentCompanyIDBeingCounted, currentCount);
			} else {
				currentCount=1;
				currentCompanyIDBeingCounted = templist.get(3);
			}
		}//INCOMPLETE
			
	}
	
}
