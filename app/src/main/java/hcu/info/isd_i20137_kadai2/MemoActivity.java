package hcu.info.isd_i20137_kadai2;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class MemoActivity extends AppCompatActivity {
    private String email;

    // ファイルを保存
    private void saveToFile(String email, String memo) {
        // 保存する内容を作成
        String data = "Email: " + email + "\nMemo: " + memo + "\n\n";

        // ファイル名を指定
        String fileName = "memo.txt";

        try (FileOutputStream fos = openFileOutput(fileName, MODE_APPEND)) {
            // データを書き込む
            fos.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "ファイル保存に失敗しました", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_memo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.MemoMessage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ボタンの取得
        Button buttonDisplayBack = findViewById(R.id.buttonDisplayBack);


        // ボタンクリック時に MemoActivity に遷移
        buttonDisplayBack.setOnClickListener(view -> {
            Intent intent = new Intent(MemoActivity.this, DisplayActivity.class);
            startActivity(intent);
        });



        EditText memoContent = findViewById(R.id.memoContent);
        Button buttonMemoSubmit = findViewById(R.id.buttonMemoSubmit);
        TextView emailTextView = findViewById(R.id.emailTextView);






        // ボタンがクリックされたときの処理
        buttonMemoSubmit.setOnClickListener(view -> {
            // ユーザーが入力した情報を取得
            String memo = memoContent.getText().toString();

            // 入力が空でないかチェック
            if (memo.isEmpty()) {
                Toast.makeText(MemoActivity.this, "メモを入力して下さい", Toast.LENGTH_SHORT).show();
                return;
            }


            // SharedPreferences からメールアドレスを取得
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String email = prefs.getString("email", null);



            // メールアドレスを表示
            if (email != null) {
                emailTextView.setText(email);
            }

            // メモとメールアドレスをファイルに保存
            saveToFile(email, memo);

            // 保存完了のメッセージ
            Toast.makeText(MemoActivity.this, "メモが保存されました", Toast.LENGTH_SHORT).show();
        });



    }
}