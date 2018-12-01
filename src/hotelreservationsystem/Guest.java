package hotelreservationsystem;
import java.util.Date;
import java.util.UUID;
import java.util.ArrayList;

public class Guest {
    String name;
    String id;
    Date birthday;
    String phoneNumber;
    String email;
    String password;
    ArrayList<Reservation> reservations = new ArrayList<>();
    
    public Guest (String name, Date birthday, String phoneNumber, String email, String password) {
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
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
