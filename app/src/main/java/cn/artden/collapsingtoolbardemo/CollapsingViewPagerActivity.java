package cn.artden.collapsingtoolbardemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CollapsingViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing_view_pager);


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(100);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                switch (position) {
                    case 0:
                        return new CollapsingFragment();
                    case 1:
                        return new CollapsingFragment();
                    case 2:
                        return new CollapsingFragment();

                    default:
                        return new BlankFragment();
                }

            }

            @Override
            public int getCount() {
                return 3;
            }
        });

    }
}
