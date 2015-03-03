package javaproject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import persistance.DBFunctions;

import java.util.Scanner;

/**
 * Created by MKS on 8. 11. 2014.
 */
public class MainMenu extends BasicGameState{
    private int id;
    private StateBasedGame game;

    MainMenu(int i){
        this.id = i;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException {
        this.game = game;
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException {
        g.drawString("Hello, This is MainMenu", 50, 100);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {

    }

    @Override
    public int getID() {
        return this.id;
    }

    public void loginprompt(){
        boolean control = true;
        while(control){
            System.out.println("Login: ");
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

    public void registerprompt(String nickname){
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

