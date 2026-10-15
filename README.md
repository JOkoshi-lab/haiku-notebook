# 俳句帳アプリ

俳句データの登録・表示・いいねを扱うWebアプリケーションです。画面とDAOを分け、データベースを利用する構成に整理しています。

## 使用技術
Java / Spring Boot / Thymeleaf / Spring JDBC / H2 Database

## 起動方法（Windows）

プロジェクトフォルダをPowerShellで開き、次のコマンドを実行してください。

```powershell
.\mvnw.cmd spring-boot:run
```

アプリのアクセス先：http://localhost:8080/haiku

データベースを使用する作品は、起動時にH2のインメモリデータベースを初期化します。再起動すると、登録内容は初期状態に戻ります。