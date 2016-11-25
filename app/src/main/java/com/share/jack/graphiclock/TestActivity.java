package com.share.jack.graphiclock;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.widget.TextView;

import com.share.jack.graphiclock.widget.RoundedBackgroundSpan;

/**
 *
 */
public class TestActivity extends Activity {

    private TextView mTvText;
    private String str = "     订 单     您发布的维修订单有最新报价!省略省略省略省略省略省略";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initView();
    }

    private void initView() {
        mTvText = (TextView) findViewById(R.id.at_tv_text);
        SpannableStringBuilder ssb=new SpannableStringBuilder(str);
        ssb.setSpan(new RoundedBackgroundSpan(this), 0, 13, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ssb.setSpan(new AbsoluteSizeSpan(26), 0, 12, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        mTvText.setText(ssb);
    }
}
