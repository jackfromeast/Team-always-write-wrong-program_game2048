import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

class MyMouseListener implements MouseListener {
    private Game2048 game;
    private int btnEvn;

    public MyMouseListener(Game2048 game, int btnEvn) {
        this.game = game;
        this.btnEvn = btnEvn;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (btnEvn) {
        case Game2048.BUTTON_NEW_GAME: {
            game.newGame();
        }
            break;

        case Game2048.BUTTON_ABOUT: {
            game.about();
        }
            break;

        case Game2048.BUTTON_EXIT: {
            game.processWindowEvent(new WindowEvent(game,
                    WindowEvent.WINDOW_CLOSING));
        }
            break;
        }
    }
}

