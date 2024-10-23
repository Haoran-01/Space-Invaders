package GUI;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listening to keyboard behaviour
 */
public class PlayerListener implements KeyListener {
    private boolean fire;
    private boolean left;
    private boolean right;
    private boolean up;
    private boolean pause;
    private boolean jump;


    public boolean isPressedLeft(){
        return left;
    }
    public boolean isPressedRight(){
        return right;
    }
    public boolean isPressedUp(){
        return up;
    }
    public boolean isPressedFire(){
        return fire;
    }
    public boolean isPressedPause(){
        return pause;
    }
    public boolean isPressedJump(){
        return jump;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyChar() == 'P' || e.getKeyChar() == 'p') {
            pause = true;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            fire = true;
        } else if (e.getKeyCode() == 'J' || e.getKeyCode() == 'j') {
            jump = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            up = false;
        } else if (e.getKeyCode() == 'J' || e.getKeyCode() == 'j') {
            jump = false;
        }
    }

    public boolean restPaused(){
        return pause = false;
    }
    public void restFire() {
        fire = false;
    }
    public void restJump(){
        jump = false;
    }
}
