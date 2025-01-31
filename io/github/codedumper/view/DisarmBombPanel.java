package io.github.codedumper.view;

import io.github.codedumper.controller.*;
import io.github.codedumper.model.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DisarmBombPanel extends BasePanel {

    //入力した文字を4つまで表示する
    private JLabel displayLabel;
    
    //入力した文字を4つまで格納する
    private StringBuilder inputNumbers;

    //ボタンを12個配置するパネル
    private JPanel NumberButtonPanel;
    
    public DisarmBombPanel(GameController controller) {
        super(controller);
        //背景画像をset(今設定してる画像はてきとうなやつ)
        this.setBackground("io/github/codedumper/view/image-OneFloor-Lobby.jpg",0);

        // 表示領域, 上部中央に配置
        displayLabel = new JLabel("", SwingConstants.CENTER);
        displayLabel.setBounds(50, 100, 500, 100);
        displayLabel.setFont(new Font("Arial", Font.BOLD, 40));
        displayLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.add(displayLabel);

        inputNumbers = new StringBuilder();

        // パネルにボタンを配置 (3x4のグリッド)
        NumberButtonPanel = new JPanel();
        NumberButtonPanel.setLayout(new GridLayout(4, 3));
        NumberButtonPanel.setBounds(50, 200, 500, 500);
        this.add(NumberButtonPanel);

        //ボタンに数字またはEnter,Clearを与えて作成
        createNumberButton(String.valueOf(7));
        createNumberButton(String.valueOf(8));
        createNumberButton(String.valueOf(9));
        createNumberButton(String.valueOf(4));
        createNumberButton(String.valueOf(5));
        createNumberButton(String.valueOf(6));
        createNumberButton(String.valueOf(1));
        createNumberButton(String.valueOf(2));
        createNumberButton(String.valueOf(3));
        createNumberButton("Enter");
        createNumberButton(String.valueOf(0));
        createNumberButton("Clear");
    }

    //ボタンを作成、NumberButtonPanelに貼り付け
    public void createNumberButton(String number) {
        JButton numberButton = new JButton();
        numberButton.setSize(100, 100);
        numberButton.setActionCommand(number);
        numberButton.addActionListener(this);
        NumberButtonPanel.add(numberButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String number = e.getActionCommand();
        switch(number){
            case "Enter":

            break;
            case "Clear":
                inputNumbers.setLength(0); // 文字列をクリア
                displayLabel.setText(""); // ラベルを空白にする
            break;
            default:
                if (inputNumbers.length() < 4) {
                    inputNumbers.append(number);
                    displayLabel.setText(inputNumbers.toString());
                }
        }
    }
}
