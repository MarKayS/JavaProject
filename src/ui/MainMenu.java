package ui;

import core.Language;
import core.Level;
import org.newdawn.slick.Color;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import persistance.DBFunctions;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by MKS on 8. 11. 2014.
 */
public class MainMenu extends BasicGameState {
    private int id, playerID;
    private StateBasedGame game;
    boolean control = true;
    boolean pwLowerCase = false, pwUpperCase = false, pwNumber = false, badPwPrompt = false;
    Input input;
    int counter = 0;
    int page = 0;
    int max = 0;
    String login, password;
    public int counterX = 4, counterY = 4;

    TextField loginTxtField = null, pwTxtField = null, surnameTxtField = null, nameTxtField = null;

    //Images & MouseOverAreas
    Image logo = null, cz = null, nl = null, en = null, czp = null, enp = null, nlp = null, loginImg = null, loginImgp = null, regImg = null, regImgp = null,
            play = null, playp = null, createlvl = null, createlvlp = null, editlvl = null, editlvlp = null, highscores = null, highscoresp = null, quitgame = null, quitgamep = null,
            arrowUp = null, arrowUpp = null, arrowDown = null, arrowDownp = null, ok = null, okp = null, okRegister = null, okRegisterp = null, placeholder = null, placeholderp = null,
            arrowRight = null, arrowRightp;

    MouseOverArea czMouseArea, enMouseArea, nlMouseArea, loginButton, regButton, playButton, createLvlButton, editLvlButton, highscoresButton, quitButton,
            arrowUpButtonX, arrowDownButtonX, arrowUpButtonY, arrowDownButtonY, okButton, okRegisterButton, placeholder1, placeholder2, placeholder3, arrowLeftButton, arrowRightButton;

