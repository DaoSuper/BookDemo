package cn.edu.gdmec.android.bookdemo.utils;

import com.google.gson.Gson;

import cn.edu.gdmec.android.bookdemo.BookBean;
/**
 * Created by apple on 18/5/15.
 */

public class JsonUtils {

    public static BookBean getBook(String res){
        Gson gson = new Gson();
        BookBean bookBean = gson.fromJson(res, BookBean.class);
        return bookBean;
    }
}
