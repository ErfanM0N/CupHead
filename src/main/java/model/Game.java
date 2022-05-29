package model;

import java.util.ArrayList;

public class Game {
    private static Game instance = null;

    public static Game getInstance() {
        if (instance == null)
            instance = new Game();
        return instance;
    }

    private int lives;
    private double damage;
    private double damageBoss;
    private Game() {
        this.lives = 5;
        this.damage = 1;
        this.damageBoss = 1;
    }

    private ArrayList<User> users = new ArrayList<>();
    private User loginUser;
    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser (User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    public User getLoginUser() {
        return loginUser;
    }

    public int getLives() {
        return lives;
    }

    public double getDamage() {
        return damage;
    }

    public double getDamageBoss() {
        return damageBoss;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void setDamageBoss(double damageBoss) {
        this.damageBoss = damageBoss;
    }
}
