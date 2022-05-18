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

public class KingChessComponent extends ChessComponent {
    private static Image KING_WHITE;
    private static Image KING_BLACK;
    private Image kingImage;

    public void loadResource() throws IOException {
        if (KING_WHITE == null) {
            KING_WHITE = ImageIO.read(new File("./images/king-white.png"));
        }

        if (KING_BLACK == null) {
            KING_BLACK = ImageIO.read(new File("./images/king-black.png"));
        }

    }

    private void initiateRookImage(ChessColor color) {
        try {
            this.loadResource();
            if (color == ChessColor.WHITE) {
                this.kingImage = KING_WHITE;
            } else if (color == ChessColor.BLACK) {
                this.kingImage = KING_BLACK;
            }
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public KingChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        this.initiateRookImage(color);
        if (this.chessColor == ChessColor.BLACK) {
            this.name = "K";
        } else {
            this.name = "k";
        }

    }

    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = this.getChessboardPoint();
        int x1 = source.getX();
        int y1 = source.getY();
        int x2 = destination.getX();
        int y2 = destination.getY();
        return Math.abs(x1 - x2) == 1 && y1 == y2 || x1 == x2 && Math.abs(y1 - y2) == 1 || Math.abs(x1 - x2) == 1 && Math.abs(y1 - y2) == 1;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.kingImage, 0, 0, this.getWidth(), this.getHeight(), this);
        g.setColor(Color.BLACK);
        if (this.isSelected()) {
            g.setColor(Color.RED);
            g.drawOval(0, 0, this.getWidth(), this.getHeight());
        }

    }
}
