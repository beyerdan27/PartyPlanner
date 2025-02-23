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
	Scanner univScan;
	public Party(int numTablesA, int numSeatsA){ //constructing the party, like it's a party planner
		numTables = numTablesA;
		numSeats = numSeatsA;
		numGuestsPerCompany.add(-1);
		univScan = new Scanner(System.in);
		System.out.println(" _____        _____ _________     __\n" + //
						"|  __ \\ /\\   |  __ \\__   __\\ \\   / /\n" + //
						"| |__) /  \\  | |__) | | |   \\ \\_/ / \n" + //
						"|  ___/ /\\ \\ |  _  /  | |    \\   /  \n" + //
						"| |  / ____ \\| | \\ \\  | |     | |   \n" + //
						"|_| /_/    \\_\\_|  \\_\\ |_|     |_|   \n" + //
						"                                    \n" + //
						" _____  _               _   _ _   _ ______ _____  \n" + //
						"|  __ \\| |        /\\   | \\ | | \\ | |  ____|  __ \\ \n" + //
						"| |__) | |       /  \\  |  \\| |  \\| | |__  | |__) |\n" + //
						"|  ___/| |      / /\\ \\ | . ` | . ` |  __| |  _  / \n" + //
						"| |    | |____ / ____ \\| |\\  | |\\  | |____| | \\ \\ \n" + //
						"|_|    |______/_/    \\_\\_| \\_|_| \\_|______|_|  \\_\\"); //this was formatted by VSCode from a paste
		System.out.println("\nLet's plan a party!");
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
		/*for(Attendee att:guestObjs){
			System.out.println(att.toString());
		}*/
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
		/*for(Attendee attasdf:guestObjs){
			System.out.println(attasdf.getAttendance());
		}*/
		/*for(int i:numGuestsPerCompany){
			System.out.println(i);
		}*/
		if(numTables*numSeats<guestObjs.size()){
			int differential = (guestObjs.size()-(numTables*numSeats));
			//System.out.println("ASDFJASLDIFGJSLGJLASKFGJLAKDFJG " + differential);
			//this is essentially going through and taking equal numbers of guests from every company, just starting with the biggest ones.
			for(int i=10; i>0; i--){ //seeing if any companies have ten, taking from them first, then nine, etc, etc
				int templen = numGuestsPerCompany.size();
				for(int a=0;a<templen;a++){ //looping through every company
					if(numGuestsPerCompany.get(a)==i){//if company happens to have high num of ppl
						numGuestsPerCompany.set(a, i-1);//decrease company's attendance by one
						for(Attendee att: guestObjs){//executing above statement
							if(att.getCoID()==a){//if we land on an attendee from right company
								if(att.getAttendance()==true){//if they're actually going
									att.setAttendance(false);
									differential-=1;;//forcing them to not go
									break;
								}
							}
						}
					}
					if(differential==0) break;					
				}
				if(differential==0) break;
			}
			if(differential!=0) System.out.println("Error: cannot sufficiently reduce guest list");
			/*for(int i:numGuestsPerCompany){
				System.out.println(i);
			}*/
			//THE ABOVE BLOCK ENSURES THAT THE REQUISITE DELETIONS ARE DONE MOST FAIRLY, WHERE COMPANIES WITH THE MOST ATTENDEES ARE PENALIZED FIRST BUT EQUALLY AMONGST THEMSELVES.
		}
	}
	public void enterManually(){//THIS MUST BE RUN BEFORE ENUMERATEGUESTS.
			System.out.println("\n\nWould you like to enter guests manually? (Y/N)\n");
			boolean tempbool = getYN();
			if(tempbool){
				String tempAttFN, tempAttLN;
				int tempCoID;
				for(;;){ //loop for adding people manually
					System.out.println("\nEnter the FIRST NAME of the attendee:\n");
					tempAttFN = univScan.nextLine();
					System.out.println("\nEnter the LAST NAME of the attendee:\n");
					tempAttLN = univScan.nextLine();
					System.out.println("\nEnter the COMPANY ID of the attendee:\n");
					for(;;){
					try{
						tempCoID = Integer.parseInt(univScan.nextLine());
						//System.out.println("ASdf");
						break;
					}
					catch(NumberFormatException e){
						System.out.println("\nFormat your response in an INTEGER:\n"); //companyID must be an integer for other parts of code to NOT throw this error, so catching it here
						continue;
					}
					}
					System.out.println("\nSave guest: " + tempAttFN + " " + tempAttLN + " [CompanyID=" + tempCoID + "]? (Y/N)\n");
					if(!getYN()) continue;
					int tempsize = guestObjs.size(); //for efficiency because used twice in following lines
					guestObjs.add(new Attendee(tempsize+1, tempAttFN, tempAttLN, tempCoID));
					System.out.print("\nSuccess! Guest added: " + guestObjs.get(tempsize).toString()+"\n");
					System.out.println("\nWould you like to enter another guest? (Y/N)\n");
					if(!getYN()) break;
				}	
			}
		System.out.println(guestObjs.get(guestObjs.size()-1).toString()); //LATEST ADDITION TO GUESTOBJS 
	}
	public void sortGuests(){} //LOL i wonder how many centuries until i'll be able to rewrite this
	public boolean getYN(){
		boolean temp; //just stores output of user so we can close the scanner to save memory
		for(;;){
			//System.out.println("tihgn");
			String response = univScan.nextLine();
			if(response.equals("Y")){
				temp = true;
				break;
			};
			if(response.equals("N")){
				temp = false;
				break;
			}
			System.out.println("\nPlease format your response Y/N:\n");
		}
		return temp;
	} //implements a quick scanner to get a y/n input from user - this is a separate method because of how often it was used
	
}
