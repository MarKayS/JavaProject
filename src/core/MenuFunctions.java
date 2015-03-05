package core;

import persistance.DBFunctions;

import java.util.Scanner;

/**
 * Created by MarKay on 4. 3. 2015.
 */
public class MenuFunctions {

    public static void loginprompt(){
        boolean control = true;
        while(control){
            Language.language();
            System.out.println(Language.getText("loginpromptKey"));
            Scanner scanner = new Scanner(System.in);
            String nickname = scanner.nextLine();

            int playerID = DBFunctions.verifyNickname(nickname);

            if(playerID==-1){
                System.out.print("Player not found! Would you like to create a new account? Y/N\n");
                String answer = scanner.nextLine().toLowerCase();
                if(answer.equals("y")) {
                    registerprompt(nickname);

                }
            }
            else{

                System.out.println("Password: ");
                String password = scanner.nextLine();

                if(DBFunctions.verifyPassword(playerID, password) == true){
                    System.out.print("Welcome!");
                    control=false;
                }
            }
        }
    }

    public static void registerprompt(String nickname){
        System.out.print("Please type your name: \n");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.print("Please type your surname: \n");
        String surname = scanner.nextLine();
        System.out.print("Please type the password for nickname " + nickname + ": \n");
        String password = scanner.nextLine();
        if(DBFunctions.register(name,surname,nickname,password)==true){
            System.out.print("Registration successful \n");
        }
        else{
            System.out.print("Registration failed \n");
        }
    }

}
