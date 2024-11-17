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



        // IntentからUserオブジェクトを取得
        Intent intent = getIntent();
        User user = intent.getParcelableExtra("user");

        // 取得したUser情報を表示
        if (user != null) {
            TextView textView = findViewById(R.id.textWelcome);
            textView.setText("Welcome, " + user.getUsername() + "!");
        }
    }
}