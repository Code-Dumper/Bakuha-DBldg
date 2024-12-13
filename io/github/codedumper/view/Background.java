package io.github.codedumper.view;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Background extends JPanel {
    private BufferedImage background;

    public Background(){
        try {
            background = ImageIO.read(new File("d.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }        
    }
}