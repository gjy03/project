package model;

import controller.ClickController;
import view.Chessboard;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BishopChessComponent extends ChessComponent {

    private static Image BISHOP_WHITE;
    private static Image BISHOP_BLACK;

    private Image bishopImage;

    public void loadResource() throws IOException {
        if (BISHOP_WHITE == null) {
            BISHOP_WHITE = ImageIO.read(new File("./images/bishop-white.png"));
        }

        if (BISHOP_BLACK == null) {
            BISHOP_BLACK = ImageIO.read(new File("./images/bishop-black.png"));
        }
    }

    private void initiateRookImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                bishopImage = BISHOP_WHITE;
            } else if (color == ChessColor.BLACK) {
                bishopImage = BISHOP_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateRookImage(color);
        if(chessColor==ChessColor.BLACK){
            this.name="B";
        }else {
            this.name="b";
        }
    }

    /**
     * 象移动方式
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
        }else {
            return false;
        }
        return true;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bishopImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());

        }
    }
}
