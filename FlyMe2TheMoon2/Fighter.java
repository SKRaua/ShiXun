package FlyMe2TheMoon2;

import java.util.Random;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The Fighter class of the FlyMe2TheMoon.
 * 
 * @author Xinyang Zhou
 * @version 2.0
 */
public class Fighter implements KeyListener {

    private int height, width;// 游戏界面尺寸
    private int fighter_x, fighter_y;// 飞机位置
    private int bullet_x, bullet_y;// 子弹位置
    private int enemy_x, enemy_y;// 敌机位置
    private int score;// 分数
    private int HP;// 生命值

    /**
     * 构造打飞机游戏类
     */
    public Fighter() {
        Random random = new Random();
        score = 0;// 0分
        HP = 5;// 5点生命值

        // 游戏界面尺寸
        height = 24;
        width = 30;

        // 飞机位置
        fighter_x = height - 5;
        fighter_y = width / 2;

        // 敌机位置
        enemy_x = 0;
        enemy_y = (random.nextInt(width - 4) + 3);
    }

    /**
     * 画游戏图像
     */
    public void drawPicture() {
        for (int x = 0; x < height + 2; x++) {
            for (int y = 0; y < width + 2; y++) {
                if (y == 0 || y == width + 1) {// 输出边框
                    System.out.print("|");
                } else if (x == fighter_x && (y >= fighter_y - 2 && y <= fighter_y + 2)) {// 画出飞机
                    System.out.print("#");
                } else if (x == fighter_x - 1 && y == fighter_y) {// 画出飞机
                    System.out.print("A");
                } else if (x == fighter_x + 1 && (y == fighter_y - 1 || y == fighter_y + 1)) {// 画出飞机
                    System.out.print("I");
                } else if (x == bullet_x && y == bullet_y) {// 画出子弹
                    System.out.print("*");
                } else if (x == enemy_x && y == enemy_y) {// 画出敌机
                    System.out.print("V");
                } else {
                    System.out.print(" ");// 输出空格
                }
            }
            System.out.println();
        }
        System.out.println("|                              |分数：" + score + " HP: " + HP);
    }

    /**
     * 控制飞机移动
     */
    public void operateFighter() {
        // 创建一个 飞机操纵台
        JFrame frame = new JFrame("飞机操纵台");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 100);// 大小
        frame.setVisible(true);// 可见
        frame.setResizable(false);// 不可改变大小
        frame.setFocusable(true);
        frame.requestFocusInWindow();// 将焦点设置到 frame 上

        frame.addKeyListener(this);// 添加 KeyListener 到 frame,keyPressed实现飞机运动

        pcOperate();// 子弹与飞机移动
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 当按键被按下时触发
        // 处理按键事件
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case 87: {// W
                if (fighter_x > 2) {
                    fighter_x--;
                }
                break;
            }
            case 65: {// A
                if (fighter_y > 3) {
                    fighter_y--;
                }
                break;
            }
            case 83: {// S
                if (fighter_x < height - 1) {
                    fighter_x++;
                }
                break;
            }
            case 68: {// D
                if (fighter_y < width - 2) {
                    fighter_y++;
                }
                break;
            }
            case 32: {// " "
                bullet_x = fighter_x - 2;
                bullet_y = fighter_y;
                break;
            }
            case 27: {
                System.out.println("游戏结束！");
                System.exit(0); // 按下 ESC 键退出程序
                break;
            }
        }
        ifSurvive();// 飞机是否存活
        drawPicture();
    }

    /**
     * 子弹与飞机移动
     */
    public void pcOperate() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                floatBullet();// 子弹移动
                ifEnemySurvive();// 敌机是否存活
                enemyFighter();// 敌机移动
                ifSurvive();// 飞机是否存活
                ifEnemySurvive();// 敌机是否存活
            }
        }, 0, 500);

        timer.scheduleAtFixedRate(new TimerTask() {// 更新图像
            @Override
            public void run() {
                drawPicture();
            }
        }, 0, 2000);
    }

    /**
     * 子弹移动
     */
    public void floatBullet() {
        Random random = new Random();

        bullet_x--;

        if (bullet_x == enemy_x && bullet_y == enemy_y) {
            score++;// 加分
            enemy_x = 0;// 产生新敌机
            enemy_y = (random.nextInt(width - 4) + 3);
            bullet_x = -2;// 取消子弹
        }

    }

    /**
     * 敌机移动
     */
    public void enemyFighter() {
        Random random = new Random();
        if (enemy_x > height) {
            // 产生新敌机
            enemy_x = 0;
            enemy_y = (random.nextInt(width - 4) + 3);
        }
        enemy_x++;// 敌机下移

    }

    /**
     * 判断飞机是否存活
     */
    public void ifSurvive() {
        Random random = new Random();
        // 飞机被击中
        if ((enemy_x == fighter_x - 1 && enemy_y == fighter_y)
                || (enemy_x == fighter_x && enemy_y > fighter_y - 3 && enemy_y < fighter_y + 3)) {
            // 产生新敌机
            enemy_x = 0;
            enemy_y = (random.nextInt(width - 4) + 3);
            // 刷新飞机位置
            fighter_x = height - 5;
            fighter_y = width / 2;
            HP--;// 减少生命值
            if (HP == 0) {
                System.out.println("***飞机被摧毁，游戏结束！***");
                System.exit(0);
            }
        }
    }

    /**
     * 判断敌机是否存活
     */
    public void ifEnemySurvive() {
        Random random = new Random();
        if (bullet_x == enemy_x && bullet_y == enemy_y) {// 击中敌机
            score++;// 加分
            bullet_x = -2;// 取消子弹
            // 重置敌机
            enemy_x = 0;
            enemy_y = (random.nextInt(width - 4) + 3);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
