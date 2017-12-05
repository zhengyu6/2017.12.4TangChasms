package mychina.com.a2017124tangchasms.ui.login;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import mychina.com.a2017124tangchasms.R;
import mychina.com.a2017124tangchasms.base.BaseActivity;
import mychina.com.a2017124tangchasms.model.engine.SmsTextWatcher;
import mychina.com.a2017124tangchasms.utils.ToastUtils;

/**
 * Created by 张晓辉 on 2017/12/5.
 */

public class LoginActivity extends BaseActivity implements LoginContract.LoginView {
    public EditText phone;
    public EditText sms;
    public TextView sendsms;
    public Button login;
    //声明presenter
    LoginContract.Presenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        //请求验证码
        requestSms();
    }

    private void requestSms() {
        loginPresenter.requestVerifyCode();
    }

    private void initView() {
        phone = (EditText) findViewById(R.id.phone);
        sms = (EditText) findViewById(R.id.sms);
        sendsms = (TextView) findViewById(R.id.sendsms);
        login = (Button) findViewById(R.id.login);
    }

    @Override
    public void setPresenter() {

    }

    @Override
    public void setLoginButtonEnable(boolean value) {
        //设置登录按钮可用 并且颜色变成红色
        if (value) {
            login.setBackgroundColor(Color.RED);
        } else {
            login.setBackgroundColor(Color.YELLOW);
        }
    }

    @Override
    public void showVerifyError() {
        ToastUtils.show(this, R.string.verifycode_error);
    }

    @Override
    public void showVerifySuccess() {
        //提示校验成功，可以登录了
        ToastUtils.show(this, R.string.verifycode_success);
    }

    @Override
    public void showPhoneNumEmpty() {
        //手机号为空时 点击发送验证码的界面
        ToastUtils.show(this, R.string.phonenum_empty);
    }

    @Override
    public void showPhoneNumError() {
        ToastUtils.show(this, R.string.phonenum_error);

    }

    @Override
    public void listenSmsEditTextStatus() {
        sms.addTextChangedListener(new SmsTextWatcher(new SmsTextWatcher.OnSmsComplete() {
            @Override
            public void onComplete() {
                //让点击登录的按钮可用
                setLoginButtonEnable(true);
            }

            @Override
            public void onEdit() {
                setLoginButtonEnable(false);
            }
        }));

    }
    //本身已经被调用过了
    @Override
    public void setPresenter() {
        loginPresenter=new LoginPresenter(this);
    }
}
