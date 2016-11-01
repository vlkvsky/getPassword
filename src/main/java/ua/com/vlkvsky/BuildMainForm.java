package ua.com.vlkvsky;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import ua.com.vlkvsky.AddEditForm;
import ua.com.vlkvsky.Data;
import ua.com.vlkvsky.SendEmailForm;
import ua.com.vlkvsky.TableUtility;

class BuildMainForm extends JFrame {
    private static JTable jTable;
    private JTextField jTextSearch;
    private static JTextField jTextStatus;

    public BuildMainForm() {
        this.initComponents();
        TableUtility.initTable();
        BindIntoJTable();
    }

    private void initComponents() {
        JScrollPane jScrollPane1 = new JScrollPane();
        jTable = new JTable();
        JButton addButton = new JButton();
        JButton editButton = new JButton();
        JButton deleteButton = new JButton();
        jTextStatus = new JTextField();
        JLabel jLabel1 = new JLabel();
        JLabel copyRight = new JLabel();
        this.jTextSearch = new JTextField();
        JButton searchButton = new JButton();
        JButton restoreButton = new JButton();
        JButton emailButton = new JButton();
        JMenuBar jMenuBar1 = new JMenuBar();
        jTable.getTableHeader().setFont(new Font("Verdana", 0, 11));
        this.setDefaultCloseOperation(3);
        this.setTitle("Password Checker");
        this.setResizable(false);
        jScrollPane1.setFont(new Font("Verdana", 0, 11));
        jTable.setFont(new Font("Verdana", 0, 11));
        jTable.setModel(new DefaultTableModel(new Object[][]{{null, null, null}}, new String[]{"Source", "Login", "Password"}));
        jScrollPane1.setViewportView(jTable);
        addButton.setFont(new Font(null, 1, 12));
        addButton.setText("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BuildMainForm.this.addButtonActionPerformed(evt);
            }
        });
        editButton.setFont(new Font(null, 1, 12));
        editButton.setText("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BuildMainForm.this.EditButtonActionPerformed(evt);
            }
        });
        deleteButton.setFont(new Font(null, 1, 12));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BuildMainForm.this.DeleteButtonActionPerformed(evt);
            }
        });
        jTextStatus.setBackground(new Color(0, 122, 153));
        jTextStatus.setFont(new Font("", 2, 10));
        jTextStatus.setToolTipText("Application Status");
        jTextStatus.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextStatus.setDisabledTextColor(new Color(255, 255, 255));
        jTextStatus.setEnabled(false);
        copyRight.setFont(new Font(null, 1, 16));
        copyRight.setForeground(new Color(0, 0, 0));
        copyRight.setText("©");
        copyRight.setToolTipText("Created by Vadim Volkovskiy");
        jLabel1.setFont(new Font(null, 2, 14));
        jLabel1.setForeground(new Color(0, 0, 0));
        jLabel1.setText("Search: ");
        this.jTextSearch.setFont(new Font("Verdana", 0, 11));
        searchButton.setText("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BuildMainForm.this.SearchButtonActionPerformed(evt);
            }
        });
        restoreButton.setFont(new Font(null, 1, 12));
        restoreButton.setForeground(new Color(0, 0, 0));
        restoreButton.setToolTipText("Reload from backup");
        restoreButton.setText("Reload");
        restoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BuildMainForm.this.RestoreButtonActionPerformed(evt);
            }
        });
        emailButton.setFont(new Font(null, 1, 12));
        emailButton.setForeground(new Color(0, 0, 0));
        emailButton.setToolTipText("Send to your Email");
        emailButton.setText("Send");
        emailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                BuildMainForm.this.EmailButtonActionPerformed(evt);
            }
        });
        this.setJMenuBar(jMenuBar1);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jTextStatus).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jScrollPane1, Alignment.TRAILING, -2, 0, 32767).addGroup(layout.createSequentialGroup().addComponent(jLabel1).addComponent(this.jTextSearch, -2, 600, -2).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addComponent(searchButton)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(addButton).addPreferredGap(ComponentPlacement.RELATED).addComponent(editButton).addPreferredGap(ComponentPlacement.RELATED).addComponent(deleteButton).addPreferredGap(ComponentPlacement.RELATED).addComponent(restoreButton).addPreferredGap(ComponentPlacement.RELATED).addComponent(emailButton).addGap(370).addComponent(copyRight))).addGap(0, 3, 32767))).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(10, 10, 10).addPreferredGap(ComponentPlacement.UNRELATED).addPreferredGap(ComponentPlacement.RELATED, -1, 32767).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(this.jTextSearch, -2, -1, -2).addComponent(searchButton, -2, 19, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(jScrollPane1, -2, 300, -2).addGap(5, 5, 5).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(addButton, Alignment.TRAILING).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(editButton).addComponent(copyRight).addComponent(deleteButton).addComponent(restoreButton).addComponent(emailButton))).addGap(2, 2, 2).addComponent(jTextStatus, -2, -1, -2).addGap(0, 0, 0)));
        this.pack();
        this.setLocationRelativeTo(null);
    }

    static void BindIntoJTable() {
        jTable.removeAll();
        List data = TableUtility.getTableData();
        if(data != null) {
            int index = 1;
            String[] colNames = new String[]{"ID", "Source", "Login", "Password"};
            DefaultTableModel dtm = new DefaultTableModel(null, colNames);

            int row;
            for(row = 0; row < data.size(); ++row) {
                dtm.addRow(new String[4]);
            }

            jTable.setModel(dtm);
            if(data.size() > 0) {
                row = 0;

                for(Iterator var5 = data.iterator(); var5.hasNext(); ++row) {
                    Data c = (Data)var5.next();
                    jTable.setValueAt(Integer.toString(index), row, 0);
                    jTable.setValueAt(c.getSource(), row, 1);
                    jTable.setValueAt(c.getLogin(), row, 2);
                    jTable.setValueAt(c.getPassword(), row, 3);
                    ++index;
                }

                jTable.getColumn("ID").setMaxWidth(30);
                jTable.getColumn("Source").setMaxWidth(500);
                jTable.getColumn("Login").setMaxWidth(500);
                jTable.getColumn("Password").setMaxWidth(500);
                jTextStatus.setText("Finish Load Data," + Integer.toString(data.size()) + " records found");
            } else {
                jTextStatus.setText("Finish Load Data, No record found");
            }
        }

    }

    private void addButtonActionPerformed(ActionEvent evt) {
        AddEditForm Form = new AddEditForm();
        Form.setFormMode(true);
        Form.UpdateStatus();
        Form.setVisible(true);
    }

    private void EditButtonActionPerformed(ActionEvent evt) {
        int row = jTable.getSelectedRow();
        if(row != -1) {
            String source = (String)jTable.getValueAt(row, 1);
            String login = (String)jTable.getValueAt(row, 2);
            String password = (String)jTable.getValueAt(row, 3);
            Data C = new Data();
            C.setPassword(password);
            C.setSource(source);
            C.setLogin(login);
            AddEditForm dlg = new AddEditForm();
            dlg.setFormMode(false);
            dlg.MapTextBox(C);
            dlg.UpdateStatus();
            dlg.setVisible(true);
        }

    }

    private void DeleteButtonActionPerformed(ActionEvent evt) {
        int row = jTable.getSelectedRow();
        if(row != -1) {
            String source = (String)jTable.getValueAt(row, 1);
            String login = (String)jTable.getValueAt(row, 2);
            String password = (String)jTable.getValueAt(row, 3);
            Data C = new Data();
            C.setPassword(password);
            C.setSource(source);
            C.setLogin(login);
            TableUtility.deleteData(C);
        }

    }

    private void RestoreButtonActionPerformed(ActionEvent evt) {
        TableUtility.readFromBackup();
        BindIntoJTable();
    }

    private void EmailButtonActionPerformed(ActionEvent evt) {
        SendEmailForm Form = new SendEmailForm();
        Form.setVisible(true);
    }

    private void SearchButtonActionPerformed(ActionEvent evt) {
        String searchValue = this.jTextSearch.getText();
        List datas = TableUtility.search(searchValue);
        TableUtility.setTableData(datas);
        BindIntoJTable();
    }
}
