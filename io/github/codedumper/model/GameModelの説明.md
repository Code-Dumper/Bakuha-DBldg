# GameModelの役割
GameModelは、ゲーム内での操作に必要な情報である
- ゲームの残り時間 
- 現在どこにいるのかを管理する状態
- 爆弾の解除状況(オープンにされているのは、全ての爆弾が解除されたかどうかであり、ViewやController側には各爆弾の解除状態はオープンにされていない)(オープンにすることは可能だが、必要がなければ実装はしない)
- 爆弾に入力されているコード(番号で、整数4桁)の状態
- Planarity実装用のグラフのノードとエッジ
を管理し、その情報全てを提供するクラス。この情報の管理は全てGameModel側で処理されるのではなく、bombパッケージ内のBombManagerやplanarityパッケージ内のGraphManagerが補助的に用いられることで処理されている。  
コンストラクタが実行されると、残り時間3600s、初期状態STATE_TITLEが設定され、ゲームの残り時間を管理するタイマーとPlanarityのグラフを管理するためマネージャのインスタンスが作成される。  
View、ManagerはこのGameModelにアクセスすることで、直感的な規則に従って必ず残り時間や現在の状態を取得、変更ができると仮定して良い。(1Fから2Fの移動はできるが、1Fから4Fの部屋に行くことはできない、というようなのが直感的な規則ととらえてここでは記載している)  
ゲームの残り時間はdouble型、現在どこにいるのかはenumのEvent型、爆弾の解除状況はboolean型、爆弾に入力されているコードはint型で提供される。
その他の詳細な設計についてはGameModelを確認してほしい。  

# メソッドの説明

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
初期化後のmodelから適切な操作で状態を1Fの爆弾にしたとする。この時、model.inputCode(3)が実行されるとkey[1]が0の状態から3に変更される。さらに、この状態でmodel.inputCode(2)を実行すると、key[1]は32になる。
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