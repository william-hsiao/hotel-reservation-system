package hotelreservationsystem;
import java.util.ArrayList;
import java.util.Date;

public class GuestManager {
    ArrayList<Guest> guests = new ArrayList<>();
    int i;
    
    public GuestManager() {
        createGuest("John Smith", new Date(93, 5, 21), "0312345678", 
                "john.smith@email.com", "password");
    }
    
    public Guest getGuest() {
        return guests.get(0);
    }
    
    public Guest guestLogin(String email, String password) {
        for (i=0; i<guests.size(); i++) {
            if (guests.get(i).email.equals(email) && 
                    guests.get(i).password.equals(password)) {
                return guests.get(i);
            }
        }
        return null;
    }
    
    public void createGuest(String name, Date birthdate, String phoneNumber, 
            String email, String password) {
        guests.add(new Guest(name, birthdate, phoneNumber, email, password));
    }
    
    public Guest findGuest(String ID) {
        for (i=0; i<guests.size(); i++) {
            if (guests.get(i).id.equals(ID)) {
                return guests.get(i);
            }
        }
        return null;
    }
    
    public void updateGuest(Guest update) {
        for (i=0; i<guests.size(); i++) {
            if (guests.get(i).id.equals(update.id)) {
                guests.set(i, update);
            }
        }
    }
    
    public void deleteGuest(String ID) {
        for (i=0; i<guests.size(); i++) {
            if (guests.get(i).id.equals(ID)) {
                guests.remove(i);
            }
        }
    }
    
    public Date StringToDate(String string) {
        String[] temp = string.split("/");
        return new Date(Integer.parseInt(temp[0])-1900, Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
    }
}
