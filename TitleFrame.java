package group;

import javax.swing.*;

class TitleFrame extends JFrame {
    public String[] PanelName = {"tp", "ep"};
    TitlePanel tp = new TitlePanel(this, PanelName[0]);
    EntrancePanel ep = new EntrancePanel(this, PanelName[1]);

    public TitleFrame() {
        this.setSize(600, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(tp);
        this.add(ep);
        tp.setVisible(true);
        ep.setVisible(false);
    }

    public void PanelChange(String current, String next) {
        if(current.equals(PanelName[0])) {
            tp.setVisible(false);
        }
        if(next.equals(PanelName[1])) {
            ep.setVisible(true);
        }
    }

    public static void main(String[] args) {
        TitleFrame tf = new TitleFrame();
        tf.setVisible(true);
    }
}