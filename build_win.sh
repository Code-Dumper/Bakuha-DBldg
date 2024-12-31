# PGM.jar の作成スクリプト

$JARNAME = 'PGM'

# エラーチェック
$ErrorActionPreference = "Stop"

# Manifest.mf が存在しない場合、作成する
if (-Not (Test-Path "Manifest.mf")) {
    Write-Host "Manifest.mf が見つかりません。自動生成します。"
    @"
Manifest-Version: 1.0
Main-Class: io.github.codedumper.view.GameFrame
"@ | Set-Content -Path "Manifest.mf"
}

# 全javaファイルをコンパイル
Write-Host "Javaファイルをコンパイル中..."
$javaFiles = Get-ChildItem -Recurse -Filter "*.java" -Path "io/github/codedumper/"
javac $javaFiles.FullName

# jarファイルを作成
Write-Host "JARファイルを作成中..."
$classFiles = Get-ChildItem -Recurse -Filter "*.class" -Path "io/github/codedumper/"
$imageFiles = Get-ChildItem -Recurse -Filter "*.jpg" -Path "io/github/codedumper/view/"
$filesToJar = $classFiles.FullName + $imageFiles.FullName
jar cfm "$JARNAME.jar" "Manifest.mf" $filesToJar

# 実行ディレクトリを表示
Get-Location
Write-Host "に$JARNAME.jarを作成しました。"
Write-Host "HINT: java -jar $JARNAME.jar により実行が可能です。"
