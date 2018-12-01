package hotelreservationsystem;
import java.util.Calendar;
import java.util.Date;

public class ClientGuestProcessor {
    RoomManager roomManager;
    ReservationManager reservationManager;
    GuestManager guestManager;
    Guest guest;
    
    public ClientGuestProcessor (RoomManager roomManager, 
            ReservationManager reservationManager, 
            GuestManager guestManager) {
        this.roomManager = roomManager;
        this.reservationManager = reservationManager;
        this.guestManager = guestManager;
        new ClientGuestLogin(this);
    }
    
    public int submitLogin (String username, String password) {
        guest = guestManager.guestLogin(username, password);
        if (guest != null) {
            new ClientGuestReservations(guest, this);
            return 1;
        }
        else {
            System.out.println("EMPTY");
            return 0;
        }
    }
    
    public int submitRegistration (String name, String birthday, String phoneNumber, String email, String password) {
        try {
            guestManager.createGuest(name, guestManager.StringToDate(birthday), phoneNumber, email, password);
            return 1;
        } catch (Exception err) {
            System.out.println(err);
            return 0;
        }
    }
    
    public int submitReservationQuery (String checkIn, String checkOut, int numGuests, String roomType) {
        try {
            Date checkInDate = StringToDate(checkIn);
            Date checkOutDate = StringToDate(checkOut);
            Room room = roomManager.getRoom(roomType, checkInDate, checkOutDate);
            Double payment = roomManager.getPayment(room, checkInDate, checkOutDate);
            new ClientGuestConfirmReservation(guest, DateToString(checkInDate),
                    DateToString(checkOutDate),
                    numGuests,
                    roomType, room, payment, this);
            return 1;
        } catch (Exception err) {
            System.out.println(err);
            return 0;
        }
    }
    
    public int submitReservation (String checkIn, String checkOut, int numGuests, Double payment, Room room) {
        try {
            Date checkInDate = StringToDate(checkIn);
            Date checkOutDate = StringToDate(checkOut);
            reservationManager.createReservation(
                    guest, checkInDate, checkOutDate, numGuests, payment, room);
            return 1;
        } catch (Exception err) {
            System.out.println(err);
            return 0;
        } 
    }
    
    public int submitReservationUpdate (Reservation reservation, Date checkIn, Date checkOut, 
            int numGuests, String status) {
        try{
            reservationManager.updateReservation(reservation,
                    checkIn, checkOut, numGuests, status);
            return 1;
        }
        catch(Exception err){
            System.out.println(err);
            return 0;
        }
    }
    
    public int submitReservationDelete (String reservationID) {
        try {
            guest.removeReservation(reservationID);
            reservationManager.deleteReservation(reservationID);
            return 1;
        }
        catch (Exception err) {
            System.out.println(err);
            return 0;
        }
    
    }
    
    public String DateToString(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String temp = "";
        temp += cal.get(Calendar.YEAR) + "/";
        temp += cal.get(Calendar.MONTH) + "/";
        temp += cal.get(Calendar.DAY_OF_MONTH);
        return temp;
    }
    
    public Date StringToDate(String string) {
        String[] temp = string.split("/");
        return new Date(Integer.parseInt(temp[0])-1900, Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
    }
}
