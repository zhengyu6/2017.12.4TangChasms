package mychina.com.mvp.View;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mychina.com.mvp.Presenter.IUserPresenter;
import mychina.com.mvp.Presenter.UserPresenter;
import mychina.com.mvp.R;

public class MainActivity extends AppCompatActivity implements IUserView {
    @BindView(R.id.user_id)
    EditText userId;

    @BindView(R.id.user_name)
    EditText userName;

    @BindView(R.id.user_password)
    EditText userPassword;

    @BindView(R.id.save_info_button)
    Button buttonSave;

    @BindView(R.id.load_info_button)
    Button buttonLoad;
    IUserPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //获取p层的接口实例，并且传入此v层,为了调用p层里的实现业务逻辑的方法
        presenter=new UserPresenter(this);
    }
    @OnClick(R.id.save_info_button)
    void save(){
        presenter.savaUser();
    }

    @OnClick(R.id.load_info_button)
    void load(){
        presenter.loadUser();
    }
    @Override
    public int getID() {
        return Integer.parseInt(userId.getText().toString());
    }

    @Override
    public String getUserName() {
        return userName.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return userPassword.getText().toString();
    }

    @Override
    public void setUserName(String user) {
        userName.setText(user);

    }

    @Override
    public void setUserPassword(String pass) {
        userPassword.setText(pass);
    }
}
