package Josephus;

/**
 * The Jose class of the Josephus.
 * 
 * @author Xinyang Zhou
 * @version 1.0
 */
public class Jose {
    // 小孩总数
    private int numOfBoys;

    // 小孩间隔
    private int m;

    // 胜利的小孩
    private Boy win;

    // 小孩圆圈
    private Ring boys;

    /**
     * josephus问题的数据
     * 
     * @param numOfBoys 小孩总数
     * @param interval  间隔数
     */
    public Jose(int numOfBoys, int interval) {
        this.numOfBoys = numOfBoys;
        this.m = interval;
        this.boys = new Ring(this.numOfBoys);// 小孩围一圈
    }

    /**
     * 游戏开始
     * 
     * @return 获胜小孩
     */
    public Boy gameBegin() {
        win = boys.getWinner(m);
        return win;
    }
}
