package hcu.info.isd_i20137_kadai2;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
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


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    // メールアドレスの検証
    public static boolean validateEmail(String email) {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";  // 正規表現
        return email.matches(emailPattern);
    }

    // パスワードの検証
    public static boolean validatePassword(String password) {
        return password.length() >= 6 && password.matches(".*[a-zA-Z].*") && password.matches(".*[0-9].*");
    }

    // Parcelable の実装部分
    protected User(Parcel in) {
        username = in.readString();
        email = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(email);
        dest.writeString(password);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
