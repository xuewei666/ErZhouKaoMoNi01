package com.bwei.erzhoukaomoni.ui.wethg;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Jack Lee on 2018/8/11.
 * name:Juck Lee
 */

public class myListView  extends ListView {
    public myListView(Context context) {
        super(context);
    }

    public myListView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public myListView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, i);
    }
}
