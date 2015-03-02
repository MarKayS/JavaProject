package javaproject;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
    //position and size
    private StateBasedGame game;
    private int x = 0;
    private int y = 400;
    private int size = 74;


    public void init() throws SlickException {
        int gameHeight = game.getContainer().getHeight();
        int gameWidth = game.getContainer().getWidth();
    }

    public void render(Graphics g) throws SlickException{

    }

    public void update(int delta) throws SlickException{
        move(delta);
    }

    public void move(int delta){    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y=y;
    }
    public void setX(int x){
        this.x=x;
    }
    public int getSize(){return size;}
}