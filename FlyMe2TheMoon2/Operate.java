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
    private ArrayList<EnemyFighter> enemyFighters = new ArrayList<EnemyFighter>();// 敌机

    public Operate() {
        fighter = new Fighter();
        bullets.add(new Bullet(-1, -1));
        enemyFighters.add(new EnemyFighter());
        // 打印初始图像
        fighter.drawer(fighter.fighter_xGetter(), fighter.fighter_yGetter(),
                bullets, enemyFighters, fighter.scoreGetter(), fighter.HPGetter());
    }

    /**
     * 控制飞机移动
     */
    public void operateFighter() {
        Scanner scanner = new Scanner(System.in);
        int speed = 0;// 控制敌机速度与敌机刷新
        do {// 控制飞机移动与
            String input = scanner.nextLine();
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
                    bullets.add(new Bullet(fighter.fighter_xGetter(), fighter.fighter_yGetter()));
                    ifEnemySurvive(bullets, enemyFighters);// 敌机是否存活
                    break;
                }
                case "b": {// 发射super子弹

                    for (int x = 0; x < 4; x++) {
                        for (int y = 0; y < 3; y++) {
                            bullets.add(new Bullet(fighter.fighter_xGetter() - x, fighter.fighter_yGetter() + y - 1));
                        }
                    }
                    ifEnemySurvive(bullets, enemyFighters);// 敌机是否存活
                    break;
                }
            }
            ifSurvive(enemyFighters);// 飞机是否存活

            // 子弹与飞机移动依赖与用户操作
            pcOperate(speed);// 子弹与敌机运动
            if (speed < 5) {
                speed++;
            } else if (speed == 5) {
                enemyFighters.add(new EnemyFighter());// 添加飞机
                speed = 0;// 更新速度值
            }
        } while (fighter.HPGetter() > 0);
        scanner.close();
    }

    public void pcOperate(int speed) {

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).floatBullet(); // 子弹移动
            if (bullets.get(i).bullet_xGetter() == -1) {
                bullets.remove(bullets.get(i));
                i--;
            }
        }
        ifEnemySurvive(bullets, enemyFighters);// 敌机是否存活

        for (int i = 0; i < enemyFighters.size(); i++) {
            if (speed % 2 == 0) {// 达到敌机移动速度
                enemyFighters.get(i).enemyFighterMove(); // 敌机移动
                ifEnemySurvive(bullets, enemyFighters);// 敌机是否存活
                ifSurvive(enemyFighters);// 飞机是否存活
                if (enemyFighters.get(i).enemy_xGetter() > fighter.heightGetter()) {
                    enemyFighters.remove(enemyFighters.get(i));
                    i--;
                }
            }
        }

        // 更新图像
        fighter.drawer(fighter.fighter_xGetter(), fighter.fighter_yGetter(),
                bullets, enemyFighters, fighter.scoreGetter(), fighter.HPGetter());
    }

    /**
     * 判断飞机是否存活
     */
    public void ifSurvive(ArrayList<EnemyFighter> enemyFighters) {
        for (int i = 0; i < enemyFighters.size(); i++) {
            // 飞机撞上敌机
            if ((enemyFighters.get(i).enemy_xGetter() == fighter.fighter_xGetter() - 1
                    && enemyFighters.get(i).enemy_yGetter() == fighter.fighter_yGetter())
                    || (enemyFighters.get(i).enemy_xGetter() == fighter.fighter_xGetter()
                            && enemyFighters.get(i).enemy_yGetter() > fighter.fighter_yGetter() - 3
                            && enemyFighters.get(i).enemy_yGetter() < fighter.fighter_yGetter() + 3)) {

                enemyFighters.remove(enemyFighters.get(i)); // 删除敌机
                enemyFighters.add(new EnemyFighter()); // 产生新敌机
                fighter.createFighter(); // 刷新飞机位置
                fighter.HPSetter(fighter.HPGetter() - 1);// 减少生命值
                if (fighter.HPGetter() == 0) {
                    System.out.println("***飞机被摧毁，游戏结束！***");
                    System.exit(0);
                }
            }
        }
    }

    /**
     * 判断敌机是否存活
     */
    public void ifEnemySurvive(ArrayList<Bullet> bullets, ArrayList<EnemyFighter> enemyFighters) {
        for (Bullet bullet : bullets) {
            for (EnemyFighter enemyFighter : enemyFighters) {
                if (bullet.bullet_xGetter() == enemyFighter.enemy_xGetter()
                        && bullet.bullet_yGetter() == enemyFighter.enemy_yGetter()) {// 击中敌机
                    fighter.scoreSetter(fighter.scoreGetter() + 1);// 加分
                    bullet.bullet_xSetter(-2);// 取消子弹
                    enemyFighter.createEnemyFighter();// 重置敌机
                }
            }
        }
    }

}
