package com.bwei.erzhoukaomoni.di;

/**
 * Created by Jack Lee on 2018/8/11.
 * name:Juck Lee
 */

public interface IContract  {
    public interface  IView{
        //回显数据
        void showResponseMsg(String failureString);


    }
    public interface  IPersenter<IView>{
        /**
         * 关联
         */
        void attachView(IView iView);


        /**
         * 解绑
         */
        void detachView(IView iView);

        /**
         * 触发请求
         * @param userName
         * @param pwd
         * @param rePwd
         */
        void requestInfo(String userName, String pwd, String rePwd);
        void requestLoginInfo(String name, String password);
        void requesrShowInfo(int page);
    }
    public interface  IModel{

        interface  onCallBack{


            //返回数据
            void responseMsg(String failureString);
        }


        /**
         * 请求数据
         * @param userName
         * @param pwd
         * @param rePwd
         */
       void  requestData(String userName, String pwd, String rePwd,onCallBack onCallBack);
       void  requestLoginData(String name, String password, onCallBack onCallBack);
       void requestShowData(int page, onCallBack onCallBack);
    }
//    public interface LoginModel{
//
//        interface onLoginCallback{
//
//            void responseMsg(String failureString);
//        }
//        void  requestLoginData(onLoginCallback onLoginCallback);
//    }
 }
