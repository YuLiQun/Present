package qunzai.present.mine;

import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;

/**
 * Created by dllo on 16/10/21.
 */
public class MineFragment extends BaseFragment {

    private TextView tvOne;
    private TextView tvRaiders;

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        tvOne = (TextView) getView().findViewById(R.id.rbtn_mine_one);
        tvRaiders = (TextView) getView().findViewById(R.id.rbtn_mine_raiders);


    }



    @Override
    protected void initData() {

    }
}
