package zjj.bwie.com.jx.sort.mvp.view;


import zjj.bwie.com.jx.homepage.bean.HomePageBean;
import zjj.bwie.com.jx.sort.bean.ChildSortBean;
import zjj.bwie.com.jx.sort.bean.SortBean;

public interface SortContract {
    public interface ISortView{
        void getSortData(SortBean sortBean);
        void getChildSortData(ChildSortBean childSortBean);
    }
    public interface ISortModel{
        interface OnRequestListenter{
            void onSortSuccess(SortBean sortBean);
        }
        interface OnRequestChildListenter{
            void onChildSortSuccess(ChildSortBean childSortBean);
        }

        void requestData(OnRequestListenter listenter);
        void requestChildData(int cid,OnRequestChildListenter Childlistenter);
    }

    public interface ISortPresenter{
        void SortData();
        void ChildSortData(int cid);
    }

}
