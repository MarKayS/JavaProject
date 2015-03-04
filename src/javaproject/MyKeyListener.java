package javaproject;

import core.Player;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;


/**
 * Created by MKS on 9. 11. 2014.
 */
public class MyKeyListener implements KeyListener {
    Player player;
    MyKeyListener(Player player){
        this.player=player;
        System.out.print("Key Listener initialized\n");
    }
    @Override
    public void keyPressed(int key, char c) {

    }

    @Override
    public void keyReleased(int key, char c) {
    }

    @Override
    public void setInput(Input input) {
    }

    @Override
    public boolean isAcceptingInput() {
        return true;
    }

    @Override
    public void inputEnded() {
    }

    @Override
    public void inputStarted() {
    }
}
