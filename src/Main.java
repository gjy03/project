import view.ChessGameFrame;

import javax.swing.*;

public class Main {
    static JFrame fatherFrame;
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            ChessGameFrame mainFrame = new ChessGameFrame(800, 608);
            mainFrame.setVisible(true);
        });
    }
}
