package helloworld.example.administrator.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * 给RecyclerView写adapter，并提供监听接口
 * Created by Administrator on 2016/5/23.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{
    private Context mContext;
    public List<String> mData;
    private LayoutInflater mInflater;
    private OnClickListener listener;
    public MyAdapter(Context context,List<String> data){
        mContext = context;
        mData = data;
        mInflater = LayoutInflater.from(mContext);
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //这里后两个参数很重要，只用设置后两个参数后，才会应用item自己的宽和高，否则会用wrp_content
        //而且，最后一个参数要传false，表示用item的宽高填充父控件
        View root = mInflater.inflate(R.layout.rv_item,parent,false);
        MyHolder holder = new MyHolder(root);

        return holder;
    }

    @Override
    public void onBindViewHolder( MyHolder holder, int position) {
            holder.tv.setText(mData.get(position));
        //添加监听时，要回传item在布局中的position，而不是这里的position，这个position类似一个固定的position，
        //当加入一个新的item并给定position时，他们的position可能相同
            //这里传入的position是没有用的，因为每个item在显示的时候才会调用这个方法，这时传递的position是固定的，
            //例如原来列表中有两个item，A和B，在显示的时候，A的position是0，B的Position是1，当调用notifyItemInsert(1)
            // 添加一个C,这个方法会调用onBindViewHolder，通过getLayoutPosition得到的Position是1，他们都是固定的，这时点击B和C得到的Position都是1，
            //所以，要在item被点击的时候，通过holder的getlayoutposition方法得到当前的Position
            setItemClick(holder,holder.getLayoutPosition());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        public final TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv);
        }
    }
    public void setOnItemClickListener(OnClickListener listener){
        this.listener = listener;
    }
    //监听器接口
    public interface OnClickListener{
        void OnItemClickListener(View view,int position);
        void OnItemLongClickListener(View view,int position);
    }
    //给item添加监听的方法
    public void setItemClick(final MyHolder holder, final int position){
        if(listener!=null){
            //给item设置监听器
           Log.e("TAG","传入的Position：" + position + "从holder中得到的position：" + holder.getLayoutPosition());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClickListener(holder.itemView,holder.getLayoutPosition());
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.OnItemLongClickListener(holder.itemView,holder.getLayoutPosition());
                    return true;
                }
            });
        }
    }
    //添加item
    public void addItem(int position){
        mData.add(position,"item one");
        //通知系统更新列表
        notifyItemInserted(position);
        //不能使用这个方法，这个方法是没有动画效果的
//        notifyDataSetChanged();

    }
    //删除item
    public void deleteItem(int position){
        mData.remove(position);
        notifyItemRemoved(position);
    }

}
