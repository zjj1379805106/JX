package zjj.bwie.com.jx.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static RetrofitUtils retrofitUtils;
    private OkHttpClient okHttpClient;

    private RetrofitUtils(){
        okHttpClient = new OkHttpClient.Builder().build();
    }

    public static RetrofitUtils getInstence(){
        if(retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if(retrofitUtils==null){
                    retrofitUtils=new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    public BaseApiInterface getApi(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseApi.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        BaseApiInterface baseApiInterface = retrofit.create(BaseApiInterface.class);

        return baseApiInterface;
    }
}
