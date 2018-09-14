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

public class MiaoshaAdapter extends RecyclerView.Adapter<MiaoshaAdapter.MyViewHolder> {

    private Context context;
    private List<HomePageBean.MiaoshaBean.ListBeanX> list;

    public MiaoshaAdapter(Context context, List<HomePageBean.MiaoshaBean.ListBeanX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homepage_miaosha_layout, parent, false);
        MyViewHolder myviewHolder = new MyViewHolder(view);
        return myviewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_miaosha.setText("￥"+list.get(position).getPrice());
        holder.img_miaosha.setImageURI(list.get(position).getImages().split("\\|")[0]);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img_miaosha;
        private final TextView tv_miaosha;

        public MyViewHolder(View itemView) {
            super(itemView);
            img_miaosha = itemView.findViewById(R.id.img_miaosha);
            tv_miaosha = itemView.findViewById(R.id.tv_miaosha);
        }
    }
}
