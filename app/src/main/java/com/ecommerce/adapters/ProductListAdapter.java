package com.ecommerce.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ecommerce.R;
import com.ecommerce.listners.ProductClickListener;
import com.ecommerce.models.ProductList;
import com.ecommerce.utilities.EndPointAPI;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ProductViewHolder> {
    Context context;
    ArrayList<ProductList> productLists;
    ProductClickListener productClickListener;

    public ProductListAdapter(Context context, ArrayList<ProductList> productLists, ProductClickListener productClickListener) {
        this.context = context;
        this.productLists = productLists;
        this.productClickListener = productClickListener;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_product_list_item, viewGroup, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, final int position) {
        productViewHolder.tvCategoryName.setText(productLists.get(position).getCategory_name() + "");
        productViewHolder.tvProductName.setText(productLists.get(position).getName() + "");
        productViewHolder.tvQuantity.setText("Qty." + productLists.get(position).getQty() + "");
        productViewHolder.tvPrice.setText("Rs." + productLists.get(position).getPrice() + "");
        Glide.with(context)
                .load(productLists.get(position).getImage() + EndPointAPI.GIF).asGif()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(productViewHolder.ivProduct);
        productViewHolder.cvLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productClickListener.OnProductClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productLists.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProduct;
        TextView tvProductName, tvCategoryName, tvQuantity, tvPrice;
        CardView cvLayout;

        public ProductViewHolder(View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.ivProduct);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            cvLayout = itemView.findViewById(R.id.cvLayout);
        }
    }
}
