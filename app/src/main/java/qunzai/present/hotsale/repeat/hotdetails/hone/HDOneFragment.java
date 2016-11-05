package qunzai.present.hotsale.repeat.hotdetails.hone;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;
import qunzai.present.internet.VolleySingleSimple;

/**
 * Created by dllo on 16/11/5.
 */
public class HDOneFragment extends BaseFragment {

    private RollPagerView roll;

    @Override
    protected int getLayout() {
        return R.layout.fragment_hd_one;
    }

    @Override
    protected void initView() {
        RecyclerView rv = bindView(R.id.rv_hot_done);
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv_hot_d_one,null);
        roll = bindView(view, R.id.roll_hot_done);

    }

    @Override
    protected void initData() {
        roll.setAnimationDurtion(500);//透明度
        roll.setAdapter(new HotDOneRollAdapter(roll,mContext));
        roll.setHintView(new ColorPointHintView(mContext, Color.RED,Color.WHITE));//颜色一:选中颜色  颜色二:默认颜色
        //TODO 这里继续
        Intent intent  =


    }

    private class HotDOneRollAdapter extends LoopPagerAdapter {
        private ImageView imageView;
        private Context context;

        public HotDOneRollAdapter(RollPagerView viewPager, Context context) {
            super(viewPager);
            this.context = context;
        }

        public HotDOneRollAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {
            imageView = new ImageView(context);

            VolleySingleSimple.getInstance().getImage(url,imageView);
            return imageView;
        }

        @Override
        protected int getRealCount() {
            return ;
        }
    }
}
