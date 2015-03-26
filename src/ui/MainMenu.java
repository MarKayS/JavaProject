package ui;

import core.Language;
import core.Level;
import core.Player;
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
import java.lang.reflect.Array;
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
    Player myPlayer;
    public int counterX = 4, counterY = 4;

    /*
    * counter 0 - language
    * counter 1 - login
    * conter 2 - menu
    * counter 3 - creator
    * counter 4 - register
    * counter 5 - game preview
    * counter 6 - editor
    * counter 7 - highscore
    *
     */

    TextField loginTxtField = null, pwTxtField = null, surnameTxtField = null, nameTxtField = null;

    //Images & MouseOverAreas
    Image logo = null, cz = null, nl = null, en = null, czp = null, enp = null, nlp = null, loginImg = null, loginImgp = null, regImg = null, regImgp = null,
            play = null, playp = null, createlvl = null, createlvlp = null, editlvl = null, editlvlp = null, highscores = null, highscoresp = null, quitgame = null, quitgamep = null,
            arrowUp = null, arrowUpp = null, arrowDown = null, arrowDownp = null, ok = null, okp = null, okRegister = null, okRegisterp = null, placeholder = null, placeholderp = null,
            arrowRight = null, arrowRightp;

    ArrayList<MouseOverArea> moas = new ArrayList<>();

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
        //screen 0 - Languages = 0 - 2
        moas.add(czMouseArea = new MouseOverArea(container, cz, container.getWidth() / 2 - cz.getWidth() / 2 + cz.getWidth() + 50, container.getHeight() / 2));
        moas.add(enMouseArea = new MouseOverArea(container, en, container.getWidth() / 2 - en.getWidth() / 2 - en.getWidth() - 50, container.getHeight() / 2));
        moas.add(nlMouseArea = new MouseOverArea(container, nl, container.getWidth() / 2 - nl.getWidth() / 2, container.getHeight() / 2));
        //screen 1/4 - Login/Register screen = 3-5
        moas.add(loginButton = new MouseOverArea(container, loginImg, container.getWidth() / 2 - loginImg.getWidth() / 2 + regImg.getWidth(), container.getHeight() / 2 + 30));
        moas.add(regButton = new MouseOverArea(container, regImg, container.getWidth() / 2 - regImg.getWidth() / 2 - regImg.getWidth(), container.getHeight() / 2 + 30));
        moas.add(okRegisterButton = new MouseOverArea(container, okRegister, container.getWidth() / 2 - 189, container.getHeight() / 2 + 135));
        //screen 2 - Menu screen = 6 - 10
        moas.add(playButton = new MouseOverArea(container, play, container.getWidth() / 2 - play.getWidth() / 2 - 100, container.getHeight() / 2 - 100));
        moas.add(createLvlButton = new MouseOverArea(container, createlvl, container.getWidth() / 2 - play.getWidth() / 2 - 100, container.getHeight() / 2 + createlvl.getHeight() * 2 - 100));
        moas.add(editLvlButton = new MouseOverArea(container, editlvl, container.getWidth() / 2 - play.getWidth() / 2 - 100, container.getHeight() / 2 + editlvl.getHeight() * 4 - 100));
        moas.add(highscoresButton = new MouseOverArea(container, highscores, container.getWidth() / 2 - play.getWidth() / 2 - 100, container.getHeight() / 2 + highscores.getHeight() * 6 - 100));
        moas.add(quitButton = new MouseOverArea(container, quitgame, container.getWidth() / 2 - play.getWidth() / 2 - 100, container.getHeight() / 2 + quitgame.getHeight() * 8 - 100));
        //screen 3 - Level Creator = 11 - 15
        moas.add(arrowUpButtonX = new MouseOverArea(container, arrowUp, container.getWidth() / 2 - arrowUp.getWidth() * 2 + 100, container.getHeight() / 2));
        moas.add(arrowDownButtonX = new MouseOverArea(container, arrowDown, container.getWidth() / 2 - arrowDown.getWidth() * 2 + 100, container.getHeight() / 2 + arrowDown.getHeight() + 5));
        moas.add(arrowUpButtonY = new MouseOverArea(container, arrowUp, container.getWidth() / 2 - arrowUp.getWidth() * 2 + arrowUp.getWidth() + 100, container.getHeight() / 2));
        moas.add(arrowDownButtonY = new MouseOverArea(container, arrowDown, container.getWidth() / 2 - arrowDown.getWidth() * 2 + arrowUp.getWidth() + 100, container.getHeight() / 2 + arrowDown.getHeight() + 5));
        moas.add(okButton = new MouseOverArea(container, ok, container.getWidth() / 2 + 100, container.getHeight() / 2 + ok.getHeight() / 2));
        //screen 5/6 - Preview Screen = 16 - 20
        moas.add(placeholder1 = new MouseOverArea(container, placeholder, ((container.getScreenWidth() - (330 * 3)) / 4), container.getScreenHeight() / 2 - 165));
        moas.add(placeholder2 = new MouseOverArea(container, placeholder, ((container.getScreenWidth() - (330 * 3)) / 4) * 2 + 330, container.getScreenHeight() / 2 - 165));
        moas.add(placeholder3 = new MouseOverArea(container, placeholder, ((container.getScreenWidth() - (330 * 3)) / 4) * 3 + 660, container.getScreenHeight() / 2 - 165));
        moas.add(arrowLeftButton = new MouseOverArea(container, arrowRight.getFlippedCopy(true, false), (container.getScreenWidth() / 2 - arrowRight.getWidth()), container.getScreenHeight() / 2 + 400));
        moas.add(arrowRightButton = new MouseOverArea(container, arrowRight, (container.getScreenWidth() / 2), container.getScreenHeight() / 2 + 400));


        arrowLeftButton.setMouseOverImage(arrowRightp.getFlippedCopy(true, false));
        arrowRightButton.setMouseOverImage(arrowRightp);

        placeholder1.setMouseOverImage(placeholderp);
        placeholder2.setMouseOverImage(placeholderp);
        placeholder3.setMouseOverImage(placeholderp);

    }

    // Screen 0
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

    // Screen 1
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

    // Screen 4
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

    // Screen 2
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

        /* Only ADMINS can create or edit levels */
        if(!myPlayer.isAdmin_rights()){
            editLvlButton.setAcceptingInput(false);
            editLvlButton.setNormalColor(Color.darkGray);

            createLvlButton.setAcceptingInput(false);
            createLvlButton.setNormalColor(Color.darkGray);
        }
    }

    // Screen 3
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

    // Screen 5
    private void renderGamePreviews(GameContainer container, StateBasedGame game, Graphics g) {
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

    }

    // Screen 6
    private void renderEditPreviews(GameContainer container, StateBasedGame game, Graphics g) {
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

    // Screen 7
    private void renderHighScores(GameContainer container, StateBasedGame game, Graphics g){

    }

    //TODO: Screen 7 @MatousVales - Highscores

    private void inputsetter(int screenNumber) {
        for (MouseOverArea m : moas) {
            m.setAcceptingInput(false);
        }
        switch (screenNumber) {
            case 0:
                for (int i = 0; i <= 2; i++) {
                    moas.get(i).setAcceptingInput(true);
                }
                break;
            case 1:
                for (int i = 3; i <= 5; i++) {
                    moas.get(i).setAcceptingInput(true);
                }
                break;
            case 2:
                for (int i = 6; i <= 10; i++) {
                    moas.get(i).setAcceptingInput(true);
                }
                break;
            case 3:
                for (int i = 11; i <= 15; i++) {
                    moas.get(i).setAcceptingInput(true);
                }
                break;
            case 4:
                for (int i = 6; i <= 10; i++) {
                    moas.get(i).setAcceptingInput(true);
                }
                break;
            case 5:
                for (int i = 16; i <= 20; i++) {
                    moas.get(i).setAcceptingInput(true);
                }
                break;
            case 6:
                for (int i = 16; i <= 20; i++) {
                    moas.get(i).setAcceptingInput(true);
                }
                break;
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        g.drawImage(logo, container.getWidth() / 2 - logo.getWidth() / 2, 50);
        inputsetter(counter);
        if (counter == 0) {
            renderLanguages(container, game, g);
        } else if (counter == 1) {
            renderLogin(container, game, g);
        } else if (counter == 2) {
            renderMenu(container, game, g);
        } else if (counter == 3) {
            renderCreateLevel(container, game, g);
        } else if (counter == 4) {
            renderRegister(container, game, g);
        } else if (counter == 5) {
            renderGamePreviews(container, game, g);
        } else if (counter == 6) {
            renderEditPreviews(container, game, g);
        } else if (counter == 7) {
            renderHighScores(container, game, g);
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
                ArrayList<Player> players = DBFunctions.getPlayers();
                if (playerID != -1) {
                    /* player FOUND in DB, verify pw: */
                    if (DBFunctions.verifyPassword(playerID, password)) {
                        /*correct login && pw, -> main menu*/
                        /* Create the player */
                        for (Player player : players) {
                            if (player.getNickname().toLowerCase().equals(login.toLowerCase())) {
                                myPlayer = new Player(player.getPlayerID(), player.getName(), player.getSurname(), player.getPassword(), player.getNickname(), player.isAdmin_rights());
                            }
                        }
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
                    //setter
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
            }

            /* Edit level */
            else if (editLvlButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                counter = 6;
            }

            /* Quit Game */
            else if (quitButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                container.exit();
            }

            /* Test of player creation */
            else if (input.isKeyPressed(Input.KEY_HOME)) {
                System.out.print(myPlayer.getNickname() + "\t" + myPlayer.isAdmin_rights());
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
                if (page == 0) Game.setGame(0, 1);

                else if (page == 1) Game.setGame(0, 2);

                else Game.setGame(page * 3 - 6, 0);
                game.enterState(2, new FadeOutTransition(), new FadeInTransition());
            }
            if (placeholder2.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                if (page == 0) Game.setGame(1, 1);

                else if (page == 1) Game.setGame(1, 2);

                else Game.setGame(page * 3 - 5, 0);
                game.enterState(2, new FadeOutTransition(), new FadeInTransition());
            }
            if (placeholder3.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                if (page == 0) Game.setGame(2, 1);

                else if (page == 1) Game.setGame(2, 2);

                else Game.setGame(page * 3 - 4, 0);
                game.enterState(2, new FadeOutTransition(), new FadeInTransition());
            }
        }
        if (counter == 6) {
            int levelNumber = 0;
            if (arrowLeftButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                if (page > 0) page--;
            }
            if (arrowRightButton.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                max = DBFunctions.getLevels(0).size() / 3;
                System.out.print(max + "\n");
                if (page < max) page++; //kek
            }
            if (placeholder1.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                levelNumber = page * 3 + 0;
                CreateLevel.editLevel(levelNumber, container);
                game.enterState(3, new FadeOutTransition(), new FadeInTransition());
            }
            if (placeholder2.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                levelNumber = page * 3 + 1;
                CreateLevel.editLevel(levelNumber, container);
                game.enterState(3, new FadeOutTransition(), new FadeInTransition());
            }
            if (placeholder3.isMouseOver() && input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                levelNumber = page * 3 + 2;
                CreateLevel.editLevel(levelNumber, container);
                game.enterState(3, new FadeOutTransition(), new FadeInTransition());
            }

        }
        if (counter == 7) {
            //DBFunctions.gethighsccores something something

        }

        if(input.isKeyPressed(Input.KEY_ESCAPE) && counter >=0){
            if(counter > 2 && counter != 4){
                counter = 2;
            }
            else if(counter == 4){
                counter = 1;
            }
            else if (counter == 1){
                counter = 0;
            }
            else if(counter == 2){
                container.exit();
            }
        }

    }

    @Override
    public int getID() {
        return this.id;
    }

    public String getLogin() {
        return login;
    }
}


