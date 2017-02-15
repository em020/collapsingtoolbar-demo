package cn.artden.collapsingtoolbardemo;

import android.graphics.Color;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CollapsingFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatusBarFucker fucker = new StatusBarFucker();
        fucker.setStatusBarColor(Color.TRANSPARENT);
        fucker.fuck(getWindow());

        setContentView(R.layout.activity_collapsing_fragment);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.frame, new CollapsingFragment()).commit();
    }
}
