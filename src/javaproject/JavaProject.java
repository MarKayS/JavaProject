package javaproject;

/**
 * Switching to another state
 * enterState(Game.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
 **/

import core.Level;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import persistance.DBFunctions;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class JavaProject extends StateBasedGame {
    public static final int SPLASHSCREEN = 0;
    public static final int MAINMENU     = 1;
    public static final int GAME         = 2;
    private static int width = 800;
    private static int height = 600;
    static int fps = 0;

    public JavaProject(String appName) {
        super(appName);
    }

    public void initStatesList(GameContainer gc) throws SlickException {
       // this.addState(new SplashScreen(SPLASHSCREEN));
        //this.addState(new MainMenu(MAINMENU));
        this.addState(new Game(GAME));

    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath",new File("native/windows").getAbsolutePath());
        try {
            AppGameContainer app = new AppGameContainer(new JavaProject("JavaRunner"));
            app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), false);
            app.setTargetFrameRate(120);
            app.setShowFPS(true);

            MainMenu menu = new MainMenu(1);
            ArrayList<Level> levels = new ArrayList<>();

            //app.setVSync(true);
            //app.start();
            //persistance.DBConnection connection = new DBConnection();
            persistance.DBFunctions functions = new DBFunctions();

          //  levels.add(functions.getLevel("1", "2"));
            //System.out.print("MaxX: " + levels.get(0).getMaxX() + "\tMaxY: " + levels.get(0).getMaxY() + "\n");
            levels.get(0).render();
            boolean gameover = false;
            Scanner scanner = new Scanner(System.in);
            char direction;
            while(!gameover){
                direction = scanner.next().charAt(0);
                levels.get(0).move(levels.get(0).locatePlayer(),direction);
                levels.get(0).render();
            }



            //menu.loginprompt();
            //DBConnection.closeConnection();

        } catch(SlickException e) {
            e.printStackTrace();
        }
    }

}
