package helloworld.example.administrator.recyclerviewdemo;

import android.content.Context;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.spec.PSource;

/**
 * Created by Administrator on 2016/5/23.
 */
public class StaggedAdapter extends MyAdapter {
    private List<Integer> mHeight = new ArrayList<Integer>();

    public StaggedAdapter(Context context, List<String> data) {
        super(context, data);
        for (int i = 0; i < data.size(); i++) {
            //创建一个随机高度
            int height = (int) (100 + Math.random() * 300);
            mHeight.add(height);
        }
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        holder.tv.setText(mData.get(position));
        layoutParams.height = mHeight.get(position);
        holder.itemView.setLayoutParams(layoutParams);
        setItemClick(holder,position);
    }
}
