/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javagame;

import Views.Login;

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
