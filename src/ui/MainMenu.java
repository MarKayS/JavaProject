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
    Input input;
    int counter = 0;
    String login, password;

    TextField loginTxtField = null, pwTxtField = null;
    //Images & MouseOverAreas
    Image logo = null, cz = null, nl = null, en = null, czp = null, enp = null, nlp = null, button = null, buttonp = null;
    MouseOverArea czMouseArea, enMouseArea, nlMouseArea, loginButton;

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

        input = container.getInput();

        try {
            logo = new Image("res/menu/logo.png");
            button = new Image("res/menu/login.png");
            buttonp = new Image("res/menu/loginp.png");

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
        loginButton = new MouseOverArea(container, button, container.getWidth()/2 - button.getWidth()/2 + 50,          container.getHeight()/2 + 30);
    }

    public void renderLanguages(GameContainer container, StateBasedGame game, Graphics g){
        g.drawString("Please select your language: ", container.getWidth()/2 - 130,     container.getHeight()/2 - 50);

        //mouseover tests
        czMouseArea.render(container, g);
        czMouseArea.setMouseOverImage(czp);

        enMouseArea.render(container, g);
        enMouseArea.setMouseOverImage(enp);

        nlMouseArea.render(container, g);
        nlMouseArea.setMouseOverImage(nlp);
    }

    public void renderLogin(GameContainer container, StateBasedGame game, Graphics g){
        g.drawString(Language.getText("loginpromptKey"), container.getWidth() / 2 - 255, container.getHeight() / 2 - 50);
        loginTxtField.render(container, g);

        g.drawString(Language.getText("passpromptKey"), container.getWidth() / 2 - 255, container.getHeight() / 2 - 15);
        pwTxtField.render(container,g);

        loginButton.render(container, g);
        loginButton.setMouseOverImage(buttonp);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(logo, container.getWidth()/2 - logo.getWidth()/2, 50);

        if(counter == 0){
            renderLanguages(container, game, g);
        }
        else if (counter == 1){
            czMouseArea.setAcceptingInput(false);
            enMouseArea.setAcceptingInput(false);
            nlMouseArea.setAcceptingInput(false);
            renderLogin(container, game, g);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            if(czMouseArea.isMouseOver()){
                Language.language("cs");
                counter = 1;
            }
            else if(nlMouseArea.isMouseOver()){
                Language.language("nl");
                counter = 1;
            }
            else if(enMouseArea.isMouseOver()){
                Language.language("en");
                counter = 1;
            }

            if(loginButton.isMouseOver()){
                login = loginTxtField.getText();
                password = pwTxtField.getText();
                System.out.print("login: " + login + "\t" + "pw: " + password);
            }
        }

    }

    @Override
    public int getID() {
        return this.id;
    }
}

