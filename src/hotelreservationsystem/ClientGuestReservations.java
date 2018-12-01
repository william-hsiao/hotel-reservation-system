package hotelreservationsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class ClientGuestReservations extends JFrame {
    public ClientGuestReservations (Guest guest, ClientGuestProcessor processor) {
        setSize(500,600);
        setTitle("Guest Client");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        MyJPanel myJPanel = new MyJPanel(guest, processor);
        Container c = getContentPane();
        c.add(myJPanel);
        setVisible(true);
    }
    
    public class MyJPanel extends JPanel implements ActionListener {
        ClientGuestProcessor processor;
        Guest guest;

        JButton Btn_newReservation, Btn_refresh, Btn_viewReservation;
        JList Lst_reservations;
        JScrollPane listScroller;
        
        public MyJPanel(Guest guest, ClientGuestProcessor processor) {
            
            this.guest = guest;
            this.processor = processor;
            
            Btn_newReservation = new JButton("New Reservation");
            Btn_refresh = new JButton("Refresh");
            Btn_viewReservation = new JButton("View");
            Lst_reservations = new JList(guest.reservations.toArray());
            Lst_reservations.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            Lst_reservations.setLayoutOrientation(JList.VERTICAL);
            
            listScroller = new JScrollPane(Lst_reservations);
            listScroller.setPreferredSize(new Dimension(450, 500));
            
            add(Btn_newReservation);
            Btn_newReservation.addActionListener(this);
            add(Btn_refresh);
            Btn_refresh.addActionListener(this);
            add(Btn_viewReservation);
            Btn_viewReservation.addActionListener(this);
            add(listScroller);
            
            
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Btn_newReservation) {
                new ClientGuestNewReservation(guest, processor);
            }
            else if (e.getSource() == Btn_refresh) {
                Lst_reservations.setListData(guest.reservations.toArray());
            }
            else if (e.getSource() == Btn_viewReservation) {
                new ClientGuestUpdateReservation(processor.reservationManager.reservations.get(Lst_reservations.getSelectedIndex()), processor);
            }
        }
        
    }
}
