package ui;

import core.Level;
import org.newdawn.slick.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by tomasbird on 19.3.2015.
 */

public class CreateLevel extends BasicGameState {
    private int id;
    private static int maxX = 0;
    private static int maxY = 0;
    private static int offsetX;
    private static int offsetY;
    private Point position;

    public static boolean initiate = false;
    static Character[][] level;
    static Level myLevel;

    static boolean mouseOvers = false;
    boolean wall, box, floor, target;
    LevelRenderer levelRenderer;
    static ArrayList<MouseOverArea> blocks = new ArrayList<>();

    static MouseOverArea blockOver;
    Image blockSelected;
    static Image empty;

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
        blockSelected = new Image("res/mouseOverBlock.png");
        empty = new Image("res/empty.png");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        levelRenderer = new LevelRenderer(2);
        levelRenderer.render(myLevel, g, container);

        for (int i = 0; i < blocks.size(); i++) {
            blocks.get(i).render(container, g);
            blocks.get(i).setMouseOverImage(blockSelected);
            if(blocks.get(i).isMouseOver() && container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
                position = new Point(i/maxX, i%maxX);
                if(!(position.x == 0 || position.x == maxY-1 || position.y == 0 || position.y == maxX-1)){
                    if(wall)
                        level[position.x][position.y] = 'W';

                    if(box)
                        level[position.x][position.y] = 'B';

                    if(floor)
                        level[position.x][position.y] = ' ';

                    if(target)
                        level[position.x][position.y] = 'X';
                }
            }


        }

    }

    private static void initiateMouseOvers(GameContainer container){
        offsetX = (container.getScreenWidth()-(maxX+1)*empty.getWidth())/2;
        offsetY = (container.getScreenHeight()-((maxY+1)*empty.getHeight()))/2;
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                blockOver = new MouseOverArea(container, empty, offsetX + 49 * j, offsetY + 49 * i);
                blocks.add(blockOver);
            }
        }
    }

    public static void initiateLevel(GameContainer container){
        level = new Character[maxY][maxX];
        for(int i = 0; i < maxY; i++){
            for(int j = 0; j < maxX; j++){
                if(i == 0 || i == maxY-1 || j == 0 || j == maxX-1)
                    level[i][j] = 'W';
                else
                    level[i][j] = ' ';
            }
        }
        myLevel = new Level(level, maxX, maxY);
        mouseOvers = true;
        initiateMouseOvers(container);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(container.getInput().isKeyDown(Input.KEY_V)){
            box = true;
            floor = false; wall = false; target = false;
        }
        else if(container.getInput().isKeyDown(Input.KEY_C)){
            floor = true;
            box = false; wall = false; target = false;
        }
        else if(container.getInput().isKeyDown(Input.KEY_Y)){
            wall = true;
            floor = false; box = false; target = false;
        }
        else if(container.getInput().isKeyDown(Input.KEY_X)){
            target = true;
            floor = false; wall = false; box = false;
        }
    }

    @Override
    public int getID() {
        return this.id;

    }
}
