package com.ecommerce.interfaces;


import com.android.volley.VolleyError;
import com.ecommerce.models.ProductList;

import java.util.ArrayList;

public interface NetworkRequest {
    public void OnSuccess(ArrayList<ProductList> productLists);

    public void OnError(VolleyError volleyError);
}
