package qunzai.present.hotsale.repeat.hotdetails;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

import qunzai.present.R;
import qunzai.present.base.BaseActivity;
import qunzai.present.been.HotSaleRepeatBean;

/**
 * Created by dllo on 16/11/5.
 */
public class HotDetailsActivity extends BaseActivity {

    private TabLayout tb;
    private ViewPager vp;

    @Override
    protected int getLayout() {
        return R.layout.fragment_hot_details;
    }

    @Override
    protected void initView() {
        tb = bindView(R.id.tb_hotsale_details);
        vp = bindView(R.id.vp_hotsale_details);
    }

    @Override
    protected void initData() {

        Intent intent  = this.getIntent();
        HotSaleRepeatBean.DataBean.ItemsBean itemsBean = (HotSaleRepeatBean.DataBean.ItemsBean) intent.getSerializableExtra("hotitem");
        HDetailsAdapter adapter = new HDetailsAdapter(getSupportFragmentManager());
        adapter.setItemsBean(itemsBean);
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);

    }
}
