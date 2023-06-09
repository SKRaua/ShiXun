package FlyMe2TheMoon3;

/**
 * The Fighter class of the FlyMe2TheMoon.
 * 
 * @author Xinyang Zhou
 * @version 2.0
 */
public class Fighter extends Picture {

    private int fighter_x, fighter_y;// 飞机位置
    private int HP;// 飞机生命值
    private int score;// 分数

    /**
     * 构造Fighter
     */
    public Fighter() {
        createFighter();
        HP = 5;// 初始生命值为5
        score = 0;// 初始分数为0
    }

    /**
     * 设置飞机x坐标
     * 
     * @param fighter_x 飞机x坐标
     */
    public void fighter_xSetter(int fighter_x) {
        this.fighter_x = fighter_x;
    }

    /**
     * 设置飞机y坐标
     * 
     * @param fighter_y 飞机y坐标
     */
    public void fighter_ySetter(int fighter_y) {
        this.fighter_y = fighter_y;
    }

    /**
     * 设置飞机HP
     * 
     * @param HP 飞机HP
     */
    public void HPSetter(int HP) {
        this.HP = HP;
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
     * @return 飞机HP
     */
    public int HPGetter() {
        return HP;
    }

    /**
     * @return 得分
     */
    public int scoreGetter() {
        return score;
    }

    /**
     * @return 飞机的x坐标
     */
    public int fighter_xGetter() {
        return fighter_x;
    }

    /**
     * @return 飞机的y坐标
     */
    public int fighter_yGetter() {
        return fighter_y;
    }

    /**
     * 创造飞机（刷新飞机位置）
     */
    public void createFighter() {
        // 飞机位置
        fighter_x = super.heightGetter() - 5;
        fighter_y = super.widthGetter() / 2;
    }
}
