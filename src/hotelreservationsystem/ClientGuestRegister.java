package hotelreservationsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClientGuestRegister extends JFrame {
    public ClientGuestRegister (ClientGuestProcessor processor) {
        setSize(360,210);
        setTitle("Registration");
        MyJPanel myJPanel = new MyJPanel(processor);
        Container c = getContentPane();
        c.add(myJPanel);
        setVisible(true);
    }
    
    public class MyJPanel extends JPanel implements ActionListener {
        ClientGuestProcessor processor;
        JLabel Lbl_name, Lbl_birthday, Lbl_phoneNumber, Lbl_email, Lbl_password,
                Lbl_error;
        JTextField TF_name, TF_birthday, TF_phoneNumber, TF_email, TF_password;
        JButton Btn_register;
        
        public MyJPanel(ClientGuestProcessor processor) {
            this.processor = processor;
                    
            Lbl_name = new JLabel("Name:          ");
            Lbl_birthday = new JLabel("Birthday (YYYY/MM/DD):  ");
            Lbl_phoneNumber = new JLabel("Phone Number:  ");
            Lbl_email = new JLabel("Email:         ");
            Lbl_password = new JLabel("Password:      ");
            TF_name = new JTextField("", 20);
            TF_birthday = new JTextField("", 10);
            TF_phoneNumber = new JTextField("", 20);
            TF_email = new JTextField("", 20);
            TF_password = new JTextField("", 20);
            Btn_register = new JButton("Register");
            Lbl_error = new JLabel("");
            
            add(Lbl_name);
            add(TF_name);
            add(Lbl_birthday);
            add(TF_birthday);
            add(Lbl_phoneNumber);
            add(TF_phoneNumber);
            add(Lbl_email);
            add(TF_email);
            add(Lbl_password);
            add(TF_password);
            add(Btn_register);
            Btn_register.addActionListener(this);
            add(Lbl_error);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Btn_register) {
                Lbl_error.setText("");
                if (processor.submitRegistration(TF_name.getText(), 
                        TF_birthday.getText(), 
                        TF_phoneNumber.getText(), TF_email.getText(), 
                        TF_password.getText()) == 1) {
                    dispose();            
                }
                else {
                    Lbl_error.setText("Error");
                }
            }
        }
    }
}
