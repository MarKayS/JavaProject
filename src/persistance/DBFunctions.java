package persistance;

import java.sql.*;

import core.Level;
import core.Player;

import java.util.ArrayList;

public class DBFunctions {

    public static ArrayList<Player> getPlayers(){
        ArrayList<Player> players = new ArrayList<>();
        String select = "SELECT * FROM Player";

        //Querries:
        Statement statement;
        Connection connection = null;
        try {
            DBConnection.connect();
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while(resultSet.next()){
                int playerID = resultSet.getInt("playerID");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String password = resultSet.getString("password");
                String nickname = resultSet.getString("nickname");
                Boolean admin_rights = resultSet.getBoolean("admin_rights");

                Player player = new Player(playerID ,name, surname, password, nickname, admin_rights);
                players.add(player);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        /*for(Player player: players){
            System.out.print(player.getNickname() + "\t" + player.getPassword() + "\n") ;
        }*/
        return players;
    }
    /* //WE DONT HAVE GAME TABLE ANYMORE
    public static ArrayList<String> getGames(){
        String select = "SELECT * from Game";
        ArrayList<String> games = new ArrayList<>();

        Statement statement;
        Connection connection = null;
        try {
            DBConnection.connect();
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select);

            while(resultSet.next()){
                int gameID = resultSet.getInt("gameNumber");
                String gameName = resultSet.getString("gameName");
                String game = String.valueOf(gameID) + ". - " + gameName;
                games.add(game);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return games;
    }
    */
    public static ArrayList<Level> getLevels(int selectGameID){
        ArrayList<Level> levels = new ArrayList<>();
        String select = "SELECT * from Level WHERE gameNumber = ";

        Statement statement;
        Connection connection = null;
        try {
            DBConnection.connect();
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(select + selectGameID);

            while(resultSet.next()){
                int levelID = resultSet.getInt("levelID");
                int gameNumber = resultSet.getInt("gameNumber");
                int levelNumber = resultSet.getInt("levelNumber");
                String levelParse = resultSet.getString("level");
                levels.add(new Level(levelID, gameNumber, levelNumber, levelParse));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return levels;
    }

    public static int verifyNickname(String nickname){
        ArrayList<Player> players = DBFunctions.getPlayers();
        for (int i = 0; i < players.size(); i++){
            if (nickname.toLowerCase().equals(players.get(i).getNickname().toLowerCase())){
                return i;
            }
        }
        return -1;
    }

    public static boolean verifyPassword(int id, String password){
        ArrayList<Player> players = DBFunctions.getPlayers();
        Player player = players.get(id);
            if (password.toLowerCase().equals(player.getPassword().toLowerCase())){
                return true;
            }
        return false;
    }
    public static boolean register(String name, String surname, String nickname, String password){

        Connection connection = null;
        PreparedStatement statement;
        try {
            DBConnection.connect();
            connection = DBConnection.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            statement = connection.prepareStatement("INSERT INTO Player (name, surname, nickname, password, admin_rights) VALUES (?,?,?,?,false)");
            statement.setString(1,name);
            statement.setString(2,surname);
            statement.setString(3,nickname);
            statement.setString(4,password);
            statement.executeUpdate();

        } catch (SQLException e) {
        e.printStackTrace();
        }
        return true;
    }
}
