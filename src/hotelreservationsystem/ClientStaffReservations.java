package hotelreservationsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class ClientStaffReservations extends JFrame {
    public ClientStaffReservations (ClientStaffProcessor processor) {
        setSize(500,620);
        setTitle("Staff Client");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        StaffJPanel myJPanel = new StaffJPanel(processor);
        Container c = getContentPane();
        c.add(myJPanel);
        setVisible(true);
    }
    
    public class StaffJPanel extends JPanel implements ActionListener {
        ClientStaffProcessor processor;
        
        JTextField TF_reservationID;
        JLabel Lbl_reservationID;
        JButton Btn_submit, Btn_refresh, Btn_viewReservation;
        JList Lst_reservations;
        JScrollPane listScroller;
        
         public StaffJPanel(ClientStaffProcessor processor) {
            this.processor = processor;
            
            Lbl_reservationID = new JLabel("Reservation ID: ");
            TF_reservationID = new JTextField("",15);
            Lst_reservations = new JList(processor.reservationManager.reservations.toArray());
            Lst_reservations.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            Lst_reservations.setLayoutOrientation(JList.VERTICAL);
            listScroller = new JScrollPane(Lst_reservations);
            listScroller.setPreferredSize(new Dimension(450, 500));
            
            Btn_submit = new JButton("Check");
            Btn_refresh = new JButton("Refresh");
            Btn_viewReservation = new JButton("View");
            Btn_refresh.addActionListener(this);
            Btn_viewReservation.addActionListener(this);
            Btn_submit.addActionListener(this);
            
            add(Lbl_reservationID);
            add(TF_reservationID);
            add(Btn_submit);
            add(listScroller);
            add(Btn_refresh);
            add(Btn_viewReservation);

        }
        
        public void actionPerformed(ActionEvent e) {
            if (e.getSource()==Btn_submit){
                try{
                    Reservation search = processor.searchReservation(TF_reservationID.getText());
                    new ClientStaffUpdateReservation(search, processor);
                }
                catch(Exception err){
                    System.out.println(err);
                }
            }
            else if (e.getSource() == Btn_refresh) {
                Lst_reservations.setListData(processor.reservationManager.reservations.toArray());
            }
            else if (e.getSource() == Btn_viewReservation) {
                new ClientStaffUpdateReservation(processor.reservationManager.reservations.get(Lst_reservations.getSelectedIndex()), processor);
            }
        }
    }
}
