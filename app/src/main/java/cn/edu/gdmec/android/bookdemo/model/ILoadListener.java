package cn.edu.gdmec.android.bookdemo.model;


import cn.edu.gdmec.android.bookdemo.BookBean;

/**
 * Created by apple on 18/5/15.
 */

public interface ILoadListener {
    void onSuccess(BookBean bookBean);
    void onFailure(Exception e);
}
