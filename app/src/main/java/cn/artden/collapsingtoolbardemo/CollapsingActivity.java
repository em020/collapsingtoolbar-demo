package cn.artden.collapsingtoolbardemo;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class CollapsingActivity extends AppCompatActivity {

    boolean tester;

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

    }
}
