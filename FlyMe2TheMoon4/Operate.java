package FlyMe2TheMoon4;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The Operate class of the FlyMe2TheMoon.（控制程序）
 * 
 * @author Xinyang Zhou
 * @version 4.0
 */
public class Operate implements KeyListener {
    private Fighter fighter;// 飞机
    private ArrayList<Bullet> bullets;// 子弹
    private ArrayList<EnemyFighter> enemyFighters;// 敌机
    private ArrayList<Boss> bosses;// Boss

    public Operate() {
        fighter = new Fighter();// 实例飞机类
        enemyFighters = new ArrayList<EnemyFighter>();// 实例敌机类
        bullets = new ArrayList<Bullet>();// 实例子弹类
        bosses = new ArrayList<Boss>();
        fighter.drawer(fighter, bullets, enemyFighters, bosses); // 打印初始图像
    }

    /**
     * 控制飞机移动
     */
    public void operateFighter() {
        // 创建一个 飞机操纵台
        JFrame frame = new JFrame("飞机操纵台");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 100);// 大小
        frame.setVisible(true);// 可见
        frame.setResizable(false);// 不可改变大小
        JLabel tips = new JLabel("WASD移动，空格发射超级子弹", null, 0);// 提示
        frame.add(tips);

        frame.setFocusable(true);
        frame.requestFocusInWindow();// 将焦点设置到 frame 上
        frame.addKeyListener(this);// 添加 KeyListener 到 frame,keyPressed实现飞机运动
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 当按键被按下时触发, 处理按键事件,操纵飞机
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case 87: {// W 上
                if (fighter.getX() > 2) {
                    fighter.setX(fighter.getX() - 1);
                }
                break;
            }
            case 65: {// A 左
                if (fighter.getY() > 3) {
                    fighter.setY(fighter.getY() - 1);
                }
                break;
            }
            case 83: {// S 下
                if (fighter.getX() < fighter.heightGetter() - 1) {
                    fighter.setX(fighter.getX() + 1);
                }
                break;
            }
            case 68: {// D 右
                if (fighter.getY() < fighter.widthGetter() - 2) {
                    fighter.setY(fighter.getY() + 1);
                }
                break;
            }
            case 32: {// " " 空格发射super子弹
                if (fighter.getsuperBullet()) {
                    for (int x = 0; x < 4; x++) {
                        for (int y = 0; y < 3; y++) {
                            bullets.add(new Bullet(fighter.getX() - x, fighter.getY() + y - 1));
                        }
                    }
                    fighter.setsuperBullet(false);// super子弹进入冷却
                    ifEnemySurvive();// 敌机是否存活
                    ifBossSurvive();// Boss是否存活
                }
                break;
            }
            case 82: {// R 发射清屏子弹
                for (int x = fighter.getX(); x > fighter.getX() - 4; x--) {
                    for (int y = 1; y < fighter.widthGetter() + 1; y++) {
                        bullets.add(new Bullet(x, y));
                    }
                }
                ifEnemySurvive();// 敌机是否存活
                ifBossSurvive();// Boss是否存活
                break;
            }
            case 27: {// ESC 退出程序
                System.out.println("游戏结束！");
                System.exit(0);
                break;
            }
        }
        ifSurvive(enemyFighters);// 飞机是否存活
        ifSurvive(bosses);
        fighter.drawer(fighter, bullets, enemyFighters, bosses); // 更新图像
    }

    public void pcOperate() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {// 每200时间更新一次子弹移动并自动发射子弹，添加敌机
            @Override
            public void run() {
                bullets.add(new Bullet(fighter.getX(), fighter.getY())); // 自动发射子弹
                enemyFighters.add(new EnemyFighter());// 自动添加敌机
                for (int i = 0; i < bullets.size(); i++) {
                    bullets.get(i).floatBullet(); // 子弹移动
                    if (bullets.get(i).getX() == -1) {
                        bullets.remove(bullets.get(i));// 移除出界子弹
                        if (i != 0)// 回正数组索引
                            i--;
                    }
                }
                ifEnemySurvive();// 敌机是否存活
                ifBossSurvive();// Boss是否存活
            }
        }, 0, 200);

        timer.scheduleAtFixedRate(new TimerTask() {// 每10000时间添加Boss
            @Override
            public void run() {
                bosses.add(new Boss());

            }
        }, 0, 10000);

        timer.scheduleAtFixedRate(new TimerTask() {// 每1000时间更新一次敌机运动
            @Override
            public void run() {
                for (int i = 0; i < enemyFighters.size(); i++) {
                    enemyFighters.get(i).enemyFighterMove(); // 敌机移动
                    if (enemyFighters.get(i).getX() > fighter.heightGetter()) {
                        enemyFighters.remove(enemyFighters.get(i));// 移除出界飞机
                        if (i != 0)// 回正数组索引
                            i--;
                    }
                }
                for (int i = 0; i < bosses.size(); i++) {
                    bosses.get(i).enemyFighterMove(); // 敌机移动
                    if (bosses.get(i).getX() > fighter.heightGetter()) {
                        bosses.remove(bosses.get(i));// 移除出界飞机
                        if (i != 0)// 回正数组索引
                            i--;
                    }
                }
                ifEnemySurvive();// 敌机是否存活
                ifSurvive(enemyFighters);// 飞机是否存活
                ifSurvive(bosses);
            }
        }, 0, 1000);

        timer.scheduleAtFixedRate(new TimerTask() {// 每4000时间刷新super子弹
            @Override
            public void run() {
                fighter.setsuperBullet(true);
            }
        }, 0, 4000);

        timer.scheduleAtFixedRate(new TimerTask() {// 更新图像
            @Override
            public void run() {
                fighter.drawer(fighter, bullets, enemyFighters, bosses);
            }
        }, 0, 2000);
    }

    /**
     * 判断飞机是否存活
     */
    public void ifSurvive(ArrayList<? extends EnemyFighter> enemyFighters) {
        for (int i = 0; i < enemyFighters.size(); i++) {
            if ((enemyFighters.get(i).getX() == fighter.getX() - 1
                    && enemyFighters.get(i).getY() == fighter.getY())
                    || (enemyFighters.get(i).getX() == fighter.getX()
                            && enemyFighters.get(i).getY() > fighter.getY() - 3
                            && enemyFighters.get(i).getY() < fighter.getY() + 3)) { // 飞机撞上敌机
                enemyFighters.remove(enemyFighters.get(i)); // 删除敌机
                fighter.createFighter(); // 刷新飞机位置
                fighter.setHP(fighter.getHP() - 1);// 减少生命值
                if (fighter.getHP() == 0) {
                    System.out.println("***飞机被摧毁，游戏结束！***");
                    System.exit(0);
                }
                break;
            }
        }
    }

    /**
     * 判断敌机是否存活
     */
    public void ifEnemySurvive() {
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemyFighters.size(); j++) {
                if (bullets.get(i).getX() == enemyFighters.get(j).getX()
                        && bullets.get(i).getY() == enemyFighters.get(j).getY()) {// 击中敌机
                    scoreUP();// 加分
                    bullets.remove(i);// 删除子弹
                    enemyFighters.remove(enemyFighters.get(j)); // 删除敌机
                    // 回正数组索引
                    if (i != 0)
                        i--;
                    if (j != 0)
                        j--;
                }
            }
        }
    }

    /**
     * 判断Boss是否存活
     */
    public void ifBossSurvive() {
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < bosses.size(); j++) {
                if (bullets.get(i).getX() == bosses.get(j).getX()
                        && (bullets.get(i).getY() > bosses.get(j).getY() - 1
                                || bullets.get(i).getY() < bosses.get(j).getY() + 1)) {// 击中Boss
                    scoreUP();// 加分
                    bosses.get(j).setHP(bosses.get(j).getHP() - 1);// Boss扣血
                    if (bosses.get(j).getHP() == 0) {
                        bosses.remove(bosses.get(j)); // 删除Boss
                        if (j != 0) // 回正数组索引
                            j--;
                    }
                    bullets.remove(i);// 删除子弹
                    if (i != 0) // 回正数组索引
                        i--;
                }
            }
        }
    }

    public void scoreUP() {
        fighter.setScore(fighter.getScore() + 1);// 加分
        if (fighter.getScore() > 1995)
            fighter.win();// 胜利
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
