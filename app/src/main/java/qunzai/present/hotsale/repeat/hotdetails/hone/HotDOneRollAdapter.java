package qunzai.present.hotsale.repeat.hotdetails.hone;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import qunzai.present.R;
import qunzai.present.internet.VolleySingleSimple;

/**
 * Created by dllo on 16/11/7.
 */
public class HotDOneRollAdapter extends LoopPagerAdapter {
    private ImageView imageView;
    private Context context;
    private List<String> urls;
//    int str [] = {R.mipmap.image_default,R.mipmap.ic_launcher,R.mipmap.image_default,R.mipmap.image_default};

    public void setUrls(ArrayList<String> urls) {
        this.urls = urls;
    }

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


        VolleySingleSimple.getInstance().getImage(urls.get(position), imageView);
        return imageView;
    }

    @Override
    protected int getRealCount() {
        return urls.size();
    }
}
