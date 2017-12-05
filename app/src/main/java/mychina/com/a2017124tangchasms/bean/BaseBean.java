package mychina.com.a2017124tangchasms.bean;

/**
 * Created by 张晓辉 on 2017/12/5.
 */

public class BaseBean<T> {
    /**
     * code : 200
     * obj : {"phone":"13511061487","sms":"0325"}
     */

    private int code;
    private T obj;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }
}
