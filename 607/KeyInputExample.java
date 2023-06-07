
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class KeyInputExample implements KeyListener {

    public static void main(String[] args) {
        KeyInputExample example = new KeyInputExample();
        example.startListening();
    }

    public void startListening() {
        // 创建一个 JFrame 或其他容器
        // 添加 KeyListener 到容器
        // 例如：
        JFrame frame = new JFrame("Key Input Example");
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(false);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // 当按键被按下时触发
        // 处理按键事件
        // 例如：
        int keyCode = e.getKeyCode();
        if (keyCode == 27) {// KeyEvent.VK_ESCAPE
            System.exit(0); // 按下 ESC 键退出程序
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // 当按键被敲击时触发
        // 此方法不会处理按下按键事件，因此可以用于忽略字符输入
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 当按键释放时触发
        // 可以在此方法中实现按键释放的逻辑
    }
}
