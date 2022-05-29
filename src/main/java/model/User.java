package model;

public class User {
    String username;
    String password;
    int score;
    int avatarNumber;

    public User(String username, String password,int avatarNumber) {
        this.username = username;
        this.password = password;
        this.score = 0;
        this.avatarNumber = avatarNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getAvatarNumber() {
        return avatarNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setAvatarNumber(int avatarNumber) {
        this.avatarNumber = avatarNumber;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
