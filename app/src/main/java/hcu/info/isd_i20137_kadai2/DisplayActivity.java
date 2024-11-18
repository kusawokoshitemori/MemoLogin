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
import android.widget.TextView;

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

        // ボタンクリック時に ログアウト する
        buttonLogout.setOnClickListener(view -> {
            Intent intent = new Intent(DisplayActivity.this, MainActivity.class);
            startActivity(intent);
        });

        emailTextView = findViewById(R.id.emailTextView);

        // SharedPreferences からメールアドレスを取得
        SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String email = prefs.getString("email", null);

        // メールアドレスを表示
        if (email != null) {
            emailTextView.setText(email);
        }
    }
}