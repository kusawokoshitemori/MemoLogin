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
import android.widget.Toast;
import android.widget.EditText;



public class SubmitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_submit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.MemoMessage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ボタン取得
        Button buttonHomeBack = findViewById(R.id.buttonHomeBack);

        EditText inputPlayerName = (EditText) findViewById(R.id.inputPlayerName);
        EditText inputPlayerEmail = (EditText) findViewById(R.id.inputPlayerEmail);
        EditText inputPlayerPassword = (EditText) findViewById(R.id.inputPlayerPassword);
        Button buttonPlayerSubmit = findViewById(R.id.buttonPlayerSubmit);


        // ボタンを押すと MainActivity に遷移する機能
        buttonHomeBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        // ボタンがクリックされたときの処理
        buttonPlayerSubmit.setOnClickListener(view -> {
            // ユーザーが入力した情報を取得
            String username = inputPlayerName.getText().toString();
            String email = inputPlayerEmail.getText().toString();
            String password = inputPlayerPassword.getText().toString();

            // 入力が空でないかチェック
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(SubmitActivity.this, "全てのフィールドを入力してください", Toast.LENGTH_SHORT).show();
                return;
            }

            // Userオブジェクトを作成し、情報をセット
            User newUser = new User(username, email, password);

            // ここで新しいUserオブジェクトが作成され、登録処理などを行えるようになります
            // 例えば、新規ユーザー情報をローカルに保存する、または他の処理を行う

            // 次の画面に遷移する例（例えばメイン画面に遷移）
            Intent intent = new Intent(SubmitActivity.this, MainActivity.class);
            startActivity(intent);
            finish();  // 現在のアクティビティを終了
        });
    }
}