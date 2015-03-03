package persistance;

import java.sql.*;

import javaproject.Level;
import javaproject.Player;
import org.lwjgl.Sys;

import java.util.ArrayList;

/**
 * Created by tomasbird on 02.3.2015.
 */
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

    public static ArrayList<Level> getLevels(String selectGameID){
        ArrayList<Level> levels = new ArrayList<>();
        String select = "SELECT * from Level WHERE gameID = ";

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
                int gameID = resultSet.getInt("gameID");
                String levelParse = resultSet.getString("level");
                System.out.print(levelParse);
                levelParser(levelParse);

                //Level level1 = new Level(levelID, gameID, level);
                //levels.add(level1);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        for(Level level: levels){
            System.out.print(level.getLevel() + "\n") ;
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

    private static Character[][] levelParser(String levelParse){
        int x = 0;
        int y = -2;
        for(int i = 0; i < levelParse.length(); i++){
            if(levelParse.charAt(i) == '\n'){
                x++;
            }
            if(levelParse.charAt(i) != '\n' && x == 0){
                y++;
                //System.out.print("\n" + levelParse.charAt(i));
            }

        }
        System.out.print("\nx: " + x + "\ty: " + y + "\n");

        Character[][] level = new Character[x][y];


        int c = 0;
        for(int i = 0; i < x; i++){
            for(int j = 0; i < y; j++){
                level[i][j] = levelParse.charAt(c);
                c++;
            }
            c++;
        }
        return level;


    }

}
