package Controller;

/**
 *
 * @author Veikr
 */
public class Score {
    
    private String username;
    private int wins;
    private int plays;
    private float percentage;
    
    public Score(String username, int wins, int plays, float percentage) {
        this.username = username;
        this.wins = wins;
        this.plays = plays;
        this.percentage = percentage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getPlays() {
        return plays;
    }

    public void setPlays(int plays) {
        this.plays = plays;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
    
    
    
}
