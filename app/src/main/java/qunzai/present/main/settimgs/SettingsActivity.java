package qunzai.present.main.settimgs;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import qunzai.present.R;
import qunzai.present.base.BaseActivity;

/**
 * Created by dllo on 16/10/25.
 */
public class SettingsActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgBack;
    private LinearLayout ll;
    private TextView tvBack, tvNext,tvGender;
    private PopupWindow pop;
    private RadioButton rbtnBoy,rbtnGirl;
    private String gender;

    @Override
    protected int getLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected void initView() {
        imgBack = bindView(R.id.img_settings_back);
        ll = bindView(R.id.ll_setting);
        imgBack.setOnClickListener(this);
        ll.setOnClickListener(this);
        tvGender = bindView(R.id.tv_identify_ll);

    }

    @Override
    protected void initData() {
        pop = new PopupWindow();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_settings_back:
                onBackPressed();
                break;
            case R.id.ll_setting:

                if (pop.isShowing() && pop != null) {
                    return;
                } else {
                    initPop();
                }

                break;
            case R.id.tv_settings_back:
                if (pop.isShowing()) {
                    pop.dismiss();
                }
                break;
            case R.id.tv_settings_next:
                if (rbtnBoy.isChecked()){
                    gender = rbtnBoy.getText().toString();

                }else {
                    gender = rbtnGirl.getText().toString();

                }

                if (pop.isShowing()) {
                    pop.dismiss();
                }

                tvGender.setText(gender + "职场新人");

                break;
        }
    }

    private void initPop() {

        pop.setWidth(500);
        pop.setHeight(600);
        pop.setAnimationStyle(R.style.pop_anim_style);
        View view = LayoutInflater.from(this).inflate(R.layout.item_settings_pop, null);
        tvBack = bindView(view, R.id.tv_settings_back);
        tvNext = bindView(view, R.id.tv_settings_next);
        rbtnBoy = bindView(view, R.id.rbtn_settings_boy);
        rbtnGirl = bindView(view,R.id.rbtn_settings_girl);

        tvBack.setOnClickListener(this);
        tvNext.setOnClickListener(this);
        pop.setContentView(view);
        pop.showAsDropDown(ll, 150, 0);


    }
}
