package ui;

import core.Level;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.awt.*;

/**
 * Created by MarKay on 8. 3. 2015.
 */
public class LevelRenderer {
    Graphics g;
    String path;

    Character[][] chars;
    Image wall = null, box = null, floor = null, boxf = null, floort = null, player = null;


    public LevelRenderer(int skinNumber) {

        path = "game" + skinNumber;
        try {
            wall = new Image("res/" + path + "/wall.png");
            box = new Image("res/" + path + "/box.png");
            boxf = new Image("res/" + path + "/boxf.png");
            floor = new Image("res/" + path + "/floor.png");
            floort = new Image("res/" + path + "/floort.png");
            player = new Image("res/player/player.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void render(Level l, Graphics g, GameContainer container){
        int maxY, maxX;
        maxY = l.getMaxY();
        maxX = l.getMaxX();

        int offsetX=(container.getScreenWidth()-(maxX+1)*wall.getWidth())/2;
        int offsetY=(container.getScreenHeight()-((maxY+1)*wall.getHeight()))/2;

        chars = l.getLevel();
        for(int i = 0; i < maxY; i++){
            for(int j = 0; j < maxX; j++){
                //floor
                g.drawImage(floor, offsetX + j * floor.getWidth(), offsetY + (i * floor.getHeight()));

                if(chars[i][j].equals('W')){
                    g.drawImage(wall, offsetX + j * wall.getWidth(), offsetY + i * wall.getHeight());
                }
                else if(chars[i][j].equals('B')){
                    g.drawImage(box, offsetX + j * box.getWidth(), offsetY + i * box.getHeight());
                }
                else if(chars[i][j].equals('X')){
                    g.drawImage(floort, offsetX + j * floort.getWidth(), offsetY + i * floort.getHeight());
                }
                else if(chars[i][j].equals('0')){
                    g.drawImage(boxf, offsetX + j * boxf.getWidth(), offsetY + i * boxf.getHeight());
                }
                else if (chars[i][j].equals('P')){
                    if(Game.faceRight){
                        g.drawImage(player, offsetX + j * player.getWidth(), offsetY + i * player.getHeight());
                    }
                    else{
                        g.drawImage(player.getFlippedCopy(true, false), offsetX + j * player.getWidth(), offsetY + i * player.getHeight());
                    }

                }

            }
        }
    }

    public void renderPreview(Level l, Graphics g, Point target, float scale){
        int maxY, maxX;
        maxY = l.getMaxY();
        maxX = l.getMaxX();

        int offsetX=target.x;//(container.getScreenWidth()-(maxX+1)*wall.getWidth())/2;
        int offsetY=target.y;//(container.getScreenHeight()-((maxY+1)*wall.getHeight()))/2;
        //float scale = 0.1f;

        chars = l.getLevel();
        for(int i = 0; i < maxY; i++){
            for(int j = 0; j < maxX; j++){
                //floor
                g.drawImage(floor.getScaledCopy(scale), offsetX + j * floor.getScaledCopy(scale).getWidth(), offsetY + (i * floor.getScaledCopy(scale).getHeight()));

                if(chars[i][j].equals('W')){
                    g.drawImage(wall.getScaledCopy(scale), offsetX + j * wall.getScaledCopy(scale).getWidth(), offsetY + i * wall.getScaledCopy(scale).getHeight());
                }
                else if(chars[i][j].equals('B')){
                    g.drawImage(box.getScaledCopy(scale), offsetX + j * box.getScaledCopy(scale).getWidth(), offsetY + i * box.getScaledCopy(scale).getHeight());
                }
                else if(chars[i][j].equals('X')){
                    g.drawImage(floort.getScaledCopy(scale), offsetX + j * floort.getScaledCopy(scale).getWidth(), offsetY + i * floort.getScaledCopy(scale).getHeight());
                }
                else if(chars[i][j].equals('0')){
                    g.drawImage(boxf.getScaledCopy(scale), offsetX + j * boxf.getScaledCopy(scale).getWidth(), offsetY + i * boxf.getScaledCopy(scale).getHeight());
                }
                else if (chars[i][j].equals('P')){
                    if(Game.faceRight){
                        g.drawImage(player.getScaledCopy(scale), offsetX + j * player.getScaledCopy(scale).getWidth(), offsetY + i * player.getScaledCopy(scale).getHeight());
                    }
                    else{
                        g.drawImage(player.getScaledCopy(scale).getFlippedCopy(true, false), offsetX + j * player.getScaledCopy(scale).getWidth(), offsetY + i * player.getScaledCopy(scale).getHeight());
                    }

                }

            }
        }

    }
}
