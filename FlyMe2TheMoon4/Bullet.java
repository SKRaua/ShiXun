package FlyMe2TheMoon4;

/**
 * The Bullet class of the FlyMe2TheMoon.
 * 
 * @author Xinyang Zhou
 * @version 4.0
 */
public class Bullet extends Picture {

    /**
     * 子弹构造器
     */
    public Bullet(int fighter_x, int fighter_y) {
        // 创造一个子弹
        setX(fighter_x - 2);
        setY(fighter_y);
    }

    /**
     * 子弹移动
     */
    public void floatBullet() {
        setX(getX() - 1);// 子弹移动
    }
}
