package FlyMe2TheMoon2;

import java.util.Random;

/**
 * The enemyFighter class of the FlyMe2TheMoon.
 * 
 * @author Xinyang Zhou
 * @version 2.0
 */
public class enemyFighter extends Picture {
    private int enemy_x, enemy_y;// 敌机位置

    /**
     * 敌机构造器
     */
    public enemyFighter() {
        createEnemyFighter();
    }

    /**
     * @return 敌机x坐标
     */
    public int enemy_xGetter() {
        return enemy_x;
    }

    /**
     * @return 敌机y坐标
     */
    public int enemy_yGetter() {
        return enemy_y;
    }

    public void enemyFighterMove() {
        if (enemy_x > super.heightGetter()) {// 过界重置敌机
            createEnemyFighter();
        }
        enemy_x++; // 敌机移动
    }

    /**
     * 创造敌机（刷新敌机位置）
     */
    public void createEnemyFighter() {
        Random random = new Random();
        // 产生新敌机
        enemy_x = 0;
        enemy_y = (random.nextInt(super.widthGetter() - 4) + 3);// 宽度限制敌机出现位置
    }
}
