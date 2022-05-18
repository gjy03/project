//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package model;

import java.awt.Color;

public enum ChessColor {
    BLACK("Black", Color.BLACK),
    WHITE("White", Color.WHITE),
    NONE("No Player", Color.WHITE);

    private final String name;
    private final Color color;

    private ChessColor(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return this.name;
    }

    public Color getColor() {
        return this.color;
    }
}
