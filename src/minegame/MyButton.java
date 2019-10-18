package minegame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MyButton extends JButton
{
    private boolean boom;

    private int boomAroundNum;

    // 在manager中设置boom之后 然后算出aroundnum
    private int numInAll;
    boolean flag;

    public void addListenClick(MyIcon boomIcon, MyIcon[] icons)
    {
        this.addActionListener(
                e -> {
                    if (e.getSource() instanceof MyButton) {
                        MyButton button = (MyButton) e.getSource();
                        if (button.isBoom()) {
                            button.setIcon(boomIcon); // boomicon
                            // System.out.println("boom~");
                        } else {
                            // System.out.println("你点了空白区域 以后显示附近雷数: " + this.getBoomAroundNum());
                            button.setIcon(icons[this.getBoomAroundNum()]); // drawint
                        }
                        button.removeActionListener(this.getActionListeners()[0]); // gei了再remove
                    }
                });
    }

    public void addMyMouseListener(MyIcon[] icons, ArrayList<Integer> flagArray, ArrayList<Integer> boomArray)
    {
        // 用此方法替换上一个
        this.addMouseListener(
                new MouseListener()
                {
                    @Override
                    public void mouseReleased(MouseEvent e)
                    {
                        MyButton button = MyButton.this;//匿名内部类

                        if (e.getButton() == MouseEvent.BUTTON1) {
                            System.out.println("点击了左键" + MyButton.this.getNumInAll());
                            if (button.isBoom()) {
                                button.setIcon(icons[12]); // boomicon
                                JOptionPane.showMessageDialog(null, "猪猪你输了");
                                JFrame f = (JFrame) button.getTopLevelAncestor();
                                f.dispose();
                            } else {
                                // System.out.println("你点了空白区域 以后显示附近雷数: " + this.getBoomAroundNum());
                                button.setIcon(icons[button.getBoomAroundNum()]);//drawint
                            }
//                            for (int i = 0; i < button.getMouseListeners().length; i++) {
////                                button.removeMouseListener(button.getMouseListeners()[i]); // gei了再remove
////                            }
                            button.removeMouseListener(button.getMouseListeners()[0]); // gei了再remove
                            // }
                        }
                        if (e.getButton() == MouseEvent.BUTTON3) {

                            System.out.println("点击了右键");

                            if (flagArray.contains(button.getNumInAll())) {
                                flagArray.remove(Integer.valueOf(button.getNumInAll()));
                            } else {
                                flagArray.add(button.getNumInAll());
                            }
                            if (button.flag) {
                                button.setIcon(icons[10]);

                                System.out.println("button.flag = " + button.flag);
                            }
                            if (!button.flag) {
                                button.setIcon(icons[11]); // flagicon

                                System.out.println("button.flag = " + button.flag);
                            }
                            button.flag = !button.flag;


                            System.out.println(flagArray);
                            if (flagArray.size() == boomArray.size()) {
                                if (flagArray.containsAll(boomArray)) {
                                    JOptionPane.showMessageDialog(null, "猪猪你把雷都点出来了 赢了");
                                } else {
                                    JOptionPane.showMessageDialog(null, "猪猪你把雷点错了 输了");
                                    JFrame f = (JFrame) button.getTopLevelAncestor();
                                    f.dispose();
                                }
                            }
                            // }
                        }
                    }

                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        MyButton button = MyButton.this;
                        for (int i = 0; i < button.getMouseListeners().length; i++) {
                            button.removeMouseListener(button.getMouseListeners()[i]); // gei了再remove
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e)
                    {
                        // mouseClicked(e);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e)
                    {
                    }

                    @Override
                    public void mouseExited(MouseEvent e)
                    {
                    }
                });
        //        this.addMouseListener(new
        //        {
        //            @Override
        //            public void mouseClicked(MouseEvent e)
        //            {
        //                //super.mouseClicked(e);
        //                //System.out.println("e.toString() = " + e.toString());
        ////                if (e.getButton() == MouseEvent.BUTTON1) {
        ////                    System.out.println("左键点击");
        ////                }
        ////                if (e.getButton() == MouseEvent.BUTTON3) {
        ////                    System.out.println("右键点击");
        ////                }
        //                if (e.getButton() == MouseEvent.BUTTON1) {
        //                    System.out.println("左键点击");
        //                    if (e.getSource() instanceof MyButton) {
        //                        MyButton button = (MyButton) e.getSource();
        //                        if (button.isBoom()) {
        //                            button.setIcon(boomIcon);//boomicon
        //                            //System.out.println("boom~");
        //                            button.removeMouseListener(button.getMouseListeners()[0]);
        //                        } else {
        //                            //System.out.println("你点了空白区域 以后显示附近雷数: " +
        // this.getBoomAroundNum());
        //                            button.setIcon(icons[button.getBoomAroundNum()]);
        //                            button.removeMouseListener(button.getMouseListeners()[0]);
        //                        }
        //                        System.out.println(button.getMouseListeners().toString());
        //                        //button.removeMouseListener(button.getMouseListeners()[0]);
        //                    }
        //                }
        //
        //
        ////                if (e.getSource() instanceof MyButton) {
        ////                    MyButton button = (MyButton) e.getSource();
        ////                    if (button.isBoom()) {
        ////                        button.setIcon(boomIcon);//boomicon
        ////                        //System.out.println("boom~");
        ////                    } else {
        ////                        //System.out.println("你点了空白区域 以后显示附近雷数: " +
        // this.getBoomAroundNum());
        ////                        button.setIcon(icons[button.getBoomAroundNum()]);
        ////                    }
        ////                    //button.removeMouseListener(button.getMouseListeners()[0]);
        ////                }
        //            }
        //        });
    }

    public boolean isBoom()
    {
        return boom;
    }

    public void setBoomAroundNum(int boomAroundNum)
    {
        this.boomAroundNum = boomAroundNum;
    }

    public int getBoomAroundNum()
    {
        return boomAroundNum;
    }

    public int getNumInAll()
    {
        return numInAll;
    }

    public void setNumInAll(int numInAll)
    {
        this.numInAll = numInAll;
    }

    public void setBoom(boolean boom)
    {
        this.boom = boom;
    }
}
