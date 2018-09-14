package zjj.bwie.com.jx.homepage;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.SearchView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import zjj.bwie.com.jx.R;
import zjj.bwie.com.jx.SaoActivity;
import zjj.bwie.com.jx.homepage.adapter.KPFLAdapter;
import zjj.bwie.com.jx.homepage.adapter.MiaoshaAdapter;
import zjj.bwie.com.jx.homepage.adapter.MyHomePagerAdapter;
import zjj.bwie.com.jx.homepage.bean.HomePageBean;
import zjj.bwie.com.jx.homepage.mvp.presenter.HomePagePresenter;
import zjj.bwie.com.jx.homepage.mvp.view.HomePageContract;
import zjj.bwie.com.jx.sort.bean.SortBean;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment implements HomePageContract.HomePageView {

    @BindView(R.id.img_sao)
    ImageView imgSao;
    @BindView(R.id.search)
    SearchView search;
    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    Unbinder unbinder;
    private HomePagePresenter homePagePresenter;
    private MyHomePagerAdapter myHomePagerAdapter;


//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            MyHomePagerAdapter.MyViewHolder holder = (MyHomePagerAdapter.MyViewHolder) msg.obj;
//            MyHomePagerAdapter.miaosha(holder);
//            sendEmptyMessageDelayed(0, 1000);
//        }
//
//
//    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        unbinder = ButterKnife.bind(this, view);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        search.clearFocus();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        homePagePresenter = new HomePagePresenter(this);
        homePagePresenter.homePageData();

        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                homePagePresenter.homePageData();
                xRecyclerView.refreshComplete();
            }
            @Override
            public void onLoadMore() {
                xRecyclerView.refreshComplete();
            }
        });
    }

    @Override
    public void getData(HomePageBean homePageBean) {
        myHomePagerAdapter = new MyHomePagerAdapter(getActivity(), homePageBean);
        xRecyclerView.setAdapter(myHomePagerAdapter);
    }

    @Override
    public void getSortData(SortBean sortBean) {
        EventBus.getDefault().postSticky(sortBean);
    }


    @OnClick(R.id.img_sao)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), SaoActivity.class));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
