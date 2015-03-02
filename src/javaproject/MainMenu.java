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
            System.out.println("Password: ");
            String password = scanner.nextLine();
            if(DBFunctions.verify(nickname, password)==false){
                System.out.print("Nice try guy, Try Again \n");
            }
            else{
                System.out.print("Lets get this partz started");
                control=false;
            }
        }
    }
}
