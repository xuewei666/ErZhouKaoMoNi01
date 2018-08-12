package com.bwei.erzhoukaomoni.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwei.erzhoukaomoni.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * Created by Jack Lee on 2018/8/12.
 * name:Juck Lee
 */

public class ErWMActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_scan;
    private EditText et_QRCode;
    private Button btn_generate;
    private ImageView iv_showQRCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_er);
        initView();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initView() {
        btn_scan = (Button) findViewById(R.id.btn_scan);
        et_QRCode = (EditText) findViewById(R.id.et_QRCode);
        btn_generate = (Button) findViewById(R.id.btn_generate);
        iv_showQRCode = (ImageView) findViewById(R.id.iv_showQRCode);

        btn_scan.setOnClickListener(this);
        btn_generate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scan:
                new IntentIntegrator(this).initiateScan();
                break;
            case R.id.btn_generate:
                try {
                    String responseString = et_QRCode.getText().toString();
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(responseString, BarcodeFormat.QR_CODE, 400, 400);
                    iv_showQRCode.setImageBitmap(bitmap);
                } catch (Exception e) {

                }
                break;
        }
    }


}
