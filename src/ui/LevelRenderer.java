package ui;

import core.Level;
import org.newdawn.slick.*;

/**
 * Created by MarKay on 8. 3. 2015.
 */
public class LevelRenderer {
    Graphics g;
    int height;
    int width;
    String game;
    int maxY, maxX;
    Character[][] chars;


    public LevelRenderer(Graphics g, int height, int width, int gameNumber) {
        this.height = height;
        this.width = width;
        this.g = g;
        game = "game" + gameNumber;
    }

    public void Render(Level l, Graphics gg, GameContainer container){
        maxY = l.getMaxY();
        maxX = l.getMaxX();
        float divY = height/maxY;
        float divX = width/maxX;
        Image wall = null, box = null, floor = null, boxf = null, floort = null, player = null;
        try {
            wall = new Image("res/" + game + "/wall.png");
            box = new Image("res/" + game + "/box.png");
            boxf = new Image("res/" + game + "/boxf.png");
            floor = new Image("res/" + game + "/floor.png");
            floort = new Image("res/" + game + "/floort.png");
            player = new Image("res/player/player.png");

        } catch (SlickException e) {
            e.printStackTrace();
        }
        int offsetX = 0;
        int offsetY = 0;

        offsetX=(container.getScreenWidth()-(maxX+1)*wall.getWidth())/2;
        offsetY=(container.getScreenHeight()-((maxY+1)*wall.getHeight()))/2;

        chars = l.getLevel();
        for(int i = 0; i < maxY; i++){
            for(int j = 0; j < maxX; j++){
                //floor
                gg.drawImage(floor, offsetX+j*floor.getWidth(), offsetY+(i*floor.getHeight()));

                if(chars[i][j].equals('W')){
                    gg.drawImage(wall, offsetX+j*wall.getWidth(), offsetY+i*wall.getHeight());
                }
                else if(chars[i][j].equals('B')){
                    gg.drawImage(box, offsetX+j * box.getWidth(), offsetY+i * box.getHeight());
                }
                else if(chars[i][j].equals('X')){
                    gg.drawImage(floort, offsetX+j * floort.getWidth(), offsetY+i * floort.getHeight());
                }
                else if(chars[i][j].equals('0')){
                    gg.drawImage(boxf, offsetX+j*boxf.getWidth(), offsetY+i*boxf.getHeight());
                }
                else if (chars[i][j].equals('P')){
                    if(Game.faceRight){
                        gg.drawImage(player, offsetX+j * player.getWidth(), offsetY+i * player.getHeight());
                    }
                    else{
                        gg.drawImage(player.getFlippedCopy(true, false), offsetX+j * player.getWidth(), offsetY+i * player.getHeight());
                    }

                }

            }
        }
    }
}
