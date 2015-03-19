package ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by tomasbird on 19.3.2015.
 */

public class CreateLevel extends BasicGameState {
    private int id;
    private static int maxX;
    private static int maxY;

    public CreateLevel(int createlevel) {
        this.id = createlevel;
    }

    public static void setX(int x){
        maxX = x;
    }

    public static void setY(int y){
        maxY = y;
    }


    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }

    @Override
    public int getID() {
        return this.id;

    }
}
