package ua.com.vlkvsky;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import ua.com.vlkvsky.BuildMainForm;
import ua.com.vlkvsky.Data;
import ua.com.vlkvsky.TableUtility;

class AddEditForm extends JFrame {
    private boolean formMode;
    private Data editDataDetails;
    private JTextField jSource;
    private JTextField jLogin;
    private JButton saveButton;
    private JTextField jPassword;
    private JTextField jTextStatus;

    public AddEditForm() {
        this.initComponents();
    }

    void UpdateStatus() {
        if(this.formMode) {
            this.jTextStatus.setText("Add Data Mode");
            this.saveButton.setText(this.formMode?"Save":"Update");
        } else {
            this.jTextStatus.setText("Edit Data Mode");
            this.saveButton.setText(this.formMode?"Save":"Update");
        }

    }

    void MapTextBox(Data c) {
        if(c != null) {
            this.jSource.setText(c.getSource());
            this.jLogin.setText(c.getLogin());
            this.jPassword.setText(c.getPassword());
            this.editDataDetails = c;
        }

    }

    private void CloseDialog() {
        this.dispose();
    }

    private void initComponents() {
        JLabel jLabel1 = new JLabel();
        JLabel jLabel2 = new JLabel();
        this.jSource = new JTextField();
        this.jLogin = new JTextField();
        JLabel jLabel3 = new JLabel();
        this.jPassword = new JTextField();
        JLabel jLabel4 = new JLabel();
        this.saveButton = new JButton();
        JButton cancelButton = new JButton();
        this.jTextStatus = new JTextField();
        this.setDefaultCloseOperation(1);
        this.setResizable(false);
        this.setTitle("Password Checker");
        jLabel1.setFont(new Font("Verdana", 0, 12));
        jLabel1.setForeground(new Color(0, 0, 0));
        jLabel1.setText("Enter info:");
        jLabel2.setFont(new Font("Verdana", 0, 11));
        jLabel2.setText("Source");
        this.jSource.setFont(new Font("Verdana", 0, 11));
        this.jLogin.setFont(new Font("Verdana", 0, 11));
        this.jLogin.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                AddEditForm.this.JTextSourceKeyPressed(evt);
            }
        });
        jLabel3.setFont(new Font("Verdana", 0, 11));
        jLabel3.setText("Login");
        this.jPassword.setFont(new Font("Verdana", 0, 11));
        jLabel4.setFont(new Font("Verdana", 0, 11));
        jLabel4.setText("Password");
        this.saveButton.setFont(new Font("Verdana", 0, 11));
        this.saveButton.setText("Save");
        this.saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AddEditForm.this.saveActionPerformed(evt);
            }
        });
        cancelButton.setFont(new Font("Verdana", 0, 11));
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AddEditForm.this.canceButtonActionPerformed(evt);
            }
        });
        this.jTextStatus.setBackground(new Color(0, 122, 153));
        this.jTextStatus.setFont(new Font("", 2, 10));
        this.jTextStatus.setToolTipText("Application Status");
        this.jTextStatus.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        this.jTextStatus.setDisabledTextColor(new Color(255, 255, 255));
        this.jTextStatus.setEnabled(false);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.TRAILING, false).addGroup(layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.saveButton).addPreferredGap(ComponentPlacement.RELATED).addComponent(cancelButton, -2, 81, -2)).addGroup(Alignment.LEADING, layout.createSequentialGroup().addGap(8, 8, 8).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel4).addComponent(this.jPassword, -2, 270, -2).addComponent(jLabel3).addComponent(this.jLogin, -2, 270, -2).addComponent(jLabel2).addComponent(jLabel1).addComponent(this.jSource, -2, 270, -2)))).addContainerGap(0, 0)).addComponent(this.jTextStatus));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(5, 5, 5).addComponent(jLabel1).addGap(5, 5, 5).addComponent(jLabel2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jSource, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(jLabel3).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jLogin, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(jLabel4).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPassword, -2, -1, -2).addGap(5, 5, 5).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.saveButton).addComponent(cancelButton)).addPreferredGap(ComponentPlacement.RELATED, 3, 32767).addComponent(this.jTextStatus, -2, -1, -2)));
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void canceButtonActionPerformed(ActionEvent evt) {
        this.CloseDialog();
    }

    private void saveActionPerformed(ActionEvent evt) {
        String Source = this.jSource.getText();
        String Login = this.jLogin.getText();
        String Password = this.jPassword.getText();
        String buildData = "";
        if(!Source.isEmpty()) {
            buildData = buildData + Source + ",";
        } else {
            buildData = buildData + "NULL,";
        }

        if(!Login.isEmpty()) {
            buildData = buildData + Login + ",";
        } else {
            buildData = buildData + "NULL,";
        }

        if(!Password.isEmpty()) {
            buildData = buildData + Password + ",";
        } else {
            buildData = buildData + "NULL,";
        }

        buildData = buildData.substring(0, buildData.length() - 1);
        if(this.formMode) {
            if(TableUtility.appendText(buildData + "\r\n")) {
                TableUtility.read();
                BuildMainForm.BindIntoJTable();
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add source : " + Source, "Error", 0);
            }
        } else if(TableUtility.updateData(this.editDataDetails.getSource(), this.editDataDetails.getLogin(), this.editDataDetails.getPassword(), buildData)) {
            TableUtility.read();
            BuildMainForm.BindIntoJTable();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Failed to update source : " + Source, "Error", 0);
        }

    }

    private void JTextSourceKeyPressed(KeyEvent evt) {
        if(this.jLogin.getText().length() == 50) {
            this.jLogin.setText(this.jLogin.getText().substring(0, this.jLogin.getText().length() - 1));
        }

    }

    public void setFormMode(boolean formMode) {
        this.formMode = formMode;
    }
}
