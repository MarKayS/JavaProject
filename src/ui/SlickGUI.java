package ui;

/**
 * Switching to another state
 * enterState(Game.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
 **/

import core.Level;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;


import java.io.File;
import java.util.ArrayList;

public class SlickGUI extends StateBasedGame {
    public static final int SPLASHSCREEN = 0;
    public static final int MAINMENU = 1;
    public static final int GAME = 2;
    public static final int CREATELEVEL = 3;
    private static int width = 800;
    private static int height = 600;
    static int fps = 0;
    private static int levelNumber = 1;
    private static int gameNumber = 1;

    public SlickGUI(String appName) {
        super(appName);
    }

    public void initStatesList(GameContainer gc) throws SlickException {
        //this.addState(new SplashScreen(SPLASHSCREEN));
        this.addState(new MainMenu(MAINMENU));
        this.addState(new Game(GAME, levelNumber, gameNumber));
        this.addState(new CreateLevel(CREATELEVEL));
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {  return width;
    }

    public static void main(String[] args) {
        System.setProperty("org.lwjgl.librarypath", new File("native/windows").getAbsolutePath());
        try {
            AppGameContainer app = new AppGameContainer(new SlickGUI("jSokoban"));
            app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), false);
            app.setTargetFrameRate(120);
            app.setShowFPS(true);

            //MainMenu menu = new MainMenu(MAINMENU);
            //Game game = new Game(GAME, gameNumber, levelNumber);
            //ArrayList<Level> levels = new ArrayList<>();

            app.setVSync(true);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
