package hotelreservationsystem;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ReservationManager {
    RoomManager roomManager;
    ArrayList<Reservation> reservations = new ArrayList<>();
    int i;
    
    public ReservationManager (RoomManager roomManager, GuestManager guestManager) {
        this.roomManager = roomManager;
        createReservation(guestManager.getGuest(), new Date(118,1,15), 
                new Date(118,1,18), 1, 400, roomManager.getRoom("Single",  
                        new Date(118,1,15), new Date(118,1,18)));
    }
    
    public void createReservation(Guest guest, Date checkIn, Date checkOut,
           int numGuests, double payment, Room room) {        
        reservations.add(new Reservation(guest, checkIn, checkOut, numGuests, payment, room));
        room.reservations.add(reservations.get(reservations.size()-1));
    }
    
    public Reservation findReservation(String ID) {
        for (i=0; i<reservations.size(); i++) {
            if (reservations.get(i).reservationID.equals(ID)) {
                return reservations.get(i);
            }
        }
        return null;
    }
    
    public void updateReservation(Reservation reservation, Date checkIn, 
            Date checkOut, int numGuests, String status) {
        for (i=0; i<reservations.size(); i++) {
            if (reservations.get(i).reservationID.equals(reservation.reservationID)) {
                reservations.get(i).checkIn = checkIn;
                reservations.get(i).checkOut = checkOut;
                reservations.get(i).numGuests = numGuests;
                reservations.get(i).status = status;
                reservations.get(i).payment = roomManager.getPayment(reservation.room, checkIn, checkOut);
                break;
            }
        }
    }
    
    public void deleteReservation(String ID) {
        for (i=0; i<reservations.size(); i++) {
            if (reservations.get(i).reservationID.equals(ID)) {
                reservations.get(i).room.removeReservation(ID);
                reservations.remove(i);
            }
        }
    }
}
