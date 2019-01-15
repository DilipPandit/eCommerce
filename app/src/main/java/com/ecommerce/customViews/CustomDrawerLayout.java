package com.ecommerce.customViews;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.Gravity;

import com.ecommerce.R;
import com.ecommerce.activities.BaseActivity;
import com.ecommerce.activities.HomeActivity;
import com.ecommerce.adapters.SideMenuAdapter;
import com.ecommerce.fragments.CommonFragment;
import com.ecommerce.listners.SideMenuItemClickListener;
import com.ecommerce.utilities.EndPointAPI;


public class CustomDrawerLayout extends DrawerLayout implements SideMenuItemClickListener {
    RecyclerView rvCustomList;
    public SideMenuAdapter sideMenuAdapter;
    Context context;
    String[] menuList;

    public SideMenuAdapter getL42SideMenuAdapter() {
        return sideMenuAdapter;
    }

    public void setL42SideMenuAdapter(SideMenuAdapter l42SideMenuAdapter) {
        this.sideMenuAdapter = l42SideMenuAdapter;
    }


    public CustomDrawerLayout(Context context) {
        super(context);
        this.context = context;
        _init(context);
    }

    public CustomDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        _init(context);
    }

    private void _init(Context context) {
        menuList = context.getResources().getStringArray(R.array.SideMenu);

    }

    private void setSideMenu() {
        _init(context);
        if (sideMenuAdapter == null) {
            sideMenuAdapter = new SideMenuAdapter(context, menuList, this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            rvCustomList.setLayoutManager(linearLayoutManager);
            rvCustomList.setAdapter(sideMenuAdapter);
        } else {
            sideMenuAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public void onItemClick(int position) {
        ((HomeActivity) context).getCustomDraerLayout().closeDrawer(Gravity.LEFT);
        ((HomeActivity) context).getCustomActionBar().setTitle(menuList[position]);

        switch (menuList[position]) {
            case EndPointAPI
                    .MYACC:
            case EndPointAPI.MYORDER:
            case EndPointAPI.OFFERS:
                ((HomeActivity) context).loadFragment(new CommonFragment(), true);
                ((BaseActivity) context).showToast(context.getResources().getString(R.string.work_in_progress));
                break;
            case EndPointAPI.LOGOUT:
                ((HomeActivity) context).finish();
                break;
        }

    }

    @Override
    public boolean callOnClick() {
        return false;
    }

    public void setListView(RecyclerView rvCustomList) {
        this.rvCustomList = rvCustomList;
        setSideMenu();
    }
}
