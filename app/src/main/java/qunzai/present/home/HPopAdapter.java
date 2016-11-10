package qunzai.present.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import qunzai.present.R;
import qunzai.present.base.CommonViewHolder;
import qunzai.present.been.HomeTitleBean;

/**
 * Created by dllo on 16/11/3.
 */
public class HPopAdapter extends RecyclerView.Adapter<CommonViewHolder>{
    private ArrayList<String> arrayList;
    //接口声明
    private OnRecyclerItemClickListener onRecyclerItemClickListener;


    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_rv_home_pop);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        String name = arrayList.get(position);
//        holder.setRBtnText(R.id.rbtn_home_pop,name).setItemClick(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("test","+++++");
//                //接口注册
//                onRecyclerItemClickListener.onClick(holder.getLayoutPosition());
//
//            }
//        });

//        holder.setRBtnText(R.id.rbtn_home_pop,name).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //接口注册
//                Log.d("sss", "position: + sss" + position);
//                onRecyclerItemClickListener.onClick(position);
//
//            }
//        });

        holder.setViewCliad(R.id.rbtn_home_pop, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerItemClickListener.onClick(position);
            }
        }).setText(R.id.rbtn_home_pop,name);

    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }


}
