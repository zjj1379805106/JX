package zjj.bwie.com.jx.homepage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import zjj.bwie.com.jx.R;
import zjj.bwie.com.jx.homepage.bean.HomePageBean;
import zjj.bwie.com.jx.sort.bean.SortBean;

public class KPFLAdapter extends RecyclerView.Adapter<KPFLAdapter.MyItemHolder>{
    private Context context;
    private List<SortBean.DataBean> sortdata;


    public KPFLAdapter(Context context, List<SortBean.DataBean> sortdata) {
        this.context = context;
        this.sortdata = sortdata;
    }

    @NonNull
    @Override
    public KPFLAdapter.MyItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.homepage_kpfl, parent, false);
        KPFLAdapter.MyItemHolder myItemHolder = new KPFLAdapter.MyItemHolder(view);
        return myItemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyItemHolder holder, int position) {

        holder.img_kpfl.setImageURI(sortdata.get(position).getIcon());
        holder.tv_kpfl.setText(sortdata.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return 16;
    }

    class MyItemHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img_kpfl;
        private final TextView tv_kpfl;
        public MyItemHolder(View itemView) {
            super(itemView);
            tv_kpfl = itemView.findViewById(R.id.tv_kpfl);
            img_kpfl = itemView.findViewById(R.id.img_kpfl);
        }
    }

}
