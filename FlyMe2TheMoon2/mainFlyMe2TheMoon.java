package FlyMe2TheMoon2;

/**
 * main of FlyMe2TheMoon
 * 
 * @author xinyang Zhou
 * @version 2.0
 */
public class mainFlyMe2TheMoon {
    /**
     * main函数（主模块）
     * 
     * @param args A reference to a string array containing command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("这里是打飞机小游戏~~");
        // 实例操作类
        Operate FlyMe2TheMoon = new Operate();

        // 打飞机游戏开始运作
        FlyMe2TheMoon.operateFighter();

    }
}
