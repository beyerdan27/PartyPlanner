import java.util.ArrayList;
public class Tester{
	public static void main(String[] args){
		ArrayList<Attendee> attList = new ArrayList<Attendee>();
		//just adding the participants
		/*for(Attendee att:attList){ //testing the toString(); function for every attendee object - and ID of -1 means undefined or not set yet
			System.out.println(att.toString());
		}
		
		for(Attendee att:attList){ //searching for company two: in this case it's John Smith and Vladiimir Putin
			if(att.getCoID() == 2) System.out.println("\n\nCOMPANY ID TWO: " + att.toString());
		}*/
		Party temp = new Party(10, 10);
		temp.loadData();
		temp.enumerateGuests();
		temp.enterManually();
		/*for(int c:temp.enumerateGuests()){
			System.out.println(c);
		}*/
	}
}
