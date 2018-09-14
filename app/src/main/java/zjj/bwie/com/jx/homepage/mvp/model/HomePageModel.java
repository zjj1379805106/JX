package zjj.bwie.com.jx.homepage.mvp.model;

import javax.security.auth.SubjectDomainCombiner;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import zjj.bwie.com.jx.homepage.bean.HomePageBean;
import zjj.bwie.com.jx.homepage.mvp.view.HomePageContract;
import zjj.bwie.com.jx.sort.bean.SortBean;
import zjj.bwie.com.jx.utils.BaseApiInterface;
import zjj.bwie.com.jx.utils.RetrofitUtils;

public class HomePageModel implements HomePageContract.HomePageModel {

    @Override
    public void requestData(final OnRequestListenter listenter) {
        BaseApiInterface api = RetrofitUtils.getInstence().getApi();

        Observable<HomePageBean> homePageBean = api.getHomePageBean();
        homePageBean.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<HomePageBean>() {
                    @Override
                    public void accept(HomePageBean homePageBean) throws Exception {
                        listenter.onSuccess(homePageBean);
                    }
                });
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
}
