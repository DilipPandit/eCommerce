package com.ecommerce.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.VolleyError;
import com.ecommerce.R;
import com.ecommerce.activities.BaseActivity;
import com.ecommerce.activities.HomeActivity;
import com.ecommerce.adapters.ProductListAdapter;
import com.ecommerce.interfaces.NetworkRequest;
import com.ecommerce.listners.ProductClickListener;
import com.ecommerce.models.ProductList;
import com.ecommerce.networking.NetworkService;
import com.ecommerce.utilities.EndPointAPI;

import java.util.ArrayList;

public class DashBoardFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView rvProductList;
    ProductListAdapter productListAdapter;
    ArrayList<ProductList> productLists;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    SwipeRefreshLayout srLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        _init(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).getCustomActionBar().showHomeButton();
        ((HomeActivity) getActivity()).getCustomActionBar().hideBackButton();
        ((HomeActivity) getActivity()).getCustomActionBar().setTitle(getResources().getString(R.string.product_list));
    }

    private void _init(View view) {
        rvProductList = view.findViewById(R.id.rvProductList);
        srLayout = view.findViewById(R.id.srLayout);
        srLayout.setOnRefreshListener(this);
        srLayout.setColorSchemeColors(getActivity().getResources().getColor(R.color.colorPrimary), getActivity().getResources().getColor(R.color.colorAccent));
        setAdapter();
        if (productLists.size() == 0) {
            srLayout.setRefreshing(true);
            getProductList();
        }

    }

    private void getProductList() {
        if (!((BaseActivity) getActivity()).isNetworkConnected()) {
            ((BaseActivity) getActivity()).showToast(getResources().getString(R.string.no_internet));
            srLayout.setRefreshing(false);
            return;
        }
        NetworkService.getInstance().NetworkObjectRequest(EndPointAPI.PRODUCTLIST, getActivity(), new NetworkRequest() {
            @Override
            public void OnSuccess(ArrayList<ProductList> lists) {
                if (productLists.size() > 0)
                    productLists.clear();
                productLists.addAll(lists);
                productListAdapter.notifyDataSetChanged();
                srLayout.setRefreshing(false);
            }

            @Override
            public void OnError(VolleyError volleyError) {
                srLayout.setRefreshing(false);
            }
        });
    }

    private void setAdapter() {
        if (productLists == null)
            productLists = new ArrayList<>();
        if (productListAdapter == null)
            productListAdapter = new ProductListAdapter(getActivity(), productLists, new ProductListener());
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);
        rvProductList.setLayoutManager(staggeredGridLayoutManager);
        rvProductList.setAdapter(productListAdapter);
    }

    @Override
    public void onRefresh() {
        ((BaseActivity) getActivity()).showToast(getResources().getString(R.string.please_wait));
        getProductList();
    }

    private class ProductListener implements ProductClickListener {

        @Override
        public void OnProductClick(int position) {
            ProductDetailFragment productDetailFragment = new ProductDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable(EndPointAPI.productDetails, productLists.get(0));
            productDetailFragment.setArguments(bundle);
            ((HomeActivity) getActivity()).loadFragment(productDetailFragment, true);
        }
    }
}
