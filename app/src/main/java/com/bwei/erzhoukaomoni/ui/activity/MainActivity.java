package com.bwei.erzhoukaomoni.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.erzhoukaomoni.R;
import com.bwei.erzhoukaomoni.data.persenter.PresentImpl;
import com.bwei.erzhoukaomoni.di.IContract;

public class MainActivity extends AppCompatActivity implements IContract.IView, View.OnClickListener {


    private EditText et_userName;
    private EditText et_password;
    private EditText et_rePassword;
    private Button btn_register;

    private IContract.IPersenter<IContract.IView> present;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        present = new PresentImpl();
        present.attachView(this);
    }


    @Override
    public void showResponseMsg(final String failureString) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, failureString, Toast.LENGTH_SHORT).show();
                if (failureString.equals("注册成功")){
                    Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);

                }

            }
        });
    }


    private void initView() {
        et_userName = (EditText) findViewById(R.id.et_userName);
        et_password = (EditText) findViewById(R.id.et_password);
        et_rePassword = (EditText) findViewById(R.id.et_rePassword);
        btn_register = (Button) findViewById(R.id.btn_register);

        btn_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                String userName = et_userName.getText().toString();
                String Pwd = et_password.getText().toString();
                String rePwd = et_rePassword.getText().toString();
                //v层触发P层
                present.requestInfo(userName,Pwd,rePwd);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        present.detachView(this);
    }
}
