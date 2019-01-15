package com.ecommerce.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecommerce.R;
import com.ecommerce.listners.SideMenuItemClickListener;

public class SideMenuAdapter extends RecyclerView.Adapter<SideMenuAdapter.MenuHolder> {
    Context context;
    String[] menuArrayList;
    SideMenuItemClickListener sideMenuItemClickListener;

    public SideMenuAdapter(Context context, String[] menuArrayList, SideMenuItemClickListener sideMenuItemClickListener) {
        this.context = context;
        this.menuArrayList = menuArrayList;
        this.sideMenuItemClickListener = sideMenuItemClickListener;
    }

    @Override
    public SideMenuAdapter.MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_left_menu, null);
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(SideMenuAdapter.MenuHolder holder, final int position) {
        holder.tvTitle.setText(menuArrayList[position]);
        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sideMenuItemClickListener.onItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return menuArrayList.length;
    }

    protected class MenuHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        public MenuHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }
}
