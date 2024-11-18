package hcu.info.isd_i20137_kadai2;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private static UserManager instance;
    private List<User> userList;

    // シングルトンのインスタンスを取得
    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    // コンストラクタ（プライベート）
    private UserManager() {
        userList = new ArrayList<>();
    }

    // ユーザーを追加
    public void addUser(User user) {
        userList.add(user);
    }

    // メールアドレスでユーザーを検索
    public User getUserByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    // 全ユーザーの取得
    public List<User> getAllUsers() {
        return userList;
    }
}