package GUI;
import data.ScoreChange;
import all.SpaceGame;
import ucd.comp2011j.engine.GameManager;

import javax.swing.*;

/**
 * Code to run the game
 */
public class WindowApplication {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setSize(SpaceGame.GAME_WIDTH,SpaceGame.GAME_LENGTH);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Space game");
        window.setLocationRelativeTo(null);

        PlayerListener pl = new PlayerListener();
        window.addKeyListener(pl);
        MenuListener ml = new MenuListener();
        window.addMouseListener(ml);
        SpaceGame spaceGame = new SpaceGame(pl);
        GameScr gs = new GameScr(spaceGame);
        MenuScr ms = new MenuScr();
        AboutScr as = new AboutScr();
        ScoreChange sk = new ScoreChange();
        ScoreScr ss = new ScoreScr(sk);
        GameManager gm = new GameManager(spaceGame,window,ml,ms,as,ss,gs,sk);
        window.setVisible(true);
        gm.run();
    }
}
