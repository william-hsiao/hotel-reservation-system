package hotelreservationsystem;
import java.util.Calendar;
import java.util.Date;

public class ClientStaffProcessor {
    RoomManager roomManager;
    ReservationManager reservationManager;
    GuestManager guestManager;
    Guest guest;
    
    public ClientStaffProcessor (RoomManager roomManager, 
            ReservationManager reservationManager, 
            GuestManager guestManager) {
        this.roomManager = roomManager;
        this.reservationManager = reservationManager;
        this.guestManager = guestManager;
        new ClientStaffReservations(this);
    }

    
    public Reservation searchReservation(String reservationID) {
        try{
            return reservationManager.findReservation(reservationID);
            
        }
        catch(Exception err){
            System.out.println(err);
            return null;
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
