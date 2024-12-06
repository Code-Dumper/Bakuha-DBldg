package group;

//import java.awt.event.*;
import javax.swing.*;
//import group.TitleFrame.*;

class EntrancePanel extends JPanel {
    TitleFrame tf;

    public EntrancePanel(TitleFrame tf, String name) {
        this.tf = tf;
        this.setName(name);
        this.setSize(600, 800);
        this.setLayout(null);
        JLabel entrance = new JLabel("title", JLabel.CENTER);
        entrance.setBounds(200, 150, 20, 100);
        this.add(entrance);
    }
}