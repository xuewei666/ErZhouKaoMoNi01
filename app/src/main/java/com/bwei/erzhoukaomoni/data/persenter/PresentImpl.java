package com.bwei.erzhoukaomoni.data.persenter;

import android.view.View;

import com.bwei.erzhoukaomoni.data.model.MedelImpl;
import com.bwei.erzhoukaomoni.di.IContract;

import java.lang.ref.WeakReference;

/**
 * Created by Jack Lee on 2018/8/11.
 * name:Juck Lee
 */

public class PresentImpl implements IContract.IPersenter<IContract.IView> {


    IContract.IView iView;
    IContract.IModel  medel;
    private WeakReference<IContract.IView> iViewWeakReference;
    private WeakReference<IContract.IModel> iModelWeakReference;

    @Override
    public void attachView(IContract.IView iView) {
        this.iView = iView;
        medel = new MedelImpl();
        //弱应用包裹v层 和 m层
        iViewWeakReference = new WeakReference<>(iView);
        iModelWeakReference = new WeakReference<>(medel);
    }

    @Override
    public void detachView(IContract.IView iView) {
        iViewWeakReference.clear();
        iModelWeakReference.clear();
    }

    @Override
    public void requestInfo(String userName, String pwd, String rePwd) {
            medel.requestData(userName, pwd, rePwd, new IContract.IModel.onCallBack() {
                @Override
                public void responseMsg(String failureString) {
                    iView.showResponseMsg(failureString);

                }
            });
    }

    @Override
    public void requestLoginInfo(String name, String password) {
        medel.requestLoginData(name,password,new IContract.IModel.onCallBack() {
            @Override
            public void responseMsg(String failureString) {
                iView.showResponseMsg(failureString);
            }
        });
    }

    @Override
    public void requesrShowInfo(int page) {
        medel.requestShowData(page,new IContract.IModel.onCallBack() {
            @Override
            public void responseMsg(String failureString) {
                iView.showResponseMsg(failureString);
            }
        });
    }
}
