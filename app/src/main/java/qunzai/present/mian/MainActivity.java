package qunzai.present.mian;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import qunzai.present.R;
import qunzai.present.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private RadioButton rbtnHome;
    private RadioButton rbtnHotSale;
    private RadioButton rbtnSort;
    private RadioButton rbtnMine;
    private FragmentTransaction transaction;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        rbtnHome = bindView(R.id.rbtn_home);
        rbtnHotSale = bindView(R.id.rbtn_hotsale);
        rbtnSort = bindView(R.id.rbtn_sort);
        rbtnMine = bindView(R.id.rbtn_mine);

        setClickListener(this,rbtnHome);
        setClickListener(this,rbtnHotSale);
        setClickListener(this,rbtnSort);
        setClickListener(this,rbtnMine);

    }

    @Override
    protected void initData() {

        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_main,new HomeFragment());
        transaction.commit();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rbtn_home:
                transaction.replace(R.id.fl_main,new HomeFragment());

                break;
            case R.id.rbtn_hotsale:
                transaction.replace(R.id.fl_main,new HotSaleFragment());

                break;
            case R.id.rbtn_sort:
                transaction.replace(R.id.fl_main,new SortFragment());

                break;
            case R.id.rbtn_mine:
                transaction.replace(R.id.fl_main,new MineFragment());

                break;

        }

    }
}
