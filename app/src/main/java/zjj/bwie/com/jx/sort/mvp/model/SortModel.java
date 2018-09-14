package zjj.bwie.com.jx.sort.mvp.model;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import zjj.bwie.com.jx.homepage.bean.HomePageBean;
import zjj.bwie.com.jx.homepage.mvp.view.HomePageContract;
import zjj.bwie.com.jx.sort.bean.ChildSortBean;
import zjj.bwie.com.jx.sort.bean.SortBean;
import zjj.bwie.com.jx.sort.mvp.view.SortContract;
import zjj.bwie.com.jx.utils.BaseApiInterface;
import zjj.bwie.com.jx.utils.RetrofitUtils;

public class SortModel implements SortContract.ISortModel {
    @Override
    public void requestData(final OnRequestListenter listenter) {
        BaseApiInterface api = RetrofitUtils.getInstence().getApi();

        Observable<SortBean> sortBean = api.getSortBean();
        sortBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SortBean>() {
                    @Override
                    public void accept(SortBean sortBean) throws Exception {
                        listenter.onSortSuccess(sortBean);
                    }
                });

    }

    @Override
    public void requestChildData(int cid, final OnRequestChildListenter Childlistenter) {

        BaseApiInterface api = RetrofitUtils.getInstence().getApi();
        Observable<ChildSortBean> childShrtBean = api.getChildShrtBean(cid);
        childShrtBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ChildSortBean>() {
                    @Override
                    public void accept(ChildSortBean childSortBean) throws Exception {
                        Childlistenter.onChildSortSuccess(childSortBean);
                    }
                });

    }
}
