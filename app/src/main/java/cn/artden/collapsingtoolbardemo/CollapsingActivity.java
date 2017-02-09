package cn.artden.collapsingtoolbardemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CollapsingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setStatusBarColor(getWindow(), Color.TRANSPARENT);
        setContentView(R.layout.collapsing_layout);
    }
}
