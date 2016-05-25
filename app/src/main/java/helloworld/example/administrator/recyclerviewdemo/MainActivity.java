package helloworld.example.administrator.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView ;
    private List<String> mData ;
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
        adapter = new MyAdapter(this,mData);
        //1.设置适配器
        recyclerView.setAdapter(adapter);
        //2.设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
//        //3.设置item的分隔线
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        //设置监听
        adapter.setOnItemClickListener(new MyAdapter.OnClickListener() {
            @Override
            public void OnItemClickListener(View view, int position) {
                Toast.makeText(MainActivity.this, "OnClickPosition:" + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnItemLongClickListener(View view, int position) {
                Toast.makeText(MainActivity.this, "OnLongClickPosition:" + position, Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.gridview:
                recyclerView.setLayoutManager(new GridLayoutManager(this,3));
                break;
            case R.id.listview:
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.staggedview:
                Intent intent = new Intent(this,StaggedActivity.class);
                startActivity(intent);
                break;
            case R.id.gridview_hor:
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
                break;
            case R.id.add:
                adapter.addItem(1);
                break;
            case R.id.delete:
                adapter.deleteItem(1);
                break;
        }
        return true;
    }
}
