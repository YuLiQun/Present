package qunzai.present.sort;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.main.find.FindActivity;
import qunzai.present.sort.one.SOneFragment;
import qunzai.present.sort.raiders.SRaidersFragment;

/**
 * Created by dllo on 16/10/21.
 */
public class SortFragment extends BaseFragment {

    private TabLayout tb;
    private ViewPager vp;
    private LinearLayout llFind;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected void initView() {
        tb = bindView(R.id.tb_sort);
        vp = bindView(R.id.vp_sort);
        llFind = bindView(R.id.ll_home_selection_find);



    }

    @Override
    protected void initData() {
        ArrayList<BaseFragment> fragments = new ArrayList<>();
        fragments.add( new SRaidersFragment());
        fragments.add( new SOneFragment());

        SortAdapter adapter = new SortAdapter(getChildFragmentManager());
        adapter.setFragments(fragments);
        vp.setAdapter(adapter);
        tb.setupWithViewPager(vp);

        llFindClick();


    }

    private void llFindClick() {
        llFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FindActivity.class);
                startActivity(intent);
            }
        });
    }
}
