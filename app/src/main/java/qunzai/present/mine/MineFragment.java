package qunzai.present.mine;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import static qunzai.present.R.id.*;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.been.TextEvent;
import qunzai.present.internet.VolleySingleSimple;
import qunzai.present.main.login.LoginActivity;
import qunzai.present.main.settimgs.SettingsActivity;

/**
 * Created by dllo on 16/10/21.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {


    private RelativeLayout flLanding;
    private ImageView imgGirl, imgSettings, imgCz;
    private TextView tvUserName, tvCz;
    private String s;
    private String name;
    private String icon;
    private ImageView backIv;
    private PlatformActionListener platformActionListener;

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
        backIv = bindView(R.id.img_mine_message);

        imgCz = bindView(R.id.img_mine_cz);
        tvCz = bindView(R.id.tv_mine_cz);

        imgSettings.setOnClickListener(this);
        imgGirl.setOnClickListener(this);
        flLanding.setOnClickListener(this);
        backIv.setOnClickListener(this);


    }



    //第三方登录用
    @Override
    protected void initData() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        try {

            PlatformDb platformDb = qq.getDb();
            name = platformDb.getUserName();
            icon = platformDb.getUserIcon();

            if (!TextUtils.isEmpty(name)) {
                tvUserName.setVisibility(View.GONE);
                tvUserName.setVisibility(View.VISIBLE);
                tvUserName.setText(name);
                VolleySingleSimple.getInstance().getImage(icon, imgGirl);
            }
        } catch (NullPointerException e) {

        }

        // TODO Auto-generated method stub
//输出所有授权信息
// TODO Auto-generated method stub
        platformActionListener = new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                // TODO Auto-generated method stub
                //输出所有授权信息
                platform.getDb().exportData();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                // TODO Auto-generated method stub
                throwable.printStackTrace();
            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        };
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)//先发送后注册,,粘性,,,将发送和接受绑定在一起
    public void getTextEvent(TextEvent event) {
        s = event.getNum();
        tvUserName.setText(s);
        tvCz.setVisibility(View.INVISIBLE);
        imgCz.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        //点击跳转到设置界面
        Intent intent1 = new Intent(getActivity(), LoginActivity.class);
        switch (v.getId()) {

            case R.id.img_mine_settings:
                //点击跳转到登录界面
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.img_mine_girl:

                startActivity(intent1);
                break;
            case R.id.fl_mine_landing:

                startActivity(intent1);
                break;
            case R.id.img_mine_message://第三方登录用
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                if (qq.isAuthValid()) {
                    qq.removeAccount(true);
                }
                qq.setPlatformActionListener(platformActionListener);
//authorize与showUser单独调用一个即可
                qq.authorize();//单独授权，OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息
//isValid和removeAccount不开启线程，会直接返回。
//                qq.removeAccount(true);
                getActivity().setResult(-1);
                break;
        }
    }



    /*第三方登录用*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            //退出登录
            tvUserName.setVisibility(View.VISIBLE);
            tvUserName.setVisibility(View.GONE);
            imgGirl.setImageResource(R.mipmap.me_avatar_girl);
            return;
        }


        if (requestCode == 1 && LoginActivity.RESULT == resultCode && data != null) {

            name = data.getStringExtra("name");
            icon = data.getStringExtra("icon");
            tvUserName.setVisibility(View.GONE);
            tvUserName.setVisibility(View.VISIBLE);
            tvUserName.setText(name);
            VolleySingleSimple.getInstance().getImage(icon, imgGirl);

        }

    }
}
