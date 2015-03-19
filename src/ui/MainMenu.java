package ui;

import core.Language;
import org.newdawn.slick.*;
import org.newdawn.slick.gui.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import persistance.DBFunctions;

/**
 * Created by MKS on 8. 11. 2014.
 */
public class MainMenu extends BasicGameState{
    private int id;
    private StateBasedGame game;
    boolean control = true;
    Input input;
    int counter = 0;
    String login, password;
    public int counterX = 4, counterY = 4;

    TextField loginTxtField = null, pwTxtField = null;

    //Images & MouseOverAreas
    Image logo = null, cz = null, nl = null, en = null, czp = null, enp = null, nlp = null, loginImg = null, loginImgp = null, regImg = null, regImgp = null,
                play = null, playp = null, createlvl = null, createlvlp = null, editlvl = null, editlvlp = null, highscores = null, highscoresp = null, quitgame = null, quitgamep = null,
                arrowUp = null, arrowUpp = null, arrowDown = null, arrowDownp = null, ok = null, okp = null;
    MouseOverArea czMouseArea, enMouseArea, nlMouseArea, loginButton, regButton, playButton, createLvlButton, editLvlButton, highscoresButton, quitButton,
                  arrowUpButtonX, arrowDownButtonX, arrowUpButtonY, arrowDownButtonY, okButton;

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

        input = container.getInput();

        try {
            /* LOGINS */
            logo = new Image("res/menu/logo.png");
            loginImg = new Image("res/menu/login.png");
            loginImgp = new Image("res/menu/loginp.png");
            regImg = new Image("res/menu/reg.png");
            regImgp = new Image("res/menu/regp.png");

            /* MENU */
            play = new Image("res/menu/play.png");
            playp = new Image("res/menu/playp.png");
            createlvl = new Image("res/menu/createlvl.png");
            createlvlp = new Image("res/menu/createlvlp.png");
            editlvl = new Image("res/menu/editlvl.png");
            editlvlp = new Image("res/menu/editlvlp.png");
            highscores = new Image("res/menu/highscores.png");
            highscoresp = new Image("res/menu/highscoresp.png");
            quitgame = new Image("res/menu/quitgame.png");
            quitgamep = new Image("res/menu/quitgamep.png");
            ok = new Image("res/menu/ok.png");
            okp = new Image("res/menu/okp.png");
            arrowUp = new Image("res/menu/arrowUp.png");
            arrowUpp = new Image("res/menu/arrowUpp.png");
            arrowDown = new Image("res/menu/arrowDown.png");
            arrowDownp = new Image("res/menu/arrowDownp.png");

            /* LANGUAGES */
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

        loginButton = new MouseOverArea(container, loginImg, container.getWidth()/2 - loginImg.getWidth()/2 + regImg.getWidth(),      container.getHeight()/2 + 30);
        regButton = new MouseOverArea(container, regImg, container.getWidth()/2 - regImg.getWidth()/2 - regImg.getWidth(),          container.getHeight()/2 + 30);

        playButton = new MouseOverArea(container, play, container.getWidth()/2 - play.getWidth()/2 - 100,  container.getHeight()/2 - 100);
        createLvlButton = new MouseOverArea(container, createlvl, container.getWidth()/2 - play.getWidth()/2 - 100,  container.getHeight()/2 + createlvl.getHeight() * 2 - 100);
        editLvlButton = new MouseOverArea(container, editlvl, container.getWidth()/2 - play.getWidth()/2 - 100,  container.getHeight()/2 + editlvl.getHeight() * 4 - 100);
        highscoresButton = new MouseOverArea(container, highscores, container.getWidth()/2 - play.getWidth()/2 - 100,  container.getHeight()/2 + highscores.getHeight() * 6 - 100);
        quitButton = new MouseOverArea(container, quitgame, container.getWidth()/2 - play.getWidth()/2 - 100,  container.getHeight()/2 + quitgame.getHeight() * 8 - 100);

        arrowUpButtonX = new MouseOverArea(container, arrowUp, container.getWidth()/2 - arrowUp.getWidth()*2  + 100,  container.getHeight()/2);
        arrowDownButtonX = new MouseOverArea(container, arrowDown, container.getWidth()/2 - arrowDown.getWidth()*2  + 100,  container.getHeight()/2 + arrowDown.getHeight()+5);
        arrowUpButtonY = new MouseOverArea(container, arrowUp, container.getWidth()/2 - arrowUp.getWidth()*2 + arrowUp.getWidth()  + 100,  container.getHeight()/2);
        arrowDownButtonY = new MouseOverArea(container, arrowDown, container.getWidth()/2 - arrowDown.getWidth()*2 + arrowUp.getWidth()  + 100,  container.getHeight()/2 + arrowDown.getHeight()+5);

        okButton = new MouseOverArea(container, ok, container.getWidth()/2 + 100,  container.getHeight()/2 + ok.getHeight()/2);
    }

