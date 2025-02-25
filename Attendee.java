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
	private int coID, tableID, tablePos, attID;
	private String name, fname, lname;
	private boolean isAttending;
	public Attendee(int attIDInp, String fnameInp, String lnameInp, int coIDInp){//sets all instance vars of attendee
		name = fnameInp + lnameInp;
		fname = fnameInp;
		lname = lnameInp;
		coID = coIDInp;
		tableID = -1;
		tablePos = -1;
		attID = attIDInp;
	}
	public Attendee(int attIDInp, String fnameInp, String lnameInp, int coIDInp, int tableIDA, int tablePosA){//sets all instance vars of attendee, plus tableID and tablePos if a human needed to intervene in the sorting process
		name = fnameInp + lnameInp;
		fname = fnameInp;
		lname = lnameInp;
		coID = coIDInp;
		tableID = tableIDA;
		tablePos = tablePosA;
		attID = attIDInp;
	}
	//all the mutators/accessors, some are never even used, but they're here just in case
	public int getAttID(){
		return attID;
	}
	public String getFName(){
		return fname;
	}
	public String getLName(){
		return lname;
	}
	public String getName(){
		return name;
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
	public void setAttendance(boolean attA){
		isAttending = attA;
	}
	public boolean getAttendance(){
		return isAttending;
	}
	public void setTablePos(int tablePosA){
		tablePos = tablePosA;
	}
	public int getCoID(){
		return coID;
	}
	public int getID(){
		 return attID;
	 }
	public void setCoID(int coIDA){
		coID = coIDA;
	}
	public void setID(int IDA){
		 attID = IDA;
	 }
	public String toString(){//my favorite function in this whole project
		return lname + ", " + fname + ", Company: " + coID + ", Location: Table " + tableID + " Position " + tablePos;
	}
}
