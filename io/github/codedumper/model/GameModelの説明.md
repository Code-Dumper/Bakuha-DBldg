GameModelの説明です。これ読んでGameModelのやりたいことがわからなかったら、多分作りながら考えたほうがうまく行く可能性が高いので、これを完璧に理解するんじゃなくてこれを片手に動く形でとりあえず実装を進めた方がいいと思います。そんなに時間がないので。  


  
目次  
* [GameModelの役割とは？](GameModelの役割とは？)  
* [GameModelのメソッドの説明](メソッドの説明)  


# GameModelの役割とは？
## ◆GameModelの提供する情報
GameModelは、ゲーム内での操作に必要な情報を管理し、その情報を提供するクラス。具体的には
- ゲームの残り時間(currentTime)
- 現在どこにいるのかを管理する状態(currentState)
- 爆弾の解除状況(オープンにされているのは、全ての爆弾が解除されたかどうかであり、ViewやController側には各爆弾の解除状態はオープンにされていない)(オープンにすることは可能だが、必要がなければ実装はしない)
- 爆弾に入力されているコード(番号で、整数4桁)の状態(key)
- Planarity実装用のグラフのノードとエッジ

を管理し、その情報を提供する。


## ◆内部的な都合の話
情報の管理はGameModelだけで行われておらず、GameModelでない別クラスにより一部管理されている。 したがって、GameModel.javaだけを見ても処理の全貌を把握することは困難である。
具体的には、bombパッケージ内のBombManagerやplanarityパッケージ内のGraphManagerが補助的に用いられることで情報は処理されている。ViewやControllerからGameModelを使用するという状況に限れば、ViewやControllerはGameModel内部の仕様を知る必要はなく、GameModelだけにアクセスすれば良い。  
注意：この仕様だとGameModelは依存ズブズブで疎結合の観点からあまり適切ではない可能性がある。
## ◆コンストラクタが呼ばれて実行される処理の説明
GameModelのコンストラクタが実行されると、残り時間3600s、初期状態STATE_TITLEが設定され、ゲームの残り時間を管理するタイマーとPlanarityのグラフを管理するためのマネージャのインスタンスが作成される。 
## ◆View, ControllerがGameModelを使ってできること
View、ControllerはこのGameModelにアクセスすることで、直感的な規則に従って必ず残り時間や現在の状態の取得と変更ができる。
## ◆ゲームの情報の型
```java
private Event currentState; //現在のゲーム状態
private GameTimer timer; //ゲームで共通のタイマー
private double remainTime; //残り時間
private BombManager bombManager; //爆弾管理
private StateMachine stateMachine; //状態管理
private GraphManager graphManager; //ミニゲームのグラフ管理
private int key[]; //ユーザの入力した爆弾解除のコード
```
最新の設計についてはGameModelを確認してほしい。  

# GameModelのメソッドの説明

## コンストラクタ
```java
public GameModel()
```
ViewやControllerには直接的に関連性のないメソッド。
GameModelの初期化処理を行う。このコンストラクタが実行されると、残り時間3600s、初期状態STATE_TITLEが設定され、ゲームの残り時間を管理するタイマーとPlanarityのグラフを管理するためマネージャのインスタンスが作成される。
## タイマー関連メソッド
```java
public synchronized double getTimeRemaining()
```
**ViewやControllerに関連性があるメソッド。**
残り時間を返す。残り時間はdouble型で返される。Viewはこの情報を用いて、TimerPanelなどで時間系の表示を行うことができる。
```java
public void onTimeChange(double newTime)
public void onTimeOut()
```
ViewやControllerには直接的に関連性のないメソッド。TimerListenerの実装用のメソッドで、残り時間を新しい時間に更新するメソッドとタイマーの時間切れ時の処理メソッド。onTimeOutが実行された段階で、現在の状態がいかなる状態であろうとも、STATE_GAMEOVERに移行することが期待される。
## 状態遷移メソッド
Event型の定義はmodelパッケージ内のEvent.javaに記載される。
```java
public synchronized void setCurrentState(Event event)
```
**ViewやControllerに関連性があるメソッド。**
ゲームの状態を指定したイベントに基づいて遷移させる。
終了イベントSTATE_ENDが指定された時はアプリケーションを正常に終了させる。
指定したイベントが本来進めないイベントであったときは、状態遷移は発生せず、現状態が維持される。  
例えば、STATE_TITLEから直接的にSTATE_GAMECLEARとなるのは異常であるため、現状態がSTATE_TITLEであるときにsetCurrentState(STATE_GAMECLEAR)という処理による遷移は許可されず、現状態はSTATE_TITLEのままとなる。
詳しい設計については、StateMachine.javaに記載されるコードを閲覧してほしい。
```java
public synchronized Event getCurrentState()
```
**ViewやControllerに関連性があるメソッド。**
現在のゲームの状態を返すメソッド。返されるのはEvent型である。
## グラフ操作メソッド
これはPlanarityPanelで操作する用のメソッドなので直接的にViewやControllerには関連性がない可能性が高いので、必要がなければ次のセクションに進んでほしい。
```java
public List<Point> getNodes()
```
パズルのノード（点）リストを取得する。

```java
public void moveNode(int index, Point newPosition)
指定したノードを新しい位置に移動する。
```
```java
public boolean isPuzzleSolved()
パズルが解けたかどうかを判定する。今後パズルが増えるなどにより仕様が変わる可能性がある。
```
```java
public void recreatePuzzle()
新しいパズルを再生成する。
```
```java
public List<Edge> getEdges()
パズルのエッジ（線）リストを取得する。
```
```java
public List<Edge> getIntersectingEdges()
交差しているエッジのリストを取得する。
```
## 爆弾解除コードの操作
```java
public void inputCode(int input)
```
(ボタンに対応した)入力1桁を受け取り、状態に適した保存場所(内部的にはint配列)に数字を保存する。保存される数字は最大で4桁で、それ以上の入力は無視される。注意：GameModel内部の状態が爆弾解除可能な場面かどうかを参照し処理している。  
例：
初期化後のmodelから適切な操作で状態を1Fの爆弾にしたとする。この時、
model.inputCode(3)が実行されるとkey[1]が0の状態から3に変更される。さらに、この状態でmodel.inputCode(2)を実行すると、key[1]は32になる。
```
初期状態：
currentState = STATE_1F_BOMB
処理によるkey[1]の動き：
0
↓ model.inputCode(3)
3
↓ model.inputCode(2)
32
↓ model.inputCode(5)
325
↓ model.inputCode(2)
3252
↓ model.inputCode(2)
3252
↓ model.inputCode(2)
3252
↓ model.resetCode()
0
```
```java
public void resetCode()
```
現在のゲーム状態に対応する爆弾解除コードをリセットする
リセットボタンを用意し、ユーザが解除コードの入力をキャンセルした際に呼び出す、などの利用方法が考えられる。
```java
public int getcurrentCode()
```
現在のゲーム状態に対応するユーザの入力状態を取得する
爆弾に入力した数字をユーザーに提示するときとかに使う。
```java
public boolean disarmBomb()
```
現在のゲーム状態に対応する爆弾の解除を試み、成功か失敗かbooleanで伝える。Controller, Viewは内部的な仕様を気にする必要はない。disarmBomb()を使えば状態に対応した爆弾の解除を試せると捉えれば良い。

## 通知メソッド
```java
private void notifyTimeChange()
```
タイマーの変化を監視しているオブザーバーに通知
```java
private void notifyStateChange()
```
状態の変化を監視しているオブザーバーに通知
