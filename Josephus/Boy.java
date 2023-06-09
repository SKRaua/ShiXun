package Josephus;

/**
 * The Boy class of the Josephus.
 * 
 * @author Xinyang Zhou
 * @version 1.0
 */
public class Boy {
    // 小孩编号
    private int code;
    // 下一个小孩
    private Boy pNext;

    /**
     * 构造小孩
     * 
     * @param pPosition 用于连接为上一个小孩
     * @param id        小孩编号
     */
    public Boy(Boy pPosition, int id) {
        code = id;
        if (pPosition == null) {
            this.pNext = this;// 只有一个小孩时，自己指向自己
        } else {
            this.pNext = pPosition.pNext;// 插入到小孩pPosition的后面
            pPosition.pNext = this;
        }
    }

    /**
     * 小孩离开
     * 
     * @param pPosition 离开小孩的上一个
     */
    public void leave(Boy pPosition) {
        pPosition.pNext = this.pNext;// 上一个小孩的下一个接为离开小孩的下一个
        System.out.print(code + "离开，");
    }

    /**
     * 打印小孩ID
     */
    public void print() {
        System.out.print("ID: " + code);
    }

    /**
     * 下一个小孩
     * 
     * @return 下一个小孩
     */
    public Boy next() {
        return pNext;
    }

}
