package javaproject;


import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import java.math.*;
import java.util.Random;


public class Game extends BasicGameState {
    private int id;
    private Graphics g;
    private StateBasedGame game;
    private int texx=500;
    private int texy=500;
    private double a=0;
    public int gamestate = 0;  // 0 intro, 1 game, 2 finish, -1 gameover(fail)

    Game(int i){
        this.id = i;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        Input i = container.getInput();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        String s = String.valueOf(a);
        g.drawString(s,texx,texy);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

        double x = 500+Math.cos(a)*100;
        double y = 500+Math.sin(a)*100;
        texx = (int) x;
        texy = (int) y;
        a+=0.1;
    }


    @Override
    public int getID() {
        return this.id;
    }
}
