package mychina.com.mvp.Model;

import android.util.SparseArray;

import mychina.com.mvp.Bean.UserBean;

/**
 * Created by 张晓辉 on 2017/12/11.
 * 数据存储的模型层 只需要考虑怎么把数据存起来
 */

public class UserModel implements IUserModel {
    //android的优化版hashmap 数据千条以下的时候效率高
    private SparseArray<UserBean> array = new SparseArray<>();

    @Override
    public void saveUser(int id, String name, String password) {
        //存入array
        array.append(id, new UserBean(name, password));

    }

    @Override
    public UserBean loadUser(int id) {
        if (array.indexOfKey(id) >= 0) {
            return array.get(id);
        } else
            return null;
    }
}
