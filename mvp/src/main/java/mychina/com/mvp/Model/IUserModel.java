package mychina.com.mvp.Model;

import mychina.com.mvp.Bean.UserBean;

/**
 * Created by 张晓辉 on 2017/12/11.
 * 3 Model接口
 * 同样 Model也需要对这三个字段进行读写操作 并储存在某个载体内。(这不是所关心的)
 * 可以存在内存 文件 数据库或者远程服务器  但对于Presenter及View无影响 定义IUserModel接口:
 *
 */

public interface IUserModel {
    void saveUser(int id, String name, String password);

    UserBean loadUser(int id);
}
