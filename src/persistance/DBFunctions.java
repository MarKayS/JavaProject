package persistance;

import java.sql.*;

import core.Level;
import core.Player;
import ui.MainMenu;

import java.util.ArrayList;

public class DBFunctions {

    private static int playerID;

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
                String levelName = resultSet.getString("levelName");
                String levelParse = resultSet.getString("level");
                levels.add(new Level(levelID, gameNumber, levelNumber, levelParse, levelName));
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
                playerID = players.get(i).getPlayerID();
                return i;
            }
        }
        return -1;
    }

    public static int reportScore(int moves, int time, int levelID){
        Connection connection = null;
        PreparedStatement statement;
        try {
            DBConnection.connect();
            connection = DBConnection.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{

            System.out.print(moves + " " + time + " " + levelID);
            statement = connection.prepareStatement("INSERT INTO Scores (playerID, levelID, time, moves) VALUES (?,?,?,?)");
            statement.setInt(1, playerID);
            statement.setInt(2, levelID);
            statement.setInt(3, time);
            statement.setInt(4, moves);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public static boolean verifyPassword(int id, String password){
        ArrayList<Player> players = DBFunctions.getPlayers();
        Player player = players.get(id);
            if (password.toLowerCase().equals(player.getPassword().toLowerCase())){
                return true;
            }
        return false;
    }

    public static void insertLevel(String levelName, Character[][] level, int levelNumber, int maxX, int maxY){
        PreparedStatement statement;
        Connection connection = null;
        String levelString = "";

        for(int i = 0; i < maxY; i++){
            for(int j = 0; j < maxX; j++){
                levelString += level[i][j];
            }
            if(i != maxY - 1)
                levelString += "\n";
        }

        try {
            DBConnection.connect();
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            statement = connection.prepareStatement("INSERT INTO Level (gameNumber, levelNumber, level, levelName) VALUES (?,?,?,?)");
            statement.setInt(1, 0);
            statement.setInt(2, levelNumber);
            statement.setString(3, levelString);
            statement.setString(4, levelName);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateLevel(String levelName, Character[][] level, int maxX, int maxY){
        PreparedStatement statement;
        Connection connection = null;
        String levelString = "";

        for(int i = 0; i < maxY; i++){
            for(int j = 0; j < maxX; j++){
                levelString += level[i][j];
            }
            if(i != maxY - 1)
                levelString += "\n";
        }

        try {
            DBConnection.connect();
            connection = DBConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            statement = connection.prepareStatement("UPDATE Level SET level = ? WHERE levelName = ?");
            statement.setString(1, levelString);
            statement.setString(2, levelName);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        verifyNickname(nickname);
        return true;
    }
}
