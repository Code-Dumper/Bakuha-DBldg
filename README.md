## 開発フロー
Github Flowを使います。
調べたらわかりやすい記事がたくさん出てくるので、ここでは省略します。
## (変更をPull Requestとしてオンライン上にあげてレビュー待ちにする)

作業をして、ファイルが完成したら、そのファイルをオンライン上にあげます。この手続きについては
https://docs.github.com/ja/desktop/making-changes-in-a-branch/committing-and-reviewing-changes-to-your-project-in-github-desktop
を読んでください。

## PRのレビュー

https://qiita.com/obscure723/items/5265556d1b89e77c456b
を読んでください

## jarファイルの作成

jarファイルを作って実行したい場合、基本的なjar作成のコマンドをまとめたシェルファイルが用意されています。(現状Mac以外の動作を確認していません)build_mac.shを実行してください。
```
./build_mac.sh
```
# ファイル名の指定
各classファイルはアッパーキャメルケース(ThisApplePenなど、各単語の先頭を大文字にする)で運用します。

各画像ファイルはローワーキャメルケースで、pngが望ましいファイル形式とします。ただし、jpg、jpegも許容するものとします。
## コードの可読性についての推奨事項
# 1. コメントを適切に書く
必要な箇所や、意味が明確でないところにはコメントを書きましょう。
# 2.命名規則を統一する。
・変数名や関数名は意味がわかるものにしましょう。  
・わかりやすい例：userDataやprocessOrderなど。
・わかりにくい、混乱を生む例
```java
public void isTrue{
  this.result = 1 + 2;
}
```
# 3.不要なコードは削除する。
# 4.インデントやフォーマットを整える。
・おそらく当たり前なので省略します。

# 使用フォントに関して
このプログラムでは
[DS-DIGI](https://www.dafont.com/ds-digital.font)
[x12y16pxMaruMonica](https://hicchicc.github.io/00ff/)
を利用させていただいています。これらのフォントの著作権は製作者様にあります。
# 参考リンク

Minecraftのプラグイン開発
https://github.com/GiganticMinecraft/SeichiAssist
データ構造や技術を集めたリポジトリ
https://qiita.com/baby-degu/items/6c0c73a1e79644ebbb1a
