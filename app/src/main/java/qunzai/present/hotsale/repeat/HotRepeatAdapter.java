package qunzai.present.hotsale.repeat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.BaseFragment;

/**
 * Created by dllo on 16/10/25.
 */
public class HotRepeatAdapter extends RecyclerView.Adapter<HotRepeatAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<BaseFragment> fragments;

    public HotRepeatAdapter(Context context) {
        this.context = context;
    }

    public void setFragments(ArrayList<BaseFragment> fragments) {
        this.fragments = fragments;
    }

    @Override
    public HotRepeatAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_data,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HotRepeatAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);

        }
    }
}
