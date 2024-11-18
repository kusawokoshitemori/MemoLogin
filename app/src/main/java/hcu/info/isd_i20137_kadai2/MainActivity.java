package hcu.info.isd_i20137_kadai2;

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



public class MainActivity extends AppCompatActivity {

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
        EditText enterPlayerName = findViewById(R.id.enterPlayerName);
        EditText enterPlayerEmail = findViewById(R.id.enterPlayerEmail);
        EditText enterPlayerPassword = findViewById(R.id.enterPlayerPassword);
        Button buttonPlayerSubmit = findViewById(R.id.buttonPlayerSubmit);


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