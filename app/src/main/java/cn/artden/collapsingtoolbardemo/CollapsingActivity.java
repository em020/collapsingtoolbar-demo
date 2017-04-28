package cn.artden.collapsingtoolbardemo;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class CollapsingActivity extends AppCompatActivity {

    boolean tester;
    CollapsingToolbarLayout collapsing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarFucker fucker = new StatusBarFucker();
        fucker.setStatusBarColor(Color.TRANSPARENT);
        fucker.fuck(getWindow());

        setContentView(R.layout.collapsing_layout);

        View tv1 = findViewById(R.id.tv1);
        View tv2 = findViewById(R.id.tv2);
        View tv3 = findViewById(R.id.tv3);

        collapsing = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBar = (AppBarLayout) findViewById(R.id.appbar);
        final TextView hello = (TextView) findViewById(R.id.hello);


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tester = !tester;

                StatusBarFucker fucker = new StatusBarFucker();
                fucker.setStatusBarColor(tester ? Color.RED : Color.TRANSPARENT);
                fucker.setUseDarkNotiIcon(tester);
                fucker.setWindowExtend(tester ? 2 : 1);
                fucker.fuck(getWindow());

                /**
                 * 有ABC三个方法：
                 * A=view.setSystemUiVisibility  B=window.setFlags  C=window.setExtraFlags(MIUI)
                 * 他们之间连续调用时，会有未知原因的冲突，导致界面错乱
                 * 这段测试代码是用于测试这个问题
                 *
                 * 对于原生系统，通过A来设置状态栏深色图标，A与B连续调用会有冲突
                 * 对于MIUI，是通过C来设置状态栏深色图标，C与A连续调用会有冲突
                 *
                 * 大概是这二者内部都会导致向UI线程任务队列添加某个（共同的？）message。
                 * 为避免这个问题，这里用post使这二个方法断开。还需要注意的是，经测试发现，
                 * 把window.setFlags放在post中时 效果是完美的，如果把view.setSystemUiVisibility放在post中，
                 * 则状态栏窗口会有偶现的、可见的状态变化（颜色 显隐）
                 *
                 */

            }
        });

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitleMargin(350,0,0,0);
//
//        collapsing.setTitle("HHHHHHHHHH");
//
//        collapsing.setCollapsedTitleTextAppearance(R.style.test_text_apperance_1);
//        collapsing.setExpandedTitleTextAppearance(R.style.test_text_apperance_2);
//
//        collapsing.setExpandedTitleMargin(0,0,0,0);
//        /*collapsing.setCollapsedTitleGravity(Gravity.CENTER | Gravity.BOTTOM);*/
//        collapsing.setExpandedTitleGravity(Gravity.CENTER | Gravity.BOTTOM);

        hello.setAlpha(0f);

        collapsing.setScrimVisibleHeightTrigger(300);
        collapsing.setScrimAnimationDuration(200);

        AppBarLayout.OnOffsetChangedListener mListener = new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(collapsing.getHeight() + verticalOffset < 300) {
                    /*hello.animate().alpha(1).setDuration(200);*/
                    hello.setAlpha(1f);



                } else {
                    /*hello.animate().alpha(0).setDuration(200);*/
                    hello.setAlpha(0f);
                }

                Log.d("edmund", "verticalOffset: " + verticalOffset);

            }
        };

        appBar.addOnOffsetChangedListener(mListener);

    }
}
