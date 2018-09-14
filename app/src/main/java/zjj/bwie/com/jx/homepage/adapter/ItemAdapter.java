package zjj.bwie.com.jx.homepage.adapter;

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
import zjj.bwie.com.jx.homepage.bean.HomePageBean;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyItemHolder> {
    private Context context;
    private List<HomePageBean.TuijianBean.ListBean> list;

    public ItemAdapter(Context context, List<HomePageBean.TuijianBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homepage_tuijian_layout, parent, false);
        MyItemHolder myItemHolder = new MyItemHolder(view);
        return myItemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemHolder holder, final int position) {
        holder.img.setImageURI(list.get(position).getImages().split("\\|")[0]);
        holder.tv.setText(list.get(position).getTitle());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyItemHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView tv;
        public MyItemHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            img = itemView.findViewById(R.id.iv);
        }
    }

    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
