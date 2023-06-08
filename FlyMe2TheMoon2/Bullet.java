package FlyMe2TheMoon2;

/**
 * The Bullet class of the FlyMe2TheMoon.
 * 
 * @author Xinyang Zhou
 * @version 2.0
 */
public class Bullet extends Fighter {
    private int bullet_x, bullet_y;// 子弹位置

    /**
     * 子弹构造器
     */
    public Bullet() {
        // 创造一个子弹
        bullet_x = super.fighter_xGetter() - 2;
        bullet_y = super.fighter_yGetter();
    }

    /**
     * 设置子弹x坐标
     * 
     * @param bullet_x 子弹x坐标
     */
    public void bullet_xSetter(int bullet_x) {
        this.bullet_x = bullet_x;
    }

    /**
     * 设置子弹y坐标
     * 
     * @param bullet_y 子弹y坐标
     */
    public void bullet_ySetter(int bullet_y) {
        this.bullet_y = bullet_y;
    }

    /**
     * @return 子弹x坐标
     */
    public int bullet_xGetter() {
        return bullet_x;
    }

    /**
     * @return 子弹x坐标
     */
    public int bullet_yGetter() {
        return bullet_y;
    }

    /**
     * 子弹移动
     */
    public void floatBullet() {
        bullet_x--;// 子弹移动
    }
}
