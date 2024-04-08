import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

public class AddDeleteStock extends JFrame implements ActionListener {
    JButton addbtn,clearbtn,clbtn,delbtn,Backbtn;
    JTextField itemidtextfield,itemtextfield, pricetextfield, qtytextfield,ditem;
    JTable stockTable;
    DefaultTableModel tableModel;
    AddDeleteStock() {
        Container c = getContentPane();

        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();


        JLabel label = new JLabel("Add/Delete Stocks");
        label.setBounds(50, -8, 400, 70);
        label.setFont(new Font("Raleway", Font.BOLD, 30));
        label.setForeground(Color.black);
        add(label);

        JLabel label1 = new JLabel("Add  items");
        label1.setBounds(320, 69, 200, 70);
        label1.setFont(new Font("Ralway", Font.BOLD, 20));
        label1.setForeground(Color.black);
        add(label1);

        JLabel label8 = new JLabel("Item_id");
        label8.setBounds(318, 116, 200, 70);
        label8.setFont(new Font("Ralway", Font.BOLD, 18));
        label8.setForeground(Color.black);
        add(label8);


        JLabel label3 = new JLabel("Item");
        label3.setBounds(320, 140, 200, 70);
        label3.setFont(new Font("Ralway", Font.BOLD, 18));
        label3.setForeground(Color.black);
        add(label3);

        JLabel label4 = new JLabel("Qty");
        label4.setBounds(320, 170, 200, 70);
        label4.setFont(new Font("Ralway", Font.BOLD, 18));
        label4.setForeground(Color.black);
        add(label4);

        JLabel label5 = new JLabel("Price");
        label5.setBounds(320, 200, 200, 70);
        label5.setFont(new Font("Ralway", Font.BOLD, 18));
        label5.setForeground(Color.black);
        add(label5);



        addbtn = new JButton("Add");
        addbtn.setFont(new Font("Ralway", Font.ITALIC, 14));
        addbtn.setForeground(Color.black);
        addbtn.setBackground(new Color(160, 242, 153));
        addbtn.setBounds(320, 270, 80, 30);
        addbtn.addActionListener(this);
        add(addbtn);

        clearbtn = new JButton("Clear");
        clearbtn.setFont(new Font("Ralway", Font.ITALIC, 14));
        clearbtn.setForeground(Color.black);
        clearbtn.setBackground(new Color(160, 242, 153));
        clearbtn.setBounds(450, 270, 80, 30);
        clearbtn.addActionListener(this);
        add(clearbtn);

        itemidtextfield = new JTextField();
        itemidtextfield.setBounds(400, 140, 160, 20);
        add(itemidtextfield);

        itemtextfield = new JTextField();
        itemtextfield.setBounds(400, 170, 160, 20);
        add(itemtextfield);

        qtytextfield = new JTextField();
        qtytextfield.setBounds(400, 200, 160, 20);
        add(qtytextfield);

        pricetextfield = new JTextField();
        pricetextfield.setBounds(400, 230, 160, 20);
        add(pricetextfield);

        JLabel label6 = new JLabel("Delete items");
        label6.setBounds(320, 350, 200, 70);
        label6.setFont(new Font("Ralway", Font.BOLD, 20));
        label6.setForeground(Color.black);
        add(label6);

        JLabel label7 = new JLabel("Item");
        label7.setBounds(320, 420, 200, 70);
        label7.setFont(new Font("Ralway", Font.BOLD, 18));
        label7.setForeground(Color.black);
        add(label7);

        delbtn = new JButton("Delete");
        delbtn.setFont(new Font("Ralway", Font.ITALIC, 14));
        delbtn.setForeground(Color.black);
        delbtn.setBackground(new Color(160, 242, 153));
        delbtn.setBounds(320, 500, 80, 30);
        delbtn.addActionListener(this);
        add(delbtn);


        clbtn = new JButton("Clear");
        clbtn.setFont(new Font("Ralway", Font.ITALIC, 14));
        clbtn.setForeground(Color.black);
        clbtn.setBackground(new Color(160, 242, 153));
        clbtn.setBounds(450, 500, 80, 30);
        clbtn.addActionListener(this);
        add(clbtn);

        ditem = new JTextField();
        ditem.setBounds(400, 448, 160, 20);
        add(ditem);


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

        panel2.setBounds(300, 70, 350, 250);
        panel2.setBackground(Color.white);

        panel3.setBounds(770, 100, 450, 500);
        panel3.setBackground(Color.white);

        panel4.setBounds(300, 350, 350, 250);
        panel4.setBackground(Color.white);

        String[] columnNames = {"Item_id","Item", "Qty", "Price"};
        tableModel = new DefaultTableModel(columnNames, 0);
        stockTable = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(stockTable);
        scrollPane.setBounds(800, 120, 400, 440);
        add(scrollPane);

        c.add(panel);
        c.add(panel1);
        c.add(panel2);
        c.add(panel3);
        c.add(panel4);
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
        String Itemid=itemidtextfield.getText();
        String Item = itemtextfield.getText();
        String Price = pricetextfield.getText();
        String Qty = qtytextfield.getText();
        try {
            if (ae.getSource() == addbtn) {
                if (itemtextfield.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter the fields");
                } else {
                    if (isItemExists(Item)) {
                        JOptionPane.showMessageDialog(null, "Item already exist please update the item ");
                       }else {
                        Con con = new Con();
                        String q = "INSERT INTO stock(p_code,s_name,s_quantity,Price) VALUES('" + Itemid + " ','" + Item + " ','" + Qty + " ','" + Price + "')";
                        String q2 = "INSERT INTO incoming(p_code,Item,Qty) VALUES('" + Itemid + " ','" + Item + " ','" + Qty + " ')";

                        con.statement.executeUpdate(q);
                        con.statement.executeUpdate(q2);
                        JOptionPane.showMessageDialog(null, "Item added \nItem:" + Item + "\n Qty:" + Qty + "\n Price:" + Price);
                        refreshStockData();
                    }
                }
            } else if (ae.getSource() == clearbtn) {
                itemtextfield.setText("");
                qtytextfield.setText("");
                pricetextfield.setText("");
            } else if (ae.getSource() == Backbtn) {
                Dashboard dashboard = new Dashboard("");
                dashboard.setVisible(true);
                this.dispose();
            } else if (ae.getSource() == delbtn) {
                String Item1 = ditem.getText();
                try {
                    if (ae.getSource() == delbtn) {
                        if (ditem.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Enter the fields");
                        } else{
                            if(!isItemExists(Item1)) {
                                JOptionPane.showMessageDialog(null,"Item don't exist in stock");
                            }else {
                                Con con = new Con();
                                String q1 = "DELETE FROM stock WHERE s_name ='" + Item1 + "'";
                                String q2="DELETE FROM incoming WHERE Item='"+Item1+"'";
                                String q3="DELETE FROM outgoing WHERE Item='"+Item1+"'";
                                con.statement.executeUpdate(q2);
                                con.statement.executeUpdate(q1);

                                con.statement.executeUpdate(q3);
                                JOptionPane.showMessageDialog(null, "Item deleted \nItem:" + Item1);
                                refreshStockData();

                            }
                        }
                    }else if(ae.getSource()==clbtn) {
                        ditem.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private boolean isItemExists(String item) {
        try {
            Con con = new Con();
            String q2 = "SELECT * FROM stock WHERE s_name= '" + item + "'";
            try (ResultSet resultset = con.statement.executeQuery(q2)) {
                return resultset.next();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return false;
    }

    private void refreshStockData() {
        tableModel.setRowCount(0);
        loadStockData();
    }

    public static  void main(String[] args){
        AddDeleteStock addDeleteStock=new AddDeleteStock();
        }
    }
