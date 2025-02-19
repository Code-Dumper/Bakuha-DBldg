package io.github.codedumper.model;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.InputStream;
import java.io.IOException;

public class DataManager {
    public int oneFloorBombCode;
    public int twoFloorBombCode;
    public int threeFloorBombCode;
    public int fourFloorBombCode;
    public int timesLabel1Clicked;
    public int timesLabel2Clicked;

    public DataManager(int size){
        oneFloorBombCode = 2241;
        twoFloorBombCode = 7705;
        threeFloorBombCode = 3333;
        fourFloorBombCode = 4444;
        timesLabel1Clicked = 5;
        timesLabel2Clicked = 5;
    }
    public Font genFont(String filename, float size){
        Font font = null;
        InputStream is = null;
        try{
            is = getClass().getResourceAsStream(filename);
            if (is == null) {
                System.out.println("Font file not found!");
                throw new NullPointerException("FontFileNotFound");
            }
            font = Font.createFont(Font.TRUETYPE_FONT,is);
            font = font.deriveFont(size);
            is.close();
        }catch(IOException e){
            System.out.println("IOException");
            e.printStackTrace();
        }catch(FontFormatException e){
            System.out.println("FontFormatException");
            e.printStackTrace();
        }
        return font;
        
    }

    public void updateFontSize(Font font, int size){
        font.deriveFont(size);
    }

    public int getCode(int floor){
        switch (floor){
            case 1: return oneFloorBombCode;
            case 2: return twoFloorBombCode; 
            case 3: return threeFloorBombCode;
            case 4: return fourFloorBombCode;
            default: throw new IllegalArgumentException();
        }
    }
}
