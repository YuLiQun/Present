package qunzai.present.main;

import android.content.Intent;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import qunzai.present.R;
import qunzai.present.base.BaseActivity;
import qunzai.present.internet.MyURL;
import qunzai.present.internet.VolleySingleSimple;

/**
 * Created by dllo on 16/10/24.
 */
public class WelcomeActivity extends BaseActivity {

    private ImageView imgWelcome;
    int reclen = 3;
    Timer timer = new Timer();
//    String strImage = "http://h.hiphotos.baidu.com/image/pic/item/4afbfbedab64034fd7b9c9d3a7c379310b551dea.jpg";

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        //自动生成全局变量是View,,这里请注意
        imgWelcome = bindView(R.id.img_welcome);

    }

    @Override
    protected void initData() {
        //网络请求图片
        VolleySingleSimple.getInstance().getImage(MyURL.WELCOME,imgWelcome);

        timer.schedule(task,1000,1000);
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    reclen --;
                    if (reclen ==0){
                        timer.cancel();
                        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });
        }
    };
}