    MainMenu(int i) {
        this.id = i;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        this.game = game;

        this.nameTxtField = new TextField(container, container.getDefaultFont(), container.getWidth() / 2 + 65, container.getHeight() / 2 + 55, 150, 20);
        nameTxtField.setBackgroundColor(Color.white);
        nameTxtField.setBorderColor(Color.gray);
        nameTxtField.setTextColor(Color.black);
        this.surnameTxtField = new TextField(container, container.getDefaultFont(), container.getWidth() / 2 + 65, container.getHeight() / 2 + 20, 150, 20);
        surnameTxtField.setBackgroundColor(Color.white);
        surnameTxtField.setBorderColor(Color.gray);
        surnameTxtField.setTextColor(Color.black);
        this.loginTxtField = new TextField(container, container.getDefaultFont(), container.getWidth() / 2 + 65, container.getHeight() / 2 - 50, 150, 20);
        loginTxtField.setBackgroundColor(Color.white);
        loginTxtField.setBorderColor(Color.gray);
        loginTxtField.setTextColor(Color.black);
        this.pwTxtField = new TextField(container, container.getDefaultFont(), container.getWidth() / 2 + 65, container.getHeight() / 2 - 15, 150, 20);
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
            okRegister = new Image("res/menu/ok.png");
            okRegisterp = new Image("res/menu/okp.png");


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
            placeholder = new Image("res/menu/placeholder.png");
            placeholderp = new Image("res/menu/placeholderp.png");
            arrowRight = new Image("res/menu/arrowRight.png");
            arrowRightp = new Image("res/menu/arrowRightp.png");

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
        czMouseArea = new MouseOverArea(container, cz, container.getWidth() / 2 - cz.getWidth() / 2 + cz.getWidth() + 50, container.getHeight() / 2);
        enMouseArea = new MouseOverArea(container, en, container.getWidth() / 2 - en.getWidth() / 2 - en.getWidth() - 50, container.getHeight() / 2);
        nlMouseArea = new MouseOverArea(container, nl, container.getWidth() / 2 - nl.getWidth() / 2, container.getHeight() / 2);

        loginButton = new MouseOverArea(container, loginImg, container.getWidth() / 2 - loginImg.getWidth() / 2 + regImg.getWidth(), container.getHeight() / 2 + 30);
        regButton = new MouseOverArea(container, regImg, container.getWidth() / 2 - regImg.getWidth() / 2 - regImg.getWidth(), container.getHeight() / 2 + 30);
        okRegisterButton = new MouseOverArea(container, okRegister, container.getWidth() / 2 - 189, container.getHeight() / 2 + 135);

        playButton = new MouseOverArea(container, play, container.getWidth() / 2 - play.getWidth() / 2 - 100, container.getHeight() / 2 - 100);
        createLvlButton = new MouseOverArea(container, createlvl, container.getWidth() / 2 - play.getWidth() / 2 - 100, container.getHeight() / 2 + createlvl.getHeight() * 2 - 100);
        editLvlButton = new MouseOverArea(container, editlvl, container.getWidth() / 2 - play.getWidth() / 2 - 100, container.getHeight() / 2 + editlvl.getHeight() * 4 - 100);
        highscoresButton = new MouseOverArea(container, highscores, container.getWidth() / 2 - play.getWidth() / 2 - 100, container.getHeight() / 2 + highscores.getHeight() * 6 - 100);
        quitButton = new MouseOverArea(container, quitgame, container.getWidth() / 2 - play.getWidth() / 2 - 100, container.getHeight() / 2 + quitgame.getHeight() * 8 - 100);

        arrowUpButtonX = new MouseOverArea(container, arrowUp, container.getWidth() / 2 - arrowUp.getWidth() * 2 + 100, container.getHeight() / 2);
        arrowDownButtonX = new MouseOverArea(container, arrowDown, container.getWidth() / 2 - arrowDown.getWidth() * 2 + 100, container.getHeight() / 2 + arrowDown.getHeight() + 5);
        arrowUpButtonY = new MouseOverArea(container, arrowUp, container.getWidth() / 2 - arrowUp.getWidth() * 2 + arrowUp.getWidth() + 100, container.getHeight() / 2);
        arrowDownButtonY = new MouseOverArea(container, arrowDown, container.getWidth() / 2 - arrowDown.getWidth() * 2 + arrowUp.getWidth() + 100, container.getHeight() / 2 + arrowDown.getHeight() + 5);

        okButton = new MouseOverArea(container, ok, container.getWidth() / 2 + 100, container.getHeight() / 2 + ok.getHeight() / 2);

        placeholder1 = new MouseOverArea(container, placeholder, ((container.getScreenWidth() - (330 * 3)) / 4), container.getScreenHeight() / 2 - 165);
        placeholder2 = new MouseOverArea(container, placeholder, ((container.getScreenWidth() - (330 * 3)) / 4) * 2 + 330, container.getScreenHeight() / 2 - 165);
        placeholder3 = new MouseOverArea(container, placeholder, ((container.getScreenWidth() - (330 * 3)) / 4) * 3 + 660, container.getScreenHeight() / 2 - 165);

        arrowLeftButton = new MouseOverArea(container, arrowRight.getFlippedCopy(true, false), (container.getScreenWidth() / 2 - arrowRight.getWidth()), container.getScreenHeight() / 2 + 400);
        arrowRightButton = new MouseOverArea(container, arrowRight, (container.getScreenWidth() / 2), container.getScreenHeight() / 2 + 400);

        arrowLeftButton.setMouseOverImage(arrowRightp.getFlippedCopy(true, false));
        arrowRightButton.setMouseOverImage(arrowRightp);

        placeholder1.setMouseOverImage(placeholderp);
        placeholder2.setMouseOverImage(placeholderp);
        placeholder3.setMouseOverImage(placeholderp);

        playButton.setAcceptingInput(false);
        createLvlButton.setAcceptingInput(false);
        editLvlButton.setAcceptingInput(false);
        highscoresButton.setAcceptingInput(false);
        quitButton.setAcceptingInput(false);
        okButton.setAcceptingInput(false);
        okRegisterButton.setAcceptingInput(false);
    }

    private void renderLanguages(GameContainer container, StateBasedGame game, Graphics g) {
        g.drawString("Please select your language: ", container.getWidth() / 2 - 130, container.getHeight() / 2 - 50);

        //mouseover tests
        czMouseArea.render(container, g);
        czMouseArea.setMouseOverImage(czp);

        enMouseArea.render(container, g);
        enMouseArea.setMouseOverImage(enp);

        nlMouseArea.render(container, g);
        nlMouseArea.setMouseOverImage(nlp);
    }

