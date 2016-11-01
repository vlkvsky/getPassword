package ua.com.vlkvsky;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class PasswordForm extends JFrame {
    private JPasswordField PasswordField;
    private static final String defaultPassword = String.valueOf(System.getProperties().getProperty("os.version"));
    private static final String sourceData = Data.getDataFile();
    private static final String destinationData = Data.getBackupFile();

    public PasswordForm() {
        this.initComponents();
    }

    private String getDefaultPassword() {
        return defaultPassword;
    }

    private void initComponents() {
        this.PasswordField = new JPasswordField();
        JLabel PasswordLabel = new JLabel();
        JButton OKButton = new JButton();
        JButton resetButton = new JButton();
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.PasswordField.setFont(new Font("Verdana", 0, 10));
        this.PasswordField.setEchoChar('X');
        PasswordLabel.setFont(new Font(null, 1, 12));
        PasswordLabel.setText("Password:");
        PasswordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 5) {
                    JOptionPane.showMessageDialog(null, "Your default password is: \n\'" + PasswordForm.defaultPassword + "\'", "Default password", 1);
                }

            }
        });
        OKButton.setFont(new Font("Verdana", 0, 11));
        OKButton.setText("Start");
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                PasswordForm.this.OKActionPerformed(evt);
            }
        });
        resetButton.setFont(new Font("Verdana", 0, 11));
        resetButton.setText("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                PasswordForm.this.ResetButtonActionPerformed(evt);
            }
        });
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.TRAILING, false).addGroup(layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(OKButton).addComponent(resetButton).addPreferredGap(ComponentPlacement.RELATED)).addGroup(Alignment.LEADING, layout.createSequentialGroup().addGap(8, 8, 8).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(PasswordLabel).addComponent(this.PasswordField, -2, 120, -2)))).addGap(8, 8, 8).addContainerGap(0, 0)));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(5, 5, 5).addPreferredGap(ComponentPlacement.RELATED).addPreferredGap(ComponentPlacement.RELATED).addPreferredGap(ComponentPlacement.RELATED).addPreferredGap(ComponentPlacement.RELATED).addComponent(PasswordLabel).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.PasswordField, -2, -1, -2).addGap(5, 5, 5).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(OKButton).addComponent(resetButton)).addPreferredGap(ComponentPlacement.RELATED, 3, 32767)));
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void OKActionPerformed(ActionEvent evt) {
        char[] Password = this.PasswordField.getPassword();
        if (Password.length == 0) {
            JOptionPane.showMessageDialog(null, "Enter password!", "Error", 1);
        } else if (!String.valueOf(Password).equals(this.getPassword()) && !String.valueOf(Password).equals(defaultPassword)) {
            this.PasswordField.setText("");
            JOptionPane.showMessageDialog(null, "Wrong password!", "Error", 0);
        } else {
            this.dispose();
            (new BuildMainForm()).setVisible(true);
        }

    }

    private void ResetButtonActionPerformed(ActionEvent evt) {
        (new PasswordForm.ResetPasswordForm()).setVisible(true);
    }

    private String getPassword() {
        String line = "";
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(sourceData));
            line = br.readLine();
            if (String.valueOf(line).equals(String.valueOf(this.PasswordField.getPassword()))) {
                return line;
            }
        } catch (IOException var14) {
            JOptionPane.showMessageDialog(null, var14.getMessage(), "File not found", 0);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }

                return line;
            } catch (IOException ignored) {
            }
        }

        return line;
    }

    class ResetPasswordForm extends JFrame {
        private JTextField jOldPasswordField;
        private JTextField jNewPasswordField;
        private JButton saveButton;
        private JButton cancelButton;
        private JTextField jPassword;

        public ResetPasswordForm() {
            this.initComponents();
        }

        private void initComponents() {
            JLabel jOldPasswortLabel = new JLabel();
            this.jOldPasswordField = new JTextField();
            this.jNewPasswordField = new JTextField();
            JLabel jNewPasswordLabel = new JLabel();
            this.jPassword = new JTextField();
            JLabel jRepeatLabel = new JLabel();
            this.saveButton = new JButton();
            this.cancelButton = new JButton();
            this.setDefaultCloseOperation(1);
            this.setResizable(false);
            this.setTitle("Change password");
            jOldPasswortLabel.setFont(new Font("Verdana", 0, 11));
            jOldPasswortLabel.setText("Old password:");
            this.jOldPasswordField.setFont(new Font("Verdana", 0, 11));
            this.jNewPasswordField.setFont(new Font("Verdana", 0, 11));
            this.jNewPasswordField.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    ResetPasswordForm.this.JTextSourceKeyPressed(evt);
                }
            });
            jNewPasswordLabel.setFont(new Font("Verdana", 0, 11));
            jNewPasswordLabel.setText("New password:");
            this.jPassword.setFont(new Font("Verdana", 0, 11));
            jRepeatLabel.setFont(new Font("Verdana", 0, 11));
            jRepeatLabel.setText("Repeat new password");
            this.saveButton.setFont(new Font("Verdana", 0, 11));
            this.saveButton.setText("Save");
            this.saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ResetPasswordForm.this.saveActionPerformed(evt);
                }
            });
            this.cancelButton.setFont(new Font("Verdana", 0, 11));
            this.cancelButton.setText("Cancel");
            this.cancelButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ResetPasswordForm.this.cancelButtonActionPerformed(evt);
                }
            });
            GroupLayout layout = new GroupLayout(this.getContentPane());
            this.getContentPane().setLayout(layout);
            layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(Alignment.TRAILING, false).addGroup(layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.saveButton).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.cancelButton, -2, 81, -2)).addGroup(Alignment.LEADING, layout.createSequentialGroup().addGap(8, 8, 8).addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(jRepeatLabel).addComponent(this.jPassword, -2, 270, -2).addComponent(jNewPasswordLabel).addComponent(this.jNewPasswordField, -2, 270, -2).addComponent(jOldPasswortLabel).addComponent(this.jOldPasswordField, -2, 270, -2)))).addContainerGap(0, 0)));
            layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addGroup(layout.createSequentialGroup().addGap(5, 5, 5).addGap(5, 5, 5).addComponent(jOldPasswortLabel).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jOldPasswordField, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(jNewPasswordLabel).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jNewPasswordField, -2, -1, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(jRepeatLabel).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jPassword, -2, -1, -2).addGap(5, 5, 5).addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(this.saveButton).addComponent(this.cancelButton)).addPreferredGap(ComponentPlacement.RELATED, 3, 32767)));
            this.pack();
            this.setLocationRelativeTo(null);
        }

        private void cancelButtonActionPerformed(ActionEvent evt) {
            this.dispose();
        }

        private void saveActionPerformed(ActionEvent evt) {
            String oldPassword = this.jOldPasswordField.getText();
            String newPassword = this.jNewPasswordField.getText();
            String repeatPassword = this.jPassword.getText();
            PasswordForm p = new PasswordForm();
            boolean newRepeatEquals = newPassword.equals(repeatPassword);
            boolean oldEquals = String.valueOf(oldPassword).equals(p.getPassword());
            boolean oldEqDefault = String.valueOf(oldPassword).equals(p.getDefaultPassword());
            boolean empty = oldPassword.isEmpty() || newPassword.isEmpty() || repeatPassword.isEmpty();
            if (!oldEquals && (!oldEqDefault || empty)) {
                JOptionPane.showMessageDialog(null, "Wrong old password.", "Error", 0);
            } else if (newRepeatEquals) {
                this.changePassword(newPassword);
            } else {
                JOptionPane.showMessageDialog(null, "Passwords don\'t match.", "Error", 0);
            }

        }

        private void changePassword(String newPassword) {
            BufferedReader br = null;
            BufferedWriter bw = null;

            try {
                br = new BufferedReader(new FileReader(PasswordForm.sourceData));
                bw = new BufferedWriter(new FileWriter(PasswordForm.destinationData));
                String oldFile = "";

                while ((oldFile = br.readLine()) != null) {
                    oldFile = oldFile.replace(PasswordForm.this.getPassword(), newPassword);
                    bw.write(oldFile + "\n");
                }
            } catch (Exception var13) {
                JOptionPane.showMessageDialog(null, "Can\'t change password.", "Changing password", 1);
            } finally {
                try {
                    if (br != null) {
                        br.close();
                        bw.close();
                    }
                } catch (IOException var12) {
                    JOptionPane.showMessageDialog(null, "Can\'t close connection.", "Changing password", 0);
                }

            }

            File oldFile1 = new File(PasswordForm.sourceData);
            oldFile1.delete();
            File newFile = new File(PasswordForm.destinationData);
            newFile.renameTo(oldFile1);
            JOptionPane.showMessageDialog(null, "Password successfully changed.", "Changing password", 1);
            this.dispose();
        }

        private void JTextSourceKeyPressed(KeyEvent evt) {
            if (this.jNewPasswordField.getText().length() == 12) {
                this.jNewPasswordField.setText(this.jNewPasswordField.getText().substring(0, this.jNewPasswordField.getText().length() - 1));
            }

        }
    }
}
