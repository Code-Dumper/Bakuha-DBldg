# 開発手順
## これから楽に開発をするために
ようこそ。
この開発ではGitHubを使います。GitHubはオンライン上にコードを保存し、コードの共有をしやすくするツールだと思ってください。このドキュメントは、1人でGitHub Desktopを使い開発を進められるようにするためのドキュメントです。これを読みながら実際に手を動かすことで、将来的に役に立つGitHubの初級スキルを身につけることができます。

開発をするにあたり、オンライン上のコードを自分のPCに持ってくる必要があります。これをfetchあるいはpullと呼ぶことにします。(https://qiita.com/wann/items/688bc17460a457104d7d)

Github Desktop上でこの処理をする手順は次の通りです。
①現在のブランチをmainブランチに合わせる
②Fetch Origin(♻️みたいなマーク)とPull Origin(↓のマーク)を行う
これにより、コードが最新のものに反映されます。

## (ブランチを作る)

githubでは、複数人での開発がしやすくなるよう、ブランチを作ることが推奨されています。そのため、コードを最新の物にしたら、開発をするためのmainのブランチ(分岐)を作ります。

Github Desktopの画面上のブランチ選択のボタン(上にある枝分かれみたいなマーク)をクリックして、上の検索バーに好きな文字列(feature-testなど)を入力してください。
入力したらブランチの作成をクリックします。
この操作をすることで、mainから分岐したブランチが出来ます。
ここで作業をします。
## (変更をPull Requestとしてオンライン上にあげてレビュー待ちにする)

作業をして、ファイルが完成したら、そのファイルをオンライン上にあげます。この手続きについては
https://docs.github.com/ja/desktop/making-changes-in-a-branch/committing-and-reviewing-changes-to-your-project-in-github-desktop
を読んでください。

## PRのレビュー

https://qiita.com/obscure723/items/5265556d1b89e77c456b
を読んでください

## jarファイルの作成

jarファイルを作って実行したい場合、基本的なjar作成のコマンドをまとめたシェルファイルが用意されています。build_win.shかbuild_mac.shを叩いて実行するか、コマンド上で実行してください。
```
./build_mac.sh
```
```
sudo Set-ExecutionPolicy RemoteSigned #必要なら
.\build-win.sh
```
winの場合実行できるかは検証していないので、できなかったら頑張ってください。

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
