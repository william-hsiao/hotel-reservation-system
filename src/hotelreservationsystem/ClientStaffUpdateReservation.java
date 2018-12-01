package hotelreservationsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClientStaffUpdateReservation extends JFrame {
    public ClientStaffUpdateReservation (Reservation reservation,
            ClientStaffProcessor processor) {
        setSize(320,300);
        setTitle("Reservation");
        MyJPanel myJPanel = new MyJPanel(reservation, processor);
        Container c = getContentPane();
        c.add(myJPanel);
        setVisible(true);
    }
    
    public class MyJPanel extends JPanel implements ActionListener {
        ClientStaffProcessor processor;   
        Reservation reservation;
        JTextField TF_test, TF_reservationID, TF_guestname, TF_guestBD, TF_checkIn, TF_checkOut, TF_numGuests, TF_roomType, TF_roomNum, TF_payment, TF_status;
        JLabel Lbl_test, Lbl_reservationID, Lbl_guestname, Lbl_guestBD, Lbl_checkIn, Lbl_checkOut, Lbl_numGuests, Lbl_roomType, Lbl_roomNum, Lbl_payment, Lbl_status;
        JButton Btn_update;
        
         public MyJPanel(Reservation reservation, ClientStaffProcessor processor) {
            this.processor = processor;
            this.reservation = reservation;
            
            Lbl_reservationID = new JLabel("Reservation ID: ");
            Lbl_guestname = new JLabel("Guest: ");
            Lbl_guestBD = new JLabel("Birthday: ");
            Lbl_checkIn = new JLabel("Check In: ");
            Lbl_checkOut = new JLabel("Check Out: ");
            Lbl_numGuests = new JLabel("Number of Guests: ");
            Lbl_roomType = new JLabel(" Room Type: ");
            Lbl_roomNum = new JLabel("Room #: ");
            Lbl_payment = new JLabel("Payment: ");
            Lbl_status = new JLabel("Status: ");
            
            TF_reservationID = new JTextField(reservation.reservationID,18);
            TF_guestname = new JTextField(reservation.guest.name,22);
            TF_guestBD = new JTextField(processor.DateToString(reservation.guest.birthday),20);
            TF_checkIn = new JTextField(processor.DateToString(reservation.checkIn),20);
            TF_checkOut = new JTextField(processor.DateToString(reservation.checkOut),19);
            TF_numGuests = new JTextField(""+reservation.numGuests, 2);
            TF_roomType = new JTextField(reservation.room.type, 5);
            TF_roomNum = new JTextField(""+reservation.room.roomNum, 5);
            TF_payment = new JTextField(""+reservation.payment, 8);
            TF_status = new JTextField(reservation.status, 20);
                    
            Btn_update = new JButton("Update");
            Btn_update.addActionListener(this);
            
            add(Lbl_reservationID);
            add(TF_reservationID);
            add(Lbl_guestname);
            add(TF_guestname);
            add(Lbl_guestBD);
            add(TF_guestBD);
            add(Lbl_checkIn);
            add(TF_checkIn);
            add(Lbl_checkOut);
            add(TF_checkOut);
            add(Lbl_numGuests);
            add(TF_numGuests);
            add(Lbl_roomType);
            add(TF_roomType);
            add(Lbl_roomNum);
            add(TF_roomNum);
            add(Lbl_payment);
            add(TF_payment);
            add(Lbl_status);
            add(TF_status);
            add(Btn_update);
        }
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==Btn_update){
                if (processor.submitReservationUpdate(reservation,
                            processor.StringToDate(TF_checkIn.getText()), 
                            processor.StringToDate(TF_checkOut.getText()),
                            Integer.parseInt(TF_numGuests.getText()),
                            reservation.status) == 1) {
                    TF_payment.setText(String.valueOf(reservation.payment));
                }
            }
        }
    }
}
