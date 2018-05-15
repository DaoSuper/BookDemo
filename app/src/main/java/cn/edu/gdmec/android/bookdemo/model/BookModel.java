package cn.edu.gdmec.android.bookdemo.model;


import cn.edu.gdmec.android.bookdemo.BookBean;
import cn.edu.gdmec.android.bookdemo.utils.OkHttpUtils;

/**
 * Created by apple on 18/5/15.
 */

public class BookModel implements IBookModel {
    @Override
    public void loadBook(String url, final ILoadListener loadListener) {
        OkHttpUtils.ResultCallback resultCallback = new OkHttpUtils.ResultCallback() {
            @Override
            public void getBook(BookBean bookBean) {
                loadListener.onSuccess(bookBean);
            }

            @Override
            public void onFailure(Exception e) {
                  loadListener.onFailure(e);
            }
        };
        OkHttpUtils.getResultCallback(url,resultCallback);
    }
}
