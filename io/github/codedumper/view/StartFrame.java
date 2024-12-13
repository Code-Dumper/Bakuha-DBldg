import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


class Background extends JPanel {
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

class Title extends JPanel {
    private BufferedImage title;
    JPanel p1 = new JPanel(null);

    public Title(){
        JLabel b1 = new JLabel();
        
        try {
            ImageIcon icon1 = new ImageIcon("title1.jpg");
            
            b1.setIcon(icon1);
            
            b1.setPreferredSize(new Dimension(600,200));
            
        }catch(Exception e){
            e.printStackTrace();
        }
        b1.setBounds(0,0,600,200);
        
        p1.add(b1); 
    }
    
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (title != null) {
            g.drawImage(title, 0, 0, getWidth(), getHeight(), this);
        }        
    }

    public JPanel getButtonPanel() {
        return p1;
    }
}

class Button extends JButton{
    private BufferedImage button;
    JPanel p1 = new JPanel(null);
    public Button(){
        JButton b2 = new JButton();
        JButton b3 = new JButton();

        try {
            ImageIcon icon2 = new ImageIcon("start.jpg");
            ImageIcon icon3 = new ImageIcon("end.jpg");

            b2.setIcon(icon2);
            b3.setIcon(icon3);

            b2.setPreferredSize(new Dimension(500,200));
            b3.setPreferredSize(new Dimension(500,200));
        } catch (Exception e){
            e.printStackTrace();
        }
        b2.setBounds(0,0,150,50);
        b3.setBounds(0,50,150,50);

        p1.add(b2); p1.add(b3);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (button != null) {
            g.drawImage(button, 0, 0, getWidth(), getHeight(), this);
        }        
    }

    public JPanel getButtonPanel() {
        return p1;
    }
}

class StartFrame extends JFrame{
    public StartFrame(){
        this.setSize(600,800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Background back = new Background();
        this.setContentPane(back);

        Title title = new Title();
        title.setBounds(0, 150, 600, 200); // タイトルパネルの位置とサイズを設定
        title.setLayout(null);
        title.getButtonPanel().setBounds(0, 0, 600, 600); // ボタンパネルをタイトル画像の上に重ねる
        title.add(title.getButtonPanel()); // タイトルパネルにボタンパネルを追加

        // フレームにタイトルパネルを追加
        this.add(title);

        
        Button button = new Button();
        button.setBounds(225, 400, 150, 100); 
        button.setLayout(null);
        button.getButtonPanel().setBounds(0, 0, 600, 600); // ボタンパネルをタイトル画像の上に重ねる
        button.add(button.getButtonPanel()); // タイトルパネルにボタンパネルを追加

        // フレームにタイトルパネルを追加
        this.add(button);
        this.setLayout(null);
        
        this.setVisible(true);

    }

    public static void main (String argv[]){
        new StartFrame();
    }
}