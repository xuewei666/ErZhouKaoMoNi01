package com.bwei.erzhoukaomoni.data.model;

import com.bwei.erzhoukaomoni.data.OkhttpUtils;
import com.bwei.erzhoukaomoni.data.bean.Bean;
import com.bwei.erzhoukaomoni.di.IContract;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

/**
 * Created by Jack Lee on 2018/8/11.
 * name:Juck Lee
 */

public class MedelImpl implements IContract.IModel {

    public static final String URL_STRING = "http://www.wanandroid.com/user/register";
    public static final String URL_LOGIN = "http://www.wanandroid.com/user/login";
    public static final String URL_Show = "https://www.zhaoapi.cn/product/searchProducts?keywords=笔记本&page=";
    @Override
    public void requestData(String userName, String pwd, String rePwd, final onCallBack onCallBack) {
        if (userName.equals("") || pwd.equals("") || rePwd.equals("")) {
            onCallBack.responseMsg("用户名不能为空");
            return;
        }
        FormBody formBody = new FormBody.Builder()
                .add("username", userName)
                .add("password", pwd)
                .add("repassword", rePwd).build();
        OkhttpUtils.getInstance().post(URL_STRING, formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String failureString = e.getMessage().toString();
                onCallBack.responseMsg(failureString);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String reponseString = response.body().string();
                Gson gson = new Gson();
                Bean bean = gson.fromJson(reponseString, Bean.class);
                int errorCode = bean.getErrorCode();
                if (errorCode==0){
                    onCallBack.responseMsg("注册成功");

                }else{
                    String errorMsg = bean.getErrorMsg();
                    onCallBack.responseMsg(errorMsg);
                }

            }
        });

    }

    @Override
    public void requestLoginData(String name, String password, final onCallBack onCallBack) {
        if (name.equals("") || password.equals("") ) {
            onCallBack.responseMsg("用户名或密码不能为空");
            return;
        }
        FormBody formBody = new FormBody.Builder()
                .add("username", name)
                .add("password", password).build();
        OkhttpUtils.getInstance().post(URL_LOGIN, formBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String failureString = e.getMessage().toString();
                onCallBack.responseMsg(failureString);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String reponseString = response.body().string();
                Gson gson = new Gson();
                Bean bean = gson.fromJson(reponseString, Bean.class);
                int errorCode = bean.getErrorCode();
                if (errorCode==0){
                    onCallBack.responseMsg("登录成功");

                }else {
                    String errorMsg = bean.getErrorMsg();
                    onCallBack.responseMsg(errorMsg);
                }
            }
        });
    }

    @Override
    public void requestShowData(int page, final onCallBack onCallBack) {
            OkhttpUtils.getInstance().get(URL_Show + page, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    String failureString = e.getMessage().toString();
                    onCallBack.responseMsg(failureString);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String reponseString = response.body().string();
                    onCallBack.responseMsg(reponseString);
                }
            });
    }
}
