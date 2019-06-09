import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Test2{
	Game2048 game;
	public Test2() {}
	JFrame frame; //主界面框架
	//主界面函数
	public void mainFace() {
		frame=new JFrame("主界面");
		frame.setLayout(new BorderLayout());
		JButton b1=new JButton("新游戏");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game=new Game2048(400,400);
				game.setTitle("2048");
				game.setLocationRelativeTo(null);
		        game.setVisible(true);
		        game.newGame();
				frame.dispose(); //主界面关闭
			}
		});
		
		JButton b2=new JButton("关于");
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				otherFace(); //进入另一个界面
				frame.dispose(); //主界面关闭
			}
		});
		
		JButton b3=new JButton("退出");
		
		b3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose(); //主界面关闭
			}
		});
		frame.setLayout(new GridLayout(3,1));
		frame.add(b1);
		frame.add(b2);
		frame.add(b3);
		frame.setSize(400,400);
		frame.setLocation(100,50);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void otherFace() {
		final JFrame oframe=new JFrame("about");
		oframe.setLayout(new BorderLayout());
		
		JTextArea displayArea=new JTextArea();
		oframe.add(displayArea,BorderLayout.CENTER);
		displayArea.setFont(new Font("Serif",Font.BOLD,20));
		displayArea.setForeground(Color.black);
		displayArea.append("\n  您可以通过按键盘上↑→↓←进行游戏\n"
				+ "  当屏幕内无可移动方向时为失败游戏结束\n"
				+"  当出现2048即成功，可晋级\n");
		
		JTextArea notes=new JTextArea();
		oframe.add(notes,BorderLayout.NORTH);
		notes.setFont(new Font("微软雅黑",Font.BOLD,10));
		notes.setForeground(Color.black);
		notes.append("\t\t抵制不良游戏，拒绝盗版游戏\n"
				+ "\t\t适度游戏益脑，过度游戏伤身");
		
		JButton button1=new JButton("返回主界面");
		oframe.add(button1,BorderLayout.SOUTH);
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true); //主界面显示
				oframe.dispose(); //另一个界面关闭
			}
		});
		
		oframe.setSize(400,400);
		oframe.setLocation(150,100);
		oframe.setVisible(true);
	}
}
