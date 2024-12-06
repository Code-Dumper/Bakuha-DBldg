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

class Title extends JPanel{
    private BufferedImage title;
    JPanel p1 = new JPanel(new GridLayout(3,1));

    public Title(){
        try {
            title = ImageIO.read(new File("title.jpg"));
        } catch (IOException e){
            e.printStackTrace();            
        }
        // 画像を縦に3分割する
        int height = title.getHeight();
        int width = title.getWidth();
        int buttonHeight = width / 3; // 高さを3等分

        // 画像を分割してボタンに設定
        JButton b1 = createButtonFromImage(0, 0, width, buttonHeight); // 上部
        JButton b2 = createButtonFromImage(0, buttonHeight, width, buttonHeight); // 中央
        JButton b3 = createButtonFromImage(0, 2 * buttonHeight, width, buttonHeight); // 下部

        p1.add(b1); p1.add(b2); p1.add(b3);
    }
    private JButton createButtonFromImage(int x, int y, int width, int height) {
        // 画像の指定部分を切り出す
        BufferedImage subImage = title.getSubimage(x, y, width, height);

        // 切り出した部分をImageIconに変換
        ImageIcon icon = new ImageIcon(subImage);

        // アイコンを設定したボタンを作成
        JButton button = new JButton();
        button.setIcon(icon);

        // ボタンの背景を透明に設定
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setText(""); // テキストは表示しない

        return button;
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if (title != null) {
            g.drawImage(title, 0, 0, getWidth(), getHeight(), this);
        }        
    }

    public JPanel getButtonPanel(){
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
        title.setBounds(0,150,600,600);
        title.setLayout(null);
        title.getButtonPanel().setBounds(0, 0, 600, 600); // ボタンパネルをタイトル画像に重ねる
        title.add(title.getButtonPanel()); // タイトルにボタンパネルを追加

        // フレームにタイトルパネルを追加
        this.add(title);
        this.setLayout(null);
        
        this.setVisible(true);

    }

    public static void main (String argv[]){
        new StartFrame();
    }
}