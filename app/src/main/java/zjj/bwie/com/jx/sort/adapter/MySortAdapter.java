package zjj.bwie.com.jx.sort.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import zjj.bwie.com.jx.R;
import zjj.bwie.com.jx.sort.bean.SortBean;


public class MySortAdapter extends RecyclerView.Adapter<MySortAdapter.MyViewHolder> {

    private Context context;
    private SortBean sortBean;
    private int isclick=0;



    public MySortAdapter(Context context, SortBean sortBean) {
        this.context = context;
        this.sortBean = sortBean;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType==1){
            view = LayoutInflater.from(context).inflate(R.layout.sort_layout, parent, false);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.sort_layout_bg, parent, false);
        }
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tv_sort.setText(sortBean.getData().get(position).getName());
        holder.tv_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(sortBean.getData().get(position).getCid());
                isclick=position;
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return sortBean.getData().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position==isclick?0:1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tv_sort;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_sort = itemView.findViewById(R.id.tv_sort);
        }
    }
}
