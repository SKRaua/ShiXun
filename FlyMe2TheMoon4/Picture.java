package FlyMe2TheMoon4;

import java.util.ArrayList;

/**
 * The Picture class of the FlyMe2TheMoon.
 * 
 * @author Xinyang Zhou
 * @version 4.0
 */
public class Picture {

    private int height, width;// 游戏界面尺寸
    private int x, y;// 界面坐标

    /**
     * 构造游戏图像框架数据
     */
    public Picture() {
        // 游戏界面尺寸
        height = 24;
        width = 30;

    }

    /**
     * @return 游戏高
     */
    public int heightGetter() {
        return height;
    }

    /**
     * @return 游戏宽
     */
    public int widthGetter() {
        return width;
    }

    /**
     * 设置x坐标
     * 
     * @param x x坐标
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * 设置y坐标
     * 
     * @param y y坐标
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return x坐标
     */
    public int getX() {
        return x;
    }

    /**
     * @return y坐标
     */
    public int getY() {
        return y;
    }

    /**
     * 画图像
     * 
     * @param fighter       飞机
     * @param bullets       子弹
     * @param enemyFighters 敌机
     * @param score         分数
     */
    public void drawer(Fighter fighter, ArrayList<Bullet> bullets,
            ArrayList<EnemyFighter> enemyFighters, ArrayList<Boss> bosses) {
        String SuperBullet;
        if (fighter.getsuperBullet()) {
            SuperBullet = "Ready";// 超级子弹已装填
        } else {
            SuperBullet = "Not Ready";
        }
        for (int x = 0; x < height + 2; x++) {
            for (int y = 0; y < width + 2; y++) {
                boolean printed = false;

                // 输出边框
                if (y == 0 || y == width + 1) {
                    System.out.print("|");
                    printed = true;
                }

                // 画出飞机
                if (!printed && x == fighter.getX()
                        && (y >= fighter.getY() - 2 && y <= fighter.getY() + 2)) {
                    System.out.print("#");
                    printed = true;
                } else if (!printed && x == fighter.getX() - 1 && y == fighter.getY()) {
                    System.out.print("A");
                    printed = true;
                } else if (!printed && x == fighter.getX() + 1
                        && (y == fighter.getY() - 1 || y == fighter.getY() + 1)) {
                    System.out.print("I");
                    printed = true;
                }

                // 画出Boss
                for (Boss boss : bosses) {
                    if (!printed && x == boss.getX() && y == boss.getY()) {
                        System.out.print("V");
                        printed = true;
                        break;
                    } else if (!printed && x == boss.getX() - 1 && y > boss.getY() - 2 && y < boss.getY() + 2) {
                        System.out.print("=");
                        printed = true;
                        break;
                    }
                }

                // 画出子弹
                for (Bullet bullet : bullets) {
                    if (!printed && x == bullet.getX() && y == bullet.getY()) {
                        System.out.print("*");
                        printed = true;
                        break;
                    }
                }

                // 画出敌机
                for (EnemyFighter enemyFighter : enemyFighters) {
                    if (!printed && x == enemyFighter.getX() && y == enemyFighter.getY()) {
                        System.out.print("V");
                        printed = true;
                        break;
                    }
                }

                // 输出空格
                if (!printed) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
        System.out.println(
                "|                              |分数：" + fighter.getScore() + " | HP: " + fighter.getHP()
                        + " | Super Bullet: " + SuperBullet);
    }

    public void win() {
        System.out.println("        *******        ");
        System.out.println("     *************     ");
        System.out.println("  *******************  ");
        System.out.println(" ********************* ");
        System.out.println("***********************");
        System.out.println("Fly Me 2 The Moon ~~~~~");
        System.out.println("***********************");
        System.out.println(" ********************* ");
        System.out.println("  *******************  ");
        System.out.println("     *************     ");
        System.out.println("        *******        ");
        System.exit(0);

    }
}
