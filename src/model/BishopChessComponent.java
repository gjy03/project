//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

import controller.ClickController;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import view.ChessboardPoint;

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
            this.loadResource();
            if (color == ChessColor.WHITE) {
                this.bishopImage = BISHOP_WHITE;
            } else if (color == ChessColor.BLACK) {
                this.bishopImage = BISHOP_BLACK;
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        this.initiateRookImage(color);
        if (this.chessColor == ChessColor.BLACK) {
            this.name = "B";
        } else {
            this.name = "b";
        }

    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = this.getChessboardPoint();
        int x1 = source.getX();
        int y1 = source.getY();
        int x2 = destination.getX();
        int y2 = destination.getY();
        if (Math.abs(x1 - x2) != Math.abs(y1 - y2)) {
            return false;
        } else {
            int i;
            if (x1 < x2 && y1 < y2) {
                for(i = 1; i < x2 - x1; ++i) {
                    if (!(chessComponents[x1 + i][y1 + i] instanceof EmptySlotComponent)) {
                        return false;
                    }
                }
            } else if (x1 > x2 && y1 > y2) {
                for(i = 1; i < x1 - x2; ++i) {
                    if (!(chessComponents[x2 + i][y2 + i] instanceof EmptySlotComponent)) {
                        return false;
                    }
                }
            } else if (x1 < x2 && y1 > y2) {
                for(i = 1; i < x2 - x1; ++i) {
                    if (!(chessComponents[x2 - i][y2 + i] instanceof EmptySlotComponent)) {
                        return false;
                    }
                }
            } else if (x1 > x2 && y1 < y2) {
                for(i = 1; i < x1 - x2; ++i) {
                    if (!(chessComponents[x2 + i][y2 - i] instanceof EmptySlotComponent)) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.bishopImage, 0, 0, this.getWidth(), this.getHeight(), this);
        g.setColor(Color.BLACK);
        if (this.isSelected()) {
            g.setColor(Color.RED);
            g.drawOval(0, 0, this.getWidth(), this.getHeight());
        }

    }
}
