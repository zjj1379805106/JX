package zjj.bwie.com.jx.utils;


import org.greenrobot.eventbus.Subscribe;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import zjj.bwie.com.jx.homepage.bean.HomePageBean;
import zjj.bwie.com.jx.sort.bean.ChildSortBean;
import zjj.bwie.com.jx.sort.bean.SortBean;

public interface BaseApiInterface {
    @GET("ad/getAd")
    Observable<HomePageBean> getHomePageBean();
    @GET("product/getCatagory")
    Observable<SortBean> getSortBean();
    @GET("product/getProductCatagory")
    Observable<ChildSortBean> getChildShrtBean(@Query("cid") int cid);



}
