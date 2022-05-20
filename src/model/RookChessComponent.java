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

public class RookChessComponent extends ChessComponent {
    private static Image ROOK_WHITE;
    private static Image ROOK_BLACK;
    private Image rookImage;

    public void loadResource() throws IOException {
        if (ROOK_WHITE == null) {
            ROOK_WHITE = ImageIO.read(new File("./images/rook-white.png"));
        }

        if (ROOK_BLACK == null) {
            ROOK_BLACK = ImageIO.read(new File("./images/rook-black.png"));
        }

    }

    private void initiateRookImage(ChessColor color) {
        try {
            this.loadResource();
            if (color == ChessColor.WHITE) {
                this.rookImage = ROOK_WHITE;
            } else if (color == ChessColor.BLACK) {
                this.rookImage = ROOK_BLACK;
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public RookChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        this.initiateRookImage(color);
        if (this.chessColor == ChessColor.BLACK) {
            this.name = "R";
        } else {
            this.name = "r";
        }

    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = this.getChessboardPoint();
        int col;
        int row;
        if (source.getX() == destination.getX()) {
            col = source.getX();

            for(row = Math.min(source.getY(), destination.getY()) + 1; row < Math.max(source.getY(), destination.getY()); ++row) {
                if (!(chessComponents[col][row] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        } else {
            if (source.getY() != destination.getY()) {
                return false;
            }

            col = source.getY();

            for(row = Math.min(source.getX(), destination.getX()) + 1; row < Math.max(source.getX(), destination.getX()); ++row) {
                if (!(chessComponents[row][col] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        }

        return true;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.rookImage, 0, 0, this.getWidth(), this.getHeight(), this);
        g.setColor(Color.BLACK);
        if (this.isSelected()) {
            g.setColor(Color.RED);
            g.drawOval(0, 0, this.getWidth(), this.getHeight());
        }

    }

}
