package com.bwei.erzhoukaomoni.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.erzhoukaomoni.R;
import com.bwei.erzhoukaomoni.data.persenter.PresentImpl;
import com.bwei.erzhoukaomoni.di.IContract;

/**
 * Created by Jack Lee on 2018/8/11.
 * name:Juck Lee
 */

public class LoginActivity extends AppCompatActivity implements IContract.IView, View.OnClickListener {
    private EditText login_name;
    private EditText login_password;

    private Button btn_login;
    private IContract.IPersenter<IContract.IView> present;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        initView();
        //链接p层
        present = new PresentImpl();
        present.attachView(this);
    }

    @Override
    public void showResponseMsg(final String failureString) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, failureString, Toast.LENGTH_SHORT).show();
                if (failureString.equals("登录成功")){
                    Intent intent = new Intent(LoginActivity.this,ShowActivity.class);
                    startActivity(intent);

                }

            }
        });
    }

    private void initView() {
        login_name = (EditText) findViewById(R.id.login_name);
        login_password = (EditText) findViewById(R.id.login_password);

        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                String name = login_name.getText().toString();
                String password = login_password.getText().toString();
                present.requestLoginInfo(name,password);
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        present.detachView(this);
    }
}

