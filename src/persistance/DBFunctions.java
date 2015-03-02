package persistance;

import javaproject.Player;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

            for(Player player: players){
                System.out.print("Name: " + player.getName() + "\t|Surname: " + player.getSurname() + "\t|Nickname: " + player.getNickname() + "\n");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } {

        }

        return players;
    }

    public static boolean verify(String nickname, String password){
        ArrayList<Player> players = DBFunctions.getPlayers();
        for (Player player : players){
            if (nickname.toLowerCase() == player.getNickname().toLowerCase() && password.toLowerCase() == player.getPassword().toLowerCase()){
                return true;
            }
        }
        return false;
    }


}
