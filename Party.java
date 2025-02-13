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
	private int numCompanies;
	public Party(int numTablesA, int numSeatsA){ //constructing the party, like it's a party planner
		numTables = numTablesA;
		numSeats = numSeatsA;
		numGuestsPerCompany.add(-1);
	}
	public int loadData(){ //parses every file necesssary and sets all vars related to that, run before enumerateGuests()
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
			numCompanies = companyIDs.size();
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
		for(ArrayList<String> templist:guests){
			guestObjs.add((new Attendee(Integer.parseInt(templist.get(0)), templist.get(1), templist.get(2), Integer.parseInt(templist.get(3))))); //PICK UP HERE
		}
		for(Attendee att:guestObjs){
			System.out.println(att.toString());
		}
		return 200;
	}
	public void enumerateGuests(){ //this is a thing for some reason and it sucks and i dont think its necessary but im doing it anyway to avoi d anything stupid happening in the future
		for(Attendee att:guestObjs){
			int currentCompanyIDBeingCounted = att.getCoID();
			int tempct = numGuestsPerCompany.get(currentCompanyIDBeingCounted);
			if(tempct == 10){
				att.setAttendance(false);
			} else {
				numGuestsPerCompany.set(currentCompanyIDBeingCounted, tempct+1);
				att.setAttendance(true);
			}
		}
		for(Attendee attasdf:guestObjs){
			System.out.println(attasdf.getAttendance());
		}
		for(int i:numGuestsPerCompany){
			System.out.println(i);
		}
		if(numTables*numSeats<guestObjs.size()){
			int differential = (guestObjs.size()-(numTables*numSeats));
			//System.out.println("ASDFJASLDIFGJSLGJLASKFGJLAKDFJG " + differential);
			for(int i=10;i>0;i++){
				for(int ){ //stupif method wont work i hate this
					
				}
			}
		}
					
	}
	public void sortGuests(){} //LOL i wonder how many centuries until i'll be able to rewrite this
	
}
