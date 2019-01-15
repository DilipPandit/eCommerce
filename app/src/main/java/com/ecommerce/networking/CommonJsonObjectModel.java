package com.ecommerce.networking;

import java.io.Serializable;

public class CommonJsonObjectModel<T> implements Serializable {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
