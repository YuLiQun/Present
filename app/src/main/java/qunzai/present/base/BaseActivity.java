package qunzai.present.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import qunzai.present.R;

/**
 * Created by dllo on 16/10/21.
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayout());
        initView();
        initData();
    }

    //绑定布局
    protected abstract int getLayout();
    //初始化View
    protected abstract void initView();
    //初始化数据
    protected abstract void initData();

    //简化findViewById
    protected <T extends View> T bindView(int id){
        return (T) findViewById(id);
    }

    protected <T extends View> T bindView(View view, int id){
        return (T) view.findViewById(id);
    }

    //简化监听
    protected void setClickListener(View.OnClickListener clickListener ,View ...views){
        for (View view :views) {
            view.setOnClickListener(clickListener);
        }
    }
}
