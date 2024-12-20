# 開発手順
## ローカルにコードを持ってくる
私たちがやった作業は全てオンライン上にあります。なので、誰かが作業を行い、マージされたなどで、進度が進んだ場合は、それをひとまず自分の環境に持ってくる必要があります。
Github Desktop上でmainブランチに合わせて、Fetch OriginとPull Originを行ってください。これにより、コードが更新されます
# ファイル名の指定
各classファイルはアッパーキャメルケース(ThisApplePenなど、各単語の先頭を大文字にする)で運用  
各画像ファイルはアッパーキャメルケースで、pngが望ましい  
## コードの可読性についての推奨事項
# 1. コメントを適切に書く
必要な箇所や、意味が明確でないところにはコメントを書きましょう。
```Java
//タイマーの情報と現在地点の情報を提供するクラス。タイマーの情報や現在位置をViewに伝える必要があるため、
//こいつはObservableの性質を持っていなければならない。
public class GameState{
 private String currentPanel;
 private boolean isPaused;
 private int remainingTime;
 private boolean isTimerRunning;
 ...
}
```
# 2.命名規則を統一する。
・変数名や関数名は意味がわかるものにしましょう。  
・例：user_dataやprocess_orderなど。

# 3.不要なコードは削除する
・不要なコードは削除してください。

# 4.インデントやフォーマットを整える。
・おそらく当たり前なので省略します。

# 参考リンク

Minecraftのプラグイン開発
https://github.com/GiganticMinecraft/SeichiAssist
データ構造や技術を集めたリポジトリ
https://qiita.com/baby-degu/items/6c0c73a1e79644ebbb1a

Powered by ChatGPT
