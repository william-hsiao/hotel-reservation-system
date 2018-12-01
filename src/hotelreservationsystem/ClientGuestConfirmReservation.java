package hotelreservationsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Date;

public class ClientGuestConfirmReservation extends JFrame {
    public ClientGuestConfirmReservation (Guest guest, String checkIn, 
            String checkOut, int numGuests, String roomType, Room room, Double payment, ClientGuestProcessor processor) {
        setSize(500,500);
        setTitle("New Reservation");
        MyJPanel myJPanel = new MyJPanel(guest, checkIn, checkOut,
                numGuests, roomType, room, payment, processor);
        Container c = getContentPane();
        c.add(myJPanel);
        setVisible(true);
    }
    
    public class MyJPanel extends JPanel implements ActionListener {
        Guest guest;
        ClientGuestProcessor processor;
        String checkIn, checkOut;
        int numGuests;
        String roomType;
        double payment;
        Room room;
        
        JLabel Lbl_checkIn, Lbl_checkOut, Lbl_numGuests, Lbl_roomType, 
                Lbl_payment, Lbl_error;
        JButton Btn_confirm;
        
        public MyJPanel(Guest guest, String checkIn, String checkOut, 
                int numGuests, String roomType, Room room, Double payment, ClientGuestProcessor processor) {
            this.guest = guest;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.numGuests = numGuests;
            this.processor = processor;
            this.roomType = roomType;
            this.room = room;
            this.payment = payment;
            
            
            
            Lbl_checkIn = new JLabel("Check-In: "+checkIn);
            Lbl_checkOut = new JLabel("Check-Out: "+checkOut);
            Lbl_numGuests = new JLabel("Number of Guests: "+numGuests);
            Lbl_roomType = new JLabel("Room Type: "+roomType);
            Lbl_payment = new JLabel("Payment (USD): "+payment);
            Lbl_error = new JLabel("");
            Btn_confirm = new JButton("Confirm");
            
            add(Lbl_checkIn);
            add(Lbl_checkOut);
            add(Lbl_numGuests);
            add(Lbl_roomType);
            add(Lbl_payment);
            add(Btn_confirm);
            Btn_confirm.addActionListener(this);
            add(Lbl_error);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Btn_confirm) {
                Lbl_error.setText("");
                if (processor.submitReservation(
                            checkIn, checkOut, numGuests, payment, room) == 1) {
                    dispose();
                } else {
                    Lbl_error.setText("Error");
                }
                
            }
            
        }
        
    }
}
