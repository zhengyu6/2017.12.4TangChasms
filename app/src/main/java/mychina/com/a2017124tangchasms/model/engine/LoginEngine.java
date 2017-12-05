package mychina.com.a2017124tangchasms.model.engine;

import java.io.IOException;
import java.util.HashMap;

import mychina.com.a2017124tangchasms.bean.BaseBean;
import mychina.com.a2017124tangchasms.constant.Constant;
import mychina.com.a2017124tangchasms.http.OkHttpUtils;
import mychina.com.a2017124tangchasms.utils.SignUtils;

/**
 * Created by 张晓辉 on 2017/12/5.
 */

public class LoginEngine {

    //发送验证码 已经停用了
    public BaseBean getVerifyCode(String phone) throws IOException {
        //请求需要的参数有:bs phone ip mac time type;
        String bs = "sms";
        String ip = "223.72.243.62";
        String mac = "b4:0b:44:80:da:a0";
        int type = 1;
        long time = System.currentTimeMillis() / 1000;
        //现在要得到签名，需要time,"",phone,ip
        String sign = SignUtils.encryptSign((time - 444) + "", phone, ip);
        HashMap<String, String> map = new HashMap<>();
        map.put("bs", bs);
        map.put("phone", phone);
        map.put("ip", ip);
        map.put("mac", mac);
        map.put("time", time + "");
        map.put("sign", sign);
        map.put("type", type + "");

        BaseBean bean = OkHttpUtils.getInstance().postBean(Constant.VERIFY_CODE, map);
        return bean;
    }

    //登录逻辑 手机号phone和验证码 sms
    public BaseBean login(String phone, String sms) throws IOException {
        //校检的算法
        String cid = "a52fd360a022f8fd84aafb72ead49588";
        String bs = "login";
        HashMap<String, String> map = new HashMap<>();
        map.put("bs", bs);
        map.put("cid", cid);
        map.put("phone", phone);
        map.put("sms", sms);
        BaseBean bean = OkHttpUtils.getInstance().postBean(Constant.LOGIN, map);
        return bean;
    }
}
