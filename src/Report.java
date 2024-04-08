import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;

import java.util.Vector;

public class Report extends JFrame implements ActionListener{
    JLabel l6;
    String username;
    JButton Printbtn,Backbtn;
    JTable stockTable1,stockTable2,stockTable3;
    DefaultTableModel tableModel,tableModel2,tableModel3;
    Report(String username) {
        this.username=username;
        Container c = getContentPane();
        Border blackline=BorderFactory.createLineBorder(Color.black);
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        JLabel label = new JLabel("Report");
        label.setBounds(50, -3, 200, 70);
        label.setFont(new Font("Ralway", Font.BOLD, 30));
        label.setForeground(Color.black);
        add(label);

        JLabel label1 = new JLabel("-----------Stock Now----------");
        label1.setBounds(550, 40, 700, 70);
        label1.setFont(new Font("Ralway", Font.BOLD, 20));
        label1.setForeground(Color.black);
        add(label1);


        JLabel label3 = new JLabel(" Address:                     Vyas -2 ,Damauli,Tanahun");
        label3.setBounds(270, 100, 900, 25);
        label3.setFont(new Font("Ralway", Font.BOLD, 18));
        label3.setForeground(Color.black);
        label3.setBorder(blackline);
        add(label3);

        JLabel label4 = new JLabel("Contact no:                  9812345678,   9823456725");
        label4.setBounds(270, 125, 900, 25);
        label4.setFont(new Font("Ralway", Font.BOLD, 18));
        label4.setForeground(Color.black);
        label4.setBorder(blackline);
        add(label4);

        JLabel label5 = new JLabel("Department Name:       Warehouse");
        label5.setBounds(270, 150, 900, 25);
        label5.setFont(new Font("Ralway", Font.BOLD, 18));
        label5.setForeground(Color.black);
        label5.setBorder(blackline);
        add(label5);

        l6 = new JLabel("Stock keeper Name:    ");
        l6.setBounds(270, 175, 900, 25);
        l6.setFont(new Font("Ralway", Font.BOLD, 18));
        l6.setForeground(Color.black);
        l6.setBorder(blackline);
        add(l6);

        JLabel label7 = new JLabel("Stock Report",SwingConstants.CENTER);
        label7.setBounds(270, 200, 900, 30);
        label7.setFont(new Font("Ralway", Font.BOLD, 20));
        label7.setForeground(Color.black);
        label7.setBackground(Color.YELLOW);
        label7.setOpaque(true);
        label7.setBorder(blackline);
        add(label7);

        JLabel label8 = new JLabel("Stock In",SwingConstants.CENTER);
        label8.setBounds(278, 230, 289, 17);
        label8.setFont(new Font("Ralway", Font.BOLD, 15));
        label8.setForeground(Color.black);
        label8.setBackground(Color.YELLOW);
        label8.setOpaque(true);
        label8.setBorder(blackline);
        add(label8);

        JLabel label9 = new JLabel("Stock Out",SwingConstants.CENTER);
        label9.setBounds(568, 230, 290, 17);
        label9.setFont(new Font("Ralway", Font.BOLD, 15));
        label9.setForeground(Color.black);
        label9.setBackground(Color.YELLOW);
        label9.setOpaque(true);
        label9.setBorder(blackline);
        add(label9);

        JLabel label10 = new JLabel("Remaining Stock",SwingConstants.CENTER);
        label10.setBounds(858, 230, 290, 17);
        label10.setFont(new Font("Ralway", Font.BOLD, 15));
        label10.setForeground(Color.black);
        label10.setBackground(Color.YELLOW);
        label10.setOpaque(true);
        label10.setBorder(blackline);
        add(label10);

        Printbtn = new JButton("Print");
        Printbtn.setFont(new Font("Ralway", Font.BOLD, 16));
        Printbtn.setForeground(Color.black);
        Printbtn.setBackground(new Color(160, 242, 153));
        Printbtn.setBounds(1050, 580, 100, 30);
        Printbtn.addActionListener(this);
        add(Printbtn);


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

        panel2.setBounds(270, 50, 900, 150);
        panel2.setBackground(Color.LIGHT_GRAY);

        panel3.setBounds(270, 230, 900, 380);
        panel3.setBackground(new Color(68,173,116));

        String[] columnNames = {"Item", "Qty"};
        tableModel = new DefaultTableModel(columnNames, 0);
        stockTable1 = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(stockTable1);
        scrollPane.setBounds(278, 247, 290, 330);
        add(scrollPane);

        String[] columnNames2 = {"Date","Item","Qty"};
        tableModel2 = new DefaultTableModel(columnNames2, 0);
        stockTable2 = new JTable(tableModel2);

        JScrollPane scrollPane2 = new JScrollPane(stockTable2);
        scrollPane2.setBounds(568, 247, 290, 330);
        add(scrollPane2);


        String[] columnNames3 = {"Item", "Qty"};
        tableModel3 = new DefaultTableModel(columnNames3, 0);
        stockTable3 = new JTable(tableModel3);

        JScrollPane scrollPane3 = new JScrollPane(stockTable3);
        scrollPane3.setBounds(858, 247, 290, 330);
        add(scrollPane3);

        loadStockKeeperName();
        add(panel);
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);

