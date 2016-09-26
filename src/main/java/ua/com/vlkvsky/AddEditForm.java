package ua.com.vlkvsky;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class AddEditForm extends JFrame {

    private boolean formMode;
    private Data editDataDetails;
    private JTextField jSource;
    private JTextField jLogin;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField jPassword;
    private JTextField jTextStatus;

    public AddEditForm() {
        initComponents();
    }

    protected void UpdateStatus() {
        if (formMode) {
            jTextStatus.setText("Add Data Mode");
            saveButton.setText(formMode ? "Save" : "Update");
        } else {
            jTextStatus.setText("Edit Data Mode");
            saveButton.setText(formMode ? "Save" : "Update");
        }
    }

    protected void MapTextBox(Data c) {
        if (c != null) {
            jSource.setText(c.getSource());
            jLogin.setText(c.getLogin());
            jPassword.setText(c.getPassword());
            editDataDetails = c;
        }
    }

    private void CloseDialog() {
        this.dispose();
    }

    private void initComponents() {

        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        jSource = new JTextField();
        jLogin = new JTextField();
        JLabel jLabel3 = new JLabel();
        jPassword = new JTextField();
        JLabel jLabel4 = new JLabel();
        saveButton = new JButton();
        cancelButton = new JButton();
        jTextStatus = new JTextField();

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setResizable(false);
        setTitle("Password Checker");

        jLabel1.setFont(new Font("Verdana", 0, 12));
        jLabel1.setForeground(new Color(0, 0, 0));
        jLabel1.setText("Enter info:");

        jLabel2.setFont(new Font("Verdana", 0, 11));
        jLabel2.setText("Source");

        jSource.setFont(new Font("Verdana", 0, 11));

        jLogin.setFont(new Font("Verdana", 0, 11));
        jLogin.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                JTextSourceKeyPressed(evt);
            }
        });

        jLabel3.setFont(new Font("Verdana", 0, 11));
        jLabel3.setText("Login");

        jPassword.setFont(new Font("Verdana", 0, 11));

        jLabel4.setFont(new Font("Verdana", 0, 11));
        jLabel4.setText("Password");

        saveButton.setFont(new Font("Verdana", 0, 11));
        saveButton.setText("Save");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        cancelButton.setFont(new Font("Verdana", 0, 11));
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                canceButtonActionPerformed(evt);
            }
        });

        jTextStatus.setBackground(new Color(0, 122, 153));
        jTextStatus.setFont(new Font("", 2, 10));
        jTextStatus.setToolTipText("Application Status");
        jTextStatus.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextStatus.setDisabledTextColor(new Color(255, 255, 255));
        jTextStatus.setEnabled(false);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(saveButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cancelButton, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jPassword, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLogin, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jSource, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(0, 0))
                        .addComponent(jTextStatus)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel1)
                                .addGap(5, 5, 5)
                                .addComponent(jLabel2)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSource, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(saveButton)
                                        .addComponent(cancelButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
                                .addComponent(jTextStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void canceButtonActionPerformed(ActionEvent evt) {
        CloseDialog();
    }

    private void saveActionPerformed(ActionEvent evt) {
        String Source = jSource.getText();
        String Login = jLogin.getText();
        String Password = jPassword.getText();
        String buildData = "";

        if (!Source.isEmpty()) {
            buildData += Source + ",";
        } else {
            buildData += "NULL" + ",";
        }

        if (!Login.isEmpty()) {
            buildData += Login + ",";
        } else {
            buildData += "NULL" + ",";
        }

        if (!Password.isEmpty()) {
            buildData += Password + ",";
        } else {
            buildData += "NULL" + ",";
        }

        buildData = buildData.substring(0, buildData.length() - 1);

        if (formMode) {
            if (TableUtility.appendText(buildData + "\r\n")) {
                TableUtility.read();
                BuildMainForm.BindIntoJTable();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add source : " + Source, "Error", 0);
            }
        } else {
            if (TableUtility.updateData(editDataDetails.getSource(),
                    editDataDetails.getLogin(),
                    editDataDetails.getPassword(), buildData)) {
                TableUtility.read();
                BuildMainForm.BindIntoJTable();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to update source : " + Source, "Error", 0);
            }
        }
    }

    private void JTextSourceKeyPressed(KeyEvent evt) {
        if (jLogin.getText().length() == 12) {
            jLogin.setText(jLogin.getText().substring(0, jLogin.getText().length() - 1));
        }
    }

    public void setFormMode(boolean formMode) {
        this.formMode = formMode;
    }
}