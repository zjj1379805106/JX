package zjj.bwie.com.jx.homepage.mvp.presenter;

import android.widget.Toast;

import zjj.bwie.com.jx.homepage.bean.HomePageBean;
import zjj.bwie.com.jx.homepage.mvp.model.HomePageModel;
import zjj.bwie.com.jx.homepage.mvp.view.HomePageContract;
import zjj.bwie.com.jx.sort.bean.SortBean;

public class HomePagePresenter implements HomePageContract.HomePagePresenter {
    private HomePageModel homePageModel;
    private HomePageContract.HomePageView homePageView;

    public HomePagePresenter(HomePageContract.HomePageView homePageView) {
        this.homePageView = homePageView;
        this.homePageModel=new HomePageModel();
    }

    @Override
    public void homePageData() {
        homePageModel.requestData(new HomePageContract.HomePageModel.OnRequestListenter() {
            @Override
            public void onSuccess(HomePageBean homePageBean) {
                homePageView.getData(homePageBean);
            }

            @Override
            public void onSortSuccess(SortBean sortBean) {
                homePageView.getSortData(sortBean);
            }
        });
    }
}
