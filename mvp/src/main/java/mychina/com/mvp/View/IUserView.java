package mychina.com.mvp.View;

/**
 * Created by 张晓辉 on 2017/12/11.
 * 2 再来看看view接口
 * 根据需求可知 save按下按钮后 会把id firstname lastname这三个数据传给Model 进行储存，由于
 * 我们是mvp架构 所以是传给presenter层让它和model层打交道所以要有读取view组件里数据的3个方法;
 * load按钮按下后，读取id数值，然后传给p层，让它从m层取出对应的用户名密码，然后返回数据给v层并且显示出来，
 * 所以要有把text set进两个edittext里的方法。
 */

public interface IUserView {
    int getID();

    String getUserName();

    String getUserPassword();

    void setUserName(String userName);

    void setUserPassword(String userPassword);
}
