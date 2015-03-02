package javaproject;


/**
 * Switching to another state
 * enterState(Game.ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
 **/

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import java.io.File;

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
            app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), true);
            app.setTargetFrameRate(120);
            app.setShowFPS(true);
            //app.setVSync(true);
            app.start();
        } catch(SlickException e) {
            e.printStackTrace();
        }
    }

}
