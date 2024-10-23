package GUI;
import all.SpaceGame;
import ucd.comp2011j.engine.MenuCommands;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *Listening to mouse behaviour
 */
public class MenuListener implements MenuCommands, MouseListener {
    private boolean newGame;
    private boolean aboutScreen;
    private boolean highScreen;
    private boolean menu;
    private boolean exit;
    @Override

    public boolean hasPressedNewGame() {
        return newGame;
    }

    @Override
    public boolean hasPressedAboutScreen() {
        return aboutScreen;
    }

    @Override
    public boolean hasPressedHighScoreScreen() {
        return highScreen;
    }

    @Override
    public boolean hasPressedMenu() {
        return menu;
    }

    @Override
    public boolean hasPressedExit() {
        return exit;
    }

    @Override
    public void resetKeyPresses() {
        menu = false;
        newGame = false;
        aboutScreen = false;
        highScreen = false;
        exit = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Rectangle newGameButton = new Rectangle(SpaceGame.GAME_WIDTH/2 - 70,SpaceGame.GAME_LENGTH / 5 + 30,200,100);
        Rectangle controlButton = new Rectangle(SpaceGame.GAME_WIDTH/2 - 70, SpaceGame.GAME_LENGTH * 2/5 + 30,200,200);
        Rectangle scoreListButton = new Rectangle(SpaceGame.GAME_WIDTH/2 - 70,SpaceGame.GAME_LENGTH * 3/5 + 30,200,100);
        Rectangle exitGameButton = new Rectangle(SpaceGame.GAME_WIDTH/2 - 70,SpaceGame.GAME_LENGTH * 4/5 + 30,200, 100);
        Rectangle menuButton = new Rectangle(SpaceGame.GAME_WIDTH/2 - 70,SpaceGame.GAME_LENGTH * 4/5 - 70,200, 100);

        if (e.getButton() == MouseEvent.BUTTON1) {
            if (newGameButton.contains(e.getLocationOnScreen())) {
                newGame = true;
            } else if (scoreListButton.contains(e.getLocationOnScreen())) {
                highScreen = true;
            } else if (exitGameButton.contains(e.getLocationOnScreen())) {
                exit = true;
            } else if (controlButton.contains(e.getLocationOnScreen())){
                aboutScreen = true;
            } else if (menuButton.contains(e.getLocationOnScreen())){
                menu = true;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
