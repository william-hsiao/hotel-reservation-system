package hotelreservationsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClientGuestUpdateReservation extends JFrame {
    public ClientGuestUpdateReservation (Reservation reservation,
            ClientGuestProcessor processor) {
        setSize(320,250);
        setTitle("Reservation");
        MyJPanel myJPanel = new MyJPanel(reservation, processor);
        Container c = getContentPane();
        c.add(myJPanel);
        setVisible(true);
    }
    
    public class MyJPanel extends JPanel implements ActionListener {
        Reservation reservation;
        ClientGuestProcessor processor;
        JTextField TF_test, TF_reservationID, TF_checkIn, TF_checkOut, TF_numGuests, TF_roomType, TF_payment;
        JLabel Lbl_test, Lbl_reservationID, Lbl_checkIn, Lbl_checkOut, Lbl_numGuests, Lbl_roomType, Lbl_payment;
        JButton Btn_update, Btn_delete;
        
         public MyJPanel(Reservation reservation, ClientGuestProcessor processor) {
            this.processor = processor;
            this.reservation = reservation;
            
            Lbl_reservationID = new JLabel("Reservation ID: ");
            Lbl_checkIn = new JLabel("Check In: ");
            Lbl_checkOut = new JLabel("Check Out: ");
            Lbl_numGuests = new JLabel("Number of Guests: ");
            Lbl_roomType = new JLabel(" Room Type: ");
            Lbl_payment = new JLabel("Payment: ");
            
            TF_reservationID = new JTextField(reservation.reservationID,18);
            TF_checkIn = new JTextField(processor.DateToString(reservation.checkIn),20);
            TF_checkOut = new JTextField(processor.DateToString(reservation.checkOut),19);
            TF_numGuests = new JTextField(""+reservation.numGuests, 2);
            TF_roomType = new JTextField(reservation.room.type, 5);
            TF_payment = new JTextField(""+reservation.payment, 15);
                    
            Btn_update = new JButton("Update");
            Btn_delete = new JButton("Delete");
            Btn_update.addActionListener(this);
            Btn_delete.addActionListener(this);
            
            add(Lbl_reservationID);
            add(TF_reservationID);
            add(Lbl_checkIn);
            add(TF_checkIn);
            add(Lbl_checkOut);
            add(TF_checkOut);
            add(Lbl_numGuests);
            add(TF_numGuests);
            add(Lbl_roomType);
            add(TF_roomType);
            add(Lbl_payment);
            add(TF_payment);
            add(Btn_update);
            add(Btn_delete);
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
            else if (e.getSource() == Btn_delete) {
                if (processor.submitReservationDelete(reservation.reservationID) == 1) dispose();
            }
        }
        
    }
}
