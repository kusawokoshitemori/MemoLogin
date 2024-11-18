package hcu.info.isd_i20137_kadai2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemoActivity extends AppCompatActivity {

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

        // ボタンがクリックされたときの処理
        buttonMemoSubmit.setOnClickListener(view -> {
            // ユーザーが入力した情報を取得
            String username = memoContent.getText().toString();

            // 入力が空でないかチェック
            if (username.isEmpty()) {
                Toast.makeText(MemoActivity.this, "メモを入力して下さい", Toast.LENGTH_SHORT).show();
                return;
            }

            // ここにjsonファイル送る
        });
    }
}