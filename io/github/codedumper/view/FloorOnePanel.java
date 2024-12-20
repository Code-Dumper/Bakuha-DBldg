package io.github.codedumper.view;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FloorOnePanel extends JPanel{
    private BufferedImage floor1;
    public FloorOnePanel(){
        try{
            floor1 =ImageIO.read(new File("1f_background.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (floor1 != null) {
            g.drawImage(floor1, 0, 0, getWidth(), getHeight(), this);
        }        
    }

}
