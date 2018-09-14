package zjj.bwie.com.jx.sort.mvp.presenter;

import zjj.bwie.com.jx.homepage.bean.HomePageBean;
import zjj.bwie.com.jx.homepage.mvp.model.HomePageModel;
import zjj.bwie.com.jx.homepage.mvp.view.HomePageContract;
import zjj.bwie.com.jx.sort.bean.ChildSortBean;
import zjj.bwie.com.jx.sort.bean.SortBean;
import zjj.bwie.com.jx.sort.mvp.model.SortModel;
import zjj.bwie.com.jx.sort.mvp.view.SortContract;

public class SortPresenter implements SortContract.ISortPresenter {
    private SortModel sortModel;
    private SortContract.ISortView iSortView;

    public SortPresenter(SortContract.ISortView iSortView) {
        this.iSortView = iSortView;
        this.sortModel=new SortModel();
    }

    @Override
    public void SortData() {
        sortModel.requestData(new SortContract.ISortModel.OnRequestListenter() {
            @Override
            public void onSortSuccess(SortBean sortBean) {
                iSortView.getSortData(sortBean);
            }
        });
    }

    @Override
    public void ChildSortData(int cid) {
        sortModel.requestChildData(cid, new SortContract.ISortModel.OnRequestChildListenter() {
            @Override
            public void onChildSortSuccess(ChildSortBean childSortBean) {
                iSortView.getChildSortData(childSortBean);
            }
        });
    }
}
