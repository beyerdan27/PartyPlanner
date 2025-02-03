/*
 * Attendee.java class for creating attendee objects in PartyPlanner
 * Preconditions: file to import attendees - .txt or .csv
 * Postconditions: generates an attendee object with name, tableID, tablePos, and coID
 * @param nameInp name of the attendee, formatted: firstname lastname
 * @param coIDInp company ID of attendee. Indexes of each company stored in an ArrayList, in Party class
 */
public class Attendee{
	private int coID, tableID, tablePos;
	private String name;
	public Attendee(String nameInp, int coIDInp){
		name = nameInp;
		coID = coIDInp;
		tableID = -1;
		tablePos = -1;
	}
	public int getTableID(){
		return tableID;
	}
	public int getTablePos(){
		return tablePos;
	}
	public int getCoID(){
		return coID;
	}
	//INCOMPLETE
}
