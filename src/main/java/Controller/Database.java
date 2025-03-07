package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author loren
 */
public class Database {
    
    private static Database instance;
    
    private Database() { }
    
    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
    
    private Connection conectar() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/javaGame";
        String user = "veikr";
        String pass = "11037";
        Connection c = DriverManager.getConnection(url, user, pass);
        return c;
    }

    private void desconectar(Connection c) throws SQLException {
        c.close();
    }

    public boolean userPassExists(String username, String password) throws SQLException {
        Connection c = conectar();
        Statement st = c.createStatement();
        String query = "select * from users where username = '" + username + "' and password = '" + password + "';";
        ResultSet rs = st.executeQuery(query);
        boolean exists = false;
        if (rs.next()) {
            exists = true;
        }
        rs.close();
        st.close();
        desconectar(c);
        return exists;
    }
    
    public boolean userExists(String username) throws SQLException {
        Connection c = conectar();
        Statement st = c.createStatement();
        String query = "select * from users where username = '" + username + "';";
        ResultSet rs = st.executeQuery(query);
        boolean exists = false;
        if (rs.next()) {
            exists = true;
        }
        rs.close();
        st.close();
        desconectar(c);
        return exists;
    }

    public void createUser(String userName, String email, String password) throws SQLException {
        Connection c = conectar();
        PreparedStatement ps = c.prepareStatement("insert into users (username,password,email,wins,loses) values (?,?,?,0,0);");
        ps.setString(1, userName);
        ps.setString(2, password);
        ps.setString(3, email);
        ps.executeUpdate();
        ps.close();
        desconectar(c);
    }
    
    public String getEmail(String username, String password) throws SQLException {
        Connection c = conectar();
        Statement st = c.createStatement();
        String query = "select email from users where username = '" + username + "' and password = '" + password + "';";
        ResultSet rs = st.executeQuery(query);
        String email = "";
        if (rs.next()) {
            email = rs.getString("email");
        }
        rs.close();
        st.close();
        desconectar(c);
        return email;
    }
    
    public void deleteUser(String username, String password) throws SQLException {
        Connection c = conectar();
        Statement st = c.createStatement();
        PreparedStatement ps = c.prepareStatement("delete from users where username = '" + username + "' and password = '" + password + "';");
        ps.executeUpdate();
        ps.close();
        st.close();
        desconectar(c);
    }
    
    public ArrayList<String[]> getHighscore() throws SQLException {
        ArrayList<String[]> result = new ArrayList<>();
        Connection c = conectar();
        Statement st = c.createStatement();
        String query = "select * from score;";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String username = rs.getString("user");
            int wins = rs.getInt("wins");
            int plays = rs.getInt("plays");
            float percentage = wins*100/plays;
            String[] row = new String[4];
            row[0] = username;
            row[1] = String.valueOf(wins);
            row[2] = String.valueOf(plays);
            row[3] = String.valueOf(percentage);
            
            result.add(row);
        }
        rs.close();
        st.close();
        desconectar(c);
        return result;
    }
    
}
