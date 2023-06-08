package GSnake;

import java.util.Scanner;

/**
 * The Operate class of the FlyMe2TheMoon.（控制程序）
 * 
 * @author Xinyang Zhou
 * @version 2.0
 */
public class Operate {
    private Snack snack;

    public Operate() {
        snack = new Snack();
        // 打印初始图像
        snack.drawPicture();
    }

    /**
     * 控制飞机移动
     */
    public void operateFighter() {
        Scanner Sc = new Scanner(System.in);
        int speed = 0;// 控制敌机速度
        do {// 控制飞机移动
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
                    bullet.bullet_xSetter(fighter.fighter_xGetter() - 2);
                    bullet.bullet_ySetter(fighter.fighter_yGetter());
                    ifEnemySurvive();
                    break;
                }
            }
            ifSurvive();// 飞机是否存活

            if (speed < 2) {
                pcOperate(speed);// 子弹与敌机运动
                speed++;
            } else if (speed == 2) {
                pcOperate(speed);// 子弹与敌机运动
                speed = 0;// 更新速度值
            }
        } while (fighter.HPGetter() > 0);
        Sc.close();
    }

    public void pcOperate(int speed) {

        bullet.floatBullet(); // 子弹移动
        ifEnemySurvive();// 敌机是否存活
        if (speed == 2) {// 达到敌机移动速度
            enemyFighter.enemyFighterMove(); // 敌机移动
            ifEnemySurvive();// 敌机是否存活
            ifSurvive();// 飞机是否存活
        }

        // 更新图像
        fighter.drawer(fighter.fighter_xGetter(), fighter.fighter_yGetter(),
                bullet.bullet_xGetter(), bullet.bullet_yGetter(),
                enemyFighter.enemy_xGetter(), enemyFighter.enemy_yGetter(),
                bullet.scoreGetter(), fighter.HPGetter());

    }

    /**
     * 判断飞机是否存活
     */
    public void ifSurvive() {
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
    public void ifEnemySurvive() {
        if (bullet.bullet_xGetter() == enemyFighter.enemy_xGetter()
                && bullet.bullet_yGetter() == enemyFighter.enemy_yGetter()) {// 击中敌机
            bullet.scoreSetter(bullet.scoreGetter() + 1);// 加分
            bullet.bullet_xSetter(-2);// 取消子弹
            enemyFighter.createEnemyFighter();// 重置敌机
        }
    }
}
