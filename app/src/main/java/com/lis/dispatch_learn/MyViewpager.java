package com.lis.dispatch_learn;

import android.content.Context;
import android.graphics.Matrix;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

public class MyViewpager extends ViewPager {
    public MyViewpager(@NonNull Context context) {
        super(context);
    }

    int mLastX;
    int mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                getParent().requestDisallowInterceptTouchEvent(true);//请求不允许中断
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) { //左右滑
                    getParent().requestDisallowInterceptTouchEvent(false);//允许中断
                }
                break;
        }
        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
               mLastX= (int) ev.getX();
               mLastY= (int) ev.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                if (Math.abs(deltaX) > Math.abs(deltaY)) { //左右滑
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
