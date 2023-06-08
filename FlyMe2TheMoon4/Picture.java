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
     * @param fighter       飞机
     * @param bullets       子弹
     * @param enemyFighters 敌机
     * @param score         分数
     */
    public void drawer(Fighter fighter, ArrayList<Bullet> bullets, ArrayList<EnemyFighter> enemyFighters) {
        String SuperBullet;
        if (fighter.superBulletGetter()) {
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
                if (!printed && x == fighter.fighter_xGetter()
                        && (y >= fighter.fighter_yGetter() - 2 && y <= fighter.fighter_yGetter() + 2)) {
                    System.out.print("#");
                    printed = true;
                } else if (!printed && x == fighter.fighter_xGetter() - 1 && y == fighter.fighter_yGetter()) {
                    System.out.print("A");
                    printed = true;
                } else if (!printed && x == fighter.fighter_xGetter() + 1
                        && (y == fighter.fighter_yGetter() - 1 || y == fighter.fighter_yGetter() + 1)) {
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
        System.out.println(
                "|                              |分数：" + fighter.scoreGetter() + "  |  HP: " + fighter.HPGetter()
                        + "  |  Super Bullet: " + SuperBullet);
    }
}
