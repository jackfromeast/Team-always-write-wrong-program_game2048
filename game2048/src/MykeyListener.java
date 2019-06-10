import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

class MyKeyListener implements KeyListener {
    /*
     * 键盘代码 w/87 s/83 a/65 d/68 up/38 down/40 left/37 right/39 f1/112 f2/113
     * f3/114
     */
    final public static int KEY_W = 0xf57;
    final public static int KEY_S = 0xf53;
    final public static int KEY_A = 0xf41;
    final public static int KEY_D = 0xf44;
    final public static int KEY_UP = 0xf26;
    final public static int KEY_DOWN = 0xf28;
    final public static int KEY_LEFT = 0xf25;
    final public static int KEY_RIGHT = 0xf27;

    private Game2048 game;
    
    /**
     * 构造一个键盘监听器
     * 
     * @param game
     *            主界面
     */
    public MyKeyListener(Game2048 game) {
        this.game = game;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode() + 0xf00;

        switch (game.getGameState()) {
        case Game2048.GAME_CONTINUE: {
            switch (keyCode) {
            case MyKeyListener.KEY_W:
            case MyKeyListener.KEY_UP: {
                game.up();
            }
                break;

            case MyKeyListener.KEY_S:
            case MyKeyListener.KEY_DOWN: {
                game.down();
            }
                break;

            case MyKeyListener.KEY_A:
            case MyKeyListener.KEY_LEFT: {
                game.left();
            }
                break;

            case MyKeyListener.KEY_D:
            case MyKeyListener.KEY_RIGHT: {
                game.right();
            }
                break;
            }
        }
            break;

        case Game2048.GAME_OVER: {
        	float time=game.getTime()/1000;
        	float n=(float)(Math.round((game.getCount()/time)*100)/100);
            int jop = JOptionPane
                    .showConfirmDialog(null, "很遗憾，你没能达成本次目标，你的平均下手速度为"+n+"步/s"+"\n是否开启新游戏?","游戏结束",
                            JOptionPane.YES_NO_OPTION);

            if (jop == JOptionPane.YES_OPTION) {
                game.newGame();
            }
        }
            break;

        case Game2048.GAME_WIN: {
        	float time=game.getTime()/1000;
        	float n=(float)(Math.round((game.getCount()/time)*100)/100);
            int jop = JOptionPane.showConfirmDialog(null,
                    "你已完成本次目标:" + game.getTitle() + "\n你的平均下手速度为" +n+"步/s"+"\n是否进入更高难度游戏?", "恭喜晋级",
                    JOptionPane.YES_NO_OPTION);

            if (jop == JOptionPane.YES_OPTION) {
                game.nextLv();
            }
        }
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
