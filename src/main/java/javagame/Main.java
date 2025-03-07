package javagame;

import Views.Login;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author loren
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static Login login;
    
    public static void main(String[] args) {
        login = new Login();
        login.setVisible(true);
    }
    
}
