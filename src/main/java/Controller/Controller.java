package Controller;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

/**
 *
 * @author Veikr
 */
public class Controller {
    
    private static Controller instance;
    
    private Controller() { }
    
    public static Controller getController() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }
    
    public void textFieldsBehaviour(JTextField userTB, JTextField emailTB) {
        userTB.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (userTB.getText().equals("Usuario")) {
                    userTB.setText("");
                    userTB.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (userTB.getText().isEmpty()) {
                    userTB.setText("Usuario");
                    userTB.setForeground(Color.GRAY);
                }
            }
        });
        
        emailTB.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (emailTB.getText().equals("E-Mail")) {
                    emailTB.setText("");
                    emailTB.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (emailTB.getText().isEmpty()) {
                    emailTB.setText("E-Mail");
                    emailTB.setForeground(Color.GRAY);
                }
            }
        });
    }
}
