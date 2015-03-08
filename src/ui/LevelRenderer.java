package ui;

import core.Level;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by MarKay on 8. 3. 2015.
 */
public class LevelRenderer {
    Graphics g;
    int height;
    int width;


    public LevelRenderer(Graphics g, int height, int width) {
        this.height = height;
        this.width = width;
        this.g = g;
    }
    public void Render(Level l, Graphics gg){
        int maxY = l.getMaxY();
        int maxX = l.getMaxX();
        float divY = height/maxY;
        float divX = width/maxX;
        Image wall = null, box = null, floor = null, boxf = null, floort = null, player = null;
        try {
            wall = new Image("res/game1/wall1.png");
            box = new Image("res/game1/box1.png");
            boxf = new Image("res/game1/box1f.png");
            floor = new Image("res/game1/floor1.png");
            floort = new Image("res/game1/floor1t.png");
            player = new Image("res/player/player.png");

        } catch (SlickException e) {
            e.printStackTrace();
        }

        Character[][] chars = l.getLevel();
        for(int i = 0; i < maxY; i++){
            for(int j = 0; j < maxX; j++){
                //floor
                gg.drawImage(floor, j*floor.getHeight(), i*floor.getWidth());

                if(chars[i][j].equals('W')){
                    gg.drawImage(wall, j*wall.getHeight(), i*wall.getWidth());
                }
                else if(chars[i][j].equals('B')){
                    gg.drawImage(box, j * box.getHeight(), i * box.getWidth());
                }
                else if(chars[i][j].equals('X')){
                    gg.drawImage(floort, j * floort.getHeight(), i * floort.getWidth());
                }
                else if(chars[i][j].equals('0')){
                    gg.drawImage(boxf, j*boxf.getHeight(), i*boxf.getWidth());
                }
                else if (chars[i][j].equals('P')){
                    if(Game.faceRight == true){
                        gg.drawImage(player, j * player.getHeight(), i * player.getWidth());
                    }
                    else if(Game.faceRight == false){
                        gg.drawImage(player.getFlippedCopy(true, false), j * player.getHeight(), i * player.getWidth());
                    }

                }

            }
        }
    }
}
