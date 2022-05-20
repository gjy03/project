package view;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.JDBCType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PositionListener implements ActionListener {

    // deltaX < 0: 向左移动; deltaY < 0: 向上移动
    private int deltaX, deltaY;
    private int stepSize;

    public static Timer timer;
    public static LinkedList<Integer> moveXArray = new LinkedList<>();
    public static LinkedList<Integer> moveYArray = new LinkedList<>();

    public PositionListener(int deltaX, int deltaY, JLabel chessImageLabel) {
        setStepSize(deltaX, deltaY);
        int TIMER_DELAY = 2;
        timer = new Timer(TIMER_DELAY, e -> {
            if (!moveXArray.isEmpty()) {
                chessImageLabel.setLocation(
                        chessImageLabel.getX() + moveXArray.poll(),
                        chessImageLabel.getY() + moveYArray.poll()
                );
            }
        });
        timer.start();
    }

    public void setStepSize (int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        stepSize = Math.max(Math.abs(deltaX), Math.abs(deltaY));
    }

    public void setStepSize (int stepSize) {
        if(deltaX != 0) deltaX = deltaX / Math.abs(deltaX) * stepSize;
        if(deltaY != 0) deltaY = deltaY / Math.abs(deltaY) * stepSize;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < stepSize; ++i) {
            moveXArray.add(deltaX == 0 ? 0: deltaX / Math.abs(deltaX));
            moveYArray.add(deltaY == 0 ? 0: deltaY / Math.abs(deltaY));
        }
    }
}
