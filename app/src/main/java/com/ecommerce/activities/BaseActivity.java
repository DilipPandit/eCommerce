package com.ecommerce.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Toast;

import com.ecommerce.R;

public class BaseActivity extends AppCompatActivity {
    /**
     * this will load fragment pass to it
     *
     * @param fragment
     */
    public void loadFragment(Fragment fragment, boolean isAddToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (isAddToBackStack) {
            fragmentTransaction.setCustomAnimations(R.anim.gd_rack, R.anim.push_left_out);
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.replace(R.id.containerBase, fragment);
        fragmentTransaction.commit();
    }

    public void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
