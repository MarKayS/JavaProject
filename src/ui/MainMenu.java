package ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
    Image logo = null, cz = null, nl = null, en = null;


    MainMenu(int i){
        this.id = i;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;

        try {
            logo = new Image("res/menu/logo.png");
            cz = new Image("res/menu/czech.png");
            nl = new Image("res/menu/dutch.png");
            en = new Image("res/menu/english.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(logo, container.getWidth()/2 - logo.getWidth()/2, 50);
        g.drawString("Please select your language: ", container.getWidth()/2, container.getHeight()/2 - 50);
        g.drawImage(cz, container.getWidth()/2 - cz.getWidth()/2 + cz.getWidth() + 50,  container.getHeight()/2);
        g.drawImage(nl, container.getWidth()/2 - nl.getWidth()/2,                       container.getHeight()/2);
        g.drawImage(en, container.getWidth()/2 - en.getWidth()/2 - en.getWidth() - 50,  container.getHeight()/2);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }

    @Override
    public int getID() {
        return this.id;
    }
}

