import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
public class log extends JFrame implements ActionListener {
    JButton button1;
    JPasswordField passwordField;
    JTextField textField1;
    log() {
       Container c=getContentPane();

       JPanel panel=new JPanel();
       JPanel panel1=new JPanel();

        panel.setLayout(null);
        panel1.setLayout(null);

        ImageIcon addIcon = new ImageIcon("C:\\Users\\gurun\\Desktop\\project\\logo.jpg");
        JLabel addlabel=new JLabel(addIcon);
        addlabel.setBounds(130,70,100,100);
        panel.add(addlabel);

        //BLACK BACKGROUND PART
        JLabel label =new JLabel("WELCOME BACK!");
        label.setBounds(120,200,300,40);
        label.setFont(new Font("Monospaced",Font.BOLD,22));
        label.setForeground(Color.white);
        add(label);

        JLabel label0 =new JLabel("LOGIN AND START MANAGING YOUR");
        label0.setBounds(60,232,400,30);
        label0.setFont(new Font("Monospaced",Font.BOLD,18));
        label0.setForeground(Color.white);
        add(label0);

        JLabel label00 =new JLabel("STOCKS NOW");
        label00.setBounds(140,254,400,30);
        label00.setFont(new Font("Monospaced",Font.BOLD,18));
        label00.setForeground(Color.white);
        add(label00);

        //WHITE BACKGROUND PART

        JLabel labelT =new JLabel("Stock Now");
        labelT.setBounds(520,50,300,60);
        labelT.setFont(new Font("Monospaced",Font.BOLD,40));

        labelT.setForeground(Color.BLACK);
        add(labelT);

        JLabel label1 = new JLabel("Username");
        label1.setBounds(480, 190, 375, 30);
        label1.setFont(new Font("Monospaced", Font.BOLD, 18));
        label1.setForeground(Color.black);
        add(label1);

        textField1 = new JTextField(15);
        textField1.setBounds(575, 190, 200, 30);
        textField1.setBackground(new Color(217, 217, 217));
        textField1.setFont(new Font("Ralway", Font.PLAIN, 15));
        add(textField1);

        JLabel label3 = new JLabel("Password");
        label3.setBounds(480, 250, 375, 30);
        label3.setFont(new Font("Monospaced", Font.BOLD, 18));
        label3.setForeground(Color.black);
        add(label3);

        passwordField = new JPasswordField(15);
        passwordField.setBounds(575, 250, 200, 30);
        passwordField.setFont(new Font("Ralway", Font.BOLD, 15));
        passwordField.setBackground(new Color(217, 217, 217));
        add(passwordField);

        button1 = new JButton("Login");
        button1.setFont(new Font("Monospaced", Font.BOLD, 18));
        button1.setForeground(Color.black);
        button1.setBackground(new Color(160, 242, 153));
        button1.setBounds(600, 310, 150, 35);
        button1.addActionListener(this);
        add(button1);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(850, 480);
        setLocation(200, 100);

        panel.setBackground(Color.black);
        panel.setBounds(10,10,400,400);


        panel1.setBackground(new Color(68,173,116));
        panel1.setBounds(320,10,500,400);

        c.add(panel);
        c.add(panel1);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == button1) {
                Con c = new Con();
                String username = textField1.getText();
                String password = new String(passwordField.getPassword());
                String q = "select * from users where username = '" + username + "' and password = '" + password + "'";
                ResultSet rs = c.statement.executeQuery(q);

                if (rs.next()) {
                    String name=rs.getString("Username");
                    JOptionPane.showMessageDialog(null, "Login successfully as "+name);
                    setVisible(false);

                    Dashboard dashboard=new Dashboard(username);
                    dashboard.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect username or password");
                }
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    public static void main(String[] args) {

        new log();
    }
}
