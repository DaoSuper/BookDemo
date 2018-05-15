package cn.edu.gdmec.android.bookdemo.utils;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.edu.gdmec.android.bookdemo.BookBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by apple on 18/5/15.
 */

public class OkHttpUtils {
    String res = null;
    private static OkHttpUtils okHttpUtils;

    private synchronized static OkHttpUtils getInstance()
    {
        if (okHttpUtils == null){
            okHttpUtils = new OkHttpUtils();
        }
        return okHttpUtils;
    }

    public static void getResultCallback(String url, ResultCallback resultCallback){
        getInstance().sendRequest(url,resultCallback);
    }

    public void sendRequest(String url, final ResultCallback resultCallbak){
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(2, TimeUnit.SECONDS).build();

        final Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (resultCallbak != null){
                    resultCallbak.onFailure(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                  res = response.body().string();
                Log.i("res",res);
               BookBean bean = JsonUtils.getBook(res);
                if (resultCallbak != null){
                    resultCallbak.getBook(bean);
                }
            }
        });
    }

    public interface ResultCallback{
        void getBook(BookBean bookBean);
        void onFailure(Exception e);
    }
}
