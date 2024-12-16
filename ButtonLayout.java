package io.github.codedumper.view;

/*ボタンの位置とサイズを返す */

public class ButtonLayout {
    private int x;
    private int y;
    private int width;
    private int height;

    public ButtonLayout(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }
    //各要素のゲッター
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}