        setVisible(true);

        loadoutData();
        loadinData();
        loadstockData();
    }
    private void loadinData() {
        try {
            Con con = new Con();
            String query = "SELECT * FROM incoming";
            ResultSet resultSet = con.statement.executeQuery(query);
            while (resultSet.next()) {
                String item = resultSet.getString("Item");
                String qty = resultSet.getString("Qty");

                Vector<String> row = new Vector<>();
                row.add(item);
                row.add(qty);

                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadoutData() {
        try {
            Con con = new Con();
            String query = "SELECT * FROM outgoing";
            ResultSet resultSet = con.statement.executeQuery(query);
            while (resultSet.next()) {
                String item = resultSet.getString("Item");
                String date=resultSet.getString("date");
                String qty = resultSet.getString("Qty");

                Vector<String> row = new Vector<>();
                row.add(item);
                row.add(date);
                row.add(qty);
                tableModel2.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadstockData() {
        try {
            Con con = new Con();
            String query = "SELECT * FROM stock";
            ResultSet resultSet = con.statement.executeQuery(query);

            while (resultSet.next()) {
                String item = resultSet.getString("s_name");
                String qty = resultSet.getString("s_quantity");

                Vector<String> row = new Vector<>();
                row.add(item);
                row.add(qty);

                tableModel3.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadStockKeeperName(){
        try{
            User.loadUser();

            l6.setText("Stock Keeper Name: " + username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == Backbtn) {
            if (ae.getSource() == Backbtn) {
                Dashboard dashboard = new Dashboard("");
                dashboard.setVisible(true);
                this.dispose();
            }  else if (ae.getSource() == Printbtn) {
                try {
                    boolean complete1 = stockTable1.print();
                    if (!complete1) {
                        JOptionPane.showMessageDialog(this, "Printing Cancelled or Failed!", "Print Status", JOptionPane.WARNING_MESSAGE);
                    }
                    boolean complete2 = stockTable2.print();
                    if (!complete2) {
                        JOptionPane.showMessageDialog(this, "Printing Cancelled or Failed!", "Print Status", JOptionPane.WARNING_MESSAGE);
                    }

                    boolean complete3 = stockTable3.print();
                    if (!complete3) {
                        JOptionPane.showMessageDialog(this, "Printing Cancelled or Failed!", "Print Status", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (PrinterException pe) {
                    JOptionPane.showMessageDialog(this, "Printing Failed: " + pe.getMessage(), "Print Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    }}

        public static void main(String []args){
        Report report =new Report("");
    }
}
