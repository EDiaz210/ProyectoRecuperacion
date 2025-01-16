import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public static void main(String[] args) {
        JFrame jp = new JFrame();
        jp.setContentPane(new Login().login);
        jp.setSize(200,200);
        jp.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jp.setVisible(true);

    }
}