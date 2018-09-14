package zjj.bwie.com.jx.sort.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import zjj.bwie.com.jx.R;
import zjj.bwie.com.jx.sort.bean.ChildSortBean;

public class MyChildSortAdapter extends RecyclerView.Adapter<MyChildSortAdapter.MyViewHolder> {

    private Context context;
    private ChildSortBean childSortBean;

    public MyChildSortAdapter(Context context, ChildSortBean childSortBean) {
        this.context = context;
        this.childSortBean = childSortBean;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sort_child_layout, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_sort_child.setText(childSortBean.getData().get(position).getList().get(position).getName());
        holder.recycler_childsort_item.setLayoutManager(new GridLayoutManager(context,3));
        holder.recycler_childsort_item.setAdapter(new MyChildSortItemadapter(context,childSortBean.getData().get(position).getList()));
    }

    @Override
    public int getItemCount() {
        return childSortBean.getData().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_sort_child;
        private final RecyclerView recycler_childsort_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_sort_child = itemView.findViewById(R.id.tv_sort_child);
            recycler_childsort_item = itemView.findViewById(R.id.recycler_childsort_item);
        }
    }
}
