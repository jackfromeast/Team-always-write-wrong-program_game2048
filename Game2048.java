import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Game2048 extends JFrame {
    // 记录各个移动方向移动的格数，初始化为十六进制
    final public static int MOVE_UP = 0xf37;
    final public static int MOVE_DOWN = 0xf36;
    final public static int MOVE_LEFT = 0xf35;
    final public static int MOVE_RIGHT = 0xf34;
    // 游戏状态
    final public static int GAME_OVER = 0xf33;
    final public static int GAME_CONTINUE = 0xf32;
    final public static int GAME_WIN = 0xf31;
    // 按钮事件
    final public static int BUTTON_NEW_GAME = 0xf30;
    final public static int BUTTON_ABOUT = 0xf28;
    final public static int BUTTON_EXIT = 0xf27;

    //行数
    private int column;
    //列数
    private int row;
    //游戏状态
    private int gameState;
    //网格集
    private HashMap<Point, Cube>  viewList = new HashMap<>();
    //记分板
    private JMenuItem scoreBoard;
    //计步器
    private JMenuItem arithmometer;
    //步数
    private int count;
    //游戏难度
    private int gameLv;


    public static void main(String[] args) {
        Game2048 game = new Game2048(400, 400);
        game.setTitle("2048");
        game.setLocationRelativeTo(null);
        game.setVisible(true);
        game.newGame();
    }

    /**
     * 构造默认大小的界面
     */
    public Game2048() {
        this(400, 400);
    }

    /**
     * 构造一个指定宽高的界面
     * 
     * @param width
     *            宽
     * @param height
     *            高
     */
    public Game2048(int width, int height) {
        column = width / 100;
        row = height / 100;

        this.setLayout(new GridLayout(row, column));
        // 事件监听

        enableEvents(AWTEvent.WINDOW_EVENT_MASK);
        this.setSize(width, height);

        // 利用button 绘制网格
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                viewList.put(new Point(i, j), new Cube());
                this.add(getView(i, j).getView());
            }
        }

        // 设置按键监听
        this.addKeyListener(new MyKeyListener(this));

        //设定界面具体分布
        JMenuBar jmb = new JMenuBar();
        JMenu jm = new JMenu("游戏");
        JMenuItem item1 = new JMenuItem("新游戏");
        item1.addMouseListener(new MyMouseListener(this,Game2048.BUTTON_NEW_GAME));
        JMenuItem item2 = new JMenuItem("退出");
        item2.addMouseListener(new MyMouseListener(this, Game2048.BUTTON_EXIT));
        jm.add(item1);
        jm.add(item2);

        JMenu jm2 = new JMenu("关于");
        JMenuItem item3 = new JMenuItem("关于");
        item3.addMouseListener(new MyMouseListener(this, Game2048.BUTTON_ABOUT));
        jm2.add(item3);

        scoreBoard = new JMenuItem();
        arithmometer = new JMenuItem();
        //将设置好的按钮加入主界面
        jmb.add(jm);
        jmb.add(jm2);
        jmb.add(scoreBoard);
        jmb.add(arithmometer);
        this.setJMenuBar(jmb);
    }

    /**
     * 向上移动
     */
    public void up() {
        for (int x = 1; x < row; x++) {
            for (int i = 0; i < column; i++) {
                move(Game2048.MOVE_UP, x, i, true);
            }
        }
        //界面显示
        createCube();
        for (int x = 1; x < row; x++) {
            for (int i = 0; i < column; i++) {
                move(Game2048.MOVE_UP, x, i, false);
            }
        }
        addCount();//增加步数
    }

    /**
     * 向下移动
     */
    public void down() {
        for (int x = row - 2; x >= 0; x--) {
            for (int y = 0; y < column; y++) {
                move(Game2048.MOVE_DOWN, x, y, true);
            }
        }
        //界面显示
        createCube();
        for (int x = row - 2; x >= 0; x--) {
            for (int y = 0; y < column; y++) {
                move(Game2048.MOVE_DOWN, x, y, false);
            }
        }
        //增加步数
        addCount();
    }

    /**
     * 向左移动
     */
    public void left() {
        for (int y = 1; y < column; y++) {
            for (int x = 0; x < row; x++) {
                move(Game2048.MOVE_LEFT, x, y, true);
            }
        }
        //界面显示
        createCube();
        for (int y = 1; y < column; y++) {
            for (int x = 0; x < row; x++) {
                move(Game2048.MOVE_LEFT, x, y, false);
            }
        }
        //增加步数
        addCount();
    }

    /**
     * 向右移动
     */
    public void right() {
        for (int y = column - 2; y >= 0; y--) {
            for (int x = 0; x < row; x++) {
                move(Game2048.MOVE_RIGHT, x, y, true);
            }
        }
        //界面显示
        createCube();
        for (int y = column - 2; y >= 0; y--) {
            for (int x = 0; x < row; x++) {
                move(Game2048.MOVE_RIGHT, x, y, false);
            }
        }
        //增加步数
        addCount();
    }

    /**
     * 移动
     * 
     * @param move_way
     *            移动方向
     * @param x
     *            横坐标
     * @param y
     *            纵坐标
     */
    private void move(int move_way, int x, int y, boolean isAdd) {
        switch (move_way) {
        case Game2048.MOVE_DOWN: {
            for (; x < row - 1; x++) {
                swap(getView(x + 1, y), getView(x, y), isAdd);
            }
        }
            break;

        case Game2048.MOVE_LEFT: {
            for (; y > 0; y--) {
                swap(getView(x, y - 1), getView(x, y), isAdd);
            }
        }
            break;

        case Game2048.MOVE_RIGHT: {
            for (; y < column - 1; y++) {
                swap(getView(x, y + 1), getView(x, y), isAdd);
            }
        }
            break;

        case Game2048.MOVE_UP: {
            for (; x > 0; x--) {
                swap(getView(x - 1, y), getView(x, y), isAdd);
            }
        }
            break;
        }
    }

    /**
     * 单向交换实现移动
     * 
     * @param next
     *            移动至目标位置
     * @param now
     *            需要移动的目标
     * @param isAdd
     *            是否是第一次移动
     */
    private void swap(Cube next, Cube now, boolean isAdd) {
        if (isAdd) {
            if (now.getNum() != 0 && next.getNum() == 0) {
                next.setText(now.getNum());
                now.setText(0);
                next.setIsAdded(now.isAdded());
                now.setIsAdded(false);
            } else if (!now.isAdded() && !next.isAdded()
                    && next.getNum() == now.getNum() && now.getNum() != 0) {
                next.setText(now.getNum() * 2);
                now.setText(0);
                next.setIsAdded(true);
                now.setIsAdded(false);
            }
        } else {
            if (next.getNum() == 0) {
                next.setText(now.getNum());
                now.setText(0);
            }
            now.setIsAdded(false);
            next.setIsAdded(false);
        }
    }

    /**
     * 获取指定控件
     * 
     * @param x
     * @param y
     * @return Cube
     */
    private Cube getView(int x, int y) {
        return viewList.get(new Point(x, y));
    }

    /**
     * 生成随机控件 随机位置
     */
    private void createCube() {
        int x;
        int y;

        do {
            x = (int) (Math.random() * 1000 % row);
            y = (int) (Math.random() * 1000 % column);
        } while (getView(x, y).getNum() != 0);

        getView(x, y).setText(Math.random() > 0.5 ? 2 : 4);
        isOverGame();
    }

    /**
     * 检测游戏状态
     */
    private void isOverGame() {
        int score = 0;
        int state = Game2048.GAME_OVER;

        for (int x = 0; x < row; x++) {
            for (int y = 0; y < column; y++) {

                // 计算得分
                score += getView(x, y).getNum();

                if (getView(x, y).getNum() == 0) {
                    state = Game2048.GAME_CONTINUE;
                } else if (getView(x, y).getNum() == 2048 * (gameLv + 1)) {
                    state = Game2048.GAME_WIN;
                }
            }
        }

        if (state != Game2048.GAME_CONTINUE && state != Game2048.GAME_WIN) {
            gameState = Game2048.GAME_OVER;
        } else {
            gameState = state;
        }

        scoreBoard.setText("得分:" + score);

    }

    /**
     * 计步
     */
    private void addCount() {
        count++;
        arithmometer.setText("计步：" + count);
    }

    /**
     * 获取游戏状态
     * 
     * @return int
     */
    public int getGameState() {
        return gameState;
    }

    /**
     * 初始化游戏数据
     */
    private void initialise() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                getView(i, j).setText(0);
            }
        }

        createCube();
        createCube();
        count = 0;
        arithmometer.setText("计步：" + count);
        gameLv = 0;
        this.setTitle("2048");
    }

    /**
     * 开启新游戏
     */
    public void newGame() {
        if (gameState == Game2048.GAME_CONTINUE) {
            int jop = JOptionPane.showConfirmDialog(null, "是否开始新一轮游戏?", "Tips",
                    JOptionPane.YES_NO_OPTION);

            if (jop == JOptionPane.YES_OPTION) {
                initialise();
            }
        } else {
            initialise();
        }
    }

    /**
     * 重载窗口关闭
     */
    protected void processWindowEvent(WindowEvent e) {
        if (e.getID() == WindowEvent.WINDOW_CLOSING) {
            if (getGameState() == Game2048.GAME_CONTINUE) {
                int jop = JOptionPane.showConfirmDialog(null, "是否退出游戏?",
                        "Tips", JOptionPane.YES_NO_OPTION);

                if (jop == JOptionPane.YES_OPTION) {
                    super.processWindowEvent(e);
                }
            } else {
                super.processWindowEvent(e);
            }
        }
    }

    /**
     * 进入下一难度游戏
     */
    public void nextLv() {
        gameLv++;
        this.setTitle(2048 * (gameLv + 1) + "");
    }

    /**
     * 关于
     */
    public void about() {
        JOptionPane.showMessageDialog(null, "川大路二段江安一教A108三号卓");
    }
}




