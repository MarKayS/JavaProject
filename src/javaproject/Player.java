package javaproject;

public class Player {
    int playerID;
    String name;
    String surname;
    String password;
    String nickname;
    boolean admin_rights;

    public Player(int playerID, String name, String surname, String password, String nickname, boolean admin_rights) {
        this.playerID = playerID;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.nickname = nickname;
        this.admin_rights = admin_rights;
    }
}