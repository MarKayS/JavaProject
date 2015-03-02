package javaproject;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by MKS on 8. 11. 2014.
 */
public class SplashScreen extends BasicGameState {
    private int id;
    private StateBasedGame game;

    SplashScreen(int i){
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
        g.drawString("Hello, This is SplashScreen", 50, 100);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException {

    }

    @Override
    public int getID() {

        return this.id;
    }
}
