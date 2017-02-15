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
    View mDecor;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarFucker fucker = new StatusBarFucker();
        fucker.setStatusBarColor(Color.TRANSPARENT);
        fucker.fuck(getWindow());

        setContentView(R.layout.collapsing_layout);
        mDecor = getWindow().getDecorView();

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



//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        StatusBarUtil.setStatusBarLightOrDark(getWindow(), tester);
//                    }
//                });
//
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        StatusBarUtil.setStatusBarColor(getWindow(), tester ? Color.RED : Color.TRANSPARENT);
//                    }
//                }, 5);

//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        StatusBarUtil.setStatusBarLightOrDark(getWindow(), true);
//                    }
//                });

            }
        });

//        tv2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tester = !tester;
//
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        StatusBarUtil.setStatusBarColor(getWindow(), tester ? Color.RED : Color.TRANSPARENT);
//                    }
//                });
//                StatusBarUtil.setStatusBarLightOrDark(getWindow(), tester);
//            }
//        });
//
//        tv3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                tester = !tester;
//                StatusBarUtil.setStatusBarLightOrDark(getWindow(), tester);
//                StatusBarUtil.setStatusBarColor(getWindow(), tester ? Color.RED : Color.TRANSPARENT);
//            }
//        });
    }
}
