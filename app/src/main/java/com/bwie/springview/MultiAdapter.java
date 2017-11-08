package com.bwie.springview;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import butterknife.Bind;
import butterknife.ButterKnife;
//下拉刷新/上拉加载更多的多条目适配器
public class MultiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<String> list;

    public MultiAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //如果viewType的值为偶数，加载FViewHolder类布局条目，否则加载SViewHolder布局条目
        if (viewType == 0) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_fitem, parent, false);
            return new FViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_sitem, parent, false);
            return new SViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //判断当前的ViewHolder所属类型，使用Glide.with加载数据
        if (holder instanceof FViewHolder) {
            FViewHolder fViewHolder = (FViewHolder) holder;
            fViewHolder.itemStextview.setText("条目："+position);
            Glide.with(context).load(list.get(position)).into(fViewHolder.itemSimageview);
        } else {
            SViewHolder sViewHolder = (SViewHolder) holder;
            Glide.with(context).load(list.get(position)).into(sViewHolder.itemSimageview);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class FViewHolder extends RecyclerView.ViewHolder {
        //使用butterKnife必须使用 版本7 第三方依赖   compile 'com.jakewharton:butterknife:7.0.0'
        @Bind(R.id.item_simageview)
        ImageView itemSimageview;
        @Bind(R.id.item_stextview)
        TextView itemStextview;

        FViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    class SViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.item_simageview)
        ImageView itemSimageview;
        SViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
