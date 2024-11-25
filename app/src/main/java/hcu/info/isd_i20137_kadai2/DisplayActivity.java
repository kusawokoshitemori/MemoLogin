package hcu.info.isd_i20137_kadai2;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {
    private String email;
    private TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.MemoMessage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonAddMemo = findViewById(R.id.buttonAddMemo);

        // ボタンクリック時に MemoActivity に遷移
        buttonAddMemo.setOnClickListener(view -> {
            Intent intent = new Intent(DisplayActivity.this, MemoActivity.class);
            startActivity(intent);
        });

        Button buttonLogout = findViewById(R.id.buttonLogout);

        // ボタンクリック時にログアウトする
        buttonLogout.setOnClickListener(view -> {
            // SharedPreferences を取得
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            // メールアドレスを空文字に設定（ログアウト時）
            editor.putString("email", "");
            editor.apply();

            // MainActivity に遷移
            Intent intent = new Intent(DisplayActivity.this, MainActivity.class);
            startActivity(intent);

            // 現在のアクティビティを終了
            finish();
        });

        emailTextView = findViewById(R.id.emailTextView);

        // SharedPreferences からメールアドレスを取得
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String email = prefs.getString("email", null);

        // UserManager を使ってユーザーを検索
        User user = UserManager.getInstance().getUserByEmail(email);

// ユーザー情報をまとめる
        StringBuilder userInfo = new StringBuilder();

        if (user != null) {
            // ユーザーが見つかった場合
            userInfo.append("ようこそ ").append(user.getName()).append("さん");
        } else {
            // ユーザーが見つからなかった場合
            userInfo.append("ユーザーが見つかりませんでした。ログアウトしてください");
        }

// ユーザー情報を TextView に表示
        emailTextView.setText(userInfo.toString());

        // ボタンを取得
        Button buttonMemoDisplay = findViewById(R.id.buttonMemoDisplay);

        // ボタンを押したときの動作
        buttonMemoDisplay.setOnClickListener(v -> {
            // ファイル名
            String fileName = "memo.txt";
            StringBuilder stringBuilder = new StringBuilder();

            try (FileInputStream fis = openFileInput(fileName);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {
                String line;
                String currentMemoEmail = null;
                StringBuilder currentMemo = new StringBuilder();

                // ファイルを一行ずつ
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Email: ")) {
                        if (currentMemoEmail != null && currentMemoEmail.equals(email)) {
                            stringBuilder.append(currentMemo.toString());
                        }
                        // 新しいメールアドレスに切り替え
                        currentMemoEmail = line.substring(7); // "Email: " を除いた部分の取得
                        currentMemo.setLength(0); // メモをリセット
                    } else {
                        currentMemo.append(line).append("\n");
                    }
                }
                // 最後のメモをチェック
                if (currentMemoEmail != null && currentMemoEmail.equals(email)) {
                    stringBuilder.append(currentMemo.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "ファイルの読み込みに失敗しました", Toast.LENGTH_SHORT).show();
                return;
            }

            // 呼び出した内容の表示
            String memoContent = stringBuilder.toString();
            if (!memoContent.isEmpty()) {
                new AlertDialog.Builder(this)
                        .setTitle("保存されたメモ")
                        .setMessage(memoContent)
                        .setPositiveButton("OK", null)
                        .show();
            } else {
                Toast.makeText(this, "保存されたメモがありません", Toast.LENGTH_SHORT).show();
            }
        });
    }
}