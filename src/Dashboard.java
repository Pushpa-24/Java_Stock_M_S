import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;


public class Dashboard extends JFrame implements ActionListener {
    JButton addelbtn,outgoingbtn,updatebtn,reportbtn,logoutbtn;
    String username;


    Dashboard(String username) {
        this.username=username;

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2=new JPanel();

        panel.setLayout(null);
        panel1.setLayout(null);
        panel2.setLayout(null);

        JLabel user = new JLabel(username);
        user.setBounds(10,10,200,100);
        add(user);

        ImageIcon imageIcon=new ImageIcon("C:\\Users\\gurun\\Downloads\\mountains-firewatch-green-forest-wallpaper-preview (3).jpg");
        JLabel add =new JLabel(imageIcon);
        add.setBounds(0, 5, 1272, 640);
        panel1.add(add);

        ImageIcon imageIcon1 = new ImageIcon("C:\\Users\\gurun\\Desktop\\green_background_3-wallpaper-1080x1920.jpg");
        JLabel backgroundLabel = new JLabel(imageIcon1);
        backgroundLabel.setBounds(200, -150, 600, 900);
        backgroundLabel.setBackground(new Color(0, 0, 0, 0)); // Make the background of the label transparent
        backgroundLabel.setOpaque(false);
        panel2.add(backgroundLabel);


        JLabel label2=new JLabel("Start recording your stocks with STOCK NOW");
        label2.setBounds(250, 80, 800, 70);
        label2.setFont(new Font("DialogInput", Font.ITALIC,30));
        label2.setForeground(Color.black);


        JLabel label = new JLabel("Dashboard");
        panel1.setBackground(new Color(68,173,116));
        label.setBounds(50, -10, 200, 70);
        label.setFont(new Font("Serif", Font.CENTER_BASELINE, 30));
        label.setForeground(Color.black);
        add(label);
        add(label2);



        addelbtn = new JButton("Add/Delete Product");
        addelbtn.setFont(new Font("Ralway", Font.ITALIC, 16));
        addelbtn.setForeground(Color.black);
        addelbtn.setBackground(new Color(160, 242, 153));
        addelbtn.setBounds(460, 198, 300, 60);
        addelbtn.addActionListener(this);  // Uncomment this line
        add(addelbtn);



        updatebtn = new JButton("Update Product");
        updatebtn.setFont(new Font("Ralway", Font.ITALIC, 16));
        updatebtn.setForeground(Color.black);
        updatebtn.setBackground(new Color(68,173,116));;
        updatebtn.setBounds(460, 290, 300, 60);
        updatebtn.addActionListener(this);
        add(updatebtn);

        outgoingbtn = new JButton("Stock out");
        outgoingbtn.setFont(new Font("Ralway", Font.ITALIC, 16));
        outgoingbtn.setForeground(Color.black);
        outgoingbtn.setBackground(new Color(160, 242, 153));
        outgoingbtn.setBounds(460, 390, 300, 60);
        outgoingbtn.addActionListener(this);  // Uncomment this line
        add(outgoingbtn);

        reportbtn = new JButton("Report");
        reportbtn.setFont(new Font("Ralway", Font.ITALIC, 16));
        reportbtn.setForeground(Color.black);
        reportbtn.setBackground(new Color(68,173,116));
        reportbtn.setBounds(460, 490, 300, 60);
        reportbtn.addActionListener(this);
        add(reportbtn);

        logoutbtn = new JButton("Logout");
        logoutbtn.setFont(new Font("Ralway", Font.ITALIC, 16));
        logoutbtn.setForeground(Color.black);
        logoutbtn.setBackground(new Color(68,173,116));
        logoutbtn.setBounds(1000, 550, 200, 60);
        logoutbtn.addActionListener(this);
        add(logoutbtn);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1500, 1000);
        setLocation(0, 0);

        panel.setBounds(0, 0, 1500, 50);
        panel.setBackground(new Color(68,173,116));

        panel1.setBounds(0, 0, 1272, 800);
       panel1.setBackground(Color.gray);

       panel2.setBounds(200,170,600,400);
       panel2.setBackground(Color.black);
       panel2.setOpaque(false);
       add(panel2);


        add(panel);
        add(panel1);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==addelbtn){
         AddDeleteStock addDeleteStock=new AddDeleteStock();
         addDeleteStock.setVisible(true);
         this.dispose();
        } else if (ae.getSource()==outgoingbtn) {
            Outgoing outgoing=new Outgoing();
            outgoing.setVisible(true);
            this.dispose();
        } else if (ae.getSource()==updatebtn) {
            Update update=new Update();
            update.setVisible(true);
            this.dispose();
        } else if (ae.getSource()==reportbtn) {
            Report report =new Report(username);
            report.setVisible(true);
            this.dispose();
        } else if (ae.getSource()==logoutbtn) {
            log log=new log();
            log.setVisible(true);
            this.dispose();

        }
    }

    public static void main(String[] args) {
        Dashboard dashboard=new Dashboard("");

    }

}




