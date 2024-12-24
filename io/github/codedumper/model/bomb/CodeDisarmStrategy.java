package io.github.codedumper.model;

public class CodeDisarmStrategy implements DisarmStrategy{
    private String correctCode;

    //コンストラクタ
    public CodeDisarmStrategy(String correctCode){
        this.correctCode = correctCode;
    }
    //inputCodeがcorrectCodeと等しいかどうかで解除を決める
    public boolean tryToDisarm(String inputCode){
        return inputCode.equals(correctCode);
    }
}
