package FlyMe2TheMoon2;

import java.util.ArrayList;

/**
 * The Picture class of the FlyMe2TheMoon.
 * 
 * @author Xinyang Zhou
 * @version 2.0
 */
public class Picture {

    private int height, width;// 游戏界面尺寸

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
     * 画图像
     * 
     * @param fighter_x     飞机x位置
     * @param fighter_y     飞机y位置
     * @param bullets       子弹
     * @param enemyFighters 敌机
     * @param score         分数
     * @param HP            生命值
     */
    public void drawer(int fighter_x, int fighter_y, ArrayList<Bullet> bullets,
            ArrayList<EnemyFighter> enemyFighters, int score, int HP) {
        for (int x = 0; x < height + 2; x++) {
            for (int y = 0; y < width + 2; y++) {
                boolean printed = false;

                // 输出边框
                if (y == 0 || y == width + 1) {
                    System.out.print("|");
                    printed = true;
                }

                // 画出飞机
                if (!printed && x == fighter_x && (y >= fighter_y - 2 && y <= fighter_y + 2)) {
                    System.out.print("#");
                    printed = true;
                } else if (!printed && x == fighter_x - 1 && y == fighter_y) {
                    System.out.print("A");
                    printed = true;
                } else if (!printed && x == fighter_x + 1 && (y == fighter_y - 1 || y == fighter_y + 1)) {
                    System.out.print("I");
                    printed = true;
                }

                // 画出子弹
                for (Bullet bullet : bullets) {
                    if (!printed && x == bullet.bullet_xGetter() && y == bullet.bullet_yGetter()) {
                        System.out.print("*");
                        printed = true;
                        break;
                    }
                }

                // 画出敌机
                for (EnemyFighter enemyFighter : enemyFighters) {
                    if (!printed && x == enemyFighter.enemy_xGetter() && y == enemyFighter.enemy_yGetter()) {
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
        System.out.println("|                              |分数：" + score + " HP: " + HP);
    }
}
