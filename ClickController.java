package controller;

import model.ChessComponent;
import view.A;
import view.Chessboard;
import view.ChessboardPoint;

import java.util.ArrayList;
import java.util.List;

public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;



    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
//点击棋子实现选取棋子；
public void onClick(ChessComponent chessComponent) {
    List<ChessboardPoint> points = chessComponent.getPointsCanMoveTo(chessboard.getChessComponents());
    ArrayList<ChessComponent> chessComponents = new ArrayList<>();
    for (ChessboardPoint point : points) {
        chessComponents.add(getChess(point));
    }
    if (first == null) {
        if (handleFirst(chessComponent)) {
            chessComponent.setSelected(true);
            first = chessComponent;

            for (ChessComponent component : chessComponents) {
                component.setAttacked(true);
                if (component.getName()=="K"||component.getName()=="k"){
                    component.setJiangjun(true);
                }
                component.repaint();
            }
            first.repaint();

        }
    } else {
        if (first == chessComponent) { // 再次点击取消选取

            for (ChessComponent component : chessComponents) {
                component.setAttacked(false);
                if (component.getName()=="K"||component.getName()=="k"){
                    component.setJiangjun(false);
                }
                component.repaint();
            }
            chessComponent.setSelected(false);
            ChessComponent recordFirst = first;
            first = null;
            recordFirst.repaint();

        } else if(chessComponent.getChessColor() == chessboard.getCurrentColor()){

            first.setSelected(false);
            chessComponent.setSelected(true);
            ChessComponent chessComponent1=first;
            first=chessComponent;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    chessboard.getChessComponents()[i][j].setAttacked(false);
                    chessboard.getChessComponents()[i][j].repaint();
                }
            }

            for (ChessComponent component : chessComponents) {
                component.setAttacked(true);
                component.repaint();
            }
            first.repaint();
            chessComponent1.repaint();
        } else if (handleSecond(chessComponent)) {
            //repaint in swap chess method.

            chessboard.swapColor();//更新了文档数据
            chessboard.swapChessComponents(first, chessComponent);//读取文档数据
            //chessboard.getRegretChessDataTure();
            //chessboard.swapColor();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    chessboard.getChessComponents()[i][j].setAttacked(false);
                    chessboard.getChessComponents()[i][j].repaint();
                }
            }
            first.setSelected(false);
            first.repaint();
            first = null;
            new Music_Click();
        }
    }

}

    private ChessComponent getChess(ChessboardPoint chessboardPoint){
        return chessboard.getChessComponents()[chessboardPoint.getX()][chessboardPoint.getY()];
    }

    /**
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint());
    }
}
