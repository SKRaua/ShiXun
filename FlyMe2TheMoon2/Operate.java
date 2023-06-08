package FlyMe2TheMoon2;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Operate class of the FlyMe2TheMoon.（控制程序）
 * 
 * @author Xinyang Zhou
 * @version 2.0
 */
public class Operate {
    private Fighter fighter;// 飞机
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();// 子弹
    private ArrayList<enemyFighter> enemyFighters = new ArrayList<enemyFighter>();// 敌机

    public Operate() {
        fighter = new Fighter();
        enemyFighters.add(new enemyFighter());
        // 打印初始图像
        fighter.drawer(fighter.fighter_xGetter(), fighter.fighter_yGetter(),
                bullets, enemyFighters, fighter.scoreGetter(), fighter.HPGetter());
    }

    /**
     * 控制飞机移动
     */
    public void operateFighter() {
        Scanner Sc = new Scanner(System.in);
        int speed = 0;// 控制敌机速度与敌机刷新
        do {// 控制飞机移动与
            String input = Sc.nextLine();
            switch (input) {
                case "w": {// 上
                    if (fighter.fighter_xGetter() > 2) {
                        fighter.fighter_xSetter(fighter.fighter_xGetter() - 1);
                    }
                    break;
                }
                case "a": {// 左
                    if (fighter.fighter_yGetter() > 3) {
                        fighter.fighter_ySetter(fighter.fighter_yGetter() - 1);
                    }
                    break;
                }
                case "s": {// 下
                    if (fighter.fighter_xGetter() < fighter.heightGetter() - 1) {
                        fighter.fighter_xSetter(fighter.fighter_xGetter() + 1);
                    }
                    break;
                }
                case "d": {// 右
                    if (fighter.fighter_yGetter() < fighter.widthGetter() - 2) {
                        fighter.fighter_ySetter(fighter.fighter_yGetter() + 1);
                    }
                    break;
                }
                case " ": {// 发射子弹
                    bullets.add(new Bullet());
                    ifEnemySurvive(bullets, enemyFighters);// 敌机是否存活
                    break;
                }
            }

            for (enemyFighter enemyFighter : enemyFighters) {
                ifSurvive(enemyFighter);// 飞机是否存活
            }

            // 子弹与飞机移动依赖与用户操作
            pcOperate(speed);// 子弹与敌机运动
            if (speed < 5) {
                speed++;
            } else if (speed == 5) {
                enemyFighters.add(new enemyFighter());// 添加飞机
                speed = 0;// 更新速度值
            }
        } while (fighter.HPGetter() > 0);
        Sc.close();
    }

    public void pcOperate(int speed) {

        for (Bullet bullet : bullets) {
            bullet.floatBullet(); // 子弹移动
            ifEnemySurvive(bullets, enemyFighters);// 敌机是否存活
        }

        for (enemyFighter enemyFighter : enemyFighters) {
            if (speed % 2 == 0) {// 达到敌机移动速度
                enemyFighter.enemyFighterMove(); // 敌机移动
                ifEnemySurvive(bullets, enemyFighters);// 敌机是否存活
                ifSurvive(enemyFighter);// 飞机是否存活
            }
        }

        // 更新图像
        fighter.drawer(fighter.fighter_xGetter(), fighter.fighter_yGetter(),
                bullets, enemyFighters, fighter.scoreGetter(), fighter.HPGetter());
    }

    /**
     * 判断飞机是否存活
     */
    public void ifSurvive(enemyFighter enemyFighter) {
        // 飞机撞上敌机
        if ((enemyFighter.enemy_xGetter() == fighter.fighter_xGetter() - 1
                && enemyFighter.enemy_yGetter() == fighter.fighter_yGetter())
                || (enemyFighter.enemy_xGetter() == fighter.fighter_xGetter()
                        && enemyFighter.enemy_yGetter() > fighter.fighter_yGetter() - 3
                        && enemyFighter.enemy_yGetter() < fighter.fighter_yGetter() + 3)) {
            // 产生新敌机
            enemyFighter.createEnemyFighter();
            // 刷新飞机位置
            fighter.createFighter();
            fighter.HPSetter(fighter.HPGetter() - 1);// 减少生命值
            if (fighter.HPGetter() == 0) {
                System.out.println("***飞机被摧毁，游戏结束！***");
                System.exit(0);
            }
        }
    }

    /**
     * 判断敌机是否存活
     */
    public void ifEnemySurvive(ArrayList<Bullet> bullets, ArrayList<enemyFighter> enemyFighters) {
        for (Bullet bullet : bullets) {
            for (enemyFighter enemyFighter : enemyFighters) {
                if (bullet.bullet_xGetter() == enemyFighter.enemy_xGetter()
                        && bullet.bullet_yGetter() == enemyFighter.enemy_yGetter()) {// 击中敌机
                    bullet.scoreSetter(bullet.scoreGetter() + 1);// 加分
                    bullet.bullet_xSetter(-2);// 取消子弹
                    enemyFighter.createEnemyFighter();// 重置敌机
                }
            }
        }
    }

}
