package qunzai.present.main.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import qunzai.present.R;
import qunzai.present.base.BaseActivity;
import qunzai.present.been.TextEvent;
import qunzai.present.mine.MineFragment;
import qunzai.present.other.MyUser;

/**
 * Created by dllo on 16/10/25.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgReturn;
    private EditText etNum;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvLogon;
    private ImageView qq;
    public static final int RESULT = 0;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {



        imgReturn = bindView(R.id.img_login_return);
        etNum = bindView(R.id.et_login_num);
        etPassword = bindView(R.id.et_login_password);
        btnLogin = bindView(R.id.btn_login);
        tvLogon = bindView(R.id.tv_login_logon);
        qq = bindView(R.id.qq);

        imgReturn.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvLogon.setOnClickListener(this);
        qq.setOnClickListener(this);


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_login_return:
                onBackPressed();
                break;
            case R.id.btn_login:
                final String num = etNum.getText().toString();
                String password = etPassword.getText().toString();

                MyUser myUser = new MyUser();
                myUser.setUsername(num);
                myUser.setPassword(password);
                myUser.login(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if (e == null) {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            /*EventBus*/
                            TextEvent event = new TextEvent();
                            event.setNum(num);
                            /*这里注意一下,,postSticky,,粘性的*/
                            EventBus.getDefault().postSticky(event);
                            finish();
                        } else {
                            Log.d("MainActivity", e.getMessage());
                            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                break;
            case R.id.tv_login_logon:
                Intent intent = new Intent(this, LogonActivity.class);
                startActivity(intent);
                break;
            case R.id.qq://点击qq登陆
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.authorize();
                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        PlatformDb platformDb = platform.getDb();
                        String name = platformDb.getUserName();
                        String icon = platformDb.getUserIcon();
                        Intent intent = new Intent();
                        intent.putExtra("name", name);
                        intent.putExtra("icon", icon);
                        setResult(RESULT, intent);
                        finish();
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                break;
        }
    }








}
