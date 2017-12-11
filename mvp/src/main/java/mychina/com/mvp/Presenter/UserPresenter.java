package mychina.com.mvp.Presenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import mychina.com.mvp.Bean.UserBean;
import mychina.com.mvp.Model.IUserModel;
import mychina.com.mvp.Model.UserModel;
import mychina.com.mvp.View.IUserView;

/**
 * Created by 张晓辉 on 2017/12/11.
 */

public class UserPresenter implements IUserPresenter {
    private IUserView userView;
    private IUserModel userModel;
    Context context;

    //将带有具体方法实现的实例upcast成为接口
    public UserPresenter(IUserView userView) {
        this.userView = userView;
        this.userModel = new UserModel();
    }


    //此方法将view层获取的数据存入model层，且只用到了接口里的方法
    @Override
    public void savaUser() {
        if (userView.getUserName() == null || userView.getUserPassword() == null) {
            Toast.makeText(context, "saveUser: 账号或者密码不能为空", Toast.LENGTH_LONG).show();
        } else {
            userModel.saveUser(userView.getID(), userView.getUserName(), userView.getUserPassword());
            Toast.makeText(context, "saveUser: success!", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void loadUser() {
        UserBean use1rBean = userModel.loadUser(userView.getID());
        if (use1rBean != null) {
            userView.setUserName(use1rBean.getUser());
            userView.setUserPassword(use1rBean.getPassword());
        } else Toast.makeText(context, "loadUser: 编号为id的用户数据不存在", Toast.LENGTH_LONG).show();

    }
}
