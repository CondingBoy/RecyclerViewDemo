package helloworld.example.administrator.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现瀑布流
 * Created by Administrator on 2016/5/23.
 */
public class StaggedActivity extends Activity {
    private RecyclerView recyclerView;
    private List<String> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        final MyAdapter adapter = new StaggedAdapter(this,mData);
        //1.设置适配器
        recyclerView.setAdapter(adapter);
        //2.设置布局管理器
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        //长按时删除当前item
        adapter.setOnItemClickListener(new MyAdapter.OnClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {

            }

            @Override
            public void OnItemLongClickListener(View view, int position) {
                mData.remove(position);
                adapter.notifyItemRemoved(position);
            }
        });
    }
    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv);
    }

    private void initData() {
        mData = new ArrayList<String>();
        for(int i = 'A';i<'z';i++){
            mData.add((char)i+"");
        }
    }
}
