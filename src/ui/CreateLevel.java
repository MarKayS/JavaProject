package ui;

import core.Level;
import org.newdawn.slick.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import persistance.DBFunctions;


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
    private long timer = 0;
    private static String levelName;

    public static boolean initiate = false;
    static Character[][] level;
    static Level myLevel;

    static boolean mouseOvers = false, error = false, saveNew = false, saveSuc = false;
    boolean wall, box, floor, target, player;
    LevelRenderer levelRenderer;
    static ArrayList<MouseOverArea> blocks = new ArrayList<>();

    static MouseOverArea blockOver, createLvlMenuMOA, boxOver, wallOver, playerOver, targetOver, floorOver, saveButtonMOA;
    Image blockSelected;
    static Image empty, createLvlMenu, createLvlMenuBox,createLvlMenuWall,createLvlMenuFloor,createLvlMenuPlayer, createLvlMenuTarget, saveButton, saveButtonp;
    TextField saveLevel = null;

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
        saveButton = new Image("res/menu/CreateLevel/saveButton.png");
        saveButtonp = new Image("res/menu/CreateLevel/saveButtonp.png");
        createLvlMenu = new Image("res/menu/CreateLevel/createLvlMenu.png");
        createLvlMenuBox  = new Image("res/menu/CreateLevel/createLvlMenuBox.png");
        createLvlMenuWall  = new Image("res/menu/CreateLevel/createLvlMenuWall.png");
        createLvlMenuFloor  = new Image("res/menu/CreateLevel/createLvlMenuFloor.png");
        createLvlMenuPlayer  = new Image("res/menu/CreateLevel/createLvlMenuPlayer.png");
        createLvlMenuTarget  = new Image("res/menu/CreateLevel/createLvlMenuTarget.png");

        createLvlMenuMOA = new MouseOverArea(container, createLvlMenu, container.getWidth()/2 - createLvlMenu.getWidth()/2, container.getHeight()-createLvlMenu.getHeight());
        saveButtonMOA = new MouseOverArea(container, saveButton, 0, 0);
        boxOver = new MouseOverArea(container, empty, container.getWidth()/2 - createLvlMenu.getWidth()/2 +     (+ 10),                        container.getHeight() - empty.getHeight() - 7);
        floorOver = new MouseOverArea(container, empty, container.getWidth()/2 - createLvlMenu.getWidth()/2 +   ((empty.getWidth() + 13) * 1), container.getHeight() - empty.getHeight() - 7);
        targetOver = new MouseOverArea(container, empty, container.getWidth()/2 - createLvlMenu.getWidth()/2 +  ((empty.getWidth() + 13) * 2), container.getHeight() - empty.getHeight() - 7);
        wallOver = new MouseOverArea(container, empty, container.getWidth()/2 - createLvlMenu.getWidth()/2 +    ((empty.getWidth() + 13) * 3), container.getHeight() - empty.getHeight() - 7);
        playerOver = new MouseOverArea(container, empty, container.getWidth()/2 - createLvlMenu.getWidth()/2 +  ((empty.getWidth() + 13) * 4), container.getHeight() - empty.getHeight() - 7);


        saveLevel = new TextField(container, container.getDefaultFont(), 1, saveButton.getHeight() + 1, 150, 20);
        saveLevel.setBackgroundColor(Color.white);
        saveLevel.setTextColor(Color.black);



    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        levelRenderer = new LevelRenderer(2);
        levelRenderer.render(myLevel, g, container);

        createLvlMenuMOA.render(container, g);
        saveButtonMOA.render(container, g);
        saveButtonMOA.setMouseOverImage(saveButtonp);
        boxOver.render(container, g);
        floorOver.render(container, g);
        targetOver.render(container, g);
        wallOver.render(container, g);
        playerOver.render(container, g);

        if(saveNew){
            saveLevel.render(container, g);
        }




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

                    if(player && !playerExists()){
                        level[position.x][position.y] = 'P';
                    }

                }
            }
        }

        if(error){
            g.drawString("ERROR! That level name already exists, please choose a different name!", saveLevel.getWidth() + 5, saveButton.getHeight());
        }

        if(saveSuc){
            g.drawString("Save successful!", saveLevel.getWidth() + 5, saveButton.getHeight());
        }

    }

    private static void initiateMouseOvers(GameContainer container) {
        offsetX = (container.getScreenWidth() - (maxX + 1) * empty.getWidth()) / 2;
        offsetY = (container.getScreenHeight() - ((maxY + 1) * empty.getHeight())) / 2;
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                blockOver = new MouseOverArea(container, empty, offsetX + 49 * j, offsetY + 49 * i);
                blocks.add(blockOver);
            }
        }
    }

    public static void initiateLevel(GameContainer container){
        saveNew = true;
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

    public static void editLevel(int levelNumber, GameContainer container){
        saveNew = false;
        ArrayList<Level> levels = DBFunctions.getLevels(0);
        myLevel = levels.get(levelNumber);

        maxX = myLevel.getMaxX();
        maxY = myLevel.getMaxY();
        level = myLevel.getLevel();
        mouseOvers = true;
        initiateMouseOvers(container);

    }

    private boolean saveNewLevel(){
        ArrayList<Level> levels = DBFunctions.getLevels(0);
        int levelNumber = 0;

        for(Level level : levels){
            levelNumber = level.getLevelNumber();
            if(levelName.equals(level.getLevelName())){
                return false;
            }
        }
        levelNumber += 1;

        DBFunctions.insertLevel(levelName, level, levelNumber, maxX, maxY);
        saveSuc = true;

        return true;
    }

    private void saveLevel(){
        String levelName = myLevel.getLevelName().toString();
        DBFunctions.updateLevel(levelName, level, maxX, maxY);
        saveSuc = true;
    }

    private void printLevels(){
        ArrayList<Level> levels = DBFunctions.getLevels(0);

        for(Level level : levels){
            System.out.print("Level names: " + level.getLevelName() + "\n");
        }
    }

    private boolean playerExists(){
        for (int i = 0; i < maxY; i++) {
            for (int j = 0; j < maxX; j++) {
                if(level[i][j] == 'P')
                    return true;
            }
        }
        return false;
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(boxOver.isMouseOver()){
            createLvlMenuMOA.setMouseOverImage(createLvlMenuBox);
            if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
                box = true;
                floor = false; wall = false; target = false; player = false;
            }

        }
        else if(floorOver.isMouseOver()){
            createLvlMenuMOA.setMouseOverImage(createLvlMenuFloor);
            if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
                floor = true;
                box = false; wall = false; target = false; player = false;
            }
        }
        else if(targetOver.isMouseOver()){
            createLvlMenuMOA.setMouseOverImage(createLvlMenuTarget);
            if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
                target = true;
                floor = false; wall = false; box = false; player = false;
            }
        }
        else if(wallOver.isMouseOver()){
            createLvlMenuMOA.setMouseOverImage(createLvlMenuWall);
            if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
                wall = true;
                floor = false; box = false; target = false; player = false;
            }
        }
        else if(playerOver.isMouseOver()){
            createLvlMenuMOA.setMouseOverImage(createLvlMenuPlayer);
            if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
                player = true;
                floor = false; wall = false; target = false; box = false;
            }
        }
        else if(saveButtonMOA.isMouseOver()){
            if(container.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON)){
                levelName = saveLevel.getText();
                if(saveNew){
                    if (saveNewLevel()) {
                        System.out.print("Save successfull!\n");
                    } else {
                        error = true;
                    }
                }else
                    saveLevel();
            }
        }
        else if(container.getInput().isKeyDown(Input.KEY_HOME)){
            printLevels();
        }

        if(error){
            timer += delta;
            if(timer > 2000){
                error = false;
                timer = 0;
            }
        }

        if(saveSuc){
            timer += delta;
            if(timer > 2000){
                saveSuc = false;
                timer = 0;
            }
        }

        if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            for(int i = 0; i < maxY; i ++){
                for(int j = 0; j < maxX; j++){
                    level[i][j] = null;
                }
            }
            maxX = 0;
            maxY = 0;
            game.enterState(1);
        }
    }

    @Override
    public int getID() {
        return this.id;

    }
}
