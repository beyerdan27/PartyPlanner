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
	private ArrayList<Attendee> guestObjs = new ArrayList<Attendee>();
	private ArrayList<Integer> numGuestsPerCompany = new ArrayList<Integer>();
	public Party(int numTablesA, int numSeatsA){ //constructing the party, like it's a party planner
		numTables = numTablesA;
		numSeats = numSeatsA;
		numGuestsPerCompany.add(-1);
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
		for(String s:companyIDs) numGuestsPerCompany.add(0);
		return 200;
		for(ArrayList<String> templist:guests){
			guestObjs.add(new Attendee(templist //PICK UP HERE
		}
	}
	public ArrayList<Integer> enumerateGuests(){ //return list of party guests who exceed limits
		int currentCompanyIDBeingCounted;
		for(ArrayList<String> templist:guests){
			currentCompanyIDBeingCounted = Integer.parseInt(templist.get(3));
			int tempct = numGuestsPerCompany.get(currentCompanyIDBeingCounted);
			if(tempct = 10){
					
			}
			tempct++;
			numGuestsPerCompany.set(currentCompanyIDBeingCounted, tempct);
		}
		ArrayList<Integer> faultyentries = new ArrayList<Integer>();
		int tc=0;
		for(int s:numGuestsPerCompany){
			System.out.println(s);
			tc++;
			if(s>numTables) faultyentries.add(tc);
		}
		return faultyentries;
	}
	
}
