package FlyMe2TheMoon2;

/**
 * main of FlyMe2TheMoon
 * 
 * @author xinyang Zhou
 * @version 2.0
 */
public class mainFighter {
    /**
     * main函数（主模块）
     * 
     * @param args A reference to a string array containing command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("这里是打飞机小游戏~~");
        // 实例打飞机类
        Fighter FlyMe2TheMoon = new Fighter();
        // 画出初始的图像
        FlyMe2TheMoon.drawPicture();
        // 打飞机游戏开始运作
        FlyMe2TheMoon.operateFighter();
    }
}
