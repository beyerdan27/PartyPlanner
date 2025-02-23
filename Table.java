import java.util.ArrayList;
public class Table{
    ArrayList<Attendee> tableRoster;
    int maxseats;
    public Table(int maxseatsA){
        tableRoster = new ArrayList<Attendee>();
        maxseats = maxseatsA;
    }
    public int getRemainingSeats(){
        return maxseats-tableRoster.size();
    }
    public int getPositionOfAttendeeAboutToBePlaced(){
        return tableRoster.size(); //positions will NOT be 0-indexed
    }
    public boolean addAttendee(Attendee att){
        if(tableRoster.size()<maxseats){
            tableRoster.add(att);
            return true;
        }
            return false;
    }
    public ArrayList<Attendee> getTableRoster(){
        return tableRoster;
    }
    public boolean isAttendeeHere(int attID){
        for(Attendee att:tableRoster){
            if(att.getAttID()==attID) return true;
        }
        return false;
    }
    public boolean isCompanyHere(int coID){
        for(Attendee att:tableRoster){
            if(att.getCoID()==coID) return true;
        }
        return false;
    }
}