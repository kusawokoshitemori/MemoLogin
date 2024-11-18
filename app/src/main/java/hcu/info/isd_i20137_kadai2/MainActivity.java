package hcu.info.isd_i20137_kadai2;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.MemoMessage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ボタン取得
        Button buttonPlayerSubmitTransition = findViewById(R.id.buttonPlayerSubmitTransition);

        // ボタンを押すと SubmitActivity に遷移する機能
        buttonPlayerSubmitTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
                startActivity(intent);
            }
        });

        // 色々取得
        EditText enterPlayerEmail = findViewById(R.id.enterPlayerEmail);
        EditText enterPlayerPassword = findViewById(R.id.enterPlayerPassword);
        Button buttonPlayerLogin = findViewById(R.id.buttonPlayerLogin);

        // ボタンがクリックされたときの処理
        buttonPlayerLogin.setOnClickListener(view -> {
            // ユーザーが入力した情報を取得
            String email = enterPlayerEmail.getText().toString();
            String password = enterPlayerPassword.getText().toString();

            // 入力が空でないかチェック
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(MainActivity.this, "全てのフィールドを入力してください", Toast.LENGTH_SHORT).show();
                return;
            }

            // ユーザー情報のチェック
            UserManager userManager = UserManager.getInstance();
            User user = userManager.getUserByEmail(email);

            if (user == null) {
                Toast.makeText(MainActivity.this, "ユーザーが見つかりません。", Toast.LENGTH_SHORT).show();
                return;
            }

            // パスワードの照合
            if (!user.getPassword().equals(password)) {
                Toast.makeText(MainActivity.this, "パスワードが正しくありません。", Toast.LENGTH_SHORT).show();
                return;
            }

            // SharedPreferences にメールアドレスを保存
            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("email", email);
            editor.apply();

            // DisplayActivity へ遷移
            Intent intent = new Intent(MainActivity.this, DisplayActivity.class);
            startActivity(intent);
            finish();
        });


        // Intent からメールアドレスを取得
        String email = getIntent().getStringExtra("email");

        // UserManager からユーザー情報を取得
        User user = UserManager.getInstance().getUserByEmail(email);

        // 取得したユーザー情報を表示
        TextView textView = findViewById(R.id.textWelcome);
        if (user != null) {
            textView.setText("Welcome, " + user.getUsername() + "!");
        } else {
            textView.setText("ユーザー情報が見つかりませんでした。");
        }
    }
}