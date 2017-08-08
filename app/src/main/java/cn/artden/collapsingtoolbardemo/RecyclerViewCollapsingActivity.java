package cn.artden.collapsingtoolbardemo;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class RecyclerViewCollapsingActivity extends AppCompatActivity {

    boolean tester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        StatusBarFucker fucker = new StatusBarFucker();
        fucker.setStatusBarColor(Color.TRANSPARENT);
        fucker.fuck(getWindow());

        setContentView(R.layout.collapsing_layout_with_recycler_view);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new FooAdapter());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RecyclerViewCollapsingActivity.this, "hell", Toast.LENGTH_SHORT).show();


                tester = !tester;

                StatusBarFucker fucker = new StatusBarFucker();
                fucker.setStatusBarColor(tester ? Color.RED : Color.TRANSPARENT);
                fucker.setUseDarkNotiIcon(tester);
                fucker.setWindowExtend(tester ? 2 : 1);
                fucker.fuck(getWindow());
            }
        });
    }



    static class FooAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Context context = parent.getContext();

            return new FooHolder(LayoutInflater.from(context).inflate(R.layout.item_recycler_view, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 20;
        }



        class FooHolder extends RecyclerView.ViewHolder {

            public FooHolder(View itemView) {
                super(itemView);
            }
        }
    }


}
