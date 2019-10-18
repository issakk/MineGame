package minegame;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/*
10.12日 遗留插旗模块


 */
public class MineGame
{
    static int blockWidth = 40;
    static int boomNum = 5;
    //test git

    public static void main(String[] args)
    {
        JFrame frame = new JFrame();

        frame.setTitle("MINE GAME");
        int t = new Scanner(System.in).nextInt();
        frame.setBounds(600, 300, t * blockWidth,
                t * blockWidth);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setUndecorated(true);//去掉标题
        JPanel panel = new JPanel();
        //中间件
        panel.setLayout(new GridLayout(t, t));
        ButtonManager buttonManager = new ButtonManager(t, boomNum);
        MyButton[][] jButtons = buttonManager.getButtons();
        for (MyButton[] j : jButtons) {
            for (MyButton j1 : j) {
                panel.add(j1);
            }
        }
        frame.add(panel);
        frame.setVisible(true);
    }
}
