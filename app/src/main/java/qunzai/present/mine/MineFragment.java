package qunzai.present.mine;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.main.login.LoginActivity;
import qunzai.present.main.settimgs.SettingsActivity;

/**
 * Created by dllo on 16/10/21.
 */
public class MineFragment extends BaseFragment {


    private RelativeLayout flLanding;
    private ImageView imgGirl;
    private ImageView imgSettings;

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        flLanding = bindView(R.id.fl_mine_landing);
        imgGirl = bindView(R.id.img_mine_girl);
        imgSettings = bindView(R.id.img_mine_settings);
        //点击跳转到登录界面
        flLandingClick();
        imgGirlClick();
        //点击跳转到设置界面
        imgSettingsClick();

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

    }
}
