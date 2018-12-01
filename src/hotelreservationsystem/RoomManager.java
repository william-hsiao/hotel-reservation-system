package hotelreservationsystem;
import java.util.Date;
import java.util.ArrayList;

public class RoomManager {
    int numRooms = 10, i, j;
    Room[] rooms = new Room[numRooms];
    
    public RoomManager() {
        rooms[0] = new Room("Single", 101, 80);
    }
    
    public ArrayList<Room> checkAvailability (String roomType, Date checkIn, Date checkOut) {
        ArrayList<Room> availableRooms = new ArrayList<>();
        
        for (i=0; i<numRooms; i++) {
            if (rooms[i].type.equals(roomType)) {
                boolean flag = true;
                for (j=0; j<rooms[i].reservations.size(); j++) {
                    if (rooms[i].reservations.get(j).checkOut.compareTo(checkIn) > 0
                            && rooms[i].reservations.get(j).checkIn.compareTo(checkOut) < 0) {
                        flag = false;
                    }
                }
                if (flag) availableRooms.add(rooms[i]);
            }
        }
        
        return availableRooms;
    }
    
    public Room getRoom(String roomType, Date checkIn, Date checkOut) {
        for (i=0; i<numRooms; i++) {
            if (rooms[i].type.equals(roomType)) {
                boolean flag = true;
                for (j=0; j<rooms[i].reservations.size(); j++) {
                    if (rooms[i].reservations.get(j).checkOut.compareTo(checkIn) > 0
                            && rooms[i].reservations.get(j).checkIn.compareTo(checkOut) < 0) {
                        flag = false;
                    }
                }
                if (flag) return rooms[i];
            }
        }
        return null;
    }
    
    public double getPayment(Room room, Date checkIn, Date checkOut) {
        return (checkOut.getTime() - checkIn.getTime())/(1000*60*60*24)
                * room.rate;
    }
}
