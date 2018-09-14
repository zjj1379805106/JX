package zjj.bwie.com.jx.homepage.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;
import com.sunfusheng.marqueeview.MarqueeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import zjj.bwie.com.jx.R;
import zjj.bwie.com.jx.homepage.GrideSnapHelper;
import zjj.bwie.com.jx.homepage.bean.HomePageBean;
import zjj.bwie.com.jx.sort.bean.SortBean;

public class MyHomePagerAdapter extends XRecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private final int BANNER_VIEW=0;
    private final int CLASSES_VIEW=1;
    private final int MIAOSHA_VIEW=2;
    private final List<String> bannerList;
    private final List<String> bannernameList;

    private MyViewHolder myViewHolder;


    private Context context;
    private HomePageBean homePageBean;
    private List<SortBean.DataBean> sortdata;


    public MyHomePagerAdapter(Context context, HomePageBean homePageBean) {
        this.context = context;
        this.homePageBean = homePageBean;

        bannerList = new ArrayList<>();
        bannernameList=new ArrayList<>();
        for (HomePageBean.DataBean dataBean : homePageBean.getData()) {
            bannerList.add(dataBean.getIcon());
            bannernameList.add(dataBean.getTitle());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return BANNER_VIEW;
        }else if(position==1){
            return CLASSES_VIEW;
        }else if(position==2){
            return MIAOSHA_VIEW;
        }else {
            return super.getItemViewType(position);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType==BANNER_VIEW){
            view = LayoutInflater.from(context).inflate(R.layout.homepage_banner_layout, parent, false);
            BannerHolder bannerHolder = new BannerHolder(view);
            return bannerHolder;
        }else if(viewType==CLASSES_VIEW){
            view=LayoutInflater.from(context).inflate(R.layout.homepage_classes_layout,parent,false);
            ClassesHolder classesHolder = new ClassesHolder(view);
            return classesHolder;
        }else if(viewType==MIAOSHA_VIEW){
            view = LayoutInflater.from(context).inflate(R.layout.homepage_item_layout, parent, false);
            myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof BannerHolder){
            ((BannerHolder) holder).shouye_banner.setData(bannerList,bannernameList);
            ((BannerHolder) holder).shouye_banner.setmAdapter(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(view).load(bannerList.get(position)).into((ImageView) view);
                }
            });
            ((BannerHolder) holder).shouye_banner.setPageTransformer(Transformer.Default);
            ((BannerHolder) holder).shouye_banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
                @Override
                public void onItemClick(XBanner banner, Object model, int position) {
                    Toast.makeText(context, "您点击了" +bannernameList.get(position), Toast.LENGTH_SHORT).show();
                }
            });
        }else if(holder instanceof ClassesHolder){

            EventBus.getDefault().register(this);


            //HomePageBean.TuijianBean tuijian = homePageBean.getTuijian();
            GridLayoutManager gm = new GridLayoutManager(context, 2);
            gm.setOrientation(GridLayout.HORIZONTAL);
            ((ClassesHolder) holder).recycle_view_kpfl.setLayoutManager(gm);
            ((ClassesHolder) holder).recycle_view_kpfl.setAdapter(new KPFLAdapter(context,sortdata));
            GrideSnapHelper grideSnapHelper = new GrideSnapHelper(4, 2, (Activity) context);
            grideSnapHelper.attachToRecycleView(((ClassesHolder) holder).recycle_view_kpfl);

        }else if(holder instanceof MyViewHolder){
            //秒杀
            miaosha((MyViewHolder) holder);
//            Message message = new Message();
//            message.obj=holder;
//            message.what=0;
//            handler.sendMessage(message);

            //跑马灯
            List<String> pmdlist = new ArrayList<>();
            pmdlist.add("欢迎来到我的京西");
            pmdlist.add("云想衣裳花想容，春风拂槛露华浓。");
            pmdlist.add("回眸一笑百媚生，六宫粉黛无颜色。");
            pmdlist.add("(づ￣3￣)づ╭❤～");
            ((MyViewHolder) holder).pmd_view.startWithList(pmdlist);
            //数据展示
            List<HomePageBean.MiaoshaBean.ListBeanX> list_miaosha = homePageBean.getMiaosha().getList();
            List<HomePageBean.TuijianBean.ListBean> list_tuijian = homePageBean.getTuijian().getList();
            LinearLayoutManager ms = new LinearLayoutManager(context);
            ms.setOrientation(LinearLayoutManager.HORIZONTAL);
            ((MyViewHolder) holder).recycle_view_miaosha.setLayoutManager(ms);
            ((MyViewHolder) holder).recycle_view_miaosha.setAdapter(new MiaoshaAdapter(context,list_miaosha));

            ((MyViewHolder) holder).recycle_view_tuijian.setLayoutManager(new GridLayoutManager(context,2));
            ((MyViewHolder) holder).recycle_view_tuijian.setAdapter(new ItemAdapter(context,list_tuijian));


        }
    }

    public static void miaosha(@NonNull MyViewHolder holder) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        String format = df.format(curDate);
        StringBuffer buffer = new StringBuffer();
        String substring = format.substring(0, 11);
        buffer.append(substring);
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour % 2 == 0) {
            holder.tv_miaosha_time.setText(hour + "点场");
            buffer.append((hour + 2));
            buffer.append(":00:00");
        } else {
            holder.tv_miaosha_time.setText((hour - 1) + "点场");
            buffer.append((hour + 1));
            buffer.append(":00:00");
        }
        String totime = buffer.toString();
        try {
            Date date = df.parse(format);
            Date date1 = df.parse(format);
            long defferenttime = date.getTime() - date1.getTime();
            long days = defferenttime / (1000 * 60 * 60 * 24);
            long hours = (defferenttime - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minute = (defferenttime - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            long seconds = defferenttime % 60000;
            long second = Math.round((float) seconds / 1000);
            holder.tv_miaosha_shi.setText("0" + hours + "");
            if (minute >= 10) {
                holder.tv_miaosha_minter.setText(minute + "");
            } else {
                holder.tv_miaosha_minter.setText("0" + minute + "");
            }
            if (second >= 10) {
                holder.tv_miaosha_second.setText(second + "");
            } else {
                holder.tv_miaosha_second.setText("0" + second + "");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void handleData(SortBean sortBean) {
        Log.i("aaaa",sortBean.toString());
        sortdata = sortBean.getData();

    }


    @Override
    public int getItemCount() {
        return 3;
    }


    class BannerHolder extends RecyclerView.ViewHolder{

        private final XBanner shouye_banner;

        public BannerHolder(View itemView) {
            super(itemView);
            shouye_banner = itemView.findViewById(R.id.shouye_banner);
        }
    }
    class ClassesHolder extends RecyclerView.ViewHolder {
        private final RecyclerView recycle_view_kpfl;
        public ClassesHolder(View itemView) {
            super(itemView);
            recycle_view_kpfl=itemView.findViewById(R.id.recycle_view_kpfl);
        }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView recycle_view_tuijian;
        private final RecyclerView recycle_view_miaosha;
        private final MarqueeView pmd_view;
        private final TextView tv_miaosha_time;
        private final TextView tv_miaosha_shi;
        private final TextView tv_miaosha_second;
        private final TextView tv_miaosha_minter;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_miaosha_time = itemView.findViewById(R.id.tv_miaosha_time);
            tv_miaosha_shi = itemView.findViewById(R.id.tv_miaosha_shi);
            tv_miaosha_second = itemView.findViewById(R.id.tv_miaosha_second);
            tv_miaosha_minter = itemView.findViewById(R.id.tv_miaosha_minter);

            pmd_view = itemView.findViewById(R.id.pmd_view);
            recycle_view_miaosha = itemView.findViewById(R.id.recycle_view_miaosha);
            recycle_view_tuijian=itemView.findViewById(R.id.recycle_view_tuijian);

        }
    }
}
