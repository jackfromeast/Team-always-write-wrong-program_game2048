import java.awt.Font;

import javax.swing.JButton;

class Cube {
    private int num;
    private JButton btn;
    private boolean isAdded;

    /**
     * 构造一个方块
     */
    public Cube() {
        btn = new JButton();
        btn.setFont(new Font("微软雅黑", Font.BOLD, 24));
        btn.setEnabled(false);
        num = 0;
        isAdded = false;
    }

    /**
     * 设置文本内容
     * 
     * @param n
     *            数值
     */
    public void setText(int n) {
        num = n;
        btn.setText(n != 0 ? n + "" : "");
    }

    /**
     * 获取控件
     * 
     * @return JButton
     */
    public JButton getView() {
        return btn;
    }

    /**
     * 获取数值
     * 
     * @return int
     */
    public int getNum() {
        return num;
    }

    /**
     * 是否是相加而成 限当前移动次有效,移动结束后改回默认值-false
     * 
     * @return
     */
    public boolean isAdded() {
        return isAdded;
    }

    /**
     * 修改生成方式
     * 
     * @param b
     *            true-相加而成
     */
    public void setIsAdded(boolean b) {
        isAdded = b;
    }
}