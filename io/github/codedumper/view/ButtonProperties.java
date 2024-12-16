package io.github.codedumper.view;

/**
 * ボタンの位置とサイズを管理するクラスButtonProperties. 
 * */
public class ButtonProperties {
    private int x;
    private int y;
    private int width;
    private int height;

    public ButtonProperties(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }
    //各要素のゲッター
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight() {
        return this.height;
    }
}