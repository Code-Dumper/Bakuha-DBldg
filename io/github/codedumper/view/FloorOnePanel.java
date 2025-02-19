package io.github.codedumper.view;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import io.github.codedumper.controller.GameController;
import io.github.codedumper.model.State;


//このクラスは1Fの中での状態遷移と1Fの画像とボタンの表示を担うクラスです。
//このクラスはFundamentalPanelを継承し、changeSubStateをオーバーライドしています。
//1Fには2つの状態が定義されており、ロビー、部屋に対応します。
//SUBSTATEはFundamentalPanelの実装に合わせ、int型として定義しており、この実装は2025/01/30時点での仕様に準拠しています。
//画像ファイルはviewパッケージの下に位置していることが仮定されています。
//このクラスはスレッドセーフではありません。
public class FloorOnePanel extends FundamentalPanel{

    //private final int SUBSTATE_INITIAL = 1 FundamentalPanelから継承している
    private final int SUBSTATE_1FROOM = 2; //部屋
    private final int SUBSTATE_1F_BOMB = 3; //1F爆弾
    private final int SUBSTATE_1F_FRONTDOOR = 4; //部屋から進んだ先のドアの目の前
    private final int SUBSTATE_1F_TABLE = 5; // 部屋にあるフラスコの場所
    
    public FloorOnePanel(GameController controller){
        super(controller);
        changeSubState(currentSubState);
    }

    @Override
    protected void changeSubState(int subState){
        //layeredPane上のコンポーネントを全て削除する
        layeredPane.removeAll();
        this.revalidate();
        this.repaint();
        this.currentSubState = subState;
        JLabel imageLabel;
        //TODO 禁忌的なnull手法
        buttonToFront = buttonToLeft = buttonToRear = buttonToRight = null;
        switch(subState){
            //初期状態の時、後ろへ進むボタンはロビーへ戻るボタンで、前へ進むボタンは部屋へ移るボタンである。
            case SUBSTATE_INITIAL:
                imageLabel = createJLabelWithImage("image-OneFloor-Lobby-edited.png", 0, 0, 600, 800);
                buttonToRear = createStateChangeDirectionalButton(State.STATE_LOBBY, "LOBBYREAR");
                this.addLabel(imageLabel, LAYER_FIGURE_FIRST);
                //パズルが解かれているかどうかで表示するボタンを変える
                //もし解除されていれば、爆弾パネルが表示可能に
                //解除されていなければ、パズル用のボタンを表示
                if(controller.isPuzzleSolved()){
                    System.out.println("bombButton created.");
                    JButton bombButton = BumbcreateButtonWithImage(SUBSTATE_1F_BOMB, "image-Bomb.jpg", 50, 490, 150, 100);
                    addButton(bombButton, LAYER_UTIL_SECOND);
                }else{
                    System.out.println("boxButton created");
                    JButton boxButton = createButtonWithoutImage(State.STATE_MINIGAME, 0, 490, 150, 100);
                    addButton(boxButton, LAYER_UTIL_SECOND);
                }
                //ボタンが何回も押されていたら、看板の文字が変わるようにする機構。
                //TODO getModelで実質的にmodelの処理を呼び出しているので、controllerに委任したいかもしれない
                if(controller.getModel().dataManager.timesLabel1Clicked <= 0){
                    this.addLabelWithMessage("4! = ?",260, 270, 100, 100, 25, Color.BLACK);
                }else{
                    this.addLabelWithMessage("実験室内",260, 270, 100, 100, 25, Color.BLACK);
                }
                if(controller.getModel().dataManager.timesLabel2Clicked <= 0){
                    this.addLabelWithMessage("部屋番号?",260, 288, 100, 100, 25, Color.BLACK);
                }
                else{
                    this.addLabelWithMessage("飲食禁止",260, 288, 100, 100, 25, Color.BLACK);
                }
                if(controller.isPuzzleSolved()){
                    this.addLabelWithMessage("⌊e/2⌋ = ?",60,430,60, 50, 25, Color.BLUE);
                }else{
                    this.addLabelWithMessage("Locked",68,430,60, 50, 20, Color.RED);
                }
                this.addLabelWithMessage("開放厳禁",305, 350, 100, 100, 25, Color.BLACK);
                this.addButton(createLifeButton("label1",240, 250, 90, 100),LAYER_UTIL_SECOND);
                this.addButton(createLifeButton("label2",280, 350, 80, 100),LAYER_UTIL_SECOND);
            break;

            case SUBSTATE_1F_BOMB:
                //番号確認、番号入力用のコンポーネントを表示
                createNumberButtonPanel("image-Bumb-Detail2.jpg");
                buttonToRear = createDirectionalButton(SUBSTATE_INITIAL, "BOMBREAR");
                
            break;
        }
        System.out.println("Succenssfully change substate to " + subState);
        updateButton();
        this.revalidate();
        this.repaint();
    }

    JButton createLifeButton(String description, int x, int y, int width, int height){
        JButton lButton = new JButton();
        lButton.setActionCommand(description);
        lButton.addActionListener(this);
        lButton.setOpaque(false);
        lButton.setContentAreaFilled(false);
        lButton.setBounds(x,y,width, height);
        return lButton;
    }

    @Override
    public void actionPerformed(ActionEvent e){
        String command = e.getActionCommand();
         //爆弾解除のため入力を処理するコード
        if (command.startsWith("Number_")) {
            int number = Integer.parseInt(command.substring(7));
            controller.inputCodeToCurrentStateBomb(number);
            displayLabel.setText(String.valueOf(controller.getCodeOfCurrentStateBomb()));
            return;
        }else if(command.equals("Enter")){
            if(controller.disarmCurrentStateBombByCurrentCode()) {
                displayLabel.setText("Correct!");
                JOptionPane.showMessageDialog(this, "1階の爆弾の解除に成功した!");
                changeSubState(SUBSTATE_INITIAL);
                if(controller.areAllBombsDisarmed()){
                    JOptionPane.showMessageDialog(this, "全ての階の爆弾を解除した!");
                    controller.transition(State.STATE_GAMECLEAR);
                }
                return;
            }
            else {
                displayLabel.setText("Incorrect");
                controller.resetCodeOfCurrentStateBomb();
                return;
            }
        }else if(command.equals("Clear")){
            controller.resetCodeOfCurrentStateBomb();
            displayLabel.setText("");
            return;
        }


        switch(command){
            case "STATE_LOBBY":
                controller.transition(State.STATE_LOBBY);
            break;
            case "STATE_MINIGAME":
                controller.transition(State.STATE_MINIGAME);
            break;
            case "label1":
                System.out.println("label1--" + controller.getModel().dataManager.timesLabel1Clicked);
                controller.getModel().dataManager.timesLabel1Clicked--;
            break;
            case "label2":
                System.out.println("label2--" + controller.getModel().dataManager.timesLabel2Clicked);
                controller.getModel().dataManager.timesLabel2Clicked--;
            break;
            default:
                changeSubState(Integer.parseInt(command));
            break;
        }
    }
}
