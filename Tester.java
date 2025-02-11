import java.util.ArrayList;
public class Tester{
	public static void main(String[] args){
		ArrayList<Attendee> attList = new ArrayList<Attendee>();
		//just adding the participants
		Attendee att1 = new Attendee("John", "Smith", 2);
		attList.add(att1);
		Attendee att2 = new Attendee("Daniel", "Beyerbach", 1, 5, 1);
		attList.add(att2);
		Attendee att3 = new Attendee("Luigi", "Mangione", 3);
		attList.add(att3);
		Attendee att4 = new Attendee("Vladimir", "Putin", 2, 9, 12);
		attList.add(att4);
		Attendee att5 = new Attendee("Kim", "Jong-Un", 16);
		attList.add(att5);
		
		/*for(Attendee att:attList){ //testing the toString(); function for every attendee object - and ID of -1 means undefined or not set yet
			System.out.println(att.toString());
		}
		
		for(Attendee att:attList){ //searching for company two: in this case it's John Smith and Vladiimir Putin
			if(att.getCoID() == 2) System.out.println("\n\nCOMPANY ID TWO: " + att.toString());
		}*/
		Party temp = new Party(10, 10);
		temp.loadData();
		temp.enumerateGuests();
		/*for(int c:temp.enumerateGuests()){
			System.out.println(c);
		}*/
	}
}
