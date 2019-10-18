package minegame;

import java.util.ArrayList;
import java.util.Random;

public class ButtonManager
{
    private MyButton[][] buttons;
    //定义 myicon数组 0-9 显示数字 10为初始 11为旗子 12为雷
    private ArrayList<Integer> boomArray, flagArray;
    private int num;

    private MyIcon[] icons;
    private int boomNum;

    public int getBoomNum()
    {
        return boomNum;
    }

    public void setBoomNum(int boomNum)
    {
        this.boomNum = boomNum;
    }


    public ButtonManager(int num, int boomNum)
    {

        setBoomNum(boomNum);
        icons = new MyIcon[13];
        for (int i = 0; i < 13; i++) {
            icons[i] = new MyIcon(MineGame.blockWidth, i);
        }
        setNum(num);
        boomArray = new ArrayList<>();
        flagArray = new ArrayList<>();
        Random r = new Random();
        boomArray.add(r.nextInt(num * num));
        for (int i = 0; i <= this.getBoomNum(); i++) {
            int tempInt = r.nextInt(num * num);
            if (!boomArray.contains(tempInt)) {
                //找一个递归方法 输出一个不重复的arraylist
                boomArray.add(tempInt);
                //}
            }
            i = boomArray.size();
        }
        System.out.println("boomArray.size() = " + boomArray.size());
        System.out.println(boomArray);
        boomArray.sort(null);
        System.out.println("boomArray = " + boomArray);
        setButtons(num);
        inits();
        setAllBoomAroundNum();

    }

    public void inits()
    {
        int count = -1;
        for (int i = 0; i < this.buttons.length; i++) {
            for (int j = 0; j < this.buttons[0].length; j++) {
                this.buttons[i][j] = new MyButton();
                // this.buttons[i][j].addListenClick(this.getIcons()[12], this.getIcons());

                this.buttons[i][j].setIcon(this.getIcons()[10]);
                this.buttons[i][j].addMyMouseListener(this.getIcons(), flagArray, boomArray);
                count++;
                this.buttons[i][j].setNumInAll(count);
                //随机出boomnum个炸弹
                for (int i1 = 0; i1 < this.getBoomNum(); i1++) {
                    if (count == this.boomArray.get(i1)) {
                        this.buttons[i][j].setBoom(true);
                    }
                }
            }
        }
    }

    public void setAllBoomAroundNum()
    {
         for (int i = 0; i < this.buttons.length; i++) {
            for (int j = 0; j < this.buttons[0].length; j++) {
                int tmpCount = 0;
                //遍历button 搜索button附近的8个元素 isBoom
                //排除操作最外一圈
                if (i != 0 && i != this.buttons.length - 1 &&
                        j != 0 && j != this.buttons[0].length - 1) {
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (k == i && l == j) {
                                continue;
                            }
                            if (this.buttons[k][l].isBoom()) {
                                tmpCount++;
                            }
                        }
                    }
                } else if (i == 0 && j == 0) {
                    if (this.buttons[1][0].isBoom()) {
                        tmpCount++;
                    }
                    if (this.buttons[1][1].isBoom()) {
                        tmpCount++;
                    }
                    if (this.buttons[0][1].isBoom()) {
                        tmpCount++;
                    }

                } else if (i == this.buttons.length - 1 && j == 0) {
                    if (this.buttons[this.buttons.length - 2][0].isBoom()) {
                        tmpCount++;
                    }
                    if (this.buttons[this.buttons.length - 2][1].isBoom()) {
                        tmpCount++;
                    }
                    if (this.buttons[this.buttons.length - 1][1].isBoom()) {
                        tmpCount++;
                    }
                } else if (i == this.buttons.length - 1 && j == this.buttons[0].length - 1) {
                    if (this.buttons[this.buttons.length - 1][this.buttons[0].length - 2].isBoom()) {
                        tmpCount++;
                    }
                    if (this.buttons[this.buttons.length - 2][this.buttons[0].length - 2].isBoom()) {
                        tmpCount++;
                    }
                    if (this.buttons[this.buttons.length - 1][this.buttons[0].length - 1].isBoom()) {
                        tmpCount++;
                    }
                } else if (i == 0 && j == this.buttons[0].length - 1) {
                    if (this.buttons[0][this.buttons[0].length - 2].isBoom()) {
                        tmpCount++;
                    }
                    if (this.buttons[1][this.buttons[0].length - 2].isBoom()) {
                        tmpCount++;
                    }
                    if (this.buttons[1][this.buttons[0].length - 1].isBoom()) {
                        tmpCount++;
                    }
                } else if (i == 0 && j < this.buttons[0].length - 1 && j > 0) {
                    //只有这个是正确的 当在第0行时 只找i=0 i=1  1<j<5
                    for (int k = 0; k <= 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (k == i && l == j) {
                                continue;
                            }
                            if (this.buttons[k][l].isBoom()) {
                                tmpCount++;
                            }
                        }
                    }
                } else if (i == this.buttons.length - 1 && j < this.buttons[0].length - 1 && j > 0) {
                    //当在第5行时 只找i=5 i=4  1<j<5
                    for (int k = this.buttons.length - 2; k <= this.buttons.length - 1; k++) {
                        for (int l = j - 1; l <= j + 1; l++) {
                            if (k == i && l == j) {
                                continue;
                            }
                            if (this.buttons[k][l].isBoom()) {
                                tmpCount++;
                            }
                        }
                    }
                } else if (i < this.buttons.length - 1 && i > 0 && j == 0) {
                    //当在第0列时 只找j=0,j=1  1<i<5
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = 0; l <= 1; l++) {
                            if (k == i && l == j) {
                                continue;
                            }
                            if (this.buttons[k][l].isBoom()) {
                                tmpCount++;
                            }
                        }
                    }
                } else if (i < this.buttons.length - 1 && i > 0 && j == this.buttons[0].length - 1) {
                    //当在第5列时 只找j=5,j=4  1<i<5
                    for (int k = i - 1; k <= i + 1; k++) {
                        for (int l = j - 1; l <= j; l++) {
                            if (k == i && l == j) {
                                continue;
                            }
                            if (this.buttons[k][l].isBoom()) {
                                tmpCount++;
                            }
                        }
                    }
                }
                this.buttons[i][j].setBoomAroundNum(tmpCount);

            }
        }
    }


    public int getNum()
    {
        return num;
    }

    public MyIcon[] getIcons()
    {
        return icons;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public void setButtons(int num)
    {
        this.buttons = new MyButton[num][num];
    }

    public MyButton[][] getButtons()

    {
        return this.buttons;
    }

}
