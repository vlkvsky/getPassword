//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ua.com.vlkvsky;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import ua.com.vlkvsky.Mail;

class SendEmailForm extends JFrame {
    private JTextField jMail;

    public SendEmailForm() {
        this.initComponents();
    }

    private void initComponents() {
        this.jMail = new JTextField();
        JLabel jMailLabel = new JLabel();
        JButton saveButton = new JButton();
        JButton cancelButton = new JButton();
        this.setDefaultCloseOperation(1);
        this.setResizable(false);
        this.setTitle("Send to Email");
        this.jMail.setFont(new Font("Verdana", 0, 11));
        jMailLabel.setFont(new Font("Verdana", 0, 11));
        jMailLabel.setText("Enter the email where to send: ");
        saveButton.setFont(new Font("Verdana", 0, 11));
        saveButton.setText("Send");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SendEmailForm.this.saveActionPerformed(evt);
            }
        });
        cancelButton.setFont(new Font("Verdana", 0, 11));
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                SendEmailForm.this.cancelButtonActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.TRAILING, false).addGroup(layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(saveButton).addPreferredGap(ComponentPlacement.RELATED).addComponent(cancelButton, -2, 81, -2)).addGroup(Alignment.LEADING, layout.createSequentialGroup().addGap(8, 8, 8).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jMailLabel).addComponent(this.jMail, -2, 270, -2)))).addContainerGap(0, 0)));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(5, 5, 5).addGap(5, 5, 5).addPreferredGap(ComponentPlacement.RELATED).addPreferredGap(ComponentPlacement.RELATED).addPreferredGap(ComponentPlacement.RELATED).addPreferredGap(ComponentPlacement.RELATED).addComponent(jMailLabel).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jMail, -2, -1, -2).addGap(5, 5, 5).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(saveButton).addComponent(cancelButton)).addPreferredGap(ComponentPlacement.RELATED, 3, 32767)));
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void cancelButtonActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    private void saveActionPerformed(ActionEvent evt) {
        String jMailText = this.jMail.getText();
        if(jMailText.contains("@") & jMailText.contains(".")) {
            new Mail(jMailText);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Wrong email", "Error", 0);
        }

    }
}
