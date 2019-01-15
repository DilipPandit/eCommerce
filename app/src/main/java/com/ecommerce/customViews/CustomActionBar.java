package com.ecommerce.customViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecommerce.R;

public class CustomActionBar extends RelativeLayout implements View.OnClickListener {
    Context context;
    ImageView ivHome, ivBack;
    View customView;
    TextView tvTitle;
    CustomActionBarListener customActionBarListener;

    public void setListeners(CustomActionBarListener customActionBarListener) {
        this.customActionBarListener = customActionBarListener;
    }

    /**
     * Constructor
     *
     * @param context
     */
    public CustomActionBar(Context context) {
        super(context);
        this.context = context;
        _init(context);
    }

    /**
     * Constructor
     *
     * @param context
     * @param attrs
     */
    public CustomActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        _init(context);
    }

    private void _init(Context context) {
        customView = LayoutInflater.from(context).inflate(R.layout.custom_actionbar, this);
        ivBack = customView.findViewById(R.id.ivBack);
        ivHome = customView.findViewById(R.id.ivHome);
        ivHome.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        tvTitle = customView.findViewById(R.id.tvTitle);
    }

    public void showTitle() {
        tvTitle.setVisibility(VISIBLE);
    }

    public void hideTitle() {
        tvTitle.setVisibility(GONE);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void hideBackButton() {
        ivBack.setVisibility(GONE);
    }

    public void showBackButton() {
        ivBack.setVisibility(VISIBLE);
    }

    public void hideHomeButton() {
        ivHome.setVisibility(GONE);
    }

    public void showHomeButton() {
        ivHome.setVisibility(VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                customActionBarListener.onBackClick();
                break;
            case R.id.ivHome:
                customActionBarListener.OnHomeButtonClick();
                break;
        }
    }

    public interface CustomActionBarListener {
        public void onBackClick();

        public void OnHomeButtonClick();

    }


}
