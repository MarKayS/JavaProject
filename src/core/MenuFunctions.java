package core;

import org.lwjgl.Sys;
import persistance.DBFunctions;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by MarKay on 4. 3. 2015.
 */
public class MenuFunctions {

    public static void loginprompt(){
        boolean control = true;
        while(control){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Please chose your default language:\n\ten - english\n\tcs - czech\n\tnl - dutch\n");
            String lang = scanner.nextLine();
            Language.language(lang);
            System.out.println(Language.getText("loginpromptKey"));
            String nickname = scanner.nextLine();

            int playerID = DBFunctions.verifyNickname(nickname);

            if(playerID==-1){
                System.out.print(Language.getText("loginNFKey"));
                String answer = scanner.nextLine().toLowerCase();
                if(answer.equals("y")) {
                    registerprompt(nickname);

                }
            }
            else{

                System.out.println(Language.getText("passpromptKey"));
                String password = scanner.nextLine();

                if(DBFunctions.verifyPassword(playerID, password) == true){
                    System.out.print(Language.getText("loginSucKey") + "\n");
                    control=false;
                }
            }
        }
    }

    public static void registerprompt(String nickname){
        System.out.print(Language.getText("loginpromptKey"));
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.print(Language.getText("surnamepromptKey"));
        String surname = scanner.nextLine();
        System.out.print(Language.getText("passpromptKey"));
        String password = scanner.nextLine();
        if(DBFunctions.register(name,surname,nickname,password)==true){
            System.out.print(Language.getText("regSucKey"));
        }
        else{
            System.out.print(Language.getText("regUnsucKey"));
        }
    }

    public static int gameselectpromt(){
        ArrayList<String> games = DBFunctions.getGames();
        System.out.print(Language.getText("gameSelectKey") + "\n");

        for (String game : games){
            System.out.print(game + "\n");
        }
        Scanner scanner = new Scanner(System.in);
        int gameID = scanner.nextInt();
        return gameID;
    }

    public static int levelselectprompt(int size){
        System.out.print(Language.getText("levelSelectKey") + size + ")" );
        Scanner scanner = new Scanner(System.in);
        int levelID = scanner.nextInt();
        return levelID;
    }
}
