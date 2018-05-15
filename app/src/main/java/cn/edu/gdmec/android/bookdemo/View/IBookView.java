package cn.edu.gdmec.android.bookdemo.View;


import cn.edu.gdmec.android.bookdemo.BookBean;

/**
 * Created by apple on 18/5/15.
 */

public interface IBookView {
    void showProgress();
    void hideProgress();
    void showBookData(BookBean bookBean);
    void showLoadFailMsg(Exception e);
}
