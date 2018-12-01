package hotelreservationsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClientGuestNewReservation extends JFrame {
    public ClientGuestNewReservation (Guest guest, 
            ClientGuestProcessor processor) {
        setSize(355,180);
        setTitle("New Reservation");
        MyJPanel myJPanel = new MyJPanel(guest, processor);
        Container c = getContentPane();
        c.add(myJPanel);
        setVisible(true);
    }
    
    public class MyJPanel extends JPanel implements ActionListener {
        Guest guest;
        ClientGuestProcessor processor;
        JLabel Lbl_checkIn, Lbl_checkOut, Lbl_numGuests, Lbl_roomType, Lbl_error;
        JTextField TF_checkIn, TF_checkOut, TF_numGuests;
        JComboBox CB_roomType;
        JButton Btn_register;
        
        public MyJPanel(Guest guest, ClientGuestProcessor processor) {
            this.guest = guest;
            this.processor = processor;
            String roomTypes[] = {"Single", "Double"};
            
            Lbl_checkIn = new JLabel("Check-In (YYYY/MM/DD):   ");
            Lbl_checkOut = new JLabel("Check-Out (YYYY/MM/DD):  ");
            Lbl_numGuests = new JLabel("Number of Guests:  ");
            Lbl_roomType = new JLabel("Room Type:   ");
            TF_checkIn = new JTextField("", 15);
            TF_checkOut = new JTextField("", 15);
            TF_numGuests = new JTextField("", 5);
            CB_roomType = new JComboBox(roomTypes);
            Btn_register = new JButton("Register");
            Lbl_error = new JLabel("");
            
            add(Lbl_checkIn);
            add(TF_checkIn);
            add(Lbl_checkOut);
            add(TF_checkOut);
            add(Lbl_numGuests);
            add(TF_numGuests);
            add(Lbl_roomType);
            add(CB_roomType);
            add(Btn_register);
            Btn_register.addActionListener(this);
            add(Lbl_error);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Btn_register) {
                Lbl_error.setText("");
                if (processor.submitReservationQuery(
                        TF_checkIn.getText(),
                        TF_checkOut.getText(),
                        Integer.parseInt(TF_numGuests.getText()),
                        String.valueOf(CB_roomType.getSelectedItem())) == 1) {
                    dispose();
                } 
                else {
                    Lbl_error.setText("Error");
                }
            }
        }
    }
}
