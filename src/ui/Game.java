package ui;

import core.Level;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import persistance.DBFunctions;

import java.util.ArrayList;


public class Game extends BasicGameState {
    private int id;
    private Graphics g;
    private Input i;
    private StateBasedGame game;
    private static ArrayList<Level> levels;
    private static LevelRenderer lrend;
    static boolean faceRight = true;
    public int gamestate = -1;  // -1 preparation, 0 game, 1 win, 2 proceed to another level
    private int moves = 0;
    private int time = 0;
    static int levelNumber, gameNumber;

    Game(int i) {
        this.id = i;
    }

    public static void setGame(int ln, int gn){
        levelNumber = ln;
        gameNumber = gn;
        initGame();
    }

    public static void initGame(){
        lrend = new LevelRenderer(gameNumber);
        levels = DBFunctions.getLevels(gameNumber);
    }

    private Level getLevel(int gameNumber, int levelNumber) {
        return null;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        i = container.getInput();
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if (gamestate != 2) lrend.render(levels.get(levelNumber), g, container);
        if (gamestate == 1) {
            g.setColor(new Color(1f, 1f, 1f, 0.8f));
            g.fillRoundRect(container.getScreenWidth() / 2 - container.getScreenWidth() / 4 / 2, container.getScreenHeight() / 2 - container.getScreenHeight() / 4 / 2, container.getScreenWidth() / 4, container.getScreenHeight() / 4, 7);
            g.setColor(Color.red);
            int levelino = levelNumber + 1;
            String win1 = gameNumber + " - " + levelino + " completed !";
            String win2 = "Level completed in " + moves + " moves and took " + time / 1000 + " seconds";
            String cont = "Press 'SPACEBAR' to continue";
            g.drawString(win1, (container.getScreenWidth() / 2 - (win1.length()*9)/2), container.getScreenHeight() / 2 - container.getScreenHeight() / 4 / 2 + 50);
            g.drawString(win2, (container.getScreenWidth() / 2 - (win2.length()*9)/2), container.getScreenHeight() / 2 - container.getScreenHeight() / 4 / 2 + 100);
            g.drawString(cont, (container.getScreenWidth() / 2 - (cont.length()*9)/2), container.getScreenHeight() / 2 - container.getScreenHeight() / 4 / 2 + 200);
            g.setColor(Color.white);
        }
        //GUI
        int offsetx = container.getScreenWidth()/2;
        int offsety = container.getScreenHeight() - 40;
        String movestr = "Moves: "+Integer.toString(moves);
        String restartstr = "Press 'R' to restart this game";
        String timestr = "Time: " + Integer.toString(time/1000);
        String lowestmovestr = "Lowest n of moves: ";
        String number = Integer.toString(gameNumber) + " - " + Integer.toString(levelNumber+1);
        String besttime = "Best time: ";
        g.drawString(movestr, offsetx-movestr.length()*8/2-300, offsety );
        g.drawString(restartstr, offsetx - restartstr.length()*8/2, offsety);
        g.drawString(timestr, (offsetx+300) - timestr.length()*8/2, offsety);
        g.drawString(lowestmovestr, offsetx-lowestmovestr.length()*8/2-300, 10);
        g.drawString(number, offsetx - number.length()*8/2, 10);
        g.drawString(besttime, (offsetx+300) - besttime.length()*8/2, 10);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (levels.get(levelNumber).checkWin()) {
            if (gamestate != 1) {
                DBFunctions.reportScore(moves,time/1000,levels.get(levelNumber).getLevelID());
            }
            gamestate = 1;
        }
        if (gamestate < 1) {
            if (gamestate == 0) {
                time += delta;
            }
            if (i.isKeyPressed(Input.KEY_UP) || i.isKeyPressed(Input.KEY_W)) {
                levels.get(levelNumber).move(levels.get(levelNumber).locatePlayer(), 'w');
                moves++;
                if (gamestate == -1) gamestate++;
            } else if (i.isKeyPressed(Input.KEY_DOWN) || i.isKeyPressed(Input.KEY_S)) {
                levels.get(levelNumber).move(levels.get(levelNumber).locatePlayer(), 's');
                moves++;
                if (gamestate == -1) gamestate++;
            } else if (i.isKeyPressed(Input.KEY_LEFT) || i.isKeyPressed(Input.KEY_A)) {
                faceRight = false;
                levels.get(levelNumber).move(levels.get(levelNumber).locatePlayer(), 'a');
                moves++;
                if (gamestate == -1) gamestate++;
            } else if (i.isKeyPressed(Input.KEY_RIGHT) || i.isKeyPressed(Input.KEY_D)) {
                faceRight = true;
                levels.get(levelNumber).move(levels.get(levelNumber).locatePlayer(), 'd');
                moves++;
                if (gamestate == -1) gamestate++;
            } else if (i.isKeyPressed(Input.KEY_R)){
                initGame();
            }

        } else {
            if (i.isKeyDown(Input.KEY_SPACE)) {
                gamestate = 2;
                if (levelNumber < levels.size() - 1) {
                    this.levelNumber++;
                    game.enterState(2, new EmptyTransition(), new FadeInTransition());
                    gamestate = -1;
                } else {
                    gamestate=-1;
                    game.enterState(1, new FadeInTransition(), new FadeInTransition());
                }

            }
        }
    }


    @Override
    public int getID() {
        return this.id;
    }
}
