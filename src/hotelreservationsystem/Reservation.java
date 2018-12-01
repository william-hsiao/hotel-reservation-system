package hotelreservationsystem;
import java.util.Date;
import java.util.UUID;

public class Reservation {
    String reservationID;
    Guest guest;
    Date checkIn;
    Date checkOut;
    int numGuests;
    Room room;
    double payment;
    String status;
    
    public Reservation (Guest guest, Date checkIn, Date checkOut, 
            int numGuests, double payment, Room room) {
        reservationID = UUID.randomUUID().toString();
        this.guest = guest;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numGuests = numGuests;
        this.room = room;
        this.payment = payment;
        this.status = "Incoming";
        room.reservations.add(this);
        guest.reservations.add(this);
    }
    
    public Reservation (String reservationID, Guest guest, Date checkIn, 
            Date checkOut, int numGuests, double payment, Room room, String status) {
        this.reservationID = reservationID;
        this.guest = guest;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.numGuests = numGuests;
        this.room = room;
        this.payment = payment;
        this.status = status;
        room.reservations.add(this);
        guest.reservations.add(this);
    }
}
