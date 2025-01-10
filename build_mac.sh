#!/bin/bash

JARNAME='PGM'
# 実行前にエラーが発生した場合、スクリプトを停止
set -e

# Manifest.mf が存在しない場合、作成する
if [ ! -f "Manifest.mf" ]; then
  echo "Manifest.mf が見つかりません。自動生成します。"
  echo "Manifest-Version: 1.0" > Manifest.mf
  echo "Main-Class: io.github.codedumper.view.GameFrame" >> Manifest.mf  # ここで実行するメインクラスを指定
fi

# 全javaファイルをコンパイル
echo "Javaファイルをコンパイル中..."
javac $(find io/github/codedumper/ -name "*.java")

# jarファイルを作成
echo "JARファイルを作成中..."
jar cfm ${JARNAME}.jar Manifest.mf $(find io/github/codedumper/ -name "*.class" -o -name "*.jpg" -o -name "*.jpeg" -o -name "*.png")

# 実行ディレクトリを表示
pwd
echo "に${JARNAME}.jarを作成しました。"
echo "HINT: java -jar ${JARNAME}.jar により実行が可能です。"

