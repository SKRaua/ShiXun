package GSnake;

public class Picture {
    private int height, width;
    private int[][] size;

    /**
     * 构造游戏图像框架数据
     */
    public Picture() {
        // 游戏界面尺寸
        height = 5;
        width = 10;
        size = new int[height + 2][width + 2];
        for (int i = 0; i < height + 2; i++) {

        }
    }

    /**
     * @return 坐标上的值
     */
    public int xGetter(int x, int y) {
        return size[x][y];
    }

    /**
     * @return 游戏高
     */
    public int heightGetter() {
        return height;
    }

    /**
     * @return 游戏宽
     */
    public int widthGetter() {
        return width;
    }

    /**
     * 画游戏图像
     */
    public void drawPicture() {
        for (int x = 0; x < height + 2; x++) {
            for (int y = 0; y < width + 2; y++) {
                size[x][y];
                if (y == 0 || y == width + 1) {// 输出边框
                    System.out.print("|");
                } else if (x == 0 || x == height + 1) {// 输出边框
                    System.out.print("=");
                } else {
                    System.out.print(" ");// 输出空格
                }
            }
            System.out.println();
        }
        // System.out.println("| |");

    }
}
