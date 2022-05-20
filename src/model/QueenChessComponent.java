package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class QueenChessComponent extends ChessComponent{
    private static Image QUEEN_WHITE;
    private static Image QUEEN_BLACK;

    private Image queenImage;

    public void loadResource() throws IOException {
        if (QUEEN_WHITE == null) {
            QUEEN_WHITE = ImageIO.read(new File("./images/queen-white.png"));
        }

        if (QUEEN_BLACK == null) {
            QUEEN_BLACK = ImageIO.read(new File("./images/queen-black.png"));
        }
    }

    private void initiateRookImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                queenImage = QUEEN_WHITE;
            } else if (color == ChessColor.BLACK) {
                queenImage = QUEEN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QueenChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateRookImage(color);
        if(chessColor==ChessColor.BLACK){
            this.name="Q";
        }else {
            this.name="q";
        }
    }



    /**
     * 皇后移动方式
     */
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        int x1 = source.getX();int y1 = source.getY();
        int x2 = destination.getX();int y2 = destination.getY();
        if (Math.abs(x1-x2)==Math.abs(y1-y2)){
            if (x1<x2&&y1<y2){
                for (int i=1;i<x2-x1;i++){
                    if (!(chessComponents[x1+i][y1+i] instanceof EmptySlotComponent)){
                        return false;}
                }
            }else if (x1>x2&&y1>y2){
                for (int i=1;i<x1-x2;i++){
                    if (!(chessComponents[x2+i][y2+i] instanceof EmptySlotComponent)){
                        return false;}
                }
            }else if (x1<x2&&y1>y2){
                for (int i=1;i<x2-x1;i++){
                    if (!(chessComponents[x2-i][y2+i] instanceof EmptySlotComponent)){
                        return false;}
                }
            } else if (x1>x2&&y1<y2){
                for (int i=1;i<x1-x2;i++){
                    if (!(chessComponents[x2+i][y2-i] instanceof EmptySlotComponent)){
                        return false;}
                }
            }
        }else if (source.getX() == destination.getX()) {
            int row = source.getX();
            for (int col = Math.min(source.getY(), destination.getY()) + 1;
                 col < Math.max(source.getY(), destination.getY()); col++) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        } else if (source.getY() == destination.getY()) {
            int col = source.getY();
            for (int row = Math.min(source.getX(), destination.getX()) + 1;
                 row < Math.max(source.getX(), destination.getX()); row++) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        } else { // Not on the same row or the same column.
            return false;
        }
        return true;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(queenImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
