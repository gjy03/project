package controller;

import model.ChessComponent;
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
    //鐐瑰嚮妫嬪瓙瀹炵幇閫夊彇妫嬪瓙锛?
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
            if (first == chessComponent) { // 鍐嶆鐐瑰嚮鍙栨秷閫夊彇

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

                chessboard.swapColor();//鏇存柊浜嗘枃妗ｆ暟鎹?
                chessboard.swapChessComponents(first, chessComponent);//璇诲彇鏂囨。鏁版嵁
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
     * @param chessComponent 鐩爣閫夊彇鐨勬瀛?
     * @return 鐩爣閫夊彇鐨勬瀛愭槸鍚︿笌妫嬬洏璁板綍鐨勫綋鍓嶈妫嬫柟棰滆壊鐩稿悓
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first妫嬪瓙鐩爣绉诲姩鍒扮殑妫嬪瓙second
     * @return first妫嬪瓙鏄惁鑳藉绉诲姩鍒皊econd妫嬪瓙浣嶇疆
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint());
    }
}
