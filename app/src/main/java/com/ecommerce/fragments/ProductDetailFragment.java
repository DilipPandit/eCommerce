package com.ecommerce.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ecommerce.R;
import com.ecommerce.activities.HomeActivity;
import com.ecommerce.models.ProductList;
import com.ecommerce.utilities.EndPointAPI;

public class ProductDetailFragment extends Fragment {
    TextView tvSize, tvPrice, tvCategoryId, tvCategoryName;
    ImageView ivProduct;
    ProductList productList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        _init(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).getCustomActionBar().hideHomeButton();
        ((HomeActivity) getActivity()).getCustomActionBar().showBackButton();
        ((HomeActivity) getActivity()).getCustomActionBar().setTitle(getResources().getString(R.string.product_detail));
    }

    private void _init(View view) {
        tvSize = view.findViewById(R.id.tvSize);
        tvPrice = view.findViewById(R.id.tvPrice);
        tvCategoryId = view.findViewById(R.id.tvCategoryId);
        tvCategoryName = view.findViewById(R.id.tvCategoryName);
        ivProduct = view.findViewById(R.id.ivProduct);
        productList = (ProductList) getArguments().getSerializable(EndPointAPI.productDetails);
        setDetails(productList);
    }

    private void setDetails(ProductList productList) {
        tvCategoryName.setText(productList.getCategory_name());
        tvCategoryId.setText(productList.getCategory_id() + "");
        tvPrice.setText(productList.getPrice() + "");
        tvSize.setText(productList.getSize());
        Glide.with(getActivity())
                .load(productList.getImage() + EndPointAPI.GIF).asGif()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(ivProduct);
    }
}
