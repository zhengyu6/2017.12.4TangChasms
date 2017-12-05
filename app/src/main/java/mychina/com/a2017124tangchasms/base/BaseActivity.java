package mychina.com.a2017124tangchasms.base;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by 张晓辉 on 2017/12/4.
 */

public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setPresenter();
    }

    //强制让子类去设置presenter
    public abstract void setPresenter();
}
