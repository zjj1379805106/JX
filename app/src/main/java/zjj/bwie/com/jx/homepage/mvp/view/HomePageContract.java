package zjj.bwie.com.jx.homepage.mvp.view;


import zjj.bwie.com.jx.homepage.bean.HomePageBean;
import zjj.bwie.com.jx.sort.bean.SortBean;

public interface HomePageContract {
    public interface HomePageView{
        void getData(HomePageBean homePageBean);
        void getSortData(SortBean sortBean);
    }
    public interface HomePageModel{
        interface OnRequestListenter{
            void onSuccess(HomePageBean homePageBean);

            void onSortSuccess(SortBean sortBean);
        }

        void requestData(OnRequestListenter listenter);
    }

    public interface HomePagePresenter{
        void homePageData();
    }

}
