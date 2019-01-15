package com.ecommerce.activities;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;

import com.ecommerce.R;
import com.ecommerce.customViews.CustomActionBar;
import com.ecommerce.customViews.CustomDrawerLayout;
import com.ecommerce.fragments.DashBoardFragment;

public class HomeActivity extends BaseActivity implements CustomActionBar.CustomActionBarListener {
    CustomActionBar customActionBar;
    CustomDrawerLayout customDraerLayout;
    RecyclerView rvCustomList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        _init();
    }

    private void _init() {
        customActionBar = findViewById(R.id.customActionBar);
        customActionBar.setListeners(this);
        rvCustomList = findViewById(R.id.rvCustomList);
        customDraerLayout = findViewById(R.id.customDraerLayout);
        customDraerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        customDraerLayout.setListView(rvCustomList);
        loadFragment(new DashBoardFragment(), false);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            super.onBackPressed();
        else
            getSupportFragmentManager().popBackStack();

    }

    @Override
    public void onBackClick() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void OnHomeButtonClick() {
        if (!customDraerLayout.isDrawerOpen(GravityCompat.START))
            customDraerLayout.openDrawer(Gravity.LEFT);
        else
            customDraerLayout.closeDrawer(Gravity.LEFT);

    }

    public CustomActionBar getCustomActionBar() {
        return customActionBar;
    }

    public CustomDrawerLayout getCustomDraerLayout() {
        return customDraerLayout;
    }

}
