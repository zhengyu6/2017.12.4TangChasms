package mychina.com.mvp.Bean;

/**
 * Created by 张晓辉 on 2017/12/11.
 * 1 首先我们需要一个userBean 用来保存用户信息。
 */

public class UserBean {
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String user;
    private String password;
    public UserBean(String user, String password) {
        this.user = user;
        this.password = password;
    }



}
