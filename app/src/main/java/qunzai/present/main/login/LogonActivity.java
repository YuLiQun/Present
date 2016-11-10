package qunzai.present.main.login;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import qunzai.present.R;
import qunzai.present.base.BaseActivity;

/**
 * Created by dllo on 16/11/10.
 */
public class LogonActivity extends BaseActivity {

    private EditText etNum;
    private EditText etPassword;
    private Button btnLogon;

    @Override
    protected int getLayout() {
        return R.layout.activity_logon;
    }

    @Override
    protected void initView() {
        etNum = bindView(R.id.et_logon_num);
        etPassword = bindView(R.id.et_logon_password);
        btnLogon = bindView(R.id.btn_logon);


    }

    @Override
    protected void initData() {


        btnLogon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etNum.getText().toString();
                String password = etPassword.getText().toString();


                BmobUser bmobUser = new BmobUser();
                bmobUser.setUsername(name);//用户名
                bmobUser.setPassword(password);//密码
                bmobUser.signUp(new SaveListener<BmobUser>() {
                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {
                        if (e == null) {


                            onBackPressed();
                            Toast.makeText(LogonActivity.this, "注册成功", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LogonActivity.this, "已经注册完成", Toast.LENGTH_SHORT).show();
                            Log.d("qqq", e.getMessage());//组册失败的原因
                        }
                    }
                });//判断注册是否成功


            }
        });


    }
}
