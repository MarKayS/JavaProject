package ui;

import core.Language;
import org.lwjgl.input.Cursor;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import persistance.DBFunctions;

import java.util.Scanner;

/**
 * Created by MKS on 8. 11. 2014.
 */
public class MainMenu extends BasicGameState{
    private int id;
    private StateBasedGame game;

    TextField loginTxtField = null, pwTxtField = null;
    //Images & MouseOverAreas
    Image logo = null, cz = null, nl = null, en = null, czp = null, enp = null, nlp = null;
    MouseOverArea czMouseArea, enMouseArea, nlMouseArea;


    MainMenu(int i){
        this.id = i;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;
        this.loginTxtField = new TextField(container,container.getDefaultFont(),container.getWidth() / 2 - 20, container.getHeight() / 2 - 50,150,20);
        loginTxtField.setBackgroundColor(Color.white);
        loginTxtField.setBorderColor(Color.gray);
        loginTxtField.setTextColor(Color.black);

        this.pwTxtField = new TextField(container,container.getDefaultFont(),container.getWidth() / 2 - 20, container.getHeight() / 2 - 15,150,20);
        pwTxtField.setBackgroundColor(Color.white);
        pwTxtField.setBorderColor(Color.gray);
        pwTxtField.setTextColor(Color.black);

        core.Language.language("cs");

        try {
            logo = new Image("res/menu/logo.png");

            cz = new Image("res/menu/czech.png");
            czp = new Image("res/menu/czechp.png");

            nl = new Image("res/menu/dutch.png");
            nlp = new Image("res/menu/dutchp.png");

            en = new Image("res/menu/english.png");
            enp = new Image("res/menu/englishp.png");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        //mouseOverAreas
        czMouseArea = new MouseOverArea(container, cz, container.getWidth()/2 - cz.getWidth()/2 + cz.getWidth() + 50,  container.getHeight()/2);
        enMouseArea = new MouseOverArea(container, en, container.getWidth()/2 - en.getWidth()/2 - en.getWidth() - 50,  container.getHeight()/2);
        nlMouseArea = new MouseOverArea(container, nl, container.getWidth()/2 - nl.getWidth()/2,                       container.getHeight()/2);
    }

    public void renderMenu(GameContainer container, StateBasedGame game, Graphics g){
        g.drawImage(logo, container.getWidth()/2 - logo.getWidth()/2, 50);
        g.drawString("Please select your language: ", container.getWidth()/2 - 130,     container.getHeight()/2 - 50);

        //mouseover tests
        czMouseArea.render(container, g);
        czMouseArea.setMouseOverImage(czp);

        //g.drawString("Please select your language: ", container.getWidth() / 2 - 130, container.getHeight() / 2 - 50);
        /*g.drawImage(cz, container.getWidth()/2 - cz.getWidth()/2 + cz.getWidth() + 50,  container.getHeight()/2);
        g.drawImage(nl, container.getWidth()/2 - nl.getWidth()/2,                       container.getHeight()/2);
        g.drawImage(en, container.getWidth()/2 - en.getWidth()/2 - en.getWidth() - 50,  container.getHeight()/2);*/


        g.drawString(Language.getText("loginpromptKey"), container.getWidth() / 2 - 130, container.getHeight() / 2 - 50);
        loginTxtField.render(container, g);
        g.drawString(Language.getText("passpromptKey"), container.getWidth() / 2 - 130, container.getHeight() / 2 - 15);
        pwTxtField.render(container,g);
        enMouseArea.render(container, g);
        enMouseArea.setMouseOverImage(enp);

        nlMouseArea.render(container, g);
        nlMouseArea.setMouseOverImage(nlp);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        renderMenu(container, game, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {

    }

    @Override
    public int getID() {
        return this.id;
    }
}

