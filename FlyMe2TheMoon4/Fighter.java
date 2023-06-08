package FlyMe2TheMoon4;

/**
 * The Fighter class of the FlyMe2TheMoon.
 * 
 * @author Xinyang Zhou
 * @version 4.0
 */
public class Fighter extends Picture {

    private int HP;// 飞机生命值
    private int score;// 分数
    private boolean superBullet;// 超级子弹是否装填

    /**
     * 构造Fighter
     */
    public Fighter() {
        createFighter();
        HP = 5;// 初始生命值为5
        score = 0;// 初始分数为0
    }

    /**
     * 设置飞机HP
     * 
     * @param HP 飞机HP
     */
    public void setHP(int HP) {
        this.HP = HP;
    }

    /**
     * 设置分数
     * 
     * @param score 分数
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * 设置超级子弹
     * 
     * @param superBullet 导弹是否装填
     */
    public void setsuperBulletSetter(boolean superBullet) {
        this.superBullet = superBullet;
    }

    /**
     * @return 飞机HP
     */
    public int getHP() {
        return HP;
    }

    /**
     * @return 得分
     */
    public int getScore() {
        return score;
    }

    /**
     * @return 是否装填super子弹
     */
    public boolean getsuperBullet() {
        return superBullet;
    }

    /**
     * 创造飞机（刷新飞机位置）
     */
    public void createFighter() {
        // 飞机位置
        super.setX(super.heightGetter() - 5);
        super.setY(super.widthGetter() / 2);
        superBullet = true;// 装填超级子弹
    }
}
