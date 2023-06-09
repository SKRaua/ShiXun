package FlyMe2TheMoon4;

/**
 * The Boss class of the FlyMe2TheMoon.
 * 
 * @author Xinyang Zhou
 * @version 4.0
 */
public class Boss extends EnemyFighter {
    private int HP;

    /**
     * 创造boss（刷新敌机位置）
     */
    public Boss() {
        super();
        HP = 10;
    }

    /**
     * 扣血
     */
    public void lossHP() {
        HP--;
    }

    /**
     * @return BossHP
     */
    public int getHP() {
        return HP;
    }
}
