package qunzai.present.sort;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.sort.one.SOneFragment;
import qunzai.present.sort.raiders.SRaidersFragment;

/**
 * Created by dllo on 16/10/21.
 */
public class SortFragment extends BaseFragment {

    private TabLayout tb;
    private ViewPager vp;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected void initView() {
        tb = bindView(R.id.tb_sort);
        vp = bindView(R.id.vp_sort);



    }

    @Override
    protected void initData() {
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add( new SOneFragment());
        fragments.add( new SRaidersFragment());

        SortAdapter adapter = new SortAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);

    }
}
