package FlyMe2TheMoon4;

import java.util.Random;

/**
 * The EnemyFighter class of the FlyMe2TheMoon.
 * 
 * @author Xinyang Zhou
 * @version 4.0
 */
public class EnemyFighter extends Picture {

    /**
     * 敌机构造器
     */
    public EnemyFighter() {
        createEnemyFighter();
    }

    public void enemyFighterMove() {
        setX(getX() + 1); // 敌机移动
    }

    /**
     * 创造敌机（刷新敌机位置）
     */
    public void createEnemyFighter() {
        Random random = new Random();
        // 产生新敌机
        setX(0);
        setY((random.nextInt(super.widthGetter() - 4) + 3));// 宽度限制敌机出现位置
    }
}
