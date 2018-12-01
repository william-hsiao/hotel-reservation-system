package hotelreservationsystem;

public class HotelReservationSystem {
    static RoomManager roomManager = new RoomManager();
    static GuestManager guestManager = new GuestManager();
    static ReservationManager reservationManager = new ReservationManager(roomManager, guestManager);
    
    public static void main(String[] args) {
        // TODO code application logic here
        new ClientGuestProcessor(roomManager, reservationManager, guestManager);
        new ClientStaffProcessor(roomManager, reservationManager, guestManager);
    }
}
