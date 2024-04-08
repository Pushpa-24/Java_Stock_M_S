import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

public class Update extends JFrame implements ActionListener {

    JButton savechangebtn,clearbtn,Backbtn;
    JTextField itemidtextfield,itemtextfield, pricetextfield, qtytextfield,changeitemtextfield;
    JTable stockTable;
    DefaultTableModel tableModel;
    Update() {
        Container c = getContentPane();

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3=new JPanel();


        JLabel label = new JLabel("Update stock");
        label.setBounds(50, -3, 200, 70);
        label.setFont(new Font("Ralway", Font.BOLD, 30));
        label.setForeground(Color.black);
        add(label);


        JLabel label3 = new JLabel("Item");
        label3.setBounds(320, 120, 200, 70);
        label3.setFont(new Font("Ralway", Font.BOLD, 20));
        label3.setForeground(Color.black);
        add(label3);

        JLabel label4 = new JLabel("Qty");
        label4.setBounds(320, 170, 200, 70);
        label4.setFont(new Font("Ralway", Font.BOLD, 20));
        label4.setForeground(Color.black);
        add(label4);

        JLabel label5 = new JLabel("Price");
        label5.setBounds(320, 220, 200, 70);
        label5.setFont(new Font("Ralway", Font.BOLD, 20));
        label5.setForeground(Color.black);
        add(label5);

        JLabel label8 = new JLabel("Item_id");
        label8.setBounds(320, 93, 200, 70);
        label8.setFont(new Font("Ralway", Font.BOLD, 18));
        label8.setForeground(Color.black);
        add(label8);

        itemidtextfield = new JTextField();
        itemidtextfield.setBounds(450, 120, 160, 24);
        add(itemidtextfield);


        itemtextfield = new JTextField();
        itemtextfield.setBounds(450, 150, 160, 24);
        add(itemtextfield);

        qtytextfield = new JTextField();
        qtytextfield.setBounds(450, 196, 160, 24);
        add(qtytextfield);

        pricetextfield = new JTextField();
        pricetextfield.setBounds(450, 240, 160, 24);
        add(pricetextfield);


        JLabel label6 = new JLabel("Change item name");
        label6.setBounds(305, 260, 200, 70);
        label6.setFont(new Font("Ralway", Font.BOLD, 15));
        label6.setForeground(Color.black);
        add(label6);

        changeitemtextfield = new JTextField();
        changeitemtextfield.setBounds(450, 285, 160, 24);
        add(changeitemtextfield);

        savechangebtn = new JButton("Save Changes");
        savechangebtn.setFont(new Font("Ralway", Font.BOLD, 14));
        savechangebtn.setForeground(Color.black);
        savechangebtn.setBackground(new Color(160, 242, 153));
        savechangebtn.setBounds(320, 380, 135, 30);
        savechangebtn.addActionListener(this);
        add(savechangebtn);

        clearbtn = new JButton("Clear");
        clearbtn.setFont(new Font("Ralway", Font.BOLD, 14));
        clearbtn.setForeground(Color.black);
        clearbtn.setBackground(new Color(160, 242, 153));
        clearbtn.setBounds(490, 380, 80, 30);
        clearbtn.addActionListener(this);
        add(clearbtn);

        JLabel label2 = new JLabel("Stocks");
        label2.setBounds(800, 40, 200, 70);
        label2.setFont(new Font("Ralway", Font.BOLD, 30));
        label2.setForeground(Color.black);
        add(label2);

        Backbtn = new JButton("Back");
        Backbtn.setFont(new Font("Ralway", Font.ITALIC, 16));
        Backbtn.setForeground(Color.black);
        Backbtn.setBackground(new Color(160, 242, 153));
        Backbtn.setBounds(30, 250, 180, 60);
        Backbtn.addActionListener(this);
        add(Backbtn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1500, 700);
        setLocation(0, 0);

        panel.setBounds(0, 0, 1500, 50);
        panel.setBackground(new Color(68,173,116));

        panel1.setBounds(0, 50, 250, 1000);
        panel1.setBackground(Color.GRAY);

        panel2.setBounds(300, 70, 350, 500);
        panel2.setBackground(Color.white);

        panel3.setBounds(770, 100, 450, 500);
        panel3.setBackground(Color.white);

        String[] columnNames = {"Item_id","Item", "Qty", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        stockTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(stockTable);
        scrollPane.setBounds(800, 120, 400, 440);
        add(scrollPane);

        add(panel);
        add(panel1);
        add(panel2);
        add(panel3);
        setVisible(true);
        loadStockData();
    }
    private void loadStockData() {
        try {
            Con con = new Con();
            String query = "SELECT * FROM stock";
            ResultSet resultSet = con.statement.executeQuery(query);

            while (resultSet.next()) {
                String itemid = resultSet.getString("p_code");
                String item = resultSet.getString("s_name");
                String qty = resultSet.getString("s_quantity");
                String price = resultSet.getString("Price");

                Vector<String> row = new Vector<>();
                row.add(itemid);
                row.add(item);
                row.add(qty);
                row.add(price);

                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String Item = itemtextfield.getText();
        String Price = pricetextfield.getText();
        String Qty = qtytextfield.getText();
        String changeditem=changeitemtextfield.getText();
        try {
            if (ae.getSource() == savechangebtn) {
                if (itemtextfield.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter the fields");
                } else {
                    if (isItemExists(Item)) {
                        String q;
                        String q2;
                        Con con = new Con();
                        if (!changeditem.isEmpty()) {
                           q = "UPDATE stock SET s_name='" + changeditem + "', s_quantity='" + Qty + "', Price='" + Price + "' WHERE s_name='" + Item + "'";
                           q2="UPDATE incoming SET Item='" + changeditem + "', Qty='" + Qty + "' WHERE Item='" + Item + "'";
                            JOptionPane.showMessageDialog(null, "Item updated \nitem:" + Item + "\n Qty:" + Qty + "\n Price:" +
                                    Price+"\nItem name changed to "+changeditem);
                        }
                        else{
                            q2= "UPDATE incoming SET Qty='" + Qty + "'WHERE Item='" + Item + "'";
                            q = "UPDATE stock SET s_quantity='" + Qty + " ',Price='" + Price + "'WHERE s_name='" + Item + "'";
                            JOptionPane.showMessageDialog(null, "Item updated \nitem:" + Item + "\n Qty:" + Qty + "\n Price:" + Price);
                        }
                        con.statement.executeUpdate(q);
                        con.statement.executeUpdate(q2);
                        refreshStockData();
                    }else {
                        JOptionPane.showMessageDialog(null,"Item don't exist");
                    }
                }
            } else if (ae.getSource() == clearbtn) {
                itemtextfield.setText("");
                qtytextfield.setText("");
                pricetextfield.setText("");
                changeitemtextfield.setText("");
            } else if (ae.getSource() == Backbtn) {
                Dashboard dashboard = new Dashboard("");
                dashboard.setVisible(true);
                this.dispose();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean isItemExists(String item){
        try{
            Con con=new Con();
            String q2="SELECT * FROM stock WHERE s_name= '"+item+"'";
            try(ResultSet resultset =con.statement.executeQuery(q2)){
                return resultset.next();
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    private void refreshStockData() {
        tableModel.setRowCount(0);
        loadStockData();
    }


    public static void main(String []args){
        Update update=new Update();
    }
}
