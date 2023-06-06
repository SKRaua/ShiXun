package Josephus;

import java.util.Scanner;

/**
 * Josephus game
 * 
 * @author xinyang Zhou
 * @version 1.0
 */
public class Josephus {
    /**
     * 运行Josephus问题
     * 
     * @param args A reference to a string array containing command-line arguments
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numOfBoys;// 小孩总数
        int interval;// 小孩间隔
        Boy winnerBoy;// 获胜小孩

        System.out.println("*********************");
        System.out.println("这里是Josephus运算器!");

        System.out.print("请输入小孩个数：");
        numOfBoys = input.nextInt();

        System.out.print("请输入间隔：");
        interval = input.nextInt();

        winnerBoy = new Jose(numOfBoys, interval).gameBegin();
        System.out.print("获胜小孩");
        winnerBoy.print();

        input.close();
    }
}
