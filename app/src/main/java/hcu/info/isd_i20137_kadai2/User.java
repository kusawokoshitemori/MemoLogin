package hcu.info.isd_i20137_kadai2;

public class User {
    private String username;
    private String email;
    private String password;

    // コンストラクタ
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // GetterとSetter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // メールアドレスの検証
    public static boolean validateEmail(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";  // 正規表現
        return email.matches(emailPattern);
    }

    // パスワードの検証
    public static boolean validatePassword(String password) {
        // 6文字以上、数字と文字を含む
        return password.length() >= 6 && password.matches(".*[a-zA-Z].*") && password.matches(".*[0-9].*");
    }
}
