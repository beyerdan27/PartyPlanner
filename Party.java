/**
 * Party.java class for managing party logic in PartyPlanner
 * Preconditions: partyguests.txt and companies.txt files to import
 * Postconditions: loaded party guests, sorted at each table, allows user to interact and find individual guests and print rosters
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
	private ArrayList<ArrayList<String>> guests = new ArrayList<ArrayList<String>>(); //temporary and only used once for .split()
	private ArrayList<Attendee> guestObjs = new ArrayList<Attendee>();
	private ArrayList<Integer> numGuestsPerCompany = new ArrayList<Integer>();
	private ArrayList<Table> tableObjs = new ArrayList<Table>();
	private int numCompanies; //dont know why this isnt used
	Scanner univScan;
	public Party(int numTablesA, int numSeatsA){ //constructing the party, like it's a party planner
		numTables = numTablesA;
		numSeats = numSeatsA;
		numGuestsPerCompany.add(-1); //to make it so i dont have to +1 index all .get() calls to NGPC
		univScan = new Scanner(System.in); //yes it is never closed, but trying to use individual scanners for every user input would be a traumatic experience and would definitely never work
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
						"|_|    |______/_/    \\_\\_| \\_|_| \\_|______|_|  \\_\\"); //this was formatted by VSCode from a paste, which is pretty cool, i didn't know it could do that
		System.out.println("\nLet's plan a party!");
	}
	public int loadData(){ //parses every file necesssary and sets all vars related to that, run before enumerateGuests()
		try{
			File companies = new File("companies.txt");
			Scanner reader1 = new Scanner(companies);
			while(reader1.hasNextLine()){
				String temp = reader1.nextLine();
				if(temp.length()>0){
					companyIDs.add(temp.substring(3)); //3 is where the company name begins - this list stores each company name, the index is the company ID
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
					guests.add(templist); //arraylist used for .split(), copied into objects later
				}
			}
			reader2.close();
		}
		catch(FileNotFoundException e){
			return 404;
		}
		for(String s:companyIDs) numGuestsPerCompany.add(0); //filling it with zeroes to avoid IndexOutOfBoundsException, actually filled in at EnumGuests
		for(ArrayList<String> templist:guests){ //converting all guest lists into actual objects which are used for the rest of the program - the reason lists were used in the first place was to to utilize the .split() method
			guestObjs.add((new Attendee(Integer.parseInt(templist.get(0)), templist.get(1), templist.get(2), Integer.parseInt(templist.get(3))))); //PICK UP HERE
		}
		/*for(Attendee att:guestObjs){
			System.out.println(att.toString());
		}*/
		return 200;
	}
	public void enumerateGuests(){ //this part whines if any of the math wont work out, so run this before sortGuests to avoid errors, (it also eliminates excess guests)
		for(Attendee att:guestObjs){
			int currentCompanyIDBeingCounted = att.getCoID();
			int tempct = numGuestsPerCompany.get(currentCompanyIDBeingCounted); 
			if(tempct == 10){ //if 10 guests from that company are already going
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
			int differential = (guestObjs.size()-(numTables*numSeats)); //number of guests by which roster must be reduced
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
									differential-=1;//forcing them to not go
									break;
								}
							}
						}
					}
					if(differential==0) break; //honestly have no clue why this is here the code just breaks if i remove it				
				}
				if(differential==0) break;
			}
			if(differential!=0) System.out.println("Error: cannot sufficiently reduce guest list"); //should never happen in theory but just in case
			/*for(int i:numGuestsPerCompany){
				System.out.println(i);
			}*/
			//THE ABOVE BLOCK ENSURES THAT THE REQUISITE DELETIONS ARE DONE MOST FAIRLY, WHERE COMPANIES WITH THE MOST ATTENDEES ARE PENALIZED FIRST BUT EQUALLY AMONGST THEMSELVES.
		}
	}
	public void enterManually(){//THIS MUST BE RUN BEFORE ENUMERATEGUESTS. - EnterManually allows user to contribute to the collection of attendee data BEFORE it is sorted
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
					System.out.println("\nEnter the COMPANY ID of the attendee:\n"); //This trusts the user to enter a valid ID
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
		//System.out.println(guestObjs.get(guestObjs.size()-1).toString()); //LATEST ADDITION TO GUESTOBJS for debugging
	}
	public void sortGuests(){
		//if run after enumguests, this should produce no errors (haha funny right)
		//places largest companies first in line to be sorted
		//algo rotates starting table each time, helps it run smoother
		int startingTableIndex=0; //indicates table at which populating will begin for any company, rotating right
		int currentPopulatingIndex;
		int guestsRemaining;
		Table currentTable;
		Attendee currentAttendee;
		for(int i=0; i<numTables; i++) tableObjs.add(new Table(numSeats)); //populate array with tables
		for(int i=10;i>0;i--){//actually populating indiv. tables, one company at a time and starting with largest //\\ USE I AS COMPANY SIZE
			for(int a=0;a<numCompanies;a++){ //looping through every company //\\ USE VAR A AS COMPANYID
				if(numGuestsPerCompany.get(a)==i){
					//populating an entire company:
					currentPopulatingIndex = startingTableIndex;
					guestsRemaining = i;
					while(guestsRemaining>0){
						currentTable = tableObjs.get(currentPopulatingIndex);
						//GETTING A GUEST TO PLACE, checking for if already placed and coid matches int a

						//This value of currentAttendee will ALWAYS be changed, but this is so the compiler doesnt throw a temper tantrum because it doesnt understand that
						currentAttendee = guestObjs.get(0);
						//this downselects currentattendee based on if theyre attending, already placed or not, and the right company id
						for(Attendee att:guestObjs){
							if(att.getCoID()==a&&(att.getTableID()<0&&att.getAttendance())){
								currentAttendee=att;
								break;
							}
						}
						if((!currentTable.isCompanyHere(a))&&currentTable.getRemainingSeats()>0){//just checking to make sure something else didnt go horribly wrong and that the tables not full
							currentTable.addAttendee(currentAttendee);
							currentAttendee.setTableID(currentPopulatingIndex+1); //tableID will NOT be 0-indexed
							currentAttendee.setTablePos(currentTable.getPositionOfAttendeeAboutToBePlaced());
							guestsRemaining-=1;
						}
						if(currentPopulatingIndex>=numSeats-1){ //rotating right
							currentPopulatingIndex=0;
						} else {
							currentPopulatingIndex++;
						}
					}
					//Rotate starting index right
					if(startingTableIndex>=numSeats-1){ 
						startingTableIndex=0;
					} else {
						startingTableIndex++;
					}
				}
			}
		}
		int tempasdf=0;
		for(Attendee att:guestObjs){
			if(att.getAttendance()) tempasdf++;
		}
		System.out.println("\nSuccessfully sorted " + tempasdf + " of " + guestObjs.size() + " guests!\n");
	}
	public boolean getYN(){ //implements a quick scanner to get a y/n input from user - this is a separate method because of how often it was used
		boolean temp;
		for(;;){
			//System.out.println("tihgn");
			String response = univScan.nextLine();
			if(response.equals("Y")){
				temp = true;
				break;
			};
			if(response.equals("N")){
				temp = false;
				break; //this is the stupidest possible way to do it I'm sure
			}
			System.out.println("\nPlease format your response Y/N:\n");
		}
		return temp;
	}
	public void findAttendeeByID(int id){//just loops through guestObjs until finds the ID, yes it could be more efficient, but don't want it to break if partyguests.txt isn't sorted
		if(id<0||id>guestObjs.size()){
			System.out.println("\nInvalid ID.\n");
			return;
		}
		System.out.println("\n"+guestObjs.get(id-1).toString()+"\n");
	}
	public void findAttendeeByName(String fname, String lname){ //same thing, just loops until it finds the name of attendee and toString()s it
		for(Attendee att:guestObjs){
			if(att.getFName().equals(fname)&&att.getLName().equals(lname)){
				System.out.println("\n"+att.toString()+"\n");
				return;
			}
		}
		System.out.println("\nAttendee not found.\n");
	}
	public void enterFindMode(){//allows user to run the toString() on any user they "find" using above methods, this just implements it with user input control and flow
		System.out.println("\nWould you like to find any attendees manually before printing rosters? (Y/N)\n");
		boolean tempbool = getYN();
		if(tempbool){
			for(;;){//loop for finding ppl, prompts for find type each time
				System.out.println("\nFind by name rather than Attendee ID? (Y/N)\n");
				boolean nameMode = getYN();
				String tempAttFN2, tempAttLN2;
				int tempAttID;
				if(nameMode){
				System.out.println("\nEnter the FIRST NAME of the attendee:\n");
				tempAttFN2 = univScan.nextLine();
				System.out.println("\nEnter the LAST NAME of the attendee:\n");
				tempAttLN2 = univScan.nextLine();
				findAttendeeByName(tempAttFN2, tempAttLN2);
				} else {
					System.out.println("\nEnter the ID of the attendee:\n");
					for(;;){
						try{
							tempAttID = Integer.parseInt(univScan.nextLine());
							break;
						}
						catch(NumberFormatException e){
							System.out.println("\nFormat your response in an INTEGER:\n"); //must be an int because findbyID will cry and throw an error if not
							continue;
						}
					}
					findAttendeeByID(tempAttID);	
				}
				
				System.out.println("\nWould you like to find another attendee? (Y/N)\n");
				if(!getYN()) break;
			}	
		}
	}
	public void printRosterByTable(){//loops through all the tables and displays a toString() for each guest
		for(int i=0; i<numTables; i++){
			System.out.println("\nTable " + (i+1) + ":\n");
			for(Attendee att:tableObjs.get(i).getTableRoster()) System.out.println(att.toString());
		}
	}
	public void printRosterByCompany(){//same thing as above, just by company, and this time it has to check it guest is attending or not
		for(int i=1; i<=numCompanies;i++){
			System.out.println("\nCompany " + (i) + " (" + companyIDs.get(i-1) + "):\n");
			for(Attendee att:guestObjs){
				if(att.getCoID()==(i)&&att.getAttendance()) System.out.println(att.toString());
			}
		}
	}
	public void enterPrintingMode(){//implements both above methods with user input control and flow
		System.out.println("\nWould you like to print a roster? (Y/N)\n");
		boolean tempbool = getYN();
		if(tempbool){
			for(;;){//loop for printing rosters, as many as you like
				System.out.println("\nPrint roster by TABLE rather than by COMPANY? (Y/N)\n");
				boolean nameMode = getYN();
				System.out.println("\nPrinting mode only displays attendees which were successfully sorted.\n");
				if(nameMode){
				printRosterByTable();
				} else {
				printRosterByCompany();
				}
				
				System.out.println("\nWould you like to print another roster? (Y/N)\n");
				if(!getYN()) break;
			}	
		}		
	}
}
