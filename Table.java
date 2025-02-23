/**
 * Table.java class to help organize tables during the sorting process and roster printing process
 * Preconditions: party.java has already run enumguests, loaddata, entermanually;
 * Postconditions: generates organized tables which are used to visualize the roster later and are useful for the sorting process
 * @param maxseatsA The maximum number of seats at a table object, passed in as numSeats in Party.java
 */
import java.util.ArrayList;
public class Table{
    ArrayList<Attendee> tableRoster;
    int maxseats;
    public Table(int maxseatsA){
        tableRoster = new ArrayList<Attendee>();
        maxseats = maxseatsA;
    }
    public int getRemainingSeats(){//useful when cramming in the last handful of guests to any available seats in Party.java
        return maxseats-tableRoster.size();
    }
    public int getPositionOfAttendeeAboutToBePlaced(){//gets the Position Of Attendee About To Be Placed
        return tableRoster.size(); //positions will NOT be 0-indexed, that would be a terrible idea
    }
    public boolean addAttendee(Attendee att){//simply adds an attendee if there's room in the table
        if(tableRoster.size()<maxseats){
            tableRoster.add(att);
            return true;
        }
            return false;
    }
    public ArrayList<Attendee> getTableRoster(){//returns arraylist of roster, helpful when printing it out
        return tableRoster;
    }
    public boolean isAttendeeHere(int attID){//never used, but here in case it might ever be helpful
        for(Attendee att:tableRoster){
            if(att.getAttID()==attID) return true;
        }
        return false;
    }
    public boolean isCompanyHere(int coID){//extremely helpful, allows sorter to see if anyone from the company is already here
        for(Attendee att:tableRoster){
            if(att.getCoID()==coID) return true;
        }
        return false;
    }
}