    private void renderLogin(GameContainer container, StateBasedGame game, Graphics g) {
        g.drawString(Language.getText("loginpromptKey"), container.getWidth() / 2 - 189, container.getHeight() / 2 - 50);
        loginTxtField.render(container, g);

        g.drawString(Language.getText("passpromptKey"), container.getWidth() / 2 - 189, container.getHeight() / 2 - 15);
        pwTxtField.render(container, g);

        loginButton.render(container, g);
        loginButton.setMouseOverImage(loginImgp);

        regButton.render(container, g);
        regButton.setMouseOverImage(regImgp);

        if (playerID == -1) {
            g.drawString(Language.getText("loginNFKey"), container.getWidth() / 2 - 189, container.getHeight() / 2 + 100);
            okRegisterButton.render(container, g);
            okRegisterButton.setMouseOverImage(okRegisterp);
        }

        if (playerID == -2) {
            g.drawString(Language.getText("wrongPassKey"), container.getWidth() / 2 - 189, container.getHeight() / 2 + 100);
        }
    }

    private void renderRegister(GameContainer container, StateBasedGame game, Graphics g) {
        g.drawString(Language.getText("loginpromptKey"), container.getWidth() / 2 - 189, container.getHeight() / 2 - 50);
        loginTxtField.render(container, g);

        g.drawString(Language.getText("passpromptKey"), container.getWidth() / 2 - 189, container.getHeight() / 2 - 15);
        pwTxtField.render(container, g);

        g.drawString(Language.getText("namepromptKey"), container.getWidth() / 2 - 189, container.getHeight() / 2 + 20);
        nameTxtField.render(container, g);

        g.drawString(Language.getText("surnamepromptKey"), container.getWidth() / 2 - 189, container.getHeight() / 2 + 55);
        surnameTxtField.render(container, g);

        regButton.setLocation(container.getWidth() / 2 - 189, container.getHeight() / 2 + 90);
        regButton.render(container, g);
        regButton.setMouseOverImage(regImgp);

        if (badPwPrompt) {
            g.drawString(Language.getText("wrongPassTypeKey"), 250, container.getHeight() / 2 + 160);
        }

    }

