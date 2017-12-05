package mychina.com.a2017124tangchasms.ui.login;

import android.view.View;

import mychina.com.a2017124tangchasms.model.engine.LoginEngine;

/**
 * Created by 张晓辉 on 2017/12/5.
 */

public class LoginPresenter implements LoginContract.Presenter,View.OnClickListener {
    LoginEngine loginEngine=new LoginEngine();
    //声明view实现类
    LoginContract.LoginView loginView;
    LoginActivity loginActivity;
    public LoginPresenter(LoginContract.LoginView loginView) {
        this.loginView = loginView;
        loginActivity=(LoginActivity)loginView;

    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void requestVerifyCode() {

    }

    @Override
    public void verifySmsCode() {

    }
}
