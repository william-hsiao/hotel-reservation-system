package hotelreservationsystem;
import java.util.ArrayList;

public class Room {
    String type;
    int roomNum;
    ArrayList<Reservation> reservations = new ArrayList<>();
    double rate;
    
    public Room (String type, int roomNum, double rate) {
        this.type = type;
        this.roomNum = roomNum;
        this.rate = rate;
    }
    
    public void removeReservation (String ID) {
        int i;
        for (i=0; i<reservations.size(); i++) {
            if (reservations.get(i).reservationID.equals(ID)) {
                reservations.remove(i);
            }
        }
    }
}
