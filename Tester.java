/**
 * Tester.java class for testing functionality of partyplanner
 * Preconditions: partyguests.txt and companies.txt exist in same directory
 * Postconditions: successful test of partyplanner
 */
public class Tester{
	public static void main(String[] args){
		Party temp = new Party(10, 10);
		temp.loadData(); //loads file data, must be done before anything else
		temp.enterManually(); //adds additional data to the pile, manual user input
		temp.enumerateGuests(); //this part throws a fit if any company broke the rules and it restricts guests fairly so that the party can be full but not overbooked
		temp.sortGuests(); //sorts guests into tables, self-explanatory
		temp.enterFindMode(); //allows user to find any guest either by name or attendee ID
		temp.enterPrintingMode(); //allows user to print a roster either by table or by company
	}
}
