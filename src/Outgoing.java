import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Vector;
import java.sql.PreparedStatement;

public class   Outgoing extends JFrame implements ActionListener {
    JButton exportbtn,clearbtn,Backbtn;
    JTextField itemidtextfield, itemtextfield,  qtytextfield;
    JTable stockTable;
    JDateChooser dateChooser;

    DefaultTableModel tableModel;
    Outgoing() {
        Container c = getContentPane();

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3=new JPanel();

        JLabel label = new JLabel("Stock out");
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

        JLabel label5 = new JLabel("Date");
        label5.setBounds(320, 220, 200, 70);
        label5.setFont(new Font("Ralway", Font.BOLD, 20));
        label5.setForeground(Color.black);
        add(label5);

        dateChooser=new JDateChooser();
        dateChooser.setFont(new Font("Raleway",Font.BOLD,10));
        dateChooser.setBackground(new Color(105,105,105));
        dateChooser.setBounds(450, 240, 170, 24);
        dateChooser.getJCalendar().setPreferredSize(new Dimension(200, 150));
        add(dateChooser);


        JLabel label6 = new JLabel("Item_id");
        label6.setBounds(320, 93, 200, 70);
        label6.setFont(new Font("Ralway", Font.BOLD, 18));
        label6.setForeground(Color.black);
        add(label6);

        itemidtextfield = new JTextField();
        itemidtextfield.setBounds(450, 120, 160, 24);
        add(itemidtextfield);

        itemtextfield = new JTextField();
        itemtextfield.setBounds(450, 150, 160, 24);
        add(itemtextfield);

        qtytextfield = new JTextField();
        qtytextfield.setBounds(450, 196, 160, 24);
        add(qtytextfield);

        exportbtn = new JButton("Export");
        exportbtn.setFont(new Font("Ralway", Font.BOLD, 10));
        exportbtn.setForeground(Color.black);
        exportbtn.setBackground(new Color(160, 242, 153));
        exportbtn.setBounds(320, 380, 120, 30);
        exportbtn.addActionListener(this);
        add(exportbtn);

        clearbtn = new JButton("Clear");
        clearbtn.setFont(new Font("Ralway", Font.ITALIC, 14));
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
        Backbtn.setFont(new Font("Ralway", Font.ITALIC, 14));
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

        String[] columnNames = {"Item_id","Item", "Qty"};
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
    private void exporttodb(String itemid ,String item ,int exportedItem){
        try{
            Date date = new Date(dateChooser.getDate().getTime());
            Con con =new Con();
            String q="INSERT INTO outgoing(p_code,date,Item,Qty) VALUES(?,?,?,?)";
            try (PreparedStatement preparedStatement=con.connection.prepareStatement(q)){
                preparedStatement.setString(1,itemid);
                preparedStatement.setDate(2,date);
            preparedStatement.setString(3,item);
               preparedStatement.setInt(4,exportedItem);
               preparedStatement.executeUpdate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
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

                Vector<String> row = new Vector<>();
                row.add(itemid);
                row.add(item);
                row.add(qty);

                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String itemid=itemidtextfield.getText();
        String Item = itemtextfield.getText();
        String Qty = qtytextfield.getText();
        try {
            try {
                if (ae.getSource() == exportbtn) {
                    if (itemtextfield.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Enter the fields");
                    } else {
                        if (!isItemExists(Item)) {
                            JOptionPane.showMessageDialog(null,"Item dont exist in stock");
                        }else {
                            int requestedQty = Integer.parseInt(Qty);
                            int exportedQty = Integer.parseInt(Qty);
                            Con con = new Con();
                            if (isSufficientQuantity(Item, requestedQty, con)) {
                                updateqty(Item, exportedQty, con);
                                exporttodb(itemid,Item,exportedQty);
                                JOptionPane.showMessageDialog(null, "Item exported \nitem:" + Item + "\n Qty:" + Qty);
                                refreshStockData();
                            } else {
                                JOptionPane.showMessageDialog(null, "Insufficient quantity in stock.");
                            }
                        }
                    }
                } else if (ae.getSource() == clearbtn) {
                    itemtextfield.setText("");
                    qtytextfield.setText("");
                } else if (ae.getSource() == Backbtn) {
                    Dashboard dashboard=new Dashboard("");
                    dashboard.setVisible(true);
                    this.dispose();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isSufficientQuantity(String Item, int requestedqty,Con con) {
        try {
            String q = "SELECT  s_quantity from stock WHERE s_name =?";
            try (PreparedStatement preparedStatement = con.connection.prepareStatement(q)) {
                preparedStatement.setString(1, Item);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    int availableQty = resultSet.getInt("s_quantity");
                    return requestedqty <= availableQty;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    private void updateqty(String item,int exportedQty,Con con){
        String q="UPDATE stock SET s_quantity=s_quantity-? WHERE s_name=?";
        try(PreparedStatement preparedStatement=con.connection.prepareStatement(q)){
            preparedStatement.setInt(1,exportedQty);
            preparedStatement.setString(2,item);
            preparedStatement.executeUpdate();
        }catch (Exception e){
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
        Outgoing outgoing=new Outgoing();
    }
}
