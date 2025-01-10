@echo off
set JARNAME=PGM
chcp 65001
rem エラーチェック
setlocal enabledelayedexpansion

rem Manifest.mf が存在しない場合、作成する
if not exist "Manifest.mf" (
    echo Manifest.mf が見つかりません。自動生成します。
    echo Manifest-Version: 1.0> Manifest.mf
    echo Main-Class: io.github.codedumper.view.GameFrame>> Manifest.mf
)

rem 全javaファイルをコンパイル
echo Javaファイルをコンパイル中...
for /r io\github\codedumper %%f in (*.java) do (
    javac "%%f"
)

rem jarファイルを作成
echo JARファイルを作成中...
set filesToJar=
for /r io\github\codedumper %%f in (*.class) do (
    set filesToJar=!filesToJar! "%%f"
)
for /r io\github\codedumper\view %%f in (*.jpg *.jpeg *.png) do (
    set filesToJar=!filesToJar! "%%f"
)
jar cfm "%JARNAME%.jar" "Manifest.mf"!filesToJar!

rem 実行ディレクトリを表示
cd
echo %cd%\%JARNAME%.jar を作成しました。
echo HINT: java -jar %JARNAME%.jar により実行が可能です。
pause
