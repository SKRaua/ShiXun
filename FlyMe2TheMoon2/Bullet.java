package FlyMe2TheMoon2;

/**
 * The Bullet class of the FlyMe2TheMoon.
 * 
 * @author Xinyang Zhou
 * @version 2.0
 */
public class Bullet {
    private int bullet_x, bullet_y;// 子弹位置
    private int score;// 分数

    /**
     * 子弹构造器
     */
    public Bullet() {
        score = 0;// 初始分数为0
    }

    /**
     * 设置分数
     * 
     * @param score 分数
     */
    public void scoreSetter(int score) {
        this.score = score;
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
     * @return 得分
     */
    public int scoreGetter() {
        return score;
    }

    /**
     * 子弹移动
     */
    public void floatBullet() {
        bullet_x--;// 子弹移动
    }
}
