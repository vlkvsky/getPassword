package ua.com.vlkvsky;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

class Main {
    private Main() {
    }

    public static void main(String[] args) {
        try {
            LookAndFeelInfo[] ex = UIManager.getInstalledLookAndFeels();
            int var2 = ex.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                LookAndFeelInfo info = ex[var3];
                if("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException | ClassNotFoundException var5) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, var5);
        }

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                (new PasswordForm()).setVisible(true);
            }
        });
    }
}
