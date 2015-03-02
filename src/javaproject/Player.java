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

    public int getPlayerID() {
        return playerID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isAdmin_rights() {
        return admin_rights;
    }
}