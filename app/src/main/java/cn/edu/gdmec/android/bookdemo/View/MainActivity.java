package cn.edu.gdmec.android.bookdemo.View;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.TimerTask;

import cn.edu.gdmec.android.bookdemo.BookBean;
import cn.edu.gdmec.android.bookdemo.R;
import cn.edu.gdmec.android.bookdemo.presenter.BookPresenter;


public class MainActivity extends AppCompatActivity implements IBookView, View.OnClickListener{

    private TextView tvBook;
    private ProgressDialog progressDialog;
    private BookPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_book_search).setOnClickListener(this);
        tvBook = (TextView) findViewById(R.id.tv_book);
        presenter = new BookPresenter(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_book_search:
                presenter.loadBook("金瓶梅");
                break;
        }
    }

    @Override
    public void showProgress() {
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
        progressDialog = ProgressDialog.show(MainActivity.this, "", "正在获取");
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null && progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void showBookData(final BookBean bookBean) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                if (bookBean.getTotal() == 0) {
                    Toast.makeText(MainActivity.this, bookBean.getStart(), Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(MainActivity.this, "sdjfljl ", Toast.LENGTH_SHORT).show();
                    tvBook.setText("作者："+bookBean.getBooks().get(2).getAuthor()+ "图书标题"+bookBean.getBooks().get(1).getSubtitle());
                }
                //tvBook.setText("作者："+bookBean.getBooks().get(0).getRating().getMax());
            }
        });
    }

    @Override
    public void showLoadFailMsg(final Exception e) {
        runOnUiThread(new TimerTask() {
            @Override
            public void run() {
                tvBook.setText("加载数据失败：" + e.toString());
            }
        });
    }
}
