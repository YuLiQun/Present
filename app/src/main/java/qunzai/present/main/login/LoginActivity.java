package qunzai.present.main.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import qunzai.present.R;
import qunzai.present.base.BaseActivity;
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

        imgReturn.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvLogon.setOnClickListener(this);


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
                String num = etNum.getText().toString();
                String password = etPassword.getText().toString();

                MyUser myUser = new MyUser();
                myUser.setUsername(num);
                myUser.setPassword(password);
                myUser.login(new SaveListener<MyUser>() {
                    @Override
                    public void done(MyUser myUser, BmobException e) {
                        if (e == null) {
                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                            onBackPressed();
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
        }
    }
}
