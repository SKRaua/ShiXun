package Josephus;

/**
 * The Ring class of the Josephus.完成围小孩，数小孩，得到获胜小孩。
 * 
 * @author XinYang Zhou
 * @version 1.0
 */
public class Ring {

    // 第一个小孩
    private Boy pFirst;

    // 现在的小孩
    private Boy pCurrent;
    // 上一个小孩
    private Boy pLast;

    /**
     * 小孩围城圈
     * 
     * @param numOfBoys 小孩总数
     */
    public Ring(int numOfBoys) {
        pFirst = new Boy(null, 1);// 第一个小孩

        // 依次围上其他小孩
        Boy pNewBoy = pFirst;
        for (int i = 2; i <= numOfBoys; i++) {
            pNewBoy = new Boy(pNewBoy, i);
        }
        // 将pCurrent移至最后一个小孩使得第一次数数pCurrent移到第一个
        pCurrent = pNewBoy;
    }

    /**
     * 得到获胜小孩
     * 
     * @param m 间隔数
     * @return 获胜小孩
     */
    public Boy getWinner(int m) {
        // 数小孩,直到只剩1个小孩
        while (pCurrent != pCurrent.next()) {
            // 每次数小孩的间隔为m。
            countUpTo(m);
        }
        System.out.println();
        // 返回获胜者
        return pCurrent;
    }

    /**
     * 往下数m个小孩，数到的小孩离开。
     * 
     * @param m 间隔数
     */
    private void countUpTo(int m) {
        // 往下数m个小孩
        for (int i = 0; i < m; i++) {
            pLast = pCurrent;
            pCurrent = pCurrent.next();
        }

        // 数到的小孩离开，同时pCurrent回到上一个小孩
        pCurrent.leave(pLast);
        pCurrent = pLast;
    }
}
