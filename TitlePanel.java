package group;

import java.awt.event.*;
import javax.swing.*;
//import group.TitleFrame.*;

class TitlePanel extends JPanel implements ActionListener {
    TitleFrame tf;

    public TitlePanel(TitleFrame tf, String name) {
        this.tf = tf;
        this.setName(name);
        this.setSize(600, 800);
        this.setLayout(null);
        JLabel title = new JLabel("title", JLabel.CENTER);
        title.setBounds(200, 150, 200, 100);
        this.add(title);

        JButton bs = new JButton("START");
        bs.setBounds(200, 300, 200, 100);
        this.add(bs);
        bs.addActionListener(this);

        JButton be = new JButton("END");
        be.setBounds(200, 450, 200, 100);
        this.add(be);
        be.addActionListener(this);

        bs.setActionCommand("START");
        be.setActionCommand("END");
    }

    public void actionPerformed(ActionEvent e) {
        String es = e.getActionCommand();
        if(es.equals("START")) {
            tf.PanelChange(tf.PanelName[0], tf.PanelName[1]);
        }
        else if(es.equals("END")) {
            System.exit(0);
        }
    }
}