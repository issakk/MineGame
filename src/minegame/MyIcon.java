package minegame;

import javax.swing.*;
import java.awt.*;

public class MyIcon implements Icon
{
    private int blockWidth;
    private boolean isClicked;
    private int flag;
    //定义 myicon数组 0-9 显示数字 10为初始 11为旗子 12为雷

    public MyIcon(int blockWidth, int flag)
    {
        this.blockWidth = blockWidth;
        this.flag = flag;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        if (this.flag <= 9) {
            g.drawString("" + this.flag, getBlockWidth() / 2, getBlockWidth() / 2);
        } else if (this.flag == 10) {
            g.drawOval(getBlockWidth() / 4, getBlockWidth() / 4,
                    getBlockWidth() / 2, getBlockWidth() / 2);
        } else if (this.flag == 11) {
            g.drawString("X", getBlockWidth() / 2, getBlockWidth() / 2);

        } else {
            g.fillOval(getBlockWidth() / 4, getBlockWidth() / 4,
                    getBlockWidth() / 2, getBlockWidth() / 2);
        }

//        if (!this.isClicked) {
//            // System.out.println("kicked");
//            g.drawOval(getBlockWidth() / 4, getBlockWidth() / 4,
//                    getBlockWidth() / 2, getBlockWidth() / 2);
//        } else {
//            g.fillOval(getBlockWidth() / 4, getBlockWidth() / 4,
//                    getBlockWidth() / 2, getBlockWidth() / 2);
//        }
//        //g.drawString(""+this.flag,getBlockWidth()/2,getBlockWidth()/2);

    }

    public void paintIcon(Graphics g, int flag)
    {

        g.drawString("" + flag, getBlockWidth() / 2, getBlockWidth() / 2);

    }


    public int getBlockWidth()
    {
        return blockWidth;
    }

    @Override
    public int getIconWidth()
    {
        return 0;
    }

    @Override
    public int getIconHeight()
    {
        return 0;
    }
}
