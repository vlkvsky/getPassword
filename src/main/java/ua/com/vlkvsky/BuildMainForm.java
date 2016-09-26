package ua.com.vlkvsky;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuildMainForm extends JFrame {
    private static JTable jTable;
    private JTextField jTextSearch;
    private static JTextField jTextStatus;

    protected BuildMainForm() {
        initComponents();
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
        jTextSearch = new JTextField();
        JButton searchButton = new JButton();
        JButton restoreButton = new JButton();
        JMenuBar jMenuBar1 = new JMenuBar();

        jTable.getTableHeader().setFont(new Font("Verdana", 0, 11));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Password Checker");
        setResizable(false);

        jScrollPane1.setFont(new Font("Verdana", 0, 11));

        jTable.setFont(new Font("Verdana", 0, 11));
        jTable.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null}},
                new String[]{
                        "Source", "Login", "Password"
                }
        ));
        jScrollPane1.setViewportView(jTable);

        addButton.setFont(new Font(null, Font.BOLD, 12));
        addButton.setText("Add");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        editButton.setFont(new Font(null, Font.BOLD, 12));
        editButton.setText("Edit");
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new Font(null, Font.BOLD, 12));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        jTextStatus.setBackground(new Color(0, 122, 153));
        jTextStatus.setFont(new Font("", 2, 10));
        jTextStatus.setToolTipText("Application Status");
        jTextStatus.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextStatus.setDisabledTextColor(new Color(255, 255, 255));
        jTextStatus.setEnabled(false);

        copyRight.setFont(new Font(null, Font.BOLD, 16));
        copyRight.setForeground(new Color(0, 0, 0));
        copyRight.setText("©");
        copyRight.setToolTipText("Created by Vadim Volkovskiy");

        jLabel1.setFont(new Font(null, Font.ITALIC, 14));
        jLabel1.setForeground(new Color(0, 0, 0));
        jLabel1.setText("Search: ");

        jTextSearch.setFont(new Font("Verdana", 0, 11));

        searchButton.setText("Search");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        restoreButton.setFont(new Font(null, Font.BOLD, 12));
        restoreButton.setForeground(new Color(0, 0, 0));
        restoreButton.setToolTipText("Reload from backup");
        restoreButton.setText("Reload");
        restoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                RestoreButtonActionPerformed(evt);
            }
        });

        setJMenuBar(jMenuBar1);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(jTextStatus)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addComponent(jTextSearch, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(searchButton)
                                        )
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(addButton)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(editButton)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(deleteButton)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(restoreButton)
                                                                .addGap(472)
                                                                .addComponent(copyRight)
                                                        ))
                                                .addGap(0, 3, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jTextSearch, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)

                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(addButton, GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(editButton)
                                                .addComponent(copyRight)
                                                .addComponent(deleteButton)
                                                .addComponent(restoreButton)))
                                .addGap(2, 2, 2)
                                .addComponent(jTextStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }

    protected static void BindIntoJTable() {
        jTable.removeAll();
        List<Data> data = TableUtility.getTableData();
        if (data != null) {
            int index = 1;
            String colNames[] = {"ID", "Source", "Login", "Password"};
            DefaultTableModel dtm = new DefaultTableModel(null, colNames);

            for (int i = 0; i < data.size(); i++) {
                dtm.addRow(new String[4]);
            }
            jTable.setModel(dtm);

            if (data.size() > 0) {
                int row = 0;
                for (Data c : data) {
                    jTable.setValueAt(Integer.toString(index), row, 0);
                    jTable.setValueAt(c.getSource(), row, 1);
                    jTable.setValueAt(c.getLogin(), row, 2);
                    jTable.setValueAt(c.getPassword(), row, 3);
                    index++;
                    row++;
                }
                jTable.getColumn("ID").setMaxWidth(30);
                jTable.getColumn("Source").setMaxWidth(500);
                jTable.getColumn("Login").setMaxWidth(500);
                jTable.getColumn("Password").setMaxWidth(500);
                jTextStatus.setText("Finish Load Data," + Integer.toString(data.size())
                        + " records found");
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
        if (row != -1) {

            String source = (String) jTable.getValueAt(row, 1);
            String login = (String) jTable.getValueAt(row, 2);
            String password = (String) jTable.getValueAt(row, 3);

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
        if (row != -1) {
            String source = (String) jTable.getValueAt(row, 1);
            String login = (String) jTable.getValueAt(row, 2);
            String password = (String) jTable.getValueAt(row, 3);

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

    private void SearchButtonActionPerformed(ActionEvent evt) {
        String searchValue = jTextSearch.getText();
        java.util.List<Data> datas = TableUtility.search(searchValue);
        TableUtility.setTableData(datas);
        BindIntoJTable();
    }
}
