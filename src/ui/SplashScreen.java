package ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Created by MKS on 8. 11. 2014.
 */
public class SplashScreen extends BasicGameState {
    private int id;
    private StateBasedGame game;
    private long timer = 0;
    Image logo = null;
    FadeInTransition leave = new FadeInTransition();
    FadeOutTransition enter = new FadeOutTransition();

    SplashScreen(int i) {
        this.id = i;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;

        game.enterState(0, leave, leave);
        //Images
        try {
            logo = new Image("res/menu/doge.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(logo, container.getWidth()/2 - logo.getWidth()/2, container.getHeight()/2 - logo.getHeight()/2);

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        timer += delta;
        if(timer > 2000){
            game.enterState(1, enter, leave);
        }

    }

    @Override
    public int getID() {
        return this.id;
    }
}
