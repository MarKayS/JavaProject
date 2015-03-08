package ui;

import core.Level;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

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
        Character[][] chars = l.getLevel();
        for(int i = 0; i < maxY; i++){
            for(int j = 0; j < maxX; j++){
                if(chars[i][j].equals('W')){
                    gg.setColor(Color.red);
                }
                else if(chars[i][j].equals('B')){
                    gg.setColor(Color.yellow);
                }
                else if(chars[i][j].equals('X')){
                    gg.setColor(Color.blue);
                }
                else if(chars[i][j].equals('0')){
                    gg.setColor(Color.cyan);
                }
                else if(chars[i][j].equals(' ')){
                    gg.setColor(Color.white);
                }
                else{
                    gg.setColor(Color.pink);
                }
                gg.fillRect(divX*j,divX*i,divX,divX);

            }
        }
    }
}
