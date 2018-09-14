package zjj.bwie.com.jx.sort.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import zjj.bwie.com.jx.R;
import zjj.bwie.com.jx.sort.bean.ChildSortBean;

public class MyChildSortItemadapter extends RecyclerView.Adapter<MyChildSortItemadapter.MyViewHolder> {

    private Context context;
    private List<ChildSortBean.DataBean.ListBean> list;

    public MyChildSortItemadapter(Context context, List<ChildSortBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sort_child_item_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_sort_child_item.setText(list.get(position).getName());
        holder.img_sort_child_item.setImageURI(list.get(position).getIcon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_sort_child_item;
        private final SimpleDraweeView img_sort_child_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_sort_child_item = itemView.findViewById(R.id.tv_sort_child_item);
            img_sort_child_item = itemView.findViewById(R.id.img_sort_child_item);
        }
    }
}
