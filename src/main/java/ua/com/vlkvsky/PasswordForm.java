package ua.com.vlkvsky;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PasswordForm extends JFrame {
    private JButton OKButton;
    private JButton ResetButton;
    private JPasswordField PasswordField;
    private static String password = "8800"; // todo вынести в отдельный файл
    private static String defaultPassword = String.valueOf(System.getProperties().getProperty("os.version"));

    public PasswordForm() {
        initComponents();
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }

    private void initComponents() {

        PasswordField = new JPasswordField();
        JLabel PasswordLabel = new JLabel();
        OKButton = new JButton();
        ResetButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        PasswordField.setFont(new Font("Verdana", 0, 10));
        PasswordField.setEchoChar('X');

        PasswordLabel.setFont(new Font(null, Font.BOLD, 12));
        PasswordLabel.setText("Password:");
        PasswordLabel.addMouseListener(new MouseAdapter() {

            public void mouseClicked (MouseEvent me) {
                if (me.getClickCount() == 5) {
                    JOptionPane.showMessageDialog(null,"Your default password is: \n" +
                            "'" + defaultPassword + "'", "Default password", 1);
                }
            }
        });

        OKButton.setFont(new Font("Verdana", 0, 11));
        OKButton.setText("Start");
        OKButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                OKActionPerformed(evt);
            }
        });

        ResetButton.setFont(new Font("Verdana", 0, 11));
        ResetButton.setText("Reset");
        ResetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                ResetButtonActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(OKButton)
                                                .addComponent(ResetButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        )
                                        .addGroup(GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addGap(8, 8, 8)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(PasswordLabel)
                                                        .addComponent(PasswordField, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                                )))
                                .addGap(8, 8, 8)
                                .addContainerGap(0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PasswordLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(OKButton)
                                        .addComponent(ResetButton))

                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE)
                        ));

        pack();
        setLocationRelativeTo(null);
    }

    private void OKActionPerformed(ActionEvent evt) {
        char[] Password = PasswordField.getPassword();
        if (Password.length == 0) {
            JOptionPane.showMessageDialog(null, "Enter password!", "Error", 1);
        } else {
            if (String.valueOf(Password).equals(getPassword()) ||
                    String.valueOf(Password).equals(defaultPassword)) {
                this.dispose();
                new BuildMainForm().setVisible(true);
            } else {
                PasswordField.setText("");
                JOptionPane.showMessageDialog(null, "Wrong password!", "Error", 0);
            }
        }
    }

    private void ResetButtonActionPerformed(ActionEvent evt) {
        new ResetPasswordForm().setVisible(true);
    }

    public String getPassword() {
        return this.password;
    }

    public static String setPassword(String newPassword) {
        password = newPassword;
        return password;
    }
}

class ResetPasswordForm extends JFrame {

    private JTextField jOldPasswordField;
    private JTextField jNewPasswordField;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField jPassword;

    public ResetPasswordForm() {
        initComponents();
    }

    private void initComponents() {

        JLabel jOldPasswortLabel = new JLabel();
        jOldPasswordField = new JTextField();
        jNewPasswordField = new JTextField();
        JLabel jNewPasswordLabel = new JLabel();
        jPassword = new JTextField();
        JLabel jRepeatLabel = new JLabel();
        saveButton = new JButton();
        cancelButton = new JButton();

        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        setResizable(false);
        setTitle("Change password");

        jOldPasswortLabel.setFont(new Font("Verdana", 0, 11));
        jOldPasswortLabel.setText("Old password:");

        jOldPasswordField.setFont(new Font("Verdana", 0, 11));

        jNewPasswordField.setFont(new Font("Verdana", 0, 11));
        jNewPasswordField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                JTextSourceKeyPressed(evt);
            }
        });

        jNewPasswordLabel.setFont(new Font("Verdana", 0, 11));
        jNewPasswordLabel.setText("New password:");

        jPassword.setFont(new Font("Verdana", 0, 11));

        jRepeatLabel.setFont(new Font("Verdana", 0, 11));
        jRepeatLabel.setText("Repeat new password");

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
                cancelButtonActionPerformed(evt);
            }
        });

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
                                                        .addComponent(jRepeatLabel)
                                                        .addComponent(jPassword, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jNewPasswordLabel)
                                                        .addComponent(jNewPasswordField, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jOldPasswortLabel)
                                                        .addComponent(jOldPasswordField, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(0, 0))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGap(5, 5, 5)
                                .addComponent(jOldPasswortLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jOldPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jNewPasswordLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jNewPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRepeatLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(saveButton)
                                        .addComponent(cancelButton))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 3, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void cancelButtonActionPerformed(ActionEvent evt) {
        this.dispose();
    }

    private void saveActionPerformed(ActionEvent evt) {
        String oldPassword = jOldPasswordField.getText();
        String newPassword = jNewPasswordField.getText();
        String repeatPassword = jPassword.getText();

        PasswordForm p = new PasswordForm();
        boolean newRepeatEquals = newPassword.equals(repeatPassword);
        boolean oldEquals = String.valueOf(oldPassword).equals(p.getPassword());
        boolean oldEqDefault = String.valueOf(oldPassword).equals(p.getDefaultPassword());
        boolean empty = oldPassword.isEmpty() || newPassword.isEmpty() || repeatPassword.isEmpty();

        if (oldEquals ||oldEqDefault && !empty) {
            if (newRepeatEquals) {
                changePassword(newPassword);
            } else {
                JOptionPane.showMessageDialog(null, "Passwords don't match.", "Error", 0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Wrong old password.", "Error", 0);
        }
    }

    private void changePassword(String newPassword) {
        PasswordForm.setPassword(newPassword);
        JOptionPane.showMessageDialog(null, "Password successfully changed.", "Changing password", 1);
        this.dispose();
    }

    private void JTextSourceKeyPressed(KeyEvent evt) {
        if (jNewPasswordField.getText().length() == 12) {
            jNewPasswordField.setText(jNewPasswordField.getText().substring(0, jNewPasswordField.getText().length() - 1));
        }
    }
}