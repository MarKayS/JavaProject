package ui;

import core.Level;
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
    private static int maxX = 0;
    private static int maxY = 0;
    public static boolean initiate = false;
    static Character[][] level;
    static Level myLevel;
    LevelRenderer levelRenderer;

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
        levelRenderer = new LevelRenderer(g, maxY, maxX, 2);
        levelRenderer.Render(myLevel, g, container);
    }

    public static void initiateLevel(){
        level = new Character[maxX][maxY];
        for(int i = 0; i < maxX; i++){
            for(int j = 0; j < maxY; j++){
                if(i == 0 || i == maxY-1 || j == 0 || j == maxX-1)
                    level[i][j] = 'W';
                else
                    level[i][j] = ' ';
                System.out.print(level[i][j]);
            }
            System.out.print("\n");
        }
        myLevel = new Level(level, maxX, maxY);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
    }

    @Override
    public int getID() {
        return this.id;

    }
}
