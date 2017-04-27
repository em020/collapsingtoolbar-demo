package cn.artden.collapsingtoolbardemo;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

/**
 * Created by yzsh-sym on 2017/4/27.
 */

public class TestBehavior extends CoordinatorLayout.Behavior<TextView> {

    public TestBehavior() {
    }

    public TestBehavior(Context context, AttributeSet attrs) {

        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        Log.d("edmund", "layoutDependsOn");
        /*return dependency instanceof AppBarLayout;*/
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        Log.d("edmund", "onDependentViewChanged");
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public void onDependentViewRemoved(CoordinatorLayout parent, TextView child, View dependency) {
        Log.d("edmund", "onDependentViewRemoved");
        super.onDependentViewRemoved(parent, child, dependency);
    }

    @Override
    public boolean onInterceptTouchEvent(CoordinatorLayout parent, TextView child, MotionEvent ev) {
        Log.d("edmund", "onInterceptTouchEvent");
        return super.onInterceptTouchEvent(parent, child, ev);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, TextView child, int layoutDirection) {
        Log.d("edmund", "onLayoutChild");
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onMeasureChild(CoordinatorLayout parent, TextView child, int parentWidthMeasureSpec, int widthUsed, int parentHeightMeasureSpec, int heightUsed) {
        Log.d("edmund", "onMeasureChild");
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed);
    }

    @Override
    public boolean onTouchEvent(CoordinatorLayout parent, TextView child, MotionEvent ev) {
        Log.d("edmund", "onTouchEvent");
        return super.onTouchEvent(parent, child, ev);
    }



}
