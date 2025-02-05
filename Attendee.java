/**
 * Attendee.java class for creating attendee objects in PartyPlanner
 * Preconditions: file to import attendees - .txt or .csv
 * Postconditions: generates an attendee object with name, tableID, tablePos, and coID
 * @param fnameInp first name of the attendee
 * @param lnameInp last name of the attendee
 * @param coIDInp company ID of attendee. Indexes of each company stored in an ArrayList, in Party class
 * @param tableIDA current tableID of attendee
 * @param tablePosA current table position of attendee
 */
public class Attendee{
	private int coID, tableID, tablePos;
	private String name, fname, lname;
	public Attendee(String fnameInp, String lnameInp, int coIDInp){
		name = fnameInp + lnameInp;
		fname = fnameInp;
		lname = lnameInp;
		coID = coIDInp;
		tableID = -1;
		tablePos = -1;
	}
	public Attendee(String fnameInp, String lnameInp, int coIDInp, int tableIDA, int tablePosA){
		name = fnameInp + lnameInp;
		fname = fnameInp;
		lname = lnameInp;
		coID = coIDInp;
		tableID = tableIDA;
		tablePos = tablePosA;
	}
	public int getTableID(){
		return tableID;
	}
	public void setTableID (int tableIDA){
		tableID = tableIDA;
	}
	public int getTablePos(){
		return tablePos;
	}
	public void setTablePos(int tablePosA){
		tablePos = tablePosA;
	}
	public int getCoID(){
		return coID;
	}
	public void setCoID(int coIDA){
		coID = coIDA;
	}
	public String toString(){
		return lname + ", " + fname + ", Company: " + coID + ", Location: Table " + tableID + " Position " + tablePos;
	}
	//INCOMPLETE
}
