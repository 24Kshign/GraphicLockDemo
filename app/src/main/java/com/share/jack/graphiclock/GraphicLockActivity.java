package com.share.jack.graphiclock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.share.jack.graphiclock.widget.GraphicLockView;

public class GraphicLockActivity extends Activity implements GraphicLockView.OnGraphicLockListener {

    private TextView mTvIno;
    private GraphicLockView mGlGraphicLockView;
    private boolean isFirstSetPwd;
    private String mPassword;   //记录第一次绘制的密码
    private int type = 0;   //判断时登录还是设置密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphic_lock);

        initView();
    }

    private void initView() {
        mTvIno = (TextView) findViewById(R.id.agl_tv_info);
        mGlGraphicLockView = (GraphicLockView) findViewById(R.id.agl_gl_lock);
        isFirstSetPwd = true;
        mTvIno.setText("请绘制解锁图案");

        mGlGraphicLockView.setOnGraphicLockListener(this);
    }

    @Override
    public void setPwdSuccess(String password) {
        if (type == 0) {   //设置密码
            if (isFirstSetPwd) {
                mTvIno.setText("再次绘制图案进行确认");
                mPassword = password;
                isFirstSetPwd = false;
            } else {
                if (mPassword.equals(password)) {
                    Log.d("GraphicLockActivity--->", "password====" + mPassword);
                    Toast.makeText(this, "设置密码成功", Toast.LENGTH_SHORT).show();
                    mTvIno.setText("你可以登录了");
                    type = 1;
                } else {
                    Toast.makeText(this, "两次设置的密码不一致,请重试", Toast.LENGTH_SHORT).show();
                }
            }
        } else if (type == 1) {  //登录
            if (mPassword.equals(password)) {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LuckyActivity.class));
                finish();
            } else {
                Toast.makeText(this, "密码错误,登录失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isFirstSetPwd = true;
        mPassword = "";
    }

    @Override
    public void setPwdFailure() {
        if (type == 0) {
            mTvIno.setText("请至少连接四个点");
            Toast.makeText(this, "密码过短,设置失败", Toast.LENGTH_SHORT).show();
        } else if (type == 1) {
            mTvIno.setText("你可以登录了");
            Toast.makeText(this, "密码错误,登录失败", Toast.LENGTH_SHORT).show();
        }
    }
}