    private void renderMenu(GameContainer container, StateBasedGame game, Graphics g) {
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


    private void renderCreateLevel(GameContainer container, StateBasedGame game, Graphics g) {
        g.drawString("X: " + Integer.toString(counterX), container.getWidth() / 2 - arrowUp.getWidth() * 2 + 150, container.getHeight() / 2 - 40);
        arrowUpButtonX.render(container, g);
        arrowUpButtonX.setMouseOverImage(arrowUpp);
        arrowDownButtonX.render(container, g);
        arrowDownButtonX.setMouseOverImage(arrowDownp);

        g.drawString("Y: " + Integer.toString(counterY), container.getWidth() / 2 - arrowUp.getWidth() * 2 + arrowUp.getWidth() + 150, container.getHeight() / 2 - 40);
        arrowUpButtonY.render(container, g);
        arrowUpButtonY.setMouseOverImage(arrowUpp);
        arrowDownButtonY.render(container, g);
        arrowDownButtonY.setMouseOverImage(arrowDownp);

        okButton.render(container, g);
        okButton.setMouseOverImage(okp);
    }



    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(logo, container.getWidth() / 2 - logo.getWidth() / 2, 50);

        if (counter == 0) {
            renderLanguages(container, game, g);
        } else if (counter == 1) {
            czMouseArea.setAcceptingInput(false);
            enMouseArea.setAcceptingInput(false);
            nlMouseArea.setAcceptingInput(false);
            renderLogin(container, game, g);

            okRegisterButton.setAcceptingInput(true);
        } else if (counter == 2) {
            loginButton.setAcceptingInput(false);
            regButton.setAcceptingInput(false);
            renderMenu(container, game, g);

            playButton.setAcceptingInput(true);
            createLvlButton.setAcceptingInput(true);
            editLvlButton.setAcceptingInput(true);
            highscoresButton.setAcceptingInput(true);
            quitButton.setAcceptingInput(true);
        } else if (counter == 3) {
            renderCreateLevel(container, game, g);
            okButton.setAcceptingInput(true);
        } else if (counter == 4) {
            okButton.setAcceptingInput(false);
            loginButton.setAcceptingInput(false);
            renderRegister(container, game, g);
        } else if (counter == 5) {
            LevelRenderer levelRenderer = new LevelRenderer(1);
            ArrayList<Level> levels;
            levels = DBFunctions.getLevels(1);
            placeholder1.render(container, g);
            placeholder2.render(container, g);
            placeholder3.render(container, g);
            arrowRightButton.render(container, g);
            arrowLeftButton.render(container, g);
            int var1 = 0, var2 = 0, var3 = 0;
            boolean v1 = true, v2 = true, v3 = true;
            if (page == 0) {
                levels = DBFunctions.getLevels(1);
                var1 = 0;
                var2 = 1;
                var3 = 2;
            } else if (page == 1) {
                levels = DBFunctions.getLevels(2);
                var1 = 0;
                var2 = 1;
                var3 = 2;
            } else {
                levels = DBFunctions.getLevels(0);
                max = DBFunctions.getLevels(0).size();
                if (page * 3 - 6 >= max) {
                    v1 = false;
                }
                if (page * 3 - 5 >= max) {
                    v2 = false;
                }
                if (page * 3 - 4 >= max) {
                    v3 = false;
                }
                var1 = page * 3 - 6;
                var2 = page * 3 - 5;
                var3 = page * 3 - 4;
            }
            if (v1) {
                levelRenderer.renderPreview(levels.get(var1), g, new Point(((container.getScreenWidth() - (330 * 3)) / 4) + 5, container.getScreenHeight() / 2 - 160), 0.21f);
                placeholder1.render(container, g);
            }
            if (v2) {
                levelRenderer.renderPreview(levels.get(var2), g, new Point(((container.getScreenWidth() - (330 * 3)) / 4) * 2 + 335, container.getScreenHeight() / 2 - 160), 0.21f);
                placeholder2.render(container, g);
            }
            if (v3) {
                levelRenderer.renderPreview(levels.get(var3), g, new Point(((container.getScreenWidth() - (330 * 3)) / 4) * 3 + 665, container.getScreenHeight() / 2 - 160), 0.21f);
                placeholder3.render(container, g);
            }
        } else if (counter == 6) {
            LevelRenderer levelRenderer = new LevelRenderer(2);
            ArrayList<Level> levels;

            arrowRightButton.render(container, g);
            arrowLeftButton.render(container, g);
            int var1 = 0, var2 = 0, var3 = 0;
            boolean v1 = true, v2 = true, v3 = true;
            levels = DBFunctions.getLevels(0);
            max = DBFunctions.getLevels(0).size();

            if (page * 3 + 0 >= max) {
                v1 = false;
            }
            if (page * 3 + 1 >= max) {
                v2 = false;
            }
            if (page * 3 + 2 >= max) {
                v3 = false;
            }
            var1 = page * 3 + 0;
            var2 = page * 3 + 1;
            var3 = page * 3 + 2;

            if (v1) {
                levelRenderer.renderPreview(levels.get(var1), g, new Point(((container.getScreenWidth() - (330 * 3)) / 4) + 5, container.getScreenHeight() / 2 - 160), 0.21f);
                placeholder1.render(container, g);
            }
            if (v2) {
                levelRenderer.renderPreview(levels.get(var2), g, new Point(((container.getScreenWidth() - (330 * 3)) / 4) * 2 + 335, container.getScreenHeight() / 2 - 160), 0.21f);
                placeholder2.render(container, g);
            }
            if (v3) {
                levelRenderer.renderPreview(levels.get(var3), g, new Point(((container.getScreenWidth() - (330 * 3)) / 4) * 3 + 665, container.getScreenHeight() / 2 - 160), 0.21f);
                placeholder3.render(container, g);
            }
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
            /* Chose language */
        if (counter == 0) {
            if (czMouseArea.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                Language.language("cs");
                counter = 1;
            } else if (nlMouseArea.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                Language.language("nl");
                counter = 1;
            } else if (enMouseArea.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                Language.language("en");
                counter = 1;
            }
        }

        if (counter == 1) {
            /* Login Prompt */
            if (loginButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                login = loginTxtField.getText();
                password = pwTxtField.getText();

                /* VERIFY THE USER */
                playerID = DBFunctions.verifyNickname(login); //if verification returns -1, a prompt is displayed (implemented in renderlogin()

                if (playerID != -1) {
                    /* player FOUND in DB, verify pw: */
                    if (DBFunctions.verifyPassword(playerID, password)) {
                        /*correct login && pw, -> main menu*/
                        counter = 2;
                    } else {
                      /*player found, but entered incorrect pw*/
                        playerID = -2; // == render a pw prompt (implemented in renderlogin()
                    }
                }
            }

            if ((regButton.isMouseOver() || okRegisterButton.isMouseOver()) && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                /*user confirms registration prompt */
                counter = 4;
            }
        }

        if (counter == 4) {
            /* Registration form */
            if (regButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                password = pwTxtField.getText();
                for (int i = 0; i < password.length(); i++) {
                    if (Character.isLowerCase(password.charAt(i))) {
                        pwLowerCase = true;
                    }
                    if (Character.isUpperCase(password.charAt(i))) {
                        pwUpperCase = true;
                    }
                    if (Character.isDigit(password.charAt(i))) {
                        pwNumber = true;
                    }
                }
                if (pwLowerCase && pwUpperCase && pwNumber && pwTxtField.getText().length() >= 8) {
                    DBFunctions.register(nameTxtField.getText(), surnameTxtField.getText(), loginTxtField.getText(), pwTxtField.getText());
                    counter = 2;
                } else {
                    badPwPrompt = true;
                }
            }
        }

        if (counter == 2) {
             /* Play Game */
            if (playButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                //game.enterState(2, new FadeOutTransition(), new FadeInTransition());
                counter = 5;
            }

            /* Create level */
            else if (createLvlButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                counter = 3;
            } else if (editLvlButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                counter = 6;
            }

            /* Quit Game */
            else if (quitButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                container.exit();
            }
        }

        if (counter == 3) {
            /* Arrows for Dimension of the field in creation of a level */
            if (arrowUpButtonX.isMouseOver() && counterX < 25 && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                counterX += 1;
            } else if (arrowDownButtonX.isMouseOver() && counterX > 4 && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                counterX -= 1;
            } else if (arrowUpButtonY.isMouseOver() && counterY < 25 && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                counterY += 1;
            } else if (arrowDownButtonY.isMouseOver() && counterY > 4 && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                counterY -= 1;
            } else if (okButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                //initiate creation level state with the dimension of counterX and counterY
                CreateLevel.setX(counterX);
                CreateLevel.setY(counterY);
                CreateLevel.initiateLevel(container);
                CreateLevel.initiate = true;
                game.enterState(3, new FadeOutTransition(), new FadeInTransition());
            }
        }
        if (counter == 5) {
            if (arrowLeftButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                if (page > 0) page--;
            }
            if (arrowRightButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                max = (DBFunctions.getLevels(0).size() + DBFunctions.getLevels(1).size() + DBFunctions.getLevels(2).size()) / 3;
                System.out.print(max + "\n");
                if (page < max) page++;
            }
            if (placeholder1.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                if          (page == 0) Game.setGame(0, 1);

                else if     (page == 1) Game.setGame(0, 2);

                else                    Game.setGame(page*3-6, 0);
                game.enterState(2, new FadeOutTransition(), new FadeInTransition());
            }
            if (placeholder2.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                if (page == 0) Game.setGame(1,1);

                else if (page == 1) Game.setGame(1, 2);

                else Game.setGame(page*3-5, 0);
                game.enterState(2, new FadeOutTransition(), new FadeInTransition());
            }
            if (placeholder3.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                if (page == 0) Game.setGame(2, 1);

                else if (page == 1) Game.setGame(2,2);

                else Game.setGame(page*3 -  4, 0);
                game.enterState(2, new FadeOutTransition(), new FadeInTransition());
            }
        }
        if(counter == 6){
            int levelNumber = 0;
            if (arrowLeftButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                if (page > 0) page--;
            }
            if (arrowRightButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                max = DBFunctions.getLevels(0).size() / 3;
                System.out.print(max + "\n");
                if (page < max) page++; //kek
            }
            if (placeholder1.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)){
                levelNumber = page * 3 + 0;
                CreateLevel.editLevel(levelNumber, container);
                game.enterState(3, new FadeOutTransition(), new FadeInTransition());
            }
            if (placeholder2.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                //levelNumber = page * 3 + 1;
            }
            if (placeholder3.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                //levelNumber = page * 3 + 2;
            }
        }

    }

    @Override
    public int getID() {
        return this.id;
    }
}
