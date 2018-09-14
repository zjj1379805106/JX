package zjj.bwie.com.jx.sort;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zjj.bwie.com.jx.R;
import zjj.bwie.com.jx.sort.adapter.MyChildSortAdapter;
import zjj.bwie.com.jx.sort.adapter.MySortAdapter;
import zjj.bwie.com.jx.sort.bean.ChildSortBean;
import zjj.bwie.com.jx.sort.bean.SortBean;
import zjj.bwie.com.jx.sort.mvp.presenter.SortPresenter;
import zjj.bwie.com.jx.sort.mvp.view.SortContract;

/**
 * A simple {@link Fragment} subclass.
 */
public class SortFragment extends Fragment implements SortContract.ISortView {

    @BindView(R.id.recycler_sort)
    RecyclerView recyclerSort;
    @BindView(R.id.recycler_childsort)
    RecyclerView recyclerChildsort;
    Unbinder unbinder;
    private SortPresenter sortPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sort, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sortPresenter = new SortPresenter(this);
        sortPresenter.SortData();
        sortPresenter.ChildSortData(1);
        EventBus.getDefault().register(this);
    }

    @Override
    public void getSortData(SortBean sortBean) {
        recyclerSort.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerSort.setAdapter(new MySortAdapter(getActivity(),sortBean));
    }


    @Override
    public void getChildSortData(ChildSortBean childSortBean) {
        recyclerChildsort.setLayoutManager(new LinearLayoutManager(getContext()));
        MyChildSortAdapter myChildSortAdapter = new MyChildSortAdapter(getActivity(), childSortBean);
        recyclerChildsort.setAdapter(myChildSortAdapter);
        myChildSortAdapter.notifyDataSetChanged();

    }
    @Subscribe
    public void getclickitem(Integer cid){
        Log.i("bbbbb",cid+"");
        sortPresenter.ChildSortData(cid);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
