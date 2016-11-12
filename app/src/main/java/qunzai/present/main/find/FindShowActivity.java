package qunzai.present.main.find;

import android.content.Intent;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import qunzai.present.R;
import qunzai.present.base.BaseActivity;
import qunzai.present.been.TestBean;
import qunzai.present.been.TextEvent;
import qunzai.present.other.OnEventbusChangeListener;

/**
 * Created by dllo on 16/11/11.
 */
/*EventBus*/
public class FindShowActivity extends BaseActivity {

    private TextView tvShow;

    @Override
    protected int getLayout() {
        return R.layout.activity_findshow;
    }

    @Override
    protected void initView() {
        tvShow = bindView(R.id.tv_findshow);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String data = intent.getStringExtra("cont");
        tvShow.setText(data);

    }





}
