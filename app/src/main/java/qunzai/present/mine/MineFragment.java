package qunzai.present.mine;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static qunzai.present.R.id.*;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.TextEvent;
import qunzai.present.main.login.LoginActivity;
import qunzai.present.main.settimgs.SettingsActivity;

/**
 * Created by dllo on 16/10/21.
 */
public class MineFragment extends BaseFragment {


    private RelativeLayout flLanding;
    private ImageView imgGirl, imgSettings, imgCz;
    private TextView tvUserName, tvCz;
    private String s;

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        tvUserName = bindView(R.id.tv_mine_username);

        flLanding = bindView(R.id.fl_mine_landing);
        imgGirl = bindView(R.id.img_mine_girl);
        imgSettings = bindView(R.id.img_mine_settings);

        imgCz = bindView(R.id.img_mine_cz);
        tvCz = bindView(R.id.tv_mine_cz);



    }

    private void imgSettingsClick() {
        imgSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void imgGirlClick() {
        imgGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void flLandingClick() {
        flLanding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void initData() {

        //点击跳转到登录界面
        flLandingClick();
        imgGirlClick();
        //点击跳转到设置界面
        imgSettingsClick();

    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)//先发送后注册,,粘性,,,将发送和接受绑定在一起
    public void getTextEvent(TextEvent event) {
        s = event.getNum();
        tvUserName.setText(s);
        tvCz.setVisibility(View.INVISIBLE);
        imgCz.setVisibility(View.INVISIBLE);
    }
}
