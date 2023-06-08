package FlyMe2TheMoon4;

/**
 * main of FlyMe2TheMoon
 * 
 * @author xinyang Zhou
 * @version 4.0
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

        // 飞机操纵台启动
        FlyMe2TheMoon.operateFighter();
        // 敌机子弹开始运动
        FlyMe2TheMoon.pcOperate();

    }
}