    private void renderLanguages(GameContainer container, StateBasedGame game, Graphics g){
        g.drawString("Please select your language: ", container.getWidth()/2 - 130,     container.getHeight()/2 - 50);

        //mouseover tests
        czMouseArea.render(container, g);
        czMouseArea.setMouseOverImage(czp);

        enMouseArea.render(container, g);
        enMouseArea.setMouseOverImage(enp);

        nlMouseArea.render(container, g);
        nlMouseArea.setMouseOverImage(nlp);
    }

    private void renderLogin(GameContainer container, StateBasedGame game, Graphics g){
        g.drawString(Language.getText("loginpromptKey"), container.getWidth() / 2 - 255, container.getHeight() / 2 - 50);
        loginTxtField.render(container, g);

        g.drawString(Language.getText("passpromptKey"), container.getWidth() / 2 - 255, container.getHeight() / 2 - 15);
        pwTxtField.render(container,g);

        loginButton.render(container, g);
        loginButton.setMouseOverImage(loginImgp);

        regButton.render(container, g);
        regButton.setMouseOverImage(regImgp);
    }

    private void renderMenu(GameContainer container, StateBasedGame game, Graphics g){
        playButton.render(container, g);
        playButton.setMouseOverImage(playp);

        createLvlButton.render(container, g);
        createLvlButton.setMouseOverImage(createlvlp);

        editLvlButton.render(container, g);
        editLvlButton.setMouseOverImage(editlvlp);

        highscoresButton.render(container, g);
        highscoresButton.setMouseOverImage(highscoresp);

        quitButton.render(container, g);
        quitButton.setMouseOverImage(quitgamep);
    }

    private void renderCreateLevel(GameContainer container, StateBasedGame game, Graphics g){
        g.drawString("X: " + Integer.toString(counterX), container.getWidth()/2 - arrowUp.getWidth()*2  + 150,  container.getHeight()/2 - 40);
        arrowUpButtonX.render(container, g);
        arrowUpButtonX.setMouseOverImage(arrowUpp);
        arrowDownButtonX.render(container, g);
        arrowDownButtonX.setMouseOverImage(arrowDownp);

        g.drawString("Y: " + Integer.toString(counterY), container.getWidth()/2 - arrowUp.getWidth()*2 + arrowUp.getWidth()  + 150,  container.getHeight()/2 - 40);
        arrowUpButtonY.render(container, g);
        arrowUpButtonY.setMouseOverImage(arrowUpp);
        arrowDownButtonY.render(container, g);
        arrowDownButtonY.setMouseOverImage(arrowDownp);

        okButton.render(container, g);
        okButton.setMouseOverImage(okp);
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
            playButton.setAcceptingInput(false);
            createLvlButton.setAcceptingInput(false);
            quitButton.setAcceptingInput(false);
            renderLogin(container, game, g);
        }
        else if(counter == 2){
            loginButton.setAcceptingInput(false);
            regButton.setAcceptingInput(false);
            renderMenu(container, game, g);
        }
        else if(counter == 3){
            playButton.setAcceptingInput(true);
            createLvlButton.setAcceptingInput(true);
            quitButton.setAcceptingInput(true);
            renderCreateLevel(container, game, g);
        }

    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
            /* CHOSE LANGUAGE */
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

            /* Play Game */
            if(playButton.isMouseOver()){
                game.enterState(2, new FadeOutTransition(), new FadeInTransition());
            }

            /* Create level */
            if(createLvlButton.isMouseOver()){
                counter = 3;
            }

            /* Quit Game */
            if(quitButton.isMouseOver()){
                container.exit();
            }

            /* Arrows for Dimension of the field in creation of a level */
            if(arrowUpButtonX.isMouseOver() && counterX < 25){
                counterX += 1;
            }
            else if(arrowDownButtonX.isMouseOver() && counterX > 4){
                counterX -= 1;
            }

            else if(arrowUpButtonY.isMouseOver() && counterY < 25){
                counterY += 1;
            }
            else if(arrowDownButtonY.isMouseOver() && counterY > 4){
                counterY -= 1;
            }
            else if(okButton.isMouseOver()){
                //initiate creation level state with the dimension of counterX and counterY
                CreateLevel.setX(counterX);
                CreateLevel.setY(counterY);
                game.enterState(3, new FadeOutTransition(), new FadeInTransition());
            }

            /* LOGIN HERE */
            if(loginButton.isMouseOver()){
                login = loginTxtField.getText();
                password = pwTxtField.getText();

                control = true;
                //while(control){
                    /* VERIFY THE USER */
                    int playerID = DBFunctions.verifyNickname(login);

                    /* player not found in DB, ask for registration etc. : */
                    if(playerID == -1){
                        /*
                        System.out.print(Language.getText("loginNFKey"));
                        String answer = scanner.nextLine().toLowerCase();
                        if(answer.equals("y")) {
                            registerprompt(nickname);
                            */

                        }
                    /* player FOUND in DB, verify pw: */
                    else{
                        if(DBFunctions.verifyPassword(playerID, password)){
                            System.out.print(Language.getText("loginSucKey").length() + "\n");
                            counter = 2;
                            control = false;
                        }
                        else{
                            System.out.print(Language.getText("wrongPassKey") + "\n");
                            control = false;
                        }
                    }
                }
            }
        }

//    }

    @Override
    public int getID() {
        return this.id;
    }
}

