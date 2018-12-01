package hotelreservationsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClientGuestLogin extends JFrame {
    public ClientGuestLogin (ClientGuestProcessor processor) {
        setTitle("Guest Login");
        setSize(220,160);
        MyJPanel myJPanel = new MyJPanel(processor);
        Container c = getContentPane();
        c.add(myJPanel);
        setVisible(true);
    }
    
    public class MyJPanel extends JPanel implements ActionListener {
        ClientGuestProcessor processor;
        
        JTextField TF_username, TF_password;
        JLabel Lbl_title, Lbl_username, Lbl_password;
        JButton Btn_login, Btn_newUser;
        
        public MyJPanel(ClientGuestProcessor processor) {
            
            this.processor = processor;
            
            Lbl_title = new JLabel("     ------ GUEST LOGIN ------     ");
            Lbl_username = new JLabel("Email: ");
            Lbl_password = new JLabel("Password: ");
            TF_username = new JTextField("john.smith@email.com",10);
            TF_password = new JTextField("password", 10);
            Btn_login = new JButton("Login");
            Btn_newUser = new JButton("Register");
            Btn_login.addActionListener(this);
            Btn_newUser.addActionListener(this);
            
            add(Lbl_title);
            add(Lbl_username);
            add(TF_username);
            add(Lbl_password);
            add(TF_password);
            add(Btn_login);
            add(Btn_newUser);
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == Btn_login) {
                if (processor.submitLogin(TF_username.getText(), TF_password.getText()) == 1) {
                    dispose();
                }
                else {
                    System.out.println("Error");
                }
            }
            else if (e.getSource() == Btn_newUser) {
                new ClientGuestRegister(processor);
            }
        }
    }
